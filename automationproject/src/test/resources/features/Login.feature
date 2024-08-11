@Login
Feature: Login

	@SCRUM-TC-7 @JREQ-SCRUM-13
	Scenario Outline: Login Valid login credentials
		Given I am on the landing page
		When I pick the option to login
		When I provide the username "<invalid username>"
		When I provide the password "<invalid password>"
		Then the user should be logged in with the planetarium

	Examples: 
		| invalid username | invalid password |
		| Batman           | I am the night   |

	@SCRUM-TC-8 @JREQ-SCRUM-14
	Scenario Outline: Login Invalid login credentials with Registered Username
	Registered Username and Invalid Password
		Given I am on the landing page
		When I pick the option to login
		When I provide the username "<valid username>"
		When I provide the password "<invalid password>"
		Then the user should not be logged in with the planetarium

	Examples: 
		| valid username                 | invalid password                |
		| Batman and Robin unite now!!!! | Reverse Flash strikes again!!!! |

	@SCRUM-TC-9 @JREQ-SCRUM-15
	Scenario Outline: Login Invalid login credentials with Non-Registered Username
	N0n-registered Username and valid Password
		Given I am on the landing page
		When I pick the option to login
		When I provide the username "<invalid username>"
		When I provide the password "<valid password>"
		Then the user should not be logged in with the planetarium

	Examples: 
		| invalid username                | valid password                 |
		| Gordon and Clark are friends!!! | Riddler and Joker disagree!!!! |

	@SCRUM-TC-36
	Scenario Outline: Login Negative Scenario Null username 
	given no username and a valid password a user should not be able to log in to the planetarium
		Given I am on the landing page
		When I pick the option to login
		When I provide no username
		When I provide the password "<invalid password>"
		Then the user should not be logged in with the planetarium

	Examples: 
		| invalid password |
		| I am the night   |

	@SCRUM-TC-37
	Scenario Outline: Login Negative Scenario Null password
	given Valid username and no password a user should not be able to log in to the planetarium
		Given I am on the landing page
		When I pick the option to login
		When I provide the username "<invalid username>"
		When I provide no password
		Then the user should not be logged in with the planetarium

	Examples: 
		| invalid username |
		| Batman           |

	@SCRUM-TC-38
	Scenario: Login Negative Scenario Null username and password
	given no username and no password a user should not be able to log in to the planetarium
		Given I am on the landing page
		When I pick the option to login
		When I provide no username
		When I provide no password
		Then the user should not be logged in with the planetarium