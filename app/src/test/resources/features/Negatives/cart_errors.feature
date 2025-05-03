Feature: Negative Cart Checkout Scenarios
    As a standard user
    i want to see clear error messages when I try to checkout under various invalid conditions
    So that I can correct issues before completing my purchase

Scenario: Empty cart
    Given I am logged in as "standard_user" with password "secret_sauce"
    And I have navigated to the cart page
    And my cart is empty
    When I attempt to checkout
    Then I should see an error message "Your cart is empty"

Scenario: Missing checkout information
    Given I am logged in as "standard_user" with password "secret_sauce"
    And I have added an item to my cart
    And I have navigated to the cart page
    When I navigate to the checkout information page
    And I leave all required fields blank
    Then I should see an error message "Error: First Name is required"

