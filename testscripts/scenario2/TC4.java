package testscripts.scenario2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.cognizant.framework.Status;
import com.cognizant.framework.IterationOptions;
import com.cognizant.framework.selenium.WebDriverUtil;

import supportlibraries.DriverScript;
import supportlibraries.TestCase;
import pages.FlightConfirmationPage;
import pages.FlightFinderPage;
import pages.SignOnPage;


/**
 * Test for book flight tickets and verify booking
 * @author Cognizant
 */
public class TC4 extends TestCase
{	
	@Test
	public void runTC4()
	{
		testParameters.setCurrentTestDescription("Test for book flight tickets and verify booking");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		//testParameters.setBrowser(Browser.InternetExplorer);
		
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
		
		SignOnPage signOnPage = new SignOnPage(scriptHelper);
		signOnPage.loginAsValidUser();
	}
	
	@Override
	public void executeTest()
	{
		FlightFinderPage flightFinderPage = new FlightFinderPage(scriptHelper);
		
		/*
		// Typical usage of Page Object Model
		SelectFlightPage selectFlightPage = flightFinderPage.findFlights();
		BookFlightPage bookFlightPage = selectFlightPage.selectFlights();
		FlightConfirmationPage flightConfirmationPage = bookFlightPage.bookFlights();*/
		
		// Example of using chaining as an alternative to improve readability
		FlightConfirmationPage flightConfirmationPage = flightFinderPage.findFlights()
																		.selectFlights()
																		.bookFlights();
		
		// Verify Booking
		WebDriverUtil driverUtil = new WebDriverUtil(driver);
		if(driverUtil.isTextPresent("^[\\s\\S]*Your itinerary has been booked![\\s\\S]*$")) {
			report.updateTestLog("Verify Booking", "Tickets booked successfully", Status.PASS);
			
			//WebElement flightConfirmation = driver.findElement(By.xpath("//font/font/b/font"));
			WebElement flightConfirmation =
								driver.findElement(By.cssSelector("font > font > b > font"));
			
			String flightConfirmationNumber = flightConfirmation.getText();
			flightConfirmationNumber = flightConfirmationNumber.split("#")[1].trim();
			dataTable.putData("Flights_Data", "FlightConfirmationNumber", flightConfirmationNumber);
			report.updateTestLog("Flight Confirmation",
					"The flight confirmation number is " + flightConfirmationNumber,
					Status.DONE);
		} else {
			report.updateTestLog("Verify Booking", "Tickets booking failed", Status.FAIL);
		}
		
		flightFinderPage = flightConfirmationPage.backToFlights();
	}
	
	@Override
	public void tearDown()
	{
		report.addTestLogSection("Teardown");
		
		FlightFinderPage flightFinderPage = new FlightFinderPage(scriptHelper);
		flightFinderPage.logout();
	}
}