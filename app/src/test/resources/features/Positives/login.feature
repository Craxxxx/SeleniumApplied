Feature: Login to SauceDemo
#   As a standard user
#   I want to log in
#   So that I can see my inventory page

Scenario: Login to saucedemo with valid credenntials
#    Allows users to log in and out of the system securely.
    Given I open the SauceDemo login page
    When I login as "standard_user" with password "secret_sauce"
    Then i should see the inventory page
