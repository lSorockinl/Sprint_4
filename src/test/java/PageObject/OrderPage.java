package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private WebDriver driver;
    //загаловок страницы
    private By header = By.className("Order_Header__BZXOb");
    //поле Имя
    private By nameInputField = By.xpath(".//input[@placeholder='* Имя']");
    //поле Фамилия
    private By surnameInputField = By.xpath(".//input[@placeholder='* Фамилия']");
    //поле Адрес
    private By addressInputField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //поле Станция метро
    private By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    //поле Телефон
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //кнопка Далее
    private By nextButton = By.className("Button_Middle__1CSJM");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadHeader() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }
    //метод для ввода имени
    public void setName(String name) {
        driver.findElement(nameInputField).sendKeys(name);
    }
    //метод для ввода фамилии
    public void setSurname(String surname) {
        driver.findElement(surnameInputField).sendKeys(surname);
    }
    //метод для ввода адреса
    public void setAddress(String address) {
        driver.findElement(addressInputField).sendKeys(address);
    }
    //метод для выбора станци
    public void setMetroStation(String metroStationFromOrder) {
        driver.findElement(metroStationField).click();
        driver.findElement(metroStationField).sendKeys(metroStationFromOrder);
        driver.findElement(metroStationField).sendKeys(Keys.DOWN, Keys.ENTER);
    }
    //метод для ввода телефона
    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }
    //метод для нажатия на кнопку Далее
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
    //общий метод Сделать заказ
    public void makingOrder(String name, String surname, String address, String metroStationFromOrder, String phoneNumber) {
        waitLoadHeader();
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetroStation(metroStationFromOrder);
        setPhoneNumber(phoneNumber);
    }

}