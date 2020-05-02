package com.smoke;

import org.testng.annotations.Test;

import com.generic.library.DriverManager;

public class CheckSmoke {

	
	@Test
	public void smoke() {
		
	
		DriverManager.getDriverManager("https://www.cnn.com/business");
	}
	
}
