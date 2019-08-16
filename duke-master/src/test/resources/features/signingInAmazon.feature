@TestAmazon
Feature: users want to login to amazon webpage and signin and printout my order history in execel


  Scenario: user login amazon webpage with correct userid and password
    Given user is on amazon webpage and click signon button
    When user sign username and password
    #Then user validates the status
    When user find my order history and clic
    Then print out list of order history into report

    #Examples: 
      #| userName              | value            | status  |
      #| sthorson120@gmail.com | JHV5Z2h1cjMyVQ== | success |
