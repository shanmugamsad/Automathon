package testscripts.scenario1;

import org.testng.annotations.Test;

import pages.SignOnPage;

import com.cognizant.framework.Status;
import com.cognizant.framework.selenium.Browser;

import supportlibraries.DriverScript;
import supportlibraries.TestCase;


/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class TC2 extends TestCase
{
	@Test
	public void runTC2()
	{
		testParameters.setCurrentTestDescription("Test for login with invalid user credentials");
		testParameters.setBrowser(Browser.Chrome);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}
	
	@Override
	public void executeTest()
	{
		SignOnPage signOnPage = new SignOnPage(scriptHelper);
		signOnPage = signOnPage.loginAsInvalidUser();
		
		// The login fails if the sign-on page is displayed again
		// Hence no further verification is required
		report.updateTestLog("Verify Login", "Login failed for invalid user", Status.PASS);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}