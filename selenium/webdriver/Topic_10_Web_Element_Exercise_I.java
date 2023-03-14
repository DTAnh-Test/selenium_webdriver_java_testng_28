package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Web_Element_Exercise_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExcutor;
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		
		jsExcutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	@Test
	public void TC_01_Displayed() { //throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
//		sleepInSecond(5);

		if (driver.findElement(By.id("mail")).isDisplayed()) {
			System.out.println("Email textbox is displayed");
			driver.findElement(By.id("mail")).sendKeys("abc@mgm.com");
		} else {
			System.out.println("Email textbox is not displayed");
		}
		
		if (driver.findElement(By.id("under_18")).isDisplayed()) {
			System.out.println("Radio Age Under 18 is displayed");
			driver.findElement(By.id("under_18")).click();
		} else {
			System.out.println("Radio Age Under 18 is not displayed");
		}
		
		if (driver.findElement(By.id("edu")).isDisplayed()) {
			System.out.println("Textarea Education is displayed");
			driver.findElement(By.id("edu")).sendKeys("dasd");
		} else {
			System.out.println("Textarea Education is not displayed");
		}
		
		if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
			System.out.println("Name User 5 is displayed");
		} else {
			System.out.println("Name User 5 is not displayed");
		}
	}

	@Test
	public void TC_02_Enable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		if (driver.findElement(By.id("disable_password")).isEnabled ()) {
			System.out.println("Password textbox is enabled");
		} else {
			System.out.println("Password textbox is disabled");
		}
		
		if (driver.findElement(By.id("mail")).isEnabled ()) {
			System.out.println("Email textbox is enabled");
		} else {
			System.out.println("Email textbox is disabled");
		}		
		
		if (driver.findElement(By.id("radio-disabled")).isEnabled ()) {
			System.out.println("Radio button is enabled");
		} else {
			System.out.println("Radio button is disabled");
		}	
		
		if (driver.findElement(By.id("development")).isEnabled ()) {
			System.out.println("Development checkbox is enabled");
		} else {
			System.out.println("Development checkbox is disabled");
		}	
	}

	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.id("under_18")).click();
		// Dùng để kiểm tra 1 element được chọn thành công
		Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
		if (driver.findElement(By.id("under_18")).isSelected()) {
			System.out.println("Radio Age Under 18 is selected");
		} else {
			System.out.println("Radio Age Under 18 is not selected");
		}
		
		driver.findElement(By.id("java")).click();
		sleepInSecond(2);
		// Dùng để kiểm tra 1 element chưa được chọn thành công
			//Assert.assertFalse(driver.findElement(By.id("java")).isSelected());
		Assert.assertTrue(driver.findElement(By.id("java")).isSelected());
		if (driver.findElement(By.id("development")).isSelected()) {
			System.out.println("Development checkbox is selected");
		} else {
			System.out.println("Development checkbox is selected");
		}	
		// isSelected(): Nếu 1 element được chọn thành công, nó sẽ trả về true/ chưa trọn trả về false
		
		driver.findElement(By.id("under_18")).click();
		driver.findElement(By.id("java")).click();
		sleepInSecond(2);
		// Dùng để kiểm tra 1 element được chọn thành công
		Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
		
		// Dùng để kiểm tra 1 element chưa được chọn thành công
		Assert.assertFalse(driver.findElement(By.id("java")).isSelected());
	}

	@Test
	public void TC_04_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.id("create-account-enabled")).click();
		sleepInSecond(2);
		
		// Verify signup button is enable
		Assert.assertTrue(driver.findElement(By.id("create-account-enabled")).isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//button[@id='create-account-enabled' and text()='Sign Up']")).isDisplayed());
		
		// Verify error mesage is displayed
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']/following-sibling::span")).getText(), "An email address must contain a single @.");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='new_username']/following-sibling::span")).getText(), "Please enter a value");
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']/span")).getText(), "One lowercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']/span")).getText(), "One uppercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='number-char not-completed']/span")).getText(), "One number");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='special-char not-completed']/span")).getText(), "One special character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='8-char not-completed']/span")).getText(), "8 characters minimum");
		
		driver.findElement(By.id("email")).sendKeys("abc@gmail.com");
		
		// Case 1: Number
		driver.findElement(By.id("new_password")).sendKeys("123");
		// scroll tới element
		jsExcutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("create-account-enabled")));
		driver.findElement(By.id("create-account-enabled")).click();
		sleepInSecond(1);
		 
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
		
		// Case 2: Lowercase
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("abc");
		sleepInSecond(1);
		
		 
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
		
		// Case 3: UpperCase
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("ABC");
		sleepInSecond(1);
		
		 
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
		
		// Case 4: Special Charactor
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("@#$!");
		sleepInSecond(1);
		
		 
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
		
		// Case 5: Greater  than 7 char
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("12345678");
		sleepInSecond(1);
		
		 
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
		
		// Case 6: Valid
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("123abC@13");
		sleepInSecond(1);
		
		 
		Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
		
		driver.findElement(By.id("marketing_newsletter")).click();
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.id("marketing_newsletter")).isSelected());
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