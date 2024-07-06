package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDrivenTest extends BaseClass {

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups= "DataDriven")
	public void verifyLoginDDT(String email, String pwd, String exp) {
		
		logger.info("**** Starting TC003_LoginDataDrivenTest ****");
		
		try {
			
				HomePage hp = new HomePage(driver);
				hp.clickMyAccount();
				logger.info("Clicking on MyAccount ...");
				hp.clickLogin();
				logger.info("Clicking on Login ...");
				
				LoginPage lp = new LoginPage(driver);
				logger.info("Entering email ...");
				lp.setEmailAddress(email);
				logger.info("Enterring password ...");
				lp.setPassword(pwd);
				logger.info("Clicking on Login ...");
				lp.clickLoginButton();
				
				//validation
				MyAccountPage myac = new MyAccountPage(driver);
				boolean isLoginSuccess = myac.isMyAccountPageExists();
				
				/* Valid data - login success - pass
				 * 				login failed - fail
				 * Invalid data - login success - fail
				 * 				  login failed - pass
				 * 
				*/
				if(exp.equalsIgnoreCase("Valid")) {
					if(isLoginSuccess)
					{
						myac.clickLogout();
						logger.info("Logging out ...");
						Assert.assertTrue(true); //test case passed
					}
					else
					{
						Assert.assertTrue(false); //test case failed if not logged in by valid data
					}
				}
				else 
				{
					if(isLoginSuccess)
					{
						myac.clickLogout();
						logger.info("Logging out ...");
						Assert.assertTrue(false); //test case failed as it shouldn't login with invalid credentials
					}
					else
					{
						Assert.assertTrue(true); //test case passed
					}
					
				}
		}catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("**** Finished TC002_LoginDataDrivenTest ****");
	
	}
	
}
