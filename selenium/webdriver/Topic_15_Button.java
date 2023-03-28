package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.Colors;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Button {
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
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		// Click chuyển qua tab Đăng nhập
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		sleepInSecond(2);
		By loginButton = By.cssSelector("button.fhs-btn-login");
		
		// Verify login button is disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		// Verify màu background login button 
		 Assert.assertTrue(driver.findElement(loginButton).getCssValue("background").contains("rgb(224, 224, 224)"));
		
		// Nhập email/ password
		driver.findElement(By.id("login_username")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123456");
		sleepInSecond(2);
		
		// Verify login button is enable
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		
		// Lấy ra mã màu 1 element
			// Chrome/ Edge/.. : RGB
			// Firefox: RGBA
		String loginButtonBackgroundColor = driver.findElement(loginButton).getCssValue("background-color");
		
		// Chuyển từ RGB/ RGBA qua kiểu Color
		Color loginButtonColor = Color.fromString(loginButtonBackgroundColor);
		
		// Color có hàm chuyển qua hexa
		// Nên chuyển qua viết hoa
		String loginButtonHexa = loginButtonColor.asHex().toUpperCase();
		
		// Verify màu background login button 
		Assert.assertEquals(loginButtonHexa, "#C92127");
			// Cách 2: Viết gom lại
		Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(), "#C92127");
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