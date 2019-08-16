@SIT-ACEAPI20-EDS-Request-ACEAPI-Request-Validation @B-113225_SIT @SIT-IT04 @SIT-ACEAPI20
Feature: ACEAPI2.0 Request to EDS/AA Request mapping

Scenario Outline: ACEAPI2.0 Request to EDS/AA Request mapping
	Given ACEAPI2.0 request payload in the file "ACEAPI20Request-<Client>-<TMPL_ID>.json"
	And Update the ACEAPI2.0 request payload using the below Excel data
	| ExcelFileName   | ExcelSheetName   | ExcelRowNum   |
	| <ExcelFileName> | <ExcelSheetName> | <ExcelRowNum> |
	
	When User submits ACEAPI2.0 request
	Then User gets ACEAPI2.0 response
	
	Then User retrieves the corresponding "AARequest" from ACEAPI2.0 Database
	Then User verifies "ACEAPI2.0Request" data elements are correctly mapped to "AARequest" 
	
Examples:
	| Client | TMPL_ID | ExcelFileName        | ExcelSheetName   | ExcelRowNum   |
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 1             |
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 2             |
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 3             |
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 4             |
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 6             |
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 7             |
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 1             |
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 2             |
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 3             |
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 4             |
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 6             |
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 7             |
