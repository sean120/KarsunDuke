@SIT-IT06-ACECheckAPI-HVS-Empty-PIWR @B-122906_SIT @SIT-IT06 @SIT-ACEAPI20 @Regression_SIT @NoAPIGEE
Feature: ACE Check API Submission -HVS Empty PIWR

Scenario Outline: ACE Check API Submission -HVS Empty PIWR
	When User submits ACE Check API (No APIGEE) request
	| partyRoleIdentifier	| loanPurposeType	| salesContractAmount	| propertyEstimatedValueAmount		| addressLineText				| cityName			| postalCode	| stateCode		|
	| <partyRoleIdentifier>	| <loanPurposeType>	| <salesContractAmount>	| <propertyEstimatedValueAmount>	| <addressLineText>				| <cityName>		| <postalCode>	| <stateCode>	|
	Then ACE Check API returns HTTP status code <returnCode>
	
Examples:
	| partyRoleIdentifier	| loanPurposeType	| salesContractAmount	| propertyEstimatedValueAmount	| addressLineText				| cityName			| postalCode	| stateCode	| returnCode	|
	#HVS Empty PIWR
	| 000601				| Purchase			| 346585				| 								| 1937 Middle Tennessee Blvd	| Murfreesboro		| 37130			| TN		| 200			|
