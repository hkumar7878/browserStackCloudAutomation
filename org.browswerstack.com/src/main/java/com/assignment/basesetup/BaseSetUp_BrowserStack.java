package com.assignment.basesetup;



import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;


public class BaseSetUp_BrowserStack {
	public static Properties prop;
	public static WebDriver driver;	
	public static String USERNAME ="hiteshghai1";
	public static String ACCESSKEY="prneyTWQ9cpL9NYqvCWe";
	public static String URL="https://"+USERNAME+":"+ACCESSKEY+"@hub.browserstack.com/wd/hub";
		
	
	
	//@Factory(dataProvider = "browserStackTestData")
	public void initializeTestBaseSetup(Platform platform,String browserName,String browserVersion) {
		try {

			DesiredCapabilities dc= new DesiredCapabilities();
			dc.setPlatform(platform);
			dc.setBrowserName(browserName);
			dc.setVersion(browserVersion);
			dc.setCapability("browserstack.debug", true);
			URL browserStackURL= new URL(URL);
			driver= new RemoteWebDriver(browserStackURL,dc);
			driver.get("https://www.thewarehouse.co.nz/");

		} catch (Exception e) {
			System.out.println("Error....." + e.getMessage());
		}

	}
	
	

}
