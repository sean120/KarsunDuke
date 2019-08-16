@SIT-IT05-ACEAPI20-GFS-GETResponse-EDS-Request-Validation @IT05 @B-120201-SIT @SIT-IT05 @SIT-ACEAPI20 @Regression_SIT @NoAPIGEE
Feature: GFS GETResponse mapping to EDS/AA Request Validation

Background: Clear previously posted data from GFS database
	Given Remove "GFS" MongoDB documents with the following values
	| LoanID_LPKey 		| addressLineText		| PartyID_SELLER 	|
	| SIT_B120201_p01	| 2140 DEMETRIUS WAY	| 000601			|
	| SIT_B120201_p02	| 2140 DEMETRIUS WAY	| 000601			|
	| SIT_B120201_p03	| 2140 DEMETRIUS WAY	| 000601			|

	Given Remove "GFS" MongoDB documents with the following values
	| sourceApplicationName	| addressLineText		| PartyID_SELLER 	|
	| ACEAPI				| 2140 DEMETRIUS WAY	| 000601			|

Scenario: GFS GETResponse mapping to EDS/AA Request Validation- ULAD Re-submission
	#Original submission
	When User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-GF-06.json"
	| LoanID_LPKey   	| LoanID_LPT   	| propertyValuationAmount	| loanProcessingStageType	|  	
	| SIT_B120201_p01 	| 001		 	| 561410					| ApplicationProcessing		|
	Then User verifies the ACEAPI2.0 response values
	| alternateAppraisalEligibilityDecision		| alternateAppraisalDecisionStatusType	|
	| Eligible									| Current								|
	#No grandfathered data	
	Then User verifies "current HVE" data is copied as "grandfathered HVE (HVE container-1)" data
	And User verifies "current PV" data is copied as "original PV (PV container-1)" data
	
	#Re-submission
	When User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-GF-06.json"
	| LoanID_LPKey   	| LoanID_LPT   	| propertyValuationAmount	| loanProcessingStageType	|  	
	| SIT_B120201_p01 	| 002		 	| 561411					| ApplicationProcessing		|	
	Then User verifies the ACEAPI2.0 response values
	| alternateAppraisalEligibilityDecision		| alternateAppraisalDecisionStatusType	|
	| Eligible									| Reuse									|
	#Grandfathered data available
	Then User verifies "GFS GET response HVE" data is copied as "grandfathered HVE (HVE container-1)" data
	And User verifies "GFS GET response PV" data is copied as "original PV (PV container-1)" data

	#Another re-submission
	When User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-GF-06.json"
	| LoanID_LPKey   	| LoanID_LPT   	| propertyValuationAmount	| loanProcessingStageType	|  	
	| SIT_B120201_p01 	| 003		 	| 561412					| ApplicationProcessing		|
	Then User verifies the ACEAPI2.0 response values
	| alternateAppraisalEligibilityDecision		| alternateAppraisalDecisionStatusType	|
	| Eligible									| Reuse									|
	#Grandfathered data	available
	Then User verifies "GFS GET response HVE" data is copied as "grandfathered HVE (HVE container-1)" data
	And User verifies "GFS GET response PV" data is copied as "original PV (PV container-1)" data

@Regression_SIT_MIN	
Scenario: GFS GETResponse mapping to EDS/AA Request Validation- ULAD submission after ACECheckAPI submission
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
	When User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-GF-06.json"
	| LoanID_LPKey   	| LoanID_LPT   	| propertyValuationAmount	| loanProcessingStageType	|  	
	| SIT_B120201_p02 	| 001		 	| 561410					| ApplicationProcessing		|
	Then User verifies the ACEAPI2.0 response values
	| alternateAppraisalEligibilityDecision		| alternateAppraisalDecisionStatusType	|
	| Eligible									| Reuse									|
	#ACECheckAPI transaction HVS data grandfathered	
	Then User verifies "GFS GET response HVE" data is copied as "grandfathered HVE (HVE container-1)" data
	#No grandfathered PV data
	And User verifies "current PV" data is copied as "original PV (PV container-1)" data
	
	#ULAD Re-submission
	When User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-GF-06.json"
	| LoanID_LPKey   	| LoanID_LPT   	| propertyValuationAmount	| loanProcessingStageType	|  	
	| SIT_B120201_p02 	| 002		 	| 561411					| ApplicationProcessing		|	
	Then User verifies the ACEAPI2.0 response values
	| alternateAppraisalEligibilityDecision		| alternateAppraisalDecisionStatusType	|
	| Eligible									| Reuse									|
	#Grandfathered data available
	Then User verifies "GFS GET response HVE" data is copied as "grandfathered HVE (HVE container-1)" data
	And User verifies "GFS GET response PV" data is copied as "original PV (PV container-1)" data

Scenario: GFS GETResponse mapping to EDS/AA Request Validation- ULAD Re-submission- PreQualification
	#Original submission
	When User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-GF-06.json"
	| LoanID_LPKey   	| LoanID_LPT   	| propertyValuationAmount	| loanProcessingStageType	|  	
	| SIT_B120201_p03 	| 001		 	| 561410					| PreQualification		|
	Then User verifies the ACEAPI2.0 response values
	| alternateAppraisalEligibilityDecision		| alternateAppraisalDecisionStatusType	|
	| Eligible									| Current								|
	#No grandfathered data	
	Then User verifies "current HVE" data is copied as "grandfathered HVE (HVE container-1)" data
	And User verifies "current PV" data is copied as "original PV (PV container-1)" data
	
	#Re-submission
	When User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-GF-06.json"
	| LoanID_LPKey   	| LoanID_LPT   	| propertyValuationAmount	| loanProcessingStageType	|  	
	| SIT_B120201_p03 	| 002		 	| 561411					| ApplicationProcessing		|	
	Then User verifies the ACEAPI2.0 response values
	| alternateAppraisalEligibilityDecision		| alternateAppraisalDecisionStatusType	|
	| Eligible									| Reuse									|
	#Grandfathered HVS data available
	Then User verifies "GFS GET response HVE" data is copied as "grandfathered HVE (HVE container-1)" data
	#No Grandfathered PV data as original submission is 'PreQualification'
	And User verifies "current PV" data is copied as "original PV (PV container-1)" data

	#Another re-submission
	When User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-GF-06.json"
	| LoanID_LPKey   	| LoanID_LPT   	| propertyValuationAmount	| loanProcessingStageType	|  	
	| SIT_B120201_p03 	| 003		 	| 561412					| ApplicationProcessing		|
	Then User verifies the ACEAPI2.0 response values
	| alternateAppraisalEligibilityDecision		| alternateAppraisalDecisionStatusType	|
	| Eligible									| Reuse									|
	#Grandfathered data	available
	Then User verifies "GFS GET response HVE" data is copied as "grandfathered HVE (HVE container-1)" data
	And User verifies "GFS GET response PV" data is copied as "original PV (PV container-1)" data
	