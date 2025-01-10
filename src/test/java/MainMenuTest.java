import com.demoqa.entities.LoginEntity;
import com.demoqa.enums.Endpoints;
import com.demoqa.utils.ConfigReader;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MainMenuTest extends BaseTest {

    String getFullUrl(Endpoints endpoint) {
        return ConfigReader.getValue("baseURL") + endpoint.getEndpoint();
    }

    @BeforeMethod
    public void openRegistrationPage() {
        browserHelper.open(ConfigReader.getValue("baseURL") +  Endpoints.SIGNIN.getEndpoint());

        Allure.step("Открыта страница регистрации");
        Allure.step("Начало теста входа с валидными данными");

        LoginEntity entity = randomUtils.generateRandomValidLoginEntity();
        loginPage.fillUpLoginForm(entity);
    }

    @Test
    public void testClickSearchButton() {
        mainMenuPage.clickStartButton();

        String expectedUrlPart = getFullUrl(Endpoints.SEARCH);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(expectedUrlPart),
                "URL не содержит '/search' после клика. Текущий URL: " + currentUrl);

        browserHelper.goBack();
    }
}