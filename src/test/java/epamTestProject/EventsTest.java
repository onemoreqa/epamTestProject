package epamTestProject;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.EventsPage;
import pages.MainPage;

@Execution(ExecutionMode.CONCURRENT)
public class EventsTest extends BaseTest {

    private MainPage homepage;
    private EventsPage eventsPage;

    @Test
    @Feature("Просмотр предстоящих мероприятий")
    @Description("Тест проверяет, что на вкладке Events отображаются карточки предстоящих мероприятий. Количество карточек равно счетчику на кнопке Upcoming Events")
    public void testEventsCounter() {
        homepage.openEvents();
        eventsPage.openUpcomingEventsTab();
        eventsPage.checkUpcomingCountersMatch();
    }

    @Test
    @Feature("Просмотр прошедших событий")
    @Description("Тест проверяет, корректность данных в карточке на вкладке прошедших событий")
    public void testPastEventsCardData() {
        homepage = new MainPage(driver);
        homepage.open(baseUrl);
        homepage.openEvents();
        eventsPage = new EventsPage(driver);
        eventsPage.openPastEventsTab();
        //eventsPage.assertUpcomingEventCardData();
    }
}
