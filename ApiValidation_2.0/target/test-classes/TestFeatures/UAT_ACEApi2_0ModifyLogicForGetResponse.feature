@Iteration04 @ACE2_0UATREGRESSION @B-112494 @GFSHVEFEEDBACK
Feature: ACE 2.0 GFS Current Reuse Flags

@CLEARGFSDATABASE
  Scenario: Clear GFS database
    Then User deletes "grandFatheringAceData" data that matches the "1009 CIRCLE DR" address in the database
    
    
    
  Scenario: User sends back to back requests with the same address and lpkey, then user should see the data is grandfathered
    Given ACEAPI2.0 request payload in the file "017_ULADRequest1009CircleDrive.json"
    Then User retrieves first record from the "grandFatheringAceData" collection in the database
    Then Return initial time stamp
   # Then Validate that the grandfathered element "alternateAppraisalDecisionStatusType" equals "Current" in the "grandFatheringAceData"  Database collection
    Then Validate that the grandfathered element "homeValueExplorerOptionType" equals "HVEFeedback" in the "grandFatheringAceData"  Database collection
    Given ACEAPI2.0 request payload in the file "017_ULADRequest1009CircleDrive.json"
    Then User retrieves first record from the "grandFatheringAceData" collection in the database
    Then Validate the expected time stamp
    Then Validate that the grandfathered element "alternateAppraisalDecisionStatusType" equals "Reuse" in the "grandFatheringAceData"  Database collection
    Then Validate that the grandfathered element "homeValueExplorerOptionType" equals "HVEFeedback" in the "grandFatheringAceData"  Database collection

 