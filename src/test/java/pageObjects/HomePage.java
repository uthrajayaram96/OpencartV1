package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//Webelemnet + locators
	/* For now only testing registration testcase later add Login while testing login page*/
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	@FindBy(xpath="//input[@placeholder='Search']")
	WebElement SearchBox;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	WebElement SearchButton;
	
	// store logo
	@FindBy(xpath="//div[@id='logo']//a")
	WebElement storeLogo;
	
	@FindBy(id="wishlist-total")
	WebElement wishListHeaderOption;
	
	// featured products save to wish list buttons
	@FindBy(xpath="//button[@data-original-title='Add to Wish List']")
	List<WebElement> fearturedPdtsWishListBtn;
	
	//Action Methods
	public void clickMyAccount()
	{
		lnkMyAccount.click();
	}

	public void clickRegister()
	{
		lnkRegister.click();
	}
	public void clickLogin()
	{
		lnkLogin.click();
	}
	
	public void setSearchText(String productName) {
		SearchBox.clear();
		SearchBox.sendKeys(productName);
	}
	
	public void clickSearch()  //For Search Product Test
	{
		SearchButton.click();
	}
	
	public void goToHomePage() {
		storeLogo.click();
	}
	
	public void clickWishListHeaderOption() {
		wishListHeaderOption.click();
	}
	
	/* Pass the a number, like which product to select. For now there are 4 pdts present in the featured
	 * product section. It will take values 1-4 */
	public void addFeaturedProductToWishList(int pdtNumber) {
		for(int i =0; i<fearturedPdtsWishListBtn.size();i++)
		{
			if(i+1 == pdtNumber)
			{
				fearturedPdtsWishListBtn.get(i).click();
				break;
			}
		}
	}
}
