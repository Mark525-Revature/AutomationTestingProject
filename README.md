We will have Use cases and test cases in here
## Use Cases List  

Minimum test cases/decision table records = 30+ 

- 1. Users should be able to open a new User account with the Planetarium 

- 2. Users should be able to securely access their account 

- 3. Users should be able to see planets and moons added to the Planetarium 

- 4. Users should be able to add new Planets to the Planetarium 

- 5. Users should be able to remove Planets from the Planetarium 

- 6. Users should be able to add Moons to the Planetarium associated with a Planet 

- 7. Users should be able to remove Moons from the Planetarium 

# Use Case 1 
- Id: 1 
- Name: user registration 
- Description: Users Should be able to register with the Planetarium 
- System: Planetarium application 
- Preconditions: 

    - No registered user with username " Batman and Robin unite now!!!!" 
    - Registered user with username "Batman" and password “I am the night” 

- Actors: 
    - User (creating a new account) 
    - Planetarium application (handles the creation of the new account) 

# Test Data 
Positive: 

    - valid username = "Batman and Robin unite now!!!!" 
    - valid password = "Riddler and Joker disagree!!!!" 

Negative:

    - non-unique username = "Batman" 
    - too long username = "Gordon and Clark are friends!!!" 
    - too long password = "Reverse Flash strikes again!!!!" 

# Scenarios 
**Positive Scenario**: 
1. get to landing page 
2. pick option to register 
3. provide valid username 
4. provide valid password 
5. user should be registered with the planetarium 

**Negative Scenario Username not unique**: 
1. get to landing page 
2. pick option to register 
3. provide non-unique username 
4. provide valid password 
5. user should be informed registration failed 

**Negative Scenario Username too long**: 
1. get to landing page 
2. pick option to register 
3. provide too long username 
4. provide valid password 
5. user should be informed registration failed 

**Negative Scenario Password too long**: 
1. get to landing page 
2. pick option to register 
3. provide valid username 
4. provide too long password 
5. user should be informed registration failed 

**Negative Scenario credentials too long**: 
1. get to landing page 
2. pick option to register 
3. provide too long username 
4. provide too long password 
5. user should be informed registration failed 

**Negative Scenario username taken and password too long**: 
1. get to landing page 
2. pick option to register 
3. provide non-unique username 
4. provide too long password 
5. user should be informed registration failed 

# Decision Table
|Test Case ID|Scenario|Username|Password|Result| 
|------------|--------|--------|--------|------|
|1|Positive Scenario|Valid username|Valid password|User registered|
|2|Negative Scenario|Non-unique username|Valid password|User not registered|
|3|Negative Scenario|Too long username|Valid password|User not registered|
|4|Negative Scenario|Valid username|Too long password|User not registered|
|5|Negative Scenario|Too long username|Too long password|User not registered| 
|6|Negative Scenario|Non-unique username|Too long password|User not registered| 

# Use Case 2 
- Id: 2 
- Name: user login 
- Description: Users should be able to securely access their account 
- System: Planetarium application 
- Preconditions: 

    - Registered user with username "Batman" and password “I am the night” 
    - No registered user with username " Batman and Robin unite now!!!!" And password "Riddler and Joker disagree!!!!" 

- Actors: 

    - User (login to user account) 
    - Planetarium application (handles the login verification) 

# Test Data 
Positive: 

    - Registered username “Batman” 
    - Registered password “I am the night” 

Negative: 

    - too long username = "Gordon and Clark are friends!!!" 
    - too long password = "Reverse Flash strikes again!!!!" 
    - Non-Registered username = "Batman and Robin unite now!!!!" 
    - Non-Registered password = "Riddler and Joker disagree!!!!" 

# Scenarios 
**Positive Scenario**: 
1. get to landing page 
2. pick option to login 
3. provide valid username 
4. provide valid password 
5. user should be logged in with the planetarium 

