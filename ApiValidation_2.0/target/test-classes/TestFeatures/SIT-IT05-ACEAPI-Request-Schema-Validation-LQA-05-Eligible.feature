@SIT-IT06-ACEAPI-Request-Schema-Validation-LQA-05-Eligible @SIT-IT05 @check-schema-all @B-110840 @SIT-ACEAPI20
Feature: ACEAPI2.0 Request Schema Validation - LQA - Eligible

  Scenario Outline: ACEAPI2.0 Request Schema Validation - LQA - Eligible
    When User submits ACEAPI2.0 request "ACEAPI20Request-<Client>-<TMPL_ID>.json" with below changes
      | <elementName>  |
      | <elementValue> |
    Then ACEAPI2.0 returns HTTP status code <returnCode>
    And User verifies the ACEAPI2.0 response values
      | alternateAppraisalEligibilityDecision   |
      | <alternateAppraisalEligibilityDecision> |

    Examples: 
      | Client | TMPL_ID | elementName                                  | elementValue     | returnCode | alternateAppraisalEligibilityDecision |
      | LQA    | 02-v2   | address_ArrayElementObject_PreScrub          | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | address_ArrayElementObject_PreScrub          | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | appraisal_Object                             | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | appraisal_Object                             | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | appraisal_Object                             | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | creditRiskDerivation_ArrayElementObject2     | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | creditRiskDerivation_ArrayElementObject2     | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | creditRiskDerivation_ArrayElementObject2     | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | insurancePolicyDetails_Object                | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | insurancePolicyDetails_Object                | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | insurancePolicyDetails_Object                | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | loanAcquisitionDetails_Object                | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | loanAcquisitionDetails_Object                | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | loanAcquisition_Object                       | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanCollateralRiskAssessmentDetails2         | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanCollateralRiskAssessmentDetails2         | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | loanCollateralRiskAssessment_Object2         | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanCollateralRiskAssessment_Object2         | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | loanCollateralRiskAssessment_Object2         | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | loanConversionRuleDetails_Object             | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | loanConversionRuleDetails_Object             | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | loanConversionRule_Object                    | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_HPGT   | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_HPGT   | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_HPGT   | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_HPLPT  | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_HPLPT  | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_HPLPT  | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_LPKey  | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_LPKey  | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_LPKey  | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_LPT    | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_LPT    | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_LPT    | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_LPUL   | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_LPUL   | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_LPUL   | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_SLS    | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_SLS    | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | loanMI_Object                                | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanMI_Object                                | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | loanMI_Object                                | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | loanOriginationDetails_Object                | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanOriginationDetails_Object                | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | loanOriginationDetails_Object                | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | loanOrigination_Object                       | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanOrigination_Object                       | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | loanOrigination_Object                       | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | loanUnderwritingDecision_Object              | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | loanUnderwritingDecision_Object              | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | loanUnderwritingDecision_Object              | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | partyRole_ArrayElementObject                 | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | partyRole_Array                              | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | partyRoles_Object                            | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | partyRoles_Object                            | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | productDefinition_Object                     | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | productDefinition_Object                     | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | productDefinition_Object                     | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | productDetails_Object                        | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | productDetails_Object                        | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | productDetails_Object                        | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | program_Object2                              | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | program_Object2                              | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | program_Object2                              | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | program_Object                               | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | program_Object                               | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | program_Object                               | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | programs_ArrayElementObject2                 | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | programs_ArrayElementObject2                 | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | programs_ArrayElementObject2                 | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | programs_ArrayElementObject                  | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | programs_ArrayElementObject                  | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | programs_ArrayElementObject                  | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | programs_Array                               | __EMPTY_ARRAY__  |        200 | Eligible                              |
      | LQA    | 02-v2   | programs_Array                               | __NO_ARRAY__     |        200 | Eligible                              |
      | LQA    | 02-v2   | programs_Array                               | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyRights_Object                        | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyRights_Object                        | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyRights_Object                        | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyTitles_Object                        | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyTitles_Object                        | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyTitles_Object                        | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyValuation_ArrayElementObject_EST_O   | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyValuation_ArrayElementObject_EST_O   | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyValuation_ArrayElementObject_EST_O   | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyValuation_ArrayElementObject_EST_S   | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyValuation_ArrayElementObject_EST_S   | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyValuation_ArrayElementObject_EST_S   | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyValuation_ArrayElementObject_FR      | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyValuation_ArrayElementObject_FR      | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | property_Object                              | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | property_Object                              | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | property_Object                              | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | realEstateProject_Object                     | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | realEstateProject_Object                     | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | realEstateProject_Object                     | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | singleFamilyLoanBorrower_ArrayElementObject2 | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | singleFamilyLoanBorrower_ArrayElementObject2 | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | singleFamilyLoanBorrower_ArrayElementObject2 | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | singleFamilyLoanBorrower_ArrayElementObject  | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | singleFamilyLoanBorrower_ArrayElementObject  | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | singleFamilyLoanBorrower_ArrayElementObject  | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | singleFamilyLoanBorrower_Array               | __EMPTY_ARRAY__  |        200 | Eligible                              |
      | LQA    | 02-v2   | singleFamilyLoanBorrower_Array               | __NO_ARRAY__     |        200 | Eligible                              |
      | LQA    | 02-v2   | singleFamilyLoanBorrower_Array               | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | structureDetails_Object                      | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | structureDetails_Object                      | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | structureDetails_Object                      | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | structure_Object                             | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | structure_Object                             | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | structure_Object                             | __NULL_VALUE__   |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyValuationAssessment_Object           | __NO_OBJECT__    |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyValuationAssessment_Object           | __EMPTY_OBJECT__ |        200 | Eligible                              |
      | LQA    | 02-v2   | propertyValuationAssessment_Object           | __NULL_VALUE__   |        200 | Eligible                              |
