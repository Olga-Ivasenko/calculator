package utils;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import io.github.bonigarcia.wdm.managers.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {
    private static WebDriver driver;
    public static WebDriver getDriver() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            browser = PropertiesReader.getProperty("browser");
        }
        if (driver == null || ((RemoteWebDriver) driver).getSessionId() == null) {
            switch (browser) {
                case "chrome":
                    ChromeDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "headless":
                default:
                    ChromeDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");
                    options.addArguments("--start-maximized");
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--no-sandbox");
                    driver = new ChromeDriver(options);
            }
        }
        return driver;
    }
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}