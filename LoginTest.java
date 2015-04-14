import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class LoginTest {
	private WebDriver driver;
	private String url;
	
	//set up code for the waiting, url, and creating a new Firefox driver
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
	    url = "http://www.coursera.org";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	//tests the ability to log in correctly to Coursera
	//if I have the correct username and password combination then the website should let me log in successfully
	@Test
	public void testCorrectLogin() {
		driver.get(url + "/");
		try{
			driver.findElement(By.linkText("Log In")).click();
			driver.findElement(By.xpath("(//input[@id='user-modal-email'])[2]")).clear();
			driver.findElement(By.xpath("(//input[@id='user-modal-email'])[2]")).sendKeys("courseratestcs1699@gmail.com");
			driver.findElement(By.xpath("(//input[@id='user-modal-password'])[2]")).clear();
			driver.findElement(By.xpath("(//input[@id='user-modal-password'])[2]")).sendKeys("15spring");
			driver.findElement(By.xpath("//div[2]/div/div/form/button")).click();
			WebElement dashboard = driver.findElement(By.className("c-dashboard-header"));
			assertEquals(dashboard.getText(),"My Courses");
		}
		catch (NoSuchElementException ex){
			ex.printStackTrace();
			fail();
		}
	}
	
	//tests that when I try to log in with the wrong password, then the website will still
	//stay on the log in page and will have the same email and password inputs
	@Test
	public void testWrongPassword(){
		driver.get(url + "/");
		try{
			driver.findElement(By.linkText("Log In")).click();
			driver.findElement(By.xpath("(//input[@id='user-modal-email'])[2]")).clear();
			driver.findElement(By.xpath("(//input[@id='user-modal-email'])[2]")).sendKeys("courseratestcs1699@gmail.com");
			driver.findElement(By.xpath("(//input[@id='user-modal-password'])[2]")).clear();
			driver.findElement(By.xpath("(//input[@id='user-modal-password'])[2]")).sendKeys("spring15");
			driver.findElement(By.xpath("//div[2]/div/div/form/button")).click();
			WebElement password = driver.findElement(By.xpath("(//input[@id='user-modal-password'])[2]"));
			assertTrue(password != null);
		}
		catch (NoSuchElementException ex){
			ex.printStackTrace();
			fail();
		}
	}
	
	//tests that when the user tries to log in with the incorrect email and correct password
	//that the website lets the user know they have either the wrong email or the wrong password
	@Test
	public void testWrongUsername(){
		driver.get(url + "/");
		try{
			driver.findElement(By.linkText("Log In")).click();
			driver.findElement(By.xpath("(//input[@id='user-modal-email'])[2]")).clear();
			driver.findElement(By.xpath("(//input[@id='user-modal-email'])[2]")).sendKeys("amazontestcs1699@gmail.com");
			driver.findElement(By.xpath("(//input[@id='user-modal-password'])[2]")).clear();
			driver.findElement(By.xpath("(//input[@id='user-modal-password'])[2]")).sendKeys("15spring");
			driver.findElement(By.xpath("//div[2]/div/div/form/button")).click();
			WebElement email = driver.findElement(By.xpath("(//input[@id='user-modal-email'])[2]"));
			assertTrue(email != null);
		}
		catch (NoSuchElementException ex){
			ex.printStackTrace();
			fail();
		}
	}
	
	//tests that I am able to successfully log out of the service once I click the
	//sign out button within the coursera drop down menu. I should be at the home page but with the buttons to log in or sign out.
	@Test
	public void testLogout(){
		driver.get(url + "/");
		try{
			driver.findElement(By.linkText("Log In")).click();
			driver.findElement(By.xpath("(//input[@id='user-modal-email'])[2]")).clear();
			driver.findElement(By.xpath("(//input[@id='user-modal-email'])[2]")).sendKeys("courseratestcs1699@gmail.com");
			driver.findElement(By.xpath("(//input[@id='user-modal-password'])[2]")).clear();
			driver.findElement(By.xpath("(//input[@id='user-modal-password'])[2]")).sendKeys("15spring");
			driver.findElement(By.xpath("//div[2]/div/div/form/button")).click();
			driver.findElement(By.xpath("//a[contains(text(),'Coursera')]")).click();
		    driver.findElement(By.linkText("Sign Out")).click();
		    WebElement link = driver.findElement(By.linkText("Sign Up"));
		    assertTrue(link != null);
		}
		catch (NoSuchElementException ex){
			ex.printStackTrace();
			fail();
		}
	}
	
	//tear down code that will close the driver
	@After
	public void tearDown() {
		driver.quit();
	}
}