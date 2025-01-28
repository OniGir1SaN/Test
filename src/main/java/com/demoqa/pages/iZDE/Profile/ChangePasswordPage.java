package com.demoqa.pages.iZDE.Profile;

import com.demoqa.entities.iZDE.ChangePasswordEntity;
import com.demoqa.pages.iZDE.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChangePasswordPage extends BasePage {

    @FindBy(xpath = "(//input[@type='password'])[1]")
    private WebElement currentPasswordInput;

    @FindBy(xpath = "(//input[@type='password'])[2]")
    private WebElement newPasswordInput;

    @FindBy(xpath = "(//input[@type='password'])[3]")
    private WebElement repeatNewPasswordInput;

    @FindBy(xpath = "//input[@placeholder='Введите текущий пароль']")
    private WebElement currentPasswordPlaceholder;

    @FindBy(xpath = "//input[@placeholder='Введите новый пароль']")
    private WebElement newPasswordPlaceholder;

    @FindBy(xpath = "//input[@placeholder='Повторите новый пароль']")
    private WebElement repeatNewPasswordPlaceholder;

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

    public ChangePasswordPage fillUpChangePasswordForm(ChangePasswordEntity changePasswordEntity){
        webElementActions.sendKeys(currentPasswordInput, changePasswordEntity.getCurrentPassword())
                .sendKeys(newPasswordInput, changePasswordEntity.getNewPassword())
                .sendKeys(repeatNewPasswordInput, changePasswordEntity.getRepeatNewPassword())
                .click(saveBtn);
        return new ChangePasswordPage();

    }

    public void verifyFormChangePasswordPlaceholders() {
        WebElement[] elements = {currentPasswordInput, newPasswordPlaceholder, repeatNewPasswordPlaceholder};
        String[] expectedPlaceholders = {"Введите текущий пароль", "Введите новый пароль", "Повторите новый пароль"};
        String[] fieldNames = {"Введите текущий пароль", "Введите новый пароль", "Повторите новый пароль"};

        webElementActions.verifyPlaceholders(elements, expectedPlaceholders, fieldNames);
    }

    public void clickSaveBtn(){
        webElementActions.click(saveBtn);
    }
}
