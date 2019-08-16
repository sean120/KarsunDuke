@SIT-IT06-ACEAPI20-EDS-AA-Rules-Validation @SIT-IT05 @SIT-ACEAPI20
Feature: ACEAPI2.0 EDS-AA Rules Validation

  Scenario Outline: ACEAPI2.0 EDS-AA Rules Validation - set-1
    When User submits ACEAPI2.0 request "ACEAPI20Request-<Client>-<TMPL_ID>.json"
      | <element1Name>  |
      | <element1Value> |
    Then User verifies the ACEAPI2.0 response values
      | alternateAppraisalEligibilityDecision   | alternateAppraisalReasonList   |
      | <alternateAppraisalEligibilityDecision> | <alternateAppraisalReasonList> |

    Examples: 
      | Client | TMPL_ID | element1Name                    | element1Value           | alternateAppraisalEligibilityDecision | alternateAppraisalReasonList |
      | LQA    | 02-v2   | strategicOfferingCreditRiskType | Accept                  | Eligible                              | Eligible                     |
      | LQA    | 02-v2   | strategicOfferingCreditRiskType | Caution                 | Eligible                              | Eligible                     |
      | LQA    | 02-v2   | appraisalIdentifier             | __NULL_VALUE__          | Eligible                              | Eligible                     |
      | LQA    | 02-v2   | appraisalIdentifier             | abc                     | Not Eligible                          | DocFileID                    |
      | LQA    | 02-v2   | loanPropertyUsageType           | PrimaryResidence        | Eligible                              | Eligible                     |
      | LQA    | 02-v2   | loanPropertyUsageType           | Investment              | Not Eligible                          | Investment                   |
      | LQA    | 02-v2   | propertyEstateType              | FeeSimple               | Eligible                              | Eligible                     |
      | LQA    | 02-v2   | propertyEstateType              | Leasehold               | Not Eligible                          | Leasehold                    |
      | LQA    | 02-v2   | programType                     | HomePossibleMortgage    | Eligible                              | Eligible                     |
      | LQA    | 02-v2   | programType                     | ConstructionConversion  | Not Eligible                          | Construction                 |
      | LQA    | 02-v2   | programType                     | Renovation              | Not Eligible                          | Construction                 |
      | LQA    | 02-v2   | loanConformityType              | ConformingMortgage      | Eligible                              | Eligible                     |
      | LQA    | 02-v2   | loanConformityType              | ConformingJumboMortgage | Not Eligible                          | NonConforming                |
      | LQA    | 02-v2   | loanConformityType              | NonConformingMortgage   | Not Eligible                          | NonConforming                |
      | LQA    | 02-v2   | projectLegalStructureType       | __NULL_VALUE__          | Eligible                              | Eligible                     |
      | LQA    | 02-v2   | projectLegalStructureType       | Condominium             | Eligible                              | Eligible                     |
      | LQA    | 02-v2   | projectLegalStructureType       | Cooperative             | Not Eligible                          | Cooperative                  |
      | LQA    | 02-v2   | financedUnitCount               |                       1 | Eligible                              | Eligible                     |
      | LQA    | 02-v2   | financedUnitCount               |                       2 | Not Eligible                          | NotSFOneUnit                 |
      | LQA    | 02-v2   | mortgageType                    | Conventional            | Eligible                              | Eligible                     |
      | LQA    | 02-v2   | mortgageType                    | Other                   | Not Eligible                          | NotLPAAccept,NotConventional |
      | LQA    | 02-v2   | loanQualityAdvisorResultType    | HighLikelihoodofAccept  | Eligible                              | Eligible                     |
      | LQA    | 02-v2   | loanQualityAdvisorResultType    | HighLikelihoodofCaution | Not Eligible                          | NotLPAAccept,NotLQAAccept    |
      | LQA    | 02-v2   | constructionMethodType          | Other                   | Eligible                              | Eligible                     |
      | LQA    | 02-v2   | constructionMethodType          | Manufactured            | Not Eligible                          | Manufactured                 |
      | ULAD   | 02-v2   | financedUnitCount               |                       1 | Eligible                              | Eligible                     |
      | ULAD   | 02-v2   | financedUnitCount               |                       2 | Not Eligible                          | NotSFOneUnit                 |
      | ULAD   | 02-v2   | mortgageType                    | Conventional            | Eligible                              | Eligible                     |
      | ULAD   | 02-v2   | mortgageType                    | Other                   | Not Eligible                          | NotLQAAccept,NotConventional |
      | ULAD   | 02-v2   | strategicOfferingCreditRiskType | Accept                  | Eligible                              | Eligible                     |
      | ULAD   | 02-v2   | strategicOfferingCreditRiskType | Caution                 | Not Eligible                          | NotLPAAccept,NotLQAAccept    |
      | ULAD   | 02-v2   | appraisalIdentifier             | __NULL_VALUE__          | Eligible                              | Eligible                     |
      | ULAD   | 02-v2   | appraisalIdentifier             | abc                     | Not Eligible                          | DocFileID                    |
      | ULAD   | 02-v2   | loanPropertyUsageType           | PrimaryResidence        | Eligible                              | Eligible                     |
      | ULAD   | 02-v2   | loanPropertyUsageType           | Investment              | Not Eligible                          | Investment                   |
      | ULAD   | 02-v2   | propertyEstateType              | FeeSimple               | Eligible                              | Eligible                     |
      | ULAD   | 02-v2   | propertyEstateType              | Leasehold               | Not Eligible                          | Leasehold                    |
      | ULAD   | 02-v2   | programType                     | HomePossibleMortgage    | Eligible                              | Eligible                     |
      | ULAD   | 02-v2   | programType                     | ConstructionConversion  | Not Eligible                          | Construction                 |
      | ULAD   | 02-v2   | programType                     | Renovation              | Not Eligible                          | Construction                 |
      | ULAD   | 02-v2   | loanConformityType              | ConformingMortgage      | Eligible                              | Eligible                     |
      | ULAD   | 02-v2   | loanConformityType              | ConformingJumboMortgage | Not Eligible                          | NonConforming                |
      | ULAD   | 02-v2   | loanConformityType              | NonConformingMortgage   | Not Eligible                          | NonConforming                |
      | ULAD   | 02-v2   | projectLegalStructureType       | __NULL_VALUE__          | Eligible                              | Eligible                     |
      | ULAD   | 02-v2   | projectLegalStructureType       | Condominium             | Eligible                              | Eligible                     |
      | ULAD   | 02-v2   | projectLegalStructureType       | Cooperative             | Not Eligible                          | Cooperative                  |

  Scenario Outline: ACEAPI2.0 EDS-AA Rules Validation - set-2
    When User submits ACEAPI2.0 request "ACEAPI20Request-<Client>-<TMPL_ID>.json"
      | <element1Name>  | <element2Name>  |
      | <element1Value> | <element2Value> |
    Then User verifies the ACEAPI2.0 response values
      | alternateAppraisalEligibilityDecision   | alternateAppraisalReasonList   |
      | <alternateAppraisalEligibilityDecision> | <alternateAppraisalReasonList> |

    Examples: 
      | Client | TMPL_ID | element1Name                          | element1Value           | element2Name    | element2Value | alternateAppraisalEligibilityDecision | alternateAppraisalReasonList         |
      | LQA    | 02-v2   | loanRefinanceCashOutDeterminationType | NoCashOut               | loanPurposeType | Purchase      | Eligible                              | Eligible                             |
      | LQA    | 02-v2   | loanRefinanceCashOutDeterminationType | CashOut                 | loanPurposeType | Purchase      | Eligible                              | Eligible                             |
      | LQA    | 02-v2   | loanRefinanceCashOutDeterminationType | CashOut                 | loanPurposeType | Refinance     | Not Eligible                          | NotLPAAccept,CashOutRefi,RefiCashOut |
      | LQA    | 02-v2   | refinanceProgramType                  | CashOut                 | loanPurposeType | Refinance     | Eligible                              | Eligible                             |
      | LQA    | 02-v2   | refinanceProgramType                  | StreamlinedRefinance    | loanPurposeType | Refinance     | Eligible                              | Eligible                             |
      | LQA    | 02-v2   | refinanceProgramType                  | TexasEquity             | loanPurposeType | Refinance     | Not Eligible                          | ReliefRefi                           |
      | LQA    | 02-v2   | refinanceProgramType                  | EnhancedReliefRefinance | loanPurposeType | Refinance     | Not Eligible                          | ReliefRefi                           |
      | ULAD   | 02-v2   | loanRefinanceCashOutDeterminationType | NoCashOut               | loanPurposeType | Purchase      | Eligible                              | Eligible                             |
      | ULAD   | 02-v2   | loanRefinanceCashOutDeterminationType | CashOut                 | loanPurposeType | Purchase      | Eligible                              | Eligible                             |
      | ULAD   | 02-v2   | loanRefinanceCashOutDeterminationType | CashOut                 | loanPurposeType | Refinance     | Not Eligible                          | CashOutRefi,NotLQAAccept,RefiCashOut |
      | ULAD   | 02-v2   | refinanceProgramType                  | CashOut                 | loanPurposeType | Refinance     | Eligible                              | Eligible                             |
      | ULAD   | 02-v2   | refinanceProgramType                  | StreamlinedRefinance    | loanPurposeType | Refinance     | Eligible                              | Eligible                             |
      | ULAD   | 02-v2   | refinanceProgramType                  | TexasEquity             | loanPurposeType | Refinance     | Not Eligible                          | ReliefRefi                           |
      | ULAD   | 02-v2   | refinanceProgramType                  | EnhancedReliefRefinance | loanPurposeType | Refinance     | Not Eligible                          | ReliefRefi                           |
