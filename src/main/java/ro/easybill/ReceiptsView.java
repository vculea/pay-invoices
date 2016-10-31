package ro.easybill;

import com.sdl.selenium.bootstrap.button.Button;
import com.sdl.selenium.bootstrap.form.Form;
import com.sdl.selenium.utils.config.WebDriverConfig;
import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.button.InputButton;
import com.sdl.selenium.web.form.TextField;
import com.sdl.selenium.web.link.WebLink;

public class ReceiptsView extends Form {

    public ReceiptsView() {
        setId("genereazaChitanta");
    }

    private WebLink chitanteSiPlatiLink = new WebLink().setTitle("Chitante si plati");
    private Button emiteButton = new Button().setText("Emite chitanta");
    private WebLink emiteChitantaEl = new WebLink().setId("earnings-report").setText("Emite chitanta");
    private TextField periodEl = new TextField().setId("rangepicker");
    private TextField nameEl = new TextField(this).setName("firma");
    private TextField addressEl = new TextField(this).setName("adresa");
    private TextField amountEl = new TextField(this).setName("suma");
    private TextField typeEl = new TextField(this).setName("reprezentand");
    private InputButton submitButton = new InputButton(this).setName("Submit2");

    public void generateReceiptsFor(Customers customer) {
        emiteChitantaEl.assertReady();
        emiteChitantaEl.click();
        setCustomer(customer);
    }

    private void setCustomer(Customers customer) {
        ready();
        nameEl.setValue(customer.getName());
        addressEl.setValue(customer.getAddress());
        amountEl.setValue(customer.getAmount());
        typeEl.setValue("Donatie");
        submitButton.click();
        WebDriverConfig.switchToLastTab();
        WebDriverConfig.getDriver().close();
        WebDriverConfig.switchToFirstTab();
    }

    public void generateReceiptsForNew(Customers customer) {
        chitanteSiPlatiLink.ready(10);
        chitanteSiPlatiLink.click();
        periodEl.ready();
        emiteButton.click();
        setCustomer(customer);
    }
}
