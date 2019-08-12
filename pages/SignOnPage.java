package pages;

import org.openqa.selenium.WebElement;

import supportlibraries.*;
import com.cognizant.framework.Status;

/**
 * SignOnPage class
 * @author Cognizant
 */
public class SignOnPage extends MasterPage
{	
	// UI Map object definitions
	private WebElement userName;
	private WebElement password;
	private WebElement login;
	
	/**
	 * Constructor to initialize the page
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	public SignOnPage(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
		
		if(!driver.getTitle().contains("Welcome") && !driver.getTitle().contains("Sign-on")) {
			throw new IllegalStateException("Sign-on page expected, but not displayed!");
		}
	}
	
	public FlightFinderPage loginAsValidUser()
	{	
		login();
		return new FlightFinderPage(scriptHelper);
	}
	
	private void login()
	{
		String userNameData = dataTable.getData("General_Data", "Username");
		String passwordData = dataTable.getData("General_Data", "Password");
		
		userName.sendKeys(userNameData);
		password.sendKeys(passwordData);
		login.click();
		report.updateTestLog("Login", "Enter login credentials: " +
											"Username = " + userNameData + ", " +
											"Password = " + passwordData, Status.DONE);
	}
	
	public SignOnPage loginAsInvalidUser()
	{	
		login();
		return new SignOnPage(scriptHelper);
	}
}