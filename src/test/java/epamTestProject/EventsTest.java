package epamTestProject;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.EventsBasePage;
import pages.HomeBasePage;

@Execution(ExecutionMode.CONCURRENT)
public class EventsTest extends BaseTest {

    private HomeBasePage homepage;
    private EventsBasePage eventsPage;

    @Test
    @Feature("Просмотр предстоящих мероприятий")
    @Description("Тест проверяет, что на вкладке Events отображаются карточки предстоящих мероприятий. Количество карточек равно счетчику на кнопке Upcoming Events")
    public void testEventsCounter() {
        homepage = new HomeBasePage(driver);
        homepage.open(baseUrl);
        homepage.openEvents();
        eventsPage = new EventsBasePage(driver);
        eventsPage.openUpcomingEventsTab();
        eventsPage.assertUpcomingEventsCounter();
    }

    @Test
    @Feature("Просмотр прошедших событий")
    @Description("Тест проверяет, корректность данных в карточке на вкладке прошедших событий")
    public void testPastEventsCardData() {
        homepage = new HomeBasePage(driver);
        homepage.open(baseUrl);
        homepage.openEvents();
        eventsPage = new EventsBasePage(driver);
        eventsPage.openPastEventsTab();
        //eventsPage.assertUpcomingEventCardData();
    }
}
