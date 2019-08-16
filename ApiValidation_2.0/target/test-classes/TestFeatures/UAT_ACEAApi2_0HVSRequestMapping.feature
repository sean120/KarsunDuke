@B-113949 @ACE2_0UATREGRESSION @HVSREQUESTMAPPING
Feature: HVS Request Mapping

  Scenario Outline: User sends ACE 2.0 request and validtes HVS request mapping
    When User submits ACEAPI2.0 request "ACEAPI20Request-<Client>-<TMPL_ID>.json"
    Then User retrieves first record from "ACEFullAssessmentRequests" Ace Requests in the database
    Then User retrieves "REQUEST" for "HVS" from UAT Mongo DB
    Then Validate ACE 2.0 to "HVS" Request Mapping

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
