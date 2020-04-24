package challenges;

import org.junit.*;
import org.openqa.selenium.By;

public class Test_K extends ParentForTests{

    @BeforeClass
    public  static void openGitHub(){
        setChromeDriver();
        driverChrome.get("https://github.com");
    }

    @AfterClass
    public static void afterClass() throws InterruptedException {
        Thread.sleep(2000);
        driverChrome.quit();
    }

    @Test
    public void test_K() throws InterruptedException {
        driverChrome.findElement(By.name("q")).sendKeys("Selenium");
        Thread.sleep(1000);
        driverChrome.findElement(By.id("jump-to-suggestion-search-global")).click();
    }
}
