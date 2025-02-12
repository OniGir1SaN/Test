package com.demoqa.pages.iZDE;

import com.demoqa.pages.BasePage;
import com.demoqa.drivers.DriverManager;
import com.demoqa.helpers.WebElementActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainMenuPage extends BasePage {

    @FindBy(xpath = "//button[@class='_burgerMenuIcon_28rgb_106']")
    public WebElement burgerBtn;

    @FindBy(xpath = "//button[@class='_btn_1er5i_27'][1]")
    public WebElement searchBtn;

    @FindBy(xpath = "(//h4[@class='_menuText_1er5i_51'])[2]")
    public WebElement travellingBtn;

    @FindBy(xpath = "(//h4[@class='_menuText_1er5i_51'])[3]")
    public WebElement supportBtn;

    @FindBy(xpath = "//button[@class='_search_1tksv_73']")
    public WebElement startBtn;

    @FindBy(xpath = "//button[text()='Поиск']")
    public WebElement cellarSearchBtn;

    @FindBy(xpath = "//button[text()='Мои поездки']")
    public WebElement cellarTravellingBtn;

    @FindBy(xpath = "//button[text()='Поддержка']")
    public WebElement cellarSupportBtn;

    @FindBy(xpath = "//a[@class='_btnA_1t1ke_73']")
    public WebElement cellarVendorBtn;

    @FindBy(xpath = "//button[@class='_btnUser_28rgb_111']")
    public WebElement profileBtn;

    @FindBy(xpath = "//img[@alt='Play Market']")
    public WebElement cellarPlayMarketBtn;

    @FindBy(xpath = "//img[@alt='App Store']")
    public WebElement cellarAppStoreBtn;

    public MainMenuPage() {
        this.webElementActions = new WebElementActions(DriverManager.getDriver());
    }

    public void clickBurgerMenu() {
        webElementActions.click(burgerBtn);
    }

    public void clickSearchButton() {
        webElementActions.click(searchBtn);
    }

    public void clickTravellingButton() {
        webElementActions.click(travellingBtn);
    }

    public void clickSupportButton() {
        webElementActions.click(supportBtn);
    }

    public void clickProfileButton() {
        webElementActions.click(profileBtn);
    }

    public void clickStartButton() {
        webElementActions.click(startBtn);
    }

    public void clickCellarSearchButton() {
        webElementActions.click(cellarSearchBtn);
    }

    public void clickCellarTravellingButton() {
        webElementActions.click(cellarTravellingBtn);
    }

    public void clickCellarVendorButton() {
        webElementActions.click(cellarVendorBtn);
    }

    public void clickCellarSupportButton() {
        webElementActions.click(cellarSupportBtn);
    }

    public void clickCellarPlayMarketButton() {
        webElementActions.click(cellarPlayMarketBtn);
    }

    public void clickCellarAppStoreButton() {
        webElementActions.click(cellarAppStoreBtn);
    }
}
