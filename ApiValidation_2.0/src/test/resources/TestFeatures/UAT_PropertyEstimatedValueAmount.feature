@Iteration03 @Iteration04 @Regression @B-88459 @B-91369 @UATREGRESSION
Feature: PROPERTY ESTIMATED VALUE AMOUNT - User is sending requests with updated fields and receiving responses through the APIGEE gateway

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
    Then User sees the expected "<messageCode>" value in the APIGEE ace response
    Then User sees the expected "<messageText>" value in the APIGEE ace response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile                               | messageCode | messageText                                                                                                                                                                                                                                                                                                                                                        |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | PropertyEstimatedValueAmount_NotNull.json | AWP0001     | Based on the submitted data, the subject property is conditionally approved for an appraisal waiver. This approval is contingent upon the maximum loan amount, the expiration date, a complete assessment of the loan by Loan Product Advisor, and compliance with the eligibility requirements in the Freddie Mac Seller/Servicer Guide for the appraisal waiver. |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | PropertyEstimatedValueAmount_Exceeds.json | AWP0002     | Based on the submitted data, the subject property is not eligible for an appraisal waiver.                                                                                                                                                                                                                                                                         |

  Scenario Outline: User takes the access token and sends another request and receives a valid response
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
    Then Service sends back ResponseCode
    Then User sees the expected "<messageCode>" value in the APIGEE ace response
    Then User sees the expected "<messageText>" value in the APIGEE ace response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile                            | messageCode | messageText                                                                                |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | PropertyEstimatedValueAmount_Null.json | AWP0002     | Based on the submitted data, the subject property is not eligible for an appraisal waiver. |

  Scenario Outline: User takes the access token and sends another request and receives a valid response
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
    Then Service sends back ResponseCode
    Then User sees the expected "<messageCode>" value in the APIGEE ace response
    Then User sees the expected "<messageText>" value in the APIGEE ace response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile                      | messageCode | messageText                                                                                                                                                                                                                                                                                                                                                        |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | SalesContractAmount_NotNull.json | AWP0001     | Based on the submitted data, the subject property is conditionally approved for an appraisal waiver. This approval is contingent upon the maximum loan amount, the expiration date, a complete assessment of the loan by Loan Product Advisor, and compliance with the eligibility requirements in the Freddie Mac Seller/Servicer Guide for the appraisal waiver. |

  Scenario Outline: User takes the access token and sends another request and receives a valid response
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
    Then Service sends back ResponseCode
    Then User sees the expected "<messageCode>" value in the APIGEE ace response
    Then User sees the expected "<messageText>" value in the APIGEE ace response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile                   | messageCode | messageText                                                                                |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | SalesContractAmount_Null.json | AWP0002     | Based on the submitted data, the subject property is not eligible for an appraisal waiver. |

  Scenario Outline: User takes the access token and sends another request and receives a valid response with updated field MaxLoanAmount with $ sign
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
    Then Service sends back ResponseCode
    Then Validate that the JSON response value "<elementName1>" equals "<elementValue1>" in the response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile           | elementName1                | elementValue1 |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | Eligible_AWP0001UAT.json | maximumAuthorizedLoanAmount |      239850.96 |

  Scenario Outline: User takes the access token and sends another request and receives a valid response with updated field MaxLoanAmount with $ sign
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
    Then Service sends back ResponseCode
    Then Validate that the JSON response value "<maximumLoan>" equals null in the response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile              | maximumLoan                 |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | NotEligible_AWP0002UAT.json | maximumAuthorizedLoanAmount |
