package com.demoqa.pages.iZDE.Profile;

import com.demoqa.pages.iZDE.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LegalInformationPage extends BasePage {

    @FindBy(xpath = "//span[text()='ПОЛЬЗОВАТЕЛЬСКОЕ СОГЛАШЕНИЕ']")
    public WebElement ucText;

    @FindBy(xpath = "//span[text()='ПОЛИТИКА КОНФИДЕНЦИАЛЬНОСТИ']")
    public WebElement pcText;

    @FindBy(xpath = "//span[text()='ПУБЛИЧНАЯ ОФЕРТА']")
    public WebElement poText;

    @FindBy(xpath = "(//p[@class='_menuText_3fts8_40'])[1]")
    public WebElement usBtn;

    @FindBy(xpath = "(//p[@class='_menuText_3fts8_40'])[2]")
    public WebElement pcBtn;

    @FindBy(xpath = "(//p[@class='_menuText_3fts8_40'])[3]")
    public WebElement poBtn;

    public void clickUserAgreementButton() {
        webElementActions.click(usBtn);
    }

    public void clickPrivacyPolicyButton() {
        webElementActions.click(pcBtn);
    }

    public void clickPublicOfferButton() {
        webElementActions.click(poBtn);
    }

    public String getUserAgreementText() {
        return ucText.getText();
    }

    public String getPrivacyPolicyText() {
        return pcText.getText();
    }

    public String getPublicOfferText() {
        return poText.getText();
    }
}
