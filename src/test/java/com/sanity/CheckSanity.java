package com.sanity;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.generic.library.DriverManager;

public class CheckSanity {

	@Test
	public void sanity() {
		
		
		WebDriver driver = DriverManager.getDriverManager("https://www.cnn.com/business");
		System.out.println(driver.getTitle());
	}
	
	
}
