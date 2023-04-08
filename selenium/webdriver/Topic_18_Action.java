package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Action {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
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
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}

//	@Test
	public void TC_02_Tooltip() {
		driver.get("https://viettel.vn/");
		action.moveToElement(driver.findElement(By.xpath("//div[@class='menu-pc']//a[text()='Dịch vụ di động']"))).perform();
		sleepInSecond(2);
		
		action.click(driver.findElement(By.xpath("//div[@class='menu-pc']//a[text()='Mua sim số']"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//strong[text()='Mua sim số']")).getText(), "Mua sim số");
	}

//	@Test
	public void TC_03_Click_And_Hold_Block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// Cách 1: 
		// action.clickAndHold(driver.findElement(By.xpath("//li[text()='1']"))).moveToElement(driver.findElement(By.xpath("//li[text()='4']"))).release().perform();	
		// 1. Click chuột trái vào 1 số bắt đầu 
		// 2. Vẫn giữ chuột trái
//		action.clickAndHold(driver.findElement(By.xpath("//li[text()='1']")))
		// 3. Kéo chuột/ move chuột tới số kết thúc
//		.moveToElement(driver.findElement(By.xpath("//li[text()='4']")))
		// 4. Nhả chuột trái ra
//		.release()
		// 5. Thực thi các hành động trên
//		.perform();
//		sleepInSecond(3);
//		Assert.assertTrue(driver.findElement(By.xpath("//li[text()='1' and contains(@class,'ui-selected')]")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//li[text()='2' and contains(@class,'ui-selected')]")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//li[text()='3' and contains(@class,'ui-selected')]")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//li[text()='4' and contains(@class,'ui-selected')]")).isDisplayed());
		
		// Cách 2:
		// Nên lưu hết tất cả 12 số lại
		// Muốn thao tác số nào thì lôi số đó ra
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		action.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(3)).release().perform();
		sleepInSecond(2);
		List<WebElement> numberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(numberSelected.size(), 4);
	}

//	@Test
	public void TC_04_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		// Chạy được cả trên windown và mac
		Keys key = null;
		if (osName.contains("Windows")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
		}
		
		// 1. Nhấn phím Ctrl
//		action.keyDown(Key).perform();
		action.keyDown(Keys.CONTROL).perform();
		// 2. Click chọn các số
		action.click(allNumber.get(0)).click(allNumber.get(2)).click(allNumber.get(5)).click(allNumber.get(7)).click(allNumber.get(9));
		// 3. Nhả phím Ctrl ra
		action.keyUp(Keys.CONTROL).perform();
		sleepInSecond(2);
		
		List<WebElement> numberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(numberSelected.size(), 5);
	}

//	@Test
	public void TC_05_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Firefox nếu các element không nằm trong viewport thì thường không click được
		if (driver.toString().contains("Firefox")) {
			// Scroll tới element (Firefox)
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		}
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
	}

//	@Test
	public void TC_06_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		// Quit chưa hiển thị
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
		// Right Click vào button
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(2);
		
		// Hover chuột vào
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(2);
		
		// Quit sẽ update thêm trạng thái hover
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-hover")).isDisplayed());
		
		// Click vào quit
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(2);
		
		// Accept alert
		driver.switchTo().alert().accept();
		sleepInSecond(2);
		
		// Quit chưa hiển thị
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

	}
	
//	@Test
	public void TC_07_Drag_Drop_HTML4() {
		// Drag and drop được list vào những case không nên auto
		// Những case không nên auto: Captcha/ SMS/ OTP/ Bar Code/ QR Code/ Chart/ Canvas/ Drag Drop/ Game/ Flex/ Flash...
		// Những trang chuyên chống auto: Google/ Facebook
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		action.dragAndDrop(driver.findElement(By.cssSelector("div#draggable")), driver.findElement(By.cssSelector("div#droptarget"))).perform();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#droptarget")).getText(), "You did great!");
		
		String targetCircle = driver.findElement(By.cssSelector("div#droptarget")).getCssValue("background-color");
		// Chuyển từ RGB/ RGBA qua kiểu Color
		Color targetCircleColor = Color.fromString(targetCircle);
		// Color có hàm chuyển qua hexa
		// Nên chuyển qua viết hoa
		String targetCircleHexa = targetCircleColor.asHex().toUpperCase();
		// Verify màu background login button 
		Assert.assertEquals(targetCircleHexa, "#03A9F4");
	}
	
	@Test
	public void TC_08_Drag_Drop_HTML5() {
		driver.get("https://automationfc.github.io/drag-drop-html5/");

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