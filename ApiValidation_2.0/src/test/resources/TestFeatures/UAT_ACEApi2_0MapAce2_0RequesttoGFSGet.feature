@Iteration04 @ACE2_0UATREGRESSION @B-112494 @GFSGetRequest
Feature: ACE 2.0 GFS Get Request

  Scenario Outline: ACE API 2.0 GFS validation
    When User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-02-min.json"
      | addressLineText   | cityName   | postalCode   | stateCode   | loanPurposeType   | propertyValuationAmount   | borrowerPropertyPurchasePriceAmount   | ltvRatioPercent1   | LoanID_LPKey        | LoanID_LPUL         | LoanID_LPT | subscriberRequestCorrelationIdentifier | alternateAppraisalEligibilityDecision   |
      | <addressLineText> | <cityName> | <postalCode> | <stateCode> | <loanPurposeType> | <propertyValuationAmount> | <borrowerPropertyPurchasePriceAmount> | <ltvRatioPercent1> | __Run_ID__<transID> | __Run_ID__<transID> | <transID>  | __Run_ID__<transID>                    | <alternateAppraisalEligibilityDecision> |
    Then User verifies the ACEAPI2.0 response values
      | alternateAppraisalEligibilityDecision   |
      | <alternateAppraisalEligibilityDecision> |

    Then User retrieves the corresponding ACEAPI2.0 "GFSPostData" from GFS Database
    And User verifies GFS "GFSPostData" "Address" data matches "HVEResponse" "Address" data
    And User verifies GFS "GFSPostData" "HVE2" data matches "HVEResponse" "HVE" data
    And User verifies GFS "GFSPostData" "AA" data matches "AAResponse" "AA" data
    And User verifies GFS "GFSPostData" "HVE" data matches "AARequest" "HVE" data
    And User verifies GFS "GFSPostData" "PV" data matches "AARequest" "Subsequent PV" data

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
 





  #Scenario: User sends a valid ace api 2.0 request and the data should be mapped correctly to a GFS get request
    #Given User sends "UAT" ACE API 2.0 request  "017_ULADRequest1009CircleDrive" and validates response code "200"
    #Then User retreives ace "grandFatheringAceRequest" data that matches the "1009 CIRCLE DR" address in the database
#
  #Scenario Outline: User sends a valid ace api 2.0 request and the data should be mapped correctly to a GFS get request
    #Then Validate that the grandfathered element "<GFSElement>" equals "<GFSValue>" in the "grandFatheringAceRequest"  Database collection
#
    #Examples: 
      #| GFSElement            | GFSValue                                        |
      #| sourceApplicationName | LPAv2                                           |
      #| loanIdentifier1       | DIMA                                            |
      #| loanIdentifierType1   | LoanProspectorKey                               |
      #| loanIdentifier2       |                                           57524 |
      #| loanIdentifierType2   | LoanProspectorUniqueLoan                        |
      #| loanIdentifier3       |                                        50109018 |
      #| loanIdentifierType3   | LoanProspectorTransaction                       |
      #| loanIdentifier4       |                                       300512420 |
      #| loanIdentifierType4   | ExtendedHewlettPackardLoanProspectorTransaction |
      #| loanIdentifier5       |                                          512420 |
      #| loanIdentifierType5   | HewlettPackardLoanProspectorTransaction         |
      #| addressLineText       | 1009 CIRCLE DR                                  |
      #| postalCode            |                                           50002 |
