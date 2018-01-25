package Transfer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.NewAccoBooking;
import ObjectRepository.Operations;
import Utility.Configuration;
import lib.DriverAndObjectDetails;
import lib.ExcelDataConfig;
import lib.ExtentManager;
import lib.Takescreenshot;
import lib.DriverAndObjectDetails.DriverName;

public class Transfer_Booking_Transfertype_Private {
	
	Configuration Config = new Configuration();
	Takescreenshot obj = new Takescreenshot();
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	NewAccoBooking acco = new NewAccoBooking();
	Operations opo = new Operations();
	WebDriver driverqa;
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	Logger logger = Logger.getLogger("transfer_Search_Apply_Filters_For_Search_Results");
	
	
	@Test
	@Parameters({ "browsername" })
	public void login(String browsername) throws Exception {
		PropertyConfigurator.configure("Log4j.properties");
		

		try {
			if (browsername.equalsIgnoreCase("CH")) {

				driverqa = new DriverAndObjectDetails(DriverName.CH).CreateDriver();
			} else if (browsername.equalsIgnoreCase("IE")) {
				driverqa = new DriverAndObjectDetails(DriverName.IE).CreateDriver();
			} else {
				driverqa = new DriverAndObjectDetails(DriverName.FF).CreateDriver();
			}
			logger.info("Browser Opened");
			driverqa.get(Config.getApplicationUrl());
			logger.info("Test Case Started");
			ExcelDataConfig excel = new ExcelDataConfig(Config.getExcelPath());
			test = rep.startTest("LoginTest");
			test.log(LogStatus.INFO, "Starting LoginTest");
			WebElement username = driverqa.findElement(LoginPage.uname);
			username.clear();
			username.sendKeys(excel.getData(0, 1, 0));
			WebElement password = driverqa.findElement(LoginPage.pwd);
			password.clear();
			password.sendKeys(excel.getData(0, 1, 1));
			driverqa.findElement(LoginPage.submit).click();
			Thread.sleep(1000);
			String expectedtitle = "DOTWconnect.com::DOTWconnect.com: My Admin";
			String atualtitle = driverqa.getTitle();
			Assert.assertEquals(atualtitle, expectedtitle);
			test.log(LogStatus.INFO, "Ending LoginTest");
			test.log(LogStatus.PASS, "PASSED LoginTest");
			logger.info("Login Successful");
		} catch (Exception e) {
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
		}
	}

