package customers;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ObjectRepository.Ci_Register;
import ObjectRepository.Cus_LoginPage;
import ObjectRepository.LoginPage;
import Utility.Configuration;
import lib.DriverAndObjectDetails;
import lib.ExcelDataConfig;
import lib.ExtentManager;
import lib.Takescreenshot;
import lib.DriverAndObjectDetails.DriverName;

public class Register_customer {
	public WebDriver driverqa;
	Configuration Config = new Configuration();
	Takescreenshot obj = new Takescreenshot();

	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	Logger logger = Logger.getLogger("Registering_new_customer");
	
	

	@Test(priority='0')
	@Parameters({ "browsername" })
	public void login(String browsername) throws Exception {
		PropertyConfigurator.configure("Log4j.properties");

		try {
			if (browsername.equalsIgnoreCase("CH")) {

				new DriverAndObjectDetails(DriverName.CH);
				driverqa = DriverAndObjectDetails.CreateDriver();
			} else if (browsername.equalsIgnoreCase("IE")) {
				new DriverAndObjectDetails(DriverName.IE);
				driverqa = DriverAndObjectDetails.CreateDriver();
			} else {
				new DriverAndObjectDetails(DriverName.FF);
				driverqa = DriverAndObjectDetails.CreateDriver();
			}
			logger.info("Browser Opened");
			driverqa.get(Config.getApplicationUrl1());
			logger.info("Test Case Started");
			ExcelDataConfig excel = new ExcelDataConfig(Config.getExcelPath());
			test = rep.startTest("LoginTest");
			test.log(LogStatus.INFO, "Starting LoginTest");
			
			 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Registering_new_customer/Customer_Interface_Homepage.jpg");

			
			driverqa.findElement(Ci_Register.Register_now).click();
			//driverqa.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			
			String expectedtitle = "DOTWconnect.com";
			String actualtitle = driverqa.getTitle();
			Assert.assertEquals(actualtitle, expectedtitle);
			
			logger.info("Navigated to registration page");
			test.log(LogStatus.INFO, "Navigated to registration page");
			
			
			driverqa.findElement(Ci_Register.Company_name).sendKeys(excel.getData(3,1,0));
		
			driverqa.findElement(Ci_Register.Company_Address).sendKeys(excel.getData(3,1,1));
			driverqa.findElement(Ci_Register.Postal_Code).sendKeys(excel.getData(3,1,2));
			
			Thread.sleep(3000);
			Select dropdown=new Select(driverqa.findElement(Ci_Register.Company_country));
			
			Thread.sleep(3000);
			//Select dropdown2 = new Select(d.findElement(By.xpath("//*[@id='companyCountry']")));
            dropdown.selectByVisibleText(excel.getData(3,1,3));
            Thread.sleep(3000);
			
            Select dropdown1=new Select(driverqa.findElement(Ci_Register.Company_city));
            dropdown1.selectByVisibleText(excel.getData(3,1,4));
            Thread.sleep(3000);
            Select dropdown2=new Select(driverqa.findElement(Ci_Register.Preffered_Payment_Method));
            dropdown2.selectByVisibleText(excel.getData(3,1,5));
            Thread.sleep(4000);
            
            Select dropdown3=new Select(driverqa.findElement(Ci_Register.Preffered_Currency));
            dropdown3.selectByVisibleText(excel.getData(3,1,6));
            Thread.sleep(1000);
			driverqa.findElement(Ci_Register.Telephone_City_Code).sendKeys(excel.getData(3,1,7));
			  
			driverqa.findElement(Ci_Register.Telephone_Number).sendKeys(excel.getData(3,1,8));
			 Thread.sleep(1000);
			 
            Select dropdown4=new Select(driverqa.findElement(Ci_Register.Salutation));
            dropdown4.selectByIndex(1);
            Thread.sleep(1000);
		
			driverqa.findElement(Ci_Register.FirstName).sendKeys(excel.getData(3,1,10));
			 Thread.sleep(1000);
			driverqa.findElement(Ci_Register.LastName).sendKeys(excel.getData(3,1,11));
			 Thread.sleep(1000);
			driverqa.findElement(Ci_Register.Email_Address).sendKeys(excel.getData(3,1,12));
			 Thread.sleep(1000);
			driverqa.findElement(Ci_Register.Confirm_Email_Address).sendKeys(excel.getData(3,1,13));
			 Thread.sleep(1000);
			driverqa.findElement(Ci_Register.Login_Id).sendKeys(excel.getData(3,1,14));
			 Thread.sleep(1000);
			driverqa.findElement(Ci_Register.Password).sendKeys(excel.getData(3,1,15));
			 Thread.sleep(1000);
			driverqa.findElement(Ci_Register.Confirm_Password).sendKeys(excel.getData(3,1,16));
			 Thread.sleep(1000);
			driverqa.findElement(Ci_Register.Terms_of_use).click();
			 Thread.sleep(1000);
			 
			 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Registering_new_customer/Registration_page.jpg");
			 
			 
			 driverqa.findElement(Ci_Register.Register_button).click();
			 Thread.sleep(2000);
			 //navigated to terms and condition
			// WebElement element=new
			 
			 logger.info("Navigated to terms and condition");
				test.log(LogStatus.INFO, "Navigated to terms and condition");
				
				
				
			 driverqa.findElement(Ci_Register.accept_continue).click();
			 
			 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Registering_new_customer/Terms_and_condition_page.jpg");

			 
			 driverqa.findElement(Ci_Register.continue1).click();
			 Thread.sleep(2000);
			 
			 
			 
			 String expectedtext = "DOTWconnect.com";
				String actualtext = driverqa.findElement(Ci_Register.thank_you).getText();
				Assert.assertEquals(actualtext, expectedtext);
			 
				 logger.info("Registration successful");
					test.log(LogStatus.INFO, "Registration successful");
		
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Registering_new_customer/Registration_successful.jpg");
					 
					 
					
			
			
			
	
	
		} catch (Exception e) {
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
		}
	}
		
