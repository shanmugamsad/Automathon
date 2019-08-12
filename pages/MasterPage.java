package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cognizant.framework.Status;
import supportlibraries.*;


/**
 * MasterPage Abstract class
 * @author Cognizant
 */
abstract class MasterPage extends ReusableLibrary
{	
	// UI Map object definitions
	@FindBy(linkText = "SIGN-OFF")
	public WebElement signOff;
	@FindBy(linkText = "REGISTER")
	public WebElement register;
	
	
	/**
	 * Constructor to initialize the functional library
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	protected MasterPage(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
		
		PageFactory.initElements(driver, this);
	}
	
	public UserRegistrationPage clickRegister()
	{
		register.click();
		return new UserRegistrationPage(scriptHelper);
	}
	
	public SignOnPage logout()
	{
		signOff.click();
		report.updateTestLog("Logout", "Click the sign-off link", Status.DONE);
		return new SignOnPage(scriptHelper);
	}
}