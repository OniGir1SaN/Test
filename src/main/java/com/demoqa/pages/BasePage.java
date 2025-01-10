package com.demoqa.pages;

import com.demoqa.drivers.DriverManager;
import com.demoqa.helper.BrowserHelper;
import com.demoqa.helper.DropDownHelper;
import com.demoqa.helper.WebElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import javax.swing.text.AbstractDocument;

public class BasePage {

    protected WebDriver driver;
    protected WebElementActions webElementActions;
    protected BrowserHelper browserHelper;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.webElementActions = new WebElementActions(driver);
        this.browserHelper = new BrowserHelper(driver);
        PageFactory.initElements(driver, this);
    }

public DropDownHelper dropDownHelper = new DropDownHelper(DriverManager.getDriver());
}

