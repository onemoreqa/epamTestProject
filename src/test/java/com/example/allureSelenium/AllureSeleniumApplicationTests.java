package com.example.allureSelenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@Epic("OTUS")
@Owner(value = "Egor Gorbunov")
class AllureSeleniumApplicationTests {

	private static final String EXPECTED_OTUS_CONTACTS_TEXT = "125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02";
	private static final String OTUS_PAGE = "https://otus.ru";

	@FindBy(xpath = "//header//a[@href='/contacts/']")
	private WebElement contactsButton;

	private Logger logger = LogManager.getLogger(AllureSeleniumApplicationTests.class);
	public WebDriver driver;


	@Test
	@Step("Check Title")
	@Story("Check Title")
	@Severity(value = SeverityLevel.BLOCKER)
	@Description("Проверка title главной страницы")
	@DisplayName("Проверка title")
	public void checkTitle() {
		String actualTitle = driver.getTitle();
		String expectedTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
		Allure.addAttachment("Главная страница", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		assertEquals("Title не соответствует ожидаемому", expectedTitle, actualTitle);
	}

	@Test
	@Step("Check Title Contacts")
	@Story("Check Contacts")
	@Severity(value = SeverityLevel.TRIVIAL)
	@Description("Проверка title страницы контакты")
	@DisplayName("Проверка контактов")
	public void checkTitleContacts() {
		PageFactory.initElements(driver, this);
		contactsButton.click();
		logger.info("Проверяем title страницы Контакты");
		String actualTitle = driver.getTitle();
		String expectedTitle = "Контакты | OTUS";
		Allure.addAttachment("Cтраница Контакты. Title", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		assertEquals("Title страницы Контакты не соответствует ожидаемому", expectedTitle, actualTitle);
	}


	@Test
	@Step("Check Address In Contacts")
	@Severity(value = SeverityLevel.CRITICAL)
	@Description("Проверка юр.адреса")
	@Story("Check Contacts")
	@DisplayName("Проверка адреса в контактах")
	public void checkAdressInContacts() {
		driver.get("https://otus.ru/contacts");
		Allure.addAttachment("Cтраница Контакты", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		assertEquals( "Адрес не соответствует ожидаемому",EXPECTED_OTUS_CONTACTS_TEXT, driver.findElement(By.xpath("//div[text()='Адрес']/../div[2]")).getText());
	}

	@Test
	@Step("Открыть страницу 'Terms of use'")
	@Severity(value = SeverityLevel.NORMAL)
	@Description("Сравнивается url страницы с ожидаемым https://otus.ru/legal/terms/")
	@Story("Check Terms of use")
	@DisplayName("Переход на страницу Terms of use")
	public void checkTermsOfUsePage() {
		navigateToTermsOfUsePage();
		Allure.addAttachment("Cтраница правовой информации", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		Assert.assertEquals("https://otus.ru/legal/terms/", driver.getCurrentUrl());
	}

	@BeforeEach
	@Story("web.driver")
	@DisplayName("Открыть браузер и перейти на главную страницу")
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		logger.info("Драйвер поднят");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(OTUS_PAGE);
		logger.info("Сайт {} открыт", OTUS_PAGE);
	}

	@AfterEach
	@Story("web.driver")
	@DisplayName("Закрыть браузер")
	public void tearDown() {
		if (driver!=null)
			driver.quit();
		logger.info("Драйвер остановлен \n\n");
	}

	public void navigateToTermsOfUsePage(){
		String script = "document.querySelector(\"[class='footer2__container container'] [href='/legal/terms/']\").click();";
		((JavascriptExecutor) driver).executeScript(script);
	}

}
