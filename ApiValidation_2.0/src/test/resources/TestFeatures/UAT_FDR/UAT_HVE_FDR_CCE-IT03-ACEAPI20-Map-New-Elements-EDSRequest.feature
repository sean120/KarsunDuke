@UAT-HVE_FDR_CCE-IT03-ACEAPI20-Map-New-Elements-EDSRequest  @B-128821-UAT

Feature: ACE API 2.0 Map New Elements for HVE/FDR CCE to EDS Request

  Background: Clear previously posted data from GFS database
    Given Remove "GFS" MongoDB documents with the following values
      | sourceApplicationName | addressLineText    | PartyID_SELLER |
      | ACEAPI                | 2140 DEMETRIUS WAY |         000601 |

  Scenario Outline: ACE API 2.0 Map New Elements for HVE/FDR CCE from HVS Response to EDS Request - LQA
    When User submits ACEAPI2.0 request "ACEAPI20Request-LQA-02-min.json"
      | addressLineText   | cityName   | postalCode   | stateCode   | loanPurposeType   | propertyValuationAmount   | borrowerPropertyPurchasePriceAmount   | ltvRatioPercent1   | LoanID_LQA          | LoanID_SLS          | subscriberRequestCorrelationIdentifier |
      | <addressLineText> | <cityName> | <postalCode> | <stateCode> | <loanPurposeType> | <propertyValuationAmount> | <borrowerPropertyPurchasePriceAmount> | <ltvRatioPercent1> | __Run_ID__<transID> | __Run_ID__<transID> | __Run_ID__<transID>                    |
    Then User verifies the following data elements from ACEAPI2.0 "HVEResponse" data are mapped to "AARequest" "HVE2"
      | predictedConditionScoreValue                       |
      | femaDisasterName                                   |
      | appraisalAlternativeDisasterEligibilityDescription |
    And User verifies the following data elements from ACEAPI2.0 "AARequest" "HVE2" data are mapped to "AARequest" "HVE1"
      | predictedConditionScoreValue                       |
      | femaDisasterName                                   |
      | appraisalAlternativeDisasterEligibilityDescription |
      | propertyValuationEffectiveDateTime                 |
      | homeValueExplorerAssessmentDateTime                |

    Examples: 
      | transID | addressLineText      | cityName | postalCode | stateCode | loanPurposeType | propertyValuationAmount | borrowerPropertyPurchasePriceAmount | ltvRatioPercent1 |
      |    1101 | 11931 BENTON LAKE RD | BRISTOW  |      20136 | VA        | Purchase        |                  384260 |                              385010 |               45 |
      |    1102 | 1938 TINNIN RD       | GREELEY  |      37072 | CO        | Purchase        |                  324260 |                              325010 |               45 |

  Scenario Outline: ACE API 2.0 Map New Elements for HVE/FDR CCE from HVS Response and GFS GET Response to EDS Request - ULAD
    When User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-02-min.json"
      | addressLineText   | cityName   | postalCode   | stateCode   | loanPurposeType   | propertyValuationAmount   | borrowerPropertyPurchasePriceAmount   | ltvRatioPercent1   | LoanID_LPKey        | LoanID_LPUL         | LoanID_LPT  | subscriberRequestCorrelationIdentifier |
      | <addressLineText> | <cityName> | <postalCode> | <stateCode> | <loanPurposeType> | <propertyValuationAmount> | <borrowerPropertyPurchasePriceAmount> | <ltvRatioPercent1> | __Run_ID__<transID> | __Run_ID__<transID> | <transID>_1 | __Run_ID__<transID>                    |
    Then User verifies the following data elements from ACEAPI2.0 "HVEResponse" data are mapped to "AARequest" "HVE2"
      | predictedConditionScoreValue                       |
      | femaDisasterName                                   |
      | appraisalAlternativeDisasterEligibilityDescription |
    And User verifies the following data elements from ACEAPI2.0 "AARequest" "HVE2" data are mapped to "AARequest" "HVE1"
      | predictedConditionScoreValue                       |
      | femaDisasterName                                   |
      | appraisalAlternativeDisasterEligibilityDescription |
      | propertyValuationEffectiveDateTime                 |
      | homeValueExplorerAssessmentDateTime                |
    When User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-02-min.json"
      | addressLineText   | cityName   | postalCode   | stateCode   | loanPurposeType   | propertyValuationAmount   | borrowerPropertyPurchasePriceAmount   | ltvRatioPercent1   | LoanID_LPKey        | LoanID_LPUL         | LoanID_LPT  | subscriberRequestCorrelationIdentifier |
      | <addressLineText> | <cityName> | <postalCode> | <stateCode> | <loanPurposeType> | <propertyValuationAmount> | <borrowerPropertyPurchasePriceAmount> | <ltvRatioPercent1> | __Run_ID__<transID> | __Run_ID__<transID> | <transID>_2 | __Run_ID__<transID>                    |
    Then User verifies the following data elements from ACEAPI2.0 "HVEResponse" data are mapped to "AARequest" "HVE2"
      | predictedConditionScoreValue                       |
      | femaDisasterName                                   |
      | appraisalAlternativeDisasterEligibilityDescription |
    And User verifies the following data elements from ACEAPI2.0 "GFSGetResponse" "HVE" data are mapped to "AARequest" "HVE1"
      | predictedConditionScoreValue                       |
      | femaDisasterName                                   |
      | appraisalAlternativeDisasterEligibilityDescription |
      | propertyValuationEffectiveDateTime                 |
      | homeValueExplorerAssessmentDateTime                |

    Examples: 
      | transID | addressLineText    | cityName  | postalCode | stateCode | loanPurposeType | propertyValuationAmount | borrowerPropertyPurchasePriceAmount | ltvRatioPercent1 |
      |    2001 | 2140 DEMETRIUS WAY | ROSEVILLE |      95661 | CA        | Purchase        |                  626420 |                              625010 |               45 |
