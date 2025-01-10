package com.demoqa.pages;

import com.demoqa.entities.ResetPasswordEntity;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResetPasswordPage extends BasePage{

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

    public void fillUpResetPasswordForm(ResetPasswordEntity entity) {
        // Используем элементы страницы для ввода нового пароля и подтверждения пароля
        passwordInput.sendKeys(entity.getPassword()); // Ввод нового пароля
        password2Input.sendKeys(entity.getPassword2()); // Ввод повторного пароля
    }

    public void verifyFormResetPasswordPlaceholders() {
        WebElement[] elements = {placeholderEmail};
        String[] expectedPlaceholders = {"Введите почту"};
        String[] fieldNames = {"Введите почту"};

        webElementActions.verifyPlaceholders(elements, expectedPlaceholders, fieldNames);
    }
}
