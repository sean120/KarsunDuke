@Iteration04 @ACE2_0UATREGRESSION @B-112494 @PRESENTVALUE
Feature: ACE 2.0 Validating GFS Present Value

  @CLEARPV
  Scenario: User clears grandfathered present value
    Then User deletes "grandFatheringPresentValueData" data that matches the "1009 CIRCLE DR" address in the database
   
  Scenario: User sends two different present value requests and validates that he always receives the original present value response
    Given User sends "UAT" ACE API 2.0 request  "017_ULADRequest1009CircleDrive_135000PV" and validates response code "200"
    Given User sends "UAT" ACE API 2.0 request  "017_ULADRequest1009CircleDrive_135000PV" and validates response code "200"
    Then User retrieves first record from the "grandFatheringPresentValueResponse" collection in the database
    Then Validate that the grandfathered element "propertyValuationType" equals "Subsequent" in the "grandFatheringPresentValueResponse"  Database collection
	  Then Validate that the grandfathered element "propertyValuationAmount" equals "135000.0" in the "grandFatheringPresentValueResponse"  Database collection  
	  Given User sends "UAT" ACE API 2.0 request  "017_ULADRequest1009CircleDrive" and validates response code "200"
    Then User retrieves first record from the "grandFatheringPresentValueResponse" collection in the database
    Then Validate that the grandfathered element "propertyValuationType" equals "Subsequent" in the "grandFatheringPresentValueResponse"  Database collection
	  Then Validate that the grandfathered element "propertyValuationAmount" equals "135000.0" in the "grandFatheringPresentValueResponse"  Database collection
