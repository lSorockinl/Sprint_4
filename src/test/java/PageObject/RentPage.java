package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RentPage {
    private WebDriver driver;
    //загаловок страницы
    private final By header = By.className("Order_Header__BZXOb");
    //поле Когда привезти самокат
    private final By rentalDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //поле Срок аренды
    private final By rentalTimeField = By.className("Dropdown-placeholder");
    //список срока аренды
    private final By rentalTime = By.xpath(".//*[(@role ='option' and text()='сутки')]");
    //чекбокс цвета "Черный жемчуг"
    private final By checkBoxColourBlack = By.xpath(".//input[@id='black']");
    //чекбокс цвета "Серая безысходность"
    private final By checkBoxColourGrey = By.xpath(".//input[@id='grey']");
    //поле Комментарий для курьера
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //кнопка Заказать
    private final By orderButton = By.xpath(".//button[(@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать')]");
    //кнопка Да
    private final By orderButtonYes = By.xpath(".//*[@id='root']/div/div[2]/div[5]/div[2]/button[2]");
    //окно Заказ оформлен
    private final By successAlert = By.className("Order_ModalHeader__3FDaJ");

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadHeader() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }
    //метод для выбора даты когда привезти самокат
    public void setRentalDateField(String rentalDate) {
        driver.findElement(rentalDateField).sendKeys(rentalDate);
        driver.findElement(rentalDateField).sendKeys(Keys.ENTER);
    }
    //метод для выбора срока аренды
    public void setRentalTimeField() {
        driver.findElement(rentalTimeField).click();
        driver.findElement(rentalTime).click();
    }
    //метод для выбора черного цвета
    public void clickCheckBoxColourBlack() {
        driver.findElement(checkBoxColourBlack).click();
    }
    //метод для выбора серого цвета
    public void clickCheckBoxColourGrey() {
        driver.findElement(checkBoxColourGrey).click();
    }
    //метод для ввода комментария
    public void setCommentField(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }
    //метод для нажатия кнопки Заказать
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
    //метод для нажатия на кнопку Да
    public void clickOrderButtonYes() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(orderButtonYes));
        driver.findElement(orderButtonYes).click();
    }
    //общий метод оформить заказ
    public void makingRentPage(String rentalDate, String comment) {
        waitForLoadHeader();
        setRentalDateField(rentalDate);
        setRentalTimeField();
        clickCheckBoxColourBlack();
        clickCheckBoxColourGrey();
        setCommentField(comment);
        clickOrderButton();
        clickOrderButtonYes();
    }
    //метод для окна Заказ оформлен
    public String getSuccessAlert() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(successAlert))).isDisplayed();
        return driver.findElement(successAlert).getText();
    }
}