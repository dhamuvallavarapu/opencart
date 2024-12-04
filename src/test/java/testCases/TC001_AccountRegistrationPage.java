package testCases;

import org.apache.logging.log4j.core.config.Configurator;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationPage extends BaseClass {

	@Test
	public void verifyAccountRegistration() {
		Configurator.initialize(null, "path/to/log4j2.xml");
		logger.info("Starting the TC001_AccountRegistrationPage..");

		try {

			HomePage hp = new HomePage(driver);

			hp.clickMyAccountLink();
			logger.info("clicking on the account link..");
			hp.clickRegisterLink();
			logger.info("clicking on the register link..");

			AccountRegistrationPage arp = new AccountRegistrationPage(driver);
			logger.info("providing the customer details..");
			System.out.print(randomString());
			arp.setFirstName(randomString().toUpperCase());
			arp.setLastName(randomString().toLowerCase());
			arp.setEmail(randomString() + "@gmail.com");
			arp.setTelephoneNum(randomNumber() + "980");

			String pwd = alphaNumeric();

			arp.setPassword(pwd);
			arp.setConfirmPwd(pwd);

			arp.setSubscribeBtn();
			arp.setAgreeBox();

			arp.setContinueBtn();
			logger.info("validating expected message..");
			String msg = arp.getCnfMessage();
			Assert.assertEquals(msg, "Your Account Has Been Created!");

		} catch (Exception e) {
			logger.error("Test failed..");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		logger.info("Finished the TC001_AccountRegistrationPage..");
		System.out.println("Log4j configuration file: " + System.getProperty("log4j.configurationFile///////////////////////////////////////////////////////////////////////"));

	}
}
