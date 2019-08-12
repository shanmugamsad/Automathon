package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import supportlibraries.*;
import com.cognizant.framework.Status;


/**
 * BookFlightPage class
 * @author Cognizant
 */
public class BookFlightPage extends MasterPage
{
	// UI Map object definitions
	private String firstName = "passFirst";
	private String lastName = "passLast";
	private WebElement creditnumber;
	private WebElement creditCard;
	private WebElement buyFlights;
	
	
	/**
	 * Constructor to initialize the page
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	public BookFlightPage(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
		
		if(!driver.getTitle().contains("Book a Flight")) {
			throw new IllegalStateException("Book a Flight page expected, but not displayed!");
		}
	}
	
	public FlightConfirmationPage bookFlights()
	{
		String[] passengerFirstNames = dataTable.getData("Passenger_Data", "PassengerFirstNames").split(",");
		String[] passengerLastNames = dataTable.getData("Passenger_Data", "PassengerLastNames").split(",");
		int passengerCount = Integer.parseInt(dataTable.getData("Passenger_Data", "PassengerCount"));
		
		for(int i=0; i<passengerCount; i++) {
			driver.findElement(By.name(firstName + i)).sendKeys(passengerFirstNames[i]);
			driver.findElement(By.name(lastName + i)).sendKeys(passengerLastNames[i]);
		}
		
		creditCard.sendKeys(dataTable.getData("Passenger_Data", "CreditCard"));
		creditnumber.sendKeys(dataTable.getData("Passenger_Data", "CreditNumber"));
		buyFlights.click();
		report.updateTestLog("Book Tickets", "Enter passenger details and book tickets", Status.DONE);
		
		return new FlightConfirmationPage(scriptHelper);
	}
}