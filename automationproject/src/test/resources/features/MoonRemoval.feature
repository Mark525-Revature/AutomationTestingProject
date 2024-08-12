@MoonRemoval
Feature: MoonRemoval

	@SCRUM-TC-31 @JREQ-SCRUM-50
	Scenario Outline: Moon Removal Valid Moon name
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert the moon name "<Valid Moon Name>"
		When I click the delete button
		Then the moon should be removed from the planetarium

		Examples:
			| Valid Moon Name               |
			| waxing crescent gibbous Moon!!|

	@SCRUM-TC-32 @JREQ-SCRUM-52
	Scenario Outline: Moon Removal Invalid Moon name
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert the moon name "<Invalid Moon Name>"
		When I click the delete button
		Then no moon should be removed from the planetarium

		Examples:
			| Invalid Moon Name |
			| Vegeta            |

	@SCRUM-TC-48 @JREQ-SCRUM-80
	Scenario: Moon Removal Negative Scenario Null Moon name
	if User provides no moon name then no moon should be removed from the planetarium
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert no moon name
		When I click the Delete button
		Then no moon should be removed from the planetarium