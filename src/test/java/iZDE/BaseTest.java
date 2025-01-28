package iZDE;

import com.demoqa.drivers.DriverManager;
import com.demoqa.entities.iZDE.LoginEntity;
import com.demoqa.entities.iZDE.RegisterEntity;
import com.demoqa.entities.iZDE.ChangePasswordEntity;
import com.demoqa.helpers.AlertHelper;
import com.demoqa.helpers.BrowserHelper;
import com.demoqa.helpers.IframeHelper;
import com.demoqa.helpers.WebElementActions;
import com.demoqa.listener.ScreenshotListener;
import com.demoqa.pages.iZDE.*;
import com.demoqa.pages.iZDE.Profile.DashboardProfilePage;
import com.demoqa.pages.iZDE.Profile.PrivateProfilePage;
import com.demoqa.utils.RandomUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import io.qameta.allure.testng.AllureTestNg;

@Listeners({AllureTestNg.class, ScreenshotListener.class})

public class BaseTest {
    protected WebDriver driver;
    protected RandomUtils randomUtils;
    protected WebElementActions webElementActions;

    protected LoginEntity loginEntity;
    protected RegisterEntity registerEntity;
    protected ChangePasswordEntity resetPasswordEntity;

    protected AlertHelper alertHelper;
    protected BrowserHelper browserHelper;
    protected IframeHelper iframeHelper;

    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected ResetPasswordPage resetPasswordPage;
    protected MainMenuPage mainMenuPage;
    protected SearchPage searchPage;
    protected DashboardProfilePage dashboardProfilePage;
    protected PrivateProfilePage privateProfilePage;

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        driver = DriverManager.getDriver();
        randomUtils = new RandomUtils();
        webElementActions = new WebElementActions(driver);

        loginEntity = new LoginEntity();
        registerEntity = new RegisterEntity();
        resetPasswordEntity = new ChangePasswordEntity();

        alertHelper = new AlertHelper(driver);
        browserHelper = new BrowserHelper(driver);
        iframeHelper = new IframeHelper(driver);

        registerPage = new RegisterPage();
        loginPage = new LoginPage();
        resetPasswordPage = new ResetPasswordPage();
        mainMenuPage = new MainMenuPage();
        searchPage = new SearchPage();
        dashboardProfilePage=  new DashboardProfilePage();
        privateProfilePage = new PrivateProfilePage();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        DriverManager.closeDriver();
    }
}
