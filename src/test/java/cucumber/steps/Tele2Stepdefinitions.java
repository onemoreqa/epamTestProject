package cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Tele2Stepdefinitions {

    protected static WebDriver driver;
    String inputLocator = "//*[@id=\"searchNumber\"]";
    String loaderLocator= "//*[@class=\"preloader-icon\"]";
    String numbersLocator = "//span[@class=\"phone-number\"]";

    @Before
    public void startUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @After
    public void end(){
        if (driver!=null)
            driver.quit();
    }

    @Given("Находясь на сайте теле2")
    public void weAreOnTheSite() {
        startUp();
        driver.get("https://msk.tele2.ru/shop/number");
        String firstNumber = getElement1AfterElement2IsGone(By.xpath(numbersLocator), By.xpath(loaderLocator)).getText();
        System.out.println("Значение первого номера: " + firstNumber);
    }

    @When("Выполнить поиск номера по ключу {string}")
    public void submitPhonenumberBy(String searchQuery) {
        getElement(By.xpath(inputLocator)).sendKeys(searchQuery + Keys.ENTER);
        System.out.println("Начинаем поиск номера по тестовому выражению: " + searchQuery);
        getElement(By.xpath(loaderLocator));
    }

    @Then("Дождаться появления свободных номеров co значением {string}")
    public void waitFreeNumbers(String searchQuery) {
        String firstFoundNumber = getElement1AfterElement2IsGone(By.xpath(numbersLocator), By.xpath(loaderLocator)).getText();
        System.out.println("Значение первого номера после поиска: " + firstFoundNumber);
        Assert.assertNotEquals("В предложенном номере отсутствует искомый ключ", -1, firstFoundNumber.lastIndexOf(searchQuery));
        end();
    }

    private WebElement getElement(By locator){
        return new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(locator));
    }

    private WebElement getElement1AfterElement2IsGone(By locator1, By locator2){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator2));
        return new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(locator1));
    }

}
