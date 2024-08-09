@View
Feature: View

	@SCRUM-TC-10 @JREQ-SCRUM-18
	Scenario Outline: Planets and Moons View Valid login credentials
	Viewing the Planets and Moons
		Given I am on the landing page.
		When I provide the username "<valid username>"
		When I provide the password "<valid password>"
		Then I should be sent to the page with planets and moons

	Examples: 
		| valid username                 | valid password                 |
		| Batman and Robin unite now!!!! | Riddler and Joker disagree!!!! |