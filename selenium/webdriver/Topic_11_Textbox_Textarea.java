package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Textbox_Textarea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	
	WebDriverWait expliciWait;
	
	String emailAddress = "automation" + rand.nextInt(999)+"@gmail.com";
	String firstname = "John";
	String lastname = "Witch";
	String fullname = firstname + " " + lastname;
	String password = "123456";
	 
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		
		// Trạng thái của element: Huển thị/ không hiển thị/ presence/ staleness
		expliciWait = new WebDriverWait(driver,30);
		
		// Apply cho việc tìm element (findElement, findElements)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Techpanda_Register() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		 
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		
		Assert.assertTrue(contactInfo.contains(fullname));
		Assert.assertTrue(contactInfo.contains(emailAddress));
		
		driver.findElement(By.xpath("//a[text()='Account Information']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"), lastname);
		Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), emailAddress);
		 
		driver.findElement(By.cssSelector("a.skip-account span.label")).click();
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.page-title img")));
		Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title img")).isDisplayed()); 
		// findElement(): Cũng là 1 cách wait hay
		// Ảnh hưởng bởi impliciWait
		// Giây đầu tiên không tìm thấy sẽ tiếp tục chờ và tìm lại
		// 0.5s tìm lại 1 lần
		// 0.5s tiếp theo thì element xuất hiện => Thấy => Trả về kết quả
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}