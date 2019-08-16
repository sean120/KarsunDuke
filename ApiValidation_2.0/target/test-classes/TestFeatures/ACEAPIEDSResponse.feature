@RegressionSIT
Feature: ACE API EDS Response with ACE API Response Mapping

  Scenario Outline: Send the ACE decision and associated message to freddiemac approved lender
    Given HVE "<user_name>" and "<password>" combinations to get ACE Access Token at "<accesstoken>" path
    And APIGEE Portal For ACE API and a Valid "<RequestFile>"
    When Sending request to ACE with the access token
    Then Save ACE Response from Mongo
    Then Check MongoDB for HVS Response
    Then save HVS Response in file "<hvsresponsefile>"
    Then Check MongoDB for EDS Response
    Then save EDS Response in file "<edsresponsefile>"
    Then Validate the Eligibility in ACE Response with EDS response file "<edsresponsefile>"
      | xpath                                                                                                                            |
      | //EDSRuleResponse/AnswerContainer/Answer/DecisionContainer/Decision[15]/DecisionValueContainer/DecisionValWrapper[1]/DecisionVal |
    Then Validate the Address in ACE Response with HVE response file "<hvsresponsefile>"
      | xpath                                                                                            |
      | //HVSResponse/HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/AddressLineText    |
      | //HVSResponse/HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/CityName           |
      | //HVSResponse/HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/FIPSStateAlphaCode |
      | //HVSResponse/HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/PostalCode         |
    Then Validate the Message in ACE Response with EDS response file "<edsresponsefile>"
      | xpath                                                                                                                 |
      | //EDSRuleResponse/AnswerContainer/Answer/DecisionContainer/Decision[15]/MessageContainer/MessageWrapper/MessageCode   |
      | //EDSRuleResponse/AnswerContainer/Answer/DecisionContainer/Decision[15]/MessageContainer/MessageWrapper/MessageHeader |
    Then Validate the Loan Amount in ACE Response with EDS response file "<edsresponsefile>"
      | xpath                                                                                                                           |
      | //EDSRuleResponse/AnswerContainer/Answer/DecisionContainer/Decision[6]/DecisionValueContainer/DecisionValWrapper[1]/DecisionVal |
    Then Validate the Expiration Date in ACE Response with EDS response file "<edsresponsefile>"
      | xpath                                                                                                                            |
      | //EDSRuleResponse/AnswerContainer/Answer/DecisionContainer/Decision[16]/DecisionValueContainer/DecisionValWrapper[1]/DecisionVal |

    Examples: 
      | user_name             | password             | accesstoken    | RequestFile     | hvsresponsefile    | edsresponsefile    |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | inputfile9.json | hveapiresponse.xml | edsapiresponse.xml |
