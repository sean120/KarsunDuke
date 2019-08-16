@SIT-HVE_FDR_CCE-IT03-ACEAPI20-Map-New-Elements-GFSPost @SIT-HVE_FDR_CCE @Regression_SIT-HVE_FDR_CCE @B-130863-SIT @SIT-HVE_FDR_CCE_IT03
Feature: ACE API 2.0 Map New Elements for HVE/FDR CCE to GFS POST

Scenario Outline: ACE API 2.0 Map New Elements for HVE/FDR CCE to GFS POST
	When User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-02-min.json"
	| addressLineText	| cityName		| postalCode	| stateCode		| loanPurposeType	| propertyValuationAmount	| borrowerPropertyPurchasePriceAmount	| ltvRatioPercent1		| LoanID_LPKey			| LoanID_LPUL			| LoanID_LPT	| subscriberRequestCorrelationIdentifier	| 
	| <addressLineText>	| <cityName>	| <postalCode>	| <stateCode>	| <loanPurposeType>	| <propertyValuationAmount>	| <borrowerPropertyPurchasePriceAmount>	| <ltvRatioPercent1>	| __Run_ID__<transID>	| __Run_ID__<transID>	| <transID>_1	| __Run_ID__<transID>						| 

	Then User verifies the following data elements from ACEAPI2.0 "AARequest" "HVE1" data are mapped to "GFSPostData" "HVE1"
	| predictedConditionScoreValue							|
	| femaDisasterName										|
	| appraisalAlternativeDisasterEligibilityDescription	|
	| propertyValuationEffectiveDateTime					|
	| homeValueExplorerAssessmentDateTime					|
	
	And User verifies the following data elements from ACEAPI2.0 "AARequest" "HVE2" data are mapped to "GFSPostData" "HVE2"
	| predictedConditionScoreValue							|
	| femaDisasterName										|
	| appraisalAlternativeDisasterEligibilityDescription	|
	| propertyValuationEffectiveDateTime					|
	| homeValueExplorerAssessmentDateTime					|
	
Examples:
	| transID	| addressLineText			| cityName			| postalCode	| stateCode	| loanPurposeType	| propertyValuationAmount	| borrowerPropertyPurchasePriceAmount	| ltvRatioPercent1	|
	| 3001		| 11931 BENTON LAKE RD		| BRISTOW			| 20136			| VA		| Purchase			| 384260					| 385010								| 45				|
	| 3002		| 1938 TINNIN RD			| GREELEY			| 37072			| CO		| Purchase			| 324260					| 325010								| 45				|
	