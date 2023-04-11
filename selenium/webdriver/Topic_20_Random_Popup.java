package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Random_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	
	String emailAddress = "test" + rand.nextInt(9999) + "@gmail.com";
	
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
	public void TC_01_Java_Code_Geeks_Not_In_HTML() {
		// 3 trường hợp:
			// Mới mở page: Có. không có trong HTML
			// Element popup được render ra: Chắc chắn có trong HTML
			// Đóng popup thì có thể có hoặc không trong HTML
		// Cách handle:
			// Nếu element có trong HTML thì dùng findElement để check isDisplayed()
			// Nếu element không có trong HTML thì dùng findElements và check size()= 0 -> không hiển thị
		
		// Mở page thì popup chưa có trong HTML
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(15);
		// Sau 1 tgian render thì popup có trong HTML
		// List findElements
		By firstStepPopup = By.cssSelector("div[data-title='Newsletter-Books Anime Brief - NEW']:not([data-page='confirmation'])");
		By secondStepPopup = By.cssSelector("div[data-title='Newsletter-Books Anime Brief - NEW'][data-page='confirmation']");		

		By popup2 = By.cssSelector("div[data-title='Newsletter Free eBooks']:not([data-page='confirmation'])");
		List<WebElement> firstStepPopupElement = driver.findElements(firstStepPopup);
		List<WebElement> firstStepPopupElement2 = driver.findElements(popup2);
		
		// 1-Nếu có hiển thị thì nhập thông tin và click
		// Xử lý tiếp step đến khi popup đóng lại
		if (firstStepPopupElement.size() > 0 && firstStepPopupElement.get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("input[placeholder='Enter your e-mail address']")).sendKeys(emailAddress);
			driver.findElement(By.cssSelector("a[data-label='Get the Books']")).click();
			sleepInSecond(7);
			
			Assert.assertTrue(driver.findElement(secondStepPopup).isDisplayed());
			sleepInSecond(12);
			Assert.assertFalse(driver.findElement(firstStepPopup).isDisplayed());
			Assert.assertFalse(driver.findElement(secondStepPopup).isDisplayed());
		}
		
		if (firstStepPopupElement2.size() > 0 && firstStepPopupElement2.get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("input[placeholder='Your Email']")).sendKeys(emailAddress);
			driver.findElement(By.cssSelector("a[data-label='OK']")).click();
			sleepInSecond(5);
			
			Assert.assertTrue(driver.findElement(secondStepPopup).isDisplayed());
			sleepInSecond(12);
			Assert.assertFalse(driver.findElement(popup2).isDisplayed());
			Assert.assertFalse(driver.findElement(secondStepPopup).isDisplayed());
		}
		
		// 2-Nếu không hiển thị thì qua step tiếp theo
		driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
		driver.findElement(By.cssSelector("button#search-submit")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("h1.page-title>span")).getText(), "Agile Testing Explained");
		
	}

//	@Test
	public void TC_02_VNK_In_HTML() {
		driver.get("https://vnk.edu.vn/");
		
		By popup = By.cssSelector("div#tve_editor div[data-style='cb_style_7']");
		
		// Luôn có trong HTML
		if (driver.findElement(popup).isDisplayed()) {
			driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
			sleepInSecond(2);
			Assert.assertFalse(driver.findElement(popup).isDisplayed());
		}
		
		// Step tiếp theo
		driver.findElement(By.cssSelector("button.btn-danger")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://vnk.edu.vn/lich-khai-giang/");
	}

	@Test
	public void TC_03_Not_In_HTML() {
		driver.get("https://dehieu.vn/");
		
		List<WebElement> popup = driver.findElements(By.cssSelector("div.popup-content"));
		// 1 - Có hiển thị popup
		if (popup.size() > 0 && popup.get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(2);
		}
		// Không hiển thị popup
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/khoa-hoc");
	}

	@Test
	public void TC_04_() {

	}

	@Test
	public void TC_05_() {

	}

	@Test
	public void TC_06_() {

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