package com.fdmgroup.stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.fdmgroup.testData.DataFile;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class F3_CompareLoansAndCredits_StepDef {
	@Given("User hovers over the Lending tab")
	public void user_hovers_over_the_lending_tab() {
		Hooks.homePage.hoverOverLendingTab();
		assertTrue(Hooks.homePage.compareProductsTabIsDisplayed());
	}

	@Given("User clicks the Compare Products option")
	public void user_clicks_the_compare_products_option() {
		Hooks.homePage.selectCompareProductsTab();
		assertEquals(DataFile.compareProductsPage, Hooks.compareProducts.getPageTitle());
	}

	@When("User selects the following options {string} and {string} and {string} and the corresponding checkboxes are ticked")
	public void user_selects_the_following_options_and_and(String string, String string2, String string3) {
	    assertTrue(Hooks.compareProducts.chooseCorrectOptions(string, string2, string3));
	}

	@When("User clicks the Compare Products button")
	public void user_clicks_the_button() {
	    Hooks.compareProducts.clickCompareProductButton();
	    assertEquals(DataFile.compareProductsPageResult, Hooks.compareProductsResult.getPageTitle());
	}

	@Then("The user sees the comparison table showing the selected options")
	public void the_user_sees_the_comparison_table_showing_the_selected_options() {
	    assertTrue(Hooks.compareProductsResult.correctResultsDisplayed());
	}

	@When("User does not choose any options")
	public void user_does_not_choose_any_options() {
	    assertFalse(Hooks.compareProducts.noOptionsChosen());
	}

	@When("User tries to click the Compare Products button")
	public void user_tries_to_click_the_button() {
	    assertTrue(Hooks.compareProducts.selectDisabledButton());
	}

	@Then("The button should be disabled and the user will not be redirected to the comparing products page")
	public void the_button_should_be_disabled_and_the_user_will_not_be_redirected_to_the_comparing_products_page() {
	    assertEquals(DataFile.compareProductsPage, Hooks.compareProducts.getPageTitle());
	}
}
