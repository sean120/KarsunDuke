@Iteration03 @Iteration04 @Regression @B-85149 @B-90706 @B-90698 @B-91374 @UATREGRESSION
Feature: RESPONSE MESSAGING - User is sending the request and receiving appropriate message code depending on the Ace Eligibility

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

  #Testing AWP_0003 Server Down Error Message
  #Scenario Outline: User takes the access token and sends another request when EDS UAT server is down and receives an error message along with corresponding text
  #Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
  #And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
  #Given User sets APIGEE Portal ACE API request "<requestURL>" url
  #When Request is performing "<operation>" on APIGEE Gateway ACE API
  #Then Service sends backas ResponseCode
  #Then User sees the expected "<messageCode>" value in the APIGEE ace response
  #Then User sees the expected "<messageText>" value in the APIGEE ace response
  #Then Validate that the JSON response value "<elementName1>" equals "<elementValue1>" in the response
  #Then Validate that the JSON response value "<elementName2>" equals "<elementValue2>" in the response
  #
  #Examples:
  #| requestURL  | content_type     | operation | RequestFile                | messageCode | messageText                                                                                                                       | elementName1 | elementValue1 | elementName2 | elementValue2                                                                                        |
  #| UatAPIGEEAccessTokenBaseUrl| application/json | POST      | NotApplicable_AWP0003.json | AWP0003      |Unable to assess the submitted property for appraisal waiver eligibility at this time. Resubmit for an assessment. | messageCode1 | AWP0003        | messageTextNotApplicable  | Unable to assess the submitted property for appraisal waiver eligibility at this time. Resubmit for an assessment. |
  Scenario Outline: User takes the access token and sends another request and receives a valid message codes along with corresponding text
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
    Then Service sends back ResponseCode
    Then User sees the expected "<messageCode>" value in the APIGEE ace response
    Then User sees the expected "<messageText>" value in the APIGEE ace response
    Then Validate that the JSON response value "<elementName1>" equals "<elementValue1>" in the response
    Then Validate that the JSON response value "<elementName2>" equals "<elementValue2>" in the response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile                | messageCode | messageText                                                                                                                                                                                                                                                                                                                                                        | elementName1 | elementValue1 | elementName2 | elementValue2                                                                                                                                                                                                                                                                                                                                                      |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | Eligible_AWP0001UAT.json      | AWP0001     | Based on the submitted data, the subject property is conditionally approved for an appraisal waiver. This approval is contingent upon the maximum loan amount, the expiration date, a complete assessment of the loan by Loan Product Advisor, and compliance with the eligibility requirements in the Freddie Mac Seller/Servicer Guide for the appraisal waiver. | messageCode1 | AWP0001       | messageText  | Based on the submitted data, the subject property is conditionally approved for an appraisal waiver. This approval is contingent upon the maximum loan amount, the expiration date, a complete assessment of the loan by Loan Product Advisor, and compliance with the eligibility requirements in the Freddie Mac Seller/Servicer Guide for the appraisal waiver. |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | NotEligible_AWP0002UAT.json   | AWP0002     | Based on the submitted data, the subject property is not eligible for an appraisal waiver.                                                                                                                                                                                                                                                                         | messageCode1 | AWP0002       | messageText  | Based on the submitted data, the subject property is not eligible for an appraisal waiver.                                                                                                                                                                                                                                                                         |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | NotApplicable_AWP0004UAT.json | AWP0004     | The subject property address cannot be validated. Please confirm accuracy and resubmit if necessary.                                                                                                                                                                                                                                                               | messageCode1 | AWP0004       | messageText  | The subject property address cannot be validated. Please confirm accuracy and resubmit if necessary.                                                                                                                                                                                                                                                               |

  #postive
  Scenario Outline: User takes the access token and sends another request and receives a valid response with updated field MaxLoanAmount with $ sign
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
    Then Service sends back ResponseCode
    Then Validate that the JSON response value "<elementName1>" equals "<elementValue1>" in the response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile           | elementName1                | elementValue1 |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | Eligible_AWP0001UAT.json | maximumAuthorizedLoanAmount |     239850.96 |

  Scenario Outline: User takes the access token and sends another request and receives a valid response with updated field MaxLoanAmount with $ sign
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
    Then Service sends back ResponseCode
    Then Validate that the JSON response value "<maximumLoan>" equals null in the response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile                       | maximumLoan                 |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | NotEligible_AWP0002UAT.json          | maximumAuthorizedLoanAmount |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | Purchase_MaxLoanAmount_Zero.json  | maximumAuthorizedLoanAmount |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | Refinance_MaxLoanAmount_Zero.json | maximumAuthorizedLoanAmount |
