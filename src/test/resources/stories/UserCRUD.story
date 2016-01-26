Scenario: Add User successfully
 
Given a user with email bddtest@universitebordeaux.com and password is toto1234
When I add user
Then a user with email bddtest@universitebordeaux.com should be added into the database

