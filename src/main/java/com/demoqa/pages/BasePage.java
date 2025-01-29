package com.demoqa.pages;

import com.demoqa.drivers.DriverManager;
import com.demoqa.helpers.BrowserHelper;
import com.demoqa.helpers.DropDownHelper;
import com.demoqa.helpers.WebElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebElementActions webElementActions;
    protected BrowserHelper browserHelper;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.webElementActions = new WebElementActions(driver);
        this.browserHelper = new BrowserHelper(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public DropDownHelper dropDownHelper = new DropDownHelper(DriverManager.getDriver());
}
