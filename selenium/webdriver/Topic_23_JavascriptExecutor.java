package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_JavascriptExecutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	WebDriverWait expliciWait;
	
	String emailAddress = "auto" + rand.nextInt(9999) + "@gmail.com";
	String firstname = "John";
	String lastname = "Witch";
	String fullname = firstname + " " + lastname;
	String password = "123456";
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
//		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver; // Ép kiểu tường mình
		
		// Trạng thái của element: Huển thị/ không hiển thị/ presence/ staleness
		expliciWait = new WebDriverWait(driver,30);
		
		// Apply cho việc tìm element (findElement, findElements)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_LiveGuru() {
		navigateToUrlByJS("http://live.techpanda.org/"); // = driver.get("http://live.techpanda.org/");
		sleepInSecond(3);
		
		String liveGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(liveGuruDomain, "live.techpanda.org");
		
		String liveGuruURL = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(liveGuruURL, "http://live.techpanda.org/");
		
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);
		
		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(3);
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(3);
		String customerServiceTitle= (String) executeForBrowser("return document.title;");
		Assert.assertEquals(customerServiceTitle, "Customer Service");
		
		hightlightElement("//input[@id='newsletter']");
		scrollToElementOnTop("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", emailAddress);
		hightlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(3);
		String demoGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(demoGuruDomain, "demo.guru99.com");
	}

//	@Test
	public void TC_02_HTML5Validation() {
		navigateToUrlByJS("https://warranty.rode.com/register");
		sleepInSecond(3);
		clickToElementByJS("//button[text()=' Register ']");
//		driver.get("https://warranty.rode.com/register");
//		driver.findElement(By.xpath("//button[text()=' Register ']")).click();
		
		Assert.assertEquals(getElementValidationMessage("//input[@id='name']"), "Please fill out this field.");
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("XXXX");
		driver.findElement(By.xpath("//button[text()=' Register ']")).click();
		
		Assert.assertEquals(getElementValidationMessage("//input[@id='email']"), "Please fill out this field.");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("XXXX");
		driver.findElement(By.xpath("//button[text()=' Register ']")).click();
		Assert.assertEquals(getElementValidationMessage("//input[@id='email']"), "Please enter an email address.");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//button[text()=' Register ']")).click();
		
		Assert.assertEquals(getElementValidationMessage("//input[@id='password']"), "Please fill out this field.");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("XXXX");
		driver.findElement(By.xpath("//button[text()=' Register ']")).click();
		Assert.assertEquals(getElementValidationMessage("//input[@id='password_confirmation']"), "Please fill out this field.");
		
	}

	@Test
	public void TC_03_Techpanda_Register() {
//		driver.get("http://live.techpanda.org/index.php/");
		navigateToUrlByJS("http://live.techpanda.org/index.php/");
		
//		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		hightlightElement("//div[@class='footer']/div//a[@title='My Account']");
		clickToElementByJS("//div[@class='footer']/div//a[@title='My Account']");
		
//		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		hightlightElement("//a[@title='Create an Account']");
		clickToElementByJS("//a[@title='Create an Account']");
		
//		driver.findElement(By.id("firstname")).sendKeys(firstname);
		sendkeyToElementByJS("//input[@id='firstname']", firstname);
		
//		driver.findElement(By.id("lastname")).sendKeys(lastname);
		sendkeyToElementByJS("//input[@id='lastname']", lastname);		
		
//		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		sendkeyToElementByJS("//input[@id='email_address']", emailAddress);	
		
//		driver.findElement(By.id("password")).sendKeys(password);
		sendkeyToElementByJS("//input[@id='password']", password);			
		
//		driver.findElement(By.id("confirmation")).sendKeys(password);
		sendkeyToElementByJS("//input[@id='confirmation']", password);		
		
//		driver.findElement(By.cssSelector("button[title='Register']")).click();
		clickToElementByJS("//button[@title='Register']");
		 
//		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		Assert.assertTrue(getInnerText().contains("Thank you for registering with Main Website Store."));
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for registering with Main Website Store."));
		
//		String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		
//		Assert.assertTrue(contactInfo.contains(fullname));
		Assert.assertTrue(areExpectedTextInInnerText(fullname));
		
//		Assert.assertTrue(contactInfo.contains(emailAddress));
		Assert.assertTrue(areExpectedTextInInnerText(emailAddress));
		
//		driver.findElement(By.xpath("//a[text()='Account Information']")).click();
		clickToElementByJS("//a[text()='Account Information']");
		
		Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), firstname);
		
		Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"), lastname);
		
		Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), emailAddress);
		 
//		driver.findElement(By.cssSelector("a.skip-account span.label")).click();
		hightlightElement("//a[@class='skip-link skip-account']/span[@class='label']");
		clickToElementByJS("//a[@class='skip-link skip-account']/span[@class='label']");
		
//		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
		hightlightElement("//a[@title='Log Out']");
		clickToElementByJS("//a[@title='Log Out']");
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.page-title img")));
		Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title img")).isDisplayed()); 
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
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		sleepInSecond(3);
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}
	
	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}
	
	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
}