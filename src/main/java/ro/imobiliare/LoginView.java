package ro.imobiliare;

import com.sdl.selenium.bootstrap.form.Form;
import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.button.Button;
import com.sdl.selenium.web.button.InputButton;
import com.sdl.selenium.web.form.TextField;
import com.sdl.selenium.web.link.WebLink;

public class LoginView extends Form {

    public LoginView() {
        setId("login-form");
    }

    private TextField userNameField = new TextField(this).setName("os_username");
    private TextField passwordField = new TextField(this).setName("os_password");
    private InputButton loginButton = new InputButton(this).setName("login");

    public void login(String user, String pass) {
        ready();
        userNameField.setValue(user);
        passwordField.setValue(pass);
        loginButton.click();
    }
}
