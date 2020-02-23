package net.groot.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;


public class ReadPropertyFiles {

	static WebDriver driver;
	
	public static void main(String[] args) throws IOException {
		
		Properties prop = new Properties();
		
		FileInputStream ip = new FileInputStream(
				"/groot-data/src/test/resources/config.properties");
		prop.load(ip);  
		
		System.out.println(prop.getProperty("name"));
		String url = prop.getProperty("url");
		String local_url = prop.getProperty("local_url");
		String aws_url_login = prop.getProperty("aws_url_login");
		
		System.out.println(url);
		String fName = prop.getProperty("fName");
		String lName = prop.getProperty("lName");
		String email = prop.getProperty("email");
		String password = prop.getProperty("password");
		

		String fName_xpath = prop.getProperty("fName_xpath");
		String lName_xpath = prop.getProperty("lName_xpath");
		String city_xpath = prop.getProperty("city_xpath");
	
				
	}
}
