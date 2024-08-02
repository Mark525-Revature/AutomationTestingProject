# Feature: User Registration
  Users should be able to register with the Planetarium application.

  ## Background:
    Given no registered user with username "Batman and Robin unite now!!!!"
    And a registered user with username "Batman" and password "I am the night"

  ### Scenario: Positive Scenario
    Given I am on the landing page
    When I pick the option to register
    When I provide the username "Batman and Robin unite now!!!!"
    When I provide the password "Riddler and Joker disagree!!!!"
    Then the user should be registered with the planetarium

  ### Scenario: Negative Scenario Username not unique
    Given I am on the landing page
    When I pick the option to register
    When I provide the username "Batman"
    When I provide the password "Riddler and Joker disagree!!!!"
    Then the user should be informed registration failed

  ### Scenario: Negative Scenario Username too long
    Given I am on the landing page
    When I pick the option to register
    When I provide the username "Gordon and Clark are friends!!!"
    When I provide the password "Riddler and Joker disagree!!!!"
    Then the user should be informed registration failed

  ### Scenario: Negative Scenario Password too long
    Given I am on the landing page
    When I pick the option to register
    When I provide the username "Batman and Robin unite now!!!!"
    When I provide the password "Reverse Flash strikes again!!!!"
    Then the user should be informed registration failed

  ### Scenario: Negative Scenario credentials too long
    Given I am on the landing page
    When I pick the option to register
    When I provide the username "Gordon and Clark are friends!!!"
    When I provide the password "Reverse Flash strikes again!!!!"
    Then the user should be informed registration failed

  ### Scenario: Negative Scenario username taken and password too long
    Given I am on the landing page
    When I pick the option to register
    When I provide the username "Batman"
    When I provide the password "Reverse Flash strikes again!!!!"
    Then the user should be informed registration failed

  ### Scenario: Negative Scenario Null username
    Given I am on the landing page
    When I pick the option to register
    When I provide no username
    When I provide the password "Riddler and Joker disagree!!!!"
    Then the user should be informed registration failed

  ### Scenario: Negative Scenario Null password
    Given I am on the landing page
    When I pick the option to register
    When I provide the username "Batman and Robin unite now!!!!"
    When I provide no password
    Then the user should be informed registration failed
  
  ### Scenario: Negative Scenario Null username and password
    Given I am on the landing page
    When I pick the option to register
    When I provide the username "Batman and Robin unite now!!!!"
    When I provide the password "Riddler and Joker disagree!!!!"
    Then the user should be informed registration failed

---

# Feature: User Login
  Users should be able to securely access their account in the Planetarium application.

  ## Background:
    Given a registered user with username "Batman" and password "I am the night"
    And no registered user with username "Batman and Robin unite now!!!!" and password "Riddler and Joker disagree!!!!"

  ### Scenario: Positive Scenario
    Given I am on the landing page
    When I pick the option to login
    When I provide the username "Batman"
    When I provide the password "I am the night"
    Then the user should be logged in with the planetarium

  ### Scenario: Negative Scenario Registered Username and Invalid Password
    Given I am on the landing page
    When I pick the option to login
    When I provide the username "Batman"
    When I provide the password "Riddler and Joker disagree!!!!"
    Then the user should not be logged in with the planetarium

  ### Scenario: Negative Scenario Non-Registered Username
    Given I am on the landing page
    When I pick the option to login
    When I provide the username "Batman and Robin unite now!!!!"
    When I provide the password "I am the night"
    Then the user should not be logged in with the planetarium

  ### Scenario: Negative Scenario Non-Registered Username and invalid password
    Given I am on the landing page
    When I pick the option to login
    When I provide the username "Batman and Robin unite now!!!!"
    When I provide the password "Riddler and Joker disagree!!!!"
    Then the user should not be logged in with the planetarium

  ### Scenario: Negative Scenario Null username
    Given I am on the landing page
    When I pick the option to register
    When I provide no username
    When I provide the password “I am the night”
    Then the user should not be logged in with the planetarium

  ### Scenario: Negative Scenario Null password
    Given I am on the landing page
    When I pick the option to register
    When I provide the username "Batman"
    When I provide no password
    Then the user should not be logged in with the planetarium

  ### Scenario: Negative Scenario Null username and password
    Given I am on the landing page
    When I pick the option to register
    When I provide no username
    When I provide no password
    Then the user should not be logged in with the planetarium

---

