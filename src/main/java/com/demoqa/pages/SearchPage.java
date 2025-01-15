package com.demoqa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends  BasePage{

    @FindBy(xpath = "//div[@class='_contentBackground_qv3mn_6']")
    public WebElement contentBackground;

    @FindBy(xpath = "//input[@class='_inputContainer__field_1r0qr_12']")
    public WebElement locationInput;

    @FindBy(xpath = "//input[@placeholder='Локация']")
    public WebElement locationPlaceholder;

    @FindBy(xpath = "//div[@class='_container_80lv0_1']")
    public WebElement calendarButton;

    @FindBy(xpath = "//div[@class='_guestsAndRoomsCounterContainer_jt1fw_36']")
    public WebElement guestsAndRoomsButton;

    @FindBy(xpath = "(//img[@alt='counterPlus'])[1]")
    public WebElement roomsPlusButton;

    @FindBy(xpath = "(//img[@alt='counterMinus'])[1]")
    public WebElement roomsMinusButton;

    @FindBy(xpath = "(//p[@class='_blockCounter__data_1u6e9_43'])[1]")
    public WebElement roomCountLabel;

    @FindBy(xpath = "(//img[@alt='counterPlus'])[2]")
    public WebElement guestPlusButton;

    @FindBy(xpath = "(//img[@alt='counterMinus'])[2]")
    public WebElement guestMinusButton;

    @FindBy(xpath = "(//p[@class='_blockCounter__data_1u6e9_43'])[2]")
    public WebElement guestCountLabel;

    @FindBy(xpath = "//input[@class='_input_1lstp_25']")
    public WebElement issueInput;

    @FindBy(xpath = "//input[@placeholder='Есть кондиционер?']")
    public WebElement issuePlaceholder;

    @FindBy(xpath = "//div[@class='_carousel_sv5ce_82']")
    public WebElement carousel;

    @FindBy(xpath = "//button[@class='_button_1hgvo_4']")
    public WebElement searchButton;

    public void verifyPageElements() {
        WebElement[] elements = {
                contentBackground, locationInput, calendarButton,
                guestsAndRoomsButton, roomsPlusButton, roomsMinusButton,
                guestPlusButton, guestMinusButton, searchButton
        };
        String[] elementNames = {
                "Фон содержимого", "Поле ввода локации", "Кнопка календаря",
                "Кнопка выбора гостей и комнат", "Кнопка увеличения комнат",
                "Кнопка уменьшения комнат", "Кнопка увеличения гостей",
                "Кнопка уменьшения гостей", "Кнопка поиска"
        };

        for (int i = 0; i < elements.length; i++) {
            webElementActions.verifyElementIsDisplayed(elements[i], elementNames[i]);
        }
    }

    public void adjustRoomsCount(int targetCount) {
        int currentCount = Integer.parseInt(roomCountLabel.getText());
        while (currentCount < targetCount) {
            roomsPlusButton.click();
            currentCount++;
        }
        while (currentCount > targetCount) {
            roomsMinusButton.click();
            currentCount--;
        }
    }

    public void adjustGuestCount(int targetCount) {
        int currentCount = Integer.parseInt(guestCountLabel.getText());
        while (currentCount < targetCount) {
            guestPlusButton.click();
            currentCount++;
        }
        while (currentCount > targetCount) {
            guestMinusButton.click();
            currentCount--;
        }
    }

    public void searchPagePlaceholders() {
        WebElement[] elements = {locationPlaceholder,issuePlaceholder};
        String[] expectedPlaceholders = {"Локация", "Есть кондиционер?"};
        String[] fieldNames = {"Локация", "Есть кондиционер?"};

        webElementActions.verifyPlaceholders(elements, expectedPlaceholders, fieldNames);
    }

    public void performSearch() {
        searchButton.click();
    }

}
