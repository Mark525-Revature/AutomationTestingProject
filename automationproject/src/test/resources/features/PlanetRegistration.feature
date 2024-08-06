@PlanetRegistration
Feature: PlanetRegistration

	@SCRUM-TC-11 @JREQ-SCRUM-21
	Scenario Outline: Planet Registration Valid Planet name without picture
		Given I am on the Planet and Moon viewing page
		When I change the selector to Planet
		When I insert the planet name "<Valid Planet name>"
		When I click the Submit Planet button
		Then the planet should be added to the planetarium

	Examples: 
		| Valid Planet name              |
		| Amphitrite Euphrosyne Virginia |

	@SCRUM-TC-12 @JREQ-SCRUM-25
	Scenario Outline: Planet Registration Invalid Planet name with picture
		Given I am on the Planet and Moon viewing page
		When I change the selector to Planet
		When I insert the planet name "<Invalid Planet name>"
		When I click choose File
		When I choose an image for the planet "<Picture for Planets>"
		When I click the Submit Planet button
		Then the planet should not be added to the planetarium

	Examples: 
		| Invalid Planet name             | Picture for Planets |
		| Amphitrite Euphrosyne Virginia! | planet-1.jpg        |

	@SCRUM-TC-13 @JREQ-SCRUM-24
	Scenario Outline: Planet Registration Invalid Planet name without picture
		Given I am on the Planet and Moon viewing page
		When I change the selector to Planet
		When I insert the planet name "<Invalid Planet name>"
		When I click the Submit Planet button
		Then the planet should not be added to the planetarium

	Examples: 
		| Invalid Planet name             |
		| Amphitrite Euphrosyne Virginia! |

	@SCRUM-TC-14 @JREQ-SCRUM-27
	Scenario Outline: Planet Registration Non-unique Planet name with picture
		Given I am on the Planet and Moon viewing page
		When I change the selector to Planet
		When I insert the planet name "<Non-Unique Planet Names>"
		When I click choose File
		When I choose an image for the planet "<Picture for Planets>"
		When I click the Submit Planet button
		Then the planet should not be added to the planetarium

	Examples: 
		| Non-Unique Planet Names | Picture for Planets |
		| Earth                   | planet-1.jpg        |

	@SCRUM-TC-15 @JREQ-SCRUM-26
	Scenario Outline: Planet Registration Non-unique Planet name without picture
		Given I am on the Planet and Moon viewing page
		And I change the selector to Planet
		And I insert the planet name "<Non-Unique Planet Names>"
		And I click the Submit Planet button
		And the planet should not be added to the planetarium

	Examples: 
		| Non-Unique Planet Names |
		| Mars                    |

	@SCRUM-TC-20 @JREQ-SCRUM-22
	Scenario Outline: Planet Registration Valid Planet name with picture
		Given I am on the Planet and Moon viewing page
		When I change the selector to Planet
		When I insert the planet name "<Valid Planet name>"
		When I click choose File
		When I choose an image for the planet "<Picture for Planets>"
		When I click the Submit Planet button
		Then the planet should be added to the planetarium

	Examples: 
		| Valid Planet name              | Picture for Planets |
		| Amphitrite Euphrosyne Virginia | planet-1.jpg        |

	@SCRUM-TC-39
	Scenario: Planet Registration Negative Scenario Null planet name without picture
	if a user enters no planet name without a picture no planet is added to the planetarium
		Given I am on the Planet and Moon viewing page
		When I change the selector to Planet
		When I insert no planet name
		When I click the Submit Planet button
		Then the planet should not be added to the planetarium

	@SCRUM-TC-40
	Scenario Outline: Planet Registration Negative Scenario Null planet name with picture
	if a user enters no planet name with a picture no planet is added to the planetarium
		Given I am on the Planet and Moon viewing page
		When I change the selector to Planet
		When I insert no planet name
		When I click choose File
		When I choose an image for the planet <Picture for Planets>
		When I click the Submit Planet button
		Then the planet should not be added to the planetarium

	Examples: 
		| Picture for Planets |
		| planet-1.jpg        |