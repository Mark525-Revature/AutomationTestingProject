@PlanetRemoval
Feature: PlanetRemoval

	@SCRUM-TC-16 @JREQ-SCRUM-30
	Scenario Outline: Planet Removal Valid Planet name
		Given I am on the Planet and Moon viewing pagefor deleting things
		When I change the selector to Planet
		When I insert the planet name "<Valid Planet name>" to delete
		When I click the Delete button
		Then the planet should be removed from the planetarium

	Examples: 
		| Valid Planet name              |
		| Amphitrite Euphrosyne Virginia |

	@SCRUM-TC-17 @JREQ-SCRUM-32
	Scenario Outline: Planet Removal Invalid Planet name
		Given I am on the Planet and Moon viewing page for deleting things
		When I change the selector to Planet
		When I insert the planet name "<Invalid Planet name>" to delete
		When I click the Delete button
		Then the planet should not be removed from the planetarium

	Examples: 
		| Invalid Planet name |
		| Vegeta              |

	@SCRUM-TC-41
	Scenario: Planet Removal Negative Scenario Null planet name
	If user enters no planet name then no planets are removed
		Given I am on the Planet and Moon viewing page for deleting things
		When I change the selector to Planet
		When I insert no planet name to delete
		When I click the Delete button
		Then the planet should not be removed from the planetarium