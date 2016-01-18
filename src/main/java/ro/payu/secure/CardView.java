package ro.payu.secure;

import com.sdl.selenium.WebLocatorUtils;
import com.sdl.selenium.utils.config.WebDriverConfig;
import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.button.InputButton;
import com.sdl.selenium.web.form.ComboBox;
import com.sdl.selenium.web.form.TextField;

public class CardView extends WebLocator {
    private final String LABEL_POSITION = "/parent::*/following-sibling::td[1]//";

    private TextField numberField = new TextField().setLabel("Numărul cardului:").setLabelTag("span").setLabelPosition(LABEL_POSITION);
    private TextField ownerField = new TextField().setLabel("Titular card:").setLabelTag("span").setLabelPosition(LABEL_POSITION);
    private ComboBox monthField = new ComboBox().setLabel("Data de expirare a cardului:").setLabelTag("span").setLabelPosition(LABEL_POSITION).setPosition(1);
    private ComboBox yearField = new ComboBox().setLabel("Data de expirare a cardului:").setLabelTag("span").setLabelPosition(LABEL_POSITION).setPosition(2);
    private TextField cvv2Field = new TextField().setLabel("Codul CVV2/CVC2 al cardului:").setLabelTag("span").setLabelPosition(LABEL_POSITION);

    private InputButton payButton = new InputButton().setText("Plateste");

    private WebLocator successMessageElement = new WebLocator().setText("Plata s-a inregistrat cu succes!", SearchType.CHILD_NODE);

    public void setValues(String number, String cvv, String month, String year, String owner) {
        numberField.setValue(number);
        cvv2Field.setValue(cvv);
        monthField.select(month);
        yearField.select(year);
        ownerField.setValue(owner);
    }

    public void switchToPopup() {
        WebLocator popup = new WebLocator().setClasses("popup");
        WebLocator iframe = new WebLocator(popup).setTag("iframe");
        iframe.assertReady();
        WebDriverConfig.getDriver().switchTo().frame(iframe.currentElement);
    }

    public void successfullyPaid() {
        successMessageElement.assertReady();
    }

    public static void main(String[] args) {
        CardView orderView = new CardView();
//        WebLocatorUtils.getXPathScript(orderView);

        WebLocatorUtils.getXPathScript(orderView.successMessageElement);
    }
}