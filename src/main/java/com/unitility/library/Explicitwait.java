package com.unitility.library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Explicitwait {
	
	
	public static  void webDeiverWait(WebDriver driver,WebElement element) {
		
		WebDriverWait wait =new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

}