# Feature: View Planets and Moons
  Users should be able to see planets and moons added to the Planetarium application.

  ## Background:
    Given a registered user with username "Batman" and password "I am the night"
    And existing Planets and Moons in the database

  ### Scenario: Positive Scenario
    Given I am on the landing page
    When I pick the option to login
    When I provide the username "Batman"
    When I provide the password "I am the night"
    Then I should be sent to the page with planets and moons

---

# Feature: Adding Planet
  Users should be able to add new Planets to the Planetarium application.

  ## Background:
    Given I am logged in to the Planetarium
    And there are registered planets with the names “Earth” and “Mars”

  ### Scenario: Positive Scenario Valid Planet name without picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Planet
    When I insert the planet name "Amphitrite Euphrosyne Virginia"
    When I click the Submit Planet button
    Then the planet should be added to the planetarium

  ### Scenario: Positive Scenario Valid Planet name with picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Planet
    When I insert the planet name "Amphitrite Euphrosyne Virginia"
    When I click choose File
    When I choose an image for the planet
    When I click the Submit Planet button
    Then the planet should be added to the planetarium

  ### Scenario: Negative Scenario planet name too long without picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Planet
    When I insert the planet name "Amphitrite Euphrosyne Virginia!"
    When I click the Submit Planet button
    Then the planet should not be added to the planetarium

  ### Scenario: Negative Scenario planet name too long with picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Planet
    When I insert the planet name "Amphitrite Euphrosyne Virginia!"
    When I click choose File
    When I choose an image for the planet
    When I click the Submit Planet button
    Then the planet should not be added to the planetarium

  ### Scenario: Negative Scenario non-unique planet name without picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Planet
    When I insert the planet name "Earth"
    When I click the Submit Planet button
    Then the planet should not be added to the planetarium

  ### Scenario: Negative Scenario non-unique planet name with picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Planet
    When I insert the planet name "Earth"
    When I click choose File
    When I choose an image for the planet
    When I click the Submit Planet button
    Then the planet should not be added to the planetarium

  ### Scenario: Negative Scenario Null planet name without picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Planet
    When I insert no planet name
    When I click the Submit Planet button
    Then the planet should not be added to the planetarium

  ### Scenario: Negative Scenario Null planet name with picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Planet
    When I insert no planet name
    When I click choose File
    When I choose an image for the planet
    When I click the Submit Planet button
    Then the planet should not be added to the planetarium

---

# Feature: Remove Planet
  Users should be able to remove Planets from the Planetarium application.

  ## Background:
    Given I am logged in to the Planetarium
    And there is a planet named “Amphitrite Euphrosyne Virginia”
    And there is no registered planet with the name “Vegeta”

  ### Scenario: Positive Scenario
    Given I am on the Planet and Moon viewing page
    When I change the selector to Planet
    When I insert the planet name "Amphitrite Euphrosyne Virginia"
    When I click the Delete button
    Then the planet should be removed from the planetarium

  ### Scenario: Negative Scenario Invalid planet name
    Given I am on the Planet and Moon viewing page
    When I change the selector to Planet
    When I insert the planet name "Vegeta"
    When I click the Delete button
    Then the planet should not be removed from the planetarium

  ### Scenario: Negative Scenario Null planet name
    Given I am on the Planet and Moon viewing page
    When I change the selector to Planet
    When I insert no planet name
    When I click the Delete button
    Then the planet should not be removed from the planetarium

---

# Feature: Adding Moon
  Users should be able to add Moons to the Planetarium application associated with a Planet.

  ## Background:
    Given I am logged in to the Planetarium
    And there are registered moons with the names “Luna” and “Titan”
    And there are registered planets with the names “Earth” and “Mars”

  ### Scenario: Positive Scenario Valid Moon name and planet ID without picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert the moon name "waxing crescent gibbous Moon!!"
    When I insert the planet ID "1"
    When I click the Submit Moon button
    Then the moon should be added to the planetarium

  ### Scenario: Positive Scenario Valid Moon name and planet ID with picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert the moon name "waxing crescent gibbous Moon!!"
    When I insert the planet ID "1"
    When I click choose File
    When I choose an image for the moon
    When I click the Submit Moon button
    Then the moon should be added to the planetarium

  ### Scenario: Negative Scenario Moon name too long with valid planet ID and a picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert the moon name "waxing crescent gibbous Moon!!!"
    When I insert the planet ID "1"
    When I click choose File
    When I choose an image for the moon
    When I click the submit moon button
    Then the moon should not be added to the planetarium

  ### Scenario: Negative Scenario Moon name too long with valid planet ID and no picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert the moon name "waxing crescent gibbous Moon!!!"
    When I insert the planet ID "1"
    When I click the submit moon button
    Then the moon should not be added to the planetarium

  ### Scenario: Negative Scenario Valid Moon name with invalid planet ID and a picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert the moon name "waxing crescent gibbous Moon!!"
    When I insert the planet ID "-1"
    When I click choose File
    When I choose an image for the moon
    When I click the submit moon button
    Then the moon should not be added to the planetarium

  ### Scenario: Negative Scenario Valid Moon name with invalid planet ID and no picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert the moon name "waxing crescent gibbous Moon!!"
    When I insert the planet ID "-1"
    When I click the submit moon button
    Then the moon should not be added to the planetarium

