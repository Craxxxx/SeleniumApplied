Feature: Negative Login to saucedemo Scenarios

Scenario: Wrong password
    Given i open the sauce demo login page
    When i login as "standard_user" with password "WrongPass"
    Then i should see an error message "Epic sadface: Username and password do not match any user in this service"

Scenario: Empty password
    Given i open the sauce demo login page
    When i login as "standard_user" with password ""
    Then i should see an error message "Epic sadface: Password is required"

Scenario: Empty username
    Given i open the sauce demo login page
    When i login as "" with password "secret_sauce"
    Then i should see an error message "Epic sadface: Username is required"    

