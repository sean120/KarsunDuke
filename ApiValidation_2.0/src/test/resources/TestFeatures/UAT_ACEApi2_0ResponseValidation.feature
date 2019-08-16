@Iteration02 @ACE2_0Iteration03 @B-112989 @113223 @113224 @ACE2_0UATREGRESSION @ResponseValidation
Feature: ACE 2.0 Response Validation

  Scenario Outline: User sends a valid ace api 2.0 request and validates that the response matches expected LPA response data
    Given User sends "UAT" ACE API 2.0 request  "<loanFileName>" and validates response code "200"
    Then User compares ACE2.0 response to LPA EDS "<edsResponseFile>" response

    Examples: 
      | loanFileName                                | edsResponseFile                                       |
      | 017_ULADRequest1009CircleDrive              | 017_LPAEDSResponse1009CircleDrive                     |
      #| 017_LQARequest1009CircleDrive               | 017_LPAEDSResponse1009CircleDrive                     |
      #| 016_ULAD14756BerkleeDrive_Refinance         | 016_LPAEDSResponse14756BerkleeDrive                   |
      #| 016_LQA14576BerkleeDrive_Purchase           | 016_LPAEDSResponse14756BerkleeDrive                   |
      #| 016_ULAD14756BerkleeDrive_Refinance         | 016_LPAEDSResponse14756BerkleeDrive                   |
      #| 012_LPAReFinanceACEEligible_LQA             | 012_LPAEDSResponseRefinanceEligible                   |
      #| 012_LPAReFinanceACEEligible_ULAD            | 012_LPAEDSResponseRefinanceEligible                   |
      #| 013_LPARefinanceAceNotEligible_LQA_FINAL    | 013_LPAEDSResponseRefinanceNotEligible                |
      #| 013_LPARefinanceAceNotEligible_ULAD         | 013_LPAEDSResponseRefinanceNotEligible                |
      #| 015_LPARefi_MO_LQA_205557                   | 015_LPAEDSResponseRefi_MO                             |
      #| 015_LPARefi_MO_ULAD                         | 015_LPAEDSResponseRefi_MO                             |
      #| 001_LQA10211MagnoliaBlossom                 | 001_LPAEDSResponse10211MagnoliaBlossom                |
      #| 001_ULAD10211MagnoliaBlossom                | 001_LPAEDSResponse10211MagnoliaBlossom                |
      #| 003_LQA1009CircleDrive_Purchase             | 003_LPAEDSResponse1009CircleDrive_Purchase            |
      #| 003_ULAD1009CircleDrive_Purchase            | 003_LPAEDSResponse1009CircleDrive_Purchase            |
      #| 001_LQA10211MagnoliaBlossomNotEligible      | 001_LPAEDSResponse10211MagnoliaBlossomNotEligible     |
      #| 001_ULAD10211MagnoliaBlossomNotEligible     | 001_LPAEDSResponse10211MagnoliaBlossomNotEligible     |
      #| 003_LQA1009CircleDrive_PurchaseNotEligible  | 003_LPAEDSResponse1009CircleDrive_PurchaseNotEligible |
      #| 003_ULAD1009CircleDrive_PurchaseNotEligible | 003_LPAEDSResponse1009CircleDrive_PurchaseNotEligible |
