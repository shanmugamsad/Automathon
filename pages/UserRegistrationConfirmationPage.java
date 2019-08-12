package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import supportlibraries.*;
import com.cognizant.framework.Status;


/**
 * UserRegistrationConfirmationPage class
 * @author Cognizant
 */
public class UserRegistrationConfirmationPage extends MasterPage
{
	// UI Map object definitions
	@FindBy(linkText = "sign-in")
	private WebElement signIn;
	
	
	/**
	 * Constructor to initialize the page
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	public UserRegistrationConfirmationPage(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
		
		if(!driver.getTitle().contains("Register")) {
			throw new IllegalStateException("User Registration Confirmation page expected, but not displayed!");
		}
	}
	
	public SignOnPage clickSignIn()
	{
		signIn.click();
		report.updateTestLog("Click sign-in", "Click the sign-in link", Status.DONE);
		return new SignOnPage(scriptHelper);
	}
}