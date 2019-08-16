@SMOKETEST-20
Feature: ACE Check API (No APIGEE) Submission

Scenario: ACE Check API (No APIGEE) Submission


	When User submits ACE Check API (No APIGEE) request
	| partyRoleIdentifier	| loanPurposeType	| salesContractAmount	| addressLineText				| cityName			| postalCode	| stateCode	| returnCode	|
	| 000601				| Purchase			| 346585				| 11931 BENTON LAKE RD			| BRISTOW			| 20136			| VA		| 200			|
	Then ACE Check API returns HTTP status code 200	