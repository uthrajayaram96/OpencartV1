package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups= {"Sanity","Master"})	
	public void verifyLogin()
	{
		logger.info("**** Starting TC002_LoginTest ****");
		
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicking on MyAccount ...");
			hp.clickLogin();
			logger.info("Clicking on Login ...");
			
			LoginPage lp = new LoginPage(driver);
			logger.info("Entering email ...");
			lp.setEmailAddress(p.getProperty("email"));
			logger.info("Enterring password ...");
			lp.setPassword(p.getProperty("password"));
			logger.info("Clicking on Login ...");
			lp.clickLoginButton();
			
			//validation
			MyAccountPage myac = new MyAccountPage(driver);
			Assert.assertEquals(myac.isMyAccountPageExists(), true,"Login Failed");
		}catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("**** Finished TC002_LoginTest ****");
	}
}
