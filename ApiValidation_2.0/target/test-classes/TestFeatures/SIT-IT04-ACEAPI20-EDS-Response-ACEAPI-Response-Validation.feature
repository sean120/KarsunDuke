@SIT-ACEAPI20-EDS-Response-ACEAPI-Response-Validation @B-113224_SIT @B-122907_SIT @B-113223_SIT @SIT-IT04 @SIT-ACEAPI20
Feature: ACEAPI2.0 EDS/AA Response to ACEAPI Response

Scenario Outline: ACEAPI2.0 EDS/AA Response to ACEAPI Response
	Given ACEAPI2.0 request payload in the file "ACEAPI20Request-<Client>-<TMPL_ID>.json"
	And Update the ACEAPI2.0 request payload using the below Excel data
	| ExcelFileName   | ExcelSheetName   | ExcelRowNum   |
	| <ExcelFileName> | <ExcelSheetName> | <ExcelRowNum> |
	
	When User submits ACEAPI2.0 request
	Then User gets ACEAPI2.0 response
	
	Then User retrieves the corresponding "AAResponse" from ACEAPI2.0 Database
	And User verifies "AAResponse" data copied to ACEAPI2.0 response
	
Examples:
	| Client | TMPL_ID | ExcelFileName        | ExcelSheetName   | ExcelRowNum   |
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 1             |
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 2             |
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 3             |
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 4             |
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 5             |
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 6             |
	| ULAD   | 02      | EDSTestData-02.xlsx  | EDSTestData-ULAD | 7             |
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 1             |
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 2             |
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 3             |
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 4             |
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 5             |
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 6             |
	| LQA    | 02      | EDSTestData-02.xlsx  | EDSTestData-LQA  | 7             |
