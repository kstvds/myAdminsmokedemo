package Rates;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.support.events.EventFiringWebDriver;
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

import ObjectRepository.AddRate;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.NewAccoBooking;
import ObjectRepository.Operations;
import ObjectRepository.RateFilter;
import Utility.Configuration;
import lib.DriverAndObjectDetails;
import lib.ExcelDataConfig;
import lib.ExtentManager;
import lib.Takescreenshot;
import lib.DriverAndObjectDetails.DriverName;

public class Accomodation_Filterrates_Inactivaterate {
	public WebDriver driverqa;
	Configuration Config = new Configuration();
	Takescreenshot obj= new Takescreenshot();
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	NewAccoBooking acco = new NewAccoBooking();
	Operations opo = new Operations();
	Logger logger = Logger.getLogger("Accomodation_Filterrates_Inactivaterate");
	
	 @Test
	 @Parameters({ "browsername" })
	  public void inactivaterate(String browsername) throws Exception {
		  test = rep.startTest("inactivaterate");
		  ExcelDataConfig excel;
		  excel = new ExcelDataConfig(Config.getExcelPathRate());
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
				username.sendKeys(excel.getData(2, 1, 0));
				WebElement password = driverqa.findElement(LoginPage.pwd);
				password.clear();
				password.sendKeys(excel.getData(2, 1, 1));
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
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());
			test.log(LogStatus.FAIL, "Login");
		}
	           try {
				logger.info("Searching Accomodation"); 
				   test.log(LogStatus.INFO, "Navigating to Accomodation search page");
					logger.info("Navigating to Accomodation search page");
					 driverqa.findElement(RateFilter.prod).click();
					 wait.until(ExpectedConditions.visibilityOfElementLocated(RateFilter.accomodation));
					 driverqa.findElement(RateFilter.accomodation).click();
					 wait.until(ExpectedConditions.visibilityOfElementLocated(RateFilter.rateninv));
					 driverqa.findElement(RateFilter.rateninv).click();
					 Thread.sleep(2000);
					 String searchaccomatualtitle = driverqa.getTitle();
					 String searchaccomexpectedtitle = "DOTWconnect.com::Filter Accommodation Units";
					 Assert.assertEquals(searchaccomatualtitle, searchaccomexpectedtitle);
					 logger.info("Navigated to Accomodation search page");
					 test.log(LogStatus.PASS, "Navigated to Accomodation search page");
			} catch (Exception e) {
				logger.info(e.getMessage());
				test.log(LogStatus.FAIL, e.getMessage());
				rep.endTest(test);
				rep.flush();
				Assert.assertTrue(false, e.getMessage());
				test.log(LogStatus.FAIL, "Navigating to Accomodation search page");
				System.out.println(e.getMessage());
			}
	           try {
				Thread.sleep(2000);
				    obj.Takesnap(driverqa, Config.SnapShotPath() + "/Rates/Accomodation_Filterrates_Inactivaterate/Rate-filter-screen.jpg");
				     logger.info("Selecting Accomodation Unit");
				     test.log(LogStatus.INFO, "Selecting Accomodation Unit");
				     wait.until(ExpectedConditions.visibilityOfElementLocated(RateFilter.accomname));
					 driverqa.findElement(RateFilter.accomname).sendKeys(excel.getData(2, 10, 0));
					 Thread.sleep(3000);
					 action.sendKeys(Keys.ARROW_DOWN).build().perform();
					 action.sendKeys(Keys.ENTER).build().perform();
					 Thread.sleep(3000);
					 driverqa.findElement(RateFilter.filter).click();
					 Thread.sleep(2000);
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Rates/Accomodation_Filterrates_Inactivaterate/Rate-filter-screen.jpg");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(RateFilter.contractvalidity));
					 String filterratescreenactualtitle = driverqa.getTitle();
					 String filterratescreenexpectedtitle = "DOTWconnect.com::Rate Filter";
					 Assert.assertEquals(filterratescreenactualtitle, filterratescreenexpectedtitle);
					 logger.info("Accomodation Unit Selected");
					 test.log(LogStatus.PASS, "Accomodation Unit Selected");
			} catch (Exception e) {
				logger.info(e.getMessage());
				test.log(LogStatus.FAIL, e.getMessage());
				rep.endTest(test);
				rep.flush();
				Assert.assertTrue(false, e.getMessage());
				test.log(LogStatus.FAIL, "Selecting Accomodation Unit");
				System.out.println(e.getMessage());
			}
	           try {
				logger.info("Navigating to ContractList");
				    test.log(LogStatus.INFO, "Navigating to ContractList");
				   wait.until(ExpectedConditions.visibilityOfElementLocated(RateFilter.filterrates));
				   obj.Takesnap(driverqa, Config.SnapShotPath() + "/Rates/Accomodation_Filterrates_Inactivaterate/Final-Rate-filter-screen.jpg");
				   driverqa.findElement(RateFilter.filterrates).click();
				   wait.until(ExpectedConditions.visibilityOfElementLocated(RateFilter.inactiveRate));
				   driverqa.findElement(RateFilter.inactiveRate).click();
				   Thread.sleep(3000);
				   JavascriptExecutor jse = (JavascriptExecutor) driverqa;
					// Scroll vertically downward by 250 pixels
					jse.executeScript("window.scrollBy(0,250)", "");
					wait.until(ExpectedConditions.visibilityOfElementLocated(RateFilter.inactiveRateBttn));
					   driverqa.findElement(RateFilter.inactiveRateBttn).click();
					   String expected="The rate was saved as draft successfully";
					   Thread.sleep(2000);
					   wait.until(ExpectedConditions.visibilityOfElementLocated(RateFilter.info));
					    String  actualresult= driverqa.findElement(RateFilter.info).getText();
					   Thread.sleep(5000);
					   System.out.println(actualresult);
					   Thread.sleep(2000);
				   obj.Takesnap(driverqa, Config.SnapShotPath() + "/Rates/Accomodation_Filterrates_Inactivaterate/Contract-List-screen.jpg");
					 Assert.assertTrue(actualresult.contains(expected));
					 logger.info("Rate Inactivated");
					 test.log(LogStatus.PASS, "Rate Inactivated");
			} catch (Exception e) {
				logger.info(e.getMessage());
				test.log(LogStatus.FAIL, e.getMessage());
				rep.endTest(test);
				rep.flush();
				Assert.assertTrue(false, e.getMessage());
				test.log(LogStatus.FAIL, "Rate Inactivated");
				
			}
}
	 @AfterMethod
		public void getResult(ITestResult result) {
			if (result.getStatus() == ITestResult.FAILURE) {
				test.log(LogStatus.FAIL, result.getThrowable());
			}
			//rep.endTest(test);
		}

		@AfterTest
		public void afterTest() {

			rep.endTest(test);
			rep.flush();
			//driverqa.close();
		}

}
