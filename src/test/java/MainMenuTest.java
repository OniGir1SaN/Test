import com.demoqa.entities.LoginEntity;
import com.demoqa.enums.Endpoints;
import com.demoqa.enums.TextElementsMain;
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

        LoginEntity entity = randomUtils.ValidLoginEntity();
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

    @Test(dataProvider = "textElementsProvider")
    public void testTextMainPage(TextElementsMain element, String expectedText, String errorMessage) {
        WebElement elementToCheck = element.getElement(driver);
        String actualText = elementToCheck.getText();
        Assert.assertEquals(actualText, expectedText, errorMessage);
        Allure.step("Проверка текста элемента: " + errorMessage);
    }

    @DataProvider(name = "textElementsProvider")
    public Object[][] textElementsProvider() {
        return new Object[][]{
                {TextElementsMain.MAIN_PAGE_TITLE1, "Откройте дверь в мир комфорта — где Ваш идеальный отдых начинается с одного клика.", "Текст для MAIN_PAGE_TITLE1 не соответствует ожидаемому тексту."},
                {TextElementsMain.MAIN_PARAGRAPH1, "Исследуйте, бронируйте и наслаждайтесь лучшим жильем по всему миру с iZDE. Наша платформа предлагает простой и гибкий путь к уникальным домам, апартаментам и отелям, обеспечивая безупречный опыт от первого взгляда до последней минуты пребывания.", "Текст для MAIN_PARAGRAPH1 не соответствует ожидаемому тексту."},

                {TextElementsMain.MAIN_PAGE_TITLE2, "Также используйте наше приложение!", "Текст для MAIN_PAGE_TITLE2 не соответствует ожидаемому тексту."},
                {TextElementsMain.MAIN_PARAGRAPH2, "Наше приложение - это не просто сервис бронирования жилья, это путеводитель по вашему идеальному отдыху. Совмещая удобства такси и бронирования, мы создали уникальную платформу, где каждый клиент получает не только комфортное проживание, но и легкий доступ к любимым местам", "Текст для MAIN_PARAGRAPH2 не соответствует ожидаемому тексту."},
                {TextElementsMain.MAIN_PARAGRAPH3, "Отдыхайте без границ", "Текст для MAIN_PARAGRAPH3 не соответствует ожидаемому тексту."},
                {TextElementsMain.MAIN_PARAGRAPH4, "Наслаждайтесь каждым моментом с нашим приложением!", "Текст для MAIN_PARAGRAPH4 не соответствует ожидаемому тексту."},
                {TextElementsMain.MAIN_PARAGRAPH5, "Отдыхайте легко - бронируйте удобно", "Текст для MAIN_PARAGRAPH5 не соответствует ожидаемому тексту."},
                {TextElementsMain.MAIN_PARAGRAPH6, "Получите максимум удобства от каждого бронирования!", "Текст для MAIN_PARAGRAPH6 не соответствует ожидаемому тексту."},
                {TextElementsMain.MAIN_PARAGRAPH7, "Управляйте своим отдыхом", "Текст для MAIN_PARAGRAPH7 не соответствует ожидаемому тексту."},
                {TextElementsMain.MAIN_PARAGRAPH8, "Все функции в одном месте через личный профиль!", "Текст для MAIN_PARAGRAPH8 не соответствует ожидаемому тексту."},
                {TextElementsMain.MAIN_PARAGRAPH9, "Каждый клиент - гость", "Текст для MAIN_PARAGRAPH9 не соответствует ожидаемому тексту."},
                {TextElementsMain.MAIN_PARAGRAPH10, "Каждое решение - шаг к комфорту!", "Текст для MAIN_PARAGRAPH10 не соответствует ожидаемому тексту."},

                {TextElementsMain.MAIN_PAGE_TITLE3, "Идеальное жилье - просто с нами", "Текст для MAIN_PAGE_TITLE3 не соответствует ожидаемому тексту."},
                {TextElementsMain.MAIN_PARAGRAPH11, "Откройте для себя идеальное место для вашего отдыха в нашем приложении. Насладитесь моментом выбора из множества вариантов размещения - от уютных апартаментов до роскошных вилл. С нашим интуитивно понятным интерфейсом и мощными функциями поиска вы сможете легко найти идеальное жилье, отвечающее вашим потребностям и бюджету. Приготовьтесь к незабываемому отдыху, начиная с выбора места проживания через наше удобное приложение!", "Текст для MAIN_PARAGRAPH11 не соответствует ожидаемому тексту."},
                {TextElementsMain.MAIN_PARAGRAPH12, "Мы предлагаем", "Текст для MAIN_PARAGRAPH12 не соответствует ожидаемому тексту."},
                {TextElementsMain.MAIN_PARAGRAPH13, "Поддержка 24/7", "Текст для MAIN_PARAGRAPH13 не соответствует ожидаемому тексту."},
                {TextElementsMain.MAIN_PARAGRAPH14, "Наша служба поддержки всегда готова помочь вам с любыми вопросами или проблемами.", "Текст для MAIN_PARAGRAPH14 не соответствует ожидаемому тексту."},
                {TextElementsMain.MAIN_PARAGRAPH15, "Надежность", "Текст для MAIN_PARAGRAPH15 не соответствует ожидаемому тексту."},
                {TextElementsMain.MAIN_PARAGRAPH16, "Наша служба поддержки — это ваш надежный щит и помощник, доступный в любое время дня и ночи.", "Текст для MAIN_PARAGRAPH16 не соответствует ожидаемому тексту."},
                {TextElementsMain.MAIN_PARAGRAPH17, "Информативность", "Текст для MAIN_PARAGRAPH17 не соответствует ожидаемому тексту."},
                {TextElementsMain.MAIN_PARAGRAPH18, "Наша платформа предоставляет актуальную и детализированную информацию по всем вопросам и аспектам бронирования.", "Текст для MAIN_PARAGRAPH18 не соответствует ожидаемому тексту."}
        };
    }
}