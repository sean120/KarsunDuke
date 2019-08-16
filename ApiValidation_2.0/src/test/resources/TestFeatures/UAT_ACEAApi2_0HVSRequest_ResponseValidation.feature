@ACE2_0Iteration03 @UATREGRESSION @B-113949 @HVSNEW3232

Feature: 001 HVS request mapping

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
	Then ACEAPI2.0 returns HTTP status code <returnCode>
	
	
Examples:
	| Client | TMPL_ID | ExcelFileName     | ExcelSheetName   | ExcelRowNum   |returnCode|
	| ULAD   | 01      | HVETestData.xlsx  | HVETestData      | 1             |200|
	| ULAD   | 01      | HVETestData.xlsx  | HVETestData      | 2             |200|
	| ULAD   | 01      | HVETestData.xlsx  | HVETestData      | 3             |200|
	| ULAD   | 01      | HVETestData.xlsx  | HVETestData      | 4             |200|
	| LQA    | 01      | HVETestData.xlsx  | HVETestData      | 1             |200|
	| LQA    | 01      | HVETestData.xlsx  | HVETestData      | 2             |200|
	| LQA    | 01      | HVETestData.xlsx  | HVETestData      | 3             |200|
	| LQA    | 01      | HVETestData.xlsx  | HVETestData      | 4             |200|
