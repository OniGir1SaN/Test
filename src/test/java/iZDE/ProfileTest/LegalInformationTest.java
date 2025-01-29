package iZDE.ProfileTest;

import com.demoqa.entities.iZDE.LoginEntity;
import com.demoqa.enums.iZDE.Endpoints;
import com.demoqa.utils.ConfigReader;
import iZDE.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import java.time.Duration;

public class LegalInformationTest extends BaseTest {

    private WebDriverWait wait;

    @BeforeClass
    public void loginOnce() {
        browserHelper.open(ConfigReader.getValue("baseURL") + Endpoints.SIGNIN.getEndpoint());

        LoginEntity entity = randomUtils.validLoginEntity();
        loginPage.fillUpLoginForm(entity);
        mainMenuPage.clickProfileButton();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testUserAgreementButton() {
        legalInformationPage.clickUserAgreementButton();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='rpv-core__text-layer-text' and text()='ПОЛЬЗОВАТЕЛЬСКОЕ СОГЛАШЕНИЕ']")));

        String actualText = legalInformationPage.getUserAgreementText();
        Assert.assertEquals(actualText, "ПОЛЬЗОВАТЕЛЬСКОЕ СОГЛАШЕНИЕ", "Текст пользовательского соглашения некорректен!");
    }

    @Test
    public void testPrivacyPolicyButton() {
        legalInformationPage.clickPrivacyPolicyButton();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='rpv-core__text-layer-text' and text()='ПОЛИТИКА КОНФИДЕНЦИАЛЬНОСТИ']")));

        String actualText = legalInformationPage.getPrivacyPolicyText();
        Assert.assertEquals(actualText, "ПОЛИТИКА КОНФИДЕНЦИАЛЬНОСТИ", "Текст политики конфиденциальности некорректен!");
    }

    @Test
    public void testPublicOfferButton() {
        legalInformationPage.clickPublicOfferButton();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='rpv-core__text-layer-text' and text()='ПУБЛИЧНАЯ ОФЕРТА']")));

        String actualText = legalInformationPage.getPublicOfferText();
        Assert.assertEquals(actualText, "ПУБЛИЧНАЯ ОФЕРТА", "Текст публичной оферты некорректен!");
    }
}
