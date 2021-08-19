package epamTestProject;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class VideoTest extends BaseTest {


    @Test
    @Feature("Фильтрация докладов по категориям")
    @DisplayName("Фильтрация докладов по категориям")
    @Description("Тест проверяет фильтрацию докладов")
    public void testFilterVideo() throws InterruptedException {
        homePage.openVideo();
        videoPage.setFilter();
        videoPage.validateFoundVideoItems();
    }

    @Test
    @Feature("Поиск докладов по ключевому слову")
    @DisplayName("Поиск докладов по ключевому слову")
    @Description("Тест проверяет поиск докладов по ключевому слову QA")
    public void testSearchVideo() {
        homePage.openVideo();
        videoPage.typeAndSearch();
        videoPage.validateVideoTitle();
    }
}
