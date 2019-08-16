@Iteration03 @Iteration04 @Regression @B-87086 @UATREGRESSION
Feature: CARDINALITY VALIDATION - User sends a request with valid and invalid cardinalities in the request body. User should see the expected
  responses.

  Scenario Outline: User Story: B-87086 - Scenario 0001a - User receives access token by sending a valid request to the APIGEE gateway
    Given User sets APIGEE Portal ACE API Access Token request "<requestURL>" url
    And content type is "<content_type>" for APIGEE Developer Portal
    And passing granttype "<grant_type>" parameter
    And passing clientid "<client_id>" parameter
    And passing clientsecret "<client_secret>" parameter
    And passing username "<username>" parameter
    And passing pwd "<password>" parameter
    When Request is performing "<operation>" on APIGEE Gateway
    Then Service sends back Access Token
    Then Store the "<accesstoken>" in ACE API Local

    Examples: 
      | requestURL                  | content_type                      | grant_type | client_id                        | client_secret    | username              | password             | operation | accesstoken    |
      | UatAPIGEEAccessTokenBaseUrl | application/x-www-form-urlencoded | password   | OhAEtpEKxPCcUFFx9i1myZ4RjWhTwGQr | 9OqWuaafuGcXApf7 | baofam_lasapisys2sys26 | OBK]z/R4f98=aheWoH?j | POST      | $.access_token |

  Scenario Outline: User Story: B-87086 - Scenario 0001b - User sends a request with an invalid containter count in the request body. The request should not process and the user
    receives an error.

    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>" for "<scenarioName>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When Request is performing "<operation>" on APIGEE Gateway ACE API
    Then Service sends back "<statuscode>"

    Examples: 
      | requestURL                  | RequestFile                                   | scenarioName                   | content_type     | operation | statuscode |
#      | UatAPIGEEAccessTokenBaseUrl | ThreeAddressContainersAceRequest.json         | Two Service Request Containers | application/json | POST      |        500 |
      | UatAPIGEEAccessTokenBaseUrl | NoAddressContainersAceRequest.json            | No Service Request Containers  | application/json | POST      |        500 |
      | UatAPIGEEAccessTokenBaseUrl | ThreeLoanInformationContainersAceRequest.json | Six Service Request Containers | application/json | POST      |        500 |
      | UatAPIGEEAccessTokenBaseUrl | NoLoanInformationContainersAceRequest.json    | Six Service Request Containers | application/json | POST      |        500 |
#      | UatAPIGEEAccessTokenBaseUrl | ThreePartyContainersAceRequest.json           | Six Service Request Containers | application/json | POST      |        500 |
      | UatAPIGEEAccessTokenBaseUrl | NoPartyContainersAceRequest.json              | Six Service Request Containers | application/json | POST      |        500 |
      | UatAPIGEEAccessTokenBaseUrl | NoContainersAceRequest.json                   | Six Service Request Containers | application/json | POST      |        500 |
