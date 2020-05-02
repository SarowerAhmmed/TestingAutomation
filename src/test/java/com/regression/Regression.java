package com.regression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.config.ObjectMap;
import com.excelFactory.ExcelWriter;
import com.excelFactory.ReturnExcelNew;
import com.generic.library.DriverManager;
import com.google.common.collect.HashBiMap;
import com.page.object.model.MasterPageFactory;
import com.report.ExtentReport;
import com.unitility.library.Explicitwait;
import com.unitility.library.HighLight;
import com.unitility.library.TakeScreenShot;

public class Regression extends ExtentReport{
	WebDriver driver;
	MasterPageFactory pf;
	Logger log = Logger.getLogger("Regression");
	List<String> excelValue = new ArrayList<>();
	@Test
	public void regrassion_TC1() {

		driver = DriverManager.getDriverManager(ObjectMap.getconfig("URL"));
		PropertyConfigurator.configure("Log4j.properties");
		log.info("Home Page Title ::" + driver.getTitle());
		pf = PageFactory.initElements(driver, MasterPageFactory.class);
		TakeScreenShot.emptyScreenShotFolder();
		TakeScreenShot.captureScreenShot(driver, "Home page");
	}
	@Test
	public void regrassion_TC2() {
	
		new Explicitwait().webDeiverWait(driver, pf.getAllpage().get(0));
		log.info("Total pages to check titlte = "+pf.getAllpage().size());
		for(int i=0;i<pf.getAllpage().size();i++) {
			new Explicitwait().webDeiverWait(driver, pf.getAllpage().get(i));
			new HighLight().getColor(driver, pf.getAllpage().get(i),"red");
			pf.getAllpage().get(i).click();
			new Explicitwait().webDeiverWait(driver, pf.getPagelogo().get(0));
			log.info("Title of page " + i + " :: " + driver.getTitle());
			
			driver.get("https://www.cnn.com/business");	//driver.navigate().back();
			
		}

	}
	@Test
	public void regrassion_TC3_TC4() throws Throwable {
		new Explicitwait().webDeiverWait(driver, pf.getAllpage().get(0));
		pf.getAllpage().get(0).click();
		Map<String, String> topAppValue = new LinkedHashMap<String, String>();
		new Explicitwait().webDeiverWait(driver, pf.getTopSecurityNameAndValue().get(0));

		for (int i = 0; i < pf.getTopSecurityNameAndValue().size(); i++) {
			new HighLight().getColor(driver, pf.getTopSecurityNameAndValue().get(i), "red");
			topAppValue.put(pf.getTopSecurityNameAndValue().get(i).getAttribute("data-ticker-name"),
					pf.getTopSecurityNameAndValue().get(i).getText());
		}
		log.info("Top security value from Application ::"+topAppValue.values());
		TakeScreenShot.captureScreenShot(driver, "Top Security Value");
		List<String> appValue = new ArrayList<>(topAppValue.values());
		List<String> testDataValue = ReturnExcelNew.returnExcel("./TestData/Test Data Financial.xlsx", "Market");
		log.info("Top security value from Test Data Excel ::"+testDataValue);
		if (appValue.equals(testDataValue.get(1))) {
			log.info(appValue + " == " + testDataValue.get(1));
			//Assert.assertEquals(appValue, testDataValue.get(1), "Market Value Matched as Expected");

		} else {

			 //Assert.assertEquals(appValue, testDataValue.get(1), "Market Value Not Matchedas Expected");

		}
	}

