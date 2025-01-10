package com.demoqa.drivers;

import com.demoqa.utils.ConfigReader;
import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver(){
        if (driver == null){
            switch (ConfigReader.getValue("browser").toLowerCase()){
                case "chrome":
                    driver = ChromeWebDriver.loadChromeWebDriver();
                    break;
                case "opera":
                    driver = OperaWebDriver.loadOperaDriver();
                    break;
                case "edge":
                    driver = EdgeWebDriver.loadEdgeDriver();
                    break;
                case "fireFox":
                    driver = FireFoxWebDriver.loadFireFoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }return driver;
    }

    public static void closeDriver(){
        try {
            if (driver != null){
                driver.close();
                driver.quit();
                driver = null;
            }
        } catch (Exception e){
            System.err.println("Error while closing driver");
        }
    }
}
