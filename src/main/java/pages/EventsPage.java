package pages;

import base.BasePage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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

    @FindBy(xpath =  "//*[@class=\"evnt-details-cell language-cell\"]//span")
    public WebElement cardLanguage;
    @FindBy(xpath = "//*[@class=\"evnt-event-name\"]//span")
    public WebElement cardEventName;
    @FindBy(xpath = "//*[@class=\"evnt-dates-cell dates\"]//span[@class=\"date\"]")
    public WebElement cardEventDate;
    @FindBy(xpath = "//*[@class=\"evnt-dates-cell dates\"]//span[@class=\"status reg-close\"]")
    public WebElement cardEventRegStatus;
    @FindBy(xpath = "//*[@class=\"speakers-wrapper\"]")
    public WebElement cardEventSpeakersWrapper;

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

    @Step("Проверка содержимого карточек в прошедших событиях")
    public void checkPastCountersMatch() {

        Assertions.assertTrue(cardLanguage
                .isDisplayed(), "На карточке события не отображается язык");
        logger.info("На карточке прошедшего события есть информация об языке");

        Assertions.assertTrue(cardEventName
                .isDisplayed(), "На карточке события не отображается название события");
        logger.info("На карточке прошедшего события есть информация о названии события");

        Assertions.assertTrue(cardEventDate
                .isDisplayed(), "На карточке события не отображается период проведения события");
        logger.info("На карточке прошедшего события есть информация о периоде проведения события");

        Assertions.assertTrue(cardEventRegStatus
                .isDisplayed(), "На карточке события не отображается статус регистрации события");
        logger.info("На карточке прошедшего события есть информация о статусе регистрации события");

        Assertions.assertTrue(cardEventSpeakersWrapper
                .isDisplayed(), "На карточке события не отображается иконка спикеров события");
        logger.info("На карточке прошедшего события присутствует иконка спикеров события");

        scrollDown();
        Assertions.assertEquals(getNumberOfEventsOnLink(), getNumberOfPastEventsOnPanels());

        Allure.addAttachment("Проскролл до самого низа в прошедших событиях",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        logger.info("Проскроллили до самого низа страницы прошедших событий. Кол-во событий совпало.");
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

    //@Step("Проверка количества прошедших событий на панели")
    public int getNumberOfPastEventsOnPanels()  {

        int number = driver.findElements(By.cssSelector("div.evnt-event-card")).size();
        //logger.info("На панель выведено прошедших событий: " + number);

        return number;
    }

    @Step("Проскролл вниз для загрузки всех прошедших событий")
    public void scrollDown() {
        String js = "window.scrollTo(0, document.body.scrollHeight);";

        int infoCardCount = getNumberOfEventsOnLink();
        int visibleCardCount = 0;

        while (infoCardCount != visibleCardCount) {
            visibleCardCount = getNumberOfPastEventsOnPanels();
            ((JavascriptExecutor) driver).executeScript(js);
        }

    }

}