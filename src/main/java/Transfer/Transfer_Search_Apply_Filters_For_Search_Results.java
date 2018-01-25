package Transfer;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import ObjectRepository.PaymentPage;
import Utility.Configuration;
import lib.DriverAndObjectDetails;
import lib.ExcelDataConfig;
import lib.ExtentManager;
import lib.Takescreenshot;
import lib.DriverAndObjectDetails.DriverName;

public class Transfer_Search_Apply_Filters_For_Search_Results {
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
					 
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Transfer/Transfer_booking_for_adults_with_children/Customer-list-book-hotel.jpg");
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
					
					 /*driver.findElement(NewAccoBooking.destCity).sendKeys(excel.getData(1, 7, 0));
					 Thread.sleep(2000);
					 action.sendKeys(Keys.ARROW_DOWN).build().perform();
					 action.sendKeys(Keys.ENTER).build().perform();*/
					 driverqa.findElement(NewAccoBooking.transferName).sendKeys(excel.getData(1, 10, 0));
					 Thread.sleep(2000);
					 action.sendKeys(Keys.ARROW_DOWN).build().perform();
					 action.sendKeys(Keys.ENTER).build().perform();
					 Select transferTime= new Select(driverqa.findElement(NewAccoBooking.transferTime));
					 transferTime.selectByIndex(9);
					 driverqa.findElement(NewAccoBooking.transferDate).clear();
					 driverqa.findElement(NewAccoBooking.transferDate).sendKeys(excel.getData(1, 13, 0));
					// action.sendKeys(Keys.ARROW_DOWN).build().perform();
					 action.sendKeys(Keys.ENTER).build().perform();
					/* driver.findElement(NewAccoBooking.outDate).clear();
					 driver.findElement(NewAccoBooking.outDate).sendKeys(excel.getData(1, 13, 1));*/
					 String expected=excel.getData(1, 10, 0);
					 Select noofchild = new Select(driverqa.findElement(NewAccoBooking.transferBookingChild));
					 noofchild.selectByIndex(1);
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.transferBookingChildAge));
					 Select childage = new Select(driverqa.findElement(NewAccoBooking.transferBookingChildAge));
					 childage.selectByIndex(5);
					 //driver.findElement(NewAccoBooking.allcheckbox).click();
					 Thread.sleep(2000);
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Transfer/Transfer_booking_for_adults_with_children/Search-Hotel-filters-book-hotel.jpg");
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

		@AfterTest
		public void afterTest() {

			rep.endTest(test);
			rep.flush();
			driverqa.close();
		}
		
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
