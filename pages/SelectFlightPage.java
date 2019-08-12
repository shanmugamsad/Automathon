package pages;

import org.openqa.selenium.WebElement;

import supportlibraries.*;
import com.cognizant.framework.Status;


/**
 * SelectFlightPage class
 * @author Cognizant
 */
public class SelectFlightPage extends MasterPage
{
	// UI Map object definitions
	private WebElement reserveFlights;
	
	
	/**
	 * Constructor to initialize the page
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	public SelectFlightPage(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
		
		if(!driver.getTitle().contains("Select a Flight")) {
			throw new IllegalStateException("Select a Flight page expected, but not displayed!");
		}
	}
	
	public BookFlightPage selectFlights()
	{
		reserveFlights.click();
		report.updateTestLog("Select Flights", "Select the first available flights", Status.DONE);
		return new BookFlightPage(scriptHelper);
	}
}