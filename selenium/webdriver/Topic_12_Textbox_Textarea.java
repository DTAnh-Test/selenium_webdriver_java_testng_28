 package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Textbox_Textarea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstname, lastname,employeeID, username, password, passportID, issueDate, expiryDate, comment;
	Random rand = new Random();
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		firstname="Auto";
		lastname="mation";
		username = "Automation" + rand.nextInt(9999);
		password = "Abc1234@";
		passportID = "135055685";
		issueDate = "2018-04-17";
		expiryDate = "2028-04-17";
		comment ="Employee Passport \nIndentification Number";
	}

	@Test
	public void TC_01_OrangeGRM() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		// Login với quyền admin
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.name("firstName")).sendKeys(firstname);
		driver.findElement(By.name("lastName")).sendKeys(lastname);
		
		employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"); 
		
		sleepInSecond(3);
		driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//span")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(username);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		
		sleepInSecond(8);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastname);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"),employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportID);
		driver.findElement(By.xpath("//label[text()='Issued Date']/parent::div/following-sibling::div//input")).sendKeys(issueDate);
		driver.findElement(By.xpath("//label[text()='Expiry Date']/parent::div/following-sibling::div//input")).sendKeys(expiryDate);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(comment);
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='135055685']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='2018-04-17']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='2028-04-17']")).isDisplayed());
		
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportID);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Issued Date']/parent::div/following-sibling::div//input")).getAttribute("value"), issueDate);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Expiry Date']/parent::div/following-sibling::div//input")).getAttribute("value"), expiryDate);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);
		
		driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(3);
		
		// Login với quyền của Employee
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(passportID);
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//span[text()='My Info']")).click(); 
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastname);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"),employeeID);
		
		driver.findElement(By.xpath("//span[text()='Imigration']")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='135055685']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='2018-04-17']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='2028-04-17']")).isDisplayed());
		
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportID);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Issued Date']/parent::div/following-sibling::div//input")).getAttribute("value"), issueDate);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Expiry Date']/parent::div/following-sibling::div//input")).getAttribute("value"), expiryDate);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);

	}
 
	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

	}
//label[text()='Employee Id']/parent::div/following-sibling::div/input
	@Test
	public void TC_04_() {

	}

	@Test
	public void TC_05_() {

	}

	@Test
	public void TC_06_() {

	}
		
	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}