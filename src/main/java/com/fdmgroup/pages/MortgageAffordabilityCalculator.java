package com.fdmgroup.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.Select;

public class MortgageAffordabilityCalculator {
	private WebDriver driver;
	private Actions actions;

	public MortgageAffordabilityCalculator(WebDriver driver) {
		this.driver = driver;
		this.actions = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	public String getPageTilte() {
		return driver.getTitle();
	}
	
	@FindBy(xpath = "//input[@id='MACHouseholdIncome']")
	private WebElement grossAnualIncome;
	
	@FindBy(xpath = "//input[@id='MACDownPayment']")
	private WebElement downPayment;
	
	@FindBy(xpath = "//select[@id='MACProvince']")
	private WebElement provinceOfResidence;
	
	@FindBy(xpath = "//input[@id='MACLoansOrDebts']")
	private WebElement loans;
	
	@FindBy(xpath = "//input[@id='MACCredit']")
	private WebElement credit;
	
	@FindBy(xpath = "//input[@id='MACCondoFees']")
	private WebElement condoFees;
	
	@FindBy(xpath = "//button[@id='MACCalculateBtn']")
	private WebElement calculateButton;
	
	@FindBy(xpath = "//div[@class='mac-results']")
	private WebElement resultsVisibility;
	
	@FindBy(xpath = "//span[@id='maxHomePrice']")
	private WebElement maxPurchasePriceVisibility;
	
	@FindBy(xpath = "//span[@id='monthlyPayment']")
	private WebElement monthlyPayment;
	
	@FindBy(xpath = "//span[@id='MACHouseholdIncomeErr']")
	private WebElement totalHouseholdIncomeError;
	
	@FindBy(xpath = "//span[@id='MACDownPaymentBCONErr']")
	private WebElement downPaymentError;
	
	@FindBy(xpath = "//span[@id='MACProvinceErr']")
	private WebElement provinceError;
	
	public boolean enterGrossAnualIncome(int int1) {
		String value = String.valueOf(int1);
		grossAnualIncome.sendKeys(value);
		String actualValue = grossAnualIncome.getAttribute("data-last");
		if(value.equals(actualValue)) {
			return true;
		}
		return false;
	}
	
	public boolean enterDownPayment(int int2) {
		String value = String.valueOf(int2);
		downPayment.sendKeys(value);
		String actualValue = downPayment.getAttribute("data-last");
		if(value.equals(actualValue)) {
			return true;
		}
		return false;
	}
	
	public boolean enterLoans(int int3) {
		String value = String.valueOf(int3);
		loans.clear();
		loans.sendKeys(value);
		String actualValue = loans.getAttribute("data-last");
		if(value.equals(actualValue)) {
			return true;
		}
		return false;
	}
	
	public boolean enterCredit(int int4) {
		String value = String.valueOf(int4);
		credit.clear();
		credit.sendKeys(value);
		String actualValue = credit.getAttribute("data-last");
		if(value.equals(actualValue)) {
			return true;
		}
		return false;
	}
	
	public boolean enterCondoFees(int int5) {
		String value = String.valueOf(int5);
		condoFees.clear();
		condoFees.sendKeys(value);
		String actualValue = condoFees.getAttribute("data-last");
		if(value.equals(actualValue)) {
			return true;
		}
		return false;
	}
	
	public boolean selectProvince(String string) {
		Select provinceOfResidenceSelect = new Select(provinceOfResidence);
		provinceOfResidenceSelect.selectByContainsVisibleText(string);
		WebElement selectedOption = provinceOfResidenceSelect.getFirstSelectedOption();
		if(selectedOption.getText().equals(string)) {
			return true;
		}
		return false;
	}
	
	public boolean numberOfOptions(int int1) {
		Select provinceOfResidenceSelect = new Select(provinceOfResidence);
		if(int1 == provinceOfResidenceSelect.getOptions().size()) {
			return true;
		}
		return false;
	}
	
	public boolean dontSelectProvince(String string) {
		Select provinceOfResidenceSelect = new Select(provinceOfResidence);
		WebElement selectedOption = provinceOfResidenceSelect.getFirstSelectedOption();
		if(selectedOption.getText().equals(string)) {
			return true;
		}
		return false;
	}
	
	public void clickCalculateButton() {
		calculateButton.click();
	}
	
	public boolean checkResultsVisibility() {
		if(resultsVisibility.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean checkVisibilityOfMaxPurchaseAndMonthlyPayment() {
		if(maxPurchasePriceVisibility.isDisplayed() && monthlyPayment.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean checkVisibilityOfErrors() {
		if(totalHouseholdIncomeError.isDisplayed() && downPaymentError.isDisplayed() && provinceError.isDisplayed()) {
			return true;
		}
		return false;
	}
}
