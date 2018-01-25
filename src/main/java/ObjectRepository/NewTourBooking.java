package ObjectRepository;
import org.openqa.selenium.By;

public class NewTourBooking {
	public static final By destCity = By.id("City");
    public static final By TourName = By.id("Tour");
    public static final By TourDate = By.name("tourDate");
    public static final By NoofAdults = By.xpath(".//*[@id='searchForm']/div/div[2]/div[4]/div/div/div[1]/select");
    public static final By NoofChild = By.xpath(".//*[@id='searchForm']/div/div[2]/div[4]/div/div/div[2]/select");
    public static final By TourLang = By.xpath(".//*[@id='searchForm']/div/div[2]/div[5]/div/div/div[1]/select");
    public static final By TourType = By.xpath(".//*[@id='searchForm']/div/div[2]/div[5]/div/div/div[2]/select");
    public static final By Resultsperpage = By.xpath(".//*[@id='perPage']");
    public static final By PriceCurrency = By.xpath(".//*[@id='currency']");
    public static final By BackButton = By.xpath(".//*[@id='searchForm']/div/div[3]/a[1]");
    public static final By HomeButton = By.xpath(".//*[@id='searchForm']/div/div[3]/a[2]");
    public static final By SearchButton = By.xpath(".//*[@id='searchForm']/div/div[3]/button");
    public static final By SearchResults = By.xpath(".//*[@id='searchResults']/div[2]");
    public static final By TourNameRes = By.xpath(".//*[@id='tourName_4384']");
    public static final By GetTourButton = By.xpath(".//*[@id='searchResults']/div[3]/div[3]/button");
    public static final By TourCheckbox = By.xpath(".//*[@id='tour_124588']/div[3]/div/div[1]/div[2]/div/div[1]/label/input");
    public static final By ProceedBooking = By.xpath(".//*[@id='tour_124588']/div[5]/button");
    public static final By Title = By.xpath(".//*[@id='passengerDetailsOnModal']/div/div[1]/div[1]/select");
    public static final By PAX_FirstName = By.xpath(".//*[@id='passengerDetailsOnModal']/div/div[1]/div[2]/input");
    public static final By PAX_LastName = By.xpath(".//*[@id='passengerDetailsOnModal']/div/div[1]/div[3]/input");
    public static final By Pickup = By.xpath(".//*[@id='bookingForm']/div[4]/div[1]/input");
    public static final By BookTour = By.xpath(".//*[@id='continueToBook']");
    public static final By TourBookingStatus = By.xpath(".//*[@id='mainContainer']/div[1]/div/div[1]/div[1]/h5[2]");
    public static final By TourBookingDetails = By.xpath(".//*[@id='mainContainer']/div[1]/div/div[2]/div[2]/table/thead/tr/th");
    public static final By TourID = By.xpath(".//*[@id='mainContainer']/div[1]/div/div[1]/div[1]/h3");
}
