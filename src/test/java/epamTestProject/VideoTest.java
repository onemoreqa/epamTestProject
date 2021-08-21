package epamTestProject;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static io.qameta.allure.SeverityLevel.*;

@Execution(ExecutionMode.CONCURRENT)
public class VideoTest extends BaseTest {


    @Test
    @Feature("Фильтрация докладов по категориям")
    @DisplayName("Фильтрация докладов по категориям")
    @Description("Тест проверяет фильтрацию докладов")
    @Severity(BLOCKER)
    @Link(name = "Video", url = "http://0.0.0.0:8080/video/testFilterVideo.mp4")
    public void testFilterVideo() throws InterruptedException {
        homePage.openVideo();
        videoPage.setFilter();
        videoPage.validateFoundVideoItems();
    }

    @Test
    @Feature("Фильтрация докладов по категориям и уровню слушателя")
    @DisplayName("Фильтрация докладов по категориям и уровню слушателя")
    @Description("Тест демонстрирует ошибку фильтрации докладов, с учетом уровня слушателя")
    @Severity(MINOR)
    @Link(name = "Video", url = "http://0.0.0.0:8080/video/testFilterVideoExtra.mp4")
    public void testFilterVideoExtra() {
        homePage.openVideo();
        videoPage.setFilter();
        videoPage.setFilterExtra();
        videoPage.validateFoundVideoExtra();
    }

    @Test
    @Feature("Поиск докладов по ключевому слову")
    @DisplayName("Поиск докладов по ключевому слову")
    @Description("Тест проверяет поиск докладов по ключевому слову QA")
    @Severity(CRITICAL)
    @Link(name = "Video", url = "http://0.0.0.0:8080/video/testSearchVideo.mp4")
    public void testSearchVideo() {
        homePage.openVideo();
        videoPage.typeAndSearch();
        videoPage.validateVideoTitle();
    }
}
