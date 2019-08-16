@SIT-IT06-ACECheckAPI-MediumMatchAddressValidation @SIT-IT06 @SIT-ACEAPI20 @Regression_SIT @NoAPIGEE
Feature: ACE Check API Submission - Medium match addresses validation

Scenario Outline: Verify ACE Check API Submission for Medium match addresses returns messageCode AWP0004
	When User submits ACE Check API (No APIGEE) request
	| partyRoleIdentifier	| loanPurposeType	| salesContractAmount	| propertyEstimatedValueAmount		| addressLineText				| cityName			| postalCode	| stateCode		|
	| <partyRoleIdentifier>	| <loanPurposeType>	| <salesContractAmount>	| <propertyEstimatedValueAmount>	| <addressLineText>				| <cityName>		| <postalCode>	| <stateCode>	|
	Then ACE Check API returns HTTP status code 200
	And User verifies the ACE Check API response
	| messageCode1	| messageText1																							|
	| AWP0004		| The subject property address cannot be validated. Please confirm accuracy and resubmit if necessary.	|
Examples:
	| partyRoleIdentifier	| loanPurposeType	| salesContractAmount	| propertyEstimatedValueAmount	| addressLineText				| cityName			| postalCode	| stateCode	|
	| 000601				| Purchase			| 346585				| 								| 2290 N 160TH WEST AVE			| SAND SPRINGS		| 74063			| OK		|
	| 000601				| Purchase			| 492116				| 								| 84R INDEPENDENCE DR			| LEOMINSTER		| 01453			| MA		|
	| 000601				| Purchase			| 346585				| 								| 3880 COLD SPRINGS RD E		| CONCORD			| 28025			| NC		|
	| 000601				| Purchase			| 410658				| 								| 3564 89 DR # 2E				| JACKSON HEIGHTS	| 11372			| NY		|
	| 000601				| Purchase			| 346585				| 								| 650 Freeway RD N				| MENDOTA HEIGHTS	| 55118			| MN		|
	| 000601				| Purchase			| 410658				| 								| 117 APACHE HILLS DR			| RUIDOSO			| 88345			| NM		|
	