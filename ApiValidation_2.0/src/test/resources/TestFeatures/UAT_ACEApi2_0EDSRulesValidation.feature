@Iteration02 @ACE2_0UATREGRESSION @B-112989 @113223 @EDSRules
Feature: Ace Api 2.0 Response Schema Validation

  Scenario Outline: User sends a valid ace api 2.0 request and receives a stubbed response
    Given User sends "UAT" ACE API 2.0 request  "<loanFileName>" and validates response code "200"
    Then User sees the expected "<elementValue1>" value in the ACE 2.0 response

    Examples: 
      | loanFileName                     | elementValue1            |
      | EDSRules_Accept                  | NotLPAAccept |
      | EDSRules_Financed                | NotSFOneUnit             |
      | EDSRules_Conventional            | NotConventional          |
      | EDSRules_ConformingJumboMortgage | NonConforming            |
      | EDSRules_Manufactured            | Manufactured             |
      | EDSRules_Cooperative             | Cooperative              |
      | EDSRules_Leasehold               | Leasehold                |
      | EDSRules_TLTVarianceLimit        | TLTVVarianceLimit        |
      | EDSRules_Investment              | Investment               |
      | EDSRules_ReliefRefinance         | ReliefRefi               |
      | EDSRules_CashOut                 | RefiCashOut              |
      | EDSRules_AppraisalID             | DocFileID                |
