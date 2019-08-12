package testscripts.scenario1;

import org.testng.annotations.Test;

import com.cognizant.framework.FrameworkException;
import com.cognizant.framework.Status;
import com.cognizant.framework.selenium.WebDriverUtil;

import supportlibraries.DriverScript;
import supportlibraries.TestCase;
import pages.SignOnPage;
import pages.UserRegistrationConfirmationPage;
import pages.UserRegistrationPage;
import functionallibraries.FunctionalLibrary;


/**
 * Test for register new user and login using the registered user
 * @author Cognizant
 */
public class TC3 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC3()
	{
		testParameters.setCurrentTestDescription("Test for register new user and login using the registered user");
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}
	
	@Override
	public void executeTest()
	{
		// Register User
		SignOnPage signOnPage = new SignOnPage(scriptHelper);
		UserRegistrationPage userRegistrationPage = signOnPage.clickRegister();
		UserRegistrationConfirmationPage userRegistrationConfirmationPage = 
													userRegistrationPage.registerUser();
		
		// Verify Registration
		WebDriverUtil driverUtil = new WebDriverUtil(driver);
		String userName = dataTable.getData("General_Data", "Username");
		if(driverUtil.isTextPresent("^[\\s\\S]*Dear " +
				dataTable.getExpectedResult("FirstName") + " " +
				dataTable.getExpectedResult("LastName") + "[\\s\\S]*$")) {
		report.updateTestLog("Verify Registration",
									"User " + userName + " registered successfully", Status.PASS);
		} else {
			throw new FrameworkException("Verify Registration",
											"User " + userName + " registration failed");
		}
		
		// Login as registered user and logout
		signOnPage = userRegistrationConfirmationPage.clickSignIn();
		signOnPage = functionalLibrary.loginAsValidUserAndLogout(signOnPage);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}