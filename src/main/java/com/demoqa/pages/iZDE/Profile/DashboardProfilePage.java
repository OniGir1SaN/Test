package com.demoqa.pages.iZDE.Profile;

import com.demoqa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardProfilePage extends BasePage {

    @FindBy(className = "_menuItem_1l60t_22._active_1l60t_52")
    public WebElement privateProfileBtn;

    @FindBy(xpath = "(//button[@class='_menuItem_1l60t_22 false'])[1]")
    public WebElement currencyBtn;

    @FindBy(xpath = "(//button[@class='_menuItem_1l60t_22 false'])[2]")
    public WebElement supportBtn;

    @FindBy(className = "_policyMenu_3fts8_1")
    public WebElement legalInformationBlock;

    @FindBy(className = "_btn_197z3_10")
    public WebElement exitBtn;

    @FindBy(className ="_btnExit_8hj97_80")
    public WebElement exitBtn2;

    public void clickCurrencyBtn() {
        webElementActions.click(currencyBtn);
    }

    public void clickSupportBtn() {
        webElementActions.click(supportBtn);
    }

    public void clickExitBtn() {
        webElementActions.click(exitBtn);
    }

    public void clickExitBtn2() {
        webElementActions.click(exitBtn2);
    }


}
