# Author: Harrison Kalathil
# Date: Oct 23, 2025
@GIC_Calculator
Feature: As a CIBC account holder I want to use the GIC Calculator to accurately estimate my returns on my RRSP investment

  Background:
    Given User launches the CIBC homepage and user accepts all cookies
    And User hovers over the Investments tab
    And User clicks the Investment Calculators option
    And User clicks the GIC calculator option
    When User selects "Save for retirement (RRSP)" from the dropdown menu
    Then The variable interest rate option should be disabled

  @OneYearTermLength
  Scenario: User can only choose a one year term length if they choose the option to access money prior to maturity
    When User selects the option to access money prior to maturity
    Then The only option for term length is 1 year

  @ValidInvestmentAmount
  Scenario Outline: User can set a valid investment amount and calculate their returns
    When User enters a valid investment amount <number>
    And User clicks the "Calculate my returns" button after entering valid amount
    Then User can see the estimated returns when the investment matures

    Examples:
      | number |
      | 1000   |
      | 500000 |

  @InvalidInvestmentAmount
  Scenario Outline: User should recieve an error message when entering an invalid invesment amount
    When User enters an invalid investment amount <number>
    And User clicks the "Calculate my returns" button after entering invalid amount
    Then An error message is displayed prompting the user to enter a valid amount

    Examples:
      | number |
      | 0      |
      | 250    |
