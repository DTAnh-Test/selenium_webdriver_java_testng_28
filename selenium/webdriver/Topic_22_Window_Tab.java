package webdriver;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_Window_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_Github() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// ID của trang hiện tại mà driver đang đứng
		String githubID = driver.getWindowHandle();
		
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		
		// Bussiness: Nhảy qua tab mới: Google
		// Driver vẫn đang ở trang Github
		
		// Hiện đang có 2 tab = 2 ID
//			Set<String> allIDs = driver.getWindowHandles();
//			for (String id : allIDs) {
//				if (!id.equals(githubID)) {
//					driver.switchTo().window(id);
//					break;
//				}
//			}
//		switchToWindow(githubID);
		switchWindowByTitle("Google");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("JohnWich");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys(Keys.ENTER);
		sleepInSecond(3);
		
		// Driver đã đứng tại trang Google
//		String googleID = driver.getWindowHandle(); 
//			for (String id : allIDs) {
//				if (!id.equals(googleID)) {
//					driver.switchTo().window(id);
//					break;
//				}
//			}
		// Switch về trang Github
//		switchToWindow(googleID);
		switchWindowByTitle("Selenium WebDriver");
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(5);
		
		switchWindowByTitle("Facebook – log in or sign up");
		sleepInSecond(2);
		
		driver.findElement(By.id("email")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456567");
		sleepInSecond(2);
		
		switchWindowByTitle("Selenium WebDriver");
		
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(2);
		switchWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		
		driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")).sendKeys("Tủ lạnh");
		driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")).sendKeys(Keys.ENTER);
		
		closeAllWindowWithoutExpectedID(githubID);
	}

	@Test
	public void TC_02_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		
		// Scroll xuống cuối trang bằng js
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='facebook']")).click();
		sleepInSecond(2);
		switchWindowByTitle("Kyna.vn | Facebook");
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kyna.vn']")).isDisplayed());
		switchWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='youtube']")).click();
		sleepInSecond(5);
		switchWindowByTitle("Kyna.vn - YouTube");
		Assert.assertTrue(driver.findElement(By.xpath("//yt-formatted-string[@id='text' and text()='Kyna.vn']")).isDisplayed());
		switchWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//div[@id='k-footer-copyright']//img[contains(@src,'dathongbao.png')]")).click();
		sleepInSecond(2);
		switchWindowByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Địa chỉ tên miền:']/parent::div/following-sibling::div/p[contains(text(),'kyna.vn')]")).isDisplayed());
		switchWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepInSecond(2);
		
		closeAllWindowWithoutExpectedID(driver.getWindowHandle());
	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
	
	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Hàm switchWindow By ID
	// Chỉ chạy cho 2 tab/ 2 window
	public void switchToWindow(String windowID) {
		Set<String> allIDs = driver.getWindowHandles();
		
		for (String id : allIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	
	// SwitchWindow By Title
	// Có thể chạy cho nhiều tab/ window
	public void switchWindowByTitle(String expectedTitle) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}
	
	// Đóng tất cả tab trừ tab muốn giữ lại
	public void closeAllWindowWithoutExpectedID(String expectedID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if (!id.equals(expectedID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(expectedID);
	}
}