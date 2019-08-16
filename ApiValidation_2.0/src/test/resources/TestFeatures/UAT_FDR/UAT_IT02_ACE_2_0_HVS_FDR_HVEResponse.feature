@B-128955 @AceAPi2.0_HVS_DATAElements_Validation @UAT_Iteration02
Feature: Ace API 2.0 Consume New Elements for HVE/FDR CCE from HVS Response

  Scenario Outline: Ace API 2.0 Consume New Elements for HVE/FDR CCE from HVS Response
    When User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-02-min.json"
      | addressLineText   | cityName   | postalCode   | stateCode   | loanPurposeType   | propertyValuationAmount   | borrowerPropertyPurchasePriceAmount   | ltvRatioPercent1   | LoanID_LPKey        | LoanID_LPUL         | LoanID_LPT | subscriberRequestCorrelationIdentifier | alternateAppraisalEligibilityDecision   |
      | <addressLineText> | <cityName> | <postalCode> | <stateCode> | <loanPurposeType> | <propertyValuationAmount> | <borrowerPropertyPurchasePriceAmount> | <ltvRatioPercent1> | __Run_ID__<transID> | __Run_ID__<transID> | <transID>  | __Run_ID__<transID>                    | <alternateAppraisalEligibilityDecision> |
    And User verifies the ACEAPI2.0 "HVEResponse" data contains the following values
      | predictedConditionScoreValue   | femaDisasterName   | appraisalAlternativeDisasterEligibilityDescription   |
      | <predictedConditionScoreValue> | <femaDisasterName> | <appraisalAlternativeDisasterEligibilityDescription> |

    Examples: 
      | transID | addressLineText      | cityName | postalCode | stateCode | loanPurposeType | propertyValuationAmount | borrowerPropertyPurchasePriceAmount | ltvRatioPercent1 | predictedConditionScoreValue | femaDisasterName | appraisalAlternativeDisasterEligibilityDescription |
      |    1001 | 11931 BENTON LAKE RD | BRISTOW  |      20136 | VA        | Purchase        |                  324260 |                              325010 |               45 | 0.002                         | DisasterFlorence | ACERestrict, PIIRestrict                    |
     # |    1002 | 1938 TINNIN RD       | GREELEY  |      37072 | CO        | Purchase        |                  324260 |                              325010 |               45 | 0.002                        | DisasterFlorence | ACERestrict, PIIRestrict                     |
 
 
 
 
