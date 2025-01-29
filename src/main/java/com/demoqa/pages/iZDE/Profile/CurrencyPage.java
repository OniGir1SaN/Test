package com.demoqa.pages.iZDE.Profile;

import com.demoqa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CurrencyPage extends BasePage {

    @FindBy(xpath = "(//button[@class='_btnCur_whfjl_47'])[1]")
    public WebElement kgsBtn;

    @FindBy(xpath = "(//button[@class='_btnCur_whfjl_47'])[2]")
    public WebElement usdBtn;

    @FindBy(xpath = "(//button[@class='_btnCur_whfjl_47'])[2]")
    public WebElement eurBtn;

    @FindBy(className = "_btnCurActive_whfjl_72")
    private WebElement currentCurrency;

    @FindBy(className = "_btn_10vqr_1")
    public WebElement saveBtn;

    public void clickSave() {
        if (saveBtn.isEnabled() && saveBtn.isDisplayed()) {
            webElementActions.click(saveBtn);
        } else {
            throw new IllegalStateException("Save button is not enabled or visible");
        }
    }

    public WebElement getCurrentCurrencyElement() {
        return currentCurrency;
    }

    public void clickCurrencyIfNotSelected(WebElement currencyBtn) {
        if (!currencyBtn.equals(getCurrentCurrencyElement())) {
            webElementActions.click(currencyBtn);
        }
    }
}
