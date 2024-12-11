package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement emailTxt;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement pwdTxt;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement loginBtn;


    public void enterEmail(String email) {
        emailTxt.sendKeys(email);
    }

    public void enterPwd(String pwd) {
        pwdTxt.sendKeys(pwd);
    }

    public void loginBtnClick() {
        loginBtn.click();
    }


}