	@Test
	public void regrassion_TC5_TC6() throws Throwable {

		new Explicitwait().webDeiverWait(driver, pf.getMostPopularStock().get(0));
		List<String> appValue = new ArrayList<>();
		for (int i = 0; i < pf.getMostPopularStock().size(); i++) {

			new HighLight().getColor(driver, pf.getMostPopularStock().get(i), "red");

			appValue.add(pf.getMostPopularStock().get(i).getText().replaceAll("\n", ","));
			excelValue.add(pf.getMostPopularStock().get(i).getText().replaceAll("\n", "_"));
		}
		TakeScreenShot.captureScreenShot(driver, "Most Polpular Stock");
		log.info("Most Popular Stock from Application::"+appValue);

		List<String> testDataValue = ReturnExcelNew.returnExcel("./TestData/Test Data Financial.xlsx", "Most Popular Stocks");
		log.info("Most Popular Stock from Test data Excel ::"+testDataValue);
		if (appValue.equals(testDataValue)) {
			log.info(appValue + " == " + testDataValue.get(1));
			//Assert.assertEquals(appValue, testDataValue, "Market Value Matched as Expected");

		} else {

			//Assert.assertEquals(appValue, testDataValue, "Market Value Not Matched as Expected");

		}
	}
	@Test
	public void regrassion_TC7_TC8() throws Throwable {
		
		new Explicitwait().webDeiverWait(driver, pf.getMostPopularStock().get(0));

		new Actions(driver).moveToElement(pf.getSectorformanceBarValues().get(11)).perform();
		Map<String, Double> sctPerfAppValue = new LinkedHashMap<>();
		for (int i = 0; i < pf.getSectorformanceBarName().size(); i++) {
			new HighLight().getColor(driver, pf.getSectorformanceBarName().get(i), "red");
			new HighLight().getColor(driver, pf.getSectorformanceBarValues().get(i), "green");
			sctPerfAppValue.put(pf.getSectorformanceBarName().get(i).getText(),
					Double.parseDouble(pf.getSectorformanceBarValues().get(i)
					.getText().replace("%", "")
					.replace("-", "")));
		}
		TakeScreenShot.captureScreenShot(driver, "Sector performance Bar");
		log.info("Sector performance Bar values ::"+sctPerfAppValue.entrySet());
		String minKey = HashBiMap.create(sctPerfAppValue).inverse().get(Collections.min(sctPerfAppValue.values()));
		log.info("Small bar found with value :: "+minKey+"="+sctPerfAppValue.get(minKey));
		String maxKey = HashBiMap.create(sctPerfAppValue).inverse().get(Collections.max(sctPerfAppValue.values()));
		log.info("Large Bar found with Value::"+maxKey+"="+sctPerfAppValue.get(maxKey));


}
	@Test
	public void regrassion_TC9() throws Throwable {
		new Explicitwait().webDeiverWait(driver, pf.getLosserSectionValues().get(0));
		new Actions(driver).moveToElement(pf.getLosserSectionValues().get(0)).perform();
		
		List<String> sctPerfAppValue = new ArrayList<>();
		for (int i = 0; i < pf.getLosserSectionValues().size(); i++) {
			new Actions(driver).moveToElement(pf.getSectorformanceBarValues().get(i)).perform();
			new HighLight().getColor(driver, pf.getLosserSectionValues().get(i), "green");
			sctPerfAppValue.add(pf.getLosserSectionValues().get(i).getText().replace("\n", ""));
		}

		log.info("Losse section Values from Application::"+sctPerfAppValue);
		TakeScreenShot.captureScreenShot(driver, "Losses Sector Value");
	}
	
	@Test
	public void test_regrassion_TC10() throws Throwable {

		
		new Explicitwait().webDeiverWait(driver, pf.getKeyStartsAndCommodities().get(0));
		new Actions(driver).moveToElement(pf.getKeyStartsAndCommodities().get(0)).perform();
		
		for (int i = 0; i < pf.getKeyStartsAndCommodities().size(); i++) {
			new Actions(driver).moveToElement(pf.getKeyStartsAndCommodities().get(i)).perform();
			new HighLight().getColor(driver, pf.getKeyStartsAndCommodities().get(i), "green");
			excelValue.add(pf.getKeyStartsAndCommodities().get(i).getText().replace("\n", "_"));
		}
		TakeScreenShot.captureScreenShot(driver, "Data for exlcel");
		log.info("Data found to write the Excel :: "+excelValue);
		ExcelWriter.wtireExcelListCNN("./TestData/CNNtestData22.xlsx", excelValue);
	}
	
	@AfterTest(alwaysRun = true)
	public void teardown() {
		
		driver.quit();
	}
	
}