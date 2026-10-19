package ro.transcrieri;

import com.sdl.selenium.Go;
import com.sdl.selenium.utils.config.WebDriverConfig;
import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.button.Button;
import com.sdl.selenium.web.button.InputButton;
import com.sdl.selenium.web.form.CheckBox;
import com.sdl.selenium.web.form.ComboBox;
import com.sdl.selenium.web.form.TextField;
import com.sdl.selenium.web.link.WebLink;
import com.sdl.selenium.web.utils.Retry;
import com.sdl.selenium.web.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;

import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
public class Transcrieri {
    private final WebLocator programare = new WebLocator().setText("Programare On-line").setClasses("prima_meniu");
    private final WebLocator containerOre = new WebLocator().setClasses("ore").setAttribute("style", "display: block;", SearchType.CONTAINS);
    private final WebLocator informare = new WebLocator().setClasses("ui-dialog").setVisibility(true);
    private final Button close = new Button(informare).setClasses("ui-dialog-titlebar-close");
    private final Button continueButton = new Button(informare).setId("btn_continue3");
    private final WebLocator dayEl = new WebLocator().setClasses("af", "af_da", "ok").setExcludeClasses("blocat_runda").setInfoMessage("ziua");
    private final WebLocator oreEl = new WebLocator(containerOre).setClasses("min", "mliber").setExcludeClasses("mocupat", "mhold");
    private final WebLocator dateCompletate = new WebLocator().setClasses("date_sc");
    private final WebLocator radio = new WebLocator(dateCompletate).setClasses("tip_act");
    private final WebLocator label3 = new WebLocator(radio).setTag("label").setAttribute("for", "act3"); // Ambele
    private final TextField nameSiPrenume = new TextField().setId("nume00");
    private final TextField nrCertificatCetatenie = new TextField().setId("nr_cet0");
    private final TextField dataCertificat = new TextField().setId("data_cet0");
    private final TextField taraEl = new TextField().setId("tara_cet0");
    private final WebLocator taraTip = new WebLocator().setClasses("ui-menu-item-wrapper").setText("Ucraina");
    private final TextField emailEl = new TextField().setId("email0");
    private final InputButton incarcaFisierulEl = new InputButton().setId("but_up_fis0");
    private final WebLocator dragAndDrop = new WebLocator().setId("cos_upload");
    private final WebLocator upload = new WebLocator().setTag("input").setType("file").setClasses("dz-hidden-input");
    private final CheckBox acord1 = new CheckBox().setId("acord0");
    private final CheckBox acord2 = new CheckBox().setId("termen0");
    private final InputButton continua = new InputButton().setId("inreg");
    private final WebLocator acordLicenta = new WebLocator().setClasses("acord_licenta");
    private final WebLocator eula = new WebLocator().setClasses("container_eula");
    private final WebLocator contract = new WebLocator(acordLicenta).setText("Pentru orice întrebări sau probleme, utilizatorii pot contacta S.P.C.L.E.P. ORADEA, Compartimentul Stare Civilă.").setTag("p");
    private final Button continueButonEl = new Button().setId("btn_continue2");

