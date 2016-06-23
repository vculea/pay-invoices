package ro.electricafurnizare.oficiulvirtual;

import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.button.InputButton;
import com.sdl.selenium.web.form.CheckBox;
import com.sdl.selenium.web.form.ComboBox;
import com.sdl.selenium.web.link.WebLink;

public class InvoiceListView extends WebLocator {
    private WebLink invoiceMenuItem = new WebLink().setText("Facturi / Plati", SearchType.TRIM, SearchType.CHILD_NODE);
    private WebLink payOnline = new WebLink().setText("Plateste Online", SearchType.TRIM, SearchType.CHILD_NODE).setVisibility(true);
    private ComboBox placeCombo = new ComboBox().setLabel("Cod Client:");
    private CheckBox selectAllButton = new CheckBox().setId("bifeazaTot");
    private InputButton payAllButton = new InputButton().setText("Continua plata");
    private InputButton confirmButton = new InputButton().setText("Confirmati plata online");

    public void selectAll(String place) {
        invoiceMenuItem.mouseOver();
        invoiceMenuItem.click();
        payOnline.click();
        placeCombo.select(place);
        selectAllButton.click();
    }

    public void payAll() {
        payAllButton.click();
        confirmButton.click();
    }
}
