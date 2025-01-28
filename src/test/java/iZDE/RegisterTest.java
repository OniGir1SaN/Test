package iZDE;

import com.demoqa.entities.iZDE.RegisterEntity;
import com.demoqa.enums.iZDE.Endpoints;
import com.demoqa.enums.iZDE.TextElementsRegister;
import com.demoqa.utils.ConfigReader;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @BeforeMethod
    public void openRegistrationPage() {
        browserHelper.open(ConfigReader.getValue("baseURL") + Endpoints.SIGNUP.getEndpoint());
        Allure.step("Открыта страница регистрации");
    }

    private void submitFormAndVerifyError(RegisterEntity entity, String expectedErrorMessage) {
        registerPage.fillUpRegisterForm(entity);
        webElementActions.click(registerPage.submitBtn);

        String errorMessage = registerPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains(expectedErrorMessage),
                "Ожидаемая ошибка: " + expectedErrorMessage + ", получено: " + errorMessage);
        Allure.step("Проверка ошибки: " + expectedErrorMessage);
    }

    private void verifySuccessfulRegistration() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/code"),
                "Пользователь не был перенаправлен на страницу с кодом. Текущий URL: " + currentUrl);
        Allure.step("Проверка успешной регистрации. URL: " + currentUrl);
    }

    @Test(groups = "success")
    public void testRegistrationProcess() {
        Allure.step("Начало теста регистрации");

        webElementActions.click(registerPage.pcBtn);
        Assert.assertTrue(driver.getCurrentUrl().contains("privacy-policy"),
                "Неправильный редирект на страницу политики конфиденциальности");
        driver.navigate().back();

        webElementActions.click(registerPage.ucBtn);
        Assert.assertTrue(driver.getCurrentUrl().contains("payment-terms"),
                "Неправильный редирект на страницу пользовательского соглашения");
        driver.navigate().back();

        webElementActions.click(registerPage.poBtn);
        Assert.assertTrue(driver.getCurrentUrl().contains("public_offer"),
                "Неправильный редирект на страницу публичной оферты");
        driver.navigate().back();

        RegisterEntity registerEntity = randomUtils.generateRandomRegisterEntity();
        registerPage.fillUpRegisterForm(registerEntity);
        webElementActions.click(registerPage.submitBtn);
        verifySuccessfulRegistration();
    }

    // Тесты для поля "Имя"
    @Test(groups = "validation")
    public void testRegistrationWithEmptyFirstName() {
        RegisterEntity entity = randomUtils.generateRandomRegisterEntity();
        entity.setFirstName("");  // Пустое имя
        submitFormAndVerifyError(entity, "Обязательно к заполнению");
    }

    @Test(groups = "validation")
    public void testRegistrationWithNumericFirstName() {
        RegisterEntity entity = randomUtils.generateRandomRegisterEntity();
        entity.setFirstName("12345");  // Имя с цифрами
        submitFormAndVerifyError(entity, "Имя должно содержать только буквы");
    }

    // Тесты для поля "Email"
    @Test(groups = "validation")
    public void testRegistrationWithEmptyEmail() {
        RegisterEntity entity = randomUtils.generateRandomRegisterEntity();
        entity.setEmail("");  // Пустой email
        submitFormAndVerifyError(entity, "Обязательно к заполнению");
    }

    @Test(groups = "validation")
    public void testRegistrationWithInvalidEmailFormat() {
        RegisterEntity entity = randomUtils.generateRandomRegisterEntity();
        entity.setEmail("invalidemail");  // Неверный формат email
        submitFormAndVerifyError(entity, "Введите верный Email");
    }

    @Test(groups = "validation")
    public void testRegistrationWithExistingEmail() {
        RegisterEntity entity = randomUtils.generateRandomRegisterEntity();
        entity.setEmail("GOku.first@proton.me");  // Почта, которая уже существует
        submitFormAndVerifyError(entity, "пользователь с этой Почтой уже существует.");
    }

    // Тесты для поля "Пароль"
    @Test(groups = "validation")
    public void testRegistrationWithShortPassword() {
        RegisterEntity entity = randomUtils.generateRandomRegisterEntity();
        entity.setPassword("12345");  // Пароль слишком короткий
        submitFormAndVerifyError(entity, "Пароль должен содержать не менее 8 символов");
    }

    @Test(groups = "validation")
    public void testRegistrationWithPasswordWithoutDigits() {
        RegisterEntity entity = randomUtils.generateRandomRegisterEntity();
        entity.setPassword("Password!");  // Без цифр в пароле
        submitFormAndVerifyError(entity, "Пароль должен содержать хотя бы одну цифру");
    }

    @Test(groups = "validation")
    public void testRegistrationWithPasswordWithoutSpecialChars() {
        RegisterEntity entity = randomUtils.generateRandomRegisterEntity();
        entity.setPassword("Password1");  // Без спецсимволов
        submitFormAndVerifyError(entity, "Пароль должен содержать хотя бы один специальный символ");
    }

    @Test(groups = "validation")
    public void testRegistrationWithMismatchedPasswords() {
        RegisterEntity entity = randomUtils.generateRandomRegisterEntity();
        entity.setPassword("Password1!");
        entity.setPassword2("Password2!");  // Несовпадающие пароль
        submitFormAndVerifyError(entity, "Пароли не совпадают");
    }

    @Test(groups = "validation")
    public void testRegisterPlaceholders() {
        registerPage.verifyFormRegisterPlaceholders();
        Allure.step("Проверка плейсхолдеров полей Имя, Введите почту, Введите пароль, Повторите пароль");
    }

    @Test(dataProvider = "textElementsProvider")
    public void testTextRegister(TextElementsRegister element, String expectedText, String errorMessage) {
        String actualText = driver.findElement(By.xpath(element.getXpath())).getText();
        Assert.assertEquals(actualText, expectedText, errorMessage);
    }

    @DataProvider(name = "textElementsProvider")
    public Object[][] textElementsProvider() {
        return new Object[][]{
                {TextElementsRegister.REGISTER_TITLE1, "Зарегистрируйтесь, и ваш отпуск станет ближе.", "Текст для REGISTER_TITLE1 не соответствует ожидаемому тексту."},
                {TextElementsRegister.REGISTER_PARAGRAPH1, "Введите необходимые данные.", "Текст для REGISTER_PARAGRAPH1 не соответствует ожидаемому тексту."},
                {TextElementsRegister.REGISTER_TITLE2, "Создайте аккаунт", "Текст для REGISTER_TITLE2 не соответствует ожидаемому тексту."},
                {TextElementsRegister.REGISTER_PARAGRAPH2, "Присоединяйтесь к нам, чтобы начать бронировать уникальные места и управлять своими поездками с легкостью.", "Текст для REGISTER_PARAGRAPH2 не соответствует ожидаемому тексту."}
        };
    }
}
