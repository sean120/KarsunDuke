@SIT-IT06-ACEAPI20-Map-HVEOptionType-From-ACEAPIRequest @SIT-IT06 @SIT-ACEAPI20 @Regression_SIT @B-127353_SIT
Feature: Map the HVEOptionType value in ACEAPI Request to EDS Request

Scenario Outline: Map the HVEOptionType value in ACEAPI Request to EDS Request
	When User submits ACEAPI2.0 request "ACEAPI20Request-<Client>-<TMPL_ID>.json"
	| HVEOptionType		| 	
	| <HVEOptionType>	|
	Then User retrieves the corresponding "AARequest" from ACEAPI2.0 Database
	And User verifies the "AARequest" values
	| homeValueExplorerOptionType	| HVE2homeValueExplorerOptionType		|
	|								| <HVEOptionType>						|
Examples:
	| Client	| TMPL_ID | HVEOptionType		|
	| ULAD   	| 02      | HVEFeedback			|
	| ULAD   	| 02      | NoHVEFeedback		|
	| ULAD   	| 02      | ReliefRefinance		|
	| ULAD   	| 02      | LimitedHVEFeedback	|
	| ULAD   	| 02      | __NULL_VALUE__		|
	| ULAD   	| 02      | __NO_ELEMENT__		|
	| LQA  		| 02      | HVEFeedback			|
	| LQA   	| 02      | NoHVEFeedback		|
	| LQA   	| 02      | ReliefRefinance		|
	| LQA   	| 02      | LimitedHVEFeedback	|
	| LQA   	| 02      | __NULL_VALUE__		|
	| LQA   	| 02      | __NO_ELEMENT__		|