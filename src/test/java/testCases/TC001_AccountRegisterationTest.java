package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

//Testing with single data set and not data driven testing

public class TC001_AccountRegisterationTest extends BaseClass{

	@Test(groups={"Regression","Master"})
	public void testAccountRegisteration() {
		
		logger.info("**** Starting TC001_AccountRegisterationTest ****");
		try
		{
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicking on My Account link ...");
			
			hp.clickRegister();
			logger.info("Clicking on Register link ...");
			
			AccountRegisterationPage accReg = new AccountRegisterationPage(driver);
			
		//need to generate random data - RandomStringUtils class present in commons.lang library - installed commons-long dependency in pom file
			
			//accReg.setFirstName("Janee");
			//accReg.setLastName("Doee");
			//accReg.setEmail("janeedoee@email.com");
			//accReg.setTelephone("1234543676");
			//accReg.setPassword("testing1234");
			//accReg.setConfirmPassword("testing1234");
			//accReg.setPrivaryPolicy();
			//accReg.clickContinue();
			logger.info("Providing customer details ...");
			accReg.setFirstName(generateRandomString());
			accReg.setLastName(generateRandomString());
			accReg.setEmail(generateRandomString()+"@email.com");
			accReg.setTelephone(generateRandomNumber());
			
			String randompswd = generateRandomPassword();
			accReg.setPassword(randompswd);
			accReg.setConfirmPassword(randompswd);
			accReg.setPrivaryPolicy();
			accReg.clickContinue();
			
			logger.info("Validating expected message ...");
			Assert.assertEquals(accReg.getConfirmationMessage(), "Your Account Has Been Created!");
		}
		catch(Exception e) {
			logger.error("Test Failed ...");
			logger.debug("Debug logs ...");
			Assert.fail();
		}
		catch(AssertionError ase) {
			logger.error("Test Failed ...");
			logger.error("Assertion failed. Not the expected message!");
			logger.debug("Debug logs ...");
			Assert.fail();
		}
		
		logger.info("**** Finished TC001_AccountRegisterationTest ****");
	}
	
}
