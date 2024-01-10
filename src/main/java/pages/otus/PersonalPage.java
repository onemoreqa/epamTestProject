package pages.otus;

import config.WebsiteConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.AbstractPage;

import static webdriver.WebDriverExplicitWaitElements.*;

public class PersonalPage extends AbstractPage {
    private final WebsiteConfig cfg = ConfigFactory.create(WebsiteConfig.class);

    private static final By PAGE_TITLE = By.cssSelector("h1.title__text");
    private static final By SAVA_LATER_BUTTON = By.cssSelector("button[title='Сохранить и заполнить позже']");
    private static final By ADD_CONTACT_BUTTON = By.xpath("//button[contains(@class, 's-lk-cv-custom-select-add')]");
    public static final String CONTACT_TYPE_PATTERN = "//input[@name='contact-%s-service']/following-sibling::div";
    public static final String CONTACT_FIELD_PATTERN = "//input[@name='contact-%s-value']";

    public PersonalPage(WebDriver driver) {
        super(driver);
    }

    public PersonalPage open(){
        driver.get(cfg.otusPersonalPageUrl());
        return this;
    }

    public void fillField(By locator, String value){
        getClickableElement(locator).clear();
        getClickableElement(locator).sendKeys(value);
    }

    public String getPageTitle() {
        return getVisibilityElement(PAGE_TITLE).getText();
    }

    public String getElementText(By locator) {
        return getVisibilityElement(locator).getText();
    }

    public String getWebElementAttribute(By locator, String attributeName) {
        return getVisibilityElement(locator).getAttribute(attributeName);
    }

    public void selectDropdownValue(By locator, String value) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getClickableElement(locator)).click().perform();
        By xpath = By.xpath("//div[not(contains(@class, " +
                "'js-custom-select-options-container hide'))]/div/button[@title='" + value + "']");
        actions.moveToElement(getPresenceElement(xpath)).perform();
        getClickableElement(xpath).click();
    }

    public void addContactField() {
        getClickableElement(ADD_CONTACT_BUTTON).click();
    }

    public void saveAndFillLater() {
        getClickableElement(SAVA_LATER_BUTTON).click();
    }
}