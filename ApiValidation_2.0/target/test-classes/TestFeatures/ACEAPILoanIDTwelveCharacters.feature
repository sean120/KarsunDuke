@RegressionSIT
Feature: ACE API: Set limit up to 12 characters on Loan Identifier to EDS

  Scenario Outline: As ACE API User, I want the value for the element Loan Identifier to be less than 13 characters
    Given EDS "<user_name>" and "<password>" combinations to get ACE Access Token at "<accesstoken>"
    And APIGEE For ACE API and a Valid "<RequestFile>"
    When Sending our request to ACE API with the valid access token
    Then Get our ACE API response
    Then Check MongoDB for our EDS Request
    Then save EDS Request in our file "<edsrequestfile>"
    Then Check for element characters, according to acceptance criteria in "<edsrequestfile>"
      | xpath                                                                                                                                                                                                    | value |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[1]/LoanDetailContainer/LoanApplications/LoanApplicationContainer/LoanApplication/LoanApplicationIdentifier |       |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[2]/LoanDetailContainer/LoanApplications/LoanApplicationContainer/LoanApplication/LoanApplicationIdentifier |       |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanPropertyCollaterals/LoanPropertyCollateralContainer/LoanPropertyCollateral/LoanIdentifier                                            |       |
      #| //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanPropertyCollaterals/LoanPropertyCollateralContainer/LoanDetailContainer/Loan/LoanIdentifier                                       |       |
      #| //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer/LoanDetailContainer/LoanAcquisitionContainer/LoanAcquisition/SellerLoanIdentifier                       |       |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[1]/LoanDetailContainer/LoanOriginationContainer/LoanOrigination/LoanIdentifier                             |       |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[2]/LoanDetailContainer/LoanOriginationContainer/LoanOrigination/LoanIdentifier                             |       |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[1]/LoanDetailContainer/Loan/LoanIdentifier                                                                 |       |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[2]/LoanDetailContainer/Loan/LoanIdentifier                                                                 |       |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[1]/LoanDetailContainer/LoanApplications/LoanApplicationContainer/LoanApplication/LoanIdentifier            |       |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[2]/LoanDetailContainer/LoanApplications/LoanApplicationContainer/LoanApplication/LoanIdentifier            |       |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[1]/LoanDetailContainer/LoanApplications/LoanApplicationContainer/LoanApplication/LoanApplicationIdentifier |       |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/LoanStates/LoanStateContainer[2]/LoanDetailContainer/LoanApplications/LoanApplicationContainer/LoanApplication/LoanApplicationIdentifier |       |
    #| //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/CommunicationFailureDerivedContainer/HVSCommunicationFailureDerivation/LoanIdentifier                                                 |       |
    Then Check for Unique ID element characters, according to acceptance criteria in "<edsrequestfile>"
      | xpath                                                                                                                                                                                                                     | value |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyValuationContainer/PropertyValuation/PropertyValuationSequenceNumber                              |       |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/Properties/PropertyContainer/PropertyValuations/PropertyAppraiserValuationContainer/PropertyValuationAssessment/PropertyValuationAssessmentSequenceNumber |       |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecisionResultContainer/BusinessDecisionResult/BusinessDecisionResultIdentifier                       |       |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecisionResultContainer/BusinessDecisionResult/BusinessDecisionIdentifier                             |       |
      | //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecision/BusinessDecisionIdentifier                                                                   |       |

    #| //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecisionResultContainer/PropertyInspectionWaiverResult/BusinessDecisionIdentifier                     |       |
    #| //EnterpriseDecisionService/LoanDeliveryStructure/LoanContainer/BusinessDecisions/BusinessDecisionContainer/BusinessDecisionResultContainer/PropertyInspectionWaiverResult/BusinessDecisionResultIdentifier               |       |
    Examples: 
      | user_name             | password             | accesstoken    | RequestFile                    | edsrequestfile    |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | inputfile9.json                | edsapirequest.xml |
#      | wfbmn_lasapisys2sys5  | LCH?ff4JObM#RZ5@M%2[ | $.access_token | AnotherEligibleSITRequest.json | edsapirequest.xml |
      | peb_lasapisys2sys     | PeEbpv~0R@$8gYux(UaF | $.access_token | inputfile12.json 			   | edsapirequest.xml |
