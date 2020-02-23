package net.groot.data;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class InvocationCountTest {

	WebDriver driver;

	@Test
	@Parameters({ "env", "browser", "url" })
	public void webParameterTest(String env, String browser, String url) throws Throwable {

		if (browser.contentEquals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/test/java/net/groot/resources/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.chrome.driver", "src/test/java/net/groot/resources/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	  driver.get("http://friends-of-groot-society.s3-website-us-east-1.amazonaws.com/");
		driver.get("http://localhost:4200");
		Thread.sleep(500);
		driver.findElement(By.id("login")).click();
	}

	@Test(invocationCount = 10, dataProvider = "dProvider1")
	public void invokeProvider(Integer n, String s) {

	}

	@Test(invocationCount = 10, expectedExceptions = NumberFormatException.class)
	public void math() {
		int a = 10;
		int b = 20;
		int c = a + b;
		System.out.println("math is=====" + c);
	}

	@Test(invocationCount = 10, expectedExceptions = NumberFormatException.class)
	public void mathError() {
		String x = "100A";
		Integer.parseInt(x);
		System.out.println("mathException Catch=====" + x);
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@DataProvider
	public Object[][] dProvider1() {
		return new Object[][] { new Object[] { 1, "a" }, new Object[] { 2, "b" }, };
	}

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeSuite
	public void beforeSuite() {
	}

	@AfterSuite
	public void afterSuite() {
	}

}
