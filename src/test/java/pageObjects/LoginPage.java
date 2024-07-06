package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(name="email")
	WebElement txtEmail;
	
	@FindBy(name="password")
	WebElement txtPswd;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;
	
	public void setEmailAddress(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setPassword(String pswd)
	{
		txtPswd.sendKeys(pswd);
	}
	
	public void clickLoginButton() 
	{
		btnLogin.click();
	}
	
}
