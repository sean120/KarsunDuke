@Iteration02 @Iteration03 @UATREGRESSION @B-112989 @113223 @EndtoEndGFS @CHANGE
Feature: Ace Api 2.0 Endpoint Url


  Scenario: 001 Validated LPA and ULAD grandfathering logic for new and resubmitted files
  #001Clear grandfathered data
    Then User deletes "grandFatheringAceData" data that matches the "14576 BERKLEE DR" address in the database
  
  #002Upload LPA Loan File for Berklee driver 
    Given Login the LPA application
    And User uploads loan application "016_StateCode_TX_Refi" in the LPA system
     Then User closes and exits the browser
    
   #003Validated GFS data current
    Then User retreives "grandFatheringAceData" data that matches the "14576 BERKLEE DR" address in the database
    Then Validate that the grandfathered element "alternateAppraisalDecisionStatusType" equals "Current" in the "grandFatheringAceData"  Database collection
   
   #004Upload LPA Loan File for Berklee driver  
    Given Login the LPA application
    And User uploads loan application "016_StateCode_TX_Refi" in the LPA system
     Then User closes and exits the browser
    
    #005Validated GFS data reuse
    Then User retreives "grandFatheringAceData" data that matches the "14576 BERKLEE DR" address in the database
    Then Validate that the grandfathered element "alternateAppraisalDecisionStatusType" equals "Reuse" in the "grandFatheringAceData"  Database collection
    
    #006Send Ace 2.0 request for berklee drive
    Given User sends "UAT" ACE API 2.0 request  "016_ULAD14756BerkleeDrive_Refinance" and validates response code "200"
   
    #007Validate current in GFS database
    Then User retreives "grandFatheringAceData" data that matches the "14576 BERKLEE DR" address in the database
    Then Validate that the grandfathered element "alternateAppraisalDecisionStatusType" equals "Current" in the "grandFatheringAceData"  Database collection
   
    #008Send Ace 2.0 request for berklee drive
    Given User sends "UAT" ACE API 2.0 request  "016_ULAD14756BerkleeDrive_Refinance" and validates response code "200"
    
    #009Validate reuse in GFS database
    Then User retreives "grandFatheringAceData" data that matches the "14576 BERKLEE DR" address in the database
    Then Validate that the grandfathered element "alternateAppraisalDecisionStatusType" equals "Reuse" in the "grandFatheringAceData"  Database collection
   
   @Scenario02
  Scenario: 002 Validating LPA, LQA, and ULAD GFS logic for grandfathered addresses in ace 1.0
  #010Clear grandfathered database for Berklee driver
   Then User deletes "grandFatheringAceData" data that matches the "14576 BERKLEE DR" address in the database
    
    #011Send ace 1.0 request for berklee driver
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
    
    #012Send ULAD request for berklee driver
    Given User sends "UAT" ACE API 2.0 request  "016_ULAD14756BerkleeDrive_Refinance" and validates response code "200"
    
    #013Validate GFS reuse and timestamp from 1.0
    Then User retreives "grandFatheringAceData" data that matches the "14576 BERKLEE DR" address in the database
    Then Validate the expected time stamp
    Then Validate that the grandfathered element "addressLineText" equals "14576 BERKLEE DR" in the "grandFatheringAceData"  Database collection
		Then Validate that the grandfathered element "alternateAppraisalDecisionStatusType" equals "Reuse" in the "grandFatheringAceData"  Database collection

		#014Send LPA loan file for berklee driver
    Given Login the LPA application
    And User uploads loan application "016_StateCode_TX_Refi" in the LPA system
     Then User closes and exits the browser
    Then User retreives "grandFatheringAceData" data that matches the "14576 BERKLEE DR" address in the database
#    Then User retrieves first record from the "grandFatheringAceData" collection in the database
    
    #015Validate GFS reuse and timestamp from 1.0
    Then Validate the expected time stamp
    Then Validate that the grandfathered element "addressLineText" equals "14576 BERKLEE DR" in the "grandFatheringAceData"  Database collection
		Then Validate that the grandfathered element "alternateAppraisalDecisionStatusType" equals "Reuse" in the "grandFatheringAceData"  Database collection
    
    #016User sends LQA file with the same Berklee Address but should see current for the reuse flag
    Given User sends "UAT" ACE API 2.0 request  "016_LQA14576BerkleeDrive_Purchase" and validates response code "200"
    Then User retreives "grandFatheringAceData" data that matches the "14576 BERKLEE DR" address in the database
    Then Validate that the grandfathered element "addressLineText" equals "14576 BERKLEE DR" in the "grandFatheringAceData"  Database collection
		 Then User sees the expected "Current" value in the ACE 2.0 response

    
    Scenario: 003 Validating disaster address elgibility in ace api 1.0, ace api 2.0 and LPA
		#017User sends ace api 1.0 eligible disaster address
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
    Given APIGEE Developer Portal ACE API AccessToken and "DisasterACE1_0.json"
    And content type is "application/json" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "UatAPIGEEAccessTokenBaseUrl" url
    Given User sets UAT resource path
    When User sends "POST" request to ACE API through APIGEE
    Then Service sends back ResponseCode
    
    #018Validate ace eligible
 		Then User sees the expected "Eligible" value in the APIGEE ace response
    
		#019User sends ace 2.0 disaster address
    Given User sends "UAT" ACE API 2.0 request  "DisasterACE2_0" and validates response code "200"
    
    #020Validate not eligible
    Then User sees the expected "Not Eligible" value in the ACE 2.0 response
    Then User sees the expected "Disaster" value in the ACE 2.0 response
 #   Then User sees the expected "Disaster error code" value in the ACE 2.0 response
    
    #021User sends lpa disaster loan files
    Given Login the LPA application
    And User uploads loan application "015_StateCode_MO_Refi_Disaster" in the LPA system
    
    #022User validates not eligible
    Then User validates Loan Not Ace Eligible
     Then User closes and exits the browser
    
  