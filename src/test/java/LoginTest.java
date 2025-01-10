import com.demoqa.entities.LoginEntity;
import com.demoqa.enums.Endpoints;
import com.demoqa.utils.ConfigReader;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        loginPage.verifyEmailAndPasswordPlaceholders(); // Проверка плейсхолдеров
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
}