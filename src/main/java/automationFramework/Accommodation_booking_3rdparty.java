package automationFramework;

import lib.DriverAndObjectDetails;
import lib.DriverAndObjectDetails.DriverName;
import lib.ExcelDataConfig;
import lib.ExtentManager;

import lib.Takescreenshot;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.NewAccoBooking;
import ObjectRepository.Operations;
import ObjectRepository.PaymentPage;
import Utility.Configuration;

import org.testng.annotations.Parameters;





import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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


public class Accommodation_booking_3rdparty {
	public WebDriver driverqa;
	Configuration Config = new Configuration();
	Takescreenshot obj= new Takescreenshot();
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	String errorpath;
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	NewAccoBooking acco = new NewAccoBooking();
	Operations opo = new Operations();
	Logger logger = Logger.getLogger("Accommodation_booking_3rdparty");
	
	 @Test
	 @Parameters({ "browsername" })
	  public void bookthirdparty(String browsername) throws Exception {
		  test = rep.startTest("bookthirdparty");
		  ExcelDataConfig excel;
		  excel = new ExcelDataConfig(Config.getExcelPath());
		  PropertyConfigurator.configure("Log4j.properties");
		  logger.info("Test Case Started");
		if (browsername.equalsIgnoreCase("CH")) {
			       driverqa = new DriverAndObjectDetails(DriverName.CH).CreateDriver();
				} else if (browsername.equalsIgnoreCase("IE")) {
					driverqa = new DriverAndObjectDetails(DriverName.IE).CreateDriver();
				} else {
					driverqa = new DriverAndObjectDetails(DriverName.FF).CreateDriver();
				}
			    WebDriverWait wait= new WebDriverWait(driverqa, 30);
			    Actions action = new Actions(driverqa);
	           try{
			    logger.info("Browser Opened");
				driverqa.get(Config.getApplicationUrl());
				logger.info("Test Case Started");
				test.log(LogStatus.INFO, "Starting Login");
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
				test.log(LogStatus.INFO, "Ending Login");
				test.log(LogStatus.PASS, "PASSED Login");
				logger.info("Login Successful");
		} catch (Exception e) {
			logger.info(e.getMessage());
			obj.Takesnap(driverqa, Config.SnapShotPath() + "/Error/Accommodation_booking_3rdparty/Log-In.jpg");
			test.log(LogStatus.FAIL, "Login");
			errorpath=Config.SnapShotPath() + "/Error/Accommodation_booking_3rdparty/Log-In.jpg";
			rep.endTest(test);
			rep.flush();
			test.log(LogStatus.FAIL, "Login");
			Assert.assertTrue(false, e.getMessage());
			
		}
		logger.info("Searching Customer");
		
		     try {
				test.log(LogStatus.INFO, "Navigating to customer search page");
				 logger.info("Navigating to customer search page");
				 driverqa.findElement(HomePage.operation).click();
				 wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.newBooking));
				 driverqa.findElement(Operations.newBooking).click();
				 wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.AccomBook));
				 driverqa.findElement(Operations.AccomBook).click();
				 Thread.sleep(2000);
				 String searchcustatualtitle = driverqa.getTitle();
				 String searchcustexpectedtitle = "DOTWconnect.com::New Accommodation Booking";
				 Assert.assertEquals(searchcustatualtitle, searchcustexpectedtitle);
				 logger.info("Navigated to customer search page");
				 test.log(LogStatus.PASS, "Navigated to customer search page");
			} catch (Exception e) {
				logger.info(e.getMessage());
				obj.Takesnap(driverqa, Config.SnapShotPath() + "/Error/Accommodation_booking_3rdparty/Search.jpg");
				test.log(LogStatus.FAIL, "Search");
				errorpath=Config.SnapShotPath() + "/Error/Accommodation_booking_3rdparty/Search.jpg";
				rep.endTest(test);
				rep.flush();
				test.log(LogStatus.FAIL, e.getMessage());
				Assert.assertTrue(false, e.getMessage());
				
			}
		     logger.info("Selecting Customer");
		     test.log(LogStatus.INFO, "Selecting Customer");
			 try {
				 wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.company));
				 driverqa.findElement(Operations.company).sendKeys(excel.getData(0, 4, 1));
				 Thread.sleep(3000);
				 action.sendKeys(Keys.ARROW_DOWN).build().perform();
				 action.sendKeys(Keys.ENTER).build().perform();
				 Thread.sleep(3000);
				 action.sendKeys(Keys.ENTER).build().perform();
				 Thread.sleep(3000);
				 
				obj.Takesnap(driverqa, Config.SnapShotPath() + "/Accommodation_booking_3rdparty/Customer-list.jpg");
				wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.chooseCust));
				driverqa.findElement(Operations.chooseCust).click();
				Thread.sleep(3000);
				String searchpageactualtitle = driverqa.getTitle();
				String searchpageexpectedtitle = "DOTWconnect.com::";
				Assert.assertEquals(searchpageactualtitle, searchpageexpectedtitle);
				logger.info("Customer Selected");
				test.log(LogStatus.PASS, "Customer Selected");
				
			 }
			 catch (Exception e) {
					logger.info(e.getMessage());
					obj.Takesnap(driverqa, Config.SnapShotPath() + "/Error/Accommodation_booking_3rdparty/Customer.jpg");
					test.log(LogStatus.FAIL, "Customer Selection");
					errorpath=Config.SnapShotPath() + "/Error/Accommodation_booking_3rdparty/Customer.jpg";
					rep.endTest(test);
					rep.flush();
					test.log(LogStatus.FAIL, e.getMessage());
					Assert.assertTrue(false, e.getMessage());
					
				}
			 logger.info("Applying search Filters");
			 try{
				 test.log(LogStatus.INFO, "Starting HotelSearch");
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.AccomUnit));
				 driverqa.findElement(NewAccoBooking.AccomUnit).sendKeys(excel.getData(0, 11, 1));
				 Thread.sleep(5000);
				 action.sendKeys(Keys.ARROW_DOWN).build().perform();
				 action.sendKeys(Keys.ENTER).build().perform();
				 
				 driverqa.findElement(NewAccoBooking.inDate).clear();
				 driverqa.findElement(NewAccoBooking.inDate).sendKeys(excel.getData(0, 11, 2));
				 Thread.sleep(3000);
				 driverqa.findElement(NewAccoBooking.outDate).clear();
				 driverqa.findElement(NewAccoBooking.outDate).sendKeys(excel.getData(0, 11, 3));
				 Thread.sleep(3000);
				 String expected=excel.getData(0, 11, 1);
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Accommodation_booking_3rdparty/Search-Hotel-filters.jpg");
				 driverqa.findElement(NewAccoBooking.bookChannel).click();
				 Thread.sleep(3000);
				 driverqa.findElement(NewAccoBooking.thirdPartyChannel).click();
				 Thread.sleep(3000);
				 driverqa.findElement(NewAccoBooking.searchButton).click();
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyresultHotel));
				 String result= driverqa.findElement(NewAccoBooking.thirdPartyresultHotel).getText();
				 Thread.sleep(2000);
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Accommodation_booking_3rdparty/Search-Result.jpg");
				 Assert.assertTrue(result.contains(expected));
				 test.log(LogStatus.INFO, "Ending HotelSearch");
				 test.log(LogStatus.PASS, "PASSED HotelSearch");
				 logger.info("Hotel Search Complete");
			} catch (Exception e) {
				logger.info(e.getMessage());
				obj.Takesnap(driverqa, Config.SnapShotPath() + "/Error/Accommodation_booking_3rdparty/Search.jpg");
				test.log(LogStatus.FAIL, "Hotel Search");
				errorpath=Config.SnapShotPath() + "/Error/Accommodation_booking_3rdparty/Search.jpg";
				rep.endTest(test);
				rep.flush();
				test.log(LogStatus.FAIL, e.getMessage());
				Assert.assertTrue(false, e.getMessage());
				
			}
			 
			 logger.info("Selecting Room Type");
	         try {
				driverqa.findElement(NewAccoBooking.thirdPartyroomType).click();
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyroomSelectionbookoutdead));
				 driverqa.findElement(NewAccoBooking.thirdPartyroomSelectionbookoutdead).click();
				 logger.info("Room Type Selected");
				 test.log(LogStatus.INFO, "Start Booking");
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Accommodation_booking_3rdparty/Selected-room-available.jpg");
				 Thread.sleep(1000);
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyprocedetoBookingoutdead));
				 driverqa.findElement(NewAccoBooking.thirdPartyprocedetoBookingoutdead).click();
				 Thread.sleep(2000);
				 logger.info("Entering Passenger details");
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.paxFname));
				 driverqa.findElement(NewAccoBooking.paxFname).sendKeys(excel.getData(0, 20, 1));
				 Thread.sleep(2000);
				 driverqa.findElement(NewAccoBooking.paxLname).sendKeys(excel.getData(0, 20, 2));
				 Select passengertitle1 = new Select(driverqa.findElement(NewAccoBooking.paxtitle));
				 passengertitle1.selectByIndex(1);
				 driverqa.findElement(NewAccoBooking.paxFname2).sendKeys(excel.getData(0, 21, 1));
				 Thread.sleep(2000);
				 driverqa.findElement(NewAccoBooking.paxLname2).sendKeys(excel.getData(0, 21, 2));
				 Select passengertitle2 = new Select(driverqa.findElement(NewAccoBooking.paxtitle2));
				 passengertitle2.selectByIndex(1);
				 driverqa.findElement(NewAccoBooking.acceptChkBX).click();
				 Thread.sleep(3000);
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Accommodation_booking_3rdparty/Passenger_Details.jpg");
				 driverqa.findElement(NewAccoBooking.bookBtn).click();
				 logger.info("Entered Passenger details");
				 Thread.sleep(4000);
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Accommodation_booking_3rdparty/Confirm-Booking.jpg");
				 /* wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.confirmBook));
				
				 wait.until(ExpectedConditions.visibilityOfElementLocated(PaymentPage.ccType));
				 Select paymentmode = new Select(driverqa.findElement(PaymentPage.ccType));
				 paymentmode.selectByIndex(1);
				 driverqa.findElement(PaymentPage.ccNum).sendKeys(excel.getData(4, 18, 0));
				 driverqa.findElement(PaymentPage.cvv).sendKeys(excel.getData(4, 18, 1));
				 driverqa.findElement(PaymentPage.ccName).sendKeys(excel.getData(4, 18, 2));
				 Select expirydate = new Select(driverqa.findElement(PaymentPage.expYear));
				 expirydate.selectByIndex(2);
				 driverqa.findElement(PaymentPage.zip).sendKeys(excel.getData(4, 14, 2));
				 driverqa.findElement(PaymentPage.termsconditions).click();
				 Thread.sleep(2000);
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Accommodation_booking_3rdparty/Card-Details.jpg");
				 driverqa.findElement(PaymentPage.confirmPayment).click();
				 logger.info("Completed Payment Details");
				 test.log(LogStatus.PASS, "Completed Payment Details");*/
				 Thread.sleep(2000);
				 wait.until(ExpectedConditions.visibilityOfElementLocated(PaymentPage.confirmationStatus));
				 String actualbookingstatus= driverqa.findElement(PaymentPage.confirmationStatus).getText();
				 String expectedbookingstatus="Confirmed";
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Accommodation_booking_3rdparty/After-booking.jpg");
				 Assert.assertTrue(actualbookingstatus.contains(expectedbookingstatus));
				 test.log(LogStatus.INFO, "Ending HotelBook");
				 test.log(LogStatus.PASS, "PASSED HotelBook");
				 logger.info("Hotel Book Complete");
			} catch (Exception e) {
				logger.info(e.getMessage());
				obj.Takesnap(driverqa, Config.SnapShotPath() + "/Error/Accommodation_booking_3rdparty/Book.jpg");
				test.log(LogStatus.FAIL, "Hotel Book");
				errorpath=Config.SnapShotPath() + "/Error/Accommodation_booking_3rdparty/Book.jpg";
				rep.endTest(test);
				rep.flush();
				//test.log(LogStatus.FAIL, e.getMessage());
				Assert.assertTrue(false, e.getMessage());
				

			}
  }
 
  @AfterMethod
	public void getResult(ITestResult result) {
	  if (result.getStatus() == ITestResult.FAILURE) {
			 
			
			test.log(LogStatus.FAIL, test.addScreenCapture(errorpath));
			  test.log(LogStatus.FAIL, result.getThrowable());
			  }
			  rep.endTest(test);
	}

	@AfterTest
	public void afterTest() {

		rep.endTest(test);
		rep.flush();
		//driverqa.close();
	}
 }
