package ro.imobiliare;

import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.link.WebLink;

public class SearchView extends WebLocator {

    public SearchView() {
        setClasses("cautarile-mele-dashboard");
    }

    private WebLink mySearch = new WebLink(this).setAttribute("href", "/lista-50210554");

    public void mySearch() {
        mySearch.click();
    }
}
