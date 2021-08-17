package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BasePage {

  protected WebDriver driver;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  protected WebElement waitForElement(WebElement element) {
    return new WebDriverWait(driver, 10)
            .until(ExpectedConditions.visibilityOf(element));
  }

  protected Boolean waitForHide(WebElement element) {
    Boolean until = new WebDriverWait(driver, 10)
            .until(ExpectedConditions.invisibilityOf(element));
            return until;
  }

  protected List<WebElement> waitForElements(List<WebElement> elements) {
    return new WebDriverWait(driver, 10)
            .until(ExpectedConditions.visibilityOfAllElements(elements));
  }

}
