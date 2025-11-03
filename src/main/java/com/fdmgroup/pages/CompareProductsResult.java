package com.fdmgroup.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompareProductsResult {
	private WebDriver driver;
	private JavascriptExecutor js;

	public CompareProductsResult(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	@FindBy(xpath = "//*[@id='account0']//*[text()='CIBC Personal Loan']")
	private WebElement firstBoxPL;
	
	@FindBy(xpath = "//*[@id='account1']//*[text()='CIBC Personal Car Loan']")
	private WebElement secondBoxPCL;
	
	@FindBy(xpath = "//*[@id='account2']//*[text()='CIBC RRSP Maximizer Loan']")
	private WebElement thirdBoxRRSP;
	
	@FindBy(xpath = "//*[@id='account0']//*[text()='CIBC Personal Line of Credit']")
	private WebElement firstBoxLC;
	
	@FindBy(xpath = "//*[@id='account1']//*[text()='CIBC Home Power']")
	private WebElement secondBoxHP;
	
	@FindBy(xpath = "//*[@id='account0']//*[text()='CIBC Education Line of Credit']")
	private WebElement firstBoxELC;
	
	@FindBy(xpath = "//*[@id='account1']//*[text()='CIBC Professional Edge']")
	private WebElement secondBoxPE;
	
	@FindBy(xpath = "//*[@id='account0']//*[text()='Add another loan to compare']")
	private WebElement firstBoxEmpty;
	
	@FindBy(xpath = "//*[@id='account1']//*[text()='Add another loan to compare']")
	private WebElement secondBoxEmpty;
	
	@FindBy(xpath = "//*[@id='account2']//*[text()='Add another loan to compare']")
	private WebElement thirdBoxEmpty;
	
	//had to hardcode some strings as I could not access DataFile from this package (this is src/main, while it is in src/test)
	//maybe I made a mistake in setting up my directory
	public boolean correctResultsDisplayed() {
	    try {
	        if (isElementPresent(firstBoxPL) && isElementPresent(secondBoxPCL) && isElementPresent(thirdBoxRRSP)) {
	            return firstBoxPL.getText().contains("CIBC Personal Loan")
	                    && secondBoxPCL.getText().contains("CIBC Personal Car Loan")
	                    && thirdBoxRRSP.getText().contains("CIBC RRSP Maximizer Loan");
	        } else if (isElementPresent(firstBoxLC) && isElementPresent(secondBoxHP) && isElementPresent(thirdBoxEmpty)) {
	            return firstBoxLC.getText().contains("CIBC Personal Line of Credit")
	                    && secondBoxHP.getText().contains("CIBC Home Power")
	                    && thirdBoxEmpty.getText().contains("Add another loan to compare");
	        } else if (isElementPresent(firstBoxELC) && isElementPresent(secondBoxPE) && isElementPresent(thirdBoxEmpty)) {
	            return firstBoxELC.getText().contains("CIBC Education Line of Credit")
	                    && secondBoxPE.getText().contains("CIBC Professional Edge")
	                    && thirdBoxEmpty.getText().contains("Add another loan to compare");
	        }
	    } catch (Exception e) {
	        System.out.println("Error verifying comparison results: " + e.getMessage());
	        return false;
	    }
	    return false;
	}
	
	private boolean isElementPresent(WebElement element) {
	    try {
	        return element.isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}

}
