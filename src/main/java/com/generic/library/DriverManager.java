package com.generic.library;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriverService;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DriverManager {

	public static WebDriver getDriverManager(String URL) {

		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		 System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		    Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
//		ChromeOptions options = new ChromeOptions();
////		options.addArguments("enable-automation");
////		//options.addArguments("--headless");
////		//options.addArguments("--window-size=1920,1080");

		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(URL);
		return driver;

	}

}
