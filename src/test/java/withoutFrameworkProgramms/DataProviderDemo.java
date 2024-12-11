package withoutFrameworkProgramms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class DataProviderDemo {
    WebDriver driver;

    @BeforeClass
    void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }

    @Test(dataProvider = "dp")
    void dataProviderTest(String email, String pwd) throws InterruptedException {
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(pwd);
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        Thread.sleep(2000);
        boolean status = driver.findElement(By.xpath("//h2[text()='My Account']")).isDisplayed();
        if (status) {

            driver.findElement(By.xpath("(//a[text()='Logout'])[2]")).click();
        } else
            Assert.fail();

    }


    @AfterClass
    void tearDown() {
        driver.quit();


    }

    @DataProvider(name = "dp",indices={0,1})
    Object[][] loginData() {
        Object data[][] = {
                {"dhamuvallavarapu@gmail.com", "Dhamu@2001"},//this is valid
                {"dhamuvallavarapu3@gmail.com", "Dhamu@2001"},//this is invalid
                {"dhamuvallavarapu@gmail.com", "thamu@2001"} //this is invalid
        };
        return data;

    }

}

