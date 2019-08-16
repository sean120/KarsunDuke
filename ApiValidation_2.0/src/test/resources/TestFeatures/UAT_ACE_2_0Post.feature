Feature: Submit ACE2.0 request and validate response code

  Scenario Outline: User sends ACE 2.0 request and validtes EDS request mapping
    Given User sends "UAT" ACE API 2.0 request  "<loanFileName>" and validates response code "200"
   
   Then User retrieves first record from "ACEFullAssessmentRequests" Ace Requests in the database
    
    Then User retrieves "REQUEST" for "EDS" from UAT Mongo DB
    Then Validate HVS Response to EDS Request Mapping

    Examples: 
      | loanFileName                   |
      | 017_ULADRequest1009CircleDrive |
