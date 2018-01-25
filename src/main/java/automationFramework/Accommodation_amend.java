package automationFramework;

import lib.DriverAndObjectDetails;
import lib.ExcelDataConfig;
import lib.ExtentManager;
import lib.Takescreenshot;
import lib.DriverAndObjectDetails.DriverName;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ObjectRepository.Amend;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.NewAccoBooking;
import ObjectRepository.Operations;
import ObjectRepository.PaymentPage;
import Utility.Configuration;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.chrome.ChromeOptions;

public class Accommodation_amend {
	public WebDriver driver;
	Configuration Config = new Configuration();
	Takescreenshot obj = new Takescreenshot();
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
//Login
	@Test(priority = 1)
	@Parameters({ "browsername" })
	public void Amend(String browsername) throws Exception {

		try {
			if (browsername.equalsIgnoreCase("CH")) {

				driver = new DriverAndObjectDetails(DriverName.CH).CreateDriver();
				//jjk

			} else {

				new DriverAndObjectDetails(DriverName.IE);
				driver = DriverAndObjectDetails.CreateDriver();
			}
			driver.get(Config.getApplicationUrl());
			ExcelDataConfig excel = new ExcelDataConfig(Config.getExcelPath());

			test = rep.startTest("Amend");
			test.log(LogStatus.INFO, "Starting LoginTest");

			String expected = "http://maintenance3.stage.aws.dotw.com/_myadmin";

			WebElement username = driver.findElement(LoginPage.uname);
			username.clear();
			username.sendKeys(excel.getData(0, 1, 0));

			WebElement password = driver.findElement(LoginPage.pwd);
			password.clear();
			password.sendKeys(excel.getData(0, 1, 1));
			driver.findElement(LoginPage.submit).click();
			// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// driver.switchTo().alert().dismiss();
			String actual = driver.getCurrentUrl();

			Assert.assertEquals(actual, expected);

			test.log(LogStatus.INFO, "Ending LoginTest");
			test.log(LogStatus.PASS, "PASSED LoginTest");

			Thread.sleep(1000);

			// driver.switchTo().alert().dismiss();
			driver.findElement(HomePage.operation).click();
			WebDriverWait wait = new WebDriverWait(driver, 10);

			wait.until(ExpectedConditions.elementToBeClickable(Operations.newBooking));

			driver.findElement(Operations.newBooking).click();
			Thread.sleep(1000);
			driver.findElement(Operations.AccomBook).click();
			Thread.sleep(1000);

			/*
			 * driver.findElement(Operations.city).click();
			 * 
			 * driver.findElement(Operations.city).sendKeys(excel.getData(0, 4,
			 * 0)); Thread.sleep(1000);
			 */

			driver.findElement(Operations.company).sendKeys("maintenance3cc");
			Thread.sleep(2000);

			Actions action = new Actions(driver);
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();

			driver.findElement(Operations.chooseCust).click();
			Thread.sleep(1000);

			/*driver.findElement(NewAccoBooking.destCity).sendKeys(excel.getData(0, 7, 0));
			Thread.sleep(2000);
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();*/
			driver.findElement(NewAccoBooking.AccomUnit).sendKeys(excel.getData(0, 2, 8));
			Thread.sleep(4000);
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();

			driver.findElement(NewAccoBooking.inDate).clear();
			driver.findElement(NewAccoBooking.inDate).sendKeys(excel.getData(0, 2, 9));

			driver.findElement(NewAccoBooking.outDate).clear();
			driver.findElement(NewAccoBooking.outDate).sendKeys(excel.getData(0, 2, 10));

			obj.Takesnap(driver, Config.SnapShotPath() + "/Accommodation_amend/1Search.jpg");

			driver.findElement(NewAccoBooking.searchButton).click();

			Thread.sleep(5000);
			obj.Takesnap(driver, Config.SnapShotPath() + "/Accommodation_amend/2Results.jpg");

			String result = driver.findElement(NewAccoBooking.resultHotel).getText();

			CharSequence cs = excel.getData(0, 2, 8);
			System.out.println(cs);
			Thread.sleep(3000);
			driver.findElement(NewAccoBooking.roomtypes).click();

			if (result.contains(cs)) {

				Thread.sleep(2000);
				obj.Takesnap(driver, Config.SnapShotPath() + "/Accommodation_amend/3Correct_Hotel_room.jpg");

				test.log(LogStatus.INFO, "Correct hotel returned!");

				Assert.assertTrue(true);

				test.log(LogStatus.PASS, "PASSED Search");

				driver.findElement(NewAccoBooking.roomselection).click();
				driver.findElement(NewAccoBooking.procedetoBook).click();
				Thread.sleep(3000);

				WebDriverWait wait2 = new WebDriverWait(driver, 10);

				wait2.until(ExpectedConditions.elementToBeClickable(NewAccoBooking.paxtitle));
				Select title = new Select(driver.findElement(NewAccoBooking.paxtitle));
				title.selectByVisibleText(excel.getData(0, 15, 1));

				Thread.sleep(1000);
				driver.findElement(NewAccoBooking.paxFname).sendKeys(excel.getData(0, 1, 13));
				Thread.sleep(1000);
				driver.findElement(NewAccoBooking.paxLname).sendKeys(excel.getData(0, 1, 14));

				driver.findElement(NewAccoBooking.acceptChkBX).click();

				driver.findElement(NewAccoBooking.bookBtn).click();

				Thread.sleep(5000);

				obj.Takesnap(driver, Config.SnapShotPath() + "/Accommodation_amend/4prebooking.jpg");

				Thread.sleep(5000);

				driver.findElement(NewAccoBooking.confirmBook).click();

				Thread.sleep(3000);

				driver.findElement(PaymentPage.fName).clear();
				driver.findElement(PaymentPage.fName).sendKeys(excel.getData(0, 1, 13));

				driver.findElement(PaymentPage.LName).clear();
				driver.findElement(PaymentPage.LName).sendKeys(excel.getData(0, 1, 14));

				driver.findElement(PaymentPage.address).clear();
				driver.findElement(PaymentPage.address).sendKeys("KOLKATA");

				driver.findElement(PaymentPage.zip).clear();
				driver.findElement(PaymentPage.zip).sendKeys(excel.getData(0, 1, 17));

				Select country = new Select(driver.findElement(PaymentPage.country));
				country.selectByVisibleText(excel.getData(2, 24, 1));

				driver.findElement(PaymentPage.city).clear();
				driver.findElement(PaymentPage.city).sendKeys("Kolkata");

				Select ccType = new Select(driver.findElement(PaymentPage.ccType));
				ccType.selectByVisibleText("Visa");

				driver.findElement(PaymentPage.ccNum).clear();
				driver.findElement(PaymentPage.ccNum).sendKeys(excel.getData(0, 2, 17));

				driver.findElement(PaymentPage.ccName).clear();
				driver.findElement(PaymentPage.ccName).sendKeys(excel.getData(0, 1, 13));

				Select expMonth = new Select(driver.findElement(PaymentPage.expMonth));
				expMonth.selectByVisibleText("1");

				Select expYear = new Select(driver.findElement(PaymentPage.expYear));
				expYear.selectByVisibleText("2020");

				driver.findElement(PaymentPage.cvv).clear();
				driver.findElement(PaymentPage.cvv).sendKeys(excel.getData(0, 3, 17));

				driver.findElement(PaymentPage.termsconditions).click();

				obj.Takesnap(driver, Config.SnapShotPath() + "/Accommodation_amend/5CC_Payment.jpg");

				driver.findElement(PaymentPage.confirmPayment).click();

				Thread.sleep(3000);
				WebDriverWait wait1 = new WebDriverWait(driver, 10);
				WebDriverWait wait3 = new WebDriverWait(driver, 10);

				wait1.until(ExpectedConditions.elementToBeClickable(PaymentPage.confirmationStatus));
				String ConfirmStatus = driver.findElement(PaymentPage.confirmationStatus).getText();
				
				

				System.out.println(ConfirmStatus);
				CharSequence cc = "Confirmed";
				if (ConfirmStatus.contains(cc)) {
					obj.Takesnap(driver, Config.SnapShotPath() + "/Accommodation_amend/6Confirmed.jpg");
					Assert.assertTrue(true);
					test.log(LogStatus.PASS, "Booking Confirmed");

					driver.findElement(Amend.amendBtn).click();

					test.log(LogStatus.PASS, "Amend Page Opened");

					Thread.sleep(3000);

					driver.findElement(Amend.specialReq).click();

					driver.findElement(Amend.VIP).click();

					test.log(LogStatus.PASS, "Added Special Request");

					driver.findElement(Amend.newFname).clear();
					driver.findElement(Amend.newFname).sendKeys(excel.getData(0, 2, 13));

					test.log(LogStatus.PASS, "Changed name");

					driver.findElement(Amend.newChckIn).clear();
					driver.findElement(Amend.newChckIn).sendKeys(excel.getData(0, 1, 9));

					driver.findElement(Amend.newChckOut).clear();
					driver.findElement(Amend.newChckOut).sendKeys(excel.getData(0, 1, 10));

					test.log(LogStatus.PASS, "Changed dates");

					obj.Takesnap(driver, Config.SnapShotPath() + "/Accommodation_amend/7AmendPage.jpg");

					driver.findElement(Amend.proceedAmend).click();
					Thread.sleep(3000);
					driver.findElement(Amend.confirmAmend).click();
					Thread.sleep(5000);
					wait3.until(ExpectedConditions.visibilityOfElementLocated(Amend.StatusAmend));
					
					
					
					String expectedstatus="Amended Confirmed";
					String expectedchckIn=excel.getData(0, 1, 9);
					
					System.out.println(expectedchckIn);
					String expectedchckOut=excel.getData(0, 1, 10);
					String expectedFname=excel.getData(0, 2, 13);
					
					String AmendStatus = driver.findElement(Amend.StatusAmend).getText();
					Assert.assertTrue(AmendStatus.contains(expectedstatus));
					
					String newFname= driver.findElement(Amend.verifyfName).getText();
					Assert.assertTrue(newFname.contains(expectedFname));
					
					System.out.println(newFname);
					
					String newIndate = driver.findElement(Amend.verifyChkin).getText();
					System.out.println(newIndate);
					Assert.assertEquals(newIndate, expectedchckIn);
					
					System.out.println(newIndate);
					
					String newOutdate = driver.findElement(Amend.verifyChkout).getText();
					
					Assert.assertEquals(newOutdate, expectedchckOut);

					System.out.println(expectedchckOut);
					
									
					
				}

				else {
					obj.Takesnap(driver, Config.SnapShotPath() + "/Accommodation_amend/6Unsuccessful.jpg");
					test.log(LogStatus.FAIL, "Booking Unsuccessful");
					Assert.assertTrue(false);
				}

			} else {
				System.out.println("Fail: INCORRECT hotel returned!");
				Assert.assertTrue(false);

			}
		}

		catch (Exception e) {
			System.out.println(e);
			test.log(LogStatus.FAIL, e.getMessage());
			Assert.assertTrue(false);

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

	/*
	 * @BeforeClass public void beforeClass() throws Exception { // Create a new
	 * instance of the Chrome driver
	 * 
	 * System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
	 * 
	 * ChromeOptions chromeOptions = new ChromeOptions();
	 * chromeOptions.addArguments("--start-maximized");
	 * 
	 * chromeOptions.addArguments("--disable-web-security");
	 * chromeOptions.addArguments("--no-proxy-server");
	 * 
	 * Map<String, Object> prefs = new HashMap<String, Object>();
	 * prefs.put("credentials_enable_service", false);
	 * prefs.put("profile.password_manager_enabled", false);
	 * 
	 * chromeOptions.setExperimentalOption("prefs", prefs);
	 * 
	 * driver = new ChromeDriver(chromeOptions);
	 * 
	 * // Put a Implicit wait, this means that any search for elements on the //
	 * page could take the time the implicit wait is set for before throwing //
	 * exception driver.manage().timeouts().implicitlyWait(10,
	 * TimeUnit.SECONDS); // Launch the MyAdmin
	 * driver.get("http://maintenance3.stage.aws.dotw.com/_myadmin/login");
	 * 
	 * // driver.manage().window().maximize(); }
	 */

	@AfterTest
	public void afterTest() {

		rep.endTest(test);

		rep.flush();
		// driver.close();
	}

}
