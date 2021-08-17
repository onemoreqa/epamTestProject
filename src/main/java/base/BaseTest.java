package base;

import driver.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import utils.PropsConfiguration;

import java.util.concurrent.TimeUnit;

/**
 * Base class for all the JUnit-based test classes
 */
public class BaseTest {

    public static Logger logger = LogManager.getLogger(BaseTest.class);
    protected static String baseUrl;
    protected static String execution;
    protected WebDriver driver;


    @BeforeAll
    public static void loadParams() throws Throwable {
        PropsConfiguration config = new PropsConfiguration();
        baseUrl = config.getProperty("epam.url");
        execution = System.getProperty("execute.property", "REMOTE");
        logger.info("Начало прогона тестов в режиме {} ", execution);
    }

    @SneakyThrows
    @BeforeEach
    public void initDriver()  {
        DriverFactory driverFactory = new DriverFactory();

        if (execution.equals("LOCAL")) {
            logger.info("Запуск тестов локально");
            WebDriverManager.chromedriver().setup();
            driver = driverFactory.createDriver();
        } else {
            logger.info("Запуск тестов удаленно");
            driver = driverFactory.createRemoteDriver();
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterEach
    public void quitDriver() {
        if (driver != null) {
            logger.info("Завершение работы браузера \n");
            driver.quit();
        }
    }

    @AfterAll
    public static void endOfTests() {
        logger.info("Завершение прогона тестов в режиме {} \n\n\n\n", execution);
    }
}
