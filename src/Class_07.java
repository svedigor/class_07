import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Alert;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static java.lang.System.out;


//to keep order in testing --- important
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class Class_07 {
    private static WebDriver driverChrome, driverFirefox;

    private static final String expectedTitleAmazon = "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";
    private static final String myName = "Mayer";
    private static final String expectedIfConfirmed = "Confirmed";

    @BeforeClass
    public static void setDriver() {
        // block notifications (pop up) on facebook page for assignment H
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        System.setProperty("webdriver.chrome.driver", "/Users/igor_shved/Downloads/chromedriver");
        driverChrome = new ChromeDriver(options);
        driverChrome.get("https://www.google.com");

        System.setProperty("webdriver.gecko.driver", "/Users/igor_shved/Documents/Java/libraries/webDrivers/geckodriver");
        driverFirefox = new FirefoxDriver();
        driverFirefox.get("https://www.firefox.com");
    }

    @AfterClass
    public static void quitDriver() {
        out.println("@AfterClass");
        driverChrome.quit();
        driverFirefox.quit();
    }

    @Before
    public void beforeEachTest(){
    }

    @After
    public void afterEachTest() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test
    public void test_A() {
        driverChrome.navigate().to("https://translate.google.com/");
        // find element by different locators
        WebElement textareaById = driverChrome.findElement(By.id("source"));
        WebElement textareaByTag = driverChrome.findElement(By.tagName("textarea"));
        WebElement textareaByClassName = driverChrome.findElement(By.cssSelector("textarea[class='orig tlid-source-text-input goog-textarea']"));
        WebElement textareaByXpath = driverChrome.findElement(By.xpath("//textarea"));

        //printing element
        out.println(textareaById);
        out.println(textareaByTag);
        out.println(textareaByClassName);
        out.println(textareaByXpath);
    }

    @Test
    public void test_B() {
        driverFirefox.navigate().to("https://www.youtube.com");
        WebElement youTubeButton = driverFirefox.findElement(By.id("logo"));
        out.println(youTubeButton);
    }

    @Test
    public void test_C() {
        driverFirefox.navigate().to("https://www.seleniumhq.org");
        WebElement seatchInput = driverFirefox.findElement(By.name("search"));
        out.println(seatchInput);
    }

    @Test
    public void test_D() throws InterruptedException {
        //navigate to amazon website
        driverChrome.navigate().to("https://www.amazon.com");
        //assert Amazon website title
        String actualTitle = driverChrome.getTitle();
        assertEquals(expectedTitleAmazon, actualTitle);
        // type in search text field "Leather shoes"
        WebElement searchTextField = driverChrome.findElement(By.id("twotabsearchtextbox"));
        searchTextField.sendKeys("Leather shoes");
        Thread.sleep(2000);
        //submit
        WebElement submitInput = driverChrome.findElement(By.xpath("//input[@type ='submit'and @tabindex='20']"));
        submitInput.click();
    }

    @Test
    public void test_E() {
        driverChrome.navigate().to("https://translate.google.com/");
        driverChrome.findElement(By.id("source")).sendKeys("שלום");
    }

    @Test
    public void test_F() {
        driverFirefox.navigate().to("https://www.youtube.com");
        driverFirefox.findElement(By.name("search_query")).sendKeys("TONES AND I - DANCE MONKEY (OFFICIAL VIDEO)");
        driverFirefox.findElement(By.id("search-icon-legacy")).click(); // or submit()
    }

    @Test
    public void test_G(){
        driverFirefox.navigate().to("https://dgotlieb.github.io/Controllers/");
        //Select “Cheese option” in radio buttons.
        driverFirefox.findElement(By.cssSelector("input[value='Cheese']")).click();
        // Select "Milk" option from the drop down
        Select dairyFooid = new Select(driverFirefox.findElement(By.name("dropdownmenu")));
        dairyFooid.selectByVisibleText("Milk");
        // Print all elements in drop down.
        List<WebElement> optionsList = dairyFooid.getOptions();
        for (WebElement option : optionsList) {
            out.println(option);
        }
        // quit Firefox
        // will not use in the following tests
        driverFirefox.quit();
    }

    @Test
    public void test_H() {
        driverChrome.navigate().to("https://www.facebook.com/");
        // login to facebook
        driverChrome.findElement(By.id("email")).sendKeys("sved.igor@gmail.com");
        driverChrome.findElement(By.id("pass")).sendKeys("password");
        driverChrome.findElement(By.id("u_0_b")).submit();
    }

    @Test
    public void test_I_handleAlert(){
        driverChrome.navigate().to("https://dgotlieb.github.io/Navigation/Navigation.html");
        driverChrome.findElement(By.id("MyAlert")).click();
        // handling alert
        Alert alert = driverChrome.switchTo().alert();
        out.println(alert.getText());
        alert.accept();
    }

    @Test
    public void test_I_handlePrompt() {
        // uncomment if dont keep order of testing or run only this test
        //driverChrome.navigate().to("https://dgotlieb.github.io/Navigation/Navigation.html");
        driverChrome.findElement(By.id("MyPrompt")).click();
        Alert promptAlert = driverChrome.switchTo().alert();
        // sending input
        promptAlert.sendKeys("Mayer");
        promptAlert.accept();
        //asserting input
        WebElement inputText = driverChrome.findElement(By.id("output"));
        assertEquals(myName, inputText.getText());
    }

    @Test
    public void test_I_handleConfirmPopUp() {
        // uncomment if dont keep order of testing or run only this test
        // driverChrome.navigate().to("https://dgotlieb.github.io/Navigation/Navigation.html");
        driverChrome.findElement(By.id("MyConfirm")).click();
        Alert alertConfirmBox = driverChrome.switchTo().alert();
        alertConfirmBox.accept();
        // assert confirm box output
        String expectedIfConfirmed = "Confirmed";
        WebElement outputText = driverChrome.findElement(By.id("output"));
        assertEquals(expectedIfConfirmed, outputText.getText());
    }

    @Test
    public void test_I_iFrame() {
        // switch to iframe
        // uncomment if dont keep order of testing or run only this test
        //driverChrome.navigate().to("https://dgotlieb.github.io/Navigation/Navigation.html");
        driverChrome.switchTo().frame("my-frame").findElement(By.id("iframe_container"));
        WebElement iFrameBody = driverChrome.findElement(By.xpath("//body"));
        System.out.println(iFrameBody.getText());
    }

    @Test
    public void test_I_newTab_newWindow(){
        // uncomment if dont keep order of testing or run only this test
        //driverChrome.navigate().to("https://dgotlieb.github.io/Navigation/Navigation.html");
        //Open New Tab and go back to main
        driverChrome.switchTo().defaultContent();
        String mainTab = driverChrome.getWindowHandle();
        driverChrome.findElement(By.id("openNewTab")).click();
        driverChrome.switchTo().window(mainTab);

        //Open New Window and return to main
        driverChrome.findElement(By.xpath("//a[@href='newWindow.html']")).click();;
        driverChrome.switchTo().window(mainTab);
    }


}
