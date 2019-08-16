@Iteration05_SIT @B-120202-SIT @SIT-IT05 @IT05 @SIT-ACEAPI20 @Regression_SIT
Feature: Map EDS Request and Response to GFS POST Call

Background: Clear previously posted data from GFS database
	Given Remove "GFS" MongoDB documents with the following values
	| LoanID_LPKey 		    | addressLineText	| PartyID_SELLER 	|postalCode|
	| B0012685-88	        | 14576 BERKLEE DR	| 000601			|75001     |
	| A0100842              | 233 YUKON PASS    | 000601            |46706     | 

Scenario: User sends ACE2.0 request 
          Given ACEAPI2.0 request payload in the file "ACEAPI20Request-ULAD-VALID.json"
          When User submits ACEAPI2.0 request
          Then User retrieves the corresponding "AARequest" from ACEAPI2.0 Database
          Then User retrieves the corresponding "AAResponse" from ACEAPI2.0 Database
          And User retrieves the corresponding ACEAPI2.0 "GFSACEData" from GFS Database
          Then User verifies "ACEData" data is mapped correctly to grandfathering POST Call
          And User retrieves the corresponding ACEAPI2.0 "GFSPVData" from GFS Database
          Then User retrieves the corresponding "AARequest" from ACEAPI2.0 Database
          Then User verifies "PVData" data is mapped correctly to grandfathering PVPOST Call
