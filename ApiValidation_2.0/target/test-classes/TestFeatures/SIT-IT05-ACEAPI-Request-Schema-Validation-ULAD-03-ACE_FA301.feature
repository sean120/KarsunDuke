@SIT-IT06-ACEAPI-Request-Schema-Validation-ULAD-03-ACE_FA301 @SIT-IT05 @check-schema-all @B-110840 @SIT-ACEAPI20
Feature: ACEAPI2.0 Request Schema Validation - ULAD - ACE_FA301

Scenario Outline: ACEAPI2.0 Request Schema Validation - ULAD - ACE_FA301
	When User submits ACEAPI2.0 request "ACEAPI20Request-<Client>-<TMPL_ID>.json" with below changes
	| <elementName>	 |
	| <elementValue> |

	Then ACEAPI2.0 returns HTTP status code <returnCode>
	And User verifies the ACEAPI2.0 response values
	| errorCode 	| errorMessage		|
	| <errorCode> 	| <errorMessage>	|
		
Examples:
	| Client 	| TMPL_ID 	| elementName								| elementValue	| returnCode 	|errorCode|errorMessage|
	| ULAD | 02-v2 | address_ArrayElementObject_PreScrub | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | address_ArrayElementObject | __EMPTY_OBJECT__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | address_ArrayElementObject | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | address_Array | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | creditRiskDerivation_ArrayElementObject | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | creditRiskDerivation_Array | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | loanCollateralRiskAssessmentDetails2 | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | loanCollateralRiskAssessmentDetails | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | loanDeliveryStructure_Object | __NO_OBJECT__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | loanDeliveryStructure_Object | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | loanDetailInfo_Object | __NO_OBJECT__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | loanDetailInfo_Object | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | loanIdentification_ArrayElementObject_LPKey | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | loanIdentification_ArrayElementObject_LPUL | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | loanIdentification_ArrayElementObject_LPT | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | loanIdentification_ArrayElementObject_HPGT | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | loanIdentification_ArrayElementObject_HPLPT | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | loanIdentification_Array | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | partyRole_ArrayElementObject | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | propertyValuation_ArrayElementObject_EST_O | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | propertyValuation_ArrayElementObject_EST_S | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | propertyValuation_ArrayElementObject_FR | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | propertyValuation_ArrayElementObject | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | serviceRequestDetails_Object | __NO_OBJECT__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | serviceRequestDetails_Object | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|creditRiskDerivation_ArrayElementObject2|__NULL_VALUE__|200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|addressMatchLevelType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|addressUnitDesignatorType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|carrierRouteType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|censusTractBaseIdentifier||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|constructionLoanType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|constructionMethodType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|derivationRiskClassType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|derivedCalculationSourceType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|EST_O_propertyValuationAmount|__NO_ELEMENT__|200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|EST_O_propertyValuationAmount|__NULL_VALUE__|200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|EST_O_propertyValuationAmount||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|EST_O_propertyValuationEffectiveDateTime||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|EST_S_propertyValuationAmount|__NO_ELEMENT__|200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|EST_S_propertyValuationAmount|__NULL_VALUE__|200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|EST_S_propertyValuationAmount||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|EST_S_propertyValuationEffectiveDateTime||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|geocodingIndicator|__NO_ELEMENT__|200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|geocodingIndicator|__NULL_VALUE__|200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|loanConformityType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|LoanID_LPUL|__NO_ELEMENT__|200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|LoanID_LPUL|__NULL_VALUE__|200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|LoanID_LPUL||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|loanProcessingStageType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|loanPropertyUsageType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|loanPurposeType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|loanRefinanceCashOutDeterminationType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|loanScopeType1||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|loanScopeType2||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|loanUnderwritingDecisionDefaultProbabilityRate||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|ltvSourceType1||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|ltvSourceType2||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|miPremiumFinancedAmount||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|miPremiumFinancedIndicator||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|mortgageType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|PreScrub_stateCode||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|productIdentifier||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|productType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|programType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|programType2||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|projectAttachmentType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|projectLegalStructureType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|propertyCategoryType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|propertyEstateType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|refinanceProgramType||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|refinanceProgramType2||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|stateCode||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|strategicOfferingCreditRiskType||200|ACE_FA301|Exception in ACE API 2.0|
#required for GFS
	|ULAD|02-v2|addressMatchLevelType|__NO_ELEMENT__|200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|addressMatchLevelType|__NULL_VALUE__|200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|stateCode|__NO_ELEMENT__|200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|stateCode|__NULL_VALUE__|200|ACE_FA301|Exception in ACE API 2.0|
#The below items returned ACE_FA301 for LQA but not for ULAD. For ULAD, getting Not Eligible
#	| ULAD | 02-v2 | loanDetailInfo_Object | __EMPTY_OBJECT__ | 200 |ACE_FA301|Exception in ACE API 2.0|
#	| ULAD | 02-v2 | loanRiskAssessments_Object | __NO_OBJECT__ | 200 |ACE_FA301|Exception in ACE API 2.0|
#	| ULAD | 02-v2 | loanRiskAssessments_Object | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD		| 02-v2		| HVEOptionType								|| 200 |ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|cityName|__NO_ELEMENT__|200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|cityName|__NULL_VALUE__|200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|addressLineText|__NO_ELEMENT__|200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|addressLineText|__NULL_VALUE__|200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|geocodingIndicator||200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|postalCode|__NO_ELEMENT__|200|ACE_FA301|Exception in ACE API 2.0|
	|ULAD|02-v2|postalCode|__NULL_VALUE__|200|ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | addressDetails_Object | __NO_OBJECT__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | addressDetails_Object | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | address_ArrayElementObject | __NO_OBJECT__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | propertyDetails_Object | __EMPTY_OBJECT__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | propertyDetails_Object | __NO_OBJECT__ | 200 |ACE_FA301|Exception in ACE API 2.0|
	| ULAD | 02-v2 | propertyDetails_Object | __NULL_VALUE__ | 200 |ACE_FA301|Exception in ACE API 2.0|

	