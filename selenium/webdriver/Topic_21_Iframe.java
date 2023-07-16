package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Iframe {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
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
	}

//	@Test
	public void TC_01_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		
		WebElement facebookIframe = driver.findElement(By.cssSelector("div.fanpage iframe"));
		
		// Verify iframe hiển thị
		Assert.assertTrue(facebookIframe.isDisplayed());
		
		// Cần phải switch qua frame/ iframe
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
		
		// Lấy lượt like
		String facebookLike = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText();

		// Switch về page trước đó
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("World");
		
		// Switch vào iframe chat trước
		driver.switchTo().frame("cs_chat_iframe");
		
		// Click  vào khung chat để bật cửa số chat lên
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Test");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0999999999");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("HỖ TRỢ KỸ THUẬT");
		driver.findElement(By.name("message")).sendKeys("ABC");
		
		// Nếu như chưa sswitch về trang default lại thì không có tương tác với element của page khác được
		// Switch về page trước đó
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("input#live-search-bar")).clear();
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		
		// Verify course number
		Assert.assertEquals(courseName.size(), 9);
		
		// Verify course name
		for (WebElement course : courseName) {
			Assert.assertTrue(course.getText().contains("Excel"));
		}
	}

	@Test
	public void TC_02_HDFC_Bank() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame("login_page");
		
		// Nhập vào userID
		driver.findElement(By.name("fldLoginUserId")).sendKeys("John");
		
		// CLick Continue
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
		// Switch về page trước đó
		driver.switchTo().defaultContent();
		// Verify password hiển thị
		Assert.assertTrue(driver.findElement(By.id("keyboard")).isDisplayed());
		driver.findElement(By.id("keyboard")).sendKeys("AutoTest");
		
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
	
}