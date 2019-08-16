@Iteration03 @Iteration04 @B-87405 @Regression @UATREGRESSION 
Feature: SALES CONTRACT AMOUNT - User is sending requests with new updated fields and receiving valid responses through the APIGEE gateway

  Scenario Outline: Appraisal Waiver Pre-screen Customer - User receives access token by sending a valid request to the APIGEE gateway
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
      | UatAPIGEEAccessTokenBaseUrl | application/x-www-form-urlencoded | password   | OhAEtpEKxPCcUFFx9i1myZ4RjWhTwGQr | 9OqWuaafuGcXApf7 | baofam_lasapisys2sys26 | OBK]z/R4f98=aheWoH?j| POST      | $.access_token |

  #positiveScenario
  Scenario Outline: User takes the access token and sends another request and receives a valid response
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
    Then Service sends back ResponseCode
    Then Validate that the JSON response value "<elementName7>" equals "<elementValue7>" in the response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile                        | elementName7                            | elementValue7 |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | SalesContractAmount_StateCode.json | appraisalWaiverPrescreenEligibilityType | NoValue       |

  #negativeScenario
  Scenario Outline: User takes the access token and sends another request with invalid schema and receiving an error message
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When Request is performing "<operation>" on APIGEE Gateway ACE API
    Then Service sends back "<statuscode>"
    Then User sees the expected "<errorMessage>" value in the APIGEE ace response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile                            | statuscode | errorMessage                                                                          |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | SalesContractAmount_FIPSStateCode.json |        400 | Request data does not match the application schema, please validate the request data. |
