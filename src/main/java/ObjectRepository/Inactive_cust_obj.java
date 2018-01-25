package ObjectRepository;
import org.openqa.selenium.By;

public class Inactive_cust_obj {

   public static final By uname = By.xpath("/html/body/div/form/input[2]");
   public static final By pwd = By.name("password");
   public static final By submit = By.xpath("/html/body/div/form/button");

   public static final By cust = By.xpath("//*[@id='MenuBar_0']/div[2]/ul[1]/li[7]/a");
 
   public static final By live_cust=By.xpath("//*[@id='MenuBar_143']/div[2]/ul[1]/li[2]/a");
   public static final By simple_filter=By.xpath("//*[@id='AdvancedFilters']");
   public static final By company_name=By.xpath("//*[@id='companyName']");

   public static final By filter_cus=By.xpath("//*[@id='filter-button']");
   public static final By edit =By.xpath("/html/body/div[1]/div[2]/form/div[1]/div/table/tbody/tr/td[10]/a[1]");
 //*[@id="CompanyName"]
   public static final By check=By.xpath("//*[@id='CompanyName']");
   public static final By inactive=By.xpath("/html/body/div[1]/div[2]/form/div[1]/div/div[2]/div[2]/div[2]/div[1]/div/div[1]/div[2]/div/div/span[1]");
   
   // /html/body/div[1]/div[2]/form/div[1]/div/div[2]/div[2]/div[2]/div[1]/div/div[1]/div[2]/div/div/span[1]
   // //*[@id="inactiveReason"]
   public static final By InactiveCheck=By.xpath("//*[@id='inactiveReason']");
   // /html/body/div[1]/div[2]/form/div[2]/div/button
   public static final By update=By.xpath("/html/body/div[1]/div[2]/form/div[2]/div/button");

  // /html/body/div[4]/div/div/div[2]/div[2]/button[2]
    public static final By confirm=By.xpath("/html/body/div[4]/div/div/div[2]/div[2]/button[2]");
// //*[@id="userID"]
    public static final By CIuser=By.xpath("//*[@id='userID']");
    public static final By CIpass=By.xpath("//*[@id='password']");
    ////*[@id="companyCode"]
    public static final By companycode=By.xpath("//*[@id='companyCode']");
    //  //*[@id="companyCode"]
    ////*[@id="button"]
    public static final By CIlogin=By.xpath("//*[@id='button']");
   
}

