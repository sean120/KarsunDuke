@Iteration03 @Iteration04 @B-87093 @EndtoEnd @Regression @UATREGRESSION
Feature: END TO END: User performs an end to end scenario starting with sending a valid request to apigee gateway, receives an authentication token,
  sends a valid ace request using this token and valid request data, then user validates that the data in the response is correct.

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
      | requestURL                  | content_type                      | grant_type | client_id                        | client_secret    | username               | password             | operation | accesstoken    |
      | UatAPIGEEAccessTokenBaseUrl | application/x-www-form-urlencoded | password   | OhAEtpEKxPCcUFFx9i1myZ4RjWhTwGQr | 9OqWuaafuGcXApf7 | baofam_lasapisys2sys26 | OBK]z/R4f98=aheWoH?j | POST      | $.access_token |

  Scenario Outline: User takes the access token and sends another request and receives a valid response
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
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
    Then Return the appraisalWaiverPrescreenEligibilityType and scrubbed Address from the response
    Then Login the LPA application
    And User uploads loan application "<LPALoanFileName>" in the LPA system
    Then User validates the expected Ace Eligibility and scrubbed address in LPA
    Then User closes and exits the browser

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile                       | elementName1                            | elementValue1 | elementName2    | elementValue2              | elementName3 | elementValue3     | elementName4 | elementValue4 | elementName5 | elementValue5 | elementName6                | elementValue6 | elementName7  | elementValue7 | elementName8        | elementValue8 | LPALoanFileName           | elementName9 | elementValue9 | elementName10 | elementValue10                                                                                                                                                                                                                                                                                                                                                     |
      #     | application/json | POST      | AceApiRefinanceAceEligible.json    | appraisalWaiverPrescreenEligibilityType | Eligible      | addressLineText | 14576 BERKLEE DR           | cityName     | ADDISON           | stateCode    | TX            | postalCode   |         75001 | maximumAuthorizedLoanAmount | NoValue       | partyRoleType | LoanSeller    | partyRoleIdentifier | NoValue       | LPARefinanceAceEligible    | AWP0001     |
      #    | application/json | POST      | AceApiPurchaseAceEligible.json     | appraisalWaiverPrescreenEligibilityType | Eligible      | addressLineText | 10211 MAGNOLIA BLOSSOM AVE | cityName     | GREENWELL SPRINGS | stateCode    | LA            | postalCode   |         70739 | maximumAuthorizedLoanAmount | NoValue       | partyRoleType | LoanSeller    | partyRoleIdentifier | NoValue       | LPAPurchaseAceEligible     | AWP0001     |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | ACEAPIPurchaseAceNotEligibleUAT.json | appraisalWaiverPrescreenEligibilityType | NotEligible   | addressLineText | 9204 MARSH MOUNTAIN RD     | cityName     | PINSON            | stateCode    | AL            | postalCode   |         35126 | maximumAuthorizedLoanAmount | NoValue       | partyRoleType | LoanSeller    | partyRoleIdentifier |        121895 | LPAPurchaseAceNotEligible | messageCode1 | AWP0002       | messageText   | Based on the submitted data, the subject property is not eligible for an appraisal waiver.                                                                                                                                                                                                                                                                         |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | ACEAPIPurchaseAceEligibleUAT.json    | appraisalWaiverPrescreenEligibilityType | Eligible      | addressLineText | 10211 MAGNOLIA BLOSSOM AVE | cityName     | GREENWELL SPRINGS | stateCode    | LA            | postalCode   |         70739 | maximumAuthorizedLoanAmount |     247862.12 | partyRoleType | LoanSeller    | partyRoleIdentifier |        121895 | LPAPurchaseAceEligible    | messageCode1 | AWP0001       | messageText   | Based on the submitted data, the subject property is conditionally approved for an appraisal waiver. This approval is contingent upon the maximum loan amount, the expiration date, a complete assessment of the loan by Loan Product Advisor, and compliance with the eligibility requirements in the Freddie Mac Seller/Servicer Guide for the appraisal waiver. |
 #     | application/json | POST      | AceApiRefinanceAceNotEligible.json | appraisalWaiverPrescreenEligibilityType | NotEligible   | addressLineText | 141 DENNIS SMITH RD        | cityName     | PINE MOUNTAIN     | stateCode    | GA            | postalCode   |         31822 | maximumAuthorizedLoanAmount | NoValue       | partyRoleType | LoanSeller    | partyRoleIdentifier | NoValue       | LPARefinanceAceNotEligible | AWP0002     |
