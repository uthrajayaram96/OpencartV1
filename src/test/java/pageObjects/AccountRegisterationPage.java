package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterationPage extends BasePage{

	public AccountRegisterationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(id="input-firstname")
	WebElement firstname;
	
	@FindBy(id="input-lastname")
	WebElement lastname;
	
	@FindBy(id="input-email")
	WebElement email;
	
	@FindBy(id="input-telephone")
	WebElement telephone;
	
	@FindBy(id="input-password")
	WebElement psswd;
	
	@FindBy(id="input-confirm")
	WebElement confirmPsswd;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement pvcyChkBox;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continueButton;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement acctRegisterConfrm;

	
	public void setFirstName(String fname) {
		firstname.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		lastname.sendKeys(lname);
	}
	
	public void setEmail(String eml) {
		email.sendKeys(eml);
	}
	
	public void setTelephone(String telph) {
		telephone.sendKeys(telph);
	}
	
	public void setPassword(String password) {
		psswd.sendKeys(password);
	}
	
	public void setConfirmPassword(String password) {
		confirmPsswd.sendKeys(password);
	}
	
	public void setPrivaryPolicy() {
		pvcyChkBox.click();
	}
	
	public void clickContinue() {
		continueButton.click();
	}
	
	public String getConfirmationMessage() {
		try {
			return(acctRegisterConfrm.getText());
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	
}
