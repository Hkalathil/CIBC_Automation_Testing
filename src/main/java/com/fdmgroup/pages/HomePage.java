package com.fdmgroup.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	private WebDriver driver;
	private Actions actions;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.actions = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[text()='Investments ']")
	private WebElement investmentsTab;

	@FindBy(xpath = "//a[contains(text(), 'Investment Calculators')]")
	private WebElement investmentCalculatorTab;
	
	@FindBy(xpath = "//a[text()='Mortgages  ']")
	private WebElement mortgagesTab;
	
	@FindBy(xpath = "(//a[contains(text(), 'Mortgage Affordability Calculator')])[1]")
	private WebElement mortgageAffordabilityCalculatorTab;
	
	@FindBy(xpath = "//a[text()='Lending ']")
	private WebElement lendingTab;
	
	@FindBy(xpath = "//a[contains(text(), 'Compare Products')]")
	private WebElement compareProductsTab;
	
	@FindBy(id = "onetrust-accept-btn-handler")
	private WebElement acceptAllCookies;

	public void hoverOverInvestmentsTab() {
		actions.moveToElement(investmentsTab).perform();
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public boolean investmentCalculatorTabIsDisplayed() {
		return investmentCalculatorTab.isDisplayed();
	}

	public void selectInvestmentCalculatorTab() {
		// wait for the hover options to become visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(investmentCalculatorTab));
		// then click it
		investmentCalculatorTab.click();
	}
	
	public void hoverOverMortgageTab() {
		actions.moveToElement(mortgagesTab).perform();
	}
	
	public boolean mortgageAffordabilityCalculatorTabIsDisplayed() {
		return mortgageAffordabilityCalculatorTab.isDisplayed();
	}
	
	public void selectmortgageAffordabilityCalculatorTab() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(mortgageAffordabilityCalculatorTab));
		// then click it
		mortgageAffordabilityCalculatorTab.click();
	}
	
	public void hoverOverLendingTab() {
		actions.moveToElement(lendingTab).perform();
	}
	
	public boolean compareProductsTabIsDisplayed() {
		return compareProductsTab.isDisplayed();
	}
	
	public void selectCompareProductsTab() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(compareProductsTab));
		// then click it
		compareProductsTab.click();
	}
	
	public void acceptAllCookies() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(acceptAllCookies));
		acceptAllCookies.click();
	}
}
