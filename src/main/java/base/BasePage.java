package base;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BasePage {

  protected WebDriver driver;
  public WebDriverWait webDriverWait;
  public Logger logger = LogManager.getLogger(BaseTest.class);

  public BasePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void click(By elementBy) {
    driver.findElement(elementBy).click();
  }

  public void waitIsClickable(WebElement element) {
    webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
  }

  public void waitVisibilityOfElement(WebElement element) {
    webDriverWait.until(ExpectedConditions.visibilityOf(element));
  }

  public void waitInvisibilityOfElement(WebElement element) {
    webDriverWait.until(ExpectedConditions.invisibilityOf(element));
  }

  public void logElementIsNotDisplayed(WebElement element) {
    logger.info("Element is not displayed: " + element);
  }

  public void logElementIsClicked(WebElement element) {
    logger.info("Click completed on element: " + element);
  }

}
