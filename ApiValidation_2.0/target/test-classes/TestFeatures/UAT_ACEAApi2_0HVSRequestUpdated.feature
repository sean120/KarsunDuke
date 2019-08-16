@UATREGRESSION @B-113949 @HVSUPDATED
Feature: 001 HVS request mapping

  Scenario Outline: User sends a valid ace api 2.0 request and and retrieves the HVS request from the database
    Given User sends "UAT" ACE API 2.0 request  "<loanFileName>" and validates response code "200"
    Then User retrieves first record from "ACEFullAssessmentRequests" Ace Requests in the database
    Then User retrieves "REQUEST" for "HVS" from UAT Mongo DB
    Then Validate ACE 2.0 to "HVS" Request Mapping

    Examples: 
      | loanFileName                          |
      | 013_LPARefinanceAceNotEligible_205557 |
      | UpdatedRequest3_28_19                 |
