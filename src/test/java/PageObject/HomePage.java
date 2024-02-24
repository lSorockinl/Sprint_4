package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

public class HomePage {
    private static WebDriver driver;
    //кнопка заказа в шапке
    private By headerOrderButton = By.className("Button_Button__ra12g");
    //кнопка заказать на лендинге
    private By middleOrderButton = By.xpath(".//*[@id='root']/div/div[1]/div[4]/div[2]/div[5]/button");
    //кнопка куки
    private final By cookieButton = By.id("rcc-confirm-button");
    //FAQ
    private By listFaq = By.xpath(".//div[contains(@class, 'Home_FAQ')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadOrderButton() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(headerOrderButton));
    }

    //метод для нажатия на кнопку кук
    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }

    //метод для нажатия на кнопку "Заказать" в середине лендинга
    public void openOrderPageWithMiddleOrderButton() {
        WebElement middleButton = driver.findElement(middleOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", middleButton);
        driver.findElement(middleOrderButton).click();
    }

    //метод для нажатия на кнопку "Заказать" в шапке
    public void openOrderPageWithHeaderOrderButton() {
        driver.findElement(headerOrderButton).click();
    }

    //метод для скрола к списку вопросов
    public void findListFaq() {
        WebElement element = driver.findElement(listFaq);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //метод клика на вопрос
    public void clickQuestion(String questionId) {
        driver.findElement(By.id(questionId)).click();
    }

    //метод для получения текста ответа
    public String getAnswerText(String answerId) {
        return driver.findElement(By.id(answerId)).getText();
    }

    //метод для проверки текста в FAQ
    public String checkAnswerText(String questionId, String answerId) {
        findListFaq();
        clickQuestion(questionId);
        return getAnswerText(answerId);
    }
}