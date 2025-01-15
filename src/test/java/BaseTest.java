import com.demoqa.drivers.DriverManager;
import com.demoqa.helper.AlertHelper;
import com.demoqa.helper.BrowserHelper;
import com.demoqa.helper.IframeHelper;
import com.demoqa.helper.WebElementActions;
import com.demoqa.listener.ScreenshotListener;
import com.demoqa.pages.*;
import com.demoqa.utils.RandomUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import io.qameta.allure.testng.AllureTestNg;

@Listeners({AllureTestNg.class, ScreenshotListener.class})

public class BaseTest {
    protected WebDriver driver;
    protected RandomUtils randomUtils;
    protected WebElementActions webElementActions;

    protected AlertHelper alertHelper;
    protected BrowserHelper browserHelper;
    protected IframeHelper iframeHelper;

    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected ResetPasswordPage resetPasswordPage;
    protected MainMenuPage mainMenuPage;
    protected SearchPage searchPage;

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        driver = DriverManager.getDriver();
        randomUtils = new RandomUtils();
        webElementActions = new WebElementActions(driver);

        alertHelper = new AlertHelper(driver);
        browserHelper = new BrowserHelper(driver);
        iframeHelper = new IframeHelper(driver);

        registerPage = new RegisterPage();
        loginPage = new LoginPage();
        resetPasswordPage = new ResetPasswordPage();
        mainMenuPage = new MainMenuPage();
        searchPage = new SearchPage();

        PageFactory.initElements(driver, registerPage);
        PageFactory.initElements(driver, loginPage);
        PageFactory.initElements(driver, resetPasswordPage);
        PageFactory.initElements(driver, mainMenuPage);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        DriverManager.closeDriver();
    }
}
