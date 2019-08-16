@B-113949 @ACE2_0UATREGRESSION @EDSREQUESTMAPPING
Feature: EDS Request Mapping

  Scenario Outline: User sends ACE 2.0 request and validtes EDS request mapping
    Given ACEAPI2.0 request payload in the file "ACEAPI20Request-<Client>-<TMPL_ID>.json"
    Then ACEAPI2.0 returns HTTP status code <returnCode>
    #Then User retrieves first record from "ACEFullAssessmentRequests" Ace Requests in the database
    #Then User retrieves "REQUEST" for "EDS" from UAT Mongo DB
    #Then Validate ACE 2.0 to "EDS" Request Mapping

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






    #Examples: 
      #| loanFileName                                |
      #| 017_ULADRequest1009CircleDrive              |
  