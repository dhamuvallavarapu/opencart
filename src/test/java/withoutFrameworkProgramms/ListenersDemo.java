package withoutFrameworkProgramms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
//@Listeners(withoutFrameworkProgramms.MyListener.class)

public class ListenersDemo {
    WebDriver driver;

    @BeforeClass
    void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
        driver.get("https://www.orangehrm.com/");
    }

    @Test(priority = 1)
    void verifyHomePageContent() {
        boolean status = driver.findElement(By.xpath("//div[@class='homepage-slider-content']")).isDisplayed();
        Assert.assertTrue(status);

    }

    @Test(priority = 2)
    void verifyUrl() {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://www.orangehrm.com/");//have to change
    }

    @Test(priority = 3, dependsOnMethods = {"verifyUrl"})
    void verifyHomePageTitle() {
        String title = driver.getTitle();
        Assert.assertEquals(title, "Human Resources Management Software | OrangeHRM");
    }


    @AfterClass
    void tearDown() {
        System.out.println("Working Directory: " + System.getProperty("user.dir"));

        File reportFile = new File(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "myReport.html");
        System.out.println("Expected Report Path: " + reportFile.getAbsolutePath());
        System.out.println("Does reports folder exist? " + reportFile.getParentFile().exists());

        driver.quit();

    }
}
