package org.fasttrackit.util;

import com.sdl.selenium.materialui.button.Button;
import com.sdl.selenium.materialui.dialog.Dialog;
import com.sdl.selenium.materialui.form.ComboBox;
import com.sdl.selenium.materialui.form.TextField;
import com.sdl.selenium.utils.config.WebDriverConfig;
import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.button.UploadButton;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.sheet.ItemTO;

@Slf4j
@Getter
public class AdministratieFinanciara extends WebLocator {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdministratieFinanciara.class);
    private final Button addTransactionButton = new Button(this, "Adaugă tranzacție");
    private final Dialog addTransactionDialog = new Dialog("Adaugă tranzacție");
    private final ComboBox contEl = new ComboBox(addTransactionDialog).setResultIdx(2);
    private final TextField amountEl = new TextField(addTransactionDialog).setName("amount");
    private final WebLocator dateEl = new WebLocator(addTransactionDialog).setTag("input").setName("date");
    private final WebLocator categoryEl = new WebLocator(addTransactionDialog).setTag("input").setName("categoryId");
    private final UploadButton uploadButton = new UploadButton(addTransactionDialog).setType("file");
    private final ComboBox tipDocumentEl = new ComboBox(addTransactionDialog).setResultIdx(3);
    private final Button saveButton = new Button(addTransactionDialog, "Salvează").setType("submit");

    public AdministratieFinanciara() {
        setClasses("transactions-page");
    }

    public void openUrl(String url) {
        LOGGER.info("open {}", url);
        WebDriverConfig.getDriver().get(url);
    }

    public void addTransaction(ItemTO item, String facturaPath) {
        openUrl("http://localhost:5173/transactions");
        addTransactionButton.click();
        contEl.select("Cont (RON)");
        amountEl.sendKeys(item.getValue());
        dateEl.sendKeys(item.getData());
        String category = switch (item.getCategory()) {
            case "Copiii" -> "Copii";
            default -> item.getCategory();
        };
        categoryEl.sendKeys(category);
        categoryEl.sendKeys(Keys.ENTER);
        uploadButton.uploadFile(facturaPath);
        String tipFinal = switch (item.getType()) {
            case "Factura" -> "Factură";
            case "Bon cu CUI" -> "Bon fiscal";
            default -> throw new IllegalArgumentException("Invalid tip: " + item.getType());
        };
        tipDocumentEl.select(tipFinal);
        saveButton.click();
    }


}
