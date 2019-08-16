@SIT-IT06-ACEAPI-Request-Schema-Validation-LQA-05-Eligible-02 @SIT-IT05 @check-schema-all @B-110840 @SIT-ACEAPI20
Feature: ACEAPI2.0 Request Schema Validation - LQA - Eligible- set2

  Scenario Outline: ACEAPI2.0 Request Schema Validation - LQA - Eligible- set2
    When User submits ACEAPI2.0 request "ACEAPI20Request-<Client>-<TMPL_ID>.json" with below changes
      | <elementName>  |
      | <elementValue> |
    Then ACEAPI2.0 returns HTTP status code <returnCode>
    And User verifies the ACEAPI2.0 response values
      | alternateAppraisalEligibilityDecision   |
      | <alternateAppraisalEligibilityDecision> |

    Examples: 
      | Client | TMPL_ID | elementName                                    | elementValue   | returnCode | alternateAppraisalEligibilityDecision |
      | LQA    | 02-v2   | addressMatchLevelType                          | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | addressMatchLevelType                          | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | addressSourceType                              | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | addressSourceType                              | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | addressSourceType                              |                |        200 | Eligible                              |
      | LQA    | 02-v2   | addressUnitDesignatorType                      | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | addressUnitDesignatorType                      | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | addressUnitIdentifier                          | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | addressUnitIdentifier                          | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | addressUnitIdentifier                          |                |        200 | Eligible                              |
      | LQA    | 02-v2   | appraisalIdentifier                            | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | appraisalIdentifier                            | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | appraisalIdentifier                            |                |        200 | Eligible                              |
      | LQA    | 02-v2   | automatedUnderwritingSystemType                | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | automatedUnderwritingSystemType                | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | borrowerClassificationType                     | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | borrowerClassificationType                     | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | borrowerClassificationType                     |                |        200 | Eligible                              |
      | LQA    | 02-v2   | borrowerIdentifier                             | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | borrowerIdentifier                             | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | borrowerIdentifier                             |                |        200 | Eligible                              |
      | LQA    | 02-v2   | carrierRouteType                               | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | carrierRouteType                               | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | censusTractBaseIdentifier                      | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | censusTractBaseIdentifier                      | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | cityName                                       |                |        200 | Eligible                              |
      | LQA    | 02-v2   | constructionLoanIndicator                      | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | constructionLoanIndicator                      | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | constructionLoanIndicator                      |                |        200 | Eligible                              |
      | LQA    | 02-v2   | constructionLoanType                           | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | constructionLoanType                           | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | constructionMethodType                         | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | constructionMethodType                         | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | coreBasedStatisticalAreaCode                   | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | coreBasedStatisticalAreaCode                   | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | coreBasedStatisticalAreaCode                   |                |        200 | Eligible                              |
      | LQA    | 02-v2   | craCommunicationFailureType                    | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | craCommunicationFailureType                    | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | craCommunicationFailureType                    |                |        200 | Eligible                              |
      | LQA    | 02-v2   | derivationRiskClassType                        | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | derivationRiskClassType                        | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | derivedCalculationSourceType                   | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | derivedCalculationSourceType                   | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyAssessmentSourceType             | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyAssessmentSourceType             | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyAssessmentSourceType             |                |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyEstateType                       | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyEstateType                       | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyEstateType                       |                |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyIdentifier                       | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyIdentifier                       | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyIdentifier                       |                |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyValuationAmount                  | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyValuationAmount                  | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyValuationAmount                  |                |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyValuationEffectiveDateTime       | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyValuationEffectiveDateTime       | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyValuationEffectiveDateTime       |                |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyValuationMethodType              | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyValuationMethodType              | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyValuationMethodType              |                |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyValuationSequenceNumber          | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyValuationSequenceNumber          | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyValuationSequenceNumber          |                |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyValuationType                    | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyValuationType                    | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_O_propertyValuationType                    |                |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyAssessmentSourceType             | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyAssessmentSourceType             | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyAssessmentSourceType             |                |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyEstateType                       | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyEstateType                       | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyEstateType                       |                |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyIdentifier                       | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyIdentifier                       | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyIdentifier                       |                |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyValuationAmount                  | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyValuationAmount                  | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyValuationAmount                  |                |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyValuationEffectiveDateTime       | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyValuationEffectiveDateTime       | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyValuationEffectiveDateTime       |                |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyValuationMethodType              | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyValuationMethodType              | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyValuationMethodType              |                |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyValuationSequenceNumber          | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyValuationSequenceNumber          | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyValuationSequenceNumber          |                |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyValuationType                    | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyValuationType                    | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | EST_S_propertyValuationType                    |                |        200 | Eligible                              |
      | LQA    | 02-v2   | FIPSCountyThreeDigitCode                       | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FIPSCountyThreeDigitCode                       | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FIPSCountyThreeDigitCode                       |                |        200 | Eligible                              |
      | LQA    | 02-v2   | FIPSStateNumericCode                           | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FIPSStateNumericCode                           | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FIPSStateNumericCode                           |                |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyAssessmentSourceType                | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyAssessmentSourceType                | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyAssessmentSourceType                |                |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyEstateType                          | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyEstateType                          | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyEstateType                          |                |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyIdentifier                          | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyIdentifier                          | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyIdentifier                          |                |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyValuationAmount                     | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyValuationAmount                     | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyValuationEffectiveDateTime          | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyValuationEffectiveDateTime          | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyValuationEffectiveDateTime          |                |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyValuationMethodType                 | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyValuationMethodType                 | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyValuationMethodType                 |                |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyValuationSequenceNumber             | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyValuationSequenceNumber             | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyValuationSequenceNumber             |                |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyValuationType                       | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyValuationType                       | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyValuationType                       |                |        200 | Eligible                              |
      | LQA    | 02-v2   | grandfatheringIndicator                        | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | grandfatheringIndicator                        | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | grandfatheringIndicator                        |                |        200 | Eligible                              |
      | LQA    | 02-v2   | highwayContractIdentifier                      | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | highwayContractIdentifier                      | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | highwayContractIdentifier                      |                |        200 | Eligible                              |
      | LQA    | 02-v2   | IntentToOccupyType                             | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | IntentToOccupyType                             | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | IntentToOccupyType                             |                |        200 | Eligible                              |
      | LQA    | 02-v2   | investorCollateralProgramType                  | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | investorCollateralProgramType                  | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | latitudeNumber                                 | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | latitudeNumber                                 | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | latitudeNumber                                 |                |        200 | Eligible                              |
      | LQA    | 02-v2   | loanApplicationBaseLoanAmount                  | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanApplicationBaseLoanAmount                  | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanConformityType                             | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanConformityType                             | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | LoanID_HPGT                                    | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | LoanID_HPGT                                    | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | LoanID_HPGT                                    |                |        200 | Eligible                              |
      | LQA    | 02-v2   | LoanID_HPLPT                                   | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | LoanID_HPLPT                                   | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | LoanID_HPLPT                                   |                |        200 | Eligible                              |
      | LQA    | 02-v2   | LoanID_LPKey                                   | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | LoanID_LPKey                                   | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | LoanID_LPKey                                   |                |        200 | Eligible                              |
      | LQA    | 02-v2   | LoanID_LPT                                     | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | LoanID_LPT                                     | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | LoanID_LPT                                     |                |        200 | Eligible                              |
      | LQA    | 02-v2   | LoanID_LPUL                                    | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | LoanID_LPUL                                    | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | LoanID_LPUL                                    |                |        200 | Eligible                              |
      | LQA    | 02-v2   | LoanID_SLS                                     | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | LoanID_SLS                                     | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | LoanID_SLS                                     |                |        200 | Eligible                              |
      | LQA    | 02-v2   | loanOriginationSystemVersionIdentifier         | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanOriginationSystemVersionIdentifier         | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanOriginationSystemVersionIdentifier         |                |        200 | Eligible                              |
      | LQA    | 02-v2   | loanProcessingStageType                        | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanProcessingStageType                        | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanProcessingStageType                        |                |        200 | Eligible                              |
      | LQA    | 02-v2   | loanPropertyUsageType                          | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanPropertyUsageType                          | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanRefinanceCashOutDeterminationType          | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanRefinanceCashOutDeterminationType          | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanScopeType2                                 | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanScopeType2                                 | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanUnderwritingCaseIdentifier                 | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanUnderwritingCaseIdentifier                 | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanUnderwritingCaseIdentifier                 |                |        200 | Eligible                              |
      | LQA    | 02-v2   | loanUnderwritingDecisionDefaultProbabilityRate | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanUnderwritingDecisionDefaultProbabilityRate | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | longitudeNumber                                | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | longitudeNumber                                | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | longitudeNumber                                |                |        200 | Eligible                              |
      | LQA    | 02-v2   | ltvRatioPercent2                               | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | ltvRatioPercent2                               | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | ltvSourceType2                                 | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | ltvSourceType2                                 | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | messageCode                                    | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | messageCode                                    | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | messageCode                                    |                |        200 | Eligible                              |
      | LQA    | 02-v2   | messageText                                    | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | messageText                                    | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | messageText                                    |                |        200 | Eligible                              |
      | LQA    | 02-v2   | miAndFundingFeeFinancedAmount                  | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | miAndFundingFeeFinancedAmount                  | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | miAndFundingFeeFinancedAmount                  |                |        200 | Eligible                              |
      | LQA    | 02-v2   | miPremiumFinancedAmount                        | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | miPremiumFinancedAmount                        | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | miPremiumFinancedIndicator                     | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | miPremiumFinancedIndicator                     | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | noteAmount                                     | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | noteAmount                                     | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | PartyID_SELLER                                 | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | PartyID_SELLER                                 | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | PartyID_SELLER                                 |                |        200 | Eligible                              |
      | LQA    | 02-v2   | partyRoleIdentifier                            | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | partyRoleIdentifier                            | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | partyRoleIdentifier                            |                |        200 | Eligible                              |
      | LQA    | 02-v2   | partyRoleType                                  | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | partyRoleType                                  | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | partyRoleType                                  |                |        200 | Eligible                              |
      | LQA    | 02-v2   | postOfficeBoxIdentifier                        | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | postOfficeBoxIdentifier                        | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | postOfficeBoxIdentifier                        |                |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_addressLineText                       | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_addressLineText                       | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_addressLineText                       |                |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_addressSourceType                     | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_addressSourceType                     | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_addressSourceType                     |                |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_cityName                              | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_cityName                              | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_cityName                              |                |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_geocodingIndicator                    | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_geocodingIndicator                    | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_geocodingIndicator                    |                |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_postalCode                            | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_postalCode                            | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_postalCode                            |                |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_stateCode                             | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_stateCode                             | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_zipPlusFourCode                       | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_zipPlusFourCode                       | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | PreScrub_zipPlusFourCode                       |                |        200 | Eligible                              |
      | LQA    | 02-v2   | productDescription                             | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | productDescription                             | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | productDescription                             |                |        200 | Eligible                              |
      | LQA    | 02-v2   | productIdentifier                              | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | productIdentifier                              | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | productIdentifier                              |                |        200 | Eligible                              |
      | LQA    | 02-v2   | productType                                    | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | productType                                    | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | productType                                    |                |        200 | Eligible                              |
      | LQA    | 02-v2   | programType                                    | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | programType                                    | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | programType2                                   | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | programType2                                   | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | projectAttachmentType                          | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | projectAttachmentType                          | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | projectLegalStructureType                      | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | projectLegalStructureType                      | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyAssessmentSourceType                   | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyAssessmentSourceType                   | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyAssessmentSourceType                   |                |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyCategoryType                           | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyCategoryType                           | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyEstateType                             | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyEstateType                             | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyIdentifier                             | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyIdentifier                             | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyValuationEffectiveDateTime             | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyValuationEffectiveDateTime             | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyValuationEffectiveDateTime             |                |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyValuationSequenceNumber                | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyValuationSequenceNumber                | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyValuationSequenceNumber                |                |        200 | Eligible                              |
      | LQA    | 02-v2   | rdsCommunicationFailureType                    | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | rdsCommunicationFailureType                    | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | rdsCommunicationFailureType                    |                |        200 | Eligible                              |
      | LQA    | 02-v2   | refinanceProgramType                           | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | refinanceProgramType                           | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | refinanceProgramType2                          | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | refinanceProgramType2                          | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | ruralRouteIdentifier                           | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | ruralRouteIdentifier                           | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | ruralRouteIdentifier                           |                |        200 | Eligible                              |
      | LQA    | 02-v2   | sellerAccountIdentifier                        | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | sellerAccountIdentifier                        | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | sellerAccountIdentifier                        |                |        200 | Eligible                              |
      | LQA    | 02-v2   | sellerLoanIdentifier                           | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | sellerLoanIdentifier                           | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | sellerLoanIdentifier                           |                |        200 | Eligible                              |
      | LQA    | 02-v2   | serviceName                                    |                |        200 | Eligible                              |
      | LQA    | 02-v2   | serviceRequestOperationName                    |                |        200 | Eligible                              |
      | LQA    | 02-v2   | strategicOfferingCreditRiskType                | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | strategicOfferingCreditRiskType                | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | streetName                                     | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | streetName                                     | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | streetName                                     |                |        200 | Eligible                              |
      | LQA    | 02-v2   | streetPostDirectionalText                      | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | streetPostDirectionalText                      | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | streetPostDirectionalText                      |                |        200 | Eligible                              |
      | LQA    | 02-v2   | streetPreDirectionalText                       | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | streetPreDirectionalText                       | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | streetPreDirectionalText                       |                |        200 | Eligible                              |
      | LQA    | 02-v2   | streetPrimaryNumberText                        | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | streetPrimaryNumberText                        | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | streetPrimaryNumberText                        |                |        200 | Eligible                              |
      | LQA    | 02-v2   | streetSuffixText                               | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | streetSuffixText                               | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | streetSuffixText                               |                |        200 | Eligible                              |
      | LQA    | 02-v2   | subscriberRequestCorrelationIdentifier         |                |        200 | Eligible                              |
      | LQA    | 02-v2   | zipPlusFourCode                                | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | zipPlusFourCode                                | __NULL_VALUE__ |        200 | Eligible                              |
      | LQA    | 02-v2   | zipPlusFourCode                                |                |        200 | Eligible                              |
      | LQA    | 02-v2   | FR_propertyValuationAmount                     |                |        200 | Eligible                              |
      | LQA    | 02-v2   | loanApplicationBaseLoanAmount                  |                |        200 | Eligible                              |
      | LQA    | 02-v2   | ltvRatioPercent2                               |                |        200 | Eligible                              |
      | LQA    | 02-v2   | noteAmount                                     |                |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyIdentifier                             |                |        200 | Eligible                              |
      | LQA    | 02-v2   | strategicOfferingCreditRiskType                |                |        200 | Eligible                              |
      | LQA    | 02-v2   | HVEOptionType                                  | __NO_ELEMENT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | HVEOptionType                                  | __NULL_VALUE__ |        200 | Eligible                              |
