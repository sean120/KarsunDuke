
Feature: log into facebook page and check out couple of updates and confirm it is today's update with date format conformation

  
  #Background:
  #Given user is on facebook page
 
  #Scenario: login functionality of social networking platform.
  #Given user navigate to face book page loginPage
  #When user enters userName
  #And user enters passWord
  #And user clicks on login
  #Then user should be abble to see the successful login
  
  
  
  
   @myfacebook
  Scenario: after failed attemps number of times. the user should be able to login again
    When the user enters set of usernames and password
      | username                | password   |
      | smiddleton393@gmail.com | shaungulja |
      | sthorson120@gmail.com   | uyghur32U  |
    #Then user should be abble to see the successful login
