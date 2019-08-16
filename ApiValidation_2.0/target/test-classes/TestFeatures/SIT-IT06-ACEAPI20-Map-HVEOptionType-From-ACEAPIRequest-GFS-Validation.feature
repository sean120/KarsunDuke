@SIT-IT06-ACEAPI20-Map-HVEOptionType-From-ACEAPIRequest-GFS-Validation @SIT-IT06 @SIT-ACEAPI20 @Regression_SIT @NoAPIGEE @B-127353_SIT
Feature: Verify HVEOptionType mapping from ACEAPIRequest wrt GFS Logic

Background: Clear previously posted data from GFS database
	Given Remove "GFS" MongoDB documents with the following values
	| LoanID_LPKey 		| addressLineText		| PartyID_SELLER 	|
	| SIT_B120201_p04	| 2140 DEMETRIUS WAY	| 000601			|
	| SIT_B120201_p05	| 2140 DEMETRIUS WAY	| 000601			|

	Given Remove "GFS" MongoDB documents with the following values
	| sourceApplicationName	| addressLineText		| PartyID_SELLER 	|
	| ACEAPI				| 2140 DEMETRIUS WAY	| 000601			|

Scenario Outline: Verify HVEOptionType mapping from ACEAPIRequest wrt GFS Logic- ULAD Re-submission
	#Original submission
	When User submits ACEAPI2.0 request "ACEAPI20Request-<Client>-<TMPL_ID>.json"
	| LoanID_LPKey   	| LoanID_LPT   	| propertyValuationAmount	| loanProcessingStageType	| HVEOptionType		|  	
	| SIT_B120201_p04 	| 001		 	| 561410					| ApplicationProcessing		| <HVEOptionType>	|
	Then User verifies the ACEAPI2.0 response values
	| alternateAppraisalEligibilityDecision		| alternateAppraisalDecisionStatusType	|
	| Eligible									| Current								|	
	#No grandfathered data	
	Then User verifies "current HVE" data is copied as "grandfathered HVE (HVE container-1)" data
	And User verifies "current PV" data is copied as "original PV (PV container-1)" data
	Then User retrieves the corresponding ACEAPI2.0 "GFSPostData" from GFS Database
	Then User retrieves the corresponding "AARequest" from ACEAPI2.0 Database
	
	And User verifies the "AARequest" values
	| homeValueExplorerOptionType	| HVE2homeValueExplorerOptionType		|
	|								| <HVEOptionType>						|
	
	#Re-submission
	When User submits ACEAPI2.0 request "ACEAPI20Request-<Client>-<TMPL_ID>.json"
	| LoanID_LPKey   	| LoanID_LPT   	| propertyValuationAmount	| loanProcessingStageType	| HVEOptionType		|  	
	| SIT_B120201_p04 	| 002		 	| 561411					| ApplicationProcessing		| <HVEOptionType>	|	
	Then User verifies the ACEAPI2.0 response values
	| alternateAppraisalEligibilityDecision		| alternateAppraisalDecisionStatusType	|
	| Eligible									| Reuse									|
	#Grandfathered data available
	Then User verifies "GFS GET response HVE" data is copied as "grandfathered HVE (HVE container-1)" data
	And User verifies "GFS GET response PV" data is copied as "original PV (PV container-1)" data
	And User verifies GFS "GFSPostData" "HVE2" data matches "GFSGetResponse" "HVE" data

	Then User retrieves the corresponding "AARequest" from ACEAPI2.0 Database
	And User verifies the "AARequest" values
	| homeValueExplorerOptionType	| HVE2homeValueExplorerOptionType		|
	|								| <HVEOptionType>						|

	#Another re-submission
	When User submits ACEAPI2.0 request "ACEAPI20Request-<Client>-<TMPL_ID>.json"
	| LoanID_LPKey   	| LoanID_LPT   	| propertyValuationAmount	| loanProcessingStageType	| HVEOptionType		|   	
	| SIT_B120201_p04 	| 003		 	| 561412					| ApplicationProcessing		| <HVEOptionType>	|
	Then User verifies the ACEAPI2.0 response values
	| alternateAppraisalEligibilityDecision		| alternateAppraisalDecisionStatusType	|
	| Eligible									| Reuse									|
	#Grandfathered data	available
	Then User verifies "GFS GET response HVE" data is copied as "grandfathered HVE (HVE container-1)" data
	And User verifies "GFS GET response PV" data is copied as "original PV (PV container-1)" data
	And User verifies GFS "GFSPostData" "HVE2" data matches "GFSGetResponse" "HVE" data
	
	Then User retrieves the corresponding "AARequest" from ACEAPI2.0 Database
	And User verifies the "AARequest" values
	| homeValueExplorerOptionType	| HVE2homeValueExplorerOptionType		|
	|								| <HVEOptionType>						|
	
