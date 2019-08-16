@SIT-HVE_FDR_CCE-IT02-AceAPI20-Consume-New-Elements @SIT-HVE_FDR_CCE @Regression_SIT-HVE_FDR_CCE @B-128955-SIT @SIT-HVE_FDR_CCE_IT02
Feature: Ace API 2.0 Consume New Elements for HVE/FDR CCE from HVS Response

Scenario Outline: Ace API 2.0 Consume New Elements for HVE/FDR CCE from HVS Response - ULAD
	When User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-02-min.json"
	| addressLineText	| cityName		| postalCode	| stateCode		| loanPurposeType	| propertyValuationAmount	| borrowerPropertyPurchasePriceAmount	| ltvRatioPercent1		| LoanID_LPKey			| LoanID_LPUL			| LoanID_LPT	| subscriberRequestCorrelationIdentifier	| 
	| <addressLineText>	| <cityName>	| <postalCode>	| <stateCode>	| <loanPurposeType>	| <propertyValuationAmount>	| <borrowerPropertyPurchasePriceAmount>	| <ltvRatioPercent1>	| __Run_ID__<transID>	| __Run_ID__<transID>	| <transID>		| __Run_ID__<transID>						| 

	And User verifies the ACEAPI2.0 "HVEResponse" data contains the following values 
	| predictedConditionScoreValue		| femaDisasterName		| appraisalAlternativeDisasterEligibilityDescription	|
	| <predictedConditionScoreValue> 	| <femaDisasterName>	| <appraisalAlternativeDisasterEligibilityDescription>	|

Examples:
	| transID	| addressLineText			| cityName			| postalCode	| stateCode	| loanPurposeType	| propertyValuationAmount	| borrowerPropertyPurchasePriceAmount	| ltvRatioPercent1	| predictedConditionScoreValue	| femaDisasterName	| appraisalAlternativeDisasterEligibilityDescription	|
	| 1001		| 11931 BENTON LAKE RD		| BRISTOW			| 20136			| VA		| Purchase			| 324260					| 325010								| 45				| 0.002				 			| DisasterFlorence	| ACERestrict, PIIRestrict								|
	| 1002		| 1938 TINNIN RD			| GREELEY			| 37072			| CO		| Purchase			| 324260					| 325010								| 45				| 0.002				 			| DisasterFlorence	| ACERestrict, PIIRestrict								|
	
Scenario Outline: Ace API 2.0 Consume New Elements for HVE/FDR CCE from HVS Response - LQA
	When User submits ACEAPI2.0 request "ACEAPI20Request-LQA-02-min.json"
	| addressLineText	| cityName		| postalCode	| stateCode		| loanPurposeType	| propertyValuationAmount	| borrowerPropertyPurchasePriceAmount	| ltvRatioPercent1		| LoanID_LQA			| LoanID_SLS			| subscriberRequestCorrelationIdentifier	| 
	| <addressLineText>	| <cityName>	| <postalCode>	| <stateCode>	| <loanPurposeType>	| <propertyValuationAmount>	| <borrowerPropertyPurchasePriceAmount>	| <ltvRatioPercent1>	| __Run_ID__<transID>	| __Run_ID__<transID>	| __Run_ID__<transID>						| 

	And User verifies the ACEAPI2.0 "HVEResponse" data contains the following values 
	| predictedConditionScoreValue		| femaDisasterName		| appraisalAlternativeDisasterEligibilityDescription	|
	| <predictedConditionScoreValue> 	| <femaDisasterName>	| <appraisalAlternativeDisasterEligibilityDescription>	|

Examples:
	| transID	| addressLineText			| cityName			| postalCode	| stateCode	| loanPurposeType	| propertyValuationAmount	| borrowerPropertyPurchasePriceAmount	| ltvRatioPercent1	| predictedConditionScoreValue	| femaDisasterName	| appraisalAlternativeDisasterEligibilityDescription	|
	| 1101		| 11931 BENTON LAKE RD		| BRISTOW			| 20136			| VA		| Purchase			| 324260					| 325010								| 45				| 0.002				 			| DisasterFlorence	| ACERestrict, PIIRestrict								|
	| 1102		| 1938 TINNIN RD			| GREELEY			| 37072			| CO		| Purchase			| 324260					| 325010								| 45				| 0.002				 			| DisasterFlorence	| ACERestrict, PIIRestrict								|
	