package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "(//span[contains(@class,'hidden-xs hidden')])[3]")
	WebElement myAccountLink;

	@FindBy(xpath = "//a[contains(@href,'route=account/register')]")
	WebElement registerLink;

	@FindBy(linkText = "Login")
	WebElement loginLink;

	public void clickMyAccountLink() {
		myAccountLink.click();

	}

	public void clickRegisterLink() {
		registerLink.click();

	}

	public void clickLoginLink(){
		loginLink.click();
	}
}
