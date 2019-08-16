@Iteration2 @Iteration03 @Regression @B-82493 @B-81870 @B-87092 @UATREGRESSION @TestNew
Feature: HVE INTEGRATION - A user sends an ace request with the appropriate address property information. Then the user should receive the scrubbed address back from EDS.

  Scenario Outline: User Story: B-82493 and B-81870 and B-87092 - Scenario 0001a - The user sends a request to APIGEE and receives a valid Access token
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

  Scenario Outline: User Story: B-82493 and B-81870 and B-87092 - Scenario 0001b - Positive Scenarios- User takes the access token and sends an ace request and looks to receive the expected scrubbed address from HVE
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
    Then Service sends back ResponseCode
    Then Validate that the JSON response value "<elementName1>" equals "<elementValue1>" in the response
    Then Validate that the JSON response value "<elementName2>" equals "<elementValue2>" in the response
    Then Validate that the JSON response value "<elementName3>" equals "<elementValue3>" in the response
    Then Validate that the JSON response value "<elementName4>" equals "<elementValue4>" in the response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile        | elementName1    | elementValue1          | elementName2 | elementValue2  | elementName3 | elementValue3 | elementName4 | elementValue4 |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | ValidAddress1.json | addressLineText | 420 CLEVELAND ST       | cityName     | ENDERLIN       | stateCode    | ND            | postalCode   |         58027 |
#      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | ValidAddress2.json | addressLineText | 1938 TINNIN RD         | cityName     | GOODLETTSVILLE | stateCode    | TN            | postalCode   |         37072 |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | ValidAddress3.json | addressLineText | 2956 CASHEL LN         | cityName     | VIENNA         | stateCode    | VA            | postalCode   |         22181 |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | ValidAddress4.json | addressLineText | 2668 BRUTON SPRINGS DR | cityName     | DALLAS         | stateCode    | TX            | postalCode   |         75227 |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | ValidAddress8.json | addressLineText | 420 CLEVELAND ST       | cityName     | ENDERLIN       | stateCode    | ND            | postalCode   |         58027 |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | ValidAddress6.json | addressLineText | 1938 TINNIN RD         | cityName     | GOODLETTSVLLE  | stateCode    | TN            | postalCode   |         37072 |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | ValidAddress5.json | addressLineText | 2956 CASHEL LN         | cityName     | VIENNA         | stateCode    | VA            | postalCode   |         22181 |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | ValidAddress7.json | addressLineText | 2668 BRUTON SPRINGS DR | cityName     | DALLAS         | stateCode    | TX            | postalCode   |         75227 |

  Scenario Outline: User Story: B-82493 and B-81870 and B-87092 - Scenario 0001c - Negative Scenarios - User takes the access token and sends an ace request with an invalid address. The user then receives a null value from HVE.
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
    Then Validate that the JSON response value "<elementName1>" equals null in the response

    #    Then Validate that the JSON response value "<elementName1>" equals "<elementValue1>" in the response
    Examples: 
      | requestURL                  | content_type     | operation | RequestFile          | elementName1 |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | InvalidAddress1.json | address      |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | InvalidAddress2.json | address      |

