package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Custom_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Select select;
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Jquery() {
		// HTML Code
		// Default Dropdown: Thư viện Select của selenium
			// Thẻ select: cha
			// Thẻ option: Item con bên trong
		
		// Custom Dropdown: Tự viết hàm dựa vào hành vi của dropdown
			// UnexpectedTagNameException - when element is not a SELECT
			// thẻ div/ span/ ul/ li,...: Tùy biến từ Framework sử dụng: Jquery, boostrap, Angular/ React/VueJS...
		
		// Hành vi:
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
//			// 1. Click vào 1 thẻ để xổ hết các item
//		driver.findElement(By.cssSelector("span#number-button")).click();
//		
//			// Chờ cho tất cả items được load ra hết (trong vòng 30s)
//		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu>li>div")));
//				// Lấy tất cả items trong fropdown lưu vào trong list
//		List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu>li>div"));
		
//				// For index
//					// Duyệt qua từng item
//		for (int i = 0; i < allItems.size(); i++) {
//					// Get Text của từng item
//			String itemText = allItems.get(i).getText();
//					// Kiểm tra text đúng với cái cần chọn
//			if (itemText.equals("5")) {
//					// Click
//				allItems.get(i).click();
//					// Thoát vòng lặp
//				break;
//			}
//		}
//		
				// Foreach
					// Duyệt qua từng item
//		for (WebElement tempElement : allItems) {
//					// Get Text từng item
//			String itemTexxt = tempElement.getText();
//					// Kiểm tra text đúng với cái cần chọn
//			if (itemTexxt.equals("10")) {
//					// Click
//				tempElement.click();
//					// Thoát vòng lặp
//				break;
//			}
//		}
				// Get text của từng item
				 // Kiểm tra text đúng với cái cần chọn
				// Click
		
			// Nếu item cần chọn nó hiển thị thì chọn luôn được
		
			// Nếu như item cần chọn n chưa hiển thị thì cần scroll xuống cho đến khi thấy
			// Click chọn item
		
		// 1. Vòng lặp: for
		// 2. Điều kiện: If
		// 3. Break
		// 4. Wait: Explicit wait
		// 5. Thư viện JS Executor: Scroll xuống
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "2");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "2");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "5");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "5");
		
//		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "8");
//		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "8");
				
//		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "10");
//		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "10");
//		
//		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "15");
//		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "15");
		
		selectItemInCustomDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']/li/div", "Faster");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Faster");
		
		selectItemInCustomDropdown("//span[@id='salutation-button']", "//ul[@id='salutation-menu']/li/div", "Dr.");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Dr.");
	}

//	@Test
	public void TC_02_Edittable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdown("//input[@class='search']", "//div[@role='option']/span", "Belgium");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");
		
		selectItemInCustomDropdown("//input[@class='search']", "//div[@role='option']/span", "America Samoa");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "America Samoa");
	}

	@Test
	public void TC_03_NopCommerce() {

	}
	
	public void editItemInCustomDropdown(String xpathTextbox, String xpathChild, String expectedText) {
		// 1. Click vào 1 thẻ để xổ hết các item
				driver.findElement(By.xpath(xpathTextbox)).clear();
				driver.findElement(By.xpath(xpathTextbox)).sendKeys(expectedText);
				
					// Chờ cho tất cả items được load ra hết (trong vòng 30s)
				explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathChild)));
						// Lấy tất cả items trong fropdown lưu vào trong list
				List<WebElement> allItems = driver.findElements(By.xpath(xpathChild));
				
						// Foreach
							// Duyệt qua từng item
				for (WebElement tempElement : allItems) {
							// Get Text từng item
					String itemTexxt = tempElement.getText();
							// Kiểm tra text đúng với cái cần chọn
					if (itemTexxt.equals(expectedText)) {
							// Scroll tới element
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tempElement);
						sleepInSecond(1);
							// Click
						tempElement.click();
						sleepInSecond(1);
							// Thoát vòng lặp
						break;
					}
				}
	}
	
	public void selectItemInCustomDropdown(String xpathParent, String xpathChild, String expectedText) {
		// 1. Click vào 1 thẻ để xổ hết các item
				driver.findElement(By.xpath(xpathParent)).click();
				
					// Chờ cho tất cả items được load ra hết (trong vòng 30s)
				explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathChild)));
						// Lấy tất cả items trong fropdown lưu vào trong list
				List<WebElement> allItems = driver.findElements(By.xpath(xpathChild));
				
						// Foreach
							// Duyệt qua từng item
				for (WebElement tempElement : allItems) {
							// Get Text từng item
					String itemTexxt = tempElement.getText();
							// Kiểm tra text đúng với cái cần chọn
					if (itemTexxt.equals(expectedText)) {
							// Scroll tới element
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tempElement);
						sleepInSecond(1);
							// Click
						tempElement.click();
						sleepInSecond(1);
							// Thoát vòng lặp
						break;
					}
				}
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