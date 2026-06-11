package ro.btgo;

import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.button.Button;
import com.sdl.selenium.web.form.TextField;

public class ComboBox extends WebLocator {

    public ComboBox() {
        setTag("fba-generic-dropdown");
    }

    public ComboBox(WebLocator container) {
        this();
        setContainer(container);
    }

    public boolean select(String value) {
        click();
        WebLocator menu = new WebLocator().setClasses("dropdown-menu", "show");
        Button option = new Button(menu).setAttribute("title", value);
        return option.click();
    }
}
