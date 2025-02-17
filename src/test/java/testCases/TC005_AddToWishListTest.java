package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.SearchPage;
import pageObjects.WishListPage;
import testBase.BaseClass;

public class TC005_AddToWishListTest extends BaseClass {
	MyAccountPage myac;
	@BeforeClass
	public void login()
	{
		logger.info("**** Logging In ****");
		
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
			myac = new MyAccountPage(driver);
			Assert.assertEquals(myac.isMyAccountPageExists(), true,"Login Failed");
		}catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("**** Finished Log In ****");
	}
	
	@Test(priority =1, groups= {"Master"})
	public void verifyAddingSearchedProductsToWishList(){
		logger.info("**** Starting TC_WL_001 Verify adding searched products to Wish List ****");
		
		// Search for the product
		String productName = p.getProperty("searchProductName");
		
		HomePage hp = new HomePage(driver);
		hp.setSearchText(productName);
		hp.clickSearch();
		
		// Click on Add To Wish List icon
		SearchPage sp = new SearchPage(driver);
		sp.addToWishList();
		
	
		// Validate success message is present
		if(sp.confAddToWishListMessage()){
			sp.clickLnkWishList();
			Assert.assertTrue(true);		
		}
		else{
			Assert.fail();
		}
		
		// validate the item is present in the wish list
		WishListPage wl = new WishListPage(driver);
		if(wl.checkProductInWishList(productName)){
			Assert.assertTrue(true);			
		}
		else{
			Assert.fail();
		}
		
		logger.info("**** End TC_WL_001 Verify adding searched products to Wish List ****");
	}
	
	@Test(priority =2, groups= {"Master"})
	public void verifyWishListHeaderOption() throws InterruptedException{
		
		logger.info("**** Starting TC_WL_002 Verify navigating to 'My Wish List' page using the 'Wish List' header option ****");
		
		
		// Add Product from featured section to wish list
		String productName = p.getProperty("featuredProductName");
		int pdtNumber = Integer.parseInt(p.getProperty("featuredProductNumber"));
		
		HomePage hp = new HomePage(driver);
		hp.goToHomePage();
		Thread.sleep(1000);
		hp.addFeaturedProductToWishList(pdtNumber);
		
		// Validate success message is present
		SearchPage sp = new SearchPage(driver);
		if(sp.confAddToWishListMessage()){
			hp.clickWishListHeaderOption();
			Thread.sleep(2000);
			Assert.assertTrue(true);		
		}
		else{
			Assert.fail();
		}
				
		// validate the item is present in the wish list
		WishListPage wl = new WishListPage(driver);
		if(wl.checkProductInWishList(productName)){
			Assert.assertTrue(true);			
		}
		else{
			Assert.fail();
		}
		
		logger.info("**** End TC_WL_002 Verify navigating to 'My Wish List' page using the 'Wish List' header option ****");
		
	}
	
	
	@AfterClass
	public void logout()
	{
		myac.clickLogout();
		logger.info("Logging out ...");
	
	}

}
