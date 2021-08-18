package pages;

import base.BasePage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.io.ByteArrayInputStream;
import java.util.List;

public class EventsPage extends BasePage {

    @FindBy(xpath = "(//*[contains(text(),'Upcoming events')]/..)[2]")
    public WebElement tabUpcomingEvents;
    @FindBy(xpath = "(//*[contains(text(),'Past Events')]/..)[2]")
    public WebElement tabPastEvents;
    @FindBy(css = "a.active:nth-child(1) .evnt-tab-counter")
    public WebElement counterUpcomingEvents;
    @FindBy(css = ".evnt-event-card")
    public List<WebElement> eventCards;
    @FindBy(xpath = "//div[contains(@class, 'evnt-cards-container')]")
    private WebElement cardsContainer;
    @FindBy(css = "a.active:nth-child(1) > span:nth-child(3)")
    private WebElement eventCounterSpan;

    public EventsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыта вкладка Upcoming Events")
    public void openUpcomingEventsTab() {
        waitVisibilityOfElement(tabUpcomingEvents);
        logger.info("Дождались появления: " + tabUpcomingEvents);

        tabUpcomingEvents.click();
        waitVisibilityOfElement(cardsContainer);
        logger.info("Дождались появления: " + cardsContainer);
        Allure.addAttachment("Вкладка Upcoming Events",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Открыта вкладка Past Events")
    public void openPastEventsTab() {
        waitVisibilityOfElement(tabPastEvents);
        logger.info("Дождались появления: " + tabPastEvents);

        tabPastEvents.click();
        waitVisibilityOfElement(cardsContainer);
        logger.info("Дождались появления: " + cardsContainer);
        Allure.addAttachment("Вкладка Past Events",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    // @TODO тут бы нужен switch case (PAST / FUTURE events)

    @Step("Проверка количества карточек в Ближайших событиях")
    public void checkUpcomingCountersMatch() {
        Assertions.assertEquals(getNumberOfEventsOnLink(), getNumberOfUpcomingEventsOnPanels());
        logger.info("Число предстоящих событий совпало с счетчиком");
    }

    @Step("Проверка количества карточек в прошедших событиях")
    public void checkPastCountersMatch() {
        Assertions.assertEquals(getNumberOfEventsOnLink(), getNumberOfPastEventsOnPanels());
        logger.info("Число прошедших событий совпало с счетчиком");
    }

    private int getNumberOfEventsOnLink() {
        int number = Integer.parseInt(eventCounterSpan.getText());
        logger.info("Кол-во событий в счетчике: " + number);

        return number;
    }

    private int getNumberOfUpcomingEventsOnPanels() {
        int number = driver.findElements(By.cssSelector("div.evnt-event-card")).size();
        logger.info("На панель выведено предстоящих событий: " + number);

        return number;
    }

    private int getNumberOfPastEventsOnPanels() {
        int number = driver.findElements(By.cssSelector("div.evnt-event-card")).size();
        logger.info("На панель выведено прошедших событий: " + number);

        return number;
    }

}