	@Test(dependsOnMethods = { "login" })
	public void searchaccomodation() throws Exception {
		Actions action;
		action = new Actions(driverqa);
		WebDriverWait wait;
		ExcelDataConfig excel;
		wait = new WebDriverWait(driverqa, 30);
		excel = new ExcelDataConfig(Config.getExcelPath());
		test = rep.startTest("searchaccomodation");
		try {

			test.log(LogStatus.INFO, "Navigating to customer search page");
			logger.info("Navigating to customer search page");
			driverqa.findElement(HomePage.operation).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.newBooking));
			driverqa.findElement(Operations.newBooking).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.transferBooking));
			driverqa.findElement(Operations.transferBooking).click();
			Thread.sleep(2000);
			String searchcustatualtitle = driverqa.getTitle();
			String searchcustexpectedtitle = "DOTWconnect.com::New Transfer Booking";
			Assert.assertEquals(searchcustatualtitle, searchcustexpectedtitle);
			logger.info("Navigated to customer search page");
			test.log(LogStatus.PASS, "Customer Search Page");
		} catch (Exception e) {
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
		}
				logger.info("Selecting Customer");
			     test.log(LogStatus.INFO, "Selecting Customer");
				 try {
					 test.log(LogStatus.PASS, "Selecting Customer");
					wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.company));
					 driverqa.findElement(Operations.company).sendKeys(excel.getData(1, 4, 0));
					 Thread.sleep(1000);
					 action.sendKeys(Keys.ARROW_DOWN).build().perform();
					 action.sendKeys(Keys.ENTER).build().perform();
					 Thread.sleep(2000);
					 action.sendKeys(Keys.ENTER).build().perform();
					 Thread.sleep(2000);
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Transfer/Transfer_booking_for_adults_with_children/Customer-list-transfer-book.jpg");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.chooseCustbook));
					 driverqa.findElement(Operations.chooseCustbook).click();
					Thread.sleep(1000);
					String searchpageactualtitle = driverqa.getTitle();
					String searchpageexpectedtitle = "DOTWconnect.com::";
					Assert.assertEquals(searchpageactualtitle, searchpageexpectedtitle);
					logger.info("Customer Selected");
					test.log(LogStatus.PASS, "Customer Selected");
				 }
				 catch (Exception e) {
						logger.info(e.getMessage());
						test.log(LogStatus.FAIL, e.getMessage());
						rep.endTest(test);
						rep.flush();
						Assert.assertTrue(false, e.getMessage());;
					}
				 
				 logger.info("Applying search Filters");
				 try{
					 test.log(LogStatus.INFO, "Starting TransferSearch");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.transferName));
					 driverqa.findElement(NewAccoBooking.transferName).sendKeys(excel.getData(1, 10, 0));
					 Thread.sleep(2000);
					 action.sendKeys(Keys.ARROW_DOWN).build().perform();
					 action.sendKeys(Keys.ENTER).build().perform();
					 Select transferTime= new Select(driverqa.findElement(NewAccoBooking.transferTime));
					 transferTime.selectByIndex(9);
					 driverqa.findElement(NewAccoBooking.transferDate).clear();
					 driverqa.findElement(NewAccoBooking.transferDate).sendKeys(excel.getData(1, 13, 0));
					 action.sendKeys(Keys.ENTER).build().perform();
					 String expected=excel.getData(1, 10, 0);
					 Select noofchild = new Select(driverqa.findElement(NewAccoBooking.transferBookingChild));
					 noofchild.selectByIndex(1);
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.transferBookingChildAge));
					 Select childage = new Select(driverqa.findElement(NewAccoBooking.transferBookingChildAge));
					 childage.selectByIndex(5);
					 Thread.sleep(2000);
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Transfer/Transfer_booking_for_adults_with_children/Search-Hotel-filters-transfer-book.jpg");
					 driverqa.findElement(NewAccoBooking.searchButton).click();
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.resultHotel));
					 String result= driverqa.findElement(NewAccoBooking.resultHotel).getText();
					 Thread.sleep(2000);
					 //obj.Takesnap(driverqa, Config.SnapShotPath() + "/Transfer_booking_for_adults_with_children/Search-Result-transfer-book.jpg");
					 Assert.assertTrue(result.contains(expected));
					 test.log(LogStatus.INFO, "Ending TransferSearch");
					 test.log(LogStatus.PASS, "PASSED TransferSearch");
					 logger.info("Transfer Search Complete");
					 rep.endTest(test);
					 rep.flush();
				} catch (Exception e) {
					logger.info(e.getMessage());
					test.log(LogStatus.FAIL, e.getMessage());
					rep.endTest(test);
					rep.flush();
					Assert.assertTrue(false, e.getMessage());
				}
				 logger.info("Selecting Get Transfer");
		         try {
					driverqa.findElement(NewAccoBooking.transferGetTransfers).click();
					JavascriptExecutor jse = (JavascriptExecutor)driverqa;
					//Scroll vertically downward by 250 pixels
					jse.executeScript("window.scrollBy(0,250)", "");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.transferRadioSelection));
					 driverqa.findElement(NewAccoBooking.transferRadioSelection).click();
					 logger.info("Transfer Type Selected");
					 test.log(LogStatus.INFO, "Start Booking");
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Transfer/Transfer_booking_for_adults_with_children/Selected-transfer type-available-book-transfer.jpg");
					 Thread.sleep(1000);
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.transProceedBook));
					 driverqa.findElement(NewAccoBooking.transProceedBook).click();
					 Thread.sleep(2000);
					 logger.info("Entering First Passenger details");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.transferFrstPaxFname));
					 driverqa.findElement(NewAccoBooking.transferFrstPaxFname).sendKeys(excel.getData(1, 17, 0));
					 Thread.sleep(2000);
					 driverqa.findElement(NewAccoBooking.transferFrstPaxLname).sendKeys(excel.getData(1, 17, 1));
					 Select firstPassengertitle = new Select(driverqa.findElement(NewAccoBooking.transferFrstPaxTitle));
					 firstPassengertitle.selectByIndex(1);
					 logger.info("Initiating Pick Up From Airport");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.transferPickUp));
					 driverqa.findElement(NewAccoBooking.transferPickUp).sendKeys(excel.getData(2, 2, 0));
					 Thread.sleep(2000);
					 driverqa.findElement(NewAccoBooking.transDropOff).sendKeys(excel.getData(2, 2, 1));
					 Select airlineDetails = new Select(driverqa.findElement(NewAccoBooking.transferAirlineDetails));
					 airlineDetails.selectByIndex(1);
					 driverqa.findElement(NewAccoBooking.transferArrivingFrom).sendKeys(excel.getData(2, 2, 2));
					 Thread.sleep(2000);
					 action.sendKeys(Keys.ARROW_DOWN).build().perform();
					 action.sendKeys(Keys.ENTER).build().perform();
					 Thread.sleep(2000);
					JavascriptExecutor js = (JavascriptExecutor)driverqa;
						//Scroll vertically downward by 250 pixels
					 js.executeScript("window.scrollBy(0,250)", "");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.transferFlightNo));
					 driverqa.findElement(NewAccoBooking.transferFlightNo).sendKeys(excel.getData(2, 2, 3));
					 Select estimateArrivalHrs=new Select(driverqa.findElement(NewAccoBooking.transferArrivalHour));
					 estimateArrivalHrs.selectByIndex(1);
					 Select estimateArrivalSecs=new Select(driverqa.findElement(NewAccoBooking.transferArrivalMins));
					 estimateArrivalSecs.selectByIndex(1);
					 Thread.sleep(3000);
					 js.executeScript("window.scrollBy(0,250)", "");
					 obj.Takesnap(driverqa, "D:/Test Repository/Test Screenshots//Transfer/Transfer_booking_for_adults_with_children/Passenger-Details-transfer-book.jpg");
					 driverqa.findElement(NewAccoBooking.bookBtn).click();
					 logger.info("Entered Passenger details");
					 Thread.sleep(4000);
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Transfer/Transfer_booking_for_adults_with_children/Confirm-Booking-transfer-book.jpg");
					 test.log(LogStatus.INFO, "Ending TransferBook");
					 test.log(LogStatus.PASS, "PASSED TransferBook");
					 logger.info("Transfer Book Complete");
					 rep.endTest(test);
					 rep.flush();
				} catch (Exception e) {
					logger.info(e.getMessage());
					test.log(LogStatus.FAIL, e.getMessage());
					rep.endTest(test);
					rep.flush();
					Assert.assertTrue(false, e.getMessage());
				}

			}
	
	 @AfterMethod
		public void getResult(ITestResult result) {
			if (result.getStatus() == ITestResult.FAILURE) {
				test.log(LogStatus.FAIL, result.getThrowable());
			}
			rep.endTest(test);
		}

		/*@AfterTest
		public void afterTest() {

			rep.endTest(test);
			rep.flush();
			driverqa.close();
		}*/
		
		/*@AfterClass
		public void killDriver() {
			try {
                Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
                Runtime.getRuntime().exec("taskkill /F /IM FirefoxDriver.exe");
            } catch (IOException e) {
                e.printStackTrace();
            };
		}*/

}
