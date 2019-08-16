@Iteration4GFS @B-100759
Feature: ACE API GFS: Integrate with GFS and Initiate POST Call, B-100759

  @gfs
  Scenario Outline: As an ACE API user I want to integrate with Grandfathering service so that I can initiate POST call successfully
    Given Our request use "<user_name>" and "<password>" combinations to get ACE API Access Token at "<accesstoken>" path
    And APIGEE Developer Portal For ACE API and a GFS "<RequestFile>"
    When Sending gfs request to ACE API with the access token
    Then Check Mongo DB for GFS Request
    Then Check GFS "<response_status>" be required
    Then Check GFS Request
      | xpath                                                                                                                                                           | value   |
      | $.grandFatheringData.loanIdentifications                                                                                                                        |         |
      | $.grandFatheringData.loanIdentifications.loanIdentification                                                                                                     |         |
      | $.grandFatheringData.loanIdentifications.loanIdentification[0].loanIdentifier                                                                                   | String  |
      | $.grandFatheringData.loanIdentifications.loanIdentification[0].loanIdentifierType                                                                               | String  |
      | $.grandFatheringData.parties                                                                                                                                    |         |
      | $.grandFatheringData.parties.party                                                                                                                              |         |
      | $.grandFatheringData.parties.party[0].partyIdentifier                                                                                                           | String  |
      | $.grandFatheringData.parties.party[0].partyRoleType                                                                                                             | String  |
      | $.grandFatheringData.address                                                                                                                                    |         |
      | $.grandFatheringData.address.addressLineText                                                                                                                    | String  |
      #| $.grandFatheringData.address.addressUnitDesignatorType                                                                                                          |       |
      #| $.grandFatheringData.address.addressUnitIdentifier                                                                                                              |       |
      #| $.grandFatheringData.address.addressSourceType                                                                                                                  |       |
      #| $.grandFatheringData.address.addressMatchLevelType                                                                                                              |       |
      | $.grandFatheringData.address.cityName                                                                                                                           | String  |
      | $.grandFatheringData.address.postalCode                                                                                                                         | String  |
      | $.grandFatheringData.address.stateCode                                                                                                                          | String  |
      #| $.grandFatheringData.address.streetName                                                                                                                         |       |
      #| $.grandFatheringData.address.streetPrimaryNumberText                                                                                                            |       |
      #| $.grandFatheringData.address.streetSuffixText                                                                                                                   |       |
      | $.grandFatheringData.address.zipPlusFourCode                                                                                                                    | String  |
      | $.grandFatheringData.alternateAppraisalDecisionData                                                                                                             | String  |
      | $.grandFatheringData.alternateAppraisalDecisionData.alternateAppraisalDecisionStatusType                                                                        | String  |
      | $.grandFatheringData.alternateAppraisalDecisionData.alternateAppraisalEligibilityDecision                                                                       | String  |
      | $.grandFatheringData.alternateAppraisalDecisionData.alternateAppraisalDecisionEffectiveDatetime                                                                 | String  |
      | $.grandFatheringData.alternateAppraisalDecisionData.alternateAppraisalDecisionExpirationDatetime                                                                | String  |
      #| $.grandFatheringData.alternateAppraisalDecisionData.maximumAuthorizedLoanAmount                                                                                 |       |
      #| $.grandFatheringData.alternateAppraisalDecisionData.estimatedPropertyValueAmount                                                                                |       |
      #| $.grandFatheringData.alternateAppraisalDecisionData.salesContractAmount                                                                                         |       |
      #| $.grandFatheringData.PropertyValuationData                                                                                                                      |       |
      #| $.grandFatheringData.PropertyValuationData.PropertyValuationAmount                                                                                              |       |
      #| $.grandFatheringData.PropertyValuationData.PropertyValuationMethodType                                                                                          |       |
      #| $.grandFatheringData.PropertyValuationData.PropertyValuationType                                                                                                |       |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData                                                                                       |         |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerMidPointValueEstimateAmount   | Numeric |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerMaximumValueAmount            | Numeric |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerMinimumValueAmount            | Numeric |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerForecastStandardDeviationRate | Numeric |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerConfidenceLevelType           | String  |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.propertyValuationEffectiveDateTime             | String  |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerAssessmentDateTime            | String  |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.structureBuildingMaterialQualityRatingType          | String  |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.structureBuildingMaterialQualityRatingIdentifier    | String  |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.appraisalExistenceIndicator                         | Boolean |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.priceGrowthThresholdAmount                          | Numeric |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.propertyConditionIndicator                          | Boolean |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.legalityIndicator                                   | Boolean |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.qualityIndicator                                    | Boolean |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.physicalDeficiencyIndicator                         | Boolean |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.highestBestUseIndicator                             | Boolean |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.appraisalCompletionAsIsIndicator                    | Boolean |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.neighborhoodConformityIndicator                     | Boolean |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.armsLengthContractIndicator                         | Boolean |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.qualityAgeIndicator                                 | Boolean |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.daysOnMarketIndicator                               | Boolean |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.distressedIndicator                                 | Boolean |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.propertyAgeCount                                    | Numeric |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.publicRecordExistenceIndicator                      | Boolean |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.missingDataIndicator                                | Boolean |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.activeMLSListingIndicator                           | Boolean |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.multipleListingIndicator                            | Boolean |

    Examples:
      | user_name             | password             | RequestFile                       | accesstoken    | response_status |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | ACEAPIPurchaseAceEligible.json    | $.access_token |             200 |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | RefinanceEligibleTX.json          | $.access_token |             200 |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | ACEAPIPurchaseAceNotEligible.json | $.access_token |             200 |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | AceNotEligibleRefinance.json      | $.access_token |             200 |

  Scenario Outline: As API User, I want to check HVE Response after request with GFS
    Given Our request use "<user_name>" and "<password>" combinations to get ACE API Access Token at "<accesstoken>" path
    And APIGEE Developer Portal For ACE API and a GFS "<RequestFile>"
    When Sending gfs request to ACE API with the access token
    Then Check Mongo DB for HVE GFS response
    Then HVE GFS Response Save code in xml file "<responsexmlfile>"
    Then HVE GFS Response, Verify the value for below xpath elements in "<responsexmlfile>"
      | xpath                                                                                                                                                             | value   |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/AddressLineText                                                                                 | String  |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/CityName                                                                                        | String  |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/FIPSStateAlphaCode                                                                              | String  |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/PostalCode                                                                                      | String  |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/ActiveMLSListingIndicator              | Boolean |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/ARMsLengthContractIndicator            | Boolean |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/AppraisalCompletionAsIsIndicator       | Boolean |
      | //HVSResponse/HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PropertyConditionIndicator | Boolean |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/DaysOnMarketIndicator                  | Boolean |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/DistressedIndicator                    | Boolean |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/LegalityIndicator                      | Boolean |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/MissingDataIndicator                   | Boolean |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/MultipleListingIndicator               | Boolean |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/NeighborhoodConformityIndicator        | Boolean |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PhysicalDeficiencyIndicator            | Boolean |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PropertyAgeCount                       | Numeric |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PublicRecordExistenceIndicator         | Boolean |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/QualityAgeIndicator                    | Boolean |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/QualityIndicator                       | Boolean |

    Examples:
      | user_name             | password             | RequestFile                       | accesstoken    | responsexmlfile    |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | ACEAPIPurchaseAceEligible.json    | $.access_token | hveapiresponse.xml |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | ACEAPIPurchaseAceNotEligible.json | $.access_token | hveapiresponse.xml |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | RefinanceEligibleTX.json          | $.access_token | hveapiresponse.xml |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | AceNotEligibleRefinance.json      | $.access_token | hveapiresponse.xml |

  Scenario Outline: Send the ACE decision and associated message to freddiemac approved lender
    Given Our request use "<user_name>" and "<password>" combinations to get ACE API Access Token at "<accesstoken>" path
    And APIGEE Developer Portal For ACE API and a GFS "<RequestFile>"
    When Sending gfs request to ACE API with the access token
    Then Check MongoDB for EDS GFS Request
    Then EDS GFS Request Save code in xml file "<edsrequestfile>"
    Then EDS Request, Verify the value for below xpath elements in "<edsrequestfile>"
      | xpath                                                                                                                                                                                                                                                                      | value   |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecisionResultContainer/BusinessDecisionResult/BusinessDecisionResultDateTime                                                                          | String  |
      #| //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyValuationContainer/PropertyValuation/PropertyValuationAmount                                                                                       | Numeric |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyValuationContainer/PropertyValuation/PropertyValuationMethodType                                                                                   | String  |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyValuationContainer/PropertyValuation/PropertyValuationType                                                                                         | String  |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyValuationAssessment/HomeValueExplorerMidPointValueEstimateAmount                                               | Numeric |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyValuationAssessment/HomeValueExplorerMaximumValueAmount                                                        | Numeric |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyValuationAssessment/HomeValueExplorerMinimumValueAmount                                                        | Numeric |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyValuationAssessment/HomeValueExplorerForecastStandardDeviationRate                                             | Numeric |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyValuationAssessment/HomeValueExplorerConfidenceLevelType                                                       | String  |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyValuationAssessment/PropertyValuationEffectiveDateTime                                                         | String  |
      #| //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyValuationAssessment/HomeValueExplorerAssessmentDateTime                                                        | String  |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/StructureContainer/PropertyStructureAnalyses/PropertyStructureAnalysisContainer/PropertyStructureAnalysisDerivation/StructureBuildingMaterialQualityRatingIdentifier          | String  |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecisionResultContainer/BusinessDecisionResultDetails/BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult/AppraisalExistenceIndicator | Boolean |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecisionResultContainer/BusinessDecisionResultDetails/BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult/PriceGrowthThresholdAmount  | Numeric |

    Examples:
      | user_name             | password             | accesstoken    | RequestFile                       | edsrequestfile    |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | ACEAPIPurchaseAceEligible.json    | edsapirequest.xml |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | RefinanceEligibleTX.json          | edsapirequest.xml |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | ACEAPIPurchaseAceNotEligible.json | edsapirequest.xml |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | AceNotEligibleRefinance.json      | edsapirequest.xml |

  @compareGFSEDSHVE
  Scenario Outline: As an ACE API user I want to check if GFS values are same with HVE and EDS values
    Given Our request use "<user_name>" and "<password>" combinations to get ACE API Access Token at "<accesstoken>" path
    And APIGEE Developer Portal For ACE API and a GFS "<RequestFile>"
    When Sending gfs request to ACE API with the access token
    Then Check Mongo DB for GFS Request
    Then Check Mongo DB for HVE GFS response
    Then HVE GFS Response Save code in xml file "<hveresponsexmlfile>"
    Then Check MongoDB for EDS GFS Request
    Then EDS GFS Request Save code in xml file "<edsrequestfile>"
    Then Compare GFS and ACE Request values
    Then Compare GFS and HVE file "<hveresponsexmlfile>" values
      | gfs                                                                                                                                          | hve                                                                                                                                                               |
      | $.grandFatheringData.address.addressLineText                                                                                                 | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/AddressLineText                                                                                 |
      | $.grandFatheringData.address.cityName                                                                                                        | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/CityName                                                                                        |
      | $.grandFatheringData.address.postalCode                                                                                                      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/PostalCode                                                                              |
      | $.grandFatheringData.address.stateCode                                                                                                       | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/FIPSStateAlphaCode                                                                                      |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.propertyConditionIndicator       | //HVSResponse/HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PropertyConditionIndicator |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.legalityIndicator                | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/LegalityIndicator                      |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.qualityIndicator                 | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/QualityIndicator                       |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.physicalDeficiencyIndicator      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PhysicalDeficiencyIndicator            |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.appraisalCompletionAsIsIndicator | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/AppraisalCompletionAsIsIndicator       |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.neighborhoodConformityIndicator  | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/NeighborhoodConformityIndicator        |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.armsLengthContractIndicator      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/ARMsLengthContractIndicator            |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.qualityAgeIndicator              | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/QualityAgeIndicator                    |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.daysOnMarketIndicator            | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/DaysOnMarketIndicator                  |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.distressedIndicator              | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/DistressedIndicator                    |
      #10.0 and 10| $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.propertyAgeCount                 | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PropertyAgeCount                       |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.publicRecordExistenceIndicator   | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PublicRecordExistenceIndicator         |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.missingDataIndicator             | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/MissingDataIndicator                   |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.activeMLSListingIndicator        | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/ActiveMLSListingIndicator              |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.multipleListingIndicator         | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/MultipleListingIndicator               |
    Then Compare GFS and EDS file "<edsrequestfile>" values
      | gfs                                                                                                                                                             | eds                                                                                                                                                                                                                                                                        |
      | $.grandFatheringData.alternateAppraisalDecisionData.alternateAppraisalDecisionEffectiveDatetime                                                                 | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecisionResultContainer/BusinessDecisionResult/BusinessDecisionResultDateTime                                                                          |
      #decimal problem| $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerMidPointValueEstimateAmount   | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyValuationAssessment/HomeValueExplorerMidPointValueEstimateAmount                                               |
      #decimal problem| $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerMaximumValueAmount            | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyValuationAssessment/HomeValueExplorerMaximumValueAmount                                                        |
      #decimal problem| $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerMinimumValueAmount            | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyValuationAssessment/HomeValueExplorerMinimumValueAmount                                                        |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerForecastStandardDeviationRate | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyValuationAssessment/HomeValueExplorerForecastStandardDeviationRate                                             |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.homeValueExplorerConfidenceLevelType           | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyValuationAssessment/HomeValueExplorerConfidenceLevelType                                                       |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyAppraiserValuationContainer.propertyValuationEffectiveDateTime             | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyValuationAssessment/PropertyValuationEffectiveDateTime                                                         |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.structureBuildingMaterialQualityRatingIdentifier    | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/StructureContainer/PropertyStructureAnalyses/PropertyStructureAnalysisContainer/PropertyStructureAnalysisDerivation/StructureBuildingMaterialQualityRatingIdentifier          |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.appraisalExistenceIndicator                         | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecisionResultContainer/BusinessDecisionResultDetails/BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult/AppraisalExistenceIndicator |
      #decimal problem| $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.priceGrowthThresholdAmount                          | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecisionResultContainer/BusinessDecisionResultDetails/BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult/PriceGrowthThresholdAmount  |
      | $.grandFatheringData.homeValueExplorerDataContainer.homeValueExplorerData[0].propertyInspectionWaiverResult.highestBestUseIndicator                             | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecisionResultContainer/BusinessDecisionResultDetails/BusinessDecisionResultDetailContainer/PropertyInspectionWaiverResult/HighestBestUseIndicator     |
		
    Examples: 
      | user_name             | password             | RequestFile                       | accesstoken    | hveresponsexmlfile | edsrequestfile    |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | ACEAPIPurchaseAceEligible.json    | $.access_token | hveapiresponse.xml | edsapirequest.xml |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | ACEAPIPurchaseAceNotEligible.json | $.access_token | hveapiresponse.xml | edsapirequest.xml |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | RefinanceEligibleTX.json          | $.access_token | hveapiresponse.xml | edsapirequest.xml |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | AceNotEligibleRefinance.json      | $.access_token | hveapiresponse.xml | edsapirequest.xml |

  Scenario Outline: As an ACE API user I want to check if GFS  will not be present at MongoDB when AWP0004 is present
    Given Our request use "<user_name>" and "<password>" combinations to get ACE API Access Token at "<accesstoken>" path
    And APIGEE Developer Portal For ACE API and a GFS "<RequestFile>"
    When Sending gfs request to ACE API with the access token
    Then Check Mongo DB for not having GFS Request

    Examples:  
      | user_name             | password             | RequestFile                | accesstoken    |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | NotApplicable_AWP0004.json | $.access_token |
