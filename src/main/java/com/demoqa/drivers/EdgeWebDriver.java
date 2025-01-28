package com.demoqa.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

import static com.demoqa.utils.ConfigReader.getValue;

public class EdgeWebDriver {

    public static WebDriver loadEdgeDriver() {
        WebDriverManager.edgedriver().setup();

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-extensions");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        if (Boolean.parseBoolean(getValue("headless"))) {
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
        }

        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        return driver;
    }
}
