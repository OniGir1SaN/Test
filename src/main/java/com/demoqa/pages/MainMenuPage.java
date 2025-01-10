package com.demoqa.pages;

import com.demoqa.drivers.DriverManager;
import com.demoqa.helper.WebElementActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainMenuPage extends BasePage {

    private WebElementActions webElementActions;

    @FindBy(xpath = "//button[@class='_burgerMenuIcon_1e87t_85']")
    public WebElement burgerBtn;

    @FindBy(xpath = "//button[@class='_btn_1er5i_27'][1]")
    public WebElement searchBtn;

    @FindBy(xpath = "//button[@class='_btn_1er5i_27'][2]")
    public WebElement supportBtn;

    @FindBy(xpath = "//button[@class='_search_1tksv_73']")
    public WebElement startBtn;

    @FindBy(xpath = "//button[text()='Поиск']")
    public WebElement cellarSearchBtn;

    @FindBy(xpath = "//button[text()='Поддержка']")
    public WebElement cellarSupportBtn;

    @FindBy(xpath = "//button[text()='Правовая информация']")
    public WebElement cellarLegalInformationBtn;

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

    public void clickSupportButton() {
        webElementActions.click(supportBtn);
    }

    public void clickStartButton() {
        webElementActions.click(startBtn);
    }

    public void clickCellarSearchButton() {
        webElementActions.click(cellarSearchBtn);
    }

    public void clickCellarSupportButton() {
        webElementActions.click(cellarSupportBtn);
    }

    public void clickCellarLegalInformationButton() {
        webElementActions.click(cellarLegalInformationBtn);
    }

    public void clickCellarPlayMarketButton() {
        webElementActions.click(cellarPlayMarketBtn);
    }

    public void clickCellarAppStoreButton() {
        webElementActions.click(cellarAppStoreBtn);
    }
}
