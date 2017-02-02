package ro.jira;

import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.button.Button;
import com.sdl.selenium.web.form.TextField;
import com.sdl.selenium.web.link.WebLink;

public class LoginView extends WebLocator {

    public LoginView() {
        setTag("form");
        setId("portal_login");
    }

    private WebLocator container = new WebLocator().setClasses("container", "hidden-sm", "hidden-xs");
    private WebLink contLink = new WebLink(container).setClasses("cont-dashboard");
    private TextField userNameField = new TextField(this).setId("sUsername");
    private TextField passwordField = new TextField(this).setId("sPassword");
    private Button loginButton = new Button(this, "Intră în cont", SearchType.DEEP_CHILD_NODE_OR_SELF);

    public void login(String user, String pass) {
        contLink.click();
        ready();
        userNameField.setValue(user);
        passwordField.setValue(pass);
        loginButton.click();
    }
}
