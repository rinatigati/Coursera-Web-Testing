import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CoursesTest{
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
	
	//add Social Psychology to my courses
	@Test
	public void testAddCourse(){
		try {
			driver.findElement(By.linkText("Find a Course")).click();
			driver.findElement(By.xpath("//input[@value='']")).sendKeys("Wesleyan University");
			driver.findElement(By.linkText("Social Psychology")).click();
			driver.findElement(By.linkText("Join for Free!")).click();
			driver.findElement(By.linkText("Go to course")).click();
			driver.findElement(By.linkText("Coursera-Test")).click();
			driver.findElement(By.linkText("My Courses")).click();
			
			WebElement psych = driver.findElement(By.linkText("Social Psychology"));
			//for some reason to click on some of the javascript drown down menus I have to do get first and then try to find it
			driver.get(baseUrl + "/");
			driver.findElement(By.xpath("//div[2]/div/div/div/em")).click();
			driver.findElement(By.linkText("Leave Course")).click();
			driver.findElement(By.xpath("//div[2]/button[2]")).click();
			assertTrue(psych != null);
		}
		catch (NoSuchElementException ex){
			ex.printStackTrace();
			fail();
		}
	}
	
	//deleting Social Psychology after already having added it
	@Test
	public void testDeleteCourse() throws InterruptedException{
		try{
			driver.findElement(By.linkText("Find a Course")).click();
			driver.findElement(By.xpath("//input[@value='']")).sendKeys("Wesleyan University");
			driver.findElement(By.linkText("Social Psychology")).click();
			driver.findElement(By.linkText("Join for Free!")).click();
			driver.findElement(By.linkText("Go to course")).click();
			driver.findElement(By.linkText("Coursera-Test")).click();
			driver.findElement(By.linkText("My Courses")).click();
			
			driver.get(baseUrl + "/");
			driver.findElement(By.xpath("//div[2]/div/div/div/em")).click();
			driver.findElement(By.linkText("Leave Course")).click();
			driver.findElement(By.xpath("//div[2]/button[2]")).click();
			
			//javascript takes a bit to change the page and I haven't found a better way to wait for a 1 second before continuing
			Thread.sleep(1000);
			WebElement emptyMessage = driver.findElement(By.className("c-dashboard-empty-message"));
			assertEquals("You haven't signed up for any courses yet.", emptyMessage.getText());
		}
		catch (NoSuchElementException ex){
			ex.printStackTrace();
			fail();
		}
	}
	
	//continuing a course after adding it
	@Test
	public void testContinueCourse(){
		try{
			driver.findElement(By.linkText("Find a Course")).click();
			driver.findElement(By.xpath("//input[@value='']")).sendKeys("Wesleyan University");
			driver.findElement(By.linkText("Social Psychology")).click();
			driver.findElement(By.linkText("Join for Free!")).click();
			driver.findElement(By.linkText("Go to course")).click();
			driver.findElement(By.linkText("Coursera-Test")).click();
			driver.findElement(By.linkText("My Courses")).click();
			
			driver.get(baseUrl + "/");
			driver.findElement(By.linkText("Resume")).click();
			WebElement quiz = driver.findElement(By.linkText("Take the Snapshot Quiz"));
			
			//deleting course
			driver.get(baseUrl + "/");
			driver.findElement(By.xpath("//div[2]/div/div/div/em")).click();
			driver.findElement(By.linkText("Leave Course")).click();
			driver.findElement(By.xpath("//div[2]/button[2]")).click();
			
			assertFalse(quiz == null);
		}
		catch (NoSuchElementException ex){
			ex.printStackTrace();
			fail();
		}
	}
	
	//checking the start date for a course I have already registered for
	@Test
	public void testCheckCourse(){
		try{
			driver.findElement(By.linkText("Find a Course")).click();
			driver.findElement(By.xpath("//input[@value='']")).sendKeys("Enhance Your Career and Employability Skills");
			driver.findElement(By.linkText("Enhance Your Career and Employability Skills")).click();
			driver.findElement(By.linkText("Join for Free!")).click();
			driver.get(baseUrl + "/");
			driver.findElement(By.linkText("Upcoming")).click();
			WebElement startDate = driver.findElement(By.className("c-dashboard-course-progress-ended"));
			
			driver.findElement(By.xpath("//div[2]/div/div/div/em")).click();
		    driver.findElement(By.linkText("Leave Course")).click();
		    driver.findElement(By.xpath("//div[2]/button[2]")).click();
		    
			assertEquals("Starts in 2 months", startDate.getText());
		}
		catch (NoSuchElementException ex){
			ex.printStackTrace();
			fail();
		}
	}
	
	//exploring certificate costs for a class I am already enrolled in
	@Test
	public void testCourseCertificate(){
		driver.findElement(By.linkText("Find a Course")).click();
		driver.findElement(By.xpath("//input[@value='']")).sendKeys("Programming for Everybody");
		driver.findElement(By.linkText("Programming for Everybody (Python)")).click();
		driver.findElement(By.linkText("Join for Free!")).click();
		driver.findElement(By.linkText("Learn more »")).click();
		
		WebElement price = driver.findElement(By.className("coursera-signature-course-card-price-display"));
	    assertEquals("$4900", price.getText());
	}
	
	@After
	public void tearDown() throws Exception {
		 driver.get(baseUrl + "/");
		 driver.findElement(By.linkText("Coursera-Tes…")).click();
		 driver.findElement(By.linkText("Sign Out")).click();
		 driver.quit();
	}
}