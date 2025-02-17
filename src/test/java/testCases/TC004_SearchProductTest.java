package testCases;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC004_SearchProductTest extends BaseClass  {
	
	@Test(groups= {"Master"}, priority=1)
	public void verifyExistingProductSearch() throws IOException {
		logger.info("**** Starting TC_SF_001 Verify Search For Existing Product ****");
		
		String productName = p.getProperty("searchProductName");
		
		HomePage hp = new HomePage(driver);
		hp.setSearchText(productName);
		hp.clickSearch();
		
		SearchPage sp = new SearchPage(driver);
		if(sp.isNotValidSearch(productName))
		{
			Assert.fail();
		}
		else 
		{
			sp.selectProduct(productName);
			Assert.assertTrue(true);		
		}
		
		logger.info("**** End TC_SF_001 Verify Search For Existing Product ****");
	}
	
	@Test(priority=2)
	public void verifyNonExistingProductSearch() throws IOException {
		logger.info("**** Starting TC_SF_002 Verify Search For a Non Existing Product ****");
		
		String productName = p.getProperty("searchNonExistingProductName");
		
		HomePage hp = new HomePage(driver);
		hp.setSearchText(productName);
		hp.clickSearch();
		
		SearchPage sp = new SearchPage(driver);
		if(sp.isNotValidSearch(productName))
		{
			Assert.assertEquals(sp.validateProductNotFoundMessage(productName), "There is no product that matches the search criteria.");
		}
		else 
		{
			Assert.fail();	
		}
		
		logger.info("**** End TC_SF_002 Verify Search For a Non Existing Product ****");
	}
	
}
