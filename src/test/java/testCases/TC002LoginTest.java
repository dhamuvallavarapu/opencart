package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002LoginTest extends BaseClass {


    @Test(groups = {"Master","Sanity"})
    public void verifyLogin() {
        logger.info("started executing TC002_LoginTest ");
        try {
            //Homepage
            logger.info("entered into homepage");
            HomePage hp = new HomePage(driver);
            hp.clickMyAccountLink();
            hp.clickLoginLink();
            logger.info("left homepage");

            //Loginpage
            logger.info("entered into loginpage");
            LoginPage lp = new LoginPage(driver);
            logger.info("entering email");
            lp.enterEmail(p.getProperty("email"));
            logger.info("entering pwd");
            lp.enterPwd(p.getProperty("pwd"));
            logger.info("clicking loginbtn");
            lp.loginBtnClick();
            logger.info("left loginpage");

            //my account page
            logger.info("entered into myaccount page");
            MyAccountPage macc = new MyAccountPage(driver);
            boolean msg = macc.isMyAccountExists();
            Assert.assertTrue(msg, "login failed");
            logger.info("left myaccount page");

        } catch (Exception e) {
            logger.info("entered into catch block");
            Assert.fail();

        }
        logger.info("execution is completed");

    }


}
