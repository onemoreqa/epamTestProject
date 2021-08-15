package epamTestProject.pages;


import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.io.ByteArrayInputStream;

import static java.lang.String.format;


/**
 * Sample page
 */
public class HomePage extends Page {

    @FindBy(xpath = "//*[@href='/events']")
    public WebElement buttonEvents;
    @FindBy(xpath = "//a[contains(@href,'video')]")
    public WebElement buttonVideo;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыта страница {baseUrl}")
    public void open(String baseUrl) {
        driver.get(baseUrl);
        Allure.addAttachment("Главная страница Events",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Открыта страница Events")
    public void openEvents() {
        buttonEvents.click();
        Allure.addAttachment("Вкладка Events",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Открыта страница Video")
    public void openVideo() {
        buttonVideo.click();
        Allure.addAttachment("Вкладка Video",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
}
