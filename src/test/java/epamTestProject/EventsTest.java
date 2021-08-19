package epamTestProject;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;
import pages.EventsPage;
import pages.MainPage;

@Execution(ExecutionMode.CONCURRENT)
public class EventsTest extends BaseTest {

    @Rule
    public MethodRule watchman = new TestWatchman() {
        public void starting(FrameworkMethod method) {
            System.out.println("Starting test: " + method.getName());
        }
    };

    @Test
    @Feature("Просмотр предстоящих мероприятий")
    @DisplayName("Просмотр предстоящих мероприятий")
    @Description("Тест проверяет, что на вкладке Events отображаются карточки предстоящих мероприятий. Количество карточек равно счетчику на кнопке Upcoming Events")
    public void testEventsCounter() {
        homePage.openEvents();
        eventsPage.openUpcomingEventsTab();
        eventsPage.checkUpcomingCountersMatch();
    }

    //@TODO нужно добавить прокрутку вниз, иначе будет ошибка вида
    // org.opentest4j.AssertionFailedError: expected: <756> but was: <12>

    @Test
    @Feature("Просмотр прошедших событий")
    @DisplayName("Просмотр прошедших событий")
    @Description("Тест проверяет, корректность данных в карточке на вкладке прошедших событий")
    public void testPastEventsCardData() {
        homePage.openEvents();
        eventsPage.openPastEventsTab();
        eventsPage.checkPastCountersMatch();
    }
}
