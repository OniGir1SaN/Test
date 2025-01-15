import com.demoqa.entities.LoginEntity;
import com.demoqa.enums.Endpoints;
import com.demoqa.listener.ScreenshotListener;
import com.demoqa.utils.ConfigReader;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureTestNg.class, ScreenshotListener.class})
public class LoginTest extends BaseTest {

    @BeforeMethod
    public void openLoginPage() {
        browserHelper.open(ConfigReader.getValue("baseURL") + Endpoints.SIGNIN.getEndpoint());
        Allure.step("Открыта страница авторизации");
    }

    private void submitFormAndVerifyError(LoginEntity entity, String expectedErrorMessage) {
        loginPage.fillUpLoginForm(entity);
        webElementActions.click(loginPage.submitBtn);

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains(expectedErrorMessage),
                "Ожидаемая ошибка: " + expectedErrorMessage + ", получено: " + errorMessage);
        Allure.step("Проверка ошибки: " + expectedErrorMessage);
    }

    private void submitFormAndVerifyError2(LoginEntity entity, String expectedErrorMessage) {
        loginPage.fillUpLoginForm(entity);
        webElementActions.click(loginPage.submitBtn);

        String errorMessage = loginPage.getErrorMessage2();
        Assert.assertTrue(errorMessage.contains(expectedErrorMessage),
                "Ожидаемая ошибка: " + expectedErrorMessage + ", получено: " + errorMessage);
        Allure.step("Проверка ошибки: " + expectedErrorMessage);
    }

    private String getErrorMessage() {
        return driver.findElement(By.xpath("//p[@class='errorInputMessage']")).getText();
    }

    @Test(groups = "validation")
    public void testLoginPlaceholders() {
        loginPage.verifyEmailAndPasswordPlaceholders();
        Allure.step("Проверка плейсхолдеров полей email и пароля");
    }

    // Тесты для поля "Email"
    @Test(groups = "validation")
    public void testLoginWithEmptyEmail() {
        LoginEntity entity = randomUtils.generateRandomLoginEntity();
        entity.setEmail(""); // Пустой email
        submitFormAndVerifyError(entity, "Обязательно к заполнению");
    }

    @Test(groups = "validation")
    public void testLoginWithInvalidEmailFormat() {
        LoginEntity entity = randomUtils.generateRandomLoginEntity();
        entity.setEmail("invalidemail"); // Неверный формат email
        submitFormAndVerifyError(entity, "Введите верный Email");
    }

    // Тесты для поля "Пароль"
    @Test(groups = "validation")
    public void testLoginWithEmptyPassword() {
        LoginEntity entity = randomUtils.generateRandomLoginEntity();
        entity.setPassword(""); // Пустой пароль
        submitFormAndVerifyError(entity, "Обязательно к заполнению");
    }

    @Test(groups = "validation")
    public void testLoginWithShortPassword() {
        LoginEntity entity = randomUtils.generateRandomLoginEntity();
        entity.setPassword("short"); // Короткий пароль
        submitFormAndVerifyError2(entity,"не правильный логин или пароль");
    }

    // Тесты для комбинации email и пароля
    @Test(groups = "validation")
    public void testLoginWithInvalidCredentials() {
        LoginEntity entity = randomUtils.generateRandomLoginEntity();
        entity.setEmail("invalidemail@example.com");
        entity.setPassword("InvalidPassword123!");
        submitFormAndVerifyError2(entity, "не правильный логин или пароль");
    }

    @Test(groups = "validation")
    public void testLoginWithEmptyFields() {
        LoginEntity entity = new LoginEntity(); // Поля email и пароль пустые
        entity.setEmail("");
        entity.setPassword("");
        submitFormAndVerifyError(entity, "Обязательно к заполнению");
    }

    @Test
    public void testTextLogin1() {
        String expectedText3 = "Готовы к новым приключениям?";

        Assert.assertTrue(loginPage.isTextLogin1Correct(expectedText3),
                "Текст для Login1 не соответствует ожидаемому тексту.");
    }

    @Test
    public void testTextLogin2() {
        String expectedText4 = "Войдите и начнем!";

        Assert.assertTrue(loginPage.isTextLogin2Correct(expectedText4),
                "Текст для Login2 не соответствует ожидаемому тексту.");
    }

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot();
        }
    }
}
