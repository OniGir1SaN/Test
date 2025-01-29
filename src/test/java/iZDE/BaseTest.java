package iZDE;

import com.demoqa.drivers.DriverManager;
import com.demoqa.entities.iZDE.*;
import com.demoqa.helpers.AlertHelper;
import com.demoqa.helpers.BrowserHelper;
import com.demoqa.helpers.IframeHelper;
import com.demoqa.helpers.WebElementActions;
import com.demoqa.listener.ScreenshotListener;
import com.demoqa.pages.iZDE.*;
import com.demoqa.pages.iZDE.Profile.*;
import com.demoqa.utils.iZDE.RandomUtils;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners({AllureTestNg.class, ScreenshotListener.class})

public class BaseTest {
    protected WebDriver driver;
    protected RandomUtils randomUtils;
    protected WebElementActions webElementActions;
    protected WebDriverWait wait;

    protected LoginEntity loginEntity;
    protected RegisterEntity registerEntity;
    protected ChangePasswordEntity resetPasswordEntity;
    protected ChangePasswordEntity changePasswordEntity;
    protected ChangeEmailEntity changeEmailEntity;
    protected ResetNumberEntity resetNumberEntity;

    protected AlertHelper alertHelper;
    protected BrowserHelper browserHelper;
    protected IframeHelper iframeHelper;

    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected ResetPasswordPage resetPasswordPage;
    protected MainMenuPage mainMenuPage;
    protected SearchPage searchPage;
    protected CurrencyPage currencyPage;
    protected DashboardProfilePage dashboardProfilePage;
    protected PrivateProfilePage privateProfilePage;
    protected LegalInformationPage legalInformationPage;
    protected ChangeEmailPage changeEmailPage;
    protected ChangePasswordPage changePasswordPage;

    protected MainMenuTest mainMenuTest;

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        driver = DriverManager.getDriver();
        randomUtils = new RandomUtils();
        webElementActions = new WebElementActions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginEntity = new LoginEntity();
        registerEntity = new RegisterEntity();
        resetPasswordEntity = new ChangePasswordEntity();
        changePasswordEntity = new ChangePasswordEntity();
        changeEmailEntity = new ChangeEmailEntity();
        resetNumberEntity = new ResetNumberEntity();

        alertHelper = new AlertHelper(driver);
        browserHelper = new BrowserHelper(driver);
        iframeHelper = new IframeHelper(driver);

        registerPage = new RegisterPage();
        loginPage = new LoginPage();
        resetPasswordPage = new ResetPasswordPage();
        mainMenuPage = new MainMenuPage();
        searchPage = new SearchPage();
        currencyPage = new CurrencyPage();
        dashboardProfilePage=  new DashboardProfilePage();
        privateProfilePage = new PrivateProfilePage();
        legalInformationPage = new LegalInformationPage();
        changeEmailPage = new ChangeEmailPage();
        changePasswordPage = new ChangePasswordPage();

        mainMenuTest = new MainMenuTest();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        DriverManager.closeDriver();
    }
}
