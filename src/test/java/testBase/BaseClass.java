package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	protected WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		//loading the config.properties file
		FileReader file =new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());

		switch (br.toLowerCase()) {
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			options.setBinary("path/to/chrome");
			driver = new ChromeDriver(options);
			break;
			
		case "edge":
			System.setProperty("webdriver.edge.driver", "C:\\Users\\dhamu\\Downloads\\edgedriver_win64");
			EdgeOptions options1 = new EdgeOptions();
			options1.setBinary("path/to/edge"); 
			 options1.addArguments("--remote-allow-origins=*");
			driver = new EdgeDriver(options1);
			break;
			
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\dhamu\\Downloads\\geckodriver-v0.35.0-win-aarch64");
			FirefoxOptions options2 = new FirefoxOptions();
			options2.setBinary("path/to/firefox"); 
			driver = new FirefoxDriver(options2);
			break;
			
		default:
			System.out.print("invalid browser");
			return;

		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appurl"));//reading url from config.properties file
		driver.manage().window().maximize();

	}

	@AfterClass
	public void tearDown() {
		driver.quit();

	}

	public String randomString() {

		String generatedString = UUID.randomUUID().toString().replace("-", "");
		return generatedString;

	}

	public String randomNumber() {
		int generatedNumber = (int) (Math.random() * 100); // Generate a random number between 0 and 99
		return String.valueOf(generatedNumber); // Convert it to a String
	}

	public String alphaNumeric() {
		String password = randomString() + "@" + randomNumber();
		return password;

	}

}
