@Iteration03 @Iteration04 @B-87093 @Regression @UATREGRESSION @TestCool
Feature: EDS INTEGRATION - User sends a valid ace request through APIGEE, and should receive the correct EDS calculated fields in the ace response

  Scenario Outline: User Story: B-87093 - TC001a: A User sends a valid request to APIGEE and receives a valid access token
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

  Scenario Outline: User Story: B-87093 - TC001b: User takes the access token and sends an ace request using valid data. Then the user receives the expected response using the EDS decision calculations.
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
    Then Service sends back ResponseCode
    Then Validate that the JSON response value "<elementName1>" equals "<elementValue1>" in the response
    Then Validate that the JSON response value "<elementName2>" equals "<elementValue2>" in the response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile                 | elementName1                | elementValue1 | elementName2                            | elementValue2 |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | RefinanceEligibleTX.json    | maximumAuthorizedLoanAmount |      239850.96 | appraisalWaiverPrescreenEligibilityType | Eligible      |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | RefinanceEligibleTX2.json   | maximumAuthorizedLoanAmount |      239850.96 | appraisalWaiverPrescreenEligibilityType | Eligible      |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | PurchaseEligibleLA.json     | maximumAuthorizedLoanAmount |     247862.12 | appraisalWaiverPrescreenEligibilityType | Eligible      |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | PurchaseEligibleGA.json     | maximumAuthorizedLoanAmount |      48728 | appraisalWaiverPrescreenEligibilityType | Eligible      |

  Scenario Outline: User Story: B-87093 - TC001b: User takes the access token and sends an ace request using valid data. Then the user receives the expected response using the EDS decision calculations.
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
    Then Service sends back ResponseCode
    Then Validate that the JSON response value "<elementName1>" equals null in the response
    Then Validate that the JSON response value "<elementName2>" equals "<elementValue2>" in the response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile                 | elementName1                | elementName2                            | elementValue2 |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | RefinanceNotEligibleGA.json | maximumAuthorizedLoanAmount | appraisalWaiverPrescreenEligibilityType | NotEligible   |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | PurchaseNotEligibleAL.json  | maximumAuthorizedLoanAmount | appraisalWaiverPrescreenEligibilityType | NotEligible   |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | PurchaseNotEligibleUT.json  | maximumAuthorizedLoanAmount | appraisalWaiverPrescreenEligibilityType | NotEligible   |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | RefinanceNotEligibleCO.json | maximumAuthorizedLoanAmount | appraisalWaiverPrescreenEligibilityType | NotEligible   |

  Scenario Outline: User Story: B-87093 - TC001c: User takes the access token and sends an ace request using invalid data. Then the user receives the expected Not APplicapable decision from EDS.
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
    Then Service sends back ResponseCode
    Then Validate that the JSON response value "<elementName1>" equals "<elementValue1>" in the response
    Then Validate that the JSON response value "<maximumLoan>" equals null in the response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile          | elementName1                            | elementValue1 | maximumLoan                     |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | InvalidEDSFile1.json | appraisalWaiverPrescreenEligibilityType | NotApplicable | maximumAuthorizedLoanAmountNull |
