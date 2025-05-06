Feature: accessing Inventory Page

Background:
    Given I log in as "standard_user" with password "secret_sauce"

Scenario Outline: View product details for multiple times
    When I click the "<itemName>" item link
    Then I should see the "<itemName>" in the product title detail page

Examples:
    | itemName                |
    | Sauce Labs Backpack     |
    | Sauce Labs Bike Light   |
    | Sauce Labs Bolt T-Shirt |
    | Sauce Labs Fleece Jacket|

Scenario: View all Products
    When I am on the inventory page
    Then I should see exactly 6 products displayed
    And each product should show:
      | Field       |
      | name        | 
      | price       |
      | image       |
