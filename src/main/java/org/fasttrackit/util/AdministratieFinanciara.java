package org.fasttrackit.util;

import com.sdl.selenium.materialui.button.Button;
import com.sdl.selenium.materialui.dialog.Dialog;
import com.sdl.selenium.materialui.form.ComboBox;
import com.sdl.selenium.materialui.form.TextField;
import com.sdl.selenium.utils.config.WebDriverConfig;
import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.button.UploadButton;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ro.sheet.ItemTO;

import java.time.Duration;

@Slf4j
@Getter
public class AdministratieFinanciara extends WebLocator {
    private final Button continuaButton = new Button(null, "Continuă cu Google", SearchType.DEEP_CHILD_NODE_OR_SELF);
    private final Button addTransactionButton = new Button(this, "Adaugă tranzacție");
    private final Dialog addTransactionDialog = new Dialog("|Adaugă tranzacție|Editează tranzacția", SearchType.CONTAINS_ANY);
    private final ComboBox contEl = new ComboBox(addTransactionDialog).setResultIdx(2);
    private final TextField amountEl = new TextField(addTransactionDialog).setName("amount");
    private final WebLocator dateEl = new WebLocator(addTransactionDialog).setTag("input").setName("date");
    private final WebLocator categoryEl = new WebLocator(addTransactionDialog).setTag("input").setName("categoryId");
    private final WebLocator descriptionEl = new WebLocator(addTransactionDialog).setTag("textarea").setName("description");
    private final UploadButton uploadFacturaEl = new UploadButton(addTransactionDialog).setType("file");
    private final UploadButton uploadDovadaEl = new UploadButton(addTransactionDialog).setType("file").setAttribute("aria-label", "Fișier: Dovadă de plată");
    private final UploadButton uploadDecontEl = new UploadButton(addTransactionDialog).setType("file").setAttribute("aria-label", "Fișier: Decont");
    private final ComboBox tipDocumentEl = new ComboBox(addTransactionDialog).setResultIdx(3);
    private final Button saveButton = new Button(addTransactionDialog, "Salvează").setType("submit");

    public AdministratieFinanciara() {
        setClasses("transactions-page");
    }

    public void openUrl(String url) {
        log.info("open {}", url);
        WebDriverConfig.getDriver().get(url);
    }

    public void addTransaction(ItemTO item, String facturaPath, String decontPath, String dovadaFilePath) {
        openUrl("http://localhost:5173/transactions");
        ensureAuthenticated(WebDriverConfig.getDriver(), continuaButton::click);
        addTransactionButton.click();
        contEl.select("Cont (RON)");
        amountEl.sendKeys(item.getValue());
        dateEl.sendKeys(item.getData());
        String category = switch (item.getCategory()) {
            case "Copiii" -> "Copii";
            case "Gunoi" -> "Salubritate";
            case "TeenChallengeOut" -> "TeenChallenge";
            case "FelixOut" -> "Caminul Felix";
            case "TanzaniaOut" -> "Tanzania";
            case "SomethingNewOut" -> "Something New";
            case "Femei" -> "MomCo";
            default -> item.getCategory();
        };
        categoryEl.sendKeys(category);
        categoryEl.sendKeys(Keys.ENTER);
        descriptionEl.sendKeys(item.getDescription());
        if (facturaPath != null) {
            String tipFinal = switch (item.getType()) {
                case "Factura" -> "Factură";
                case "Bon cu CUI" -> "Bon fiscal";
                default -> throw new IllegalArgumentException("Invalid tip: " + item.getType());
            };
            tipDocumentEl.select(tipFinal);
            uploadFacturaEl.uploadFile(facturaPath);
        }
        if (decontPath != null) {
            uploadDecontEl.uploadFile(decontPath);
        }
        if (dovadaFilePath != null) {
            uploadDovadaEl.uploadFile(dovadaFilePath);
        }
        saveButton.click();
    }

    static void ensureAuthenticated(WebDriver driver, Runnable signIn) {
        By loginButton = By.xpath("//button[normalize-space(.)='Continuă cu Google']");
        By transactionButton = By.xpath("//button[normalize-space(.)='Adaugă tranzacție']");

        AuthenticationState state = new WebDriverWait(driver, Duration.ofSeconds(30)).until(currentDriver -> {
            if (isVisible(currentDriver, transactionButton)) {
                return AuthenticationState.AUTHENTICATED;
            }
            if (isVisible(currentDriver, loginButton)) {
                return AuthenticationState.LOGIN_REQUIRED;
            }
            return null;
        });

        if (state == AuthenticationState.LOGIN_REQUIRED) {
            log.info("Conectați-vă manual cu Google în browser; aștept finalizarea autentificării.");
            signIn.run();
            new WebDriverWait(driver, Duration.ofMinutes(5))
                    .until(currentDriver -> isVisible(currentDriver, transactionButton));
        }
    }

    private static boolean isVisible(WebDriver driver, By locator) {
        return driver.findElements(locator).stream().anyMatch(element -> element.isDisplayed());
    }

    private enum AuthenticationState {
        AUTHENTICATED,
        LOGIN_REQUIRED
    }

}
