# Author: Harrison Kalathil
# Date: Oct 24, 2025
@CompareProducts
Feature: As a bank account holder I want to compare different loans and lines of credit to determine what option is most suitable for my needs

  Background:
    Given User launches the CIBC homepage and user accepts all cookies
    And User hovers over the Lending tab
    And User clicks the Compare Products option

  @ValidOptionCombination
  Scenario Outline: User selects valid combinations of loan and line of credit options to compare features
    When User selects the following options "<choice1>" and "<choice2>" and "<choice3>" and the corresponding checkboxes are ticked
    And User clicks the Compare Products button
    Then The user sees the comparison table showing the selected options

    Examples:
      | choice1                       | choice2                                        | choice3                   |
#     | CIBC Personal Loan            | CIBC Personal Car Loan                         | CIBC RRSP Maximizer Loan™ |
      | CIBC Personal Line of Credit  | CIBC Home Power® Plan Line of Credit           | (none)                    |
      | CIBC Education Line of Credit | CIBC Professional Edge® Student Line of Credit | (none)                    |

  @InsufficientChoicesMade
  Scenario: User cannot use the compare feature if no or only one option is chosen
    When User does not choose any options
    And User tries to click the Compare Products button
    Then The button should be disabled and the user will not be redirected to the comparing products page
