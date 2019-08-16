@SIT-IT06-ACEAPI20-Request-HVS-EDS-ACEAPIResponse-Validation @SIT-ACEAPI20 @Regression_SIT  @B-113949_SIT @B-113950_SIT @B-113225_SIT  @B-114908_SIT @B-113224_SIT @B-122907_SIT @B-113223_SIT
Feature: ACEAPI2.0 Request-HVS-EDS-ACEAPIResponse-Validation

Scenario Outline: ACEAPI2.0 Request-HVS-EDS-ACEAPIResponse-Validation
	Given ACEAPI2.0 request payload in the file "ACEAPI20Request-<Client>-<TMPL_ID>.json"
	And Update the ACEAPI2.0 request payload using the below Excel data
	| ExcelFileName   | ExcelSheetName   | ExcelRowNum   |
	| <ExcelFileName> | <ExcelSheetName> | <ExcelRowNum> |
	
	When User submits ACEAPI2.0 request
	
	Then User gets ACEAPI2.0 response	
	And User verifies the ACEAPI2.0 response values
	| alternateAppraisalEligibilityDecision		| alternateAppraisalReasonList		|
	| <alternateAppraisalEligibilityDecision>	| <alternateAppraisalReasonList>	|
	
	Then User retrieves the corresponding "ACEAPI2.0Request" from ACEAPI2.0 Database
	And User verifies the "ACEAPI2.0Request" retrieved from ACEAPI2.0 Database

	Then User retrieves the corresponding "HVERequest" from ACEAPI2.0 Database
	And User verifies the "HVERequest" retrieved from ACEAPI2.0 Database
	
	Then User retrieves the corresponding "HVEResponse" from ACEAPI2.0 Database
	And User verifies the "HVEResponse" retrieved from ACEAPI2.0 Database
	
	Then User retrieves the corresponding "AARequest" from ACEAPI2.0 Database
	Then User verifies "ACEAPI2.0Request" data elements are correctly mapped to "AARequest"
	Then User verifies "HVE2" data in ACEAPI2.0 "AARequest"
	And User verifies the "AARequest" retrieved from ACEAPI2.0 Database

	Then User retrieves the corresponding "AAResponse" from ACEAPI2.0 Database
	And User verifies "AAResponse" data copied to ACEAPI2.0 response

	Then User retrieves the corresponding "ACEAPI2.0Response" from ACEAPI2.0 Database
	And User verifies the "ACEAPI2.0Response" retrieved from ACEAPI2.0 Database
	
Examples:
	| Client | TMPL_ID | ExcelFileName        | ExcelSheetName   | ExcelRowNum   | alternateAppraisalEligibilityDecision	| alternateAppraisalReasonList	|
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 1             | Eligible									| Eligible						|
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 2             | Not Eligible								| TLTVVarianceLimit				|
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 3             | Eligible									| Eligible						|
#	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 4             | Eligible									| Eligible						|
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 5             | Not Eligible								| NoHVEFDRAvail					|
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 6             | Eligible									| Eligible						|
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 7             | Eligible									| Eligible						|
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 1             | Eligible									| Eligible						|
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 2             | Not Eligible								| TLTVVarianceLimit				|
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 3             | Eligible									| Eligible						|
#	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 4             | Eligible									| Eligible						|
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 5             | Not Eligible								| NoHVEFDRAvail					|
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 6             | Eligible									| Eligible						|
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 7             | Eligible									| Eligible						|
