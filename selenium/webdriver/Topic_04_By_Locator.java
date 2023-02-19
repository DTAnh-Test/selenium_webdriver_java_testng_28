package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_By_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}

	// Email Address textbox
	// HTML Element
	// <input type="email" autocapitalize="off" autocorrect="off" spellcheck="false" name="login[username]" 
	// value="" id="email" class="input-text required-entry validate-email" title="Email Address">
	
	// HTML
	// 1 - Tên thẻ: Tagname : input
	// 2 - Tên thuộc tính : Attribute name - type/ autocapitalize/ autocorrect/ spellchecl/ name/ value/ id/ class/ title
	// 3 - Giá trị thuộc tính: Attribute value - email/ login[username]/ email/ input-text required-entry validate-email
	
	// XPath:
	// Format: //tagname[@attribute_name='attribute_value']
	// input[@id='email']
	
	// Css:
	// Format: tagname[attribute_name='attribute_value']
	
	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("email")).sendKeys("test@gmail.com");;
		
	}

	@Test
	public void TC_02_Class() {
		driver.findElement(By.className("search-button"));
	}

	@Test
	public void TC_03_Name() {
		driver.findElement(By.name("login[username]"));
	}

	@Test
	public void TC_04_Tagname() {
		// verify xem 1 page có bao nhiêu element giống nhau: link/ button/ radio/ textbox
		driver.findElements(By.tagName("a"));
		
	}

	@Test
	public void TC_05_LinkText() {
		// Chỉ dùng được với link
		// Lấy tuyệt đối cả text
		driver.findElement(By.linkText("SEARCH TERMS"));
	}

	@Test
	public void TC_06_Partial_LinkText() {
		// Chỉ dùng được với link
		// Lấy tương đối cả text
		driver.findElement(By.partialLinkText("SEARCH"));
		
	}
	
	@Test
	public void TC_07_Css() {
		// Css với ID
		driver.findElement(By.cssSelector("input#email"));
		// Css với class
		driver.findElement(By.cssSelector("div.new-users"));
		driver.findElement(By.cssSelector(".skip-link"));	
		driver.findElement(By.cssSelector("a.skip-link.skip-cart"));	
		driver.findElement(By.cssSelector("a[class='skip-link skip-cart  no-count']"));	
		
		// Css với Name
		driver.findElement(By.cssSelector("input[name='login[password]']"));		
		
		// Css với tagname
		driver.findElement(By.cssSelector("a"));		
		
		// Css với Link
		driver.findElement(By.cssSelector("a[title='Search Terms']"));			
		
		// Css với Partial Lịnk
		driver.findElement(By.cssSelector("a[title*='Terms']"));		
	}
	
	@Test
	public void TC_08_XPath() {
		// XPath với ID
		driver.findElement(By.xpath("//input[@id='email']"));
		// XPath với class
		driver.findElement(By.xpath("//div[@class='col-1 new-users']"));
		// XPath với Name
		driver.findElement(By.xpath("//input[@name='login[password]']"));		
		// XPath với tagname
		driver.findElement(By.xpath("//a"));		
		// XPath với Link
		driver.findElement(By.xpath("//a[@title='Search Terms']"));	
		driver.findElement(By.xpath("//a[text()='Search Terms']"));	
		// XPath với Partial Lịnk
		driver.findElement(By.xpath("//a[contains(@title,'Advance')]"));
		driver.findElement(By.xpath("//a[contains(text(),'Advance')]"));	
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}