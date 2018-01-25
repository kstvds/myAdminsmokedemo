package ObjectRepository;

import org.openqa.selenium.By;

public class NewVisaBooking {
	public static final By Country = By.xpath(".//*[@id='searchForm']/div/div[2]/div[1]/div/div/div/select");
	public static final By PassNationality = By.xpath(".//*[@id='passengerNationality']");
	public static final By Arrival = By.xpath(".//*[@id='arrivalDate']");
	public static final By Depart = By.xpath(".//*[@id='departureDate']");
	public static final By SearchBtn = By.xpath(".//*[@id='searchForm']/div/div[3]/button");
	public static final By PassengerNo = By.xpath(".//*[@id='passangersvisa_0']");
	public static final By PassportDataBtn = By.xpath(".//*[@id='bookVisaButton_77']");
	public static final By Title = By.xpath(".//*[@id='searchResults']/div/div[1]/div[1]/select");
    public static final By PAX_FirstName = By.xpath(".//*[@id='searchResults']/div/div[1]/div[2]/input");
    public static final By PAX_LastName = By.xpath(".//*[@id='searchResults']/div/div[1]/div[3]/input");
    public static final By SubmitBtn = By.xpath(".//*[@id='continueToBook']");
    public static final By VisaBookingDetails = By.xpath(".//*[@id='mainContainer']/div[1]/div/div[2]/div[1]/table/thead/tr/th");
    public static final By VisaID = By.xpath(".//*[@id='mainContainer']/div[1]/div/div[1]/div[1]/h3");

}
