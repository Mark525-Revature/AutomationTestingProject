Feature: User Registration
  Users should be able to register with the Planetarium application.

  Background:
    Given no registered user with username "Batman and Robin unite now!!!!"
    And a registered user with username "Batman" and password "I am the night"

  Scenario: Positive Scenario
    Given I am on the landing page
    When I pick the option to register
    When I provide the username "Batman and Robin unite now!!!!"
    When I provide the password "Riddler and Joker disagree!!!!"
    Then the user should be registered with the planetarium

  Scenario: Negative Scenario Username not unique
    Given I am on the landing page
    When I pick the option to register
    When I provide the username "Batman"
    When I provide the password "Riddler and Joker disagree!!!!"
    Then the user should be informed registration failed

  Scenario: Negative Scenario Username too long
    Given I am on the landing page
    When I pick the option to register
    When I provide the username "Gordon and Clark are friends!!!"
    When I provide the password "Riddler and Joker disagree!!!!"
    Then the user should be informed registration failed

  Scenario: Negative Scenario Password too long
    Given I am on the landing page
    When I pick the option to register
    When I provide the username "Batman and Robin unite now!!!!"
    When I provide the password "Reverse Flash strikes again!!!!"
    Then the user should be informed registration failed

  Scenario: Negative Scenario credentials too long
    Given I am on the landing page
    When I pick the option to register
    When I provide the username "Gordon and Clark are friends!!!"
    When I provide the password "Reverse Flash strikes again!!!!"
    Then the user should be informed registration failed

  Scenario: Negative Scenario username taken and password too long
    Given I am on the landing page
    When I pick the option to register
    When I provide the username "Batman"
    When I provide the password "Reverse Flash strikes again!!!!"
    Then the user should be informed registration failed