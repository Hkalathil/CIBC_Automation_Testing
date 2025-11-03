package com.fdmgroup.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompareProducts {
	private WebDriver driver;
	private JavascriptExecutor js;

	public CompareProducts(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='pel-noimage']")
	private WebElement personalLoan;
	
	@FindBy(xpath = "//input[@id='pcl-noimage']")
	private WebElement personalCarLoan;
	
	@FindBy(xpath = "//input[@id='rrsp-noimage']")
	private WebElement rRSPLoan;
	
	@FindBy(xpath = "//input[@id='plc-noimage']")
	private WebElement personalLineOfCredit;
	
	@FindBy(xpath = "//input[@id='hploc-noimage']")
	private WebElement homePower;
	
	@FindBy(xpath = "//input[@id='elc-noimage']")
	private WebElement educationLineOfCredit;
	
	@FindBy(xpath = "//input[@id='pesp-noimage']")
	private WebElement studentProgram;
	
	@FindBy(xpath = "//*[@id='account0']//*[text()='Add another loan to compare']")
	private WebElement firstBoxEmpty;
	
	@FindBy(xpath = "//*[@id='account1']//*[text()='Add another loan to compare']")
	private WebElement secondBoxEmpty;
	
	@FindBy(xpath = "//*[@id='account2']//*[text()='Add another loan to compare']")
	private WebElement thirdBoxEmpty;
	
	@FindBy(xpath = "(//a[@class='button secondary outer-compare _self'])[1]")
	private WebElement compareProductsButton1;
	
	@FindBy(xpath = "(//a[@class='button secondary outer-compare _self'])[2]")
	private WebElement compareProductsButton2;
	
	@FindBy(xpath = "(//a[@class='button secondary outer-compare _self'])[3]")
	private WebElement compareProductsButton3;
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	//had to hardcode some strings as I could not access DataFile from this package (this is src/main, while it is in src/test)
	//maybe I made a mistake in setting up my directory
	public boolean chooseCorrectOptions(String string1, String string2, String string3) {
		if(string1.equals("CIBC Personal Loan") && string2.equals("CIBC Personal Car Loan") && string3.equals("CIBC RRSP Maximizer Loan™")) {
			personalLoan.click();
			personalCarLoan.click();
			js.executeScript("arguments[0].scrollIntoView(true);", rRSPLoan);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			rRSPLoan.click();
			if(personalLoan.isSelected() && personalCarLoan.isSelected() && rRSPLoan.isSelected()) {
				return true;
			}
			else {
				return false;
			}
		}
		else if(string1.equals("CIBC Personal Line of Credit") && string2.equals("CIBC Home Power® Plan Line of Credit") && string3.equals("(none)")) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(compareProductsButton1));
			js.executeScript("arguments[0].scrollIntoView(true);", compareProductsButton1);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			personalLineOfCredit.click();
			homePower.click();
			if(personalLineOfCredit.isSelected() && homePower.isSelected()) {
				return true;
			}
			else {
				return false;
			}
		}
		else if(string1.equals("CIBC Education Line of Credit") && string2.equals("CIBC Professional Edge® Student Line of Credit") && string3.equals("(none)")) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(educationLineOfCredit));
			js.executeScript("arguments[0].scrollIntoView(true);", educationLineOfCredit);
			//cannot seem to fix it with the wait for some reason
			//had to hardcode a thread sleep as I am running out of time
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    educationLineOfCredit.click();
		    studentProgram.click();
			if(educationLineOfCredit.isSelected() && studentProgram.isSelected()) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	public void clickCompareProductButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(compareProductsButton1));
		js.executeScript("arguments[0].scrollIntoView(true);", compareProductsButton1);
		js.executeScript("arguments[0].click();", compareProductsButton1);
	}
	
	public boolean noOptionsChosen() {
		boolean optionChosen = false;
		if(personalLoan.isSelected() || personalCarLoan.isSelected() || rRSPLoan.isSelected()) {
			optionChosen = true;
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(compareProductsButton2));
		js.executeScript("arguments[0].scrollIntoView(true);", compareProductsButton2);
		if(personalLineOfCredit.isSelected() || homePower.isSelected()) {
			optionChosen = true;
		}
		wait.until(ExpectedConditions.elementToBeClickable(compareProductsButton3));
		js.executeScript("arguments[0].scrollIntoView(true);", compareProductsButton3);
		if(educationLineOfCredit.isSelected() || studentProgram.isSelected()) {
			optionChosen = true;
		}
		return optionChosen;
	}
	
	public boolean selectDisabledButton() {
		return compareProductsButton1.getAttribute("disabled").equals("true");
	}
}
