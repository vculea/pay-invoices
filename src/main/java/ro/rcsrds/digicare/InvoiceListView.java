package ro.rcsrds.digicare;

import com.sdl.selenium.conditions.Condition;
import com.sdl.selenium.conditions.ConditionManager;
import com.sdl.selenium.conditions.ElementRemovedSuccessCondition;
import com.sdl.selenium.web.Position;
import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.button.Button;
import com.sdl.selenium.web.form.CheckBox;
import com.sdl.selenium.web.link.WebLink;
import com.sdl.selenium.web.table.Cell;
import com.sdl.selenium.web.table.Table;
import com.sdl.selenium.web.utils.Utils;
import org.openqa.selenium.InvalidElementStateException;

public class InvoiceListView extends WebLocator {
    private WebLocator invoiceMenuItem = new WebLocator().setText("Facturi");
    private WebLink invoicesListItem = new WebLink().setText("Listă Facturi", SearchType.TRIM, SearchType.CHILD_NODE);
    private Table invoices = new Table().setId("invoices");
    private Cell lastCell = invoices.getCell(1, new Cell(7, "neachitat")).setResultIdx(Position.LAST);
    private CheckBox checkBox = new CheckBox(lastCell);
    private Button payAllButton = new Button().setText("Plătiţi factura", SearchType.STARTS_WITH);
    private WebLink seeInvoices = new WebLink().setAttribute("data-request", "/invoices-list");

    private WebLocator promoPopup = new WebLocator().setClasses("popup");
    private WebLocator closePromo = new WebLocator(promoPopup).setClasses("close").setRenderMillis(500);

    private WebLocator promoPopup2 = new WebLocator().setClasses("ui-dialog", "ui-front");
    private WebLocator closePromo2 = new WebLocator(promoPopup2).setClasses("ui-dialog-titlebar-close");

    public void selectAll() {
        try {
            closePromo2.doClick();
            // TODO try temporary until retry is implemented in click
        } catch (InvalidElementStateException e) {
            Utils.sleep(500);
            closePromo2.doClick();
        }
        invoiceMenuItem.mouseOver();
        invoicesListItem.click();

        WebLocator waiting = new WebLocator().setText("Se încarcă...");
        ConditionManager conditionManager = new ConditionManager().add(new ElementRemovedSuccessCondition(waiting));
        Condition condition = conditionManager.execute();

        closePromo.doClick();
        if (seeInvoices.isElementPresent() && !invoices.ready()) {
            seeInvoices.click();
        }
        closePromo.doClick();
        checkBox.click();
    }

    public void payAll() {
        payAllButton.click();
    }
}
