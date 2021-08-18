package pages;

import base.BasePage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
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
    @FindBy(css = "#filter_location")
    private WebElement locationDropDown;
    @FindBy(css = "#filter_language")
    private WebElement languageDropDown;
    @FindBy(css = "#filter_category")
    private WebElement categoryDropDown;
    @FindBy(xpath = "//*[@data-value='Belarus']")
    private WebElement belarusItem;
    @FindBy(xpath = "//*[@data-value='ENGLISH']")
    private WebElement englishLanguageItem;
    @FindBy(xpath = "//*[@data-value='Testing']")
    private WebElement testingCategoryItem;
    @FindBy(css = "div.evnt-toogle-filters-text:nth-child(1) > span:nth-child(1)")
    private WebElement moreFilterButton;
    @FindBy(xpath = "//span[contains(text(), 'Testing')]")
    private WebElement resultFoundTesting;


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

    @Step("Установка фильтров для поиска")
    public void setFilter() {

        waitVisibilityOfElement(moreFilterButton);
        moreFilterButton.click();
        logger.info("Дожидаемся элемента и кликаем: " + moreFilterButton);

        waitIsClickable(locationDropDown);
        locationDropDown.click();
        belarusItem.click();
        logger.info("Выбираем Беларусь в регионе: " + belarusItem);

        languageDropDown.click();
        englishLanguageItem.click();
        logger.info("Выбираем ENGLISH: " + englishLanguageItem);

        categoryDropDown.click();
        testingCategoryItem.click();
        logger.info("Выбираем категорию Testing " + testingCategoryItem);

        Allure.addAttachment("Установка фильтров для поиска докладов",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Валидация найденных видео после фильтрации")
    public void validateFoundVideoItems() {

        waitVisibilityOfElement(resultFoundTesting);
        logger.info("Дожидаемся карточку со словом Testing: " + resultFoundTesting);

        for (WebElement element : listOfCards) {

            //@TODO тут требуется ACTION, чтоб по наведению на портрет спикера проверять региональность
            // либо в отдельной вкладке и оттуда забирать Белорусь

            Assertions.assertTrue(element.findElement(By.xpath("//p[contains(@class, 'language')]"))
                    .getText()
                    .contains("En"), "Язык не соответствует выбранному");
            //logger.info("Доклад №" + listOfCards.indexOf(element) + ": " + listOfCards.get(listOfCards.indexOf(element)).getText());
        }
        logger.info("Отфильтрованные доклады на Английском языке");
    }


}