    public static void clickElement(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) WebDriverConfig.getDriver();
        jse.executeScript("arguments[0].click();", element);
    }

    public void make(List<Item> items) {
        String folder = "C:\\Users\\vculea\\OneDrive - RWS\\Desktop\\Transcrieri";
        boolean exists = Paths.get(folder, items.get(0).file()).toFile().exists();
//        WebLocator licentaEl = new WebLocator().setId("acord_lic");
//        WebLocator.getExecutor().executeScript(
//                "arguments[0].scrollTop = arguments[0].scrollHeight;",
//                licentaEl.currentElement
//        );

        WebDriver driver = WebDriverConfig.getDriver();
        Actions actions = new Actions(driver);
        WebLocator acord = new WebLocator().setId("acord_lic");
        WebLocator acord3 = new WebLocator().setId("btn_accept_acord");
        boolean waitAndRefresh = Retry.retry(1, () -> {
            driver.navigate().refresh();
            acord.ready(Duration.ofSeconds(40));
            acord3.scrollIntoView(Go.NEAREST);
            actions.moveToElement(acord.currentElement).perform();
            while (acord.getAttribute("scrollTop") == null
                    || Double.parseDouble(acord.getAttribute("scrollTop"))
                    + acord.getSize().getHeight()
                    < ((Number) ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].scrollHeight", acord.currentElement)).doubleValue()) {
                actions.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(acord.currentElement), 0, 250).perform();
            }
            acord3.scrollIntoView(Go.NEAREST);
            String attribute = acord3.getAttribute("disabled");
            return attribute == null || !attribute.equals("true");
        });
        if (!waitAndRefresh) {
            log.info("Elementul acord3 nu a devenit activ după 3 încercări.");
        }
        acord3.click();
        Retry.retry(15, () -> {
            boolean isVisible = informare.isPresent();
            if (isVisible) {
                close.click();
            }
            return isVisible;
        });
        for (Item item : items) {
            boolean result = Retry.retry(Duration.ofSeconds(5), dayEl::isPresent);
            if (result) {
                boolean selectedDay = isSelectedDay();
                if (selectedDay) {
                    Retry.retry(30, () -> {
                        boolean doClick = oreEl.doClick();
                        if (doClick && informare.isPresent()) {
                            close.click();
                        } else if (doClick) {
                            return true;
                        }
                        return false;
                    });
                } else {
                    selectedDay = isSelectedDay();
                    if (selectedDay) {
                        Retry.retry(30, () -> {
                            boolean doClick = oreEl.doClick();
                            if (doClick && informare.isPresent()) {
                                close.click();
                            } else if (doClick) {
                                return true;
                            }
                            return false;
                        });
                    } else {
                        log.info("Nu s-a putut selecta ziua pentru item-ul: " + item);
                    }
                }
            } else {
                log.info("Nu s-a putut selecta ziua pentru item-ul: " + item);
            }
//Verifying...
//            oreEl.setResultIdx(7); // 1-8


            label3.click(); // Tipul actului de transcris

            typeEachChar(item.name().toUpperCase(), nameSiPrenume, 65);
            Utils.sleep(90);
            typeEachChar(item.nr(), nrCertificatCetatenie, 55);
            selectDate(item);
            taraEl.setValue(item.tara());
            taraTip.click();
            Utils.sleep(20);
            typeEachChar(item.email(), emailEl, 55);

            acord1.check(true);
            acord2.check(true);

            incarcaFisierulEl.click();
            Utils.sleep(200);
            String pathFile = Paths.get(folder, item.file()).toString();
            upload.sendKeys(pathFile);

            continua.click();
            Utils.sleep(1);
//        for captcha ->    https://stackoverflow.com/questions/18935696/how-to-read-the-text-from-image-captcha-by-using-selenium-webdriver-with-java
        }
    }

    private boolean isSelectedDay() {
        boolean selectOra = Retry.retry(30, () -> {
            dayEl.click();
            boolean present = oreEl.isPresent();
            if (!present) {
                Utils.sleep(1100);
            }
            return present;
        });
        return selectOra;
    }

    private void selectDate(Item item) {
        dataCertificat.click();
        LocalDate now = LocalDate.parse(item.data(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String year = now.getYear() + "";
        ComboBox yearEl = new ComboBox().setClasses("ui-datepicker-year");
        yearEl.selectByValue(year);
//        yearEl.selectByIndex(4);
        ComboBox monthEl = new ComboBox().setClasses("ui-datepicker-month");
        int month = now.getMonthValue() - 1;
        monthEl.selectByIndex(month);
        String day = now.getDayOfMonth() + "";
        WebLocator dayCalendarEl = new WebLocator().setTag("td").setAttribute("data-handler", "selectDay");
        WebLink dayEl = new WebLink(dayCalendarEl).setText(day).setClasses("ui-state-default");
        Retry.retry(2, dayEl::doClick);
    }

    private static void typeEachChar(String value, TextField textField, int delay) {
        char[] chars = value.toCharArray();
        for (char aChar : chars) {
            Utils.sleep(delay);
            textField.doSendKeys(aChar + "");
        }
    }
}
