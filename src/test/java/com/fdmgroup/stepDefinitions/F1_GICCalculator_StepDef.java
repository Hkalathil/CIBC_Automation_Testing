package com.fdmgroup.stepDefinitions;

import com.fdmgroup.testData.DataFile;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class F1_GICCalculator_StepDef {
	@Given("User launches the CIBC homepage and user accepts all cookies")
	public void user_launches_the_cibc_homepage() {
		Hooks.driver.navigate().to(DataFile.homePage);
		Hooks.homePage.acceptAllCookies();
		assertEquals(DataFile.homePageTitle, Hooks.homePage.getPageTitle());
	}

	@Given("User hovers over the Investments tab")
	public void user_hovers_over_the_tab() {
		Hooks.homePage.hoverOverInvestmentsTab();
		assertTrue(Hooks.homePage.investmentCalculatorTabIsDisplayed());
	}

	@Given("User clicks the Investment Calculators option")
	public void user_clicks_the_option() {
		Hooks.homePage.selectInvestmentCalculatorTab();
		assertEquals(DataFile.investmentsPageTitle, Hooks.investments.getPageTitle());
	}

	@Given("User clicks the GIC calculator option")
	public void user_clicks_the_gic_calculator_option() {
		Hooks.investments.scrollAndClickGICTab();
		assertEquals(DataFile.gICPageTitle, Hooks.gICCalculator.getPageTitle());
	}

	@When("User selects {string} from the dropdown menu")
	public void user_selects_from_the_dropdown_menu(String string) {
	    assertEquals(string, Hooks.gICCalculator.getRRSPText(string));
	    Hooks.gICCalculator.clickRRSP(string);
	    assertTrue(Hooks.gICCalculator.rRSPOptionIsSelected(string));
	}

	@Then("The variable interest rate option should be disabled")
	public void the_variable_interest_rate_option_should_be_disabled() {
	    assertTrue(Hooks.gICCalculator.ensureVariableInterestRateOptionDisabled());
	}

	@When("User selects the option to access money prior to maturity")
	public void user_selects_the_option_to_access_money_prior_to_maturity() {
	    assertTrue(Hooks.gICCalculator.selectAccessMoneyPriorToMaturityOption());
	}

	@Then("The only option for term length is {int} year")
	public void the_only_option_for_term_length_is_year(Integer int1) {
	    assertTrue(Hooks.gICCalculator.verifyOnlyOneOptionInDropDown());
	}

	@When("User enters a valid investment amount {int}")
	public void user_enters_a_valid_investment_amount(Integer int1) {
	    Hooks.gICCalculator.enterInvestmentValues(int1);
	    assertTrue(Hooks.gICCalculator.expectedInvestmentAmount(int1));
	}

	@When("User clicks the {string} button after entering valid amount")
	public void user_clicks_the_button_after_entering_valid_amount(String string) {
	    Hooks.gICCalculator.clickCalculatReturnsButton();
	    assertTrue(Hooks.gICCalculator.calculatedEstimatedReturnsValue());
	}
	
	@When("User clicks the {string} button after entering invalid amount")
	public void user_clicks_the_button_after_entering_invalid_amount(String string) {
	    Hooks.gICCalculator.clickCalculatReturnsButton();
	    assertTrue(Hooks.gICCalculator.verifyErrorMessageisVisible());
	}

	@Then("User can see the estimated returns when the investment matures")
	public void user_can_see_the_estimated_returns_when_the_investment_matures() {
	    assertTrue(Hooks.gICCalculator.calculatedEstimatedReturnsValue());
	}

	@When("User enters an invalid investment amount {int}")
	public void user_enters_an_invalid_investment_amount(Integer int1) {
	    Hooks.gICCalculator.enterInvestmentValues(int1);
	    assertTrue(Hooks.gICCalculator.expectedInvestmentAmount(int1));
	}

	@Then("An error message is displayed prompting the user to enter a valid amount")
	public void an_error_message_is_displayed_prompting_the_user_to_enter_a_valid_amount() {
	    assertTrue(Hooks.gICCalculator.verifyErrorMessageisVisible());
	}
}
