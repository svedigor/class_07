package challenges;

import org.openqa.selenium.JavascriptExecutor;
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
        options.addArguments("--disable-extensions","--disable-notifications");
        driverFirefox = new FirefoxDriver(options);
        driverFirefox.manage().window().maximize();
        driverFirefox.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    protected static void setChromeDriver() {
        System.setProperty("webdriver.chr" +
                "ome.driver", "/Users/igor_shved/Documents/Java/libraries/webDrivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        // Tets_L  - disable extensions for Chrome
        options.addArguments("--disable-extensions");
        driverChrome = new ChromeDriver(options);
      //  driverChrome.manage().window().maximize();
        driverChrome.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    protected static void sendKeyToUnvisible(WebDriver driver, WebElement element, int timeout, String key) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(key, Keys.ENTER);
    }

    protected static void closeTabsOneByOneStartingFromLastOppened(ArrayList<String> tabs, WebDriver driver) throws InterruptedException {
        for (int i = tabs.size()-1; i >=0; i--){
            driver.switchTo().window(tabs.get(i));
            Thread.sleep(2000);
            driver.close();
            Thread.sleep(2000);
        }
    }

    protected static void documentReadyComplete(WebDriver driver,JavascriptExecutor js) throws InterruptedException {
        while (!js.executeScript("return document.readyState").toString().equals("complete")){
            out.println(js.executeScript("return document.readyState").toString());
            out.println(driver.getPageSource().length());
//            Thread.sleep(100);
        }
            out.println(js.executeScript("return document.readyState").toString());
            out.println(driver.getPageSource().length());
    }

    protected static int findOccurance(String source, String stringToFind) throws InterruptedException {
       int strLength = stringToFind.length();
       int firsIndex;
       int lastIndex;
       int count = 0;
        do {
           firsIndex = source.indexOf(stringToFind);
           lastIndex = source.lastIndexOf(stringToFind);
            if (firsIndex == -1) {
                break;
            } else if (firsIndex == lastIndex) {
                count++;
            } else if (firsIndex != lastIndex) {
                count = count + 2;
            }
            try {
                source = source.substring(firsIndex + strLength, lastIndex);
            } catch (Exception e) {
                break;
            }
        }while (firsIndex !=lastIndex);
       return count;
    }
}

