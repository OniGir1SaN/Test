package iZDE;

import com.demoqa.entities.iZDE.LoginEntity;
import com.demoqa.enums.iZDE.Endpoints;
import com.demoqa.enums.iZDE.TextElementsMain;
import com.demoqa.utils.ConfigReader;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class MainMenuTest extends BaseTest {

    private String getFullUrl(Endpoints endpoint) {
        return ConfigReader.getValue("baseURL") + endpoint.getEndpoint();
    }

    private void verifyPage(String expectedUrlPart, By elementLocator, String errorMessage) {
        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(currentUrl.contains(expectedUrlPart),
                "URL не содержит '" + expectedUrlPart + "'. Текущий URL: " + currentUrl);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));

        Assert.assertNotNull(element, errorMessage + " не найден на странице.");
        Assert.assertTrue(element.isDisplayed(), errorMessage + " не отображается.");

        browserHelper.goBack();
        webElementActions.assertBaseUrlIsCurrent();
    }

    @BeforeMethod
    public void loginOnce() {
        browserHelper.open(ConfigReader.getValue("baseURL") + Endpoints.SIGNIN.getEndpoint());

        LoginEntity entity = randomUtils.validLoginEntity();
        loginPage.fillUpLoginForm(entity);
    }

    @Test
    public void testClickStartButton() {
        mainMenuPage.clickStartButton();

        verifyPage(getFullUrl(Endpoints.SEARCH),
                By.xpath("//div[@class='_contentBackground_qv3mn_6']"),
                "Блок поисковика");

        browserHelper.goBack();
        webElementActions.assertBaseUrlIsCurrent();
    }

    @Test
    public void testClickSearchButton() {
        mainMenuPage.clickBurgerMenu();
        mainMenuPage.clickSearchButton();

        verifyPage(getFullUrl(Endpoints.SEARCH),
                By.xpath("//div[@class='_contentBackground_qv3mn_6']"),
                "Блок поисковика");

        browserHelper.goBack();
        webElementActions.assertBaseUrlIsCurrent();
    }

    @Test
    public void testClickTravellingButton() {
        mainMenuPage.clickBurgerMenu();
        mainMenuPage.clickTravellingButton();

        verifyPage(getFullUrl(Endpoints.TRIPS),
                By.xpath("//div[@class='_trips_pp0r2_1']"),
                "Блок путешествий");

        webElementActions.assertBaseUrlIsCurrent();
    }

    @Test
    public void testClickSupportButton() {
        mainMenuPage.clickBurgerMenu();
        mainMenuPage.clickSupportButton();

        verifyPage(getFullUrl(Endpoints.SUPPORT),
                By.xpath("//div[@class='_wrapperLayout_h0trw_6']"),
                "Блок поддержки");

        webElementActions.assertBaseUrlIsCurrent();
    }

    @Test
    public void testClickProfileButton() {
        mainMenuPage.clickProfileButton();

        verifyPage(getFullUrl(Endpoints.PROFILE),
                By.xpath("//div[@class='_wrapper_1yb73_1']"),
                "Блок профиля");

        webElementActions.assertBaseUrlIsCurrent();
    }

    @Test
    public void testClickCellarSearchButton() {
        mainMenuPage.clickCellarSearchButton();

        verifyPage(getFullUrl(Endpoints.SEARCH),
                By.xpath("//div[@class='_contentBackground_qv3mn_6']"),
                "Блок профиля");

        browserHelper.goBack();
        webElementActions.assertBaseUrlIsCurrent();
    }

    @Test
    public void testClickCellarTravellingButton() {
        mainMenuPage.clickCellarTravellingButton();

        verifyPage(getFullUrl(Endpoints.TRIPS),
                By.xpath("//div[@class='_trips_pp0r2_1']"),
                "Блок путешествий");

        browserHelper.goBack();
        webElementActions.assertBaseUrlIsCurrent();
    }

    @Test
    public void testClickPlayMarketButton() {
        mainMenuPage.clickCellarPlayMarketButton();

        browserHelper.switchToNewTab();

        String expectedUrl = "https://play.google.com/store/apps/details?id=com.izde.izdeuserandroid";
        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, expectedUrl,
                "URL открытой вкладки не соответствует ожидаемому. Ожидалось: " + expectedUrl + ", но было: " + currentUrl);

        browserHelper.switchToParentWindowAndCloseChildren();
        webElementActions.assertBaseUrlIsCurrent();
    }

    @Test
    public void testClickAppStoreButton() {
        mainMenuPage.clickCellarAppStoreButton();

        browserHelper.switchToNewTab();

        String expectedUrl = "https://apps.apple.com/kg/app/izde/id6498629576";
        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, expectedUrl,
                "URL открытой вкладки не соответствует ожидаемому. Ожидалось: " + expectedUrl + ", но было: " + currentUrl);

        browserHelper.switchToParentWindowAndCloseChildren();
        webElementActions.assertBaseUrlIsCurrent();
    }

    @Test
    public void testClickVendorButton() {
        mainMenuPage.clickCellarVendorButton();

        browserHelper.switchToNewTab();

        String expectedUrl = "https://vendor.izde.online/sign-in/";
        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, expectedUrl,
                "URL открытой вкладки не соответствует ожидаемому. Ожидалось: " + expectedUrl + ", но было: " + currentUrl);


        browserHelper.goBack();
        webElementActions.assertBaseUrlIsCurrent();
    }

}