package pages;


import base.BasePage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.ByteArrayInputStream;

public class MainPage extends BasePage {

    @FindBy(xpath = "//*[@href='/events']")
    public WebElement buttonEvents;

    @FindBy(xpath = "//a[contains(@href,'video')]")
    public WebElement buttonVideo;

    @FindBy(css = "#onetrust-accept-btn-handler")
    private WebElement acceptCookiesButton;

    @FindBy(css = "#onetrust-banner-sdk")
    private WebElement cookiesBanner;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public void acceptCookies() {

        webDriverWait.until(ExpectedConditions.visibilityOf(cookiesBanner));
        logger.info("Дожидаемся элемента: " + cookiesBanner);

        acceptCookiesButton.click();
        logger.info("Клик по элементу: " + acceptCookiesButton);

        waitInvisibilityOfElement(cookiesBanner);
        logger.info("Скрытие элемента: " + cookiesBanner);
    }

    @Step("Открыта страница {baseUrl}")
    public void open(String baseUrl) {
        driver.get(baseUrl);
        Allure.addAttachment("Главная страница Events",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Открыта вкладка Events")
    public void openEvents() {
        acceptCookies();
        buttonEvents.click();
        Allure.addAttachment("Вкладка Events",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        logElementIsClicked(buttonEvents);
    }

    @Step("Открыта вкладка Video")
    public void openVideo() {
        acceptCookies();
        buttonVideo.click();
        Allure.addAttachment("Вкладка Video",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        logElementIsClicked(buttonVideo);
    }
}
