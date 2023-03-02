package webdriver;


import java.io.File;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_WebElement_Commands {
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
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_() {
		// Element: textbox/ textarea/ dropdown/ checkbox/...
		
		// 1 - Chỉ tương tác lên element 1 lần (không cần khai báo biến)
		driver.findElement(By.id("send2"));
		
		// 2 - Element này dùng lại nhiều lần (chỉ trong page hiện tại) (khai báo biến)
		WebElement loginButton = driver.findElement(By.id("send2"));
		
		List<WebElement> textboxes = driver.findElements(By.xpath("//input[@type='text']"));
				
		// By loginButtonBy = By.name("login");
		
		loginButton.isDisplayed();
		loginButton.getCssValue("");
		loginButton.click(); //**
		
		
		// Xóa dữ liệu trong 1 textbox/ textarea/ dropdown (edittable)
		driver.findElement(By.id("")).clear(); //**
		
		// Nhập dữ liệu vào trong 1 textbox/ textarea/ dropdown (edittable)
		driver.findElement(By.id("")).sendKeys(""); //**
		
		//
		driver.findElement(By.xpath("//div[@class='footer]")).findElement(By.xpath("//a[text()='My Account]"));
		
		// Lấy ra placeholder
		driver.findElement(By.id("serch")).getAttribute("placeholder"); //**
		// lấy ra value
		driver.findElement(By.id("serch")).getAttribute("value");
		
		// GUI: Font/ size, color/ positon/ location,...
		// Ưu tiên thấp - ít apply để làm auto
		// lấy ra màu 
			//#09e740 -> Hexa
			// rgb(9, 231, 64) -> RGB
		driver.findElement(By.id("send2")).getCssValue("backgroud-color"); //*
		driver.findElement(By.id("send2")).getCssValue("font-size");
		
		// Lấy ra kích thước của element: cao/ rộng
		Dimension loginButtonSize = driver.findElement(By.id("send2")).getSize();
		
		// Lấy ra tọa độ bên ngoài so với độ phân giải màn hình
		Point loginButtonLocation = driver.findElement(By.id("send2")).getLocation();
		loginButtonLocation.getX();
		
		// Lấy ra cả kích thước/ tọa độ
		Rectangle loginButtonRect = driver.findElement(By.id("send2")).getRect();
		loginButtonRect.getDimension();
		loginButtonRect.getX();
		loginButtonRect.getPoint();
		
		// Report HTML + Take ScreenShot
		File screenshotFile = driver.findElement(By.id("send2")).getScreenshotAs(OutputType.FILE); //*
		String screenshotBase64 = driver.findElement(By.id("send2")).getScreenshotAs(OutputType.BASE64); //*
		
		// Lấy ra tên thẻ khi dùng các locator mà không biết trước tên thẻ là gì
		String searchTextboxTagname = driver.findElement(By.cssSelector("#email")).getTagName();
		driver.findElement(By.xpath("//" + searchTextboxTagname + "[@id='email']"));
		
		// Lấy ra text của chính nó và các thẻ con của nó
		driver.findElement(By.id("send2")).getText(); //**
		
		// Áp dụng cho tất cả Element
			// 1 element có hiển thị trên màn hình hay không
			// Nhìn thấy được,có kích thước cụ thể
		driver.findElement(By.id("send2")).isDisplayed(); //**
		
		// Áp dụng cho tất cả Element
			// 1 element thao tác được trên màn hình hay không (disable+ -> ReadOnly		
		driver.findElement(By.id("send2")).isEnabled();
		
		// Áp dụng cho 3 loại element: checkbox, radio, dropdown (Select) 
		driver.findElement(By.id("send2")).isSelected(); //*
		
		// Chỉ apply cho form/ element trong form
		// Thay thế cho hành vi click vào button
		driver.findElement(By.id("send2")).submit();
	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

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
}