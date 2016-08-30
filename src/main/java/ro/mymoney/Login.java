package ro.mymoney;

import com.sdl.selenium.extjs6.window.Window;
import com.sdl.selenium.extjs6.button.Button;
import com.sdl.selenium.extjs6.form.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Login extends Window {
    private static final Logger LOGGER = LoggerFactory.getLogger(Login.class);

    public Login() {
        super("Login");
    }

    private TextField emailField = new TextField(this, "E-mail:");
    private TextField passwordField = new TextField(this, "Password:");
    private Button logInButton = new Button(null, "LogIn");
    private Button loginButton = new Button(this, "Login");

    public void login(String user, String pass) {
        logInButton.click();
        ready();
        emailField.setValue(user);
        passwordField.setValue(pass);
        loginButton.click();
    }
}
