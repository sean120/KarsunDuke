@RegressionSIT
Feature: ACE API Response Message Codes Validation

  Scenario Outline: User sends Valid ACE API Request and gets the Response Message Code
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>" for "<scenariosname>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    When Request is performing "<operation>" on APIGEE Gateway ACE API
    Then Service sends back "<statuscode>"
    Then validate the message code "<messageCode>" and associated message in ACE API response

    Examples: 
      | RequestFile                    | scenariosname             | content_type     | operation | statuscode | messageCode |
      | inputfile10.json        	   | LoanEligible              | application/json | POST      |        200 | AWP0001     |
      | NotEligibleACEAPIRequest.json  | LoanNOTEligible           | application/json | POST      |        200 | AWP0002     |
      #  | ValidACEAPIRequest.json                        | InternalSystemDown_HVE_EDS_OR_ED        | application/json | POST      |        200 | AWP0003    |
      | RDSIncorrectACEAPIRequest.json | RDS_CAN_NOT_Match_Address | application/json | POST      |        200 | AWP0004     |
