import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SettingTest {
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://www.coursera.org/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// go into Coursera as part of the test set up
		driver.get(baseUrl);
	    driver.findElement(By.linkText("Log In")).click();
	    driver.findElement(By.xpath("(//input[@id='user-modal-email'])[2]")).clear();
	    driver.findElement(By.xpath("(//input[@id='user-modal-email'])[2]")).sendKeys("courseratestcs1699@gmail.com");
	    driver.findElement(By.xpath("(//input[@id='user-modal-password'])[2]")).clear();
	    driver.findElement(By.xpath("(//input[@id='user-modal-password'])[2]")).sendKeys("15spring");
	    driver.findElement(By.xpath("//div[2]/div/div/form/button")).click();
	}

	// adding text to the "about me" of the profile
	@Test
	public void testProfileChangeAboutMe() {
	    driver.findElement(By.xpath("//a[contains(text(),'Coursera')]")).click();
	    driver.findElement(By.linkText("Profile")).click();
	    driver.findElement(By.linkText("Edit Profile")).click();
	    driver.findElement(By.name("bio")).clear();
	    driver.findElement(By.name("bio")).sendKeys("This is information");

		WebElement bio = driver.findElement(By.cssSelector("textarea.large"));
		String observed = bio.getText();
		assertEquals(observed, "This is information");

	}
	
	// adding text location of the user in the profile
	@Test
	public void testProfileChangeLocation() {
		driver.findElement(By.xpath("//a[contains(text(),'Coursera')]")).click();
	    driver.findElement(By.linkText("Profile")).click();
	    driver.findElement(By.linkText("Edit Profile")).click();
	    driver.findElement(By.id("coursera-profile-editor-location")).clear();
	    driver.findElement(By.id("coursera-profile-editor-location")).sendKeys("Pittsburgh, PA, United States");
	    driver.findElement(By.cssSelector("a.internal-home.btn")).click();

		WebElement location = driver.findElement(By.cssSelector("span.coursera-profile-location"));
		String observed = location.getText();
		assertEquals(observed, "Pittsburgh, PA, United States");

	}
	
	// changing the timezone in the user settings. I was not sure how to test 
	// the drop down. The "getText()" and "getAttribute()" method calls do not bring in any
	// result because they are not populated. This form is using JQuery 
	@Test
	public void testSettingTimeZoneChange() {
		driver.findElement(By.xpath("//a[contains(text(),'Coursera')]")).click();
	    driver.findElement(By.linkText("Settings")).click();
	    new Select(driver.findElement(By.id("coursera-settings-timezone-change"))).selectByVisibleText("New York");
	    driver.findElement(By.cssSelector("button.btn.coursera-settings-timezone-change")).click();
	    
	    WebElement timeZone = driver.findElement(By.id("coursera-settings-timezone-change"));
	    String observed = timeZone.getText();
	    assertTrue(observed.contains("New York"));
	}
	
	// changing the account name to "Coursera-Test" and retrieving that text
	// comparing the observed vs expected result 
	@Test 
	public void testSettingAccountName() {
		driver.findElement(By.xpath("//a[contains(text(),'Coursera')]")).click();
	    driver.findElement(By.linkText("Settings")).click();
	    driver.findElement(By.id("coursera-settings-name")).clear();
	    driver.findElement(By.id("coursera-settings-name")).sendKeys("Coursera-Test");
	    driver.findElement(By.cssSelector("button.btn.coursera-settings-name-change")).click();
	    
	    WebElement name = driver.findElement(By.id("coursera-settings-name"));
	    String observed = name.getAttribute("value");
	    assertEquals(observed, "Coursera-Test");
	}
	

	@After
	public void tearDown() throws Exception {
		 driver.findElement(By.cssSelector("a.coursera-header-link.coursera-header-account")).click();
		 List<WebElement> webList = new ArrayList<WebElement>();
		 webList = driver.findElements(By.cssSelector("a.coursera-header-link.coursera-header-account"));
		 webList.get(1).click();
		 driver.findElement(By.linkText("Sign Out")).click();
		 driver.quit();
	}

}
