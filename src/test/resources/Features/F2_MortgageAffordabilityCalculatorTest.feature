# Author: Harrison Kalathil
# Date: Oct 24, 2025
@MACCalculator
Feature: As a future home owner I want to use the Mortgage Affordability Calculator to determine my maximum purchase price and monthly mortage payment

  Background:
    Given User launches the CIBC homepage and user accepts all cookies
    And User hovers over the Mortgages tab
    And User clicks the Mortgage Affordability Calculator option

  @ValidMortgageDetails
  Scenario Outline: User enters valid values into the appropriate fields and recieves a maximum purchase price and monthly mortgage payment
    When User enters <household> into the total gross household income field and enters <downpayment> into the Down payment field and enters <loans> into the loans and other debts and enters <credit> into the credit cards and lines of credit and enters <condo> into the Monthly condo fees
    And User chooses "Ontario" from the dropdown menu and ensure there are 14 options
    And User selects the "Calculate" button with valid values
    Then User sees the maximum purchase price and the monthly mortgage payment

    Examples:
      | household | downpayment | loans | credit | condo |
      | 123123    | 12312       | 1231  | 1231   | 123   |
      | 500000    | 50000       | 50000 | 2500   | 500   |

  @InvalidMortgageDetailsValidInvestmentAmount
  Scenario:User User cannot set an invalid investment amount and calculate their returns
    When User enters 0 into the total gross household income field and enters 0 into the Down payment field and enters 0 into the loans and other debts and enters 0 into the credit cards and lines of credit and enters 0 into the Monthly condo fees
    And User does not choose an option from the dropdown menu
    And User selects the "Calculate" button with invalid values
    Then User does not see the maximum purchase price and monthly mortgage payments and instead sees error messages