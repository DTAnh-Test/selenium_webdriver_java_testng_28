package webdriver;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_Upload_File {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String sea = "Sea.jpg";
	String mountain = "Mountain.jpg";
	String water = "Thac nuoc.jpg";
	
//	String seaPath = projectPath + "\\uploadFiles\\" + sea; // Window là \\, MAC là //
	String seaPath = projectPath + File.separator + "uploadFiles" + File.separator + sea; // Áp dụng đc cho tất cả các hệ điều hành
	String mountainPath = projectPath + File.separator + "uploadFiles" + File.separator + mountain;
	String waterPath = projectPath + File.separator + "uploadFiles" + File.separator + water;
	
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
	// 4 cách upload:
		// Hàm sendkey của WebElement:
			// Không cần thao tác với Menu upload/ Open file Dialog (Desktop app)
			// Selenium chỉ thao tác được với các element của web app
			// Sendkey có thể đáp ứng được tất cả yêu cầu/ tiêu chí
		// AutoIT:
			// Mỗi 1 browser phải có 1 script khác nhau
			// Chỉ có thể chạy được trên Window
			// Upload nhiều file khó
			// Phải thao tác với Open Dialog
		// Java Robot:
			// Phải thao tác với Open Dialog
			// Có thể chạy cho tất cả browsers
			// Chỉ support cho Window (MAC/ Linux: Không có chỗ để nhập text vào Dialog)
			// Khó cho việc upload nhiều file
		// Sikuli:
			// 	Phải thao tác với Open Dialog
			// Cơ chế xử lý bằng hình ảnh => Dở vì mỗi độ phân giải màn hình khác nhau thì hình ảnh sẽ khác nhau
	// Yêu cầu/ tiêu chí:
		// Cần chạy được cho tất cả browsers
		// Cần chạy được cho tất cả OS (MAC/ Window/...)
		// Cần upload được 1 hoặc nhiều file cùng lúc
	
//	@Test
	public void TC_01_Upload_Single_File() {

		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(seaPath);
		sleepInSecond(4);
//		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='Sea.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + sea + "']")).isDisplayed());
		driver.findElement(By.cssSelector("table button.start")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ sea +"']")).isDisplayed());
	}

//	@Test
	public void TC_02_Upload_Single_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		By uploadFileBy = By.xpath("//input[@type='file']");
		driver.findElement(uploadFileBy).sendKeys(seaPath);
		sleepInSecond(2);
		driver.findElement(uploadFileBy).sendKeys(mountainPath);
		sleepInSecond(2);
		driver.findElement(uploadFileBy).sendKeys(waterPath);
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + sea + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + mountain + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + water + "']")).isDisplayed());
		
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(2);
			
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ sea +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ mountain +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ water +"']")).isDisplayed());
	}

	@Test
	public void TC_03_Upload_Multi_Files() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		By uploadFileBy = By.xpath("//input[@type='file']");
		driver.findElement(uploadFileBy).sendKeys(seaPath + "\n" + mountainPath + "\n" + waterPath);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + sea + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + mountain + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + water + "']")).isDisplayed());
		
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(2);
			
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ sea +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ mountain +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ water +"']")).isDisplayed());		
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