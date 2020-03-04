package net.groot.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
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

public class TestNgRegister {

	WebDriver driver; 
	
	@BeforeSuite
	public void beforeSuite() {
		System.setProperty("webdriver.chrome.driver", "src/test/java/net/groot/resources/chromedriver.exe");
		driver = new ChromeDriver();
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
		driver.findElement(By.id("register")).click();
	}
	
	@BeforeMethod
	public void beforeMethod() throws Throwable {

	}

	@BeforeTest
	public void beforeTest() {
	}
	@Test(priority = 1, groups="pageOpen")
	public void findRegisterPage() throws Throwable {
		boolean title = driver.findElement(By.className("page-title")).isDisplayed();
		Assert.assertEquals(true, title);
	}

	@Test(priority = 2, groups="pageOpen")
	public void findRegisterElement() throws Throwable {
		WebElement title = driver.findElement(By.className("page-title"));
		Assert.assertEquals("Register", title.getText());
	}

	@Test(priority = 3, groups="elementInput", dependsOnMethods = "findRegisterElement")
	public void passInFirstName() throws Throwable {

		driver.findElement(By.xpath("//*[@id='fName']")).clear();
		driver.findElement(By.xpath("//*[@id='fName']")).sendKeys("myFirstName");

	}

	@Test(priority = 4, groups="elementInput", dependsOnMethods = "findRegisterElement")
	public void passInLastName() throws Throwable {

		driver.findElement(By.xpath("//*[@id='lName']")).clear();
		driver.findElement(By.xpath("//*[@id='lName']")).sendKeys("myLastName");

	}

	@Test(priority = 5, groups="elementInput", dependsOnMethods = "findRegisterElement")
	public void passInEmail() throws Throwable {

		driver.findElement(By.xpath("//*[@id='email']")).clear();
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("user@gmail.com");

	}

	@Test(priority = 6, groups="elementInput", dependsOnMethods = "findRegisterElement")
	public void passInPassword() throws Throwable {
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id='password']")).clear();
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("password");
	}

	@Test(priority = 7, groups="submit", dependsOnMethods = "findRegisterElement")
	public void confirm() throws Throwable {
		Thread.sleep(500);
		WebElement submitBtn = driver.findElement(By.className("register"));
		Assert.assertEquals(true, submitBtn.isDisplayed());
		submitBtn.click();
	}
	@Test(priority = 8, groups="submit", enabled=false)
	public void confirmLeft() throws Throwable {
		Thread.sleep(500);
		WebElement submitBtn = driver.findElement(By.className("register"));
		Assert.assertNotEquals(false, submitBtn.isDisplayed());
		submitBtn.click();
	}

	@AfterTest
	public void afterTest() {
	}

	@AfterMethod
	public void afterMethod() throws Throwable {
		System.out.println("afterMethod");
	}

	@AfterClass
	public void afterClass() throws Throwable {
		Thread.sleep(1000);
	} 
	
	@AfterSuite
	public void afterSuite() throws InterruptedException {
//		driver.manage().deleteAllCookies(); 
		driver.quit();
	}

}