package Transfer;

import java.io.IOException;

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
import org.testng.annotations.AfterClass;
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

public class Transfer_Booking_Transfertype_Scheduled {
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
		} catch (Throwable e) {
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
		} catch (Throwable e) {
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
					 driverqa.findElement(Operations.company).sendKeys(excel.getData(0, 4, 1));
					 Thread.sleep(1000);
					 action.sendKeys(Keys.ARROW_DOWN).build().perform();
					 action.sendKeys(Keys.ENTER).build().perform();
					 Thread.sleep(2000);
					 action.sendKeys(Keys.ENTER).build().perform();
					 Thread.sleep(2000);
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Transfer/Transfer_booking_for_adults_with_children/Customer-list-transfer-book.jpg");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.chooseCust));
					 driverqa.findElement(Operations.chooseCust).click();
					Thread.sleep(1000);
					String searchpageactualtitle = driverqa.getTitle();
					String searchpageexpectedtitle = "DOTWconnect.com::";
					Assert.assertEquals(searchpageactualtitle, searchpageexpectedtitle);
					logger.info("Customer Selected");
					test.log(LogStatus.PASS, "Customer Selected");
				 }
				 catch (Throwable e) {
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
					 driverqa.findElement(NewAccoBooking.transferName).sendKeys(excel.getData(0, 32, 1));
					 Thread.sleep(2000);
					 action.sendKeys(Keys.ARROW_DOWN).build().perform();
					 action.sendKeys(Keys.ENTER).build().perform();
					 Select transferTime= new Select(driverqa.findElement(NewAccoBooking.transferTime));
					 transferTime.selectByIndex(9);
					 driverqa.findElement(NewAccoBooking.transferDate).clear();
					 driverqa.findElement(NewAccoBooking.transferDate).sendKeys(excel.getData(0, 32, 2));
					 action.sendKeys(Keys.ENTER).build().perform();
					 Select transferType = new Select(driverqa.findElement(NewAccoBooking.transferType));
					 transferType.selectByIndex(1);
					 Select pickUpFrom = new Select(driverqa.findElement(NewAccoBooking.pickUpFrom));
					 pickUpFrom.selectByIndex(0);
					 Select dropOff = new Select(driverqa.findElement(NewAccoBooking.dropOffTo));
					 dropOff.selectByIndex(0);
					 String expected=excel.getData(0, 32, 1);
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
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Transfer/Transfer_booking_for_adults_with_children/Search-Result-book-hotel.jpg");
					 Assert.assertTrue(result.contains(expected));
					 test.log(LogStatus.INFO, "Ending TransferSearch");
					 test.log(LogStatus.PASS, "PASSED TransferSearch");
					 logger.info("Transfer Search Complete");
					 rep.endTest(test);
					 rep.flush();
				} catch (Throwable e) {
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
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.transferRadioScheduled));
					 driverqa.findElement(NewAccoBooking.transferRadioScheduled).click();
					 logger.info("Transfer Type Selected");
					 test.log(LogStatus.INFO, "Start Booking");
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Transfer_booking_for_adults_with_children/Selected-transfer type-available-book-transfer.jpg");
					 Thread.sleep(1000);
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.transProceedBook));
					 driverqa.findElement(NewAccoBooking.transProceedBook).click();
					 Thread.sleep(2000);
					 logger.info("Entering First Passenger details");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.transferFrstPaxFname));
					 driverqa.findElement(NewAccoBooking.transferFrstPaxFname).sendKeys(excel.getData(0, 21, 1));
					 Thread.sleep(2000);
					 driverqa.findElement(NewAccoBooking.transferFrstPaxLname).sendKeys(excel.getData(0, 21, 2));
					 Select firstPassengertitle = new Select(driverqa.findElement(NewAccoBooking.transferFrstPaxTitle));
					 firstPassengertitle.selectByIndex(1);
					 logger.info("Initiating Pick Up From Airport");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.transferPickUp));
					 driverqa.findElement(NewAccoBooking.transferPickUp).sendKeys(excel.getData(0, 32, 4));
					 Thread.sleep(2000);
					 driverqa.findElement(NewAccoBooking.transDropOff).sendKeys(excel.getData(0, 32, 5));
					 Thread.sleep(3000);
					 JavascriptExecutor js = (JavascriptExecutor)driverqa;
					 js.executeScript("window.scrollBy(0,250)", "");
					 obj.Takesnap(driverqa, "D:/Test Repository/Test Screenshots/Transfer_booking_for_adults/Passenger-Details-transferScheduled-book.jpg");
					 driverqa.findElement(NewAccoBooking.bookBtn).click();
					 logger.info("Entered Passenger details");
					 Thread.sleep(4000);
					 JavascriptExecutor jsd = (JavascriptExecutor)driverqa;
					 jsd.executeScript("window.scrollBy(0,250)", "");
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Transfer_booking_for_adults/Confirm-Booking-transfer-book_Scheduled.jpg");
					 test.log(LogStatus.INFO, "Ending TransferBookScheduled");
					 test.log(LogStatus.PASS, "PASSED TransferBookScheduled");
					 logger.info("Transfer_Scheduled Book Complete");
					
				} catch (Throwable e) {
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

		@AfterTest
		public void afterTest() {

			rep.endTest(test);
			rep.flush();
			//driverqa.close();
		}
		
		 
}
