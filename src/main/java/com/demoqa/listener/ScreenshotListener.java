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
            saveScreenshotPNG(); // Делает скриншот при неудаче
        } else {
            System.out.println("Driver is null, cannot take screenshot.");
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG() {
        try {
            if (driver instanceof TakesScreenshot) {
                // Снимаем скриншот страницы
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
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
    }
}
