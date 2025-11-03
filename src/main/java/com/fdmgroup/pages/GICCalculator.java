package com.fdmgroup.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class GICCalculator {
	private WebDriver driver;
	private JavascriptExecutor js;

	public GICCalculator(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[text()='Reset calculator']")
	private WebElement resetCalculatorVisible;
	
	@FindBy(xpath = "//select[@id = 'Goal']")
	private WebElement yourGoalDropDown;
	
	@FindBy(xpath = "//input[@id='Box2'][@type='checkbox']")
	private WebElement variableInterestRateOptionDisabled;
	
	@FindBy(xpath = "//input[@id='Box1'][@type='checkbox']")
	private WebElement accessMoneyPriorToMaturity;
	
	@FindBy(xpath = "//select[@id='termLengthDropdown']")
	private WebElement verifyNumberOfElements;
	
	@FindBy(xpath = "//input[@id='invAmt']")
	private WebElement investmentAmountField;
	
	@FindBy(xpath = "//button[@id='showResult']")
	private WebElement calculateReturnsButton;
	
	@FindBy(xpath = "//p[@class='text1 body-copy-l-semibold maturity']")
	private WebElement estimatedValueAtMaturity;
	
	@FindBy(xpath = "//div[@id='invAmtError']")
	private WebElement errorMessage;
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void scrollAndClickYourGoalDropDown() {
		Select yourGoalDropDownSelect =  new Select(yourGoalDropDown);
		js.executeScript("arguments[0].scrollIntoView(true);", resetCalculatorVisible);
		js.executeScript("arguments[0].click();", yourGoalDropDownSelect);
	}
	
	public String getRRSPText(String string) {
		Select yourGoalDropDownSelect =  new Select(yourGoalDropDown);
		List<WebElement> allOptions = yourGoalDropDownSelect.getOptions();
		for(WebElement option: allOptions) {
			if(option.getText().equals(string)) {
				return option.getText();
			}
		}
		return null;
	}
	
	public void clickRRSP(String string) {
		Select yourGoalDropDownSelect =  new Select(yourGoalDropDown);
		yourGoalDropDownSelect.selectByContainsVisibleText(string);
	}
	
	public boolean rRSPOptionIsSelected(String string) {
		Select yourGoalDropDownSelect =  new Select(yourGoalDropDown);
		WebElement selectedOption = yourGoalDropDownSelect.getFirstSelectedOption();
		if(selectedOption.getText().equals(string)) {
			return true;
		}
		return false;
	}
	
	public boolean ensureVariableInterestRateOptionDisabled() {
		if(variableInterestRateOptionDisabled.isEnabled()) {
			return false;
		}
		return true;
	}
	
	public boolean selectAccessMoneyPriorToMaturityOption() {
		accessMoneyPriorToMaturity.click();
		return accessMoneyPriorToMaturity.isSelected();
	}
	
	public boolean verifyOnlyOneOptionInDropDown() {
		Select verifyNumberOfElementsSelect = new Select(verifyNumberOfElements);
		if(verifyNumberOfElementsSelect.getOptions().size() == 1) {
			return true;
		}
		return false;
	}
	
	public void enterInvestmentValues(int investment) {
		//put in the sleep to debug
//	    try {
	        String value = String.valueOf(investment);
	        //clicking normally didnt work, but I think it is because of the way CIBC set up that field
	        //js focus seems to work
	        js.executeScript("arguments[0].scrollIntoView(true);", investmentAmountField);
		    js.executeScript("arguments[0].focus();", investmentAmountField);
		    investmentAmountField.sendKeys(value);
//	        Thread.sleep(5000);
//	    } catch (InterruptedException e) {
//	        e.printStackTrace();
//	    }
	}
	
	public boolean expectedInvestmentAmount(int investment) {
		String value = String.valueOf(investment);
	    String actualValue = investmentAmountField.getAttribute("value");
		if(value.equals(actualValue)) {
			return true;
		}
		return false;
	}
	
	public void clickCalculatReturnsButton() {
		//had to debug this feature a lot that is why the sleep is here
//	    try {
			//simply scrolling into view did not work so added the extra behavior
	        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", calculateReturnsButton);
//	        Thread.sleep(2000);
	        js.executeScript("arguments[0].click();", calculateReturnsButton);
//	        Thread.sleep(5000);
//	    } catch (InterruptedException e) {
//	        e.printStackTrace();
//	    }
	}
	
	public boolean calculatedEstimatedReturnsValue() {
		if(estimatedValueAtMaturity.getText().equals("$--")) {
			return false;
		}
		return true;
	}
	
	public boolean verifyErrorMessageisVisible() {
		if(errorMessage.isDisplayed()) {
			return true;
		}
		return false;
	}
	
}
