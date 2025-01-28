package com.demoqa.pages.iZDE;

import com.demoqa.entities.iZDE.ChangePasswordEntity;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResetPasswordPage extends BasePage {

    @FindBy(xpath = "//input[@type='email']")
    public WebElement emailInput;

    @FindBy(xpath = "//input[@placeholder='Введите почту']")
    public WebElement placeholderEmail;

    @FindBy(xpath = "//input[@type='text']")
    public WebElement codeInput;

    @FindBy(xpath = "//input[@placeholder='Введите код']")
    public WebElement placeholderCode;

    @FindBy(xpath = "//p[@class='errorInputMessage']")
    public WebElement errorCodeMessageElement;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(id = "password2")
    public WebElement password2Input;

    @FindBy(xpath = "//input[@placeholder='Введите новый пароль']")
    public WebElement placeholderPassword;

    @FindBy(xpath = "//input[@placeholder='Повторите новый пароль']")
    public WebElement placeholderPassword2;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitBtn;

    @FindBy(xpath = "//p[@class='_authInputErrorText_1hc7y_49']")
    public WebElement errorMessageElement;

    public String getErrorMessage() {
        if (errorMessageElement.isDisplayed()) {
            return errorMessageElement.getText();
        }
        return "";
    }

    public void fillUpResetPasswordForm(ChangePasswordEntity entity) {
        passwordInput.sendKeys(entity.getCurrentPassword());
        password2Input.sendKeys(entity.getNewPassword());
    }

    public void verifyFormResetPasswordPlaceholders() {
        WebElement[] elements = {placeholderEmail};
        String[] expectedPlaceholders = {"Введите почту"};
        String[] fieldNames = {"Введите почту"};

        webElementActions.verifyPlaceholders(elements, expectedPlaceholders, fieldNames);
    }
}
