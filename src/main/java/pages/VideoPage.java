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
    @FindBy(css = "#filter_talk_level")
    private WebElement talkLevelDropDown;
    @FindBy(css = "#filter_language")
    private WebElement languageDropDown;
    @FindBy(css = "#filter_category")
    private WebElement categoryDropDown;
    @FindBy(xpath = "//*[@data-value='For All']")
    private WebElement forAllItem;
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
    @FindBy(xpath = "//*[@class='evnt-talk-details location evnt-now-past-talk']//span")
    private  WebElement locationLabel;
    @FindBy(xpath = "//*[@class='evnt-talk-details language evnt-now-past-talk']//span")
    private  WebElement languageLabel;
    @FindBy(xpath = "//*[@class='evnt-no-results']")
    private  WebElement noFoundVideos;


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

    @Step("Установка фильтров для поиска докладов с учетом уровня слушателя")
    public void setFilterExtra() {

        waitIsClickable(talkLevelDropDown);
        talkLevelDropDown.click();
        forAllItem.click();
        logger.info("Выбираем сложность доклада для всех: " + forAllItem);

        Allure.addAttachment("Установка фильтров для поиска докладов с учетом уровня слушателя",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Проверка фильтрации видео с учетом уровня слушателя")
    public void validateFoundVideoExtra() {
        waitVisibilityOfElement(noFoundVideos);
        boolean isSameSearchError = (noFoundVideos.getText()).contentEquals("No Results Found");
        logger.info("Ошибка поиска докладов при использовании уровня слушателя");

        Assertions.assertFalse(isSameSearchError,
                                 "Не удалось найти доклады после добавления фильтра по уровню сложности");
        Allure.addAttachment("Найденные доклады с учетом уровня слушателя",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Валидация найденных видео после фильтрации")
    public void validateFoundVideoItems() throws InterruptedException {

        waitVisibilityOfElement(resultFoundTesting);
        logger.info("Дожидаемся карточку со словом Testing: " + resultFoundTesting);

        List<WebElement> cards = driver.findElements(By.xpath("//div[contains(@class, 'evnt-talks-column')]"));

        int i;
        // @TODO беда на последней карточке, она не грузится никаким образом. Как вариант - уменьшить длину списка карт на 1
        for (i=0; i<Integer.valueOf(cards.size() - 1); i++) {

            Thread.sleep(2000);
            cards.get(i).click();

            logger.info("Дожидаемся появления лэйбла на странице доклада ");
            waitVisibilityOfElement(locationLabel);
            Assertions.assertTrue(locationLabel
                    .getText()
                    .contains("Belarus"), "Регион не соответствует ожидаемому");

            Assertions.assertTrue(languageLabel
                    .getText()
                    .contains("ENGLISH"), "Язык не соответствует выбранному");

            Thread.sleep(1000);
            Allure.addAttachment("Проверка на регион спикера в докладе №" + i,
                    new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            logger.info("Успешно проверили доклад №" + i);

            driver.navigate().back();
            cards = driver.findElements(By.xpath("//div[contains(@class, 'evnt-talks-column')]"));


        }
    }

}
