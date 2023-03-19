package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Select select;
//	WebDriverWait explicitWait;
//	Actions action;
	String firstname, lastName, email, password, companyName;
	String date, month, year;
	Random rand = new Random();
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		// Khởi tạo driver
		driver = new FirefoxDriver();
		
		// Khi khởi tạo cần biến driver
		// Tham số khi khởi tạo thư viện sẽ quyết định nó được khởi tạo ở vị trí nào
	//		explicitWait = new WebDriverWait(driver, 30);
	//		action  = new Actions(driver);
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		firstname = "Tom";
		lastName = "A";
		email = "Tom" + rand.nextInt(9999) + "@gmail.com";
		password = "Abc@1234";
		companyName = "JSKA";
		
		date = "11";
		month = "May";
		year = "1994";
		
	}

//	@Test1
	public void TC_01_Facebook() {
		driver.get("https://www.facebook.com/reg");
		select = new Select(driver.findElement(By.cssSelector("select#day")));

//		Chọn item:
		
//		<select name="tinhThuongTru" id="thuongtru_tinhthanh" class="egov-select   " style="width:99%; display:block;">	
//		<option id="Chọn" value="">Tỉnh/TP</option>		
//		<option value="20602">Cục trưởng cục cảnh sát Quản lý hành chính và trật tự xã hội</option> 
//		<option value="11433">thành phố Cần Thơ</option>	
//		<option value="1">thành phố Hà Nội</option>		
//		<option value="4091">thành phố Hải Phòng</option> 
//		<option value="9806">thành phố Hồ Chí Minh</option>
	
//		Không dùng vì khi có sự thay đổi thì index có thể k đúng nữa
// 		Khi cần reproduce lỗi lại => Khó
//		select.selectByIndex(3); // => thành phố Hà Nội 
		
//		Khi cần reproduce lỗi lại => Khó
//		value không phải là tham số bắt buộc
//		select.selectByValue("9806"); // => thành phố Hồ Chí Minh
		
//		Giống hành vi của end-user chọn
		select.selectByVisibleText("thành phố Hải Phòng");
		
//		Kiểm tra 1 dropdown là single/ multioe
//		select.isMultiple();
		// Kiểm tra 1 dropdown là multipe
		Assert.assertTrue(select.isMultiple());
		
		// Kiểm tra 1 dropdown là single
		Assert.assertFalse(select.isMultiple());
		
//		Vali giá trị đã chọn
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "thành phố Hồ Chí Minh");
		
//		Lấy ra tất cả các items(option)
		List<WebElement> city = select.getOptions(); // => số item (66)
				
		Assert.assertEquals(city.size(), 66);
		
		for (WebElement text : city) {
			System.out.println(text.getText());
		}
		
	}

//	@Test
	public void TC_02_() {
		driver.get("https://www.facebook.com/reg");
		
		// Chọn ngày
		select = new Select(driver.findElement(By.cssSelector("select#day")));
		select.selectByVisibleText("11");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "11");
		sleepInSecond(3);
		
		// Chọn tháng
		select = new Select(driver.findElement(By.cssSelector("select#month")));
		select.selectByVisibleText("Jun");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Jun");
		sleepInSecond(3);
		
		// Chọn năm
		select = new Select(driver.findElement(By.cssSelector("select#year")));
		select.selectByVisibleText("2001");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "2001");
		sleepInSecond(3);
	}

	@Test
	public void TC_03_NopCommerce() {
		driver.get("https://demo.nopcommerce.com/register");
		
		driver.findElement(By.id("FirstName")).sendKeys(firstname);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
//		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
//		select.selectByVisibleText(date);		
		new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).selectByVisibleText(date);;
		
//		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
//		select.selectByVisibleText(month);
		new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).selectByVisibleText(month);;
		
//		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
//		select.selectByVisibleText(year);		
		new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).selectByVisibleText(year);;
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.registration-result-page div.result")).getText(), "Your registration completed");
		
		driver.findElement(By.cssSelector("a.ico-login")).click();
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
		
		driver.findElement(By.cssSelector("button.login-button")).click();
		sleepInSecond(3);

		driver.findElement(By.cssSelector("a.ico-account")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), lastName);
		
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).getFirstSelectedOption().getText(), date);
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).getFirstSelectedOption().getText(), year);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), companyName);
		
		// Verify số lượng item của dropdown
		// new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).getOptions().size();
		
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).getOptions().size(), 13);
		
//		List<WebElement> months = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).getOptions();
//		Assert.assertEquals(months.size(), 13);
//		
//		for (WebElement month : months) {
//			System.out.println(month.getText());
//		}
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