package ObjectRepository;
import org.openqa.selenium.By;

public class CancelAccom {
	public static final By cancelbutton = By.xpath("//*[@id='cancelBooking']");
	public static final By confirmcancel = By.xpath("//*[@id='confirmCancellation']");
	public static final By confirmcancelstatus = By.xpath("/html/body/div[5]/div/div/div[1]/h4");
	public static final By cancelstatus = By.xpath("//*[@id='mainContainer']/div[1]/div/div[1]/div[1]/h5[2]");
}
