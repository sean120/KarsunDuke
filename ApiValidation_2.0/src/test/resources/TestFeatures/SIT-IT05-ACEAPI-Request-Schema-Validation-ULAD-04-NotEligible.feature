@SIT-IT06-ACEAPI-Request-Schema-Validation-ULAD-04-NotEligible  @SIT-IT05 @check-schema-all @B-110840 @SIT-ACEAPI20
Feature: ACEAPI2.0 Request Schema Validation - ULAD- NotEligible

Scenario Outline: ACEAPI2.0 Request Schema Validation - ULAD- NotEligible
	When User submits ACEAPI2.0 request "ACEAPI20Request-<Client>-<TMPL_ID>.json" with below changes
	| <elementName>	 |
	| <elementValue> |

	Then ACEAPI2.0 returns HTTP status code <returnCode>
	And User verifies the ACEAPI2.0 response values
	| alternateAppraisalEligibilityDecision 	|
	| <alternateAppraisalEligibilityDecision> 	|
		
Examples:
	| Client 	| TMPL_ID 	| elementName								| elementValue	| returnCode 	|alternateAppraisalEligibilityDecision|
	| ULAD | 02-v2 | creditRiskDerivation_ArrayElementObject | __EMPTY_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | creditRiskDerivation_ArrayElementObject | __NO_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | creditRiskDerivation_Array | __EMPTY_ARRAY__ | 200 | Not Eligible |
	| ULAD | 02-v2 | creditRiskDerivation_Array | __NO_ARRAY__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanApplicationDetails_Object | __NO_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanApplicationDetails_Object | __NULL_VALUE__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanApplication_Object | __EMPTY_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanCollateralRiskAssessmentDetails_Array | __EMPTY_ARRAY__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanCollateralRiskAssessmentDetails_Array | __NO_ARRAY__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanCollateralRiskAssessmentDetails_Array | __NULL_VALUE__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanCollateralRiskAssessmentDetails | __EMPTY_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanCollateralRiskAssessmentDetails | __NO_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanCollateralRiskAssessment_Object | __EMPTY_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanCollateralRiskAssessment_Object | __NO_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanCollateralRiskAssessment_Object | __NULL_VALUE__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanCollateralRiskAssessments_Object | __EMPTY_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanCollateralRiskAssessments_Object | __NO_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanCollateralRiskAssessments_Object | __NULL_VALUE__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanPropertyCollateral_Object | __EMPTY_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanPropertyCollaterals_Object | __NO_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanPropertyCollaterals_Object | __NULL_VALUE__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanRiskAssessmentDetails_Object | __EMPTY_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loan_Object | __EMPTY_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loan_Object | __NO_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loan_Object | __NULL_VALUE__ | 200 | Not Eligible |
	| ULAD | 02-v2 | propertyValuationDetails_Object | __NO_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | propertyValuationDetails_Object | __NULL_VALUE__ | 200 | Not Eligible |
	| ULAD | 02-v2 | propertyValuation_ArrayElementObject | __EMPTY_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | propertyValuation_ArrayElementObject | __NO_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | propertyValuation_Array | __NULL_VALUE__ | 200 | Not Eligible |
	| ULAD | 02-v2 | propertyValuations_Object | __EMPTY_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | propertyValuations_Object | __NO_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | propertyValuations_Object | __NULL_VALUE__ | 200 | Not Eligible |
#The below items returned ACE_FA301 for LQA but not for ULAD. For ULAD, getting Not Eligible
	| ULAD | 02-v2 | loanDetailInfo_Object | __EMPTY_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanRiskAssessments_Object | __NO_OBJECT__ | 200 | Not Eligible |
	| ULAD | 02-v2 | loanRiskAssessments_Object | __NULL_VALUE__ | 200 | Not Eligible |
	
	