**Negative Scenario Registered Username and Invalid Password**: 
1. get to landing page 
2. pick option to login 
3. provide valid username 
4. provide invalid password 
5. user should not be logged in with the planetarium 

**Negative Scenario Non-Registered Username**: 
1. get to landing page 
2. pick option to login 
3. provide non-registered username 
4. provide valid password 
5. user should not be logged in with the planetarium 

**Negative Scenario Non-Registered Username and invalid password**: 
1. get to landing page 
2. pick option to login 
3. provide non-registered username 
4. provide invalid password 
5. user should not be logged in with the planetarium 

# Decision Table
|Test Case ID|Scenario|Username|Password|Result| 
|------------|--------|--------|--------|------| 
|7|Positive|Registered username|Valid password|Logged In|
|8|Valid Username and Invalid Password|Registered username|Invalid password|Not Logged In| 
|9|Invalid Username|Non-Registered username|Valid password|Not Logged In|

# Use Case 3 
- Id: 3 
- Name: View Planets and Moons 
- Description: Users should be able to see planets and moons added to the Planetarium 
- System: Planetarium application 
- Preconditions: 

    - Registered user with username "Batman" and password “I am the night” 
    - Existing Planets and Moons in the database 

- Actors: 
    - Planets 
    - Moons 
    - Planetarium application (handles the display of the planets and moons) 

# Scenarios 
**Positive Scenario**: 
1. get to landing page 
2. pick option to login 
3. provide valid username 
4. provide valid password 
5. be sent to page with planets and moons 

# Decision Table
|Test Case ID|Scenario|Username|Password|Result| 
|------------|--------|--------|--------|------|  
|10|Positive|Valid Username|Valid Password|View planets and moons|

# Use Case 4 
- Id: 4 
- Name: Adding Planet 
- Description: Users should be able to add new Planets to the Planetarium 
- System: Planetarium application 
- Preconditions: 
    - Be logged in to the Planetarium 
    - Registered Planet with the name “Earth” and “Mars” 
- Actors: 
    - Planets 
    - Planetarium application (handles the creation of the new planets) 

# Test Data 

Positive: 

    - Valid Planet Name: “Amphitrite Euphrosyne Virginia” 
    - Planet Picture 

Negative: 

    - Invalid Planet Name: “Amphitrite Euphrosyne Virginia!” 
    - Non-Unique Planet Name: “Earth” or “Mars” 

# Scenarios 
**Positive Scenario Valid Planet name without picture**: 
1. get to Planet and Moon viewing page 
2. change selector to Planet 
3. insert valid Planet name 
4. click Submit Planet button 

**Positive Scenario Valid Planet name with picture**: 
1. get to Planet and Moon viewing page 
2. change selector to Planet 
3. insert valid Planet name 
4. click choose File 
5. choose an image for the planet 
6. click Submit Planet button 

**Negative Scenario planet name too long without picture**: 
1. get to Planet and Moon viewing page 
2. change selector to Planet 
3. insert valid Planet name 
4. click Submit Planet button 

**Negative Scenario planet name too long with picture**: 
1. get to Planet and Moon viewing page 
2. change selector to Planet 
3. insert too long Planet name 
4. click choose File 
5. choose an image for the planet 
6. click Submit Planet button 

**Negative Scenario non-unique planet name without picture**: 
1. get to Planet and Moon viewing page 
2. change selector to Planet 
3. insert valid Planet name 
4. click Submit Planet button 

**Negative Scenario non-unique planet name  with picture**: 
1. get to Planet and Moon viewing page 
2. change selector to Planet 
3. insert too long Planet name 
4. click choose File 
5. choose an image for the planet 
6. click Submit Planet button 

