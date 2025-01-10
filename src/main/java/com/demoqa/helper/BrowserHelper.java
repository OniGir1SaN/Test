package com.demoqa.helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BrowserHelper {

    private WebDriver driver;

    public BrowserHelper(WebDriver driver) {
        this.driver = driver;
    }

    // Открыть URL
    public void open(String url) {
        driver.navigate().to(url);
    }

    // Вернуться назад
    public void goBack() {
        driver.navigate().back();
    }

    // Перейти вперед
    public void goForward() {
        driver.navigate().forward();
    }

    // Обновить страницу
    public void refreshPage() {
        driver.navigate().refresh();
    }

    // Получить список всех вкладок
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    // Переключиться на вкладку по индексу
    public void switchToWindow(int index) {
        List<String> windowHandles = new ArrayList<>(getWindowHandles());

        if (index < 0 || index >= windowHandles.size())
            throw new IllegalArgumentException("Invalid index: " + index);

        driver.switchTo().window(windowHandles.get(index));
    }

    // Переключиться на последнюю (новую) вкладку
    public void switchToNewTab() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    // Открыть новую вкладку с заданным URL
    public void openNewTab(String url) {
        ((JavascriptExecutor) driver).executeScript("window.open('" + url + "', '_blank');");

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    // Переключиться на родительскую вкладку
    public void switchToParentWindow() {
        List<String> windowHandles = new ArrayList<>(getWindowHandles());
        driver.switchTo().window(windowHandles.get(0));
    }

    // Переключиться на родительскую вкладку и закрыть все дочерние
    public void switchToParentWindowAndCloseChildren() {
        switchToParentWindow();
        List<String> windowHandles = new ArrayList<>(getWindowHandles());

        for (int i = 1; i < windowHandles.size(); i++) {
            driver.switchTo().window(windowHandles.get(i));
            driver.close();
        }

        switchToParentWindow();
    }

    // Открыть новую вкладку
    public void openNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
    }
}
