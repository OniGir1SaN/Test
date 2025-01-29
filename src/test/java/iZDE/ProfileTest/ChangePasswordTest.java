package iZDE.ProfileTest;

import com.demoqa.entities.iZDE.ChangePasswordEntity;
import com.demoqa.entities.iZDE.LoginEntity;
import com.demoqa.enums.iZDE.Endpoints;
import com.demoqa.utils.ConfigReader;
import com.demoqa.utils.iZDE.RandomUtils;
import iZDE.BaseTest;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordTest extends BaseTest {

    private final RandomUtils randomUtils = new RandomUtils();
    private String currentPassword = "AAAAA1!!CloseEye1";

    @BeforeMethod
    public void loginOnce() {
        LoginEntity loginEntity = randomUtils.validLoginEntity();
        loginEntity.setPassword(currentPassword);
        browserHelper.open(ConfigReader.getValue("baseURL") + Endpoints.SIGNIN.getEndpoint());
        loginPage.fillUpLoginForm(loginEntity);
        mainMenuPage.clickProfileButton();
        privateProfilePage.clickChangePassword();
    }

    @Test(groups = "validation")
    public void testChangePasswordWithEmptyFields() {
        ChangePasswordEntity entity = new ChangePasswordEntity();
        entity.setCurrentPassword(currentPassword);
        entity.setNewPassword("");
        entity.setRepeatNewPassword("");

        changePasswordPage.fillUpChangePasswordForm(entity);

        String errorMessage = changePasswordPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Обязательно к заполнению"),
                "Ожидаемая ошибка: Обязательно к заполнению, получено: " + errorMessage);
    }

    @Test(groups = "validation")
    public void testNewPasswordTooShort() {
        String shortPassword = "short";
        ChangePasswordEntity entity = new ChangePasswordEntity();
        entity.setCurrentPassword(currentPassword);
        entity.setNewPassword(shortPassword);
        entity.setRepeatNewPassword(shortPassword);

        changePasswordPage.fillUpChangePasswordForm(entity);

        String errorMessage = changePasswordPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Пароль должен содержать не менее 8 символов"),
                "Ожидаемая ошибка: Пароль должен содержать не менее 8 символов, получено: " + errorMessage);
    }

    @Test(groups = "validation")
    public void testNewPasswordOnlyLetters() {
        String passwordOnlyLetters = "passwordonly";
        ChangePasswordEntity entity = new ChangePasswordEntity();
        entity.setCurrentPassword(currentPassword);
        entity.setNewPassword(passwordOnlyLetters);
        entity.setRepeatNewPassword(passwordOnlyLetters);

        changePasswordPage.fillUpChangePasswordForm(entity);

        String errorMessage = changePasswordPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Пароль должен содержать хотя бы одну цифру"),
                "Ожидаемая ошибка: Пароль должен содержать хотя бы одну цифру, получено: " + errorMessage);
    }

    @Test(groups = "validation")
    public void testNewPasswordOnlyDigits() {
        String passwordOnlyDigits = "12345678";
        ChangePasswordEntity entity = new ChangePasswordEntity();
        entity.setCurrentPassword(currentPassword);
        entity.setNewPassword(passwordOnlyDigits);
        entity.setRepeatNewPassword(passwordOnlyDigits);

        changePasswordPage.fillUpChangePasswordForm(entity);

        String errorMessage = changePasswordPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Пароль должен содержать хотя бы одну букву латиницы"),
                "Ожидаемая ошибка: Пароль должен содержать хотя бы одну букву латиницы, получено: " + errorMessage);
    }

    @Test(groups = "validation")
    public void testNewPasswordWithoutSpecialChars() {
        String passwordWithoutSpecialChars = "Password123";
        ChangePasswordEntity entity = new ChangePasswordEntity();
        entity.setCurrentPassword(currentPassword);
        entity.setNewPassword(passwordWithoutSpecialChars);
        entity.setRepeatNewPassword(passwordWithoutSpecialChars);

        changePasswordPage.fillUpChangePasswordForm(entity);

        String errorMessage = changePasswordPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Пароль должен содержать хотя бы один специальный символ: !@#$%^&*(),.?\":{}|<>"),
                "Ожидаемая ошибка: Пароль должен содержать хотя бы один специальный символ, получено: " + errorMessage);
    }

    @Test(groups = "validation")
    public void testNewPasswordWithSpaces() {
        String passwordWithSpaces = "Password 123!";
        ChangePasswordEntity entity = new ChangePasswordEntity();
        entity.setCurrentPassword(currentPassword);
        entity.setNewPassword(passwordWithSpaces);
        entity.setRepeatNewPassword(passwordWithSpaces);

        changePasswordPage.fillUpChangePasswordForm(entity);

        String errorMessage = changePasswordPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Пароль не должен содержать пробелы"),
                "Ожидаемая ошибка: Пароль не должен содержать пробелы, получено: " + errorMessage);
    }

    @Test(groups = "validation")
    public void testChangePasswordWithEmptyRepeatPassword() {
        ChangePasswordEntity entity = new ChangePasswordEntity();
        entity.setCurrentPassword(currentPassword);
        entity.setNewPassword("NewPassword123!");
        entity.setRepeatNewPassword("");

        changePasswordPage.fillUpChangePasswordForm(entity);

        String errorMessage = changePasswordPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Обязательно к заполнению"),
                "Ожидаемая ошибка: Обязательно к заполнению, получено: " + errorMessage);
    }

    @Test(groups = "validation")
    public void testNewPasswordMismatch() {
        ChangePasswordEntity entity = new ChangePasswordEntity();
        entity.setCurrentPassword(currentPassword);
        entity.setNewPassword("NewPassword123!");
        entity.setRepeatNewPassword("AnotherPassword123!");

        changePasswordPage.fillUpChangePasswordForm(entity);

        String errorMessage = changePasswordPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Пароли должны совпадать"),
                "Ожидаемая ошибка: Пароли должны совпадать, получено: " + errorMessage);
    }

    @Test(groups = "validation")
    public void testNewPasswordNoUppercase() {
        String passwordWithoutUppercase = "password123!";
        ChangePasswordEntity entity = new ChangePasswordEntity();
        entity.setCurrentPassword(currentPassword);
        entity.setNewPassword(passwordWithoutUppercase);
        entity.setRepeatNewPassword(passwordWithoutUppercase);

        changePasswordPage.fillUpChangePasswordForm(entity);

        String errorMessage = changePasswordPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Пароль должен содержать хотя бы одну заглавную букву латиницы"),
                "Ожидаемая ошибка: Пароль должен содержать хотя бы одну заглавную букву латиницы, получено: " + errorMessage);
    }

    @Test(groups = "validation")
    public void testChangePasswordPlaceholders() {
        changePasswordPage.verifyFormChangePasswordPlaceholders();
        Allure.step("Проверка плейсхолдеров полей Введите текущий пароль, Введите новый пароль, Повторите пароль");
    }
}

