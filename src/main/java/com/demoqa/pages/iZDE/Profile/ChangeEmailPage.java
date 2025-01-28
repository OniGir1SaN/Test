package com.demoqa.pages.iZDE.Profile;

import com.demoqa.entities.iZDE.ChangeEmailEntity;
import com.demoqa.pages.iZDE.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChangeEmailPage extends BasePage {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement currentPasswordInput;

    @FindBy(xpath = "//input[@placeholder='Введите почту']")
    private WebElement emailPlaceholder;

    @FindBy(xpath = "//input[@placeholder='Введите текущий пароль']")
    private WebElement currentPasswordPlaceholder;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveBtn;

    @FindBy(xpath = "//p[@class='_authInputErrorText_1hc7y_49']")
    public WebElement errorMessageElement;

    public String getErrorMessage() {
        if (errorMessageElement.isDisplayed()) {
            return errorMessageElement.getText();
        }
        return "";
    }

    public ChangeEmailPage fillUpChangeEmailForm(ChangeEmailEntity changeEmailEntity){
        webElementActions.sendKeys(emailInput, changeEmailEntity.getEmail())
                .sendKeys(currentPasswordInput, changeEmailEntity.getCurrentPassword())
                .click(saveBtn);
        return new ChangeEmailPage();
    }

    public void verifyFormChangeEmailPlaceholders() {
        WebElement[] elements = {emailPlaceholder, currentPasswordPlaceholder};
        String[] expectedPlaceholders = {"Введите почту", "Введите текущий пароль"};
        String[] fieldNames = {"Введите почту", "Введите текущий пароль"};

        webElementActions.verifyPlaceholders(elements, expectedPlaceholders, fieldNames);
    }

    public void clickSaveBtn(){
        webElementActions.click(saveBtn);
    }
}