# Decision Table
|Test Case ID|Scenario|Planet Name|Picture|Result| 
|------------|--------|-----------|-------|------|  
|11|Positive 1|Valid Name|No|Saved|
|12|Positive 2|Valid Name|Yes|Saved|
|13|Negative 1|Too long Name|No|Not Saved|
|14|Negative 2|Too long Name|Yes|Not Saved|
|15|Negative 3|Non-Unique Name|No|Not Saved| 
|16|Negative 4|Non-Unique Name|Yes|Not Saved|

# Use Case 5 
- Id: 5 
- Name: Remove Planet 
- Description: Users should be able to remove Planets from the Planetarium 
- System: Planetarium application 
- Preconditions: 
    - Be Logged in to the Planetarium 
    - Have a planet named “Amphitrite Euphrosyne Virginia” 
    - No registered planet with the name “Vegeta” 

- Actors: 
    - Planets 
    - Planetarium application (handles the removal of planets) 

# Test Data 

Positive: 

    - Valid Planet Name: “Amphitrite Euphrosyne Virginia” 

Negative: 

    - Invalid Planet name: “Vegeta” 
# Scenarios 

**Positive Scenario**: 
1. get to Planet and Moon viewing page 
2. change selector to Planet 
3. insert valid Planet name 
4. click the Delete button 

**Negative Scenario**: 
1. get to Planet and Moon viewing page 
2. change selector to Planet 
3. insert invalid Planet name 
4. click the Delete button 

# Decision Table
|Test Case ID|Scenario|Planet Name|Result| 
|------------|--------|-----------|------| 
|17|Positive Scenario|Valid Planet Name|Data Removed|
|18|Negative Scenario|Invalid Planet Name|Data Not Removed|

# Use Case 6 
- Id: 6 
- Name: Adding Moon 
- Description: Users should be able to add Moons to the Planetarium associated with a Planet 
- System: Planetarium application 
- Preconditions: 
    - Be logged in to the Planetarium 
    - Registered Moons with the name “Luna” and “Titan” 
    - Registered Planets with the names “Earth” and “Mars” 
- Actors: 
    - Moons 
    - Planetarium application (handles the creation of the new moons) 
# Test Data 
Positive:

    -Valid Moon Name: “waxing crescent gibbous Moon!!” 
    -Moon Picture: “moon-1.jpg” 

Negative: 

    -Invalid Moon Name: “waxing crescent gibbous Moon!!!” 
    -Non-Unique Moon Name: “Luna” or “Titan” or “Earth” or “Mars” 

# Scenarios 
**Positive Scenario Valid Moon name and planet ID without picture**: 
1. get to Planet and Moon viewing page 
2. change selector to Moon 
3. insert valid Moon name 
4. insert valid Planet ID 
5. click Submit Moon button 

**Positive Scenario Valid Moon name and planet ID with picture**: 
1. get to Planet and Moon viewing page 
2. change selector to Moon 
3. insert valid Moon name 
4. insert valid Planet ID 
5. click choose File 
6. choose an image for the moon 
7. click Submit Moon button 

**Negative Scenario Moon name too long with valid planet ID and a picture**: 
1. get to Planet and Moon viewing page 
2. change selector to Moon 
3. insert too long Moon name 
4. insert valid Planet ID 
5. click choose File 
6. choose an image for the moon 
7. click Submit Moon button 

**Negative Scenario Moon name too long with valid planet ID and no picture**: 
1. get to Planet and Moon viewing page 
2. change selector to Moon 
3. insert too long Moon name 
4. insert valid Planet ID 
5. click Submit Moon button 

**Negative Scenario Valid Moon name with invalid planet ID and a picture**: 
1. get to Planet and Moon viewing page 
2. change selector to Moon 
3. insert valid Moon name 
4. insert valid Planet ID 
5. click choose File 
6. choose an image for the moon 
7. click Submit Moon button 

**Negative Scenario Valid Moon name with invalid planet ID and no picture**: 
1. get to Planet and Moon viewing page 
2. change selector to Moon 
3. insert too long Moon name 
4. insert valid Planet ID 
5. click Submit Moon button 

