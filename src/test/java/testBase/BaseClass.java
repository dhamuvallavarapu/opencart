package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"os", "browser"})
    public void setup( String os, String browser) throws IOException {
        // Loading the config.properties file
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
            try {
                DesiredCapabilities cap = new DesiredCapabilities();

                // OS
                if (os == null || os.isEmpty()) {
                    logger.error("OS parameter is missing or empty.");
                    throw new IllegalArgumentException("OS parameter is required.");
                }

                switch (os.toLowerCase()) {
                    case "windows":
                        cap.setPlatform(Platform.WINDOWS);
                        break;
                    case "mac":
                        cap.setPlatform(Platform.MAC);
                        break;
                    case "linux":
                        cap.setPlatform(Platform.LINUX);
                        break;
                    default:
                        logger.error("Invalid OS parameter: " + os);
                        throw new IllegalArgumentException("Invalid OS parameter: " + os);
                }


                // Browser
                switch (browser.toLowerCase()) {
                    case "chrome":
                        cap.setBrowserName("chrome");
                        break;
                    case "edge":
                        cap.setBrowserName("MicrosoftEdge");
                        break;
                    case "firefox":
                        cap.setBrowserName("Firefox");
                        break;
                    default:
                        System.out.println("No matching browser");
                        return;
                }

                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                System.out.println("Invalid RemoteWebDriver URL");
            }
        }

        if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.print("Invalid browser");
                    return;
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("appurl")); // Reading URL from config.properties file
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Sanity", "Regression", "Master"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String randomString() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public String randomNumber() {
        int generatedNumber = (int) (Math.random() * 100); // Generate a random number between 0 and 99
        return String.valueOf(generatedNumber); // Convert it to a String
    }

    public String alphaNumeric() {
        return randomString() + "@" + randomNumber();
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }
}
