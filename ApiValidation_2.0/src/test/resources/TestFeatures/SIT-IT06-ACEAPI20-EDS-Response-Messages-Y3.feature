@SIT-IT06-ACEAPI20-EDS-Response-Messages-Y3 @B-126084_SIT @SIT-IT06 @SIT-ACEAPI20 @Regression_SIT @B-127353_SIT @B-128048_SIT
Feature: EDS Response messages with Y3 message code wrt HVEOptionType

  Scenario Outline: Verify EDS Response messages for Y3 message wrt HVEOptionType
    Given ACEAPI2.0 request payload in the file "ACEAPI20Request-<Client>-<TMPL_ID>.json"
    And Update the ACEAPI2.0 request payload using the below Excel data
      | ExcelFileName   | ExcelSheetName   | ExcelRowNum   |
      | <ExcelFileName> | <ExcelSheetName> | <ExcelRowNum> |
    And Update the ACEAPI2.0 request payload using the below data
      | HVEOptionType   | loanPurposeType   | refinanceProgramType   |
      | <HVEOptionType> | <loanPurposeType> | <refinanceProgramType> |
    When User submits ACEAPI2.0 request
    Then User gets ACEAPI2.0 response
    And User retrieves the corresponding "HVEResponse" from ACEAPI2.0 Database
    And User verifies there is "<HVSDataAvailability>" in the "HVEResponse"
    Then User retrieves the corresponding "AARequest" from ACEAPI2.0 Database
    And User verifies the "AARequest" values
      | homeValueExplorerOptionType | HVE2homeValueExplorerOptionType |
      |                             | <HVEOptionType>                 |
    Then User retrieves the corresponding "AAResponse" from ACEAPI2.0 Database
    And User verifies the ACEAPI2.0 response values
      | alternateAppraisalEligibilityDecision   | alternateAppraisalReasonList   | messageCodeList   |
      | <alternateAppraisalEligibilityDecision> | <alternateAppraisalReasonList> | <messageCodeList> |

    Examples: 
      | Client | TMPL_ID | ExcelFileName       | ExcelSheetName   | ExcelRowNum | HVEOptionType   | loanPurposeType | refinanceProgramType      | HVSDataAvailability | alternateAppraisalEligibilityDecision | alternateAppraisalReasonList | messageCodeList      |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           5 | HVEFeedback     | Refinance       | EnhancedReliefRefinance   | NoHVSData           | Not Eligible                          | NoHVEFDRAvail                | L5,KT,Y3             |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           5 | NoHVEFeedback   | Refinance       | EnhancedReliefRefinance   | NoHVSData           | Not Eligible                          | NoHVEFDRAvail                | L5,KT                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           5 | ReliefRefinance | Refinance       | EnhancedReliefRefinance   | NoHVSData           | Not Eligible                          | NoHVEFDRAvail                | L5,KT                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           5 | __NULL_VALUE__  | Refinance       | EnhancedReliefRefinance   | NoHVSData           | Not Eligible                          | NoHVEFDRAvail                | L5,KT                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           5 | __NO_ELEMENT__  | Refinance       | EnhancedReliefRefinance   | NoHVSData           | Not Eligible                          | NoHVEFDRAvail                | L5,KT                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           5 | HVEFeedback     | Refinance       | __NULL_VALUE__            | NoHVSData           | Not Eligible                          | NoHVEFDRAvail                | L5,KT                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           5 | HVEFeedback     | Refinance       | ReliefRefinanceOpenAccess | NoHVSData           | Not Eligible                          | NoHVEFDRAvail                | L5,KT,Y3             |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           5 | NoHVEFeedback   | Refinance       | ReliefRefinanceOpenAccess | NoHVSData           | Not Eligible                          | NoHVEFDRAvail                | L5,KT                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           5 | ReliefRefinance | Refinance       | ReliefRefinanceOpenAccess | NoHVSData           | Not Eligible                          | NoHVEFDRAvail                | L5,KT                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           5 | __NULL_VALUE__  | Refinance       | ReliefRefinanceOpenAccess | NoHVSData           | Not Eligible                          | NoHVEFDRAvail                | L5,KT                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           5 | __NO_ELEMENT__  | Refinance       | ReliefRefinanceOpenAccess | NoHVSData           | Not Eligible                          | NoHVEFDRAvail                | L5,KT                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           5 | HVEFeedback     | Refinance       | StreamlinedRefinance      | NoHVSData           | Not Eligible                          | NoHVEFDRAvail                | L5,KT                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           1 | HVEFeedback     | Refinance       | EnhancedReliefRefinance   | HVSDataAvailable    | Not Eligible                          | ReliefRefi                   | L5,KT,Y7,BD,Y1,Y2,Y0 |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           1 | NoHVEFeedback   | Refinance       | EnhancedReliefRefinance   | HVSDataAvailable    | Not Eligible                          | ReliefRefi                   | L5,KT                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           1 | ReliefRefinance | Refinance       | EnhancedReliefRefinance   | HVSDataAvailable    | Not Eligible                          | ReliefRefi                   | L5,KT                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           1 | __NULL_VALUE__  | Refinance       | EnhancedReliefRefinance   | HVSDataAvailable    | Not Eligible                          | ReliefRefi                   | L5,KT                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           1 | __NO_ELEMENT__  | Refinance       | EnhancedReliefRefinance   | HVSDataAvailable    | Not Eligible                          | ReliefRefi                   | L5,KT                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           1 | HVEFeedback     | Refinance       | __NULL_VALUE__            | HVSDataAvailable    | Eligible                              | Eligible                     | B1,WH                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           1 | HVEFeedback     | Refinance       | ReliefRefinanceOpenAccess | HVSDataAvailable    | Not Eligible                          | ReliefRefi                   | L5,KT,Y7,BD,Y1,Y2,Y0 |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           1 | NoHVEFeedback   | Refinance       | ReliefRefinanceOpenAccess | HVSDataAvailable    | Not Eligible                          | ReliefRefi                   | L5,KT                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           1 | ReliefRefinance | Refinance       | ReliefRefinanceOpenAccess | HVSDataAvailable    | Not Eligible                          | ReliefRefi                   | L5,KT                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           1 | __NULL_VALUE__  | Refinance       | ReliefRefinanceOpenAccess | HVSDataAvailable    | Not Eligible                          | ReliefRefi                   | L5,KT                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           1 | __NO_ELEMENT__  | Refinance       | ReliefRefinanceOpenAccess | HVSDataAvailable    | Not Eligible                          | ReliefRefi                   | L5,KT                |
      | ULAD   |      02 | EDSTestData-02.xlsx | EDSTestData-ULAD |           1 | HVEFeedback     | Refinance       | StreamlinedRefinance      | HVSDataAvailable    | Eligible                              | Eligible                     | B1,WH                |
