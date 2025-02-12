package com.demoqa.helpers;

import com.demoqa.drivers.DriverManager;
import com.demoqa.utils.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.demoqa.drivers.DriverManager.getDriver;

public class WebElementActions {

    private Actions actions;

    BrowserHelper  browserHelper= new BrowserHelper(getDriver());

    public WebElementActions(WebDriver driver) {
        this.actions = new Actions(DriverManager.getDriver());
    }

    /*---------- Взаимодействия с клавиатурой и мышкой -------------------------*/

    // Двойное нажатие
    public WebElementActions doubleClick(WebElement element) {
        waitElementToBeVisible(element);
        waitElementToBeClickable(element);
        actions.doubleClick(element).perform();
        return this;
    }

    // Правый клик мыши
    public WebElementActions rightClick(WebElement element) {
        waitElementToBeVisible(element);
        waitElementToBeClickable(element);
        actions.contextClick(element).perform();
        return this;
    }

    // Наводит курсор к элементу
    public WebElementActions moveToElement(WebElement element) {
        waitElementToBeVisible(element);
        actions.moveToElement(element).perform();
        return this;
    }

    // Кликни когда элемент станет кликабельным
    public WebElementActions click(WebElement element) {
        waitElementToBeClickable(element);
        scrollToElement(element);
        highlightElement(element);
        element.click();
        return this;
    }

    /*--------------------------------------------------------------------------*/



    /*---------- JavaScript методы ---------------------------------------------*/

