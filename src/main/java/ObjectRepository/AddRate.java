package ObjectRepository;

import org.openqa.selenium.By;

public class AddRate {
	public static final By newrate = By.xpath("//*[@id='mainContainer']/div/div[2]/div/a[3]");
	public static final By contractlist = By.id("ratePlanCode");
	public static final By ratenpolicy = By.xpath("//*[@id='ratesAndPoliciesTab']/a");
	public static final By adultrate = By.xpath("//*[@id='rate[0][periods][0][adultRate][1]']");
	public static final By cancelpolicy = By.xpath("//*[@id='cancellationRulesDefinition1']/div/div[2]/select");
	public static final By successpush = By.xpath("//*[@id='modalBody']");
	public static final By submitrate = By.xpath("//*[@id='EditRateForm']/div[2]/button[2]");
}
