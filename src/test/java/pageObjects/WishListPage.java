package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishListPage extends BasePage{

	public WishListPage(WebDriver driver) {
		super(driver);
	}
	
	//Wish List products
	@FindBy(xpath="//div[@class='table-responsive']//td[2]")
	List<WebElement> wishListPdts;
	
	public boolean checkProductInWishList(String productName) {
		int sizeList = wishListPdts.size();
		
		// If the list is empty or pdt not found in the list return false
		if(sizeList!=0)
		{
			for(WebElement product: wishListPdts) {
				if(product.getText().equalsIgnoreCase(productName)){
					System.out.println(product.getText());
					return true;
				}
			}
		}
		return false;
	}

		
}
