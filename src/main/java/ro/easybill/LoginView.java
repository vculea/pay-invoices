package ro.easybill;

import com.sdl.selenium.bootstrap.form.Form;
import com.sdl.selenium.web.button.InputButton;
import com.sdl.selenium.web.form.TextField;

public class LoginView extends Form {

    public LoginView() {
        setId("loginForm");
    }

    private TextField userNameField = new TextField(this).setName("xUsername");
    private TextField passwordField = new TextField(this).setName("xPassword");
    private InputButton loginButton = new InputButton(this).setId("loginButton");

    public boolean login(String user, String pass) {
        ready();
        userNameField.setValue(user);
        passwordField.setValue(pass);
        return loginButton.click();
    }
}
