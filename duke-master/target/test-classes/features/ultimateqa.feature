Feature: Ultimate Qa Pages
@ultimatehqtest
Scenario: Validating login with negative credentials
Given User is on the log in page 
When User enters wrong username and password
And Click on the sign in button 
Then User should see error message

