@Iteration03 @UATREGRESSION @B-112488 @SeanEdsRequest
Feature: Mapping Ace 2.0 reuqest to EDS request

  Scenario: User sends a valid ace api 2.0 request and grabs the EDS request from the database
    Given User sends "UAT" ACE API 2.0 request  "" and validates response code "200"
    Then User retrieves "REQUEST" for "EDS" from UAT Mongo DB

  Scenario Outline: User goes into the mongo database and validates that the EDS request was mapped correctly from the ACE 2.0 request
    Then Validate "REQUEST" "EDS" element "<elementName1>" with value "<elementValue1>" from Mongo

    Examples: 
      | elementName1                                   | elementValue1                          |
      | AddressLineText                                | 14576 BERKLEE DR                   |
    #  | AddressUnitDesignatorType                      | AddressUnitDesignatorType              |
      #| AddressUnitIdentifier                          | AddressUnitIdentifier                  |
      #| HighwayContractIdentifier                      | HighwayContractIdentifier              |
      #| PostalCode                                     |                                  75001|
      #| PostOfficeBoxIdentifier                        | PostOfficeBoxIdentifier                |
      #| RuralRouteIdentifier                           | RuralRouteIdentifier                   |
      #| FIPSStateAlphaCode                             | TX                                     |
      #| StreetName                                     | StreetName                             |
      #| StreetPostDirectionalText                      | StreetPostDirectionalText              |
      #| StreetPreDirectionalText                       | StreetPreDirectionalText               |
      #| StreetPrimaryNumberText                        | StreetPrimaryNumberText                |
      #| StreetSuffixText                               | StreetSuffixText                       |
      #| ZipPlusFourCode                                |                                   4403 |
      #| AlternateLoanIdentifier1                       |  205550                                |
      #| AlternateLoanIdentifier2                       |   583586                               |
      #| AlternateLoanIdentifier3                       |   4631                                |
      #| AlternateLoanIdentifier4                       |     300583586                         |
      #| AlternateLoanIdentifier5                       |00000000-0000-0000-0000-000000000000|
      #| AlternateLoanIdentifier6                       | A0052789                              |
      #| AlternateLoanIdentifier7                       | AlternateLoanIdentifier7                       |
      #| AppraisalIdentifier                            | AppraisalIdentifier                            |
      #| DerivationRiskClassType                        | PreOverrideRiskClass                   |
      #| DerivedCalculationSourceType                   | HP                                     |
      #| LoanQualityAdvisorResultType                   | LoanQualityAdvisorResultType                   |
      #| StrategicOfferingCreditRiskType                | Accept                                 |
      #| EvaluationResultMessageCode                    | EvaluationResultMessageCode                    |
      #| EvaluationResultMessageText                    | EvaluationResultMessageText                    |
      # | ConstructionLoanIndicator                      | ConstructionLoanIndicator                      |
      #| LoanProcessingStageType                        | LoanProcessingStageType                |
      #| LoanPurposeType                                | Refinance                              |
      #| LoanRefinanceCashOutDeterminationType          | NoCashOut                              |
      #| MortgageType                                   | Conventional                           |
      #| LoanConformityType                             | ConformingMortgage                     |
      #| SellerAccountIdentifier                        |                                 000601 |
      #| SellerLoanIdentifier                           | B_16394_LPR_JW_                        |
      #| BorrowerPropertyPurchasePriceAmount            | BorrowerPropertyPurchasePriceAmount    |
      #| LoanApplicationBaseLoanAmount                  |                                90000.0 |
      #| MIAndFundingFeeFinancedAmount                  | MIAndFundingFeeFinancedAmount          |
      #| LoanScopeType                                  | SubjectLoan                            |
      #| LTVRatioPercent                                |                                     60 |
      #| LTVSourceType                                  | FreddieMacCalculated                   |
      # | ConstructionLoanType                           | ConstructionLoanType                           |
      # | MIPremiumFinancedAmount                        | MIPremiumFinancedAmount                        |
      # | InvestorCollateralProgramType                  | InvestorCollateralProgramType                  |
      #| LoanOriginationSystemVersionIdentifier         | LoanOriginationSystemVersionIdentifier |
      #| LoanPropertyUsageType                          | SecondHome                             |
      #| NoteAmount                                     |                                  90000 |
      #| FinancedUnitCount                              |                                      1 |
      #| AutomatedUnderwritingSystemType                | LoanProspector                         |
      #| LoanUnderwritingCaseIdentifier                 | A0052791                               |
      #| LoanUnderwritingDecisionDefaultProbabilityRate |                           0.0011466796 |
      # | ProductDescription                             | ProductDescription                             |
      # | ProductIdentifier                              | ProductIdentifier                              |
      # | ProductType                                    | ProductType                                    |
      #| ProgramType                                    | ProgramType                            |
      #| RefinanceProgramType                           | RefinanceProgramType                   |
      #| IntentToOccupyType                             | NO                                     |
      #| PropertyCategoryType                           | PropertyCategoryType                   |
      #| PropertyEstateType                             | FeeSimple                              |
      #| PropertyValuationAmount                        | PropertyValuationAmount                |
      #| PropertyValuationEffectiveDateTime             | PropertyValuationEffectiveDateTime     |
      #| PropertyValuationMethodType                    | PropertyValuationMethodType            |
      #| PropertyValuationType                          | PropertyValuationType                  |
      #| RDSCommunicationFailureType                    | RDSCommunicationFailureType            |
      #| ProjectAttachmentType                          | ProjectAttachmentType                  |
      #| ProjectLegalStructureType                      | ProjectLegalStructureType              |
      #| ServiceName                                    | ServiceName                            |
      #| ServiceRequestOperationName                    | ServiceRequestOperationName            |
      #| SubscriberIdentifier                           |                                      1 |
      #| SubscriberRequestCorrelationIdentifier         | SubscriberRequestCorrelationIdentifier |
      #| ConstructionMethodType                         | Other                                  |
