package com.demoqa.pages.iZDE.Profile;

import com.demoqa.entities.iZDE.ProfileEntity;
import com.demoqa.pages.iZDE.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PrivateProfilePage extends BasePage {

    @FindBy(className = "_h4_jxvmi_36")
    public WebElement name;

    @FindBy(xpath = "//input[@id='avatar']")
    public WebElement avatar;

    @FindBy(className = "_emailP_jxvmi_42")
    private WebElement email;

    @FindBy(xpath = "(//input[@class='_input_1hc7y_23'])[1]")
    private WebElement firstNameInput;

    @FindBy(xpath = "(//input[@class='_input_1hc7y_23'])[2]")
    private WebElement lastNameInput;

    @FindBy(xpath = "(//button[@class='_btnChange_jxvmi_109'])[1]")
    private WebElement changePasswordBtn;

    @FindBy(xpath = "(//button[@class='_btnChange_jxvmi_109'])[2]")
    private WebElement changeEmailBtn;

    @FindBy(xpath = "(//button[@class='_btnChange_jxvmi_109'])[3]")
    private WebElement changeNumberBtn;

    @FindBy(xpath = "//button[@class='_btn_10vqr_1 _btnDis_10vqr_22']")
    private WebElement saveBtn;

    public String getNameText() {
        return name.getText();
    }

    public String getEmailText() {
        return email.getText();
    }

    public PrivateProfilePage fillUpProfileNameForm(ProfileEntity profileEntity){
        webElementActions.sendKeys(firstNameInput, profileEntity.getFirstName() )
                .sendKeys(lastNameInput, profileEntity.getLastName())
                .sendKeys(avatar, profileEntity.getAvatar())
                .click(saveBtn);
        return new PrivateProfilePage();
    }

    public void changeName(String firstName, String lastName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        saveBtn.click();
    }

    public void clickChangePassword(){
        webElementActions.click(changePasswordBtn);
    }

    public void clickChangeEmail(){
        webElementActions.click(changeEmailBtn);
    }

    public void clickChangeNumber(){
        webElementActions.click(changeNumberBtn);
    }

    public void clickButton(WebElement button) {
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }
}
