package com.fdmgroup.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Investments {
	private WebDriver driver;
	private JavascriptExecutor js;

	public Investments(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[text()='GIC calculator']")
	private WebElement GICTab;
	
	public void scrollAndClickGICTab() {
		js.executeScript("arguments[0].scrollIntoView(true);", GICTab);
		//wait didnt work
//	    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	    wait.until(ExpectedConditions.elementToBeClickable(GICTab));
//	    GICTab.click();
	    js.executeScript("arguments[0].click();", GICTab);
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
}
