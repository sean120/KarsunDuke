@Iteration04_SIT @B-112493 @Kostia @IT04 @B-112493-SIT @SIT-ACEAPI20 @Regression_SIT
Feature: ACEAPI2.0 sends GET Request to GFS

Background: Clear previously posted data from GFS database
	Given Remove "GFS" MongoDB documents with the following values
	| LoanID_LPKey 		| addressLineText		| PartyID_SELLER 	|postalCode|
	| B4798-OG	| 14576 BERKLEE DR	| 000601			|75001     |
	Given Remove "GFS" MongoDB documents with the following values
	|postalCode|
	|44444     |


	Given Remove "GFS" MongoDB documents with the following values
	| sourceApplicationName	| addressLineText		| PartyID_SELLER 	|
	| ACEAPI				| 14576 BERKLEE DR	| 000601			|

Scenario: User Sends ACEAPI2.0 request


          Given User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-GF-07.json" 
          | LoanID_LPKey   	| LoanID_LPT   	|subscriberIdentifier|addressLineText   |postalCode|  	
	      | B4798-OG 	| 001		 	|LPAv2               |14576 BERKLEE DR|75001     |
	      When User retrieves the latest data from MongoDB "grandFatheringAceResponse" collections
	      Then Check the response in "grandFatheringAceResponse" collection 
	      |ErrorMessageACE|
	      |Data Not Found |
	      And User retrieves the lates data stored in Mongo DB collection "grandFatheringAceData"
	      #re-submit
	     Given User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-GF-07.json" 
          | LoanID_LPKey   	| LoanID_LPT   	|subscriberIdentifier|addressLineText   |postalCode|  	
	      | B4798-OG 	| 001		 	|LPAv2               |14576 BERKLEE DR|75001     |
	      When User retrieves the latest data from MongoDB "grandFatheringAceResponse" collections
	       Then Verify that latest grandFatheringResponse and grandFatheringData contains grandFathering elements 
	      | LoanID_LPKey   	| LoanID_LPT   	|addressLineText   |postalCode|alternateAppraisalDecisionStatusType|  	
	      | B4798-OG 	| 001		 	|14576 BERKLEE DR|75001     |Current                             |
	      And Verify that sourceType is "LPAv2" in both collections      
	      And User retrieves the lates data stored in Mongo DB collection "grandFatheringAceData"
	      #re-submit
	      Given User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-GF-07.json" 
          | LoanID_LPKey   	| LoanID_LPT   	|subscriberIdentifier|addressLineText   |postalCode|  	
	      | B4798-OG 	    | 001		 	|LPAv2               |14576 BERKLEE DR  |75001     |
	      When User retrieves the latest data from MongoDB "grandFatheringAceResponse" collections
	      Then Verify that latest grandFatheringResponse and grandFatheringData contains grandFathering elements 
	      | LoanID_LPKey   	| LoanID_LPT   	|addressLineText   |postalCode|alternateAppraisalDecisionStatusType|  	
	      | B4798-OG 	| 001		 	|14576 BERKLEE DR|75001     |Reuse                               |
	      And Verify that sourceType is "LPAv2" in both collections	      