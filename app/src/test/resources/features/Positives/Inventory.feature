Feature: accessing Inventory Page

Scenario Outline: View product details for multiple times
    Given I log in as "standard_user" with password "secret_sauce"
    When I click the "<itemName>" item link
    Then I should see the "<itemName>" in the product title detail page

Examples:
    | itemName                |
    | Sauce Labs Backpack     |
    | Sauce Labs Bike Light   |
    | Sauce Labs Bolt T-Shirt |
    | Sauce Labs Fleece Jacket|

Scenario: View all Products
    Given I log in as "standard_user" with password "secret_sauce"
    When 
    Then