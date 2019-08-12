package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import supportlibraries.*;


/**
 * FlightConfirmationPage class
 * @author Cognizant
 */
public class FlightConfirmationPage extends MasterPage
{
	// UI Map object definitions
	@FindBy(xpath = "//a/img")
	private WebElement flights;
	
	
	/**
	 * Constructor to initialize the component library
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	public FlightConfirmationPage(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
		
		if(!driver.getTitle().contains("Flight Confirmation")) {
			throw new IllegalStateException("Flight Confirmation page expected, but not displayed!");
		}
	}
	
	public FlightFinderPage backToFlights()
	{
		flights.click();
		return new FlightFinderPage(scriptHelper);
	}
}