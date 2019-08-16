@Iteration0
Feature: Health Check Test for ACE API Request Submission

  Scenario: ACE API Health Check
    Given ACE API Health Check URL
    When Invoke the Health Check URL
    Then user receives HTTP Status code 200
    And response body has status