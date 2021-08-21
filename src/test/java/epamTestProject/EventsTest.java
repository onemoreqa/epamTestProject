package epamTestProject;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class EventsTest extends BaseTest {

    @Test
    @Feature("Просмотр предстоящих мероприятий")
    @DisplayName("Просмотр предстоящих мероприятий")
    @Description("Тест проверяет, что на вкладке Events отображаются карточки предстоящих мероприятий. Количество карточек равно счетчику на кнопке Upcoming Events")
    @Link(name = "Video", url = "http://0.0.0.0:8080/video/testEventsCounter.mp4")
    public void testEventsCounter() {

        homePage.openEvents();
        eventsPage.openUpcomingEventsTab();
        eventsPage.checkUpcomingCountersMatch();
    }

    @Test
    @Feature("Просмотр прошедших событий")
    @DisplayName("Просмотр прошедших событий")
    @Description("Тест проверяет, корректность данных в карточке на вкладке прошедших событий")
    @Link(name = "Video", url = "http://0.0.0.0:8080/video/testPastEventsCardData.mp4")
    public void testPastEventsCardData() {

        homePage.openEvents();
        eventsPage.openPastEventsTab();
        eventsPage.checkPastCountersMatch();
    }


    // @TODO не оч понятно, как тест должен отработать, т.к. предстоящие события обычно не отображаются
    @Test
    @Feature("Валидация дат предстоящих мероприятий")
    @DisplayName("Валидация дат предстоящих мероприятий")
    @Description("Тест проверяет, что даты проведения мероприятий больше или равны текущей дате")
    @Link(name = "Video", url = "http://0.0.0.0:8080/video/testUpcomingEventsDate.mp4")
    public void testUpcomingEventsDate() {

        homePage.openEvents();
        eventsPage.openUpcomingEventsTab();
        eventsPage.checkUpcomingEventDate();

    }

    @Test
    @Feature("Просмотр прошедших мероприятий в Канаде")
    @DisplayName("Просмотр прошедших мероприятий в Канаде")
    @Description("Тест проверяет, корректность данных для карточек прошедших мероприятий в Канаде")
    @Link(name = "Video", url = "http://0.0.0.0:8080/video/testPastCanadaEvents.mp4")
    public void testPastCanadaEvents() {

        homePage.openEvents();
        eventsPage.openPastEventsTab();
        eventsPage.setFilter();
        Assertions.assertEquals(
                eventsPage.getNumberOfEventsOnLink(), eventsPage.getNumberOfPastEventsOnPanels());

    }
}
