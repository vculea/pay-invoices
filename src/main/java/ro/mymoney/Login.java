package ro.mymoney;

import com.sdl.selenium.extjs4.window.Window;
import com.sdl.selenium.extjs6.button.Button;
import com.sdl.selenium.extjs4.form.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Login extends Window {
    private static final Logger LOGGER = LoggerFactory.getLogger(Login.class);

    public Login() {
        super("Login");
    }

    private TextField emailField = new TextField(this).setLabel("E-mail:").setLabelPosition("/..//following-sibling::*//");
    private TextField passwordField = new TextField(this).setLabel("Password:").setLabelPosition("/..//following-sibling::*//");
    private Button loginButton = new Button(this, "Login");

    public void login(String user, String pass) {
        emailField.setValue(user);
        passwordField.setValue(pass);
        loginButton.click();
    }

    public static void main(String[] args) {
        Login login = new Login();
        LOGGER.debug(login.emailField.getXPath());
    }
}
