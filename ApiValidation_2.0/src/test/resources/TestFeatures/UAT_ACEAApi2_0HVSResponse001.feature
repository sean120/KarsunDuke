@Iteration03 @UATREGRESSION @B-112490 @HVSResponse
Feature: 001 HVS response validation

  Scenario: User sends a valid ace api 2.0 request and retrieves EDS request from the database
    Given User sends "UAT" ACE API 2.0 request  "012_LPARefinanceAceEligible_HVSReq_Umair" and validates response code "200"
    Then User retrieves "REQUEST" for "EDS" from UAT Mongo DB

  Scenario Outline: User validates HVS element values and compares them to the expected LPA values
    Then Validate "REQUEST" "EDS" element "<elementName1>" with value "<elementValue1>" from Mongo

    Examples: 
      | elementName1                                     | elementValue1 |
      | HVSCommunicationFailureDerivation                | LoanProspectorTransaction       |
      | ActiveMLSListingIndicator                        | false       |
      | AppraisalCompletionAsIsIndicator                 | true       |
      | AppraisalExistenceIndicator                      | true       |
      | ARMsLengthContractIndicator                      | true       |
      | DaysOnMarketIndicator                            | true       |
      | DistressedIndicator                              | true       |
      | HighestBestUseIndicator                          | true       |
      | LegalityIndicator                                | true       |
      | MissingDataIndicator                             | false       |
      | MultipleListingIndicator                         | true       |
      | NeighborhoodConformityIndicator                  | true       |
      | PhysicalDeficiencyIndicator                      | true       |
      | PriceGrowthThresholdAmount                       | 227228       |
      | PropertyAgeCount                                 | 20       |
      | PropertyConditionIndicator                       | true       |
      | PublicRecordExistenceIndicator                   | true        |
#      | StructureBuildingMaterialQualityRatingType       | NoValue       |
#      | StructureBuildingMaterialQualityRatingIdentifier | NoValue       |
      | QualityAgeIndicator                              | true       |
      | QualityIndicator                                 | true       |
      | HomeValueExplorerAssessmenntDateTime             | NoValue       |
      | HomeValueExplorerConfidenceLevelType             | H       |
      | HomeValueExplorerForecastStandardDeviationRate   | 0.07       |
      | HomeValueExplorerMaximumValueAmount              | 312585       |
      | HomeValueExplorerMidPointValueEstimateAmount     | 292620       |
      | HomeValueExplorerMinimumValueAmount              | 273931       |
      | PropertyValuationEffectiveDateTime               | 2019-03-08T08:30:52.595-05:00       |
#      | PropertyLandUseIndicator                         | NoValue       |
#      | PropertyPurchaseLotSizeOneAcreIndicator          | NoValue       |
#      | PropertyRefinanceLotSizeTwoAcreIndicator         | NoValue       |
#      | PropertyUnitIndicator                            | NoValue       |