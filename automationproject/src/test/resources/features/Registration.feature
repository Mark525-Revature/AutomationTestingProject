@Registration
Feature: Registration

	@SCRUM-TC-1 @JREQ-SCRUM-3
	Scenario Outline: Registration Positive Scenario
	given a valid username and password a user should be able to register with the planetarium
		Given I am on the landing page
		When I pick the option to register
		When I provide the username "<valid username>"
		When I provide the password "<valid password>"
		Then the user should be registered

	Examples: 
		| valid username                 | valid password                 |
		| Batman and Robin unite now!!!! | Riddler and Joker disagree!!!! |

	@SCRUM-TC-2 @JREQ-SCRUM-5
	Scenario Outline: Registration Username not unique
	given a non unique username and valid password a user should not be able to register with the planetarium
		Given I am on the landing page
		When I pick the option to register
		When I provide the username "<invalid username>"
		When I provide the password "<valid password>"
		Then the user should be informed registration failed

	Examples: 
		| invalid username | valid password                 |
		| Batman           | Riddler and Joker disagree!!!! |

	@SCRUM-TC-3 @JREQ-SCRUM-6
	Scenario Outline: Registration Username too long
	given a too long username and valid password a user should not be able to register with the planetarium
		Given I am on the landing page
		When I pick the option to register
		When I provide the username "<invalid username>"
		When I provide the password "<valid password>"
		Then the user should be informed registration failed

	Examples: 
		| invalid username                | valid password                 |
		| Gordon and Clark are friends!!! | Riddler and Joker disagree!!!! |

	@SCRUM-TC-4 @JREQ-SCRUM-7
	Scenario Outline: Registration Password too long
	given a valid username and a too long password a user should not be able to register with the planetarium
		Given I am on the landing page
		When I pick the option to register
		When I provide the username "<valid username>"
		When I provide the password "<invalid password>"
		Then the user should be informed registration failed

	Examples: 
		| valid username                 | invalid password                |
		| Batman and Robin unite now!!!! | Reverse Flash strikes again!!!! |

	@SCRUM-TC-5 @JREQ-SCRUM-8
	Scenario Outline: Registration User credentials too long
	given a too long username and a too long password a user should not be able to register with the planetarium
		Given I am on the landing page
		When I pick the option to register
		When I provide the username "<invalid username>"
		When I provide the password "<invalid password>"
		Then the user should be informed registration failed

	Examples: 
		| invalid username                | invalid password                |
		| Gordon and Clark are friends!!! | Reverse Flash strikes again!!!! |

	@SCRUM-TC-6 @JREQ-SCRUM-9
	Scenario Outline: Registration Username not unique and password too long
	given a non unique username and valid password a user should not be able to register with the planetarium
		Given I am on the landing page
		When I pick the option to register
		When I provide the username "<invalid username>"
		When I provide the password "<invalid password>"
		Then the user should be informed registration failed

	Examples: 
		| invalid username | invalid password                |
		| Batman           | Reverse Flash strikes again!!!! |

	@SCRUM-TC-33
	Scenario Outline: Registration Negative Scenario Null username 
	given no username and a valid password a user should not be able to register with the planetarium
		Given I am on the landing page
		When I pick the option to register
		When I provide no username
		When I provide the password "<valid password>"
		Then the user should be informed registration failed

	Examples: 
		| valid password                 |
		| Riddler and Joker disagree!!!! |

	@SCRUM-TC-34
	Scenario Outline: Registration Negative Scenario Null password
	given Valid username and no password a user should not be able to register with the planetarium
		Given I am on the landing page
		When I pick the option to register
		When I provide the username "<valid username>"
		When I provide no password
		Then the user should be informed registration failed

	Examples: 
		| valid username                 |
		| Batman and Robin unite now!!!! |

	@SCRUM-TC-35
	Scenario: Registration Negative Scenario Null username and password
	given no username and no password a user should not be able to register with the planetarium
		Given I am on the landing page
		When I pick the option to register
		When I provide no username
		When I provide no password
		Then the user should be informed registration failed