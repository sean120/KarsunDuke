@Iteration02 @Regression @UAT @UATREGRESSION
Feature: APIGEE GATEWAY - user sends the APIGEE gateway to send and receive the ace response using an APIGEE provided access token

  Scenario Outline: Positive Scenario - TC001a: User sends a an APIGEE request and receives an access token
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

  Scenario Outline: Positive Scenario - TC001b: User takes the access token and sends another request in order to receive the expected response
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When Request is performing "<operation>" on APIGEE Gateway ACE API
    Then Service sends back ResponseCode
    Then Validate that the JSON response value "<elementName1>" equals "<elementValue1>" in the response
    Then Validate that the JSON response value "<elementName2>" equals "<elementValue2>" in the response
    Then Validate that the JSON response value "<elementName3>" equals "<elementValue3>" in the response
    Then Validate that the JSON response value "<elementName4>" equals "<elementValue4>" in the response
    Then Validate that the JSON response value "<elementName5>" equals "<elementValue5>" in the response
    Then Validate that the JSON response value "<elementName6>" equals "<elementValue6>" in the response
    Then Validate that the JSON response value "<elementName7>" equals "<elementValue7>" in the response
    Then Validate that the JSON response value "<elementName8>" equals "<elementValue8>" in the response
    Then Validate that the JSON response value "<elementName9>" equals "<elementValue9>" in the response
    Then Validate that the JSON response value "<elementName10>" equals "<elementValue10>" in the response
    Then Validate that the JSON response value "<elementName11>" equals "<elementValue11>" in the response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile     | elementName1                            | elementValue1 | elementName2                           | elementValue2 | elementName3    | elementValue3 | elementName4 | elementValue4 | elementName5 | elementValue5 | elementName6 | elementValue6 | elementName7                | elementValue7 | elementName8                | elementValue8 | elementName9  | elementValue9 | elementName10       | elementValue10 | elementName11 | elementValue11 |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | ValidAddress4.json | appraisalWaiverPrescreenEligibilityType | NoValue       | appraisalWaiverPrescreenExpirationDate | NoValue       | addressLineText | NoValue       | cityName     | NoValue       | stateCode    | NoValue       | postalCode   | NoValue       | maximumAuthorizedLoanAmount | NoValue       | maximumAuthorizedLoanAmount | NoValue       | partyRoleType | NoValue       | partyRoleIdentifier | NoValue        | messageCode1  | NoValue        |
