@Iteration02 @Iteration03 @Regression @B-89263 @B-85366 @UAT @Iteration04 @UATREGRESSION 
Feature: DATA VALIDATION: As a user, when sending an invalid request, the appropriate warning messages should trigger.

  Scenario Outline: User Story: B-89263 and B-85366 - TC001a: User receives access token by sending a valid request to the APIGEE gateway
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

  Scenario Outline: User Story: B-89263 and B-85366 - TC001b: - User sends a request with invalid data and receives the appropriate error messages
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>" for "<scenarioName>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
	  Then User sends "<operation>" request to ACE API through APIGEE and verifies status code "<statuscode>"
    And User validates the expected message code "<messageCode>" and associated text in the response

    Examples: 
      | requestURL                  | RequestFile                   | scenarioName                | content_type     | operation | statuscode | messageCode |
      | UatAPIGEEAccessTokenBaseUrl | IncorrectLoanPurposeType.json | Incorrect Loan Purpose Type | application/json | POST      |        400 | SV005       |
      | UatAPIGEEAccessTokenBaseUrl | NoAddressLineText.json        | No Address Line Text        | application/json | POST      |        400 | SV002       |
      | UatAPIGEEAccessTokenBaseUrl | NoCityName.json               | No City Name                | application/json | POST      |        400 | SV003       |
      | UatAPIGEEAccessTokenBaseUrl | NoFipsStateAlphaCode.json     | No State Code               | application/json | POST      |        400 | SV007       |
      | UatAPIGEEAccessTokenBaseUrl | NoLoanPurposeType.json        | No Loan Purpose Type        | application/json | POST      |        400 | SV005       |
      | UatAPIGEEAccessTokenBaseUrl | NoPartyRoleIdentifier.json    | No Party Role Identifier    | application/json | POST      |        400 | SV001       |
      | UatAPIGEEAccessTokenBaseUrl | NoPropertyAmount.json         | No Property Amount          | application/json | POST      |        400 | SV006       |
      | UatAPIGEEAccessTokenBaseUrl | NoPostalCode.json             | No Postal Code              | application/json | POST      |        400 | SV004       |
      | UatAPIGEEAccessTokenBaseUrl | NoSalesContractAmount.json    | No Sales Contact Amount     | application/json | POST      |        400 | SV008       |
      | UatAPIGEEAccessTokenBaseUrl | NoPartyRoleType.json          | No Party Role Identifier    | application/json | POST      |        400 | SV009       |
