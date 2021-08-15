package epamTestProject.cases;

import epamTestProject.pages.EventsPage;
import epamTestProject.pages.HomePage;
import epamTestProject.utils.JUnitTestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class EventsTest extends JUnitTestBase {

    private HomePage homepage;

    @Test
    @Epic("Epam")
    @Feature("Просмотр предстоящих мероприятий")
    @Story("Счетчик предстоящих мероприятий")
    @Description("Тест проверяет, что на вкладке Events отображаются карточки предстоящих мероприятий. Количество карточек равно счетчику на кнопке Upcoming Events")
    public void testEventsCounter() {
        homepage = new HomePage(driver);
        homepage.open(baseUrl);
        homepage.openEvents();
        EventsPage eventsPage = new EventsPage(driver);
        eventsPage.openUpcomingEventsTab();
        eventsPage.assertUpcomingEventsCounter();
    }
}
