@End_END @Iteration-06 @EndRegression @ACE2233
Feature: Ace Api 2.0 Endpoint Url

  Scenario: 001 Validating LPA and ULAD GFS logic for grandfathered addresses in ace 1.0
    
    Then User deletes "grandFatheringAceData" data that matches the "14576 BERKLEE DR" address in the database
	@titleRequest
  Scenario: 002 Send ace 1.0 request for berklee driver
    Given User sets APIGEE Portal ACE API Access Token request "UatAPIGEEAccessTokenBaseUrl" url
    And content type is "application/x-www-form-urlencoded" for APIGEE Developer Portal
    And passing granttype "password" parameter
    And passing clientid "LnJ8T4am6A7oGneoPWbREox81mb8TK9g" parameter
    And passing clientsecret "F8DvQeQAEcQb0Zb6" parameter
    And passing username "webslr1_lasapisys2sys" parameter
    And passing pwd "Yki58s%wU0W]@unLfgXM" parameter
    When Request is performing "POST" on APIGEE Gateway
    Then Service sends back Access Token
    Then Store the "$.access_token" in ACE API Local
    Given APIGEE Developer Portal ACE API AccessToken and "ACE1_014576BerkleeDr.json"
    And content type is "application/json" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "UatAPIGEEAccessTokenBaseUrl" url
    Given User sets UAT resource path
    When User sends "POST" request to ACE API through APIGEE
    Then Service sends back ResponseCode
    Then User retreives "grandFatheringAceData" data that matches the "14576 BERKLEE DR" address in the database
    Then Return initial time stamp

  #Scenario: 003Send ULAD request for berklee driver
    #Given User sends "UAT" ACE API 2.0 request  "012_LPARefinanceAceEligible_ULAD" and validates response code "200"

 Scenario: 004Validate GFS reuse and timestamp from 1.0
    Then User retreives "grandFatheringAceData" data that matches the "14576 BERKLEE DR" address in the database
    Then Validate the expected time stamp
    Then Validate that the grandfathered element "addressLineText" equals "14576 BERKLEE DR" in the "grandFatheringAceData"  Database collection
    Then Validate that the grandfathered element "alternateAppraisalDecisionStatusType" equals "Reuse" in the "grandFatheringAceData"  Database collection

  Scenario: 005User sends LQA file with the same Berklee Address but should see current for the flag
    Given User sends "UAT" ACE API 2.0 request  "016_LQA14576BerkleeDrive_Purchase" and validates response code "200"
    Then User retreives "grandFatheringAceData" data that matches the "14576 BERKLEE DR" address in the database
    Then Validate that the grandfathered element "addressLineText" equals "14576 BERKLEE DR" in the "grandFatheringAceData"  Database collection
    Then User sees the expected "Current" value in the ACE 2.0 response

 Scenario: 006 User Send Ace2.0  LQA File with with Eds Rules

  Scenario Outline: User sends a valid ace api 2.0 request and receives a stubbed response
    Given User sends "UAT" ACE API 2.0 request  "<loanFileName>" and validates response code "200"
    Then User sees the expected "<elementValue1>" value in the ACE 2.0 response

    Examples: 
      | loanFileName                     | elementValue1     |
      | EDSRules_Accept                  | NotLPAAccept      |
      | EDSRules_Financed                | NotSFOneUnit      |
      | EDSRules_Conventional            | NotConventional   |
      | EDSRules_ConformingJumboMortgage | NonConforming     |
      | EDSRules_Manufactured            | Manufactured      |
      | EDSRules_Cooperative             | Cooperative       |
      | EDSRules_Leasehold               | Leasehold         |
      | EDSRules_TLTVarianceLimit        | TLTVVarianceLimit |
      | EDSRules_Investment              | Investment        |
      | EDSRules_ReliefRefinance         | ReliefRefi        |
      | EDSRules_CashOut                 | RefiCashOut       |
      | EDSRules_AppraisalID             | DocFileID         |


 		Scenario: User clears grandfathered present value
    Then User deletes "grandFatheringPresentValueData" data that matches the "1009 CIRCLE DR" address in the database

  	Scenario: User sends two different present value requests and validates that he always receives the original present value response
    Given User sends "UAT" ACE API 2.0 request  "017_ULADRequest1009CircleDrive_135000PV" and validates response code "200"
    Given User sends "UAT" ACE API 2.0 request  "017_ULADRequest1009CircleDrive_135000PV" and validates response code "200"
    Then User retrieves first record from the "grandFatheringPresentValueResponse" collection in the database
    Then Validate that the grandfathered element "propertyValuationType" equals "Subsequent" in the "grandFatheringPresentValueResponse"  Database collection
    Then Validate that the grandfathered element "propertyValuationAmount" equals "135000.0" in the "grandFatheringPresentValueResponse"  Database collection

  Scenario: 008 User send GFS getrequest and validates GFS Get REquest Mapped Correctly
  Scenario: User sends a valid ace api 2.0 request and the data should be mapped correctly to a GFS get request
    Given User sends "UAT" ACE API 2.0 request  "017_ULADRequest1009CircleDrive" and validates response code "200"
    Then User retreives ace "grandFatheringAceRequest" data that matches the "1009 CIRCLE DR" address in the database

  Scenario Outline: User sends a valid ace api 2.0 request and the data should be mapped correctly to a GFS get request
    Then Validate that the grandfathered element "<GFSElement>" equals "<GFSValue>" in the "grandFatheringAceRequest"  Database collection

    Examples: 
      | GFSElement            | GFSValue                                        |
      | sourceApplicationName | LPAv2                                           |
      | loanIdentifier1       | DIMA                                            |
      | loanIdentifierType1   | LoanProspectorKey                               |
      | loanIdentifier2       |                                           57524 |
      | loanIdentifierType2   | LoanProspectorUniqueLoan                        |
      | loanIdentifier3       |                                        50109018 |
      | loanIdentifierType3   | LoanProspectorTransaction                       |
      | loanIdentifier4       |                                       300512420 |
      | loanIdentifierType4   | ExtendedHewlettPackardLoanProspectorTransaction |
      | loanIdentifier5       |                                          512420 |
      | loanIdentifierType5   | HewlettPackardLoanProspectorTransaction         |
      | addressLineText       | 1009 CIRCLE DR                                  |
      | postalCode            |                                           50002 |

 