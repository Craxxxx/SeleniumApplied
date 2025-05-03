Feature: Negative Cart Checkout Scenarios
    As a standard user
    i want to see a clear error if I try to checkout with an empty cart
    So that I don’t proceed to payment by mistake

Scenario: Empty cart
    Given I am logged in as "standard_user" with password "secret_sauce"
    And I have navigated to the cart page
    And my cart is empty
    When I attempt to checkout
    Then I should see an error message "Your cart is empty"

