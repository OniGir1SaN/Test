package iZDE.ProfileTest;

import com.demoqa.entities.iZDE.LoginEntity;
import com.demoqa.enums.iZDE.Endpoints;
import com.demoqa.utils.ConfigReader;
import iZDE.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.demoqa.drivers.DriverManager.getDriver;

public class DashBordTest extends BaseTest {

    @BeforeMethod
    public void loginOnce() {
        browserHelper.open(ConfigReader.getValue("baseURL") + Endpoints.PROFILE.getEndpoint());

        LoginEntity entity = randomUtils.validLoginEntity();
        loginPage.fillUpLoginForm(entity);
        mainMenuPage.clickProfileButton();
    }

    @Test
    public void testClickExitBtn() {
        dashboardProfilePage.clickExitBtn();
        dashboardProfilePage.clickExitBtn2();

        String expectedBaseUrl = ConfigReader.getValue("baseURL");
        wait.until(ExpectedConditions.urlToBe(expectedBaseUrl));

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedBaseUrl, "URL не соответствует базовому URL после выхода.");
    }
}
