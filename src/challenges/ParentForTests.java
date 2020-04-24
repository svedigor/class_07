package challenges;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

public class ParentForTests {
    protected static WebDriver driverFirefox, driverChrome;

    protected static void setFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", "/Users/igor_shved/Documents/Java/libraries/webDrivers/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        // Tesא_L  - disable extensions for Firefox
        options.addArguments("--disable-extensions");
        driverFirefox = new FirefoxDriver(options);
        driverFirefox.manage().window().maximize();
        driverFirefox.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    protected static void setChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/igor_shved/Documents/Java/libraries/webDrivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        // Tets_L  - disable extensions for Chrome
        options.addArguments("--disable-extensions");
        driverChrome = new ChromeDriver(options);
        driverChrome.manage().window().maximize();
        driverChrome.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public static void sendKeyToUnvisible(WebDriver driver, WebElement element, int timeout, String key) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(key, Keys.ENTER);
    }

    public static void closeTabsOneByOneStartingFromLastOppened(ArrayList<String> tabs, WebDriver driver) throws InterruptedException {
        for (int i = tabs.size()-1; i >=0; i--){
            driver.switchTo().window(tabs.get(i));
            Thread.sleep(2000);
            driver.close();
            Thread.sleep(2000);
        }
    }
}

