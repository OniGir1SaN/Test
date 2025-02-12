package iZDE;

import com.demoqa.entities.iZDE.LoginEntity;
import com.demoqa.enums.iZDE.Endpoints;
import com.demoqa.utils.ConfigReader;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

public class MainMenuTest extends BaseTest {

    private String getFullUrl(Endpoints endpoint) {
        return ConfigReader.getValue("baseURL") + endpoint.getEndpoint();
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

        webElementActions.verifyPage(getFullUrl(Endpoints.SEARCH), By.xpath("//div[@class='_contentBackground_qv3mn_6']"),
                "Блок поисковика");

        browserHelper.goBack();
        webElementActions.assertBaseUrlIsCurrent();
    }

    @Test
    public void testClickSearchButton() {
        mainMenuPage.clickBurgerMenu();
        mainMenuPage.clickSearchButton();

        webElementActions.verifyPage(getFullUrl(Endpoints.SEARCH),
                By.xpath("//div[@class='_contentBackground_qv3mn_6']"),
                "Блок поисковика");

        browserHelper.goBack();
        webElementActions.assertBaseUrlIsCurrent();
    }

    @Test
    public void testClickTravellingButton() {
        mainMenuPage.clickBurgerMenu();
        mainMenuPage.clickTravellingButton();

        webElementActions.verifyPage(getFullUrl(Endpoints.TRIPS),
                By.xpath("//div[@class='_trips_pp0r2_1']"),
                "Блок путешествий");

        webElementActions.assertBaseUrlIsCurrent();
    }

    @Test
    public void testClickSupportButton() {
        mainMenuPage.clickBurgerMenu();
        mainMenuPage.clickSupportButton();

        webElementActions.verifyPage(getFullUrl(Endpoints.SUPPORT),
                By.xpath("//div[@class='_container_1nnn2_8']"),
                "Блок поддержки");

        webElementActions.assertBaseUrlIsCurrent();
    }

    @Test
    public void testClickProfileButton() {
        mainMenuPage.clickProfileButton();

        webElementActions.verifyPage(getFullUrl(Endpoints.PROFILE),
                By.xpath("//div[@class='_wrapper_1yb73_1']"),
                "Блок профиля");

        webElementActions.assertBaseUrlIsCurrent();
    }

    @Test
    public void testClickCellarSearchButton() {
        mainMenuPage.clickCellarSearchButton();

        webElementActions.verifyPage(getFullUrl(Endpoints.SEARCH),
                By.xpath("//div[@class='_contentBackground_qv3mn_6']"),
                "Блок профиля");

        browserHelper.goBack();
        webElementActions.assertBaseUrlIsCurrent();
    }

    @Test
    public void testClickCellarTravellingButton() {
        mainMenuPage.clickCellarTravellingButton();

        webElementActions.verifyPage(getFullUrl(Endpoints.TRIPS),
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