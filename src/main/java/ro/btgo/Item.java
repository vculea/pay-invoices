package ro.btgo;

import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.form.TextField;

import java.time.Duration;

public class Item extends WebLocator {

    public Item() {
        setTag("fba-document-item");
    }

    public Item(WebLocator container) {
        this();
        setContainer(container);
    }

    public Item(WebLocator container, String title, String description) {
        this(container);
        WebLocator titleEl = new WebLocator().setTag("h6").setText(title, SearchType.TRIM);
        WebLocator descriptionEl = new WebLocator().setTag("h6").setClasses("text-grayscale-label").setText(description, SearchType.TRIM, SearchType.DEEP_CHILD_NODE_OR_SELF);
        setChildNodes(titleEl, descriptionEl);
    }

    public boolean download() {
        WebLocator download = new WebLocator(this).setAttribute("data-mat-icon-name", "downloadBulk");
        download.ready(Duration.ofSeconds(50));
        return download.click();
    }
}