### Scenario: Negative Scenario Moon name too long with invalid planet ID and a picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert the moon name "waxing crescent gibbous Moon!!!"
    When I insert the planet ID "-1"
    When I click choose File
    When I choose an image for the moon
    When I click the submit moon button
    Then the moon should not be added to the planetarium

  ### Scenario: Negative Scenario Moon name too long with invalid planet ID and no picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert the moon name "waxing crescent gibbous Moon!!!"
    When I insert the planet ID "-1"
    When I click the submit moon button
    Then the moon should not be added to the planetarium

  ### Scenario: Negative Scenario Non-unqiue Moon name with valid planet ID and a picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert the moon name "Luna"
    When I insert the planet ID "1"
    When I click choose File
    When I choose an image for the moon
    When I click the submit moon button
    Then the moon should not be added to the planetarium

  ### Scenario: Negative Scenario Non-unqiue Moon name with valid planet ID and no picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert the moon name "Luna"
    When I insert the planet ID "1"
    When I click the submit moon button
    Then the moon should not be added to the planetarium

  ### Scenario: Negative Scenario Non-unqiue Moon name with invalid planet ID and a picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert the moon name "Luna"
    When I insert the planet ID "-1"
    When I click choose File
    When I choose an image for the moon
    When I click the submit moon button
    Then the moon should not be added to the planetarium

  ### Scenario: Negative Scenario Non-unqiue Moon name with invalid planet ID and no picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert the moon name "Luna"
    When I insert the planet ID "1"
    When I click the submit moon button
    Then the moon should not be added to the planetarium

  ### Scenario: Negative Scenario Null Moon name with valid planet ID and a picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert no moon name
    When I insert the planet ID "1"
    When I click choose File
    When I choose an image for the moon
    When I click the submit moon button
    Then the moon should not be added to the planetarium

  ### Scenario: Negative Scenario Null Moon name with valid planet ID and no picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert no moon name
    When I insert the planet ID "1"
    When I click the submit moon button
    Then the moon should not be added to the planetarium

  ### Scenario: Negative Scenario Valid Moon name with null planet ID and a picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert the Valid moon name
    When I insert no planet id
    When I click choose File
    When I choose an image for the moon
    When I click the submit moon button
    Then the moon should not be added to the planetarium

  ### Scenario: Negative Scenario Valid Moon name with null planet ID and no picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert the Valid moon name
    When I insert no planet id
    When I click the submit moon button
    Then the moon should not be added to the planetarium

  ### Scenario: Negative Scenario Null Moon name with Null planet ID and a picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert no moon name
    When I insert no planet id
    When I click choose File
    When I choose an image for the moon
    When I click the submit moon button
    Then the moon should not be added to the planetarium

  ### Scenario: Negative Scenario Null Moon name with Null planet ID and no picture
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert no moon name
    When I insert no planet id
    When I click the submit moon button
    Then the moon should not be added to the planetarium

---

# Feature: Remove Moon
  Users should be able to remove Moons from the Planetarium

  ## Background:
    Given I am logged in to the Planetarium
    And there is a registered moon with the name “Luna”
    And there is no registerd moon with the name "Vegeta"

  ### Scenario: Positive Scenario Valid Moon name
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert the moon name "Luna"
    When I click the delete button
    Then the moon should be removed from the planetarium

  ### Scenario: Negative Scenario Invalid Moon name
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert the moon name "Vegeta"
    When I click the delete button
    Then no moon should be removed from the planetarium

  ### Scenario: Negative Scenario Null Moon name
    Given I am on the Planet and Moon viewing page
    When I change the selector to Moon
    When I insert no moon name
    When I click the delete button
    Then no moon should be removed from the planetarium