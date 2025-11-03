package com.fdmgroup.stepDefinitions;

import org.openqa.selenium.WebDriver;

import com.fdmgroup.utilities.DriverUtilities;
import com.fdmgroup.pages.CompareProducts;
import com.fdmgroup.pages.CompareProductsResult;
import com.fdmgroup.pages.GICCalculator;
import com.fdmgroup.pages.HomePage;
import com.fdmgroup.pages.Investments;
import com.fdmgroup.pages.MortgageAffordabilityCalculator;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	static DriverUtilities driverUtilities;
	static WebDriver driver;
	static HomePage homePage;
	static Investments investments;
	static GICCalculator gICCalculator;
	static MortgageAffordabilityCalculator mortgageAffordabilityCalculator;
	static CompareProducts compareProducts;
	static CompareProductsResult compareProductsResult;

	@Before
	public static void init() {
		driverUtilities = DriverUtilities.getInstance();
		driver = driverUtilities.getDriver();
		driver.manage().window().maximize();
		homePage = new HomePage(driver);
		investments = new Investments(driver);
		gICCalculator = new GICCalculator(driver);
		mortgageAffordabilityCalculator = new MortgageAffordabilityCalculator(driver);
		compareProducts = new CompareProducts(driver);
		compareProductsResult = new CompareProductsResult(driver);
	}

	@After
	public static void tearDown() {
		DriverUtilities.resetDriver();
	}
}
