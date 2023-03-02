package webdriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.nodes.NodeId;

public class Topic_07_Web_Browser_Commands {
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
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_Browser() throws MalformedURLException {
		// Cacs command/ hàm để tương tac với Browser thì nó thông qua biến driver
		//driver.
		
		// Dùng để đóng tab hiện tại/ đóng browser nếu chỉ có 1 tab
		// Handle Windows/ tab
		driver.close(); //*
		
		// Không đóng tab ->  Đóng browser
		driver.quit(); //**
		
		// Tìm 1 Element với 1 locator nào đó (id/ class/ name/ css/ xpath/ ...)
		driver.findElement(By.id("")); //**
		
		// Tìm nhiều element với 1 locator nào đó
		// ex: Tìm all đường link/ textbox/ radio/ .. của page hiện tại
		driver.findElements(By.xpath("//a[@href]")); //**
		driver.findElements(By.xpath("//input[@type='checkbox']"));
		
		// Dùng để mở ra 1 URL nào đó
		driver.get("https://www.facebook.com/"); //**
		
		// Lấy ra Url của page hiện tại - Đang ở page nào sẽ lấy ra Url page đó
		
		driver.getCurrentUrl();
			// Cách 1: Dùng duy nhất 1 step
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/"); //*
		
			// Cách 2: Dùng cho nhiều hơn 1 step thì mới khai báo biến
				// Code rườm rà, tốn bộ nhớ
		String homeUrl = driver.getCurrentUrl();
		Assert.assertEquals(homeUrl, "https://www.facebook.com/");

		// Lấy ra code HTML/ CSS/ JS của page hiện tại
		driver.getPageSource();
		
		Assert.assertTrue(driver.getPageSource().contains("Please enter../"));
		
		// Lấy ra title của page hiện tại
		driver.getTitle();
		Assert.assertEquals(driver.getTitle(), "Mobile"); //*
		
		// Window/ Tab
			// Lấy ra ID của tab/ window hiện tại
		driver.getWindowHandle(); //*
			
			// Lấy ra tất cả ID của các tab/ window
		driver.getWindowHandles(); //*
		
		// Cookies
		Set<Cookie> cookies = driver.manage().getCookies();
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie); 
		}
		driver.navigate().refresh();
		
		driver.manage().deleteAllCookies(); //*
		
		// Lấy log trình duyệt
		driver.manage().logs().get(LogType.BROWSER);
		
		// Thời gian chờ cho element xuất hiện
			// 2 hàm: findElement/ findElements
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.DAYS); //**
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
		
		// Để chờ cho page được load xong trong vòng bao lâu
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.MINUTES);
		
		// Chờ 1 đoạn script được thực thi xog trong bao lâu
		// bài JavascriptExxcutor
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		// Windows
		driver.manage().window().maximize(); //**
		driver.manage().window().fullscreen();
		
			// Test GUI
			// Lấy vị trí ra
		Point point = driver.manage().window().getPosition();
		point.getX();
		point.getY();
			// Set tại 1 vị trí nào đó
		driver.manage().window().setPosition(new Point(1920,1080));
		
		
		Dimension dimension = driver.manage().window().getSize();
		dimension.getWidth();
		dimension.getHeight();
		
		// Set kích thước browser
		driver.manage().window().setSize(new Dimension(1240, 768));
		
		// Navigate: Điều hướng
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("https://www.facebook.com/");
		driver.navigate().to(new URL("https://www.facebook.com/"));
		
		driver.get("https://www.facebook.com/");
		
		// Window/ Tab
		// Frame/ IFrame
		// Alert
		driver.switchTo().alert(); //*
		driver.switchTo().frame(1); //*
		driver.switchTo().window("https://www.facebook.com/"); //*
	}

	@Test
	public void TC_02_Element() {
		// Cacs command/ hàm để tương tac với Element thì nó thông qua biến findElement
		// driver.findElement(By.xpath(""));
		
	}

	@Test
	public void TC_03_Tips() {
		// Chia ra 3 nhóm chính
		// Nhóm 1 - hàm đẻ tương tác/ action (click, sendKeys/ select/...): Tên hàm sẽ thể hiện chức năng của nó
			// Không trả về/ return dữ liệu
		driver.findElement(By.xpath("")).click();
		driver.findElement(By.xpath("")).sendKeys("");
		
		// Nhóm 2 - lấy ra dữ liệu cho mục đích nào đó (step tiếp theo/ hiện tại)
			// Nó sẽ bắt đầu bằng tiền đó là getXXX
			// getText/ getCurrentUrl/  getTitle/ getCssValue/ getAttribute/ getLocation/ getPosition/...
			// Trả về dữ liệu -> String
			// Dùng để kiểm tra mong đợi của dữ liệu thực tế bằng với dữ liệu mong muốn (equals) -> Hàm Assert
			// Assert (JUnit/ TestNG/ AssertJ/ Hamcrest/...)
		
		// Nhóm 3 - Dùng để kiểm tra dữ liệu
			// Dùng để kiểm tra tính đúng đắn của dữ liệu (true/ false) -> Hàm Assert
			// Trả về dữ liệu -> boolean
			// Assert (JUnit/ TestNG/ AssertJ/ Hamcrest/...)		
			// isDisplayed/ isEnabled/ isSelected/ isMultiple/...
		

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