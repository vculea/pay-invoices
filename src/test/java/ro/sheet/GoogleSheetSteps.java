package ro.sheet;

import com.google.common.base.Strings;
import com.sdl.selenium.web.utils.Utils;
import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;
import org.fasttrackit.util.AppUtils;
import org.fasttrackit.util.TestBase;
import org.fasttrackit.util.UserCredentials;
import ro.neo.Storage;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class GoogleSheetSteps extends TestBase {

    private final AppUtils appUtils = new AppUtils();
    private final UserCredentials credentials = new UserCredentials();

    @And("I add in Facturi or Bonuri in google sheet:")
    public void iAddInFacturiOrBonuriInGoogleSheet(List<ItemTO> items) {
        for (ItemTO item : items) {
            String facturaPath = item.getType().equals("Dovada") ? dovezi() : facturi();
            String deciziilePath = Strings.isNullOrEmpty(item.getDecizia()) ? "" : decizii();
            String decontPath = Strings.isNullOrEmpty(item.getDecont()) ? "" : deconturi();
            appUtils.uploadFileAndAddRowInFacturiAndContForItem(item, facturaPath, deciziilePath, decontPath);
        }
    }

    @And("in Google Sheets I get all items from Factura")
    public void inANAFIGetAllItemsFromFractura() {
        List<List<Object>> values = appUtils.getValues(appUtils.getFacturiSheetId(), "2026!A1:H");
        List<RowRecord> list = values.stream().map(i -> {
            RowRecord rowRecord = new RowRecord(
                    (String) i.get(0),
                    (String) i.get(1),
                    (String) i.get(2),
                    (String) i.get(3),
                    (String) i.get(4),
                    (String) i.get(5),
                    i.size() != 7 ? "" : (String) i.get(6),
                    i.size() != 8 ? "" : (String) i.get(7)
            );
            return rowRecord;
        }).filter(i -> i.value().contains(",")).toList();
        List<RowRecord> existingItems = new ArrayList<>(list);
        Storage.set("items", existingItems);
    }

    @And("in Google Sheets I get all Cheltuieli from CSV")
    public void inGoogleSheetsIGetAllCheltuieliFromCSV() {
        List<List<Object>> values = appUtils.getValues(credentials.getContId(), "Cheltuieli!A2:B");
        List<CheltuieliTO> list = values.stream().map(i -> new CheltuieliTO(
                (String) i.get(0),
                List.of(((String) i.get(1)).split(","))
        )).toList();
        Storage.set("cheltuieli", list);
    }

    @And("in Google Sheets I get all Venituri from CSV")
    public void inGoogleSheetsIGetAllVenituriFromCSV() {
        List<List<Object>> values = appUtils.getValues(credentials.getContId(), "Venituri!A2:B");
        List<CheltuieliTO> list = values.stream().map(i -> new CheltuieliTO(
                (String) i.get(0),
                List.of(((String) i.get(1)).split(","))
        )).toList();
        Storage.set("venituri", list);
    }

    @And("in Google Sheets I get all PeopleBank from CSV")
    public void inGoogleSheetsIGetAllPeopleBankFromCSV() {
        List<List<Object>> values = appUtils.getValues(credentials.getContId(), "PeopleBank!A2:E");
        List<PeopleTO> list = values.stream().map(i -> new PeopleTO(
                (String) i.get(0),
                List.of(((String) i.get(1)).split(",")),
                i.size() < 4 ? List.of() : (((String) i.get(3)).contains(",")
                        ? Arrays.stream(((String) i.get(3)).split("\\s*,\\s*")).toList()
                        : List.of((String) i.get(3))),
                i.size() < 5 ? List.of() : splitByComma((String) i.get(4))
        )).toList();
        Storage.set("peopleBank", list);
    }

    @And("in Google Sheets I get all data from {string} CSV")
    public void inGoogleSheetsIGetAllDataFromCSV(String id) {
        Storage.set("csvFileId", id);
        List<List<Object>> values = appUtils.getValues(id, "A20:H");
        int startRowIndex = 19;
        List<DataTO> list = IntStream.range(0, values.size())
                .mapToObj(index -> {
                    List<Object> row = values.get(index);
                    return new DataTO(
                            startRowIndex + index,
                            toStringCell(row, 0),
                            toStringCell(row, 3),
                            toStringCell(row, 4),
                            toBigDecimalCell(row, 5),
                            toBigDecimalCell(row, 6),
                            toStringCell(row, 7)
                    );
                }).toList();
        Storage.set("data", list);
    }

    private String toStringCell(List<Object> row, int index) {
        if (row.size() <= index || row.get(index) == null) {
            return "";
        }
        return String.valueOf(row.get(index));
    }

    private BigDecimal toBigDecimalCell(List<Object> row, int index) {
        if (row.size() <= index || row.get(index) == null) {
            return BigDecimal.ZERO;
        }

        Object value = row.get(index);
        if (value instanceof Number number) {
            return BigDecimal.valueOf(number.doubleValue()).abs();
        }

        String text = String.valueOf(value).trim();
        if (text.isEmpty()) {
            return BigDecimal.ZERO;
        }

        String normalized = text.replace(" ", "");
        if (normalized.contains(",") && normalized.contains(".")) {
            normalized = normalized.replace(",", "");
        } else if (normalized.contains(",")) {
            normalized = normalized.replace(",", ".");
        }
        return new BigDecimal(normalized).abs();
    }

    private List<String> splitByComma(String value) {
        if (Strings.isNullOrEmpty(value)) {
            return List.of();
        }
        return Arrays.stream(value.split("\\s*,\\s*"))
                .map(String::trim)
                .filter(token -> !token.isEmpty())
                .toList();
    }

    private boolean hasMeaningfulNote(String note) {
        return note != null && !Strings.isNullOrEmpty(extractNoteText(note));
    }

    private boolean isDescriptionMatchingNote(String description, String note) {
        String noteText = extractNoteText(note);
        return !noteText.isEmpty() && description.contains(noteText);
    }

    private String extractNoteText(String note) {
        if (Strings.isNullOrEmpty(note)) {
            return "";
        }
        int open = note.indexOf('(');
        int close = note.lastIndexOf(')');
        if (open >= 0 && close > open) {
            return note.substring(open + 1, close).trim();
        }
        return note.trim();
    }

    private BigDecimal applyDonation(BigDecimal sum, List<String> donations, List<String> notes, int donationIndex, int noteIndex, Integer rowIndex, String fullName, List<InsertTO> insertValues) {
        String donation = donations.get(donationIndex);
        String subType = donation.substring(0, donation.indexOf("{"));
        BigDecimal donationSum = new BigDecimal(donation.substring(donation.indexOf("{") + 1, donation.indexOf("}")));
        BigDecimal appliedDonation = donationSum.min(sum);
        if (appliedDonation.compareTo(BigDecimal.ZERO) <= 0) {
            return sum;
        }
        BigDecimal remainingSum = sum.subtract(appliedDonation);
        insertValues.add(new InsertTO(
                "Venituri",
                subType,
                appliedDonation,
                "added",
                rowIndex,
                fullName
        ));
        donations.remove(donationIndex);
        if (noteIndex >= 0 && noteIndex < notes.size()) {
            notes.remove(noteIndex);
        }
        return remainingSum.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : remainingSum;
    }

    private void removeDonationAt(List<String> donations, List<String> notes, int donationIndex, int noteIndex) {
        if (donationIndex >= 0 && donationIndex < donations.size()) {
            BigDecimal donationAmount = parseAmountFromDonation(donations.get(donationIndex));
            boolean shouldRemoveDonation = true;
            if (donationAmount != null) {
                for (int index = 0; index < notes.size(); index++) {
                    if (index == noteIndex) {
                        continue;
                    }
                    BigDecimal noteAmount = parseAmountBeforeParenthesis(notes.get(index));
                    if (noteAmount != null && noteAmount.compareTo(donationAmount) == 0) {
                        shouldRemoveDonation = false;
                        break;
                    }
                }
            }
            if (shouldRemoveDonation) {
                donations.remove(donationIndex);
            }
        }
        if (noteIndex >= 0 && noteIndex < notes.size()) {
            notes.remove(noteIndex);
        }
    }

    private int findDonationIndexForNote(List<String> donations, String note, int fallbackIndex) {
        BigDecimal noteAmount = parseAmountBeforeParenthesis(note);
        if (noteAmount == null) {
            return fallbackIndex < donations.size() ? fallbackIndex : -1;
        }
        for (int donationIndex = 0; donationIndex < donations.size(); donationIndex++) {
            BigDecimal donationAmount = parseAmountFromDonation(donations.get(donationIndex));
            if (donationAmount != null && donationAmount.compareTo(noteAmount) == 0) {
                return donationIndex;
            }
        }
        return fallbackIndex < donations.size() ? fallbackIndex : -1;
    }

    private BigDecimal parseAmountFromDonation(String donation) {
        int open = donation.indexOf('{');
        int close = donation.lastIndexOf('}');
        if (open < 0 || close <= open) {
            return null;
        }
        return parseFlexibleAmount(donation.substring(open + 1, close));
    }

    private BigDecimal parseAmountBeforeParenthesis(String value) {
        if (Strings.isNullOrEmpty(value)) {
            return null;
        }
        int open = value.indexOf('(');
        String amount = open >= 0 ? value.substring(0, open) : value;
        return parseFlexibleAmount(amount);
    }

    private BigDecimal parseFlexibleAmount(String value) {
        if (Strings.isNullOrEmpty(value)) {
            return null;
        }
        String normalized = value.trim().replace(" ", "");
        if (normalized.isEmpty()) {
            return null;
        }
        if (normalized.contains(",") && normalized.contains(".")) {
            normalized = normalized.replace(",", "");
        } else if (normalized.contains(",")) {
            normalized = normalized.replace(",", ".");
        }
        try {
            return new BigDecimal(normalized).abs();
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @And("in Google Sheets I populate Verificare with data from Cheltuieli and Venituri")
    public void inGoogleSheetsIPopulateVerificareWithDataFromCheltuieliAndVenituri() {
        String verificationId = credentials.getVerificationId();
        String contId = credentials.getContId();
        String csvFileId = Storage.get("csvFileId");
        List<DataTO> data = Storage.get("data");
        List<CheltuieliTO> cheltuieli = Storage.get("cheltuieli");
        List<CheltuieliTO> venituri = Storage.get("venituri");
        List<PeopleTO> peopleBank = Storage.get("peopleBank");
        List<RowRecord> items = Storage.get("items");
        List<InsertTO> insertValues = new ArrayList<>();
        for (DataTO dataTO : data) {
            if (false && !dataTO.getStatus().isEmpty()) {
                continue;
            }
            if (dataTO.getType().contains("Plata")
                    || dataTO.getType().contains("Pachet")
                    || dataTO.getType().contains("Comision")
                    || dataTO.getType().contains("Notificari prin SMS")
                    || dataTO.getType().contains("Schimb valutar")
            ) {
                cheltuieli.stream()
                        .filter(c -> c.getValues().stream().anyMatch(value ->
                                dataTO.getDescription().contains(value) || dataTO.getType().contains(value)))
                        .findFirst()
                        .ifPresentOrElse(cheltuieliTO -> insertValues.add(new InsertTO(
                                "Cheltuieli",
                                cheltuieliTO.getCategory(),
                                dataTO.getDebit(),
                                "added",
                                dataTO.getRowIndex(),
                                ""
                        )), () -> {
                            List<RowRecord> matchingFacturi = items.stream()
                                    .filter(f -> parseFacturaValue(f.value()).compareTo(dataTO.getDebit()) == 0
                                            && sameExactDate(f.data(), dataTO.getDate()))
                                    .toList();
                            if (matchingFacturi.isEmpty()) {
                                matchingFacturi = items.stream()
                                        .filter(f -> parseFacturaValue(f.value()).compareTo(dataTO.getDebit()) == 0
                                                && sameYearMonth(f.data(), dataTO.getDate()))
                                        .toList();
                            }
                            if (matchingFacturi.isEmpty()) {
                                matchingFacturi = items.stream()
                                        .filter(f -> {
                                            BigDecimal facValue = parseFacturaValue(f.value());
                                            boolean isSameValue = facValue.subtract(dataTO.getDebit()).abs().compareTo(BigDecimal.ONE) < 0;
                                            boolean isSameDate = sameYearMonth(f.data(), dataTO.getDate());
                                            return isSameValue && isSameDate;
                                        })
                                        .toList();
                            }
                            if (matchingFacturi.size() == 1) {
                                insertValues.add(new InsertTO(
                                        "Cheltuieli",
                                        matchingFacturi.get(0).category(),
                                        dataTO.getDebit(),
                                        "added",
                                        dataTO.getRowIndex(),
                                        ""
                                ));
                            } else {
//                              Mai multe sau 0 comninatii, trebuie verificat!!!
                                Utils.sleep(1);
                            }
                        });
            } else if (dataTO.getType().contains("Incasare")
                    || dataTO.getType().contains("Depunere numerar ATM")
                    || dataTO.getType().contains("Dobanda depozit")
            ) {
                peopleBank.stream()
                        .filter(v -> v.getNames().stream().anyMatch(value -> dataTO.getDescription().contains(value)))
                        .findFirst()
                        .ifPresentOrElse(peopleTO -> {
                            BigDecimal sum = dataTO.getCredit();
                            List<String> donations = new ArrayList<>(peopleTO.getDonations());
                            List<String> notes = new ArrayList<>(peopleTO.getNote());

                            for (int index = 0; index < donations.size() && sum.compareTo(BigDecimal.ZERO) > 0; ) {
                                String note = index < notes.size() ? notes.get(index) : "";
                                if (!hasMeaningfulNote(note)) {
                                    index++;
                                    continue;
                                }
                                int donationIndex = findDonationIndexForNote(donations, note, index);
                                if (!isDescriptionMatchingNote(dataTO.getDescription(), note)) {
                                    removeDonationAt(donations, notes, donationIndex, index);
                                    continue;
                                }
                                sum = applyDonation(sum, donations, notes, donationIndex, index, dataTO.getRowIndex(), peopleTO.getFullName(), insertValues);
                            }

                            for (int index = 0; index < donations.size() && sum.compareTo(BigDecimal.ZERO) > 0; ) {
                                String note = index < notes.size() ? notes.get(index) : "";
                                if (hasMeaningfulNote(note)) {
                                    index++;
                                    continue;
                                }
                                sum = applyDonation(sum, donations, notes, index, -1, dataTO.getRowIndex(), peopleTO.getFullName(), insertValues);
                            }
                            if (sum.compareTo(BigDecimal.ZERO) > 0) {
                                insertValues.add(new InsertTO(
                                        "Venituri",
                                        "Zeciuieli",
                                        sum,
                                        "added",
                                        dataTO.getRowIndex(),
                                        peopleTO.getFullName()
                                ));
                            }
                        }, () -> {
                            venituri.stream()
                                    .filter(v -> v.getValues().stream().anyMatch(value -> dataTO.getDescription().contains(value)))
                                    .findFirst()
                                    .ifPresentOrElse(venituriTO -> insertValues.add(new InsertTO(
                                            "Venituri",
                                            venituriTO.getCategory(),
                                            dataTO.getCredit(),
                                            "added",
                                            dataTO.getRowIndex(),
                                            "din tab-ul Venituri"
                                    )), () -> {
//                                        Nu am gasit persoana!!!
                                        Utils.sleep(1);
                                    });
//
                        });
            } else if (dataTO.getType().contains("Transfer intern")) {
                insertValues.add(new InsertTO(
                        "",
                        "",
                        BigDecimal.ZERO,
                        "ignore",
                        dataTO.getRowIndex(),
                        ""
                ));

            } else {
                Utils.sleep(1);
            }
        }
        String date = data.get(0).getDate();
//        appUtils.addInVerificare(insertValues, csvFileId, verificationId, date);
        appUtils.addVenituriInCont(insertValues, contId, date);
        Map<String, List<LogInsertTO>> venituriBySubType = insertValues.stream()
                .filter(i -> "added".equals(i.getStatus()) && "Venituri".equals(i.getType()))
                .collect(Collectors.groupingBy(
                        InsertTO::getSubtype,
                        Collectors.mapping(i -> new LogInsertTO(i.getValue(), i.getNameCont()), Collectors.toList())
                ));

        LinkedHashMap<String, List<BigDecimal>> cheltuieliBySubType = insertValues.stream()
                .filter(i -> "added".equals(i.getStatus()) && "Cheltuieli".equals(i.getType()))
                .collect(Collectors.groupingBy(
                        InsertTO::getSubtype,
                        LinkedHashMap::new,
                        Collectors.mapping(InsertTO::getValue, Collectors.toList())
                ));
        Utils.sleep(1);
    }

    private BigDecimal parseFacturaValue(String value) {
        String normalized = value.replace(" ", "");
        if (normalized.contains(",") && normalized.contains(".")) {
            normalized = normalized.replace(".", "").replace(",", ".");
        } else if (normalized.contains(",")) {
            normalized = normalized.replace(",", ".");
        }
        try {
            return new BigDecimal(normalized).abs();
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }

    private boolean sameExactDate(String facturaDate, String transactionDate) {
        try {
            String fd = facturaDate.trim();
            String td = transactionDate.trim();
            LocalDate facturaLocalDate = LocalDate.parse(fd, DateTimeFormatter.ofPattern(AppUtils.detectDateFormat(fd)));
            LocalDate transactionLocalDate = LocalDate.parse(td, DateTimeFormatter.ofPattern(AppUtils.detectDateFormat(td)));
            return facturaLocalDate.isEqual(transactionLocalDate);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean sameYearMonth(String facturaDate, String transactionDate) {
        try {
            String fd = facturaDate.trim();
            String td = transactionDate.trim();
            LocalDate facturaLocalDate = LocalDate.parse(fd, DateTimeFormatter.ofPattern(AppUtils.detectDateFormat(fd)));
            LocalDate transactionLocalDate = LocalDate.parse(td, DateTimeFormatter.ofPattern(AppUtils.detectDateFormat(td)));
            return facturaLocalDate.getYear() == transactionLocalDate.getYear()
                    && facturaLocalDate.getMonth() == transactionLocalDate.getMonth();
        } catch (Exception e) {
            return false;
        }
    }
}
