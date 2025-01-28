package com.demoqa.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

import static com.demoqa.utils.ConfigReader.getValue;

public class FireFoxWebDriver {

    public static WebDriver loadFireFoxDriver() {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-extensions");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        if (Boolean.parseBoolean(getValue("headless"))) {
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
        }

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        return driver;
    }
}
