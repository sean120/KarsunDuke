@RegressionSIT @EDSTesting
Feature: ACE API Integration with EDS

  Scenario Outline: ACE API EDS Request is Mapped Properly and Integrated
    Given APIGEE Developer Portal ACE API Valid AccessToken and Valid "<RequestFile>"
    When User Sends request to APIGEE Gateway ACE API
    Then ACE API sends back ACE Response
    Then Check Mongo DB for HVE Response and EDS Request
    Then Validate default mapping values
      | xpath                                                                                                                                                                                                      | value                          |
      | /EnterpriseDecisionService/ServiceRequestContainer/ServiceRequest/ServiceRequestIdentifier                                                                                                                 | EDS must generate UUID         |
      | /EnterpriseDecisionService/ServiceRequestContainer/ServiceRequest/ClientSchemaVersionIdentifier                                                                                                            |                            1.0 |
      | /EnterpriseDecisionService/ServiceRequestContainer/ServiceRequest/EDSSchemaVersionIdentifier                                                                                                               |                            1.0 |
      | /EnterpriseDecisionService/ServiceRequestContainer/ServiceRequest/RequestorIdentifier                                                                                                                      | ACEAPI                         |
      | /EnterpriseDecisionService/ServiceRequestContainer/ServiceRequest/ServiceName                                                                                                                              | EDSAppraisalAlternativeAPP     |
      # | /EnterpriseDecisionService/ServiceRequestContainer/ServiceRequest/ServiceRequestDateTime        | YYYY-MM-DDTHH:MM:SSZ           |
      | /EnterpriseDecisionService/ServiceRequestContainer/ServiceRequest/ServiceRequestOperationName                                                                                                              | evaluateAppraisalAlternative   |
      | /EnterpriseDecisionService/ServiceRequestContainer/ServiceRequest/ServiceRequestType                                                                                                                       | RuleServiceRequest             |
      | /EnterpriseDecisionService/ServiceRequestContainer/ServiceRequest/SubscriberIdentifier                                                                                                                     | ACEAPI                         |
      | /EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanPropertyCollaterals/LoanPropertyCollateralContainer/LoanPropertyCollateral/PropertyIdentifier                                           |                              1 |
      | /EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanPropertyCollaterals/LoanPropertyCollateralContainer/LoanPropertyCollateral/FinancedUnitCount                                            |                              1 |
      | /EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[1]/LoanDetailContainer/LoanRelationships/LoanRelationship/LoanRoleType                                        | SubjectLoan                    |
      # | /EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[1]/LoanState/LoanStateDate                                                                  | YYYY-MM-DDTHH:MM:SSZ         |
      | /EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[1]/LoanState/LoanStateType                                                                                    | Current                        |
      | /EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyValuationContainer/PropertyValuation/PropertyValuationMethodType                    | PropertyPresentValuation       |
      | /EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyValuationContainer/PropertyValuation/PropertyValuationType                          | Subsequent                     |
      | /EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyAssessment/PropertyAssessmentSequenceNumber                   |                              1 |
      | /EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyAssessment/PropertyAssessmentSourceType                   | HomeValueService              |
      | /EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyAssessment/PropertyIdentifier                   |                              1 |
      #  |/EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyValuationAssessment/HomeValueExplorerAssessmentDateTime | YYYY-MM-DDTHH:MM:SSZ |
      #|/EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyValuationAssessment/PropertyValuationEffectiveDateTime |  YYYY-MM-DDTHH:MM:SSZ |
      | /EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyValuationAssessment/HomeValueExplorerOptionType | HVEFeedback                    |
      #|/EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecisionResultContainer/BusinessDecisionResult/BusinessDecisionResultDateTime | YYYY-MM-DDTHH:MM:SSZ |
      | /EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecisionResultContainer/BusinessDecisionResult/BusinessDecisionResultSourceType         | EnterpriseDecisionService      |
      | /EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecisionResultContainer/BusinessDecisionResult/BusinessDecisionResultType               | AppraisalAlternative           |
      | /EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecision/BusinessDecisionEvaluationType                                                 | AppraisalAlternativeEvaluation |
    Then Validate the EDS Request

    Examples: 
      | RequestFile                 |
      | inputfile10.json  			|
#      | PurchaseACEAPIRequest.json  |
#      | RefinanceACEAPIRequest.json |