		@Test(priority='1')
		@Parameters({ "browsername" })
		public void login1(String browsername) throws Exception {
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
				test.log(LogStatus.INFO, "Navigating to customer search page");
				logger.info("Navigating to customer search page");
				driverqa.findElement(Ci_Register.customers).click();
				Thread.sleep(2000);
				driverqa.findElement(Ci_Register.new_cust).click();
				Thread.sleep(2000);
				String searchcustatualtitle = driverqa.getTitle();
				String searchcustexpectedtitle = "DOTWconnect.com::New customers";
				Assert.assertEquals(searchcustatualtitle, searchcustexpectedtitle);
				logger.info("Navigated to customer filter page");
				test.log(LogStatus.PASS, "Customer filter Page");
				driverqa.findElement(Ci_Register.company_name).sendKeys(excel.getData(3,1,0));
				Thread.sleep(2000);
				driverqa.findElement(Ci_Register.filter_cus).click();
				Thread.sleep(2000);
				driverqa.findElement(Ci_Register.edit_newcust).click();
				Thread.sleep(2000);
				String searchcustatualtitle1= driverqa.getTitle();
				String searchcustexpectedtitle1 = "DOTWconnect.com::Customer";
				Assert.assertEquals(searchcustatualtitle1, searchcustexpectedtitle1);
				driverqa.findElement(Ci_Register.active_newcust).click();
				
				 Select dropdown_manager=new Select(driverqa.findElement(Ci_Register.select_manager));
				 dropdown_manager.selectByValue("7932");
				 
				driverqa.findElement(Ci_Register.update_newcust).click();
		            Thread.sleep(3000);
		        logger.info("New customer activated successfully");
			    test.log(LogStatus.PASS, "New customer activated successfully");   
				
				
				

			} catch (Exception e) {
				logger.info(e.getMessage());
				test.log(LogStatus.FAIL, e.getMessage());
				rep.endTest(test);
				rep.flush();
				Assert.assertTrue(false, e.getMessage());
			}
	}

}


