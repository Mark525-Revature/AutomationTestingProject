@MoonRegistration
Feature: MoonRegistration

	@SCRUM-TC-18 @JREQ-SCRUM-35
	Scenario Outline: Moon Registration Valid Moon name with valid Planet ID and without picture
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert the moon name to add a moon "<Valid Moon Name>"
		When I insert the planet ID "<Valid Planet ID Number>"
		When I click the submit moon button
		Then the moon should be added to the planetarium

	Examples: 
		| Valid Moon Name                | Valid Planet ID Number |
		| waxing crescent gibbous Moon!1 | 1                      |

	@SCRUM-TC-21 @JREQ-SCRUM-36
	Scenario Outline: Moon Registration Valid Moon name with valid Planet ID and picture
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert the moon name to add a moon "<Valid Moon Name>"
		When I insert the planet ID "<Valid Planet ID Number>"
		When I choose an image for the moon "<Picture for Moons>"
		When I click the submit moon button
		Then the moon should be added to the planetarium

	Examples: 
		| Valid Moon Name                | Valid Planet ID Number | Picture for Moons |
		| waxing crescent gibbous Moon1! | 1                      | moon-1.jpg        |

	@SCRUM-TC-19 @JREQ-SCRUM-38
	Scenario Outline: Moon Registration Invalid Moon name with valid Planet ID and picture
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert the moon name to add a moon "<Invalid Moon Name>"
		When I insert the planet ID "<Valid Planet ID Number>"
		When I choose an image for the moon "<Picture for Moons>"
		When I click the submit moon button
		Then the moon should not be added to the planetarium

	Examples: 
		| Invalid Moon Name               | Valid Planet ID Number | Picture for Moons |
		| waxing crescent gibbous Moon!!! | 1                      | moon-1.jpg        |

	@SCRUM-TC-22 @JREQ-SCRUM-39
	Scenario Outline: Moon Registration Invalid Moon name with valid Planet ID and without picture
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert the moon name to add a moon "<Invalid Moon Name>"
		When I insert the planet ID "<Valid Planet ID Number>"
		When I click the submit moon button
		Then the moon should not be added to the planetarium

	Examples: 
		| Invalid Moon Name               | Valid Planet ID Number |
		| waxing crescent gibbous Moon!!! | 1                      |

	@SCRUM-TC-23 @JREQ-SCRUM-40
	Scenario Outline: Moon Registration Valid Moon name with invalid Planet ID and picture
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert the moon name to add a moon "<Valid Moon Name>"
		When I insert the planet ID "<Invalid Planet ID Number>"
		When I choose an image for the moon "<Picture for Moons>"
		When I click the submit moon button
		Then the moon should not be added to the planetarium

	Examples: 
		| Valid Moon Name                | Invalid Planet ID Number | Picture for Moons |
		| waxing crescent gibbous Moon!! | 0                        | moon-1.jpg        |

	@SCRUM-TC-24 @JREQ-SCRUM-41
	Scenario Outline: Moon Registration Valid Moon name with invalid Planet ID and without picture
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert the moon name to add a moon "<Valid Moon Name>"
		When I insert the planet ID "<Invalid Planet ID Number>"
		When I click the submit moon button
		Then the moon should not be added to the planetarium

	Examples: 
		| Valid Moon Name                | Invalid Planet ID Number |
		| waxing crescent gibbous Moon!! | 0                        |

	@SCRUM-TC-25 @JREQ-SCRUM-42
	Scenario Outline: Moon Registration Invalid Moon name with invalid Planet ID and picture
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert the moon name to add a moon "<Invalid Moon Name>"
		When I insert the planet ID "<Invalid Planet ID Number>"
		When I choose an image for the moon "<Picture for Moons>"
		When I click the submit moon button
		Then the moon should not be added to the planetarium

	Examples: 
		| Invalid Moon Name               | Invalid Planet ID Number | Picture for Moons |
		| waxing crescent gibbous Moon!!! | 0                        | moon-1.jpg        |

	@SCRUM-TC-26 @JREQ-SCRUM-43
	Scenario Outline: Moon Registration Invalid Moon name with invalid Planet ID and without picture
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert the moon name to add a moon "<Invalid Moon Name>"
		When I insert the planet ID "<Invalid Planet ID Number>"
		When I click the submit moon button
		Then the moon should not be added to the planetarium

	Examples: 
		| Invalid Moon Name               | Invalid Planet ID Number |
		| waxing crescent gibbous Moon!!! | 0                        |

	@SCRUM-TC-27 @JREQ-SCRUM-45
	Scenario Outline: Moon Registration Non-Unique Moon name with valid Planet ID and without picture
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert the moon name to add a moon "<Non-Unique Moon Name>"
		When I insert the planet ID "<Valid Planet ID Number>"
		When I click the submit moon button
		Then the moon should not be added to the planetarium

	Examples: 
		| Non-Unique Moon Name | Valid Planet ID Number |
		| Luna                 | 1                      |

	@SCRUM-TC-42
	Scenario Outline: Moon Registration Negative Scenario Null Moon name with valid planet ID and a picture
	if User provides no moon name and a valid planet id with a picture no moon should be added to the planetarium
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert no moon name
		When I insert the planet ID "<Valid Planet ID Number>"
		When I choose an image for the moon "<Picture for Moons>"
		When I click the submit moon button
		Then the moon should not be added to the planetarium

	Examples: 
		| Picture for Moons | Valid Planet ID Number |
		| moon-1.jpg        | 1                      |

	@SCRUM-TC-43
	Scenario Outline: Moon Registration Negative Scenario Null Moon name with valid planet ID and no picture
	if User provides no moon name and a valid planet id with no picture no moon should be added to the planetarium
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert no moon name
		When I insert the planet ID "<Valid Planet ID Number>"
		When I click the submit moon button
		Then the moon should not be added to the planetarium

	Examples: 
		| Valid Planet ID Number |
		| 1                      |

	@SCRUM-TC-44
	Scenario Outline: Moon Registration Negative Scenario Valid Moon name with null planet ID and a picture
	if User provides a Valid moon name and no  planet id with a picture no moon should be added to the planetarium
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert the moon name to add a moon "<Valid Moon Name>"
		When I insert no planet id
		When I choose an image for the moon "<Picture for Moons>"
		When I click the submit moon button
		Then the moon should not be added to the planetarium

	Examples: 
		| Picture for Moons | Valid Moon Name                |
		| moon-1.jpg        | waxing crescent gibbous Moon!! |

	@SCRUM-TC-45
	Scenario Outline: Moon Registration Negative Scenario Valid Moon name with null planet ID and no picture
	if User provides a Valid moon name and no planet id with no picture no moon should be added to the planetarium
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert the moon name to add a moon "<Valid Moon Name>"
		When I insert no planet id
		When I click the submit moon button
		Then the moon should not be added to the planetarium

	Examples: 
		| Valid Moon Name                |
		| waxing crescent gibbous Moon!! |

	@SCRUM-TC-46
	Scenario Outline: Moon Registration Negative Scenario null Moon name with null planet ID and a picture
	if User provides no moon name and no planet id with a picture no moon should be added to the planetarium
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert no moon name
		When I insert no planet id
		#When I click choose File
		When I choose an image for the moon "<Picture for Moons>"
		When I click the submit moon button
		Then the moon should not be added to the planetarium

	Examples: 
		| Picture for Moons |
		| moon-1.jpg        |

	@SCRUM-TC-47
	Scenario: Moon Registration Negative Scenario null Moon name with null planet ID and no picture
	if User provides no moon name and no planet id with no picture no moon should be added to the planetarium
		Given I am on the Planet and Moon viewing page
		When I change the selector to Moon
		When I insert no moon name
		When I insert no planet id
		When I click the submit moon button
		Then the moon should not be added to the planetarium