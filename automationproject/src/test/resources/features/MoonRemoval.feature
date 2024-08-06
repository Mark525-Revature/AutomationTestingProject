@MoonRemoval
Feature: MoonRemoval

	@SCRUM-TC-48
	Scenario: Moon Removal Negative Scenario Null Moon name
	if User provides no moon name then no moon should be removed from the planetarium
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert no moon name
		When I click the Delete button
		Then no moon should be removed from the planetarium