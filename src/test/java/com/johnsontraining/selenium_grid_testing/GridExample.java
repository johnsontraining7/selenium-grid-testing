package com.johnsontraining.selenium_grid_testing;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jprotractor.NgBy;
import com.jprotractor.NgWebDriver;

public class GridExample {
	
	private WebDriver driver;
	private NgWebDriver ngDriver;
	private String baseUrl = "http://www.way2automation.com/angularjs-protractor/banking/#/login";
	
	@BeforeClass
	@Parameters({"nodeUrl","browser"})
	public void setup(String nodeUrl, String browser) throws Exception {
		
		System.out.println("Using nodeurl : " + nodeUrl);
		System.out.println("Using browser : " + browser);
		DesiredCapabilities caps = null;
		if(browser.equalsIgnoreCase("chrome")) {
			caps = DesiredCapabilities.chrome();
		} else if(browser.equalsIgnoreCase("firefox")) {
			caps = DesiredCapabilities.firefox();
		} else {
			throw new Exception("Invalid browser name specified in testng.xml.");
		}
		caps.setPlatform(Platform.ANY);
		driver = new RemoteWebDriver(new URL(nodeUrl), caps);
		

		ngDriver = new NgWebDriver(driver);
		ngDriver.manage().window().maximize();
		ngDriver.get(baseUrl);
	}

	@Test
	public void loginAsCustomerTest() throws InterruptedException {
		
		ngDriver.findElement(NgBy.buttonText("Customer Login")).click();
		Thread.sleep(3000);
		
		Select select = new Select(driver.findElement(By.xpath("//select[@id='userSelect']")));
		select.selectByVisibleText("Albus Dumbledore");
		Thread.sleep(3000);
		
		ngDriver.findElement(NgBy.buttonText("Login")).click();
	}
}
