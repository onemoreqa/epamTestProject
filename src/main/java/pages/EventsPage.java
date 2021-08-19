package pages;

import base.BasePage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    @FindBy(xpath = "//*[@data-value='Canada']")
    private WebElement canadaItem;
    @FindBy(css = "#filter_location")
    private WebElement locationDropDown;
    @FindBy(css = ".evnt-cards-container:nth-child(1) .evnt-event-card .date")
    public WebElement eventDate;

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

    @Step("Начинаем работу с фильтрами событий")
    public void setFilter() {

        waitVisibilityOfElement(locationDropDown);
        locationDropDown.click();
        canadaItem.click();
        logger.info("Дождались появления Канады в выпадахе и кликаем: " + canadaItem);

        webDriverWait.until(ExpectedConditions.
                invisibilityOf(driver.findElement(By.xpath("//*[contains(@class, 'evnt-global-loader')]"))));

        Allure.addAttachment("Отфильтровались по событиям Канады",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

    }

    @Step("Проверка даты ближайшего мероприятия")
    public void checkUpcomingEventDate() {
        String upcomingEventDataString = eventDate.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
        LocalDate upcomingEventData = LocalDate.parse(upcomingEventDataString, formatter);
        LocalDate today = LocalDate.now();
        boolean isUpcomingEvent = today.isBefore(upcomingEventData);
        assertTrue(isUpcomingEvent);
        logger.info("Проверили, что дата предстоящего события больше текущей даты");
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

    @Step("Проверка количества событий в счетчике")
    public int getNumberOfEventsOnLink() {
        int number = Integer.parseInt(eventCounterSpan.getText());
        logger.info("Кол-во событий в счетчике: " + number);

        return number;
    }

    @Step("Проверка количества предстоящих событий на панели")
    public int getNumberOfUpcomingEventsOnPanels() {
        int number = driver.findElements(By.cssSelector("div.evnt-event-card")).size();
        logger.info("На панель выведено предстоящих событий: " + number);

        return number;
    }

    @Step("Проверка количества прошедших событий на панели")
    public int getNumberOfPastEventsOnPanels() {
        int number = driver.findElements(By.cssSelector("div.evnt-event-card")).size();
        logger.info("На панель выведено прошедших событий: " + number);

        return number;
    }

}