package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @Step
    public void typeAndSearch() {

        waitVisibilityOfElement(searchField);
        searchField.sendKeys(query);

        waitVisibilityOfElement(resultFoundQA);
        logger.info("Дожидаемся элемента: " + resultFoundQA);

        Assertions.assertNotSame(listOfCards.size(), 0, "Доклады не найдены");
        logger.info("Найдено докладов: " + listOfCards.size());
    }
}
