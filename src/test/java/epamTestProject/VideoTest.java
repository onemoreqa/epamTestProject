package epamTestProject;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class VideoTest extends BaseTest {

    @Test
    @Feature("Поиск докладов по ключевому слову")
    @Description("Тест проверяет поиск докладов по ключевому слову QA")
    public void videoSearchTest() {
        homePage.openVideo();
        videoPage.typeAndSearch();

    }
}
