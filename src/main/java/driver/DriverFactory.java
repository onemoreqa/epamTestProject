
package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;

public class DriverFactory {

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private final ThreadLocal<RemoteWebDriver> remoteDriver = new ThreadLocal<RemoteWebDriver>();

    public WebDriver createDriver() {

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("--kiosk");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        driver.set(new ChromeDriver(options));

        return driver.get();
    }

    public RemoteWebDriver createRemoteDriver() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName(System.getProperty("browser_name", "chrome"));
            caps.setVersion(System.getProperty("browser_version", "86.0"));
            caps.setCapability("enableVNC", true);
            caps.setCapability("screenResolution", "1280x1024");
            caps.setCapability("enableVideo", true);
            caps.setCapability("enableLog", true);
            caps.setCapability("verbose", true);
            caps.setCapability("log-level","DEBUG");
            caps.setCapability("logName", "selenoid.log");
            //caps.setCapability("headless", true);

        remoteDriver.set(new RemoteWebDriver(
                new URL("http://selenoid:4444/wd/hub"), caps));

        return remoteDriver.get();
    }

}