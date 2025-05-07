Feature: Complete Checkout
    As a standard user
    I want to complete the checkout process successfully
    So that I can purchase items in my cart

  Background:
    Given I logging in as "standard_user" with a password "secret_sauce"
    And I have added items to my cart

  Scenario: Successful Checkout
    When I proceed to checkout with valid information
    Then I should see the order summary page
    And I should see a confirmation message "Thank you for your order!"
