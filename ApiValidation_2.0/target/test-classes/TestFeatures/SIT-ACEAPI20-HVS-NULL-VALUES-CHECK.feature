@SIT-ACEAPI20-HVS-NULL-VALUES-CHECK @SIT-ACEAPI20 @Regression_SIT @B-127354_SIT
Feature: ACE API 2.0 HVS elements null check in GFS GET Response

  Scenario Outline: ACE API 2.0 HVS elements null check in GFS GET Response
    When User submits GFS POST request "ACEAPI20-POST-GFS-Full.json" with the following values
      | Client | LPKey                | <element1Name>  |
      | LPAv2  | __Run_ID__-<transID> | <element1Value> |
    Then User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-02-min.json"
      | LoanID_LPKey         |
      | __Run_ID__-<transID> |
    And User verifies the ACEAPI2.0 response values
      | alternateAppraisalEligibilityDecision   | alternateAppraisalDecisionStatusType   |
      | <alternateAppraisalEligibilityDecision> | <alternateAppraisalDecisionStatusType> |
    Then User retrieves the corresponding "HVEResponse" from ACEAPI2.0 Database
    And User retrieves the corresponding "AARequest" from ACEAPI2.0 Database
    Then User verifies "GFS GET response HVE" data is copied as "grandfathered HVE (HVE container-1)" data
    And User verifies "GFS GET response PV" data is copied as "original PV (PV container-1)" data
    And User verifies "HVE2" data in ACEAPI2.0 "AARequest"
    And User verifies "PV2" data in ACEAPI2.0 "AARequest"
    Then User retrieves the corresponding "AAResponse" from ACEAPI2.0 Database
    Then User retrieves the corresponding ACEAPI2.0 "GFSPostData" from GFS Database
    And User verifies GFS "GFSPostData" "Address" data matches "HVEResponse" "Address" data
    And User verifies GFS "GFSPostData" "HVE2" data matches "HVEResponse" "HVE" data
    And User verifies GFS "GFSPostData" "AA" data matches "AAResponse" "AA" data
    And User verifies GFS "GFSPostData" "HVE" data matches "AARequest" "HVE" data
    And User verifies GFS "GFSPostData" "PV" data matches "AARequest" "Subsequent PV" data

    Examples: 
      | transID | element1Name                                         | element1Value  | alternateAppraisalEligibilityDecision | alternateAppraisalDecisionStatusType |
      |    1001 | HVE2homeValueExplorerForecastStandardDeviationRate   | __NULL_VALUE__ | Not Eligible                          | Reuse                                |
      |    1002 | HVE2homeValueExplorerMaximumValueAmount              | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1003 | HVE2homeValueExplorerMidPointValueEstimateAmount     | __NULL_VALUE__ | Not Eligible                          | Reuse                                |
      |    1004 | HVE2homeValueExplorerMinimumValueAmount              | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1005 | HVE2propertyValuationEffectiveDateTime               | __NULL_VALUE__ | Eligible                              | Current                              |
      |    1006 | HVE2homeValueExplorerAssessmentDateTime              | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1007 | HVE2homeValueExplorerConfidenceLevelType             | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1008 | HVE2activeMLSListingIndicator                        | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1009 | HVE2appraisalCompletionAsIsIndicator                 | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1010 | HVE2appraisalExistenceIndicator                      | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1011 | HVE2armsLengthContractIndicator                      | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1012 | HVE2daysOnMarketIndicator                            | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1013 | HVE2distressedIndicator                              | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1014 | HVE2highestBestUseIndicator                          | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1015 | HVE2legalityIndicator                                | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1016 | HVE2missingDataIndicator                             | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1017 | HVE2multipleListingIndicator                         | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1018 | HVE2neighborhoodConformityIndicator                  | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1019 | HVE2physicalDeficiencyIndicator                      | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1020 | HVE2priceGrowthThresholdAmount                       | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1021 | HVE2propertyAgeCount                                 | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1022 | HVE2propertyConditionIndicator                       | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1023 | HVE2propertyLandUseIndicator                         | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1024 | HVE2propertyPurchaseLotSizeOneAcreIndicator          | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1025 | HVE2propertyRefinanceLotSizeTwoAcreIndicator         | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1026 | HVE2propertyUnitIndicator                            | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1027 | HVE2publicRecordExistenceIndicator                   | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1028 | HVE2qualityAgeIndicator                              | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1029 | HVE2qualityIndicator                                 | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1030 | HVE2structureBuildingMaterialQualityRatingType       | __NULL_VALUE__ | Eligible                              | Reuse                                |
      |    1031 | HVE2structureBuildingMaterialQualityRatingIdentifier | __NULL_VALUE__ | Eligible                              | Reuse                                |
