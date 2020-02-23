package net.groot.data;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertPopUpHandle {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "src/test/java/net/groot/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("");
		driver.findElement(By.className("proceed")).click();
		Thread.sleep(4000);

		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		
		String text = alert.getText();
		
		if (text.equals("Please enter a valid user name")) {
			System.out.println("correct alert message");
		} else {
			System.out.println("in-correct alert message");
		}
		alert.accept();
//		alert.dismiss();

	}
}
