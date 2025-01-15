package com.demoqa.enums;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public enum TextElementsRegister {
    REGISTER_TITLE1("//h1[@class='_h1_k0ma6_7']"),
    REGISTER_PARAGRAPH1("//p[@class='_titleP_k0ma6_13']"),
    REGISTER_TITLE2("//h1[@class='_h1_1p6u8_44']"),
    REGISTER_PARAGRAPH2("//p[@class='_p_1p6u8_51']");

    private final By locator;
    private final String xpath;

    TextElementsRegister(String xpath) {
        this.locator = By.xpath(xpath);
        this.xpath = xpath;
    }

    public WebElement getElement(WebDriver driver) {
        return driver.findElement(locator);
    }

    public By getLocator() {
        return locator;
    }

    public String getXpath() {
        return xpath;
    }
}
