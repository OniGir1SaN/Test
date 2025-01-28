package com.demoqa.pages.iZDE;

import com.demoqa.entities.iZDE.LoginEntity;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@type='email']")
    public WebElement emailInput;

    @FindBy(xpath = "//input[@type='password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//p[@class='_authInputErrorText_1hc7y_49']")
    public WebElement errorMessageElement;

    @FindBy(xpath = "//p[@class='errorInputMessage']")
    public WebElement errorMessageElement2;

    @FindBy(xpath = "//input[@placeholder='Введите почту']")
    private WebElement placeholderEmail;

    @FindBy(xpath = "//input[@placeholder='Введите пароль']")
    private WebElement placeholderPassword;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitBtn;

    @FindBy(xpath = "//h1[@class='_h1_10rpk_8']")
    public WebElement textLogin1;

    @FindBy(xpath = "//p[@class='_titleP_10rpk_14']")
    public WebElement textLogin2;

    public LoginPage fillUpLoginForm(LoginEntity loginEntity) {
        webElementActions.sendKeys(emailInput, loginEntity.getEmail())
                .sendKeys(passwordInput, loginEntity.getPassword())
                .click(submitBtn);
        return new LoginPage();
    }

    public void verifyEmailAndPasswordPlaceholders() {
        WebElement[] elements = {placeholderEmail, placeholderPassword};
        String[] expectedPlaceholders = {"Введите почту", "Введите пароль"};
        String[] fieldNames = {"Введите почту", "Введите пароль"};

        webElementActions.verifyPlaceholders(elements, expectedPlaceholders, fieldNames);
    }

    public String getErrorMessage() {
        if (errorMessageElement.isDisplayed()) {
            return errorMessageElement.getText();
        }
        return "";
    }

    public String getErrorMessage2() {
        if (errorMessageElement2.isDisplayed()) {
            return errorMessageElement2.getText();
        }
        return "";
    }

    public boolean isTextLogin1Correct(String expectedText) {
        return textLogin1.getText().equals(expectedText);
    }

    public boolean isTextLogin2Correct(String expectedText) {
        return textLogin2.getText().equals(expectedText);
    }
}
