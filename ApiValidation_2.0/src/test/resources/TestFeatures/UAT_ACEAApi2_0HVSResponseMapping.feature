@B-113949 @ACE2_0UATREGRESSION @HVSRESPONSEMAPPING23
Feature: HVS to EDS Response Mapping



 Scenario Outline: ACE API 2.0 GFS validation
    When User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-02-min.json"
      | addressLineText   | cityName   | postalCode   | stateCode   | loanPurposeType   | propertyValuationAmount   | borrowerPropertyPurchasePriceAmount   | ltvRatioPercent1   | LoanID_LPKey        | LoanID_LPUL         | LoanID_LPT | subscriberRequestCorrelationIdentifier | alternateAppraisalEligibilityDecision   |
      | <addressLineText> | <cityName> | <postalCode> | <stateCode> | <loanPurposeType> | <propertyValuationAmount> | <borrowerPropertyPurchasePriceAmount> | <ltvRatioPercent1> | __Run_ID__<transID> | __Run_ID__<transID> | <transID>  | __Run_ID__<transID>                    | <alternateAppraisalEligibilityDecision> |

  #Scenario: User sends ACE 2.0 request and validtes EDS request mapping
   #Given ACEAPI2.0 request payload in the file "017_ULADRequest1009CircleDrive.json"
    Then User retrieves first record from "ACEFullAssessmentRequests" Ace Requests in the database
    Then User retrieves "REQUEST" for "EDS" from UAT Mongo DB
    Then Validate HVS Response to EDS Request Mapping


    Examples: 
      | transID | addressLineText        | cityName        | postalCode | stateCode | loanPurposeType | propertyValuationAmount | borrowerPropertyPurchasePriceAmount | ltvRatioPercent1 | alternateAppraisalEligibilityDecision |
      |    1001 | 14576 BERKLEE DR       | ADDISON         |      75001 | TX        | Purchase        |                  287671 |                              325010 |               50 | Eligible                              |
      |    1002 | 2140 DEMETRIUS WAY     | ROSEVILLE       |      95661 | CA        | Refinance       |                  561410 |                              325010 |               45 | Eligible                              |
      |    1003 | 37 PARK AVE            | LAKE RONKONKOMA |      11779 | NY        | Purchase        |                  320000 |                              320000 |               45 | Eligible                              |
      |    1004 | 3365 MORONEY DR        | RICHARDSON      |      75082 | TX        | Purchase        |                  395000 |                              395000 |               45 | Eligible                              |
      |    1005 | 9204 MARSH MOUNTAIN RD | PINSON          |      35126 | AL        | Purchase        |                  146027 |                              325010 |               45 | Eligible                              |
      |    1006 | 11931 BENTON LAKE RD   | BRISTOW         |      20136 | VA        | Purchase        |                  324260 |                              325010 |               45 | Eligible                              |
      |    1007 | 25264 WINDY BLUFF LN   | ARLINGTON       |      68002 | NE        | Purchase        |                  447246 |                              447246 |               45 | Eligible                              |
      |    1008 | 5901 COVINGTON CT      | MINNETONKA      |      55345 | MN        | Purchase        |                  550000 |                              550000 |               45 | Eligible                              |
      |    1009 | 23303 ANGELA CT        | SMITHSBURG      |      21783 | MD        | Purchase        |                  324260 |                              325010 |               45 | Not Eligible                          |
      |    1010 | 1318 PRINCETON ST      | ABILENE         |      79602 | TX        | Purchase        |                  324260 |                              325010 |               45 | Not Eligible                          |
      |    1011 | 33 OAK KNOLL RD        | RIDGEFIELD      |      06877 | CT        | Purchase        |                  324260 |                              325010 |               45 | Not Eligible                          |
 
   