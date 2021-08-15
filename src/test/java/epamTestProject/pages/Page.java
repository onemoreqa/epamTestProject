package epamTestProject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page {

  protected WebDriver driver;

  public Page(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  protected WebElement waitForElement(WebElement element) {
    return new WebDriverWait(driver, 10)
            .until(ExpectedConditions.visibilityOf(element));
  }

  protected List<WebElement> waitForElements(List<WebElement> elements) {
    return new WebDriverWait(driver, 10)
            .until(ExpectedConditions.visibilityOfAllElements(elements));
  }

}
