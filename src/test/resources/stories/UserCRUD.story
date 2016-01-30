Scenario: Add User successfully
 
Given a user with email landry.desire.nzi@live.fr and mdp is toto1234
When I add user
Then a user with email landry.desire.nzi@live.fr should be added into the database

