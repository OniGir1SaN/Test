package com.demoqa.pages;

import com.demoqa.entities.RegisterEntity;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(id = "first_name")
    public WebElement nameInput;

    @FindBy(id = "email")
    public WebElement emailInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(id = "password2")
    public WebElement password2Input;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitBtn;

    @FindBy(xpath = "//p[text()='политикой конфиденциальности']")
    public WebElement pcBtn;

    @FindBy(xpath = "//p[text()='пользовательским соглашением']")
    public WebElement ucBtn;

    @FindBy(xpath = "//p[text()='публичной офертой']")
    public WebElement poBtn;

    @FindBy(xpath = "//p[@class='_authInputErrorText_1hc7y_49']")
    public WebElement errorMessageElement;

    @FindBy(xpath = "//input[@placeholder='Имя']")
    public WebElement placeholderName;

    @FindBy(xpath = "//input[@placeholder='Введите почту']")
    public WebElement placeholderEmail;

    @FindBy(xpath = "//input[@placeholder='Введите пароль']")
    public WebElement placeholderPassword;

    @FindBy(xpath = "//input[@placeholder='Повторите пароль']")
    public WebElement placeholderPassword2;

    @FindBy(xpath = "//h1[@class='_h1_k0ma6_7']")
    public WebElement textRegister1;

    @FindBy(xpath = "//p[@class='_titleP_k0ma6_13']")
    public WebElement textRegister2;

    @FindBy(xpath = "//h1[@class='_h1_1p6u8_44']")
    public WebElement textRegister3;

    @FindBy(xpath = "//p[@class='_p_1p6u8_51']")
    public WebElement textRegister4;

    public String getErrorMessage() {
        if (errorMessageElement.isDisplayed()) {
            return errorMessageElement.getText();
        }
        return "";
    }

    public RegisterPage fillUpRegisterForm(RegisterEntity registerEntity) {
        webElementActions.sendKeys(nameInput, registerEntity.getFirstName())
                .sendKeys(emailInput, registerEntity.getEmail())
                .sendKeys(passwordInput, registerEntity.getPassword())
                .sendKeys(password2Input, registerEntity.getPassword2())
                .click(submitBtn);
        return this;
    }

    public void verifyFormRegisterPlaceholders() {
        WebElement[] elements = {placeholderName, placeholderEmail, placeholderPassword, placeholderPassword2};
        String[] expectedPlaceholders = {"Имя", "Введите почту", "Введите пароль", "Повторите пароль"};
        String[] fieldNames = {"Имя", "Введите почту", "Введите пароль", "Повторите пароль"};

        webElementActions.verifyPlaceholders(elements, expectedPlaceholders, fieldNames);
    }

    public boolean isTextRegister1Correct(String expectedText) {
        return textRegister1.getText().equals(expectedText);
    }

    public boolean isTextRegister2Correct(String expectedText) {
        return textRegister2.getText().equals(expectedText);
    }

    public boolean isTextRegister3Correct(String expectedText) {
        return textRegister3.getText().equals(expectedText);
    }

    public boolean isTextRegister4Correct(String expectedText) {
        return textRegister4.getText().equals(expectedText);
    }
}
