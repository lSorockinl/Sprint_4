package Tests;

import PageObject.HomePage;
import PageObject.OrderPage;
import PageObject.RentPage;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MakeOrder {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStationFromOrder;
    private final String phoneNumber;
    private final String rentalDate;
    private final String comment;

    public MakeOrder(String name, String surname, String address, String metroStationFromOrder, String phoneNumber, String rentalDate, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStationFromOrder = metroStationFromOrder;
        this.phoneNumber = phoneNumber;
        this.rentalDate = rentalDate;
        this.comment = comment;
    }

    @Parameterized.Parameters()
    public static Object[][] orderParameters() {
        return new Object[][]{
                {"Тест", "Тест", "ТестТест", "Тест", "89996669999", "24.02.2024", "тест"},
                {"Денис", "Сорокин", "ул.Пушкина", "Бульвар Рокоссовского", "+79996669999", "24.02.2024", "Побыстрее"},
        };
    }

    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void withHeaderOrderButtonTest() {
        HomePage homePage = new HomePage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        homePage.waitLoadOrderButton();
        homePage.openOrderPageWithHeaderOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.makingOrder(name, surname, address, metroStationFromOrder, phoneNumber);
        orderPage.clickNextButton();

        RentPage rentPage = new RentPage(driver);
        rentPage.makingRentPage(rentalDate, comment);
        assertEquals("Ошибка при оформлении заказа", true, rentPage.getSuccessAlert().contains("Заказ оформлен"));
    }

    @Test
    public void createOrderMiddleOrderButtonTest() {

        HomePage homePage = new HomePage(driver);

        driver.get("https://qa-scooter.praktikum-services.ru/");
        homePage.waitLoadOrderButton();
        homePage.openOrderPageWithMiddleOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.makingOrder(name, surname, address, metroStationFromOrder, phoneNumber);
        orderPage.clickNextButton();

        RentPage rentPage = new RentPage(driver);
        rentPage.makingRentPage(rentalDate, comment);
        assertEquals("Ошибка при оформлении заказа", true, rentPage.getSuccessAlert().contains("Заказ оформлен"));
    }

    @After
    public void tearDOwn() {
        driver.quit();
    }
}