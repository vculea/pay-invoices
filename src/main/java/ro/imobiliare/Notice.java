package ro.imobiliare;

import com.sdl.selenium.web.WebLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Notice {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebLocator.class);

    private String title;
    private String link;
    private String localizare;
    private String caracteristici;
    private String pret;
    private Long oneMeter;

    public Notice(String title, String link, String localizare, String caracteristici, String pret, Long oneMeter) {
        this.title = title;
        this.link = link;
        this.localizare = localizare;
        this.caracteristici = caracteristici;
        this.pret = pret;
        this.oneMeter = oneMeter;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getLocalizare() {
        return localizare;
    }

    public String getCaracteristici() {
        return caracteristici;
    }

    public String getPret() {
        return pret;
    }

    public Long getOneMeter() {
        return oneMeter;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "title='" + title + '\'' +
                ", localizare='" + localizare + '\'' +
                ", caracteristici='" + caracteristici + '\'' +
                ", pret='" + pret + '\'' +
                ", oneMeter=" + oneMeter +
                ", link='" + link + '\'' +
                '}';
    }

    public String toCSV() {
        return title +
                "| " + localizare +
                "| " + caracteristici +
                "| " + pret +
                "| " + oneMeter +
                "| " + link;
    }
}