@SIT-IT06-ACEAPI-Request-Schema-Validation-ULAD-04-NotEligible-02 @SIT-IT05 @check-schema-all @B-110840 @SIT-ACEAPI20
Feature: ACEAPI2.0 Request Schema Validation - ULAD - NotEligible-set2

Scenario Outline: ACEAPI2.0 Request Schema Validation - ULAD - NotEligible-set2
	When User submits ACEAPI2.0 request "ACEAPI20Request-<Client>-<TMPL_ID>.json" with below changes
	| <elementName>	 |
	| <elementValue> |

	Then ACEAPI2.0 returns HTTP status code <returnCode>
	And User verifies the ACEAPI2.0 response values
	| alternateAppraisalEligibilityDecision 	|
	| <alternateAppraisalEligibilityDecision> 	|
		
Examples:
	| Client 	| TMPL_ID 	| elementName								| elementValue	| returnCode 	|alternateAppraisalEligibilityDecision|
	|ULAD|02-v2|addressLineText||200|Not Eligible|
	|ULAD|02-v2|borrowerPropertyPurchasePriceAmount|__NO_ELEMENT__|200|Not Eligible|
	|ULAD|02-v2|borrowerPropertyPurchasePriceAmount|__NULL_VALUE__|200|Not Eligible|
	|ULAD|02-v2|derivationRiskClassType|__NO_ELEMENT__|200|Not Eligible|
	|ULAD|02-v2|derivationRiskClassType|__NULL_VALUE__|200|Not Eligible|
	|ULAD|02-v2|financedUnitCount|__NO_ELEMENT__|200|Not Eligible|
	|ULAD|02-v2|financedUnitCount|__NULL_VALUE__|200|Not Eligible|
	|ULAD|02-v2|loanPurposeType|__NO_ELEMENT__|200|Not Eligible|
	|ULAD|02-v2|loanPurposeType|__NULL_VALUE__|200|Not Eligible|
	|ULAD|02-v2|loanScopeType1|__NO_ELEMENT__|200|Not Eligible|
	|ULAD|02-v2|loanScopeType1|__NULL_VALUE__|200|Not Eligible|
	|ULAD|02-v2|ltvRatioPercent1|__NO_ELEMENT__|200|Not Eligible|
	|ULAD|02-v2|ltvRatioPercent1|__NULL_VALUE__|200|Not Eligible|
	|ULAD|02-v2|ltvSourceType1|__NO_ELEMENT__|200|Not Eligible|
	|ULAD|02-v2|ltvSourceType1|__NULL_VALUE__|200|Not Eligible|
	|ULAD|02-v2|mortgageType|__NO_ELEMENT__|200|Not Eligible|
	|ULAD|02-v2|mortgageType|__NULL_VALUE__|200|Not Eligible|
	|ULAD|02-v2|postalCode||200|Not Eligible|
	|ULAD|02-v2|propertyValuationAmount|__NO_ELEMENT__|200|Not Eligible|
	|ULAD|02-v2|propertyValuationAmount|__NULL_VALUE__|200|Not Eligible|
	|ULAD|02-v2|propertyValuationMethodType|__NO_ELEMENT__|200|Not Eligible|
	|ULAD|02-v2|propertyValuationMethodType|__NULL_VALUE__|200|Not Eligible|
	|ULAD|02-v2|propertyValuationMethodType||200|Not Eligible|
	|ULAD|02-v2|propertyValuationType|__NO_ELEMENT__|200|Not Eligible|
	|ULAD|02-v2|propertyValuationType|__NULL_VALUE__|200|Not Eligible|
	|ULAD|02-v2|propertyValuationType||200|Not Eligible|
	|ULAD|02-v2|strategicOfferingCreditRiskType|__NO_ELEMENT__|200|Not Eligible|
	|ULAD|02-v2|strategicOfferingCreditRiskType|__NULL_VALUE__|200|Not Eligible|
	|ULAD|02-v2|borrowerPropertyPurchasePriceAmount||200|Not Eligible|
	|ULAD|02-v2|financedUnitCount||200|Not Eligible|
	|ULAD|02-v2|ltvRatioPercent1||200|Not Eligible|
	|ULAD|02-v2|propertyValuationAmount||200|Not Eligible|	
