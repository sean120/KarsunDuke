@filelogin
Feature: Actitime Practice Page
@actiTest001
Scenario: Validating login with positive credentials
Given user is on the log in page
When user enters valid login credentials
And click on log in button
Then time track page should be displayed

@actiTest002
Scenario: Entering valid data set to TimeTrack Page
Given user is on the Time Track Page
When user enters task details
And click on the save changes btn
Then the total working time should display correctly

@actiTest003
Scenario: Validating View Time-Track Page
Given user is on the View Time-Track Page
When user selects current month type
And clicks on the month type
Then user should be able to see the details
#
#@actiTest004
#Scenario: Validating View Time-Track Page
#Given user is on the View Time-Track Page
#When user clicks on the filter
#And selects Customer or Project Detail
#And user clicks close and apply
#Then user 
