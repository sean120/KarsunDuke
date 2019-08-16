@B_128956-UAT @ACECHECKAPI_ITERation_02 @HVE/FDR_CCE_NEW_ELEMENTS_POSTED_ON_HVS_RESPONSE
Feature: AceCheckAPI Consume New Elements for HVE/FDR CCE from HVS Response

  Scenario Outline: AceCheckAPI Consume New Elements for HVE/FDR CCE from HVS Response
  

    When User submits ACE Check API (No APIGEE) request and gets response
      | partyRoleIdentifier   | loanPurposeType   | salesContractAmount   | propertyEstimatedValueAmount   | addressLineText   | cityName   | postalCode   | stateCode   |
      | <partyRoleIdentifier> | <loanPurposeType> | <salesContractAmount> | <propertyEstimatedValueAmount> | <addressLineText> | <cityName> | <postalCode> | <stateCode> |
    And User verifies the ACE Check API "HVEResponse" data contains the following values
      | predictedConditionScoreValue   | femaDisasterName   | appraisalAlternativeDisasterEligibilityDescription   |
      | <predictedConditionScoreValue> | <femaDisasterName> | <appraisalAlternativeDisasterEligibilityDescription> |

    Examples: 
      | partyRoleIdentifier | loanPurposeType | salesContractAmount | propertyEstimatedValueAmount | addressLineText      | cityName | postalCode | stateCode | predictedConditionScoreValue | femaDisasterName | appraisalAlternativeDisasterEligibilityDescription |
      |              000601 | Purchase        |              346585 |                              | 11931 BENTON LAKE RD | BRISTOW  |      20136 | VA        |                        0.002 | DisasterFlorence | ACERestrict, PIIRestrict                     |
      |              000601 | Purchase        |              346186 |                              | 1938 TINNIN RD       | GREELEY  |      37072 | CO        |                        0.002 | DisasterFlorence | ACERestrict, PIIRestrict                     |