Examples:
	| Client | TMPL_ID | HVEOptionType			|
	| ULAD   | GF-06   | HVEFeedback			|
	| ULAD   | GF-06   | NoHVEFeedback			|
	| ULAD   | GF-06   | ReliefRefinance		|
	| ULAD   | GF-06   | __NULL_VALUE__			|
	
Scenario Outline: Verify HVEOptionType mapping from ACEAPIRequest wrt GFS Logic- ULAD submission after ACECheckAPI submission
	#ACE Check API submission
	When User submits ACE Check API (No APIGEE) request
	| partyRoleIdentifier	| loanPurposeType	| salesContractAmount	| propertyEstimatedValueAmount	| addressLineText				| cityName			| postalCode	| stateCode		|
	| 000601				| Refinance			| 						| 575000						| 2140 DEMETRIUS WAY			| ROSEVILLE			| 95661			| CA			|
	Then ACE Check API returns HTTP status code 200
	And User verifies the ACE Check API response
	| messageCode1	| messageText1																							|
	| AWP0001		| Based on the submitted data, the subject property is conditionally approved for an appraisal waiver. This approval is contingent upon the maximum loan amount, the expiration date, a complete assessment of the loan by Loan Product Advisor, and compliance with the eligibility requirements in the Freddie Mac Seller/Servicer Guide for the appraisal waiver.	|
	Then User retrieves the corresponding "ACEAPIRequest" from ACEAPI Database
	And User retrieves the corresponding "GFSPostData" from GFS Database
	
	#Original ULAD submission
	When User submits ACEAPI2.0 request "ACEAPI20Request-<Client>-<TMPL_ID>.json"
	| LoanID_LPKey   	| LoanID_LPT   	| propertyValuationAmount	| loanProcessingStageType	| HVEOptionType		|   	
	| SIT_B120201_p05 	| 001		 	| 561410					| ApplicationProcessing		| <HVEOptionType>	|
	Then User verifies the ACEAPI2.0 response values
	| alternateAppraisalEligibilityDecision		| alternateAppraisalDecisionStatusType	|
	| Eligible									| Reuse									|
	#ACECheckAPI transaction HVS data grandfathered	
	Then User verifies "GFS GET response HVE" data is copied as "grandfathered HVE (HVE container-1)" data
	#No grandfathered PV data
	And User verifies "current PV" data is copied as "original PV (PV container-1)" data
	And User verifies GFS "GFSPostData" "HVE" data matches "GFSGetResponse" "HVE" data
	
	Then User retrieves the corresponding "AARequest" from ACEAPI2.0 Database
	And User verifies the "AARequest" values
	| homeValueExplorerOptionType	| HVE2homeValueExplorerOptionType		|
	|								| <HVEOptionType>						|
	
	
	#ULAD Re-submission
	When User submits ACEAPI2.0 request "ACEAPI20Request-<Client>-<TMPL_ID>.json"
	| LoanID_LPKey   	| LoanID_LPT   	| propertyValuationAmount	| loanProcessingStageType	| HVEOptionType		|  	
	| SIT_B120201_p05 	| 002		 	| 561411					| ApplicationProcessing		| <HVEOptionType>	|	
	Then User verifies the ACEAPI2.0 response values
	| alternateAppraisalEligibilityDecision		| alternateAppraisalDecisionStatusType	|
	| Eligible									| Reuse									|
	#Grandfathered data available
	Then User verifies "GFS GET response HVE" data is copied as "grandfathered HVE (HVE container-1)" data
	And User verifies "GFS GET response PV" data is copied as "original PV (PV container-1)" data
	And User verifies GFS "GFSPostData" "HVE" data matches "GFSGetResponse" "HVE" data
	
	Then User retrieves the corresponding "AARequest" from ACEAPI2.0 Database
	And User verifies the "AARequest" values
	| homeValueExplorerOptionType	| HVE2homeValueExplorerOptionType		|
	|								| <HVEOptionType>						|
	

Examples:
	| Client | TMPL_ID | HVEOptionType			|
	| ULAD   | GF-06   | HVEFeedback			|
	| ULAD   | GF-06   | NoHVEFeedback			|
	| ULAD   | GF-06   | ReliefRefinance		|
	| ULAD   | GF-06   | __NULL_VALUE__			|
	