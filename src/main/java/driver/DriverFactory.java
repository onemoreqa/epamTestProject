
package driver;

import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

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

    public RemoteWebDriver createRemoteDriver(TestInfo testInfo) throws MalformedURLException {

        String selenoidVideoName = testInfo.getTestMethod().get().getName();

        ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--allowed-ips='0.0.0.0, 127.0.0.1, 127.0.1.1'");

        DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName(System.getProperty("browser_name", "chrome"));
            caps.setVersion(System.getProperty("browser_version", "86.0"));
            caps.setCapability("enableVNC", true);
            caps.setCapability("screenResolution", "1280x1024");
            caps.setCapability("enableVideo", true);
            caps.setCapability("videoScreenSize", "1280x1024");
            caps.setCapability("videoFrameRate", 12);
            caps.setCapability("enableLog", true);
            caps.setCapability("verbose", true);
            caps.setCapability("log-level","DEBUG");
            caps.setCapability("logName", selenoidVideoName.concat(".log"));
            caps.setCapability("videoName", selenoidVideoName.concat(".mp4"));
            caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            //caps.setCapability("headless", true);

        remoteDriver.set(new RemoteWebDriver(
                new URL("http://selenoid:4444/wd/hub"), caps));

        return remoteDriver.get();
    }

}