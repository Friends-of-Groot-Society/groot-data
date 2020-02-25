package net.groot.data;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNgLogin {

	WebDriver driver;

	@Test(priority=1)
	public void findLoginPage() throws Throwable {
		boolean title = driver.findElement(By.className("page-title")).isDisplayed();
		Assert.assertEquals(true, title);
	}
	
	@Test(priority=2)
	public void findLoginElement() throws Throwable {
		WebElement title = driver.findElement(By.className("page-title"));
		Assert.assertEquals("Sign In", title.getText());
	}
	@Test(priority=3, dependsOnMethods={"findLoginPage", "findLoginElement"})
	public void passInEmail() throws Throwable {

		 driver.findElement(By.xpath("//*[@id='email']")).clear();
		 driver.findElement(By.xpath("//*[@id='email']")).sendKeys("user@gmail.com");
		 
	}
	@Test(priority=4, dependsOnMethods="findLoginElement")
	public void passInPassword() throws Throwable {
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id='password']")).clear();
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("password"); 
	}
	
	@Test(priority=5, dependsOnMethods="findLoginElement")
	public void confirm() throws Throwable {
		Thread.sleep(500);
		WebElement submitBtn = driver.findElement(By.className("login"));
		Assert.assertEquals(true, submitBtn.isDisplayed()); 
		submitBtn.click();
	}
	
	@BeforeMethod
	public void beforeMethod() throws Throwable {

	}

	@AfterMethod
	public void afterMethod() throws Throwable {
		System.out.println("afterMethod");
	}

	@BeforeClass
	public void beforeClass() throws Throwable {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	  driver.get("http://friends-of-groot-society.s3-website-us-east-1.amazonaws.com/");
		driver.get("http://localhost:4200");	
		Thread.sleep(500);
		driver.findElement(By.id("login")).click();
		
	}

	@AfterClass
	public void afterClass() throws Throwable {
		Thread.sleep(1000);
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeSuite
	public void beforeSuite() {
		System.setProperty("webdriver.chrome.driver", "src/test/java/net/groot/resources/chromedriver.exe");
		driver = new ChromeDriver();

	}

	@AfterSuite
	public void afterSuite() throws InterruptedException {
//		driver.manage().deleteAllCookies(); 
		driver.quit();
	}

}