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

public class SearchTest {
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
	
	//Searching Coursera courses for the subject I am interested in: Ruby
	//I should be displayed a page with courses on that topic and I know that 'Web Application Architectures'
	//is a course that covers Ruby in detail so it should appear on the search results
	@Test
	public void testSearchByKeyword() {
		try{
			driver.findElement(By.linkText("Find a Course")).click();
			driver.findElement(By.xpath("//input[@value='']")).sendKeys("Ruby");
			
			WebElement course = driver.findElement(By.linkText("Web Application Architectures"));
		    String observed = course.getText();
		    assertEquals(observed, "Web Application Architectures");
		}
		catch (NoSuchElementException ex){
			ex.printStackTrace();
			fail();
		}
	}
	
	//Search Coursera for CS courses taught by 'John Hart'
	//when I get results back "Data Visualization" should be part of them because it's the professor's course
	@Test
	public void testSearchByProfessor(){
		try{
			driver.findElement(By.linkText("Find a Course")).click();
			driver.findElement(By.cssSelector("input#categories_cs-programming.bt3-col-xs-1")).click();
			driver.findElement(By.xpath("//input[@value='']")).sendKeys("John Hart");
			
			WebElement course = driver.findElement(By.linkText("Data Visualization"));
			assertTrue(course != null);
		}
		catch (NoSuchElementException ex){
			ex.printStackTrace();
			fail();
		}
	}
	
	//search Coursera for classes taught in the German language
	//assuming I know that University of Zurich has partnered with Coursera to offer classes in German
	//I should find Informatik für Ökonomen which is offered by U. of Zurich
	@Test
	public void testSearchByLanguage(){
		try{
			driver.findElement(By.linkText("Find a Course")).click();
			driver.findElement(By.cssSelector("input#languages_de.bt3-col-xs-1")).click();
			WebElement universityClass = driver.findElement(By.linkText("Informatik für Ökonomen"));
			
			assertTrue(universityClass != null);
		}
		catch (NoSuchElementException ex){
			ex.printStackTrace();
			fail();
		}
	}
	
	//search Coursera for something completely unrelated to courses
	//such as 'potatoes' and I should be shown that there were no matching results
	@Test
	public void testSearchRandomly() throws InterruptedException{
		try{
			driver.findElement(By.linkText("Find a Course")).click();
			driver.findElement(By.xpath("//input[@value='']")).sendKeys("potatoes");
			/*
			 * I know I am not really supposed to use Thread.sleep() within a test but here I am only waiting for a
			 * second to wait for a javascript call to finish so that the values update because Coursera exclusively
			 * uses ajax to return results and no actual form submission. I couldn't find a better way to accomplish
			 * this so I hope this is ok.
			 */
			Thread.sleep(1000);
			WebElement errorMessage = driver.findElement(By.cssSelector("h2.c-courseList-courseListing-header"));
			
			assertEquals("No results found", errorMessage.getText());
		}
		catch (NoSuchElementException ex){
			ex.printStackTrace();
			fail();
		}
	}
	
	//search Coursera for a course whose title I know
	@Test
	public void testSearchByCourse(){
		try{
			driver.findElement(By.linkText("Find a Course")).click();
			driver.findElement(By.xpath("//input[@value='']")).sendKeys("Introduction to Programming with MATLAB");
			
			WebElement course = driver.findElement(By.linkText("Introduction to Programming with MATLAB"));
			assertFalse(course == null);
		}
		catch (NoSuchElementException ex){
			ex.printStackTrace();
			fail();
		}
	}
	
	//search Coursera for courses based on subject 'physics'
	@Test
	public void testSearchBySubject(){
		try{
			driver.findElement(By.linkText("Find a Course")).click();
			driver.findElement(By.cssSelector("input#categories_physics.bt3-col-xs-1")).click();
			WebElement quantumPhysics = driver.findElement(By.partialLinkText("Quantum Physics"));
			
			assertTrue(quantumPhysics!= null);
		}
		catch (NoSuchElementException ex){
			ex.printStackTrace();
			fail();
		}
	}
	
	//search Coursera for courses offered by the University of Pittsburgh
	@Test
	public void testSearchByUniversity(){
		try{
			driver.findElement(By.linkText("Find a Course")).click();
			driver.findElement(By.xpath("//input[@value='']")).sendKeys("University of Pittsburgh");
			
			//I know the University of Pittsburgh offers classes on Coursera so the header below should say Courses instead of No results Found
			WebElement courses = driver.findElement(By.cssSelector("h2.c-courseList-courseListing-header"));
			assertEquals("Courses", courses.getText());
		}
		catch (NoSuchElementException ex){
			ex.printStackTrace();
			fail();
		}
	}
	
	@After
	public void tearDown() throws Exception {
		 driver.findElement(By.linkText("Coursera-Tes…")).click();
		 driver.findElement(By.linkText("Sign Out")).click();
		 driver.quit();
	}
	
}
