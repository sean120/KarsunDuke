@B-113949  @EDSRESPONSEMAPPING
Feature: EDS Response Mapping

  Scenario Outline: User sends ACE 2.0 request and validates EDS response mapping
    Given User sends "UAT" ACE API 2.0 request  "<loanFileName>" and validates response code "200"
    Then User retrieves first record from "ACEFullAssessmentRequests" Ace Requests in the database
    Then Validate EDS to ACE 2.0 Response Mapping

    Examples: 
      | loanFileName                                |
      | 017_ULADRequest1009CircleDrive              |
      | 017_LQARequest1009CircleDrive               |
      | 016_ULAD14756BerkleeDrive_Refinance         |
      | 016_LQA14576BerkleeDrive_Purchase           |
      | 016_ULAD14756BerkleeDrive_Refinance         |
      | 012_LPAReFinanceACEEligible_LQA             |
      | 012_LPAReFinanceACEEligible_ULAD            |
      | 013_LPARefinanceAceNotEligible_LQA_FINAL    |
      | 013_LPARefinanceAceNotEligible_ULAD         |
      | 015_LPARefi_MO_LQA_205557                   |
      | 015_LPARefi_MO_ULAD                         |
      | 001_LQA10211MagnoliaBlossom                 |
      | 001_ULAD10211MagnoliaBlossom                |
      | 003_LQA1009CircleDrive_Purchase             |
      | 003_ULAD1009CircleDrive_Purchase            |
      | 001_LQA10211MagnoliaBlossomNotEligible      |
      | 001_ULAD10211MagnoliaBlossomNotEligible     |
      | 003_LQA1009CircleDrive_PurchaseNotEligible  |
      | 003_ULAD1009CircleDrive_PurchaseNotEligible |