    // Кликает по html коду
    public WebElementActions jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].click()", element);
        return this;
    }

    // Заполняет по html коду
    public WebElementActions jsSendKeys(WebElement element, String txt) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].value=arguments[1];", element, txt);
        return this;
    }
    /*--------------------------------------------------------------------------*/



    /*---------- Ожидание и действие + Приколюхи -------------------------------*/

    // Пауза
    public void pause(Integer milliseconds){
        try {
            TimeUnit.MICROSECONDS.sleep(milliseconds);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    // Метод для добавления задержки
    public void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Ждет пока не станет кликабельным
    public WebElementActions waitElementToBeClickable(WebElement element) {
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }

    // Ждет пока не станет виден
    public WebElementActions waitElementToBeVisible(WebElement element) {
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(element));
        return this;
    }

    public WebElementActions scrollToElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));

        Point elementLocation = element.getLocation();
        int elementYPosition = elementLocation.getY();

        Dimension windowSize = DriverManager.getDriver().manage().window().getSize();
        int windowHeight = windowSize.getHeight();

        if (elementYPosition > windowHeight) {
            new Actions(DriverManager.getDriver())
                    .moveToElement(element)
                    .perform();
        } else {
            new Actions(DriverManager.getDriver())
                    .moveToElement(element)
                    .perform();
        }

        return this;
    }

    // Подсвечивает элемент с которым взаимодействуем
    public WebElementActions highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].style.border='3px solid purple';", element);
        return this;
    }

    // Ожидает игнорируя ошибки
    public static void waitAndClick(WebDriver driver, By locator, Duration timeout, Duration polling) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(timeout)
                .pollingEvery(polling)
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });

        element.click();
    }

    public void verifyPlaceholders(WebElement[] elements, String[] expectedPlaceholders, String[] fieldNames) {
        System.out.println("Плейсхолдеры на странице:");
        for (int i = 0; i < elements.length; i++) {
            String actualPlaceholder = elements[i].getAttribute("placeholder");
            Assert.assertEquals(actualPlaceholder, expectedPlaceholders[i],
                    "Плейсхолдер для поля '" + fieldNames[i] + "' неверный");
            System.out.println(fieldNames[i] + ": " + actualPlaceholder);
        }
    }

    public void verifyText(String actualText, String expectedText, String message) {
        try {
            Assert.assertEquals(actualText, expectedText, message);
            System.out.println("Успешная проверка: " + message);
        } catch (AssertionError e) {
            System.err.println("Ошибка проверки: " + message + "\n" +
                    "Ожидалось: " + expectedText + ", но было: " + actualText);
            throw e; // Повторно выбрасываем исключение для учета в отчете.
        }
    }

    public void verifyElementIsDisplayed(WebElement element, String elementName) {
        try {
            Assert.assertTrue(element.isDisplayed(), "Элемент '" + elementName + "' не отображается на странице.");
            System.out.println("Элемент '" + elementName + "' успешно отображается.");
        } catch (AssertionError e) {
            System.err.println("Ошибка: элемент '" + elementName + "' не отображается на странице.");
            throw e; // Повторно выбрасываем исключение для учета в отчете.
        }
    }

    // Универсальный метод для проверки базового URL
    public boolean assertBaseUrlIsCurrent() {
        String expectedBaseUrl = ConfigReader.getValue("baseURL");
        String currentBaseUrl = getDriver().getCurrentUrl();

        Assert.assertTrue(currentBaseUrl.startsWith(expectedBaseUrl),
                "URL не соответствует базовому URL после возврата. Текущий URL: " + currentBaseUrl);
        return false;
    }

    public void verifyPage(String expectedUrlPart, By elementLocator, String errorMessage) {
        String currentUrl = getDriver().getCurrentUrl();

        Assert.assertTrue(currentUrl.contains(expectedUrlPart),
                "URL не содержит '" + expectedUrlPart + "'. Текущий URL: " + currentUrl);

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));

        Assert.assertNotNull(element, errorMessage + " не найден на странице.");
        Assert.assertTrue(element.isDisplayed(), errorMessage + " не отображается.");

        browserHelper.goBack();
        assertBaseUrlIsCurrent();
    }

    public boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    /*--------------------------------------------------------------------------*/



    /*---------- Заполнить элемент ---------------------------------------------*/

    // заполняет элемент
    public WebElementActions sendKeys(WebElement element, String txt) {
        waitElementToBeVisible(element);
        scrollToElement(element);
        highlightElement(element);
        element.clear();
        element.sendKeys(txt);
        return this;
    }

    public WebElementActions clearSendKeys(WebElement element, String txt) {
        waitElementToBeVisible(element);
        scrollToElement(element);
        highlightElement(element);
        element.clear();
        element.sendKeys(txt);
        return this;
    }


    // заполняет элемент и нажимает Enter
    public WebElementActions sendKeysWithEnter(WebElement element, String txt) {
        waitElementToBeVisible(element);
        scrollToElement(element);
        highlightElement(element);
        element.sendKeys(txt);
        element.sendKeys(Keys.ENTER);//в sendKeys передаем клавиатуру и
        // дальше выбираем какую кнопку хотим нажать
        return this;
    }

    /*--------------------------------------------------------------------------*/



    /*---------- Рандомные значения ---------------------------------------------*/

    // метод находит все элементы по-указанному xPath, кладет их в лист
    // рандомно выбирает один из элементов
    //выбирает один элемент
    public WebElement randomElementSelection(List<WebElement> elements) {
        if (elements == null || elements.isEmpty()) {
            throw new IllegalArgumentException("Элементы не найдены");
        }

        Random random = new Random();
        int index = random.nextInt(elements.size());
        return elements.get(index);
    }

    // метод находит все элементы по-указанному xPath, кладет их в лист
    // рандомно выбирает один или несколько элементов, и кликает на них
    // Измененный метод clickRandomElements для возвращения списка элементов
    // Метод для случайного выбора элементов (без кликов)
    public List<WebElement> getRandomElements(List<WebElement> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Элементы не найдены");
        }
        Random random = new Random();

        // Выбираем случайное количество элементов от 1 до 15, но не больше, чем размер списка
        int numberOfElementsToSelect = random.nextInt(Math.min(15, list.size())) + 1;

        List<WebElement> randomElements = new ArrayList<>();
        for (int i = 0; i < numberOfElementsToSelect; i++) {
            int index = random.nextInt(list.size()); // Выбираем случайный элемент
            randomElements.add(list.get(index));
            list.remove(index); // Удаляем выбранный элемент из списка, чтобы не выбрать его снова
        }

        return randomElements; // Возвращаем список выбранных элементов
    }



    // метод находит элемент по-указанному xPath, кликая на него раскрывает всплывающий список
    // по указанному xPath кладет весь всплывающий список в лист
    // рандомно выбирает один из элементов
    public String randomElementSelection(String xpathDropdownControl, String xpathDropdownItems){
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        // Нахождение контроллера списка и открытие списка
        WebElement dropdownControl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathDropdownControl)));
        dropdownControl.click();
        // Явное ожидание появления элементов списка
        List<WebElement> dropdownItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathDropdownItems)));
        // Создание списка для хранения текстов элементов
        ArrayList<String> itemList = new ArrayList<>();
        // Добавление всех текстов элементов в ArrayList
        for (WebElement item : dropdownItems) {
            itemList.add(item.getText());
        }
        Random random = new Random();
        int index = random.nextInt(itemList.size());
        return itemList.get(index);
    }

    public void increaseAndDecreaseValue(WebElement plusButton, WebElement minusButton, WebElement valueElement) {
        int initialValue = Integer.parseInt(valueElement.getText());

        Random random = new Random();
        int randomIncrements = random.nextInt(5) + 1;
        int randomDecrements = random.nextInt(randomIncrements + 1);

        // Увеличиваем значение
        for (int i = 0; i < randomIncrements; i++) {
            click(plusButton);
        }

        // Уменьшаем значение, не давая ему стать меньше 1
        for (int i = 0; i < randomDecrements; i++) {
            int currentValue = Integer.parseInt(valueElement.getText());
            if (currentValue > 1) {
                click(minusButton);
            }
        }

        int expectedValue = initialValue + randomIncrements - randomDecrements;
        expectedValue = Math.max(expectedValue, 1); // Убедимся, что значение не меньше 1

        int actualValue = Integer.parseInt(valueElement.getText());

        Assert.assertEquals(actualValue, expectedValue, "Значение не совпадает с ожидаемым!");
    }


    /*--------------------------------------------------------------------------*/

}
