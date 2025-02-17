/*
 * Description : This page contains all objects related to product or product that is searched
 */

package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {

	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//p[contains(text(),'There is no product that matches the search criteria.')]")
	WebElement ProductNotFound;
	
	@FindBy(xpath="//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']//h4//a")
	List<WebElement> ProductResults;
	
	@FindBy(name="quantity")
	WebElement txtquantity;
	
	@FindBy(xpath="//button[@id='button-cart']")
	WebElement btnAddToCart;
	
	// Confirmation message after adding item to cart
	@FindBy(xpath="//div[contains(text(),'Success: You have added')]")
	WebElement cnfMsg;
	
	@FindBy(xpath="//button[@data-original-title='Add to Wish List']")
	WebElement btnAddToWishList;
	
	// Confirmation message after adding item to WishList
	@FindBy(xpath="//div[contains(text(),'Success: You have added')]")
	WebElement cnfWLMsg;
	
	@FindBy(xpath="//a[contains(normalize-space(),'wish list')]")
	WebElement lnkAlertWishList;
	
	
	
	public boolean isNotValidSearch(String productName) {
		
		try 
		{
			return ProductNotFound.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
			
	}
	
	public String validateProductNotFoundMessage(String productName) {
			
		return ProductNotFound.getText();	
	}
	
	public void selectProduct(String productName) {
		
		for(WebElement product: ProductResults) {
			if(product.getText().equalsIgnoreCase(productName)){
				product.click();
				break;
			}
		}
	}
	
	public void setQuantity(String Qty) {
		txtquantity.clear();
		txtquantity.sendKeys(Qty);
	}
	
	public void addToCart() {
		btnAddToCart.click();
	}
	
	public boolean confMsgAddToCart() {
		try {
			return cnfMsg.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void addToWishList() {
		btnAddToWishList.click();
	}
	
	public boolean confAddToWishListMessage(){
		try {
			return cnfWLMsg.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void clickLnkWishList() {
		
		//WebDriverWait myWait = new WebDriverWait(driver,Duration.ofSeconds(20));
		//myWait.until(ExpectedConditions.elementToBeClickable(lnkAlertWishList));
		lnkAlertWishList.click();
	}

}
