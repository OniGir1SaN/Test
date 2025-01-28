package com.demoqa.helpers;

import com.demoqa.drivers.DriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertHelper {

    private WebDriver driver = DriverManager.getDriver();

    public AlertHelper(WebDriver driver){
        this.driver = driver;
    }

    // Переключаемся с html кода на Alert
    public Alert getAlert(){
        new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }

    // Переключившись принимаем условие
    public void acceptAlert(){
        getAlert().accept();
    }

    // Переключившись отклоняем условие
    public void dismissAlert(){
        getAlert().dismiss();
    }

    // Переключившись вызываем текст Alert
    public String getAlertText(){
        return getAlert().getText();
    }

    // проверяем есть ли Alert
    public boolean isAlertPresent(){
        try{
            driver.switchTo().alert().accept();
            return true;
        } catch (NoAlertPresentException exception){
            return false;
        }
    }

    // Если нет Alert то остановись
    public void  acceptAlertIfPresented(){
        if (!isAlertPresent())
            return;
        acceptAlert();
    }

    public void  dismissAlertIfPresented(){
        if (!isAlertPresent())
            return;
        dismissAlert();
    }

    // Пропиши текст и прими
    public void acceptPrompt(String txt){
        if (!isAlertPresent())
            return;
        Alert alert = getAlert();
        alert.sendKeys(txt);
        alert.accept();
    }
    // Пропиши текст и отклони
    public void dismissPrompt(String txt){
        if (!isAlertPresent())
            return;
        Alert alert = getAlert();
        alert.sendKeys(txt);
        alert.dismiss();
    }
}
