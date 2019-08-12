package functionallibraries;

import com.cognizant.framework.Status;
import supportlibraries.ReusableLibrary;
import supportlibraries.ScriptHelper;

import pages.*;


/**
 * Functional Library class
 * @author Cognizant
 */
public class FunctionalLibrary extends ReusableLibrary
{
	/**
	 * Constructor to initialize the functional library
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	public FunctionalLibrary(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
	}
	
	public SignOnPage loginAsValidUserAndLogout(SignOnPage signOnPage)
	{
		FlightFinderPage flightFinderPage = signOnPage.loginAsValidUser();
		
		// The login succeeds if the flight finder page is displayed
		// Hence no further verification is required
		report.updateTestLog("Verify Login", "Login succeeded for valid user", Status.PASS);
		
		signOnPage = flightFinderPage.logout();
		return signOnPage;
	}
}