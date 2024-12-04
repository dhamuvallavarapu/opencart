package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement firstNameTxt;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement lastNameTxt;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement emailTxt;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement telephoneTxt;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement pwdTxt;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement pwdConfirmTxt;

	@FindBy(xpath = "//input[@name='newsletter']")
	WebElement subsribeRadioBtn;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement agreeCheckBox;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement continueBtn;

	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	WebElement cnfMessage;

	public void setFirstName(String fname) {
		firstNameTxt.sendKeys(fname);

	}

	public void setLastName(String lname) {
		lastNameTxt.sendKeys(lname);
	}

	public void setEmail(String email) {
		emailTxt.sendKeys(email);

	}

	public void setTelephoneNum(String num) {
		telephoneTxt.sendKeys(num);
	}

	public void setPassword(String pwd) {
		pwdTxt.sendKeys(pwd);
	}

	public void setConfirmPwd(String cpwd) {
		pwdConfirmTxt.sendKeys(cpwd);
	}

	public void setSubscribeBtn() {
		subsribeRadioBtn.click();

	}

	public void setAgreeBox() {
		agreeCheckBox.click();

	}

	public void setContinueBtn() {
		continueBtn.click();

	}

	public String getCnfMessage() {
		try {
			return (cnfMessage.getText());

		} catch (Exception e) {
			return (e.getMessage());
		}
	}
}
