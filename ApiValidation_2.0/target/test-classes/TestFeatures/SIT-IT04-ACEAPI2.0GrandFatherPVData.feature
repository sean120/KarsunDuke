@Iteration04_SIT @B-115767-SIT @Kostia @IT04 @SIT-ACEAPI20 @Regression_SIT
Feature: ACEAPI2.0 sends GET Request to GFS To Check PV Data

Background: Clear previously posted data from GFS database
	Given Remove "GFS" MongoDB documents with the following values
	| LoanID_LPKey 		| addressLineText		| PartyID_SELLER 	|postalCode|
	| SIT_B120201_01	|  14576 BERKLEE DR	| 000601			|75001     |
	Given Remove "GFS" MongoDB documents with the following values
	|postalCode|
	|44444     |
	Given Remove "GFS" MongoDB documents with the following values
	| sourceApplicationName	| addressLineText		| PartyID_SELLER 	|
	| ACEAPI				| 14576 BERKLEE DR	| 000601			|

Scenario: User Sends ACEAPI2.0 request
          
           Given User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-GF-07.json" 
          | LoanID_LPKey   	| LoanID_LPT   	|subscriberIdentifier|addressLineText   |postalCode|propertyValuationAmount|  	
	      | SIT_B120201_01 	| 001		 	|LPAv2               |14576 BERKLEE DR|75001     |100000                  |
	      When User retrieves the latest data from MongoDB "grandFatheringPresentValueResponse" collections
	      Then Check the response in "grandFatheringPresentValueResponse" collection 
	      |ErrorMessageACE|
	      |Data Not Found |
	      And User retrieves the lates data stored in Mongo DB collection "grandFatheringPresentValueData"
	      Given User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-GF-07.json" 
          | LoanID_LPKey   	| LoanID_LPT   	|addressLineText   |postalCode|propertyValuationAmount|  	
	      | SIT_B120201_01 	| 001		 	|14576 BERKLEE DR|75001     |200000                    |
	      When User retrieves the latest data from MongoDB "grandFatheringPresentValueResponse" collections
	      Then Verify that latest grandFatheringPresentValueResponse and grandFatheringPresentValueData contains grandFathering elements
	      | LoanID_LPKey   	| LoanID_LPT   	|addressLineText   |postalCode|propertyValuationAmount|  	
	      | SIT_B120201_01 	| 001		 	|14576 BERKLEE DR|75001     |100000                  |
	      Given User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-GF-07.json" 
          | LoanID_LPKey   	| LoanID_LPT   	|addressLineText   |postalCode|propertyValuationAmount|  	
	      | SIT_B120201_01 	| 001		 	|14576 BERKLEE DR|75001     |700000                 |
	       When User retrieves the latest data from MongoDB "grandFatheringPresentValueResponse" collections
	      Then Verify that latest grandFatheringPresentValueResponse and grandFatheringPresentValueData contains grandFathering elements
	      | LoanID_LPKey   	| LoanID_LPT   	|addressLineText   |postalCode|propertyValuationAmount|  	
	      | SIT_B120201_01 	| 001		 	|14576 BERKLEE DR|75001     |100000                  |
	      
	      
	      
	      
	      