**Negative Scenario Moon name too long with invalid planet ID and a picture**: 
1. get to Planet and Moon viewing page 
2. change selector to Moon 
3. insert too long Moon name 
4. insert valid Planet ID 
5. click choose File 
6. choose an image for the moon 
7. click Submit Moon button 

**Negative Scenario Moon name too long with invalid planet ID and no picture**: 
1. get to Planet and Moon viewing page 
2. change selector to Moon 
3. insert too long Moon name 
4. insert valid Planet ID 
5. click Submit Moon button 

**Negative Scenario Non-unique Moon name with valid planet ID and a picture**: 
1. get to Planet and Moon viewing page 
2. change selector to Moon 
3. insert too long Moon name 
4. insert valid Planet ID 
5. click choose File 
6. choose an image for the moon 
7. click Submit Moon button 

**Negative Scenario Non-unique Moon name with valid planet ID and no picture**: 
1. get to Planet and Moon viewing page 
2. change selector to Moon 
3. insert too long Moon name 
4. insert valid Planet ID 
5. click Submit Moon button 

**Negative Scenario Non-unique Moon name with invalid planet ID and a picture**: 
1. get to Planet and Moon viewing page 
2. change selector to Moon 
3. insert too long Moon name 
4. insert valid Planet ID 
5. click choose File 
6. choose an image for the moon 
7. click Submit Moon button 

**Negative Scenario Non-unique Moon name with invalid planet ID and no picture**: 
1. get to Planet and Moon viewing page 
2. change selector to Moon 
3. insert too long Moon name 
4. insert valid Planet ID 
5. click Submit Moon button 

# Decision Table
|Test Case ID|Scenario|Moon Name|Planet Name|Picture|Result| 
|------------|--------|---------|-----------|-------|------| 
|19|Positive 1|Valid Name|Valid Planet|No|Data added|
|20|Positive 2|Valid Name|Valid Planet|Yes|Data added|
|21|Negative 1|Too long name|Valid Planet|Yes|Data not added|
|22|Negative 2|Too Long Name|Valid Planet|No|Data not added|
|23|Negative 3|Valid Name|Invalid Planet|Yes|Data not added| 
|24|Negative 4|Valid Name|Invalid Planet|No|Data not added|
|25|Negative 5|Too Long Name|Invalid Planet|Yes|Data not added|
|26|Negative 6|Too Long Name|Invalid Planet|No|Data not added|
|27|Negative 7|Non-Unique Name|Valid Planet|Yes|Data not added|
|28|Negative 8|Non-Unique Name|Valid Planet|No|Data not added|
|29|Negative 9|Non-Unique Name|Invalid Planet|Yes|Data not added|
|30|Negative 10|Non-Unique Name|Invalid Planet|No|Data not added| 

# Use Case 7 
- Id: 7 
- Name: Remove Moon 
- Description: Users should be able to remove Moons from the Planetarium 
- System: Planetarium application 
- Preconditions: 
    - Be logged in to the Planetarium 
    - Registered Moon called “Luna”  
    - No registered moon called “Vegeta” 
- Actors: 
    - Moons 
    - Planetarium application (handles the removal of moons) 
# Test Data 
Positive:

    - Valid moon name: “waxing crescent gibbous Moon!!” 
Negative:

    - Invalid moon name: “Vegeta” 

# Scenarios 
**Positive Scenario Valid Moon name**: 
1. get to Planet and Moon viewing page 
2. change selector to Moon 
3. input a valid moon name 
4. click the Delete button 

**Negative Scenario Invalid Moon name**: 
1. get to Planet and Moon viewing page 
2. change selector to Moon 
3. input an invalid moon name 
4. click the Delete button 

# Decision Table
|Test Case ID|Scenario|Moon Name|Result| 
|------------|--------|---------|------| 
|31|Positive Scenario|Valid Moon Name|Data Removed|
|32|Negative Scenario|Invalid Moon Name|Data Not Removed|

