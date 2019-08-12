package pages;

import org.openqa.selenium.WebElement;

import supportlibraries.*;
import com.cognizant.framework.Status;


/**
 * FlightFinderPage class
 * @author Cognizant
 */
public class FlightFinderPage extends MasterPage
{
	// UI Map object definitions
	private WebElement passCount;
	private WebElement fromPort;
	private WebElement fromMonth;
	private WebElement fromDay;
	private WebElement toPort;
	private WebElement toMonth;
	private WebElement toDay;
	private WebElement airline;
	private WebElement findFlights;
	
	
	/**
	 * Constructor to initialize the page
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	public FlightFinderPage(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
		
		if(!driver.getTitle().contains("Find a Flight")) {
			throw new IllegalStateException("Find a Flight page expected, but not displayed!");
		}
	}
	
	public SelectFlightPage findFlights()
	{
		passCount.sendKeys((dataTable.getData("Passenger_Data", "PassengerCount")));
		fromPort.sendKeys((dataTable.getData("Flights_Data","FromPort")));
		fromMonth.sendKeys((dataTable.getData("Flights_Data","FromMonth")));
		fromDay.sendKeys((dataTable.getData("Flights_Data","FromDay")));
		toPort.sendKeys((dataTable.getData("Flights_Data","ToPort")));
		toMonth.sendKeys((dataTable.getData("Flights_Data","ToMonth")));
		toDay.sendKeys((dataTable.getData("Flights_Data","ToDay")));
		airline.sendKeys((dataTable.getData("Flights_Data","Airline")));
		findFlights.click();
		report.updateTestLog("Find Flights", "Search for flights using given test data", Status.DONE);
		
		return new SelectFlightPage(scriptHelper);
	}
}