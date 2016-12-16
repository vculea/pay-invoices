package ro.btrl;

import com.sdl.selenium.web.button.InputButton;
import com.sdl.selenium.web.form.TextField;

public class Secure3DPassword {

    private TextField passwordField = new TextField().setName("PASSWORD").setVisibility(true);
    private InputButton continueButton = new InputButton(null, "Continua");

    public TextField getPasswordField() {
        return passwordField;
    }

    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    public void clickContinue() {
        continueButton.click();
    }
}
