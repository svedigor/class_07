package challenges;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;

public class Test_O extends ParentForTests{

    @BeforeClass
    public static void setDriveChrome(){
    setChromeDriver();
    }

    @Test
    public void test_O() throws InterruptedException {
        ArrayList<String> myTabs;
        JavascriptExecutor js = (JavascriptExecutor)driverChrome;
        //open google - first tab
        driverChrome.get("https://www.google.com/");
        //open youtube - second tab
        js.executeScript("window.open('https://www.youtube.com/','_blank');");
        Thread.sleep(2000);
        // open google translate - third tab
        js.executeScript("window.open('https://translate.google.com/','_blank');");
        myTabs = new ArrayList<String> (driverChrome.getWindowHandles());
        Thread.sleep(2000);
        driverChrome.switchTo().window(myTabs.get(0));
        Thread.sleep(2000);
        driverChrome.switchTo().window(myTabs.get(2));
        Thread.sleep(2000);
        //clossing all tabs
        closeTabsOneByOneStartingFromLastOppened(myTabs, driverChrome);
    }
}
