@UATREGRESSION 
Feature: DATE FORMAT VALIDATION - User sends an ace api request and receives a valid expiration date

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
      | UatAPIGEEAccessTokenBaseUrl | application/x-www-form-urlencoded | password   | OhAEtpEKxPCcUFFx9i1myZ4RjWhTwGQr | 9OqWuaafuGcXApf7 | baofam_lasapisys2sys26 | OBK]z/R4f98=aheWoH?j | POST      | $.access_token |
      
      Scenario Outline: User sends a valid requests, receives an ace eligible decision, and validates the expiration date format. 
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>" for "<scenarioName>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
    Then Service sends back ResponseCode
    Then User validates that the date is formatted correctly
    
    Examples: 
      | requestURL                  | content_type     | operation | RequestFile                | messageCode | messageText                                                                                                                                                                                                                                                                                                                                                        | elementName1 | elementValue1 | elementName2 | elementValue2                                                                                                                                                                                                                                                                                                                                                      |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | Eligible_AWP0001UAT.json      | AWP0001     | Based on the submitted data, the subject property is conditionally approved for an appraisal waiver. This approval is contingent upon the maximum loan amount, the expiration date, a complete assessment of the loan by Loan Product Advisor, and compliance with the eligibility requirements in the Freddie Mac Seller/Servicer Guide for the appraisal waiver. | messageCode1 | AWP0001       | messageText  | Based on the submitted data, the subject property is conditionally approved for an appraisal waiver. This approval is contingent upon the maximum loan amount, the expiration date, a complete assessment of the loan by Loan Product Advisor, and compliance with the eligibility requirements in the Freddie Mac Seller/Servicer Guide for the appraisal waiver. |