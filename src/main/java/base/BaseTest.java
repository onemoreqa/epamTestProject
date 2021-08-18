package base;

import driver.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.EventsPage;
import pages.MainPage;
import pages.VideoPage;
import utils.PropsConfiguration;
import base.BasePage;

import java.util.concurrent.TimeUnit;

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
    public void initDriver()  {
        DriverFactory driverFactory = new DriverFactory();

        if (execution.equals("LOCAL")) {
            logger.info("Запуск тестов локально"); //@TODO надо override
            WebDriverManager.chromedriver().setup();
            driver = driverFactory.createDriver();
        } else {
            logger.info("Запуск тестов удаленно"); //@TODO надо override
            driver = driverFactory.createRemoteDriver();
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
