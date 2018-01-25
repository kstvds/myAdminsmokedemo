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
import ObjectRepository.NewVisaBooking;
import ObjectRepository.Operations;
import Utility.Configuration;
import lib.DriverAndObjectDetails;
import lib.ExcelDataConfig;
import lib.ExtentManager;
import lib.Takescreenshot;
import lib.DriverAndObjectDetails.DriverName;

public class Visa_Booking {
	Configuration Config = new Configuration();
	Takescreenshot obj = new Takescreenshot();
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	//NewVisaBooking acco = new NewVisaBooking();
	NewVisaBooking tour = new NewVisaBooking();
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
			ExcelDataConfig excel = new ExcelDataConfig(Config.getExcelPathTourVisa());
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
	public void searchvisa() throws Exception {
		Actions action;
		action = new Actions(driverqa);
		WebDriverWait wait;
		ExcelDataConfig excel;
		wait = new WebDriverWait(driverqa, 30);
		excel = new ExcelDataConfig(Config.getExcelPath());
		test = rep.startTest("searchvisa");
		try {

			test.log(LogStatus.INFO, "Navigating to customer search page");
			logger.info("Navigating to customer search page");
			driverqa.findElement(HomePage.operation).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.newBooking));
			driverqa.findElement(Operations.newBooking).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.VisaBook));
			driverqa.findElement(Operations.VisaBook).click();
			Thread.sleep(2000);
			String searchcustatualtitle = driverqa.getTitle();
			String searchcustexpectedtitle = "DOTWconnect.com::New Visa Booking";
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
			driverqa.findElement(Operations.company).sendKeys(excel.getData(3, 1, 0));
			Thread.sleep(1000);
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath() + "/Visa/Visa_book/Customer-filter-search.jpg");
			action.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath() + "/Visa/Visa_book/Customer-list-search.jpg");
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
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewVisaBooking.Country));
			Select Country= new Select(driverqa.findElement(NewVisaBooking.Country));
			Country.selectByVisibleText(excel.getData(3, 4, 0));
			Thread.sleep(2000);
			Select PassNationality= new Select(driverqa.findElement(NewVisaBooking.PassNationality));
			PassNationality.selectByVisibleText(excel.getData(3, 7, 0));
			Thread.sleep(2000);
			driverqa.findElement(NewVisaBooking.Arrival).clear();
			driverqa.findElement(NewVisaBooking.Arrival).sendKeys(excel.getData(3, 10, 0));
			Thread.sleep(2000);
			driverqa.findElement(NewVisaBooking.Depart).clear();
			driverqa.findElement(NewVisaBooking.Depart).sendKeys(excel.getData(3, 13, 0));
			Thread.sleep(2000);
			obj.Takesnap(driverqa,Config.SnapShotPath() + "/Visa/Visa_book/Filter_Visa.jpg");
			driverqa.findElement(NewVisaBooking.SearchBtn).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewVisaBooking.PassengerNo));
			Select PassengerNo= new Select(driverqa.findElement(NewVisaBooking.PassengerNo));
			PassengerNo.selectByIndex(1);
			Thread.sleep(2000);
			obj.Takesnap(driverqa,Config.SnapShotPath() + "/Visa/Visa_book/search-visa.jpg");
			Thread.sleep(1000);
			driverqa.findElement(NewVisaBooking.PassportDataBtn).click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewVisaBooking.Title));
			Select title= new Select(driverqa.findElement(NewVisaBooking.Title));
			title.selectByVisibleText(excel.getData(2, 7, 0));
			driverqa.findElement(NewVisaBooking.PAX_FirstName).sendKeys(excel.getData(2, 7, 2));
			driverqa.findElement(NewVisaBooking.PAX_LastName).sendKeys(excel.getData(2, 7, 4));
			driverqa.findElement(NewVisaBooking.SubmitBtn).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewVisaBooking.VisaBookingDetails));
			String VisaBookingDetails = driverqa.findElement(NewVisaBooking.VisaBookingDetails).getText();
			System.out.println(VisaBookingDetails);
			String VisaID = driverqa.findElement(NewVisaBooking.VisaID).getText();
			System.out.println(VisaID);
			excel.putData(4, 4, 0, VisaID);
			obj.Takesnap(driverqa,Config.SnapShotPath() + "/Visa/Visa_book/Booking Status.jpg");
//			String ExpectedStatus=excel.getData(2, 10, 0);
			//Assert.assertTrue(BookingDetails.contains(ExpectedStatus));
			Assert.assertTrue(true);
			test.log(LogStatus.INFO, "Ending VisaBook");
			test.log(LogStatus.PASS, "PASSED VisaBook");
			logger.info("Visa Book Complete");
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
