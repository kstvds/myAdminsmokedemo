package customers;

import lib.Takescreenshot;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ObjectRepository.Cus_LoginPage;
import Utility.Configuration;
import lib.DriverAndObjectDetails;
import lib.DriverAndObjectDetails.DriverName;
import lib.ExcelDataConfig;
//import lib.ExtentManager;
import lib.ExtentManager;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

/*import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;*/

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.NewAccoBooking;
import ObjectRepository.Operations;
import ObjectRepository.PaymentPage;
import ObjectRepository.Cus_LoginPage;
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

public class Filter_customer {
	Configuration Config = new Configuration();
	Takescreenshot obj = new Takescreenshot();
	Cus_LoginPage login = new Cus_LoginPage();
	//HomePage home = new HomePage();
	//NewAccoBooking acco= new NewAccoBooking();
	//Operations opo = new Operations();
	WebDriver driverqa;
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	Logger logger = Logger.getLogger("Filter Customer ");

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
			test = rep.startTest("login");
			test.log(LogStatus.INFO, "Starting LoginTest");
			WebElement username = driverqa.findElement(Cus_LoginPage.uname);
			username.clear();
			username.sendKeys(excel.getData(4, 1, 0));
			WebElement password = driverqa.findElement(Cus_LoginPage.pwd);
			password.clear();
			password.sendKeys(excel.getData(4, 1, 1));
			driverqa.findElement(LoginPage.submit).click();

			//System.out.println("dew");
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
		}
		logger.info(" Live Customer");
		try {
			//System.out.println("HI");
			test.log(LogStatus.INFO, "Navigating to customer search page");
			logger.info("Navigating to customer search page");
			driverqa.findElement(Cus_LoginPage.cust).click();
			Thread.sleep(2000);
			driverqa.findElement(Cus_LoginPage.live_cust).click();
			Thread.sleep(2000);
			String searchcustatualtitle = driverqa.getTitle();
			String searchcustexpectedtitle = "DOTWconnect.com::Live customers";
			Assert.assertEquals(searchcustatualtitle, searchcustexpectedtitle);
			logger.info("Navigated to customer filter page");
			test.log(LogStatus.PASS, "Customer filter Page");
			

		} catch (Exception e) {
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());
		}
		logger.info("Applying filter");
		try {
			driverqa.findElement(Cus_LoginPage.simple_filter).click();
			Thread.sleep(2000);ExcelDataConfig excel = new ExcelDataConfig(Config.getExcelPath());
			WebElement Companyname= driverqa.findElement(Cus_LoginPage.company_name);
			Companyname.clear();
			System.out.println(1);
			
			Companyname.sendKeys(excel.getData(4,4,0));
			System.out.println(2);
			driverqa.findElement(Cus_LoginPage.filter_cus).click();
			Thread.sleep(2000);
			driverqa.findElement(Cus_LoginPage.edit).click();
			Thread.sleep(2000);
			String actual= driverqa.findElement(Cus_LoginPage.check).getAttribute("value");
			
			Assert.assertEquals(actual,Companyname);
			logger.info("Navigated to mentioned customer page");
			test.log(LogStatus.PASS, "Customer Page");
			

		} catch (Exception e) {
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());
		}
		
	}
}
