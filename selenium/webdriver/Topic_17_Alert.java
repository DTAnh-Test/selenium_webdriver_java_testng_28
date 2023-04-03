package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Alert {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String autoITFirefox = projectPath + "\\autoIT\\authen_firefox.exe";

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

//	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(2);
		// Switch qua: Alert/ Iframe/ Window
			// Alert alert = driver.switchTo().alert();
		Alert alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(2);
		
		// Verify title alert
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		// Accept Alert:
		alert.accept();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
		// GetText ra verify title alert: alert.getText();

		
	}

//	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(2);
		
		// Wait thấy rồi switch qua
		Alert alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(2);
		
		// Verify title alert
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		// Cancel Alert: 
		alert.dismiss();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}

//	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(2);
		
		// Wait thấy rồi switch qua
		Alert alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(2);
		
		// Verify title alert
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		// Nhập dữ liệu vào alert: 
		String keyword = "Selenium";
		alert.sendKeys(keyword);
		sleepInSecond(2);
		
		alert.accept();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + keyword);
	}

//	@Test
	public void TC_04_Authentication_Alert() {
		// Không dùng thư viện Alert xử lý được
		// Cách 1:
		// Selenium trick
			// Truyền trực tiếp username - password vào URL
			// http://username:password@domain
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
//		driver.get("http://the-internet.herokuapp.com/basic_auth");
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(), "Congratulations! You must have the proper credentials.");
	}
	
	@Test
	public void TC_05_Authentication_Alert() {
		driver.get("http://the-internet.herokuapp.com");
		
		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");  // lấy ra URL của element nối tiếp vào
		
		driver.get(getUrlByUserPass(basicAuthenLink, "admin", "admin"));
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(), "Congratulations! You must have the proper credentials.");
	}
	
//	@Test
	public void TC_06_Authentication_Alert_AutoIT() throws IOException {
		// Cách 2: Dùng AutoIT - Chỉ dùng đc trên HĐH Window
		Runtime.getRuntime().exec(new String[] {autoITFirefox, "admin","admin"});
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		sleepInSecond(8);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(), "Congratulations! You must have the proper credentials.");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getUrlByUserPass(String url, String username, String password) {
		String[] newUrl =  url.split("//");
		url = newUrl[0] + "//" + username + ":" + password + "@" + newUrl[1];
		return url;
	}
}