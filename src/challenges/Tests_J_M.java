package challenges;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import static java.lang.System.out;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests_J_M extends ParentForTests {

    @BeforeClass
    public static void googleAcountWebPage() {
        setFirefoxDriver();
        driverFirefox.get("https://accounts.google.com/signin");
    }

    @AfterClass
    public static void afterClass(){
        driverFirefox.quit();
    }

    @After
    public void waitBeforeEachtest() throws InterruptedException {
        Thread.sleep(3000);
    }

    @Test
    public void test1_LogIN() throws InterruptedException {
        driverFirefox.findElement(By.xpath("//input[@id='identifierId']")).sendKeys("sved.igor@gmail.com" + Keys.ENTER);
        WebElement password = driverFirefox.findElement(By.xpath("//input[@name='password']"));
        sendKeyToUnvisible(driverFirefox, password, 10, "Igor326849775");
    }

    @Test
    public void test2_switchToAppsFrameAndOpenGmail() throws InterruptedException {
        Thread.sleep(3000);
        driverFirefox.switchTo().frame(driverFirefox.findElement(By.xpath("//iframe[@role='presentation']")));
        WebElement gmailButton = driverFirefox.findElement(By.linkText("Gmail"));
        WebDriverWait wait = new WebDriverWait(driverFirefox, 20);
        wait.until(ExpectedConditions.elementToBeClickable(gmailButton));
        gmailButton.click();
    }

    @Test
    public void test3_switchToGmailTab() throws InterruptedException {
        ArrayList <String> myTabs = new ArrayList<String>(driverFirefox.getWindowHandles());
        Thread.sleep(3000);
        driverFirefox.switchTo().window(myTabs.get(1));
    }

    @Test
    public void test4_clickPrintComposeMessageElement() throws InterruptedException {
        WebElement composeMessage = driverFirefox.findElement(By.xpath("//div[@class='T-I J-J5-Ji T-I-KE L3']"));
        WebDriverWait wait = new WebDriverWait(driverFirefox, 20);
        wait.until(ExpectedConditions.elementToBeClickable(composeMessage));
        out.println(composeMessage);
        composeMessage.click();
    }

    @Test
    public void test5_composeSendMessage() throws InterruptedException {
        WebElement mailAdress = driverFirefox.findElement(By.xpath("//textarea[@class='vO']"));
        WebElement mailSubject = driverFirefox.findElement(By.name("subjectbox"));
        WebElement mailBody =  driverFirefox.findElement(By.id(":qs"));
        // sending keys to elements
        sendKeyToUnvisible(driverFirefox, mailAdress, 20, "sved.igor@gmail.com");
        Thread.sleep(1000);
        sendKeyToUnvisible(driverFirefox, mailSubject, 20, "Subject for automation composed message");
        Thread.sleep(1000);
        sendKeyToUnvisible(driverFirefox, mailBody, 20, "Automation message");
        Thread.sleep(1000);
        driverFirefox.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")).click();
    }
}
