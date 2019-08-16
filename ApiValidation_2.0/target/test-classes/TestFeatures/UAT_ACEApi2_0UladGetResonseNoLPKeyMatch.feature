@Iteration04323
Feature: ACE 2.0 Modified GFS Logic for ULAD

  @DELETEGFS
  
  Scenario: user send valid ace api2.0 request 
  
    Then User deletes "grandFatheringAceData" data that matches the "1009 CIRCLE DR" address in the database
  
    Scenario Outline: USer sends ace 2.0 reuqest nad 
  
    Given ACEAPI2.0 request payload in the file "017_ULADRequest1009CircleDrive.json"
 When User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-02-min.json"
      | addressLineText   | cityName   | postalCode   | stateCode   | loanPurposeType   | propertyValuationAmount   | borrowerPropertyPurchasePriceAmount   | ltvRatioPercent1   | LoanID_LPKey        | LoanID_LPUL         | LoanID_LPT | subscriberRequestCorrelationIdentifier | alternateAppraisalEligibilityDecision   |
      | <addressLineText> | <cityName> | <postalCode> | <stateCode> | <loanPurposeType> | <propertyValuationAmount> | <borrowerPropertyPurchasePriceAmount> | <ltvRatioPercent1> | __Run_ID__<transID> | __Run_ID__<transID> | <transID>  | __Run_ID__<transID>                    | <alternateAppraisalEligibilityDecision> |
    

    Examples: 
      | transID | addressLineText      | cityName | postalCode | stateCode | loanPurposeType | propertyValuationAmount | borrowerPropertyPurchasePriceAmount | ltvRatioPercent1 | 
      |    1001 | 11931 BENTON LAKE RD | BRISTOW  |      20136 | VA        | Purchase        |                  324260 |                              325010 |               45 |
      |    1002 | 1938 TINNIN RD       | GREELEY  |      37072 | CO        | Purchase        |                  324260 |                              325010 |               45 |

  Scenario Outline: User sends a valid ace api 2.0 request and there should be no grandfathered data
    Then User retrieves first record from the "grandFatheringAceResponse" collection in the database
    Then Validate that the grandfathered element "<GFSElement>" equals "<GFSValue>" in the "grandFatheringAceResponse"  Database collection

    Examples: 
      | GFSElement | GFSValue       |
      | message    | Data Not Found |

  Scenario: User sends a valid ace api 2.0 request
    Then User deletes "grandFatheringAceData" data that matches the "1009 CIRCLE DR" address in the database

  Scenario Outline: User Sends Ace api 1.0 apigee request
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
      | UatAPIGEEAccessTokenBaseUrl | application/x-www-form-urlencoded | password   | LnJ8T4am6A7oGneoPWbREox81mb8TK9g | F8DvQeQAEcQb0Zb6 | webslr1_lasapisys2sys | Yki58s%wU0W]@unLfgXM | POST      | $.access_token |

  Scenario Outline: User sends valida ace 1.0 request with apigee token
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    Given User sets UAT resource path
    When User sends "<operation>" request to ACE API through APIGEE
    Then Service sends back ResponseCode

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile                           | elementName1                            |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | ACE1_01009CircleDriver.json | appraisalWaiverPrescreenEligibilityType |

  Scenario: User sends a valid ace api 2.0 request
    Then User retrieves first record from the "grandFatheringAceData" collection in the database
    Then Return initial time stamp
   Given ACEAPI2.0 request payload in the file "017_ULADRequest1009CircleDrive.json"
    Then User retrieves first record from the "grandFatheringAceData" collection in the database
    Then Validate the expected time stamp

  Scenario Outline: User should see ace 1.0 grandfathered data
    Then Validate that the grandfathered element "<GFSElement>" equals "<GFSValue>" in the "grandFatheringAceData"  Database collection

    Examples: 
      | GFSElement                           | GFSValue       |
      | addressLineText                      | 1009 CIRCLE DR |
      | alternateAppraisalDecisionStatusType | current  |
