package com.demoqa.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.time.Duration;

public class OperaWebDriver {

    public static WebDriver loadOperaDriver(){
        WebDriverManager.operadriver().setup(); // Установка драйвера через WebDriverManager
        WebDriver driver = new OperaDriver(); // Создание экземпляра OperaDriver
        driver.manage().window().maximize(); // Максимизация окна браузера
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); // Установка времени ожидания
        return driver;
    }
}
