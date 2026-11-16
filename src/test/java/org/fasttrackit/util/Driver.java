package org.fasttrackit.util;

import com.sdl.selenium.utils.config.WebDriverConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class Driver {
    public static WebDriver driver;

    public static void initialize() throws IOException {
        if (driver == null) {
            driver = createDriver();
            try {
                String downloadPath = WebDriverConfig.getDownloadPath();
                File download = new File(downloadPath.substring(0, downloadPath.lastIndexOf("\\") + 1));
                FileUtils.cleanDirectory(download);
                File directory = new File(downloadPath);
                FileUtils.forceMkdir(directory);
            } catch (IOException e) {
                log.error("{}", e.toString());
            }
        }
    }

    private static WebDriver createDriver() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = Driver.class.getClassLoader().getResourceAsStream("chrome.properties")) {
            if (input == null) {
                throw new IOException("Cannot find chrome.properties");
            }
            properties.load(input);
        }

        String originalArguments = System.getProperty("options.arguments");
        String arguments = originalArguments != null ? originalArguments : properties.getProperty("options.arguments");
        String profilePath = new File(".chrome-profile").getAbsolutePath();
        if (arguments.contains("--user-data-dir=.chrome-profile") && profilePath.contains(" ")) {
            throw new IOException("Chrome profile path cannot contain spaces: " + profilePath);
        }
        try {
            System.setProperty("options.arguments", arguments.replace(
                    "--user-data-dir=.chrome-profile", "--user-data-dir=" + profilePath));
            return WebDriverConfig.getWebDriver("chrome.properties");
        } finally {
            if (originalArguments == null) {
                System.clearProperty("options.arguments");
            } else {
                System.setProperty("options.arguments", originalArguments);
            }
        }
    }

    public static void initialize(boolean newDriver) throws IOException {
        stop(newDriver);
        initialize();
    }

    public static void stop(boolean newDriver) {
        if (newDriver && driver != null) {
            driver.quit();
            driver = null;
        }
    }
}