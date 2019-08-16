@B-128048  @AceApiResponse_ULAD_MessageValidation

Feature: EDS Response messages with pipe delimited values

Scenario Outline: Verify EDS Response messages with pipe delimited values are split into multiple listText values
	When User submits ACEAPI2.0 request "ACEAPI20Request-<Client>-<TMPL_ID>.json"
	| <element1Name>	| <element2Name>	|
	| <element1Value>	| <element2Value>	|

	Then User retrieves the corresponding "AAResponse" from ACEAPI2.0 Database
	And User verifies "AAResponse" data copied to ACEAPI2.0 response
	And User verifies the ACEAPI2.0 response values
	| alternateAppraisalEligibilityDecision		| alternateAppraisalReasonList		| messageCodeList	|
	| <alternateAppraisalEligibilityDecision>	| <alternateAppraisalReasonList>	| <messageCodeList>	|
	
Examples:
	| Client | TMPL_ID 	| element1Name							| element1Value				| element2Name							| element2Value		| alternateAppraisalEligibilityDecision	| alternateAppraisalReasonList	| messageCodeList		|
	| ULAD	 | 02-v2  	| refinanceProgramType					| EnhancedReliefRefinance	| loanPurposeType						| Refinance			| Not Eligible							| ReliefRefi					| L5,KT,Y7,BD,Y1,Y2,Y0	|
