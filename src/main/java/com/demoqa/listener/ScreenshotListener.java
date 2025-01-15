package com.demoqa.listener;

import com.demoqa.drivers.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {

    private WebDriver driver;

    @Override
    public void onStart(ITestContext context) {
        // Инициализация драйвера перед тестами
        driver = DriverManager.getDriver();
        if (driver != null) {
            System.out.println("Driver initialized successfully.");
        } else {
            System.err.println("Driver initialization failed.");
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
        if (driver != null) {
            System.out.println("Taking screenshot...");
            saveScreenshotPNG(result.getName()); // Делает скриншот при неудаче
        } else {
            System.out.println("Driver is null, cannot take screenshot.");
        }
    }

    /**
     * Снимок экрана и добавление в Allure отчет.
     * @param testName Имя теста, используется в качестве аттачмента.
     * @return Скриншот в формате байтов.
     */
    @Attachment(value = "Screenshot for failed test: {testName}", type = "image/png")
    public byte[] saveScreenshotPNG(String testName) {
        try {
            if (driver instanceof TakesScreenshot) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                System.out.println("Screenshot captured for test: " + testName);
                return screenshot;
            } else {
                System.err.println("Driver does not support screenshots.");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test execution finished.");
        // Можно добавить код для очистки ресурсов, анализа отчетов и т.д.
    }
}
