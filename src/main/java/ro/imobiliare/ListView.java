package ro.imobiliare;

import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.link.WebLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ListView extends WebLocator {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebLocator.class);

    public ListView() {
        setClasses("box-anunt");
        setExcludeClasses("profesional");
    }

    private WebLocator title = new WebLocator(this).setTag("h2").setClasses("titlu-anunt");
    private WebLink link = new WebLink(title);
    private WebLocator localizare = new WebLocator(this).setClasses("localizare");
    private WebLocator caracteristici = new WebLocator(this).setTag("ul").setClasses("caracteristici");
    private WebLocator pret = new WebLocator(this).setClasses("pret");

    public void getData() {
        List<String> list = new ArrayList<>();
        StringBuilder ap;
        for (int i = 1; i < 11; i++) {
            setPosition(i);
            ap = new StringBuilder();
            String text = title.getText();
            ap.append(text).append(", ");
//            LOGGER.info("title: {}", text);
            String href = link.getAttribute("href");
            ap.append(href).append(", ");
//            LOGGER.info("link: {}", href);
            String localizareText = localizare.getText();
            ap.append(localizareText).append(", ");
//            LOGGER.info("localizare: {}", localizareText);
            String car = caracteristici.getText();
            if(car == null){
                LOGGER.debug("");
            }
            String[] split = car != null ? car.split("\n") : new String[0];
            String caracteristiciText = "";
            int mp = 0;
            for (String s : split) {
                caracteristiciText = caracteristiciText + " " + s;
                if(s.contains("mp")){
                    String s1 = s.split(" ")[0].replaceAll("\\.", "");
                    mp = Integer.parseInt(s1);
                }
            }
            ap.append(caracteristiciText).append(", ");
//            LOGGER.info("caracteristici: {}", caracteristiciText);
            String pretText = pret.getText();
            ap.append(pretText).append(", ");
            String s = pretText.split(" ")[0];
            pretText = s.replaceAll("\\.", "");
            int pr = Integer.parseInt(pretText);
            long ff = pr/mp;
            ap.append(ff).append(";");
//            LOGGER.info("pret: {}", pretText);
            list.add(ap.toString());
        }
        for (String anunt : list) {
            LOGGER.debug("Anunt: {}", anunt);
        }
    }
}
