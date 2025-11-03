package com.fdmgroup.stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.fdmgroup.testData.DataFile;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class F2_MortgageAffordabilityCalculator_StepDef {
	@Given("User hovers over the Mortgages tab")
	public void user_hovers_over_the_mortgages_tab() {
		Hooks.homePage.hoverOverMortgageTab();
		assertTrue(Hooks.homePage.mortgageAffordabilityCalculatorTabIsDisplayed());
	}

	@Given("User clicks the Mortgage Affordability Calculator option")
	public void user_clicks_the_mortgage_affordability_calculator_option() {
		Hooks.homePage.selectmortgageAffordabilityCalculatorTab();
		assertEquals(DataFile.mortgageAffordabilityCalculatorPage, Hooks.mortgageAffordabilityCalculator.getPageTilte());
	}

	@When("User enters {int} into the total gross household income field and enters {int} into the Down payment field and enters {int} into the loans and other debts and enters {int} into the credit cards and lines of credit and enters {int} into the Monthly condo fees")
	public void user_enters_into_the_total_gross_household_income_field_and_enters_into_the_down_payment_field_and_enters_into_the_loans_and_other_debts_and_enters_into_the_credit_cards_and_lines_of_credit_and_enters_into_the_monthly_condo_fees(
			Integer int1, Integer int2, Integer int3, Integer int4, Integer int5) {
		assertTrue(Hooks.mortgageAffordabilityCalculator.enterGrossAnualIncome(int1));
		assertTrue(Hooks.mortgageAffordabilityCalculator.enterDownPayment(int2));
		assertTrue(Hooks.mortgageAffordabilityCalculator.enterLoans(int3));
		assertTrue(Hooks.mortgageAffordabilityCalculator.enterCredit(int4));
		assertTrue(Hooks.mortgageAffordabilityCalculator.enterCondoFees(int5));	
	}

	@When("User chooses {string} from the dropdown menu and ensure there are {int} options")
	public void user_chooses_from_the_dropdown_menu(String string, Integer int1) {
		assertTrue(Hooks.mortgageAffordabilityCalculator.selectProvince(string));
		assertTrue(Hooks.mortgageAffordabilityCalculator.numberOfOptions(int1));
	}

	@When("User selects the {string} button with valid values")
	public void user_selects_the_button_with_valid_values(String string) {
		Hooks.mortgageAffordabilityCalculator.clickCalculateButton();
		assertTrue(Hooks.mortgageAffordabilityCalculator.checkResultsVisibility());
	}

	@Then("User sees the maximum purchase price and the monthly mortgage payment")
	public void user_sees_they_maximum_purchase_price_and_the_monthly_mortgage_payment() {
		assertTrue(Hooks.mortgageAffordabilityCalculator.checkVisibilityOfMaxPurchaseAndMonthlyPayment());
	}

	@When("User does not choose an option from the dropdown menu")
	public void user_does_not_choose_an_option_from_the_dropdown_menu() {
		assertTrue(Hooks.mortgageAffordabilityCalculator.dontSelectProvince(DataFile.defaultProvinceOption));
	}
	
	@When("User selects the {string} button with invalid values")
	public void user_selects_the_button_with_invalid_values(String string) {
		Hooks.mortgageAffordabilityCalculator.clickCalculateButton();
		assertFalse(Hooks.mortgageAffordabilityCalculator.checkVisibilityOfMaxPurchaseAndMonthlyPayment());
	}

	@Then("User does not see the maximum purchase price and monthly mortgage payments and instead sees error messages")
	public void user_does_not_see_the_maximum_purchase_price_and_monthly_mortgage_payments_and_instead_sees_error_messages() {
		assertFalse(Hooks.mortgageAffordabilityCalculator.checkVisibilityOfMaxPurchaseAndMonthlyPayment());
		assertTrue(Hooks.mortgageAffordabilityCalculator.checkVisibilityOfErrors());
	}

}
