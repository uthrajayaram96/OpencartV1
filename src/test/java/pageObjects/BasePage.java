/* Parent class of all the classes as we have to include 
 * driver all the page object class, just put it in a single class and inherit that class. */

package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	WebDriver driver;
	
	BasePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
}
