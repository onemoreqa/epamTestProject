package cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class OtusStepdefinitions {

    protected static WebDriver driver;

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

    @Given("Находясь на сайте Otus")
    public void weAreOnTheSiteOtus() {
        startUp();
        String otusSite = "https://otus.ru";
        driver.get(otusSite);
    }

    @When("Пользователь переходит в контакты")
    public void userGoesToTabContacts() {
        driver.findElement(By.xpath("//a[contains(@href,'contacts')]")).click();
    }

    @Then("Адрес соответствует ожидаемому")
    public void weCheckAddress() {
        String adresValue = "125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02";
        String adresLocator = "//div[text()='Адрес']/following::div[1]";
        Assert.assertEquals("Проверка адреса", adresValue, driver.findElement(By.xpath(adresLocator)).getText());
        end();
    }
}
