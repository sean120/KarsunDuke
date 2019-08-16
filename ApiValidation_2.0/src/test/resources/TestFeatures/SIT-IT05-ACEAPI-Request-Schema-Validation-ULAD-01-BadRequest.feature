@SIT-IT06-ACEAPI-Request-Schema-Validation-ULAD-01-BadRequest @SIT-IT05 @check-schema-all  @B-110840 @SIT-ACEAPI20 @Regression_SIT
Feature: ACEAPI2.0 Request Schema Validation - ULAD - BadRequest

Scenario Outline: ACEAPI2.0 Request Schema Validation - ULAD - BadRequest
	When User submits ACEAPI2.0 request "ACEAPI20Request-<Client>-<TMPL_ID>.json" with below changes
	| <elementName>	 |
	| <elementValue> |

	Then ACEAPI2.0 returns HTTP status code <returnCode>
	
Examples:
	| Client 	| TMPL_ID 	| elementName								| elementValue	| returnCode 	|
	| ULAD | 02-v2 | address_Array | __EMPTY_ARRAY__ | 400 |
	| ULAD | 02-v2 | address_Array | __NO_ARRAY__ | 400 |
	| ULAD | 02-v2 | addressDetails_Object | __EMPTY_OBJECT__ | 400 |
	| ULAD | 02-v2 | loanAcquisition_Object | __NO_OBJECT__ | 400 |
	| ULAD | 02-v2 | loanAcquisition_Object | __NULL_VALUE__ | 400 |
	| ULAD | 02-v2 | loanAcquisitionDetails_Object | __EMPTY_OBJECT__ | 400 |
	| ULAD | 02-v2 | loanApplication_Object | __NO_OBJECT__ | 400 |
	| ULAD | 02-v2 | loanApplication_Object | __NULL_VALUE__ | 400 |
	| ULAD | 02-v2 | loanApplicationDetails_Object | __EMPTY_OBJECT__ | 400 |
	| ULAD | 02-v2 | loanConversionRule_Object | __NO_OBJECT__ | 400 |
	| ULAD | 02-v2 | loanConversionRule_Object | __NULL_VALUE__ | 400 |
	| ULAD | 02-v2 | loanConversionRuleDetails_Object | __EMPTY_OBJECT__ | 400 |
	| ULAD | 02-v2 | loanDeliveryStructure_Object | __EMPTY_OBJECT__ | 400 |
	| ULAD | 02-v2 | loanDetails_Object | __EMPTY_OBJECT__ | 400 |
	| ULAD | 02-v2 | loanDetails_Object | __NO_OBJECT__ | 400 |
	| ULAD | 02-v2 | loanDetails_Object | __NULL_VALUE__ | 400 |
	| ULAD | 02-v2 | loanIdentification_Array | __EMPTY_ARRAY__ | 400 |
	| ULAD | 02-v2 | loanIdentification_Array | __NO_ARRAY__ | 400 |
	| ULAD | 02-v2 | loanIdentifications_Object | __EMPTY_OBJECT__ | 400 |
	| ULAD | 02-v2 | loanIdentifications_Object | __NO_OBJECT__ | 400 |
	| ULAD | 02-v2 | loanIdentifications_Object | __NULL_VALUE__ | 400 |
	| ULAD | 02-v2 | loanPropertyCollateral_Object | __NO_OBJECT__ | 400 |
	| ULAD | 02-v2 | loanPropertyCollateral_Object | __NULL_VALUE__ | 400 |
	| ULAD | 02-v2 | loanPropertyCollateralDetails_Object | __EMPTY_OBJECT__ | 400 |
	| ULAD | 02-v2 | loanPropertyCollateralDetails_Object | __NO_OBJECT__ | 400 |
	| ULAD | 02-v2 | loanPropertyCollateralDetails_Object | __NULL_VALUE__ | 400 |
	| ULAD | 02-v2 | loanPropertyCollaterals_Object | __EMPTY_OBJECT__ | 400 |
	| ULAD | 02-v2 | loanRiskAssessmentDetails_Object | __NO_OBJECT__ | 400 |
	| ULAD | 02-v2 | loanRiskAssessmentDetails_Object | __NULL_VALUE__ | 400 |
	| ULAD | 02-v2 | loanRiskAssessments_Object | __EMPTY_OBJECT__ | 400 |
	| ULAD | 02-v2 | partyRole_Array | __EMPTY_ARRAY__ | 400 |
	| ULAD | 02-v2 | partyRole_Array | __NO_ARRAY__ | 400 |
	| ULAD | 02-v2 | partyRole_ArrayElementObject | __NO_OBJECT__ | 400 |
	| ULAD | 02-v2 | partyRoles_Object | __EMPTY_OBJECT__ | 400 |
	| ULAD | 02-v2 | propertyValuation_Array | __EMPTY_ARRAY__ | 400 |
	| ULAD | 02-v2 | propertyValuation_Array | __NO_ARRAY__ | 400 |
	| ULAD | 02-v2 | propertyValuationDetails_Object | __EMPTY_OBJECT__ | 400 |
	| ULAD | 02-v2 | serviceRequestDetails_Object | __EMPTY_OBJECT__ | 400 |
	|ULAD|02-v2|productDefinition_Object|__NO_OBJECT__|400 |
	|ULAD|02-v2|productDefinition_Object|__NULL_VALUE__|400 |
	|ULAD|02-v2|productDetails_Object|__EMPTY_OBJECT__|400 |
	|ULAD|02-v2|serviceName|__NO_ELEMENT__|400|
	|ULAD|02-v2|serviceName|__NULL_VALUE__|400|
	|ULAD|02-v2|serviceRequestOperationName|__NO_ELEMENT__|400|
	|ULAD|02-v2|serviceRequestOperationName|__NULL_VALUE__|400|
	|ULAD|02-v2|subscriberIdentifier|__NO_ELEMENT__|400|
	|ULAD|02-v2|subscriberIdentifier|__NULL_VALUE__|400|
	|ULAD|02-v2|subscriberRequestCorrelationIdentifier|__NO_ELEMENT__|400|
	|ULAD|02-v2|subscriberRequestCorrelationIdentifier|__NULL_VALUE__|400|	