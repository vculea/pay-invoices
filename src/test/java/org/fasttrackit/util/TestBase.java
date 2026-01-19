package org.fasttrackit.util;

import com.sdl.selenium.utils.config.WebDriverConfig;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

//@CucumberContextConfiguration
//@ContextConfiguration("classpath:cucumber.xml")
public abstract class TestBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestBase.class);

    public static WebDriver driver;

    static {
        if (WebDriverConfig.getDriver() == null) {
            try {
                Driver.initialize();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String dovezi() {
        return plati() + "Dovezi\\";
    }

    public static String facturi() {
        return plati() + "Facturi\\";
    }

    public static String deconturi() {
        return plati() + "Deconturi\\";
    }

    public static String decizii() {
        return plati() + "Decizii\\";
    }

    public static String csv() {
        return location() + "CSV\\";
    }

    public static String extrase() {
        return location() + "Extrase\\";
    }

    public static String plati() {
        return location() + "Plati\\";
    }

    public static String location() {
        return "C:\\Users\\vculea\\OneDrive - RWS\\Desktop\\Biserica\\2026\\";
    }

    public static String bt() {
        return "C:\\Users\\vculea\\Desktop\\BT\\2025\\";
    }
}
