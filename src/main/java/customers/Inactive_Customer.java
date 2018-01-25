package customers;

import lib.Takescreenshot;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ObjectRepository.Ci_Register;
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
import ObjectRepository.Inactive_cust_obj;
import ObjectRepository.LoginPage;
import ObjectRepository.NewAccoBooking;
import ObjectRepository.Operations;
import ObjectRepository.PaymentPage;
import ObjectRepository.Cus_LoginPage;
import Utility.Configuration;

import org.testng.annotations.Parameters;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
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

public class Inactive_Customer {
	Configuration Config = new Configuration();
	Takescreenshot obj = new Takescreenshot();
	Cus_LoginPage login = new Cus_LoginPage();
	// HomePage home = new HomePage();
	// NewAccoBooking acco= new NewAccoBooking();
	// Operations opo = new Operations();
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
			
			//driverqa.get(Config.getApplicationUrl1());
			
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
			driverqa.findElement(Inactive_cust_obj.cust).click();
			Thread.sleep(2000);
			driverqa.findElement(Inactive_cust_obj.live_cust).click();
			Thread.sleep(2000);
			String searchcustatualtitle = driverqa.getTitle();
			String searchcustexpectedtitle = "DOTWconnect.com::Live customers";
			Assert.assertEquals(searchcustatualtitle, searchcustexpectedtitle);
			logger.info("Navigated to customer filter page");
			test.log(LogStatus.PASS, "Customer filter Page");
			// Thread.sleep(2000);
			/*
			 * driverqa.findElement(Cus_LoginPage.simple_filter).click();
			 * Thread.sleep(2000); //*[@id="companyName"]
			 * driverqa.findElement(Cus_LoginPage.company_name).
			 * sendKeys("FAYEDA TRAVEL & TOURISM - MUSCAT");
			 * driverqa.findElement(Cus_LoginPage.filter_cus).click(); String
			 * searchcustatualtitle = driverqa.getTitle(); String
			 * searchcustexpectedtitle =
			 * "DOTWconnect.com::New Accommodation Booking";
			 * Assert.assertEquals(searchcustatualtitle,
			 * searchcustexpectedtitle);
			 * logger.info("Navigated to customer filter page");
			 * test.log(LogStatus.PASS, "Customer Search Page");
			 */

		} catch (Exception e) {
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());
		}
		logger.info("Applying filter");
		try {
			driverqa.findElement(Inactive_cust_obj.simple_filter).click();
			Thread.sleep(2000);
			// *[@id="companyName"]
			ExcelDataConfig excel = new ExcelDataConfig(Config.getExcelPath());
			WebElement Companyname= driverqa.findElement(Inactive_cust_obj.company_name);
			Companyname.clear();
			System.out.println(1);
			
			Companyname.sendKeys(excel.getData(4,4,0));
			System.out.println(2);
			driverqa.findElement(Inactive_cust_obj.filter_cus).click();
			Thread.sleep(2000);
			driverqa.findElement(Inactive_cust_obj.edit).click();
			Thread.sleep(2000);
			driverqa.findElement(Inactive_cust_obj.inactive).click();
			//driverqa.getPageSource().contains("ddd");
			WebElement InactiveReason=driverqa.findElement(Inactive_cust_obj.InactiveCheck);
			InactiveReason.clear();
			InactiveReason.sendKeys(excel.getData(4,7,0));
			Thread.sleep(1000);
			System.out.println(3);
			driverqa.findElement(Inactive_cust_obj.update).click();
			//System.out.println(b);
			   Thread.sleep(2000);
			   driverqa.findElement(Inactive_cust_obj.confirm).click();
			   
			
			String actual= driverqa.findElement(Cus_LoginPage.check).getAttribute("value");
			//String expected="FAYEDA TRAVEL & TOURISM - MUSCAT";
			
			//ExcelDataConfig excel = new ExcelDataConfig(Config.getExcelPath());
			//Customer name.sendKeys(excel.getData(4, 1, 1));
			//Assert.assertEquals(actual,Companyname);
		//	logger.info("Navigated to mentioned customer page");
		//	test.log(LogStatus.PASS, "Customer Page");
			
			//System.out.println(expected);
	    	//String expected= 
			/*String searchcustatualtitle = driverqa.getTitle();
			String searchcustexpectedtitle = "DOTWconnect.com::Customer";
			Assert.assertEquals(searchcustatualtitle, searchcustexpectedtitle);
			logger.info("Navigated to mentioned customer page");
			test.log(LogStatus.PASS, "Customer Page");*/

		} catch (Exception e) {
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());
		}
		PropertyConfigurator.configure("Log4j.properties");
		logger.info(" CI logIn Page");

		try {
			if (browsername.equalsIgnoreCase("CH")) {
				System.out.println("hi");

				driverqa = new DriverAndObjectDetails(DriverName.CH).CreateDriver();
			} else if (browsername.equalsIgnoreCase("IE")) {
				driverqa = new DriverAndObjectDetails(DriverName.IE).CreateDriver();
			} else {
				driverqa = new DriverAndObjectDetails(DriverName.FF).CreateDriver();
			}
			rep.startTest("Extranet");
			logger.info("Browser Opened");
			driverqa.get(Config.getApplicationUrl1());
			logger.info("Test Case Started");
			ExcelDataConfig excel = new ExcelDataConfig(Config.getExcelPath());
			test = rep.startTest("Extranet_LoginTest");
			test.log(LogStatus.INFO, "Starting Extranet_LoginTest");
			WebElement username = driverqa.findElement(Inactive_cust_obj.uname);
			username.clear();
			username.sendKeys(excel.getData(2, 1, 0));
			WebElement password = driverqa.findElement(Inactive_cust_obj.pwd);
			password.clear();
			password.sendKeys(excel.getData(2, 1, 1));
			driverqa.findElement(Inactive_cust_obj.submit).click();
			Thread.sleep(1000);
			
			String expectedtitle = "DOTWconnect.com::DOTWconnect.com: Extranet";
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
		try
		{
			ExcelDataConfig excel = new ExcelDataConfig(Config.getExcelPath());
			WebElement CIUsername = driverqa.findElement(Inactive_cust_obj.CIuser);
			CIUsername.clear();
			CIUsername.sendKeys(excel.getData(4,10,0));
			Thread.sleep(2000);
			WebElement CIPassword = driverqa.findElement(Inactive_cust_obj.CIpass);
			CIPassword.clear();
			CIPassword.sendKeys(excel.getData(4,10,1));
			Thread.sleep(2000);
			WebElement  Company_Code= driverqa.findElement(Inactive_cust_obj.companycode);
			Company_Code.clear();
			Company_Code.sendKeys(excel.getData(4,10,1));
			driverqa.findElement(Inactive_cust_obj.CIlogin).click();
			
			
		}
		catch (Exception e) {
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			
}
		
		}
		
		  @AfterMethod 
		  public void getResult(ITestResult result) { if
		  (result.getStatus() == ITestResult.FAILURE) {
		 test.log(LogStatus.FAIL, result.getThrowable()); } rep.endTest(test);
		  }
		  
		  @AfterTest public void afterTest1() {
		  
		  rep.endTest(test); rep.flush(); driverqa.close(); }
		
	}

