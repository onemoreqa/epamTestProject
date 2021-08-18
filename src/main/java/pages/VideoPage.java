package pages;

import base.BasePage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.hamcrest.core.SubstringMatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.io.ByteArrayInputStream;
import java.util.List;

public class VideoPage extends BasePage {

    public VideoPage(WebDriver driver) {
        super(driver);
    }

    String query = "QA";

    @FindBy(xpath = "//input[contains(@class, 'evnt-search') and contains(@placeholder, 'Search by Talk Name')]")
    private WebElement searchField;
    @FindBy(xpath = "//span[contains(text(), 'QA')]")
    private WebElement resultFoundQA;
    @FindBy(xpath = "//div[contains(@class, 'evnt-talks-column')]")
    private List<WebElement> listOfCards;

    @Step("Поиск по ключевому слову")
    public void typeAndSearch() {

        waitVisibilityOfElement(searchField);
        searchField.sendKeys(query);

        waitVisibilityOfElement(resultFoundQA);
        logger.info("Дожидаемся элемента: " + resultFoundQA);

        Assertions.assertNotSame(listOfCards.size(), 0, "Доклады не найдены");
        logger.info("Найдено докладов: " + listOfCards.size());
        Allure.addAttachment("Найденные доклады",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Проверка наличия поискового запроса в названии докладов")
    public void validateVideoTitle() {

        for (WebElement element : listOfCards) {

            Assertions.assertTrue(element.findElement(By.xpath("//*[contains(@class, 'evnt-talk-name')]/h1/span"))
                    .getText()
                    .contains(query), "Доклады по ключевому слову " + query + " не найдены");
            logger.info("Доклад №" + listOfCards.indexOf(element) + ": " + listOfCards.get(listOfCards.indexOf(element)).getText());
        }
        Allure.addAttachment("Проверка наличия поискового запроса в названии докладов",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

    }
}
