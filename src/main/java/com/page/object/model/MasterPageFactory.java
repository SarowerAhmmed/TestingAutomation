package com.page.object.model;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MasterPageFactory {
	
	
	@FindBy(xpath ="(//*[@class='sc-htoDjs dpodOf'])[1]//a")
	private List<WebElement> allpage;

	@FindBy(xpath ="//*[contains(@class,'ticker-name-change')]")
	private List<WebElement> topSecurityNameAndValue;

	
	@FindBy(xpath ="//*[contains(@class,'logo')]")
	private List<WebElement> pagelogo;
	//*[@class='stock']
	@FindBy(xpath ="//*[@class='module-body wsod most-popular-stocks']//a")
	private List<WebElement> mostPopularStock;
	
	
	
	@FindBy(xpath ="//*[@class='module-body wsod key-stats' or @class='module-body wsod commodities']//*[@class='quote']")
	private List<WebElement> KeyStartsAndCommodities;
	
	public List<WebElement> getKeyStartsAndCommodities() {
		return KeyStartsAndCommodities;
	}

	@FindBy(xpath ="//*[name()='g']//*[@class='pct-change negative']")
	private List<WebElement> sectorformanceBarValues;
	
	@FindBy(xpath ="//*[name()='g']//*[name()='text'][2]")
	private List<WebElement> sectorformanceBarName;
	
	@FindBy(xpath ="//*[@class='module-body wsod losers']//li")
	private List<WebElement> losserSectionValues;
	
	
	public List<WebElement> getLosserSectionValues() {
		return losserSectionValues;
	}

	public List<WebElement> getSectorformanceBarName() {
		return sectorformanceBarName;
	}

	public List<WebElement> getSectorformanceBarValues() {
		return sectorformanceBarValues;
	}

	public List<WebElement> getMostPopularStock() {
		return mostPopularStock;
	}

	public List<WebElement> getPagelogo() {
		return pagelogo;
	}

	public List<WebElement> getAllpage() {
		return allpage;
	}

	public List<WebElement> getTopSecurityNameAndValue() {
		return topSecurityNameAndValue;
	}
	
	
	

}
