package net.groot.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Test;

//import net.groot.utility.DataRegisterUtil; 

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNgRegisterData {

	WebDriver driver;

//	@DataProvider
//	public Iterator<Object[]> getTheData() {
//		ArrayList<Object[]> testData = DataRegisterUtil.getDataFromExcel();
//		return testData.iterator();
//	}
	
	@DataProvider(name="getTheData")
	public static Object[][] register() {
		
		return new Object[][] {{"fName", "Firstname"}, {"lName", "Lastname"},{"email", "Ganon@gmail.com"},{"password", "password"}};
	}
	
	@BeforeSuite
	public void beforeSuite() {
		System.setProperty("webdriver.chrome.driver", "src/test/java/net/groot/resources/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@BeforeClass
	public void beforeClass() throws Throwable {

	}

	@BeforeMethod
	public void beforeMethod() throws Throwable {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	  driver.get("http://friends-of-groot-society.s3-website-us-east-1.amazonaws.com/");
		driver.get("http://localhost:4200/register");
	}

	@BeforeTest
	public void beforeTest() {
	}

	@Test(priority = 1, groups = "pageOpen")
	public void findRegisterPage() throws Throwable {
		boolean title = driver.findElement(By.className("page-title")).isDisplayed();
		Assert.assertEquals(true, title);
	}

	@Test(priority = 2, groups = "pageOpen")
	public void findRegisterElement() throws Throwable {
		WebElement title = driver.findElement(By.className("page-title"));
		Assert.assertEquals("Register", title.getText());
	}

	@Test(priority = 3, dataProvider="getTheData") 
	public void registerPage(String email, String password, String fName, String lName) throws Throwable {
		Thread.sleep(1000);
		System.out.println(email);
		driver.findElement(By.xpath("//*[@id='email']")).clear();
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(email);

		driver.findElement(By.xpath("//*[@id='password']")).clear();
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys(password);

		driver.findElement(By.xpath("//*[@id='fName']")).clear();
		driver.findElement(By.xpath("//*[@id='fName']")).sendKeys(fName);

		driver.findElement(By.xpath("//*[@id='lName']")).clear();
		driver.findElement(By.xpath("//*[@id='lName']")).sendKeys(lName);

	}

	@Test(priority = 4, groups = "submit", dependsOnMethods = "findRegisterElement")
	public void confirm() throws Throwable {
		Thread.sleep(500);
		WebElement submitBtn = driver.findElement(By.className("register"));
		Assert.assertEquals(true, submitBtn.isDisplayed());
		submitBtn.click();
	}

	@Test(priority = 5, groups = "submit", enabled = false)
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
