package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Popup {
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

//	@Test
	public void TC_01_Fix_Popup_In_DOM() {
		driver.get("https://ngoaingu24h.vn/");
		// Popup dạng luôn có trong HTML
		
		// Luôn có trong HTML dù có hiển thị hay kh
		By loginPopup = By.cssSelector("div[id='modal-login-v1'][style]>div.modal-dialog");
		
		// Click button Đăng nhập
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(2);
		
		// Verify login popup isDisplayed
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#account-input")).sendKeys("Automation");
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#password-input")).sendKeys("Automation");
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.btn-login-v1")).click();
		sleepInSecond(2);
		
		// Verify error message
		Assert.assertEquals(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		// Close popup
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
		
		// Verify login popup không hiển thị
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
	}

//	@Test
	public void TC_02_Login_Invalid_Email_Password() {
		// Popup dạng render mới xuất hiện trong HTML
		
		driver.get("https://skills.kynaenglish.vn/");
		By loginPopup = By.cssSelector("#k-popup-account-login-mb div.modal-content");
		// Click button Đăng nhập
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(2);
		
		// Verify popup isDisplayed
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("ABCD@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("ABCD");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(2);
		
		// Verify error message
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		// Close popup
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		
		// Verify login popup không hiển thị
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
	}

//	@Test
	public void TC_03_Login_Email_Password_Blank() {
		// Popup dạng close thì không còn hiển thị trong HTML
		
		driver.get("https://tiki.vn/");
		By loginPopup = By.cssSelector("div.ReactModal__Content");
		
		// Click Tài khoản button
		driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
		sleepInSecond(2);
		
		// Verify popup hiển thị
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		// Click Đăng nhập bằng email
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSecond(1);
		
		// Click button Đăng nhập
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(1);
		
		// Verify error Message
		assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span")).getText(), "Email không được để trống");
		assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span")).getText(), "Mật khẩu không được để trống");
		
		// Close popup
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(1);
		
		// Verify popup không còn hiển thị
			// Dùng findElements để tìm
			//findElements: Trả về 1 list: Tìm thấy thì sẽ trả về 1 list Element, không tìm thấy thì trả về list rỗng mà không đánh fail testcase
			//findElement: Trả về 1 Element: Tìm thấy và trả về element đầu tiên, không tìm thấy thì đánh fail testcase và throw exception: NoSuchElementException
			// findElement/ findElements đều chịu ảnh hưởng của implicitWait nên case không tìm thấy element sẽ chờ hết time
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
	}

	@Test
	public void TC_04_Popup_Not_In_HTML() {
		driver.get("https://www.facebook.com/");
		
		By signPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		// Verify popup hiển thị
		Assert.assertTrue(driver.findElement(signPopup).isDisplayed());
		
		// Click Đăng ký button
		driver.findElement(By.xpath("//button[@name='websubmit']")).click();
		sleepInSecond(2);
		
		// Verify icon error FirstName hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='lastname']/parent::div/following-sibling::i[contains(@class,'_5dbc')]")).isDisplayed());
		
		// Click icon error FirstName
		driver.findElement(By.xpath("//input[@name='lastname']/parent::div/following-sibling::i[contains(@class,'_5dbc')]")).click();
		
		// Veridy NoteMessage và icon error FirstName không hiển thị
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name='lastname']/parent::div/following-sibling::i[contains(@class,'_5dbc')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'uiContextualLayerBelowLeft')]")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'uiContextualLayerBelowLeft')]/div/div")).getText(), "What's your name?");
		
		// Close popup
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		
		// Verify popup k hiển thị
		Assert.assertEquals(driver.findElements(signPopup).size(), 0);
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