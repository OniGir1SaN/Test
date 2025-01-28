package com.demoqa.drivers;

import com.demoqa.utils.ConfigReader;
import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            switch (ConfigReader.getValue("browser").toLowerCase()) {
                case "chrome":
                    driver.set(ChromeWebDriver.loadChromeWebDriver());
                    break;
                case "opera":
                    driver.set(OperaWebDriver.loadOperaDriver());
                    break;
                case "edge":
                    driver.set(EdgeWebDriver.loadEdgeDriver());
                    break;
                case "firefox":
                    driver.set(FireFoxWebDriver.loadFireFoxDriver());
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        return driver.get();
    }

    public static void closeDriver() {
        try {
            if (driver.get() != null) {
                driver.get().close();
                driver.get().quit();
                driver.remove();
            }
        } catch (Exception e) {
            System.err.println("Error while closing driver");
        }
    }
}
