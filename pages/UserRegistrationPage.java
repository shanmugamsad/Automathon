package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import supportlibraries.*;

import com.cognizant.framework.Status;


/**
 * UserRegistrationPage class
 * @author Cognizant
 */
public class UserRegistrationPage extends MasterPage
{
	// UI Map object definitions
	private WebElement firstName;
	private WebElement lastName;
	private WebElement phone;
	@FindBy(name = "userName")
	private WebElement email;
	private WebElement address1;
	private WebElement city;
	private WebElement state;
	private WebElement postalCode;
	@FindBy(name = "email")
	private WebElement userName;
	private WebElement password;
	private WebElement confirmPassword;
	private WebElement register;
	
	
	/**
	 * Constructor to initialize the page
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	public UserRegistrationPage(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
		
		if(!driver.getTitle().contains("Register")) {
			throw new IllegalStateException("User Registration page expected, but not displayed!");
		}
	}
	
	public UserRegistrationConfirmationPage registerUser()
	{
		firstName.sendKeys(dataTable.getData("RegisterUser_Data","FirstName"));
		lastName.sendKeys(dataTable.getData("RegisterUser_Data","LastName"));		
		phone.sendKeys(dataTable.getData("RegisterUser_Data","Phone"));		
		email.sendKeys(dataTable.getData("RegisterUser_Data","Email"));	
		address1.sendKeys(dataTable.getData("RegisterUser_Data","Address"));
		city.sendKeys(dataTable.getData("RegisterUser_Data","City"));
		state.sendKeys(dataTable.getData("RegisterUser_Data","State"));
		postalCode.sendKeys(dataTable.getData("RegisterUser_Data","PostalCode"));
		userName.sendKeys(dataTable.getData("General_Data", "Username"));
		String passwordData = dataTable.getData("General_Data", "Password");
		password.sendKeys(passwordData);
		confirmPassword.sendKeys(passwordData);
		register.click();
		report.updateTestLog("Registration", "Enter user details for registration", Status.DONE);
		
		return new UserRegistrationConfirmationPage(scriptHelper);
	}
}