//    public void testChangePasswordWithValidData() {
//        browserHelper.refreshPage();
//        ChangePasswordEntity entity = randomUtils.generateRandomChangePasswordEntity();
//        entity.setCurrentPassword(currentPassword);
//
//        changePasswordPage.fillUpChangePasswordForm(entity);
//
//        String successMessage = changePasswordPage.getErrorMessage();
//        Assert.assertTrue(successMessage.isEmpty(), "Ожидаемое отсутствие ошибок, получено: " + successMessage);
//
//        String newPassword = entity.getNewPassword();
//
//
//
//        LoginEntity loginEntity = new LoginEntity();
//        loginEntity.setEmail("GOku.first@proton.me");
//        loginEntity.setPassword(newPassword);
//        loginPage.fillUpLoginForm(loginEntity);
//
//        String loginSuccessMessage = privateProfilePage.getNameText();
//        Assert.assertNotNull(loginSuccessMessage, "Не удалось авторизоваться с новым паролем!");
//
//        ChangePasswordEntity revertEntity = new ChangePasswordEntity();
//        revertEntity.setCurrentPassword(newPassword);  // Старый пароль для восстановления
//        revertEntity.setNewPassword(currentPassword);  // Восстановление исходного пароля
//        revertEntity.setRepeatNewPassword(currentPassword);  // Повторный ввод старого пароля
//
//        changePasswordPage.fillUpChangePasswordForm(revertEntity);
//
//        String revertSuccessMessage = changePasswordPage.getErrorMessage();
//        Assert.assertTrue(revertSuccessMessage.isEmpty(), "Ожидаемое отсутствие ошибок при восстановлении пароля!");
//
//        loginOnce();
//    }