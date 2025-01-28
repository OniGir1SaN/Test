package iZDE.ProfileTest;

import com.demoqa.entities.iZDE.LoginEntity;
import com.demoqa.enums.iZDE.Endpoints;
import com.demoqa.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CurrencyTest extends BaseTest {

    @BeforeClass
    public void loginOnce() {
        browserHelper.open(ConfigReader.getValue("baseURL") + Endpoints.SIGNIN.getEndpoint());

        LoginEntity entity = randomUtils.validLoginEntity();
        loginPage.fillUpLoginForm(entity);
    }

    private void changeCurrencyIfNeeded(WebElement currencyBtn, String expectedCurrency) {
        String initialCurrencyText = currencyPage.getCurrentCurrencyElement().getText();

        if (!initialCurrencyText.equals(expectedCurrency)) {
            currencyPage.clickCurrencyIfNotSelected(currencyBtn);
            currencyPage.clickSave();
            browserHelper.waitForElementTextToBe(currencyPage.getCurrentCurrencyElement(), expectedCurrency, 10);
        }
    }

    @Test(priority = 1)
    public void currencyChangeToEurTest() {
        mainMenuPage.clickProfileButton();
        dashboardProfilePage.clickCurrencyBtn();

        changeCurrencyIfNeeded(currencyPage.eurBtn, "Евро\nEUR");

        mainMenuPage.clickBurgerMenu();
        mainMenuPage.clickSearchButton();

        String actualDisplayedText = String.valueOf(searchPage.getEurText());
        Assert.assertTrue(actualDisplayedText.contains("€"), "Отображение валюты в поиске некорректно!");
    }

    @Test(priority = 2)
    public void currencyChangeToUsdTest() {
        mainMenuPage.clickProfileButton();
        dashboardProfilePage.clickCurrencyBtn();
        dashboardProfilePage.clickCurrencyBtn();

        changeCurrencyIfNeeded(currencyPage.usdBtn, "Доллар США\nUSD");

        mainMenuPage.clickBurgerMenu();
        mainMenuPage.clickSearchButton();

        String actualDisplayedText = String.valueOf(searchPage.getUsdText());
        Assert.assertTrue(actualDisplayedText.contains("$"), "Отображение валюты в поиске некорректно!");
    }

    @Test(priority = 3)
    public void currencyChangeToKgsTest() {
        mainMenuPage.clickProfileButton();
        dashboardProfilePage.clickCurrencyBtn();
        dashboardProfilePage.clickCurrencyBtn();

        changeCurrencyIfNeeded(currencyPage.kgsBtn, "Кыргызский сом\nKGS");

        mainMenuPage.clickBurgerMenu();
        mainMenuPage.clickSearchButton();

        String actualDisplayedText = String.valueOf(searchPage.getKgsText());
        Assert.assertTrue(actualDisplayedText.contains("с"), "Отображение валюты в поиске некорректно!");
    }
}
