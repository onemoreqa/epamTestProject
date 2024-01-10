import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.ext.Locator2;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Hometask2 {

    Logger logger = LogManager.getLogger(Hometask2.class);
    protected static WebDriver driver;

    @Before
    public void startUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        String mode = "headless";
        options.addArguments(mode);
        driver = new ChromeDriver(options);
        logger.info("Драйвер поднят в режиме " + mode);
    }
    @After
    public void end(){
        if (driver!=null)
            driver.quit();
        logger.info("Драйвер остановлен");
    }

    @Test
    public void webDriverTest1() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String otusSite = "https://otus.ru";
        driver.get(otusSite);
        logger.info("Переход на сайт " + otusSite);

        String contacts = "//a[contains(@href,'contacts')]";
        driver.findElement(By.xpath(contacts)).click();
        String adresValue = "125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02";
        String adresLocator = "//div[text()='Адрес']/following::div[1]";
        Assert.assertEquals("Проверка адреса", adresValue, driver.findElement(By.xpath(adresLocator)).getText());
        logger.info("Успешная проверка Адреса ООО «Отус онлайн-образование»");

        driver.manage().window().maximize();
        logger.info("Разворот на полный экран (не киоск)");

        String title = driver.getTitle();
        Assert.assertEquals("Проверка title страницы контактов", "Контакты | OTUS", title);
        logger.info("Успешная проверка title страницы контактов");
    }

    @Test
    public void webDriverTest2() throws InterruptedException {
        String tele2Site = "https://msk.tele2.ru/shop/number ";
        String inputLocator = "//*[@id=\"searchNumber\"]";
        String loaderLocator= "//*[@class=\"preloader-icon\"]";
        String numbersLocator = "//span[@class=\"phone-number\"]";
        String searchQuery = "97";
        driver.get(tele2Site);
        logger.info("Переход на сайт " + tele2Site);

        String firstNumber = getElement1AfterElement2IsGone(By.xpath(numbersLocator), By.xpath(loaderLocator)).getText();
        logger.info("Пропал лоадер, значение первого номера: " + firstNumber );

        getElement(By.xpath(inputLocator)).sendKeys(searchQuery + Keys.ENTER);
        logger.info("Начинаем поиск номера по тестовому выражению: " + searchQuery);
        getElement(By.xpath(loaderLocator));
        logger.info("Ждем появление пре-лоадера");

        String firstSearchNumber = getElement1AfterElement2IsGone(By.xpath(numbersLocator), By.xpath(loaderLocator)).getText();
        logger.info("Пропал пре-лоадер, значение первого предложенного номера: " + firstSearchNumber );
        Assert.assertNotEquals("Проверяем, что значения номеров до и после поиска отличаются", firstNumber, firstSearchNumber);
    }

    @Test
    public void webDriverTest3() throws InterruptedException {
        String faqLocator = "//*[@title='FAQ']";
        String courseLocator = "//*[text()=\"Где посмотреть программу интересующего курса?\"]";
        String answerLocator = courseLocator + "/../div[2]";
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String otusSite = "https://otus.ru";
        driver.get(otusSite);
        logger.info("Переход на сайт " + otusSite);

        driver.findElement(By.xpath(faqLocator)).click();
        logger.info("Перешли в FAQ");

        driver.findElement(By.xpath(courseLocator)).click();
        logger.info("Раскрыть выпадаху курса");
        String answer = driver.findElement(By.xpath(answerLocator)).getText();
        String expectedAnswer = "Программу курса в сжатом виде можно увидеть на странице курса после блока с преподавателями. Подробную программу курса можно скачать кликнув на “Скачать подробную программу курса”";
        Assert.assertEquals("Проверка соответствия ответа в FAQ", expectedAnswer, answer);
        logger.info("Успешная проверка в FAQ");
    }

    @Test
    public void webDriverTest4() throws InterruptedException {
        String footerButtonLocator = "//footer//button[@type=\"submit\"]";
        String emailInputLocator = "//footer//input[@type=\"email\"]";
        String expectedMessage = "Вы успешно подписались";
        String successSubscribeLocator = "//footer//*[text()=\"" + expectedMessage + "\"]";
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String otusSite = "https://otus.ru";
        driver.get(otusSite);
        logger.info("Переход на сайт " + otusSite);

        driver.findElement(By.xpath(emailInputLocator)).sendKeys("asd@asd.com");
        logger.info("Заполнен инпут почты тестовым значением");
        driver.findElement(By.xpath(footerButtonLocator)).click();
        logger.info("Подписка по нажатию на кнопку");

        String actual = driver.findElement(By.xpath(successSubscribeLocator)).getText();
        Assert.assertEquals("Проверка соответствия сообщения при подписке", expectedMessage, actual);
        logger.info("Успешная проверка сообщения при подписке");
    }

    private WebElement getElement(By locator){
        return new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(locator));
    }

    private WebElement getElement1AfterElement2IsGone(By locator1, By locator2){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator2));
        return new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(locator1));
    }

}