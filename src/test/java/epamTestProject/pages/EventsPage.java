package epamTestProject.pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.ByteArrayInputStream;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EventsPage extends Page {

    @FindBy(css = "a.active:nth-child(1)")
    public WebElement tabUpcomingEvents;
    @FindBy(css = "a.active:nth-child(1) .evnt-tab-counter")
    public WebElement counterUpcomingEvents;
    @FindBy(css = ".evnt-event-card")
    public List<WebElement> eventCards;

    public EventsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыта вкладка Upcoming Events")
    public void openUpcomingEventsTab() {
        tabUpcomingEvents.click();
        Allure.addAttachment("Вкладка Upcoming Events",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    private List<WebElement> getEventCards() {
        return waitForElements(eventCards);
    }

    @Step("Проверка количества карточек в Ближайших событиях")
    public void assertUpcomingEventsCounter() {
        int eventCardCount = getEventCards().size();
        String eventCardCountText = Integer.toString(eventCardCount);
        String upcomingEventsCounter = counterUpcomingEvents.getText();
        assertThat(eventCardCountText, equalTo(upcomingEventsCounter));
        Allure.addAttachment("Вкладка Upcoming Events, проверка счетчика",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

}