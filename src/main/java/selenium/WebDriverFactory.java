package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by crypt on 5/21/2016.
 */
public class WebDriverFactory {
    private WebDriverFactory() {
    }

    public static WebDriver create() {
        WebDriver driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(750, TimeUnit.MILLISECONDS);
        return driver;
    }
}
