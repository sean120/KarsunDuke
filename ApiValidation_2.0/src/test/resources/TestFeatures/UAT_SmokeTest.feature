@UATSMOKETEST
Feature: ACE RESPONSE -A user sends a request to Ace API with the appropriate information and receives the expected response from ACE api

  Scenario Outline: User Story: B-81324 and B-81877 and B-84976 - TC001a: A user sends a request to APIGEE and receives a valid access token
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

  Scenario Outline: User Story: B-81324 and B-81877 and B-84976 - TC001b: The user sends an ace request with the valid access token and receives a valid response in the correct format
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
    Then Service sends back ResponseCode

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile     | elementName1                            | elementValue1 | elementName2                           | elementValue2 | elementName3    | elementValue3 | elementName4 | elementValue4 | elementName5 | elementValue5 | elementName6 | elementValue6 | elementName7                | elementValue7 | elementName8                | elementValue8 | elementName9  | elementValue9 | elementName10       | elementValue10 | elementName11 | elementValue11 |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | ValidAddress4.json | appraisalWaiverPrescreenEligibilityType | NoValue       | appraisalWaiverPrescreenExpirationDate | NoValue       | addressLineText | NoValue       | cityName     | NoValue       | stateCode    | NoValue       | postalCode   | NoValue       | maximumAuthorizedLoanAmount | NoValue       | maximumAuthorizedLoanAmount | NoValue       | partyRoleType | NoValue       | partyRoleIdentifier | NoValue        | messageCode1  | NoValue        |
