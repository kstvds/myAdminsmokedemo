package Tour;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
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
import ObjectRepository.NewTourBooking;
import ObjectRepository.Operations;
import Utility.Configuration;
import lib.DriverAndObjectDetails;
import lib.ExcelDataConfig;
import lib.ExtentManager;
import lib.Takescreenshot;
import lib.DriverAndObjectDetails.DriverName;

public class Tour_Booking {
	Configuration Config = new Configuration();
	Takescreenshot obj = new Takescreenshot();
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	//NewTourBooking acco = new NewTourBooking();
	NewTourBooking tour = new NewTourBooking();
	Operations opo = new Operations();
	WebDriver driverqa;
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	Logger logger = Logger.getLogger("Tour_Booking");

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
	public void booktour() throws Exception {
		Actions action;
		action = new Actions(driverqa);
		WebDriverWait wait;
		ExcelDataConfig excel;
		wait = new WebDriverWait(driverqa, 30);
		excel = new ExcelDataConfig(Config.getExcelPathTourVisa());
		test = rep.startTest("booktour");
		try {

			test.log(LogStatus.INFO, "Navigating to customer search page");
			logger.info("Navigating to customer search page");
			driverqa.findElement(HomePage.operation).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.newBooking));
			driverqa.findElement(Operations.newBooking).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.TourBook));
			driverqa.findElement(Operations.TourBook).click();
			Thread.sleep(2000);
			String searchcustatualtitle = driverqa.getTitle();
			String searchcustexpectedtitle = "DOTWconnect.com::New Tour Booking";
			Assert.assertEquals(searchcustatualtitle, searchcustexpectedtitle);
			logger.info("Navigated to customer search page");
			test.log(LogStatus.PASS, "Customer Search Page");
		} catch (Exception e) {
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
		}
		logger.info("Searching Customer");

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.city));
			driverqa.findElement(Operations.city).sendKeys(excel.getData(0, 4, 0));
			Thread.sleep(1000);
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath() + "/Tour/Tour_book/Customer-filter-search-hotel.jpg");
			action.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath() + "/Tour/Tour_book/Customer-list-search-hotel.jpg");
			wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.chooseCust));
			logger.info("Selecting Customer");
			driverqa.findElement(Operations.chooseCust).click();
			Thread.sleep(1000);
			String searchpageactualtitle = driverqa.getTitle();
			String searchpageexpectedtitle = "DOTWconnect.com::";
			Assert.assertEquals(searchpageactualtitle, searchpageexpectedtitle);
			logger.info("Customer Selected");
			test.log(LogStatus.PASS, "Customer Selected");

		} catch (Exception e) {
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
		}

		logger.info("Applying search Filters");

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewTourBooking.destCity));
			driverqa.findElement(NewTourBooking.TourName).sendKeys(excel.getData(2, 1, 0));
			Thread.sleep(2000);
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(2000);
			driverqa.findElement(NewTourBooking.TourDate).clear();
			driverqa.findElement(NewTourBooking.TourDate).sendKeys(excel.getData(2, 4, 0));
			Thread.sleep(2000);
			obj.Takesnap(driverqa,Config.SnapShotPath() + "/Tour/Tour_book/Filter_Tour.jpg");
			driverqa.findElement(NewTourBooking.SearchButton).click();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(NewTourBooking.SearchResults));
			/*String result = driverqa.findElement(NewTourBooking.TourNameRes).getText();
			System.out.println(result);
			String expected = excel.getData(2, 1, 0);
			String expected = "CR-CLASSIC DINNER CRUISE (MIDWEEK)";
			System.out.println(expected);*/
			Thread.sleep(2000);
			obj.Takesnap(driverqa,Config.SnapShotPath() + "/Tour/Tour_book/search-tour.jpg");
			Thread.sleep(1000);
			driverqa.findElement(NewTourBooking.GetTourButton).click();
			Thread.sleep(1000);
			driverqa.findElement(NewTourBooking.TourCheckbox).click();
			driverqa.findElement(NewTourBooking.ProceedBooking).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewTourBooking.Title));
			Select title= new Select(driverqa.findElement(NewTourBooking.Title));
			title.selectByVisibleText(excel.getData(2, 7, 0));
			driverqa.findElement(NewTourBooking.PAX_FirstName).sendKeys(excel.getData(2, 7, 2));
			driverqa.findElement(NewTourBooking.PAX_LastName).sendKeys(excel.getData(2, 7, 4));
			driverqa.findElement(NewTourBooking.Pickup).sendKeys(excel.getData(2, 7, 6));
			driverqa.findElement(NewTourBooking.BookTour).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewTourBooking.TourBookingStatus));
			String BookingStatus = driverqa.findElement(NewTourBooking.TourBookingStatus).getText();
			System.out.println(BookingStatus);
			String TourID = driverqa.findElement(NewTourBooking.TourID).getText();
			excel.putData(4, 1, 0, TourID);
			obj.Takesnap(driverqa,Config.SnapShotPath() + "/Tour/Tour_book/Booking Status.jpg");
		//String ExpectedStatus=excel.getData(2, 10, 0);
		//	Assert.assertTrue(BookingDetails.contains(ExpectedStatus));
			//Assert.assertTrue(true);
			test.log(LogStatus.INFO, "Ending TourBook");
			test.log(LogStatus.PASS, "PASSED TourBook");
			logger.info("Tour Book Complete");
		} catch (Exception e) {
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
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
}
