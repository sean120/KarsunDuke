@SIT-ACEAPI20-HVS-ReqResp-Validation-v2 @B-113949_SIT @B-113950_SIT @SIT-IT03 @SIT-ACEAPI20
Feature: ACEAPI2.0 HVS Request/Response Validation

Scenario Outline: ACEAPI2.0 HVS Request/Response Validation
	Given ACEAPI2.0 request payload in the file "ACEAPI20Request-<Client>-<TMPL_ID>.json"
	And Update the ACEAPI2.0 request payload using the below Excel data
	| ExcelFileName   | ExcelSheetName   | ExcelRowNum   |
	| <ExcelFileName> | <ExcelSheetName> | <ExcelRowNum> |

	When User submits ACEAPI2.0 request
	
	Then User gets ACEAPI2.0 response	
	And User retrieves the corresponding "HVERequest" from ACEAPI2.0 Database
	And User verifies the "HVERequest" retrieved from ACEAPI2.0 Database
	And User retrieves the corresponding "HVEResponse" from ACEAPI2.0 Database
	And User verifies the "HVEResponse" retrieved from ACEAPI2.0 Database
	
Examples:
	| Client | TMPL_ID | ExcelFileName     | ExcelSheetName   | ExcelRowNum   |
	| ULAD   | 01      | HVETestData.xlsx  | HVETestData      | 1             |
	| ULAD   | 01      | HVETestData.xlsx  | HVETestData      | 2             |
	| ULAD   | 01      | HVETestData.xlsx  | HVETestData      | 3             |
	| ULAD   | 01      | HVETestData.xlsx  | HVETestData      | 4             |
	| LQA    | 01      | HVETestData.xlsx  | HVETestData      | 1             |
	| LQA    | 01      | HVETestData.xlsx  | HVETestData      | 2             |
	| LQA    | 01      | HVETestData.xlsx  | HVETestData      | 3             |
	| LQA    | 01      | HVETestData.xlsx  | HVETestData      | 4             |
