package net.groot.data;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
 
public class InvocationNewsTest {

	WebDriver driver;

	@BeforeSuite 
	public void beforeSuite(  ) {
	
	}

	@BeforeClass
	public void beforeClass() throws Throwable { 
			
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@Test(priority=4)
	@Parameters({ "env", "browser", "local_url" })
	public void webParameterTest(String env, String browser, String local_url) throws Throwable {
		if (browser.contentEquals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/test/java/net/groot/resources/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.chrome.driver", "src/test/java/net/groot/resources/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.get(local_url);
		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(500);
		driver.findElement(By.id("news")).click();
	}

	@Test(priority=2, invocationCount = 3, dataProvider = "dProvider1")
	@Parameters("browser")
	public void invokeProvider(Integer n, String s) {

	}

	@Test(priority=1, invocationCount = 3 )
	@Parameters("browser")
	public void math() {
		int a = 10;
		int b = 20;
		int c = a + b;
		Assert.assertEquals(30, c);
		System.out.println("math is=====" + c);
	}

	@Test(priority=3, invocationCount = 10, expectedExceptions = NumberFormatException.class)
	@Parameters("browser")
	public void mathError() {
		String x = "100A";
		Integer.parseInt(x);
		System.out.println("mathException Catch=====" + x);
	}

	@Test
	@Parameters("browser")
	public void SearchBarMethod() {
		
	}
	
	@AfterMethod
	public void afterMethod() {
	}

	@DataProvider
	public Object[][] dProvider1() {
		return new Object[][] { new Object[] { 1, "a" }, new Object[] { 2, "b" }, };
	}

	@BeforeTest
	public void beforeTest() {
	}
	
	@AfterTest
	public void afterTest() {
	}

	@AfterClass
	public void afterClass() {
	}



	@AfterSuite
	public void afterSuite() throws InterruptedException {
//		driver.manage().deleteAllCookies(); 
		driver.quit();
	}

}
