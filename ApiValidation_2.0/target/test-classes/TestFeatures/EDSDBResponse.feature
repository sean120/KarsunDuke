@RegressionSIT
Feature: Persistance of EDS Request and Response in Mongo DB
  @Iteration03SITEDS
  Scenario Outline: EDS Response Stored in Mongo DB
    Given APIGEE Developer Portal ACE API Valid AccessToken and Valid "<RequestFile>"
    When User Sends request to APIGEE Gateway ACE API
    Then ACE API sends back ACE Response
    Then Check Mongo DB for EDS Response
    Then Validate the EDS Response

    Examples: 
      | RequestFile             |
      | inputfile10.json |

#  Scenario Outline: EDS Request Stored in Mongo DB
#    Given APIGEE Developer Portal ACE API Valid AccessToken and Valid "<RequestFile>"
#    When User Sends request to APIGEE Gateway ACE API
#    Then ACE API sends back ACE Response
#    Then Check Mongo DB for EDS Request
#    Then Validate the EDS Request
#
#    Examples: 
#      | RequestFile                 |
#      | PurchaseACEAPIRequest.json  |
#      | RefinanceACEAPIRequest.json |
      
      
      
      
