Feature: Shopping Cart

Background:
    Given I log in as "standard_user" with a password "secret_sauce"

Scenario: Add Single Item
    When I add "Sauce Labs Backpack" to the cart
    Then the cart badge shows "1" item
    And the cart page lists "Sauce Labs Backpack"

Scenario: Add Multiple Items
    When I add "Sauce Labs Backpack", "Sauce Labs Bike Light", and "Sauce Labs Bolt T-Shirt" to the cart
    Then the cart badge shows "3" items
    And the cart page lists all three items

Scenario: Remove Item
    Given I have added "Sauce Labs Backpack", "Sauce Labs Bike Light" to the cart
    When I remove "Sauce Labs Backpack" from the cart
    Then the cart badge shows "1"
    And the cart page lists only the "Sauce Labs Bike Light"

