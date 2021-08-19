package base;

import com.google.common.io.Files;
import driver.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.EventsPage;
import pages.MainPage;
import pages.VideoPage;
import utils.PropsConfiguration;
import base.BasePage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.sun.activation.registries.LogSupport.log;

/**
 * Base class for all the JUnit-based test classes
 */
public class BaseTest {

    public Logger logger = LogManager.getLogger(BaseTest.class);
    protected static String baseUrl;
    protected static String execution;
    public BasePage basePage;
    public MainPage homePage;
    public EventsPage eventsPage;
    public VideoPage videoPage;
    protected WebDriver driver;

    @BeforeAll
    public static void loadParams() throws Throwable {
        PropsConfiguration config = new PropsConfiguration();
        baseUrl = config.getProperty("epam.url");
        execution = System.getProperty("execute.property", "REMOTE");
    }

    @SneakyThrows
    @BeforeEach
    public void initDriver(TestInfo testInfo)  {

        //Добавим ссылку на видео: http://0.0.0.0:8080/video/testPastCanadaEvents.mp4
        String testMethod = testInfo.getTestMethod().get().getName();
        Allure.addAttachment("ВИДЕО", "text/plain", "http://0.0.0.0:8080/video/".concat(testMethod).concat(".mp4"));
        //можно было сделать как СБЕР -> https://habr.com/ru/company/sberbank/blog/359302/

        DriverFactory driverFactory = new DriverFactory();

        if (execution.equals("LOCAL")) {
            logger.info("Запуск тестов локально");
            WebDriverManager.chromedriver().setup();
            driver = driverFactory.createDriver();
        } else {
            logger.info("Запуск тестов в контейнере Selenoid"); //
            driver = driverFactory.createRemoteDriver(testInfo);
            driver.manage().window().setSize(new Dimension(1280, 1024));
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        homePage = PageFactory.initElements(driver, MainPage.class);
        eventsPage = PageFactory.initElements(driver, EventsPage.class);
        videoPage = PageFactory.initElements(driver, VideoPage.class);

        homePage.open(baseUrl);
    }

    @AfterEach
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
