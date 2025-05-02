Feature: Login to saucedemo

Scenario: Login to sauce demo with invalid credentials
    Given i open the sauce demo login page
    When i login as "standard_user" with password "WrongPass"
    Then i should see an error message "Epic sadface: Username and password do not match any user in this service"

