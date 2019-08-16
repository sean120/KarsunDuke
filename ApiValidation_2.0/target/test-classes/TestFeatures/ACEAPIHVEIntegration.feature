@RegressionSIT
Feature: ACE API: HVE Integration, B87092

  Scenario Outline: As API User, I want to integrate HVE service with ACE API
    Given HVE "<user_name>" and "<password>" combinations to get ACE API Access Token at "<accesstoken>" path
    And APIGEE Developer Portal For ACE API and a Valid HVE "<RequestFile>"
    When Sending request to ACE API with the access token
    Then Check Mongo DB for HVE request
    Then HVE Request Save code in xml file "<requestxmlfile>"
    Then HVE Request, Verify the value for below xpath elements in "<requestxmlfile>"
      | xpath                                                                             | value                   |
      | //HVSRequest/ServiceRequestContainer/ServiceRequest/ClientSchemaVersionIdentifier |                     1.0 |
      | //HVSRequest/ServiceRequestContainer/ServiceRequest/SchemaVersionIdentifier       |                     1.0 |
      | //HVSRequest/ServiceRequestContainer/ServiceRequest/RequestorIdentifier           | ACEAPI                  |
      | //HVSRequest/ServiceRequestContainer/ServiceRequest/ServiceName                   | HVSWS                   |
      | //HVSRequest/ServiceRequestContainer/ServiceRequest/ServiceRequestDateTime        | YYYY-MM-DDThh:mm:ss.SSS |
      | //HVSRequest/ServiceRequestContainer/ServiceRequest/ServiceRequestOperationName   | GetHVSData              |
      | //HVSRequest/HVSRequestContainers/HVSRequestContainer/RequestType                 |                      03 |
      #| //HVSRequest/ServiceRequestContainer/ServiceRequest/SubscriberRequestCorrelationIdentifier | qewd123                 |
      | //HVSRequest/AccountIdentifier                                                    | lasaceapi               |
    #| //HVSRequest/HVSRequestContainers/HVSRequestContainer/ADDRESS/AddressLineText              | 420 CLEVELAND ST        |
    #| //HVSRequest/HVSRequestContainers/HVSRequestContainer/ADDRESS/CityName                     | ENDERLIN                |
    #| //HVSRequest/HVSRequestContainers/HVSRequestContainer/ADDRESS/FIPSStateAlphaCode           | ND                      |
    #| //HVSRequest/HVSRequestContainers/HVSRequestContainer/ADDRESS/PostalCode                   |                   58027 |
    Then Check Mongo DB for HVE response
    Then HVE Response Save code in xml file "<responsexmlfile>"
    Then HVE Response, Verify the value for below xpath elements in "<responsexmlfile>"
      | xpath                                                                                                                                                             | value                   |
      | HVSResponse/ServiceRequestContainer/ServiceRequest/ClientSchemaVersionIdentifier                                                                                  |                     1.0 |
      | //HVSResponse/ServiceRequestContainer/ServiceRequest/SchemaVersionIdentifier                                                                                      |                     1.0 |
      | //HVSResponse/ServiceRequestContainer/ServiceRequest/RequestorIdentifier                                                                                          | ACEAPI                  |
      | //HVSResponse/ServiceRequestContainer/ServiceRequest/ServiceName                                                                                                  | HVSWS                   |
      | //HVSResponse/ServiceRequestContainer/ServiceRequest/ServiceRequestDateTime                                                                                       | YYYY-MM-DDThh:mm:ss.SSS |
      | //HVSResponse/ServiceRequestContainer/ServiceRequest/ServiceRequestOperationName                                                                                  | GetHVSData              |
      #| //HVSResponse/ServiceRequestContainer/ServiceRequest/ServiceRequestType                                                                                           |                      03 |
      #!| //HVSResponse/ServiceRequestContainer/ServiceRequest/SubscriberRequestCorrelationIdentifier                                                                       |                   12345 |
      #| //HVSResponse/HVSResponseContainers/HVSResponseContainer/AlternativeLoanIdentifications/AlternativeLoanIdentification/LoanIdentifier                              |                         |
      | //HVSResponse/HVSResponseContainers/HVSResponseContainer/AlternativeLoanIdentifications/                                                                          |                         |
      #| //HVSResponse/HVSResponseContainers/HVSResponseContainer/AlternativeLoanIdentifications/AlternativeLoanIdentification/LoanIdentifier                              |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSTransactionIdentifier                                                                                             |                         |
      | //HVSResponseContainers/HVSResponseContainer/BillingType                                                                                                          |                         |
      | //HVSResponseContainers/HVSResponseContainer/FDRVersion                                                                                                           |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/AddressLineText                                                                                 |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/CityName                                                                                        |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/FIPSStateAlphaCode                                                                              |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/PostalCode                                                                                      |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation[1]/AVMConfidenceScoreValue                                                      |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation[1]/AVMIndexType                                                                 |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation[1]/AVMHighValueRangeAmount                                                      |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation[1]/AVMLowValueRangeAmount                                                       |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation[1]/AVMModelNameType                                                             |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation[1]/AVMValueAmount                                                               |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation[1]/HVEStandardDeviationNumber                                                   |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/ActiveMLSListingIndicator              |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/ARMsLengthContractIndicator            |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/AppraisalCompletionAsIsIndicator       |                         |
      | //HVSResponse/HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PropertyConditionIndicator |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/DaysOnMarketIndicator                  |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/DistressedIndicator                    |                         |
      #| //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/HighestBestUseRule                     |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/LegalityIndicator                      |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/MissingDataIndicator                   |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/MultipleListingIndicator               |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/NeighborhoodConformityIndicator        |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PhysicalDeficiencyIndicator            |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PropertyAgeCount                       |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PublicRecordExistenceIndicator         |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/QualityAgeIndicator                    |                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/QualityIndicator                       |                         |

    Examples: 
      | user_name             | password             | RequestFile     | accesstoken    | requestxmlfile    | responsexmlfile    |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | inputfile9.json | $.access_token | hveapirequest.xml | hveapiresponse.xml |

  Scenario Outline: Testing scenario, where we send wrong address data
    Given HVE "<user_name>" and "<password>" combinations to get ACE API Access Token at "<accesstoken>" path
    And APIGEE Developer Portal For ACE API and a Valid HVE "<RequestFile>"
    When Sending request to ACE API with the access token
    #	    Then Check Mongo DB for HVE request
    #	    Then HVE Request Save code in xml file "<requestxmlfile>"
    #	    Then HVE Request, Verify the value for below xpath elements in "<requestxmlfile>"
    Then Check Mongo DB for HVE response
    Then HVE Response Save code in xml file "<responsexmlfile>"
    #Then HVE Response, Verify the value for below xpath elements in "<responsexmlfile>"
    #| //HVSResponseContainers/HVSResponseContainer/Messages/Message/MessageCode |  |
    #| //HVSResponseContainers/HVSResponseContainer/Messages/Message/MessageText |  |
    #| //HVSResponseContainers/HVSResponseContainer/Messages/Message/MessageType |  |
    Then Checking Message Error Code "<MessageCode>" in "<responsexmlfile>"

    Examples: 
      | user_name             | password             | RequestFile     | accesstoken    | requestxmlfile    | responsexmlfile    | MessageCode |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | inputfile7.json | $.access_token | hveapirequest.xml | hveapiresponse.xml |        2210 |

  Scenario Outline: Testing scenario, where we send data with empty fields
    Given HVE "<user_name>" and "<password>" combinations to get ACE API Access Token at "<accesstoken>" path
    And APIGEE Developer Portal For ACE API and a Valid HVE "<RequestFile>"
    Then Sending request with missing data to ACE API with the access token and checking for "<ErrorCode>"

    Examples: 
      | user_name             | password             | RequestFile                | accesstoken    | requestxmlfile    | responsexmlfile    | ErrorCode |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | NoPostalCode.json          | $.access_token | hveapirequest.xml | hveapiresponse.xml |       400 |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | NoCityName.json            | $.access_token | hveapirequest.xml | hveapiresponse.xml |       400 |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | NoLoanPurposeType.json     | $.access_token | hveapirequest.xml | hveapiresponse.xml |       400 |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | NoSalesContractAmount.json | $.access_token | hveapirequest.xml | hveapiresponse.xml |       400 |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | NoPartyroleIdentifier.json | $.access_token | hveapirequest.xml | hveapiresponse.xml |       400 |

#  Scenario Outline: As API User, I want to check scrubb address is working in HVE
#    Given HVE "<user_name>" and "<password>" combinations to get ACE API Access Token at "<accesstoken>" path
#    And APIGEE Developer Portal For ACE API and a Valid HVE "<RequestFile>"
#    When Sending request to ACE API with the access token
#    Then Check Mongo DB for HVE request
#    Then HVE Request Save code in xml file "<requestxmlfile>"
#    Then HVE Request, Verify the value for below xpath elements in "<requestxmlfile>"
#      | xpath                                                                            | value                    |
#      | //HVSRequest/HVSRequestContainers/HVSRequestContainer/ADDRESS/AddressLineText    | 2300 crand cypress drive |
#      | //HVSRequest/HVSRequestContainers/HVSRequestContainer/ADDRESS/CityName           | DALLAS                   |
#      | //HVSRequest/HVSRequestContainers/HVSRequestContainer/ADDRESS/FIPSStateAlphaCode | CO                       |
#      | //HVSRequest/HVSRequestContainers/HVSRequestContainer/ADDRESS/PostalCode         |                    33559 |
#    Then Check Mongo DB for HVE response
#    Then HVE Response Save code in xml file "<responsexmlfile>"
#    Then HVE Response, Verify the value for below xpath elements in "<responsexmlfile>"
#      | xpath                                                                                | value                 |
#      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/AddressLineText    | 2300 GRAND CYPRESS DR |
#      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/CityName           | LUTZ                  |
#      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/FIPSStateAlphaCode | FL                    |
#      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/PostalCode         |                 33559 |
#
#    Examples: 
#      | user_name             | password             | RequestFile             | accesstoken    | requestxmlfile    | responsexmlfile    |
#      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | inputScrubAddress1.json | $.access_token | hveapirequest.xml | hveapiresponse.xml |

  Scenario Outline: As API User, I want to check scrubb address is working in HVE (different address)
    Given HVE "<user_name>" and "<password>" combinations to get ACE API Access Token at "<accesstoken>" path
    And APIGEE Developer Portal For ACE API and a Valid HVE "<RequestFile>"
    When Sending request to ACE API with the access token
    Then Check Mongo DB for HVE request
    Then HVE Request Save code in xml file "<requestxmlfile>"
    Then HVE Request, Verify the value for below xpath elements in "<requestxmlfile>"
      | xpath                                                                            | value                |
      | //HVSRequest/HVSRequestContainers/HVSRequestContainer/ADDRESS/AddressLineText    | 420 cleveland street |
      | //HVSRequest/HVSRequestContainers/HVSRequestContainer/ADDRESS/CityName           | VIRGINIA             |
      | //HVSRequest/HVSRequestContainers/HVSRequestContainer/ADDRESS/FIPSStateAlphaCode | VA                   |
      | //HVSRequest/HVSRequestContainers/HVSRequestContainer/ADDRESS/PostalCode         |                58027 |
    Then Check Mongo DB for HVE response
    Then HVE Response Save code in xml file "<responsexmlfile>"
    Then HVE Response, Verify the value for below xpath elements in "<responsexmlfile>"
      | xpath                                                                                | value            |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/AddressLineText    | 420 CLEVELAND ST |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/CityName           | ENDERLIN         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/FIPSStateAlphaCode | ND               |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/PostalCode         |            58027 |

    Examples: 
      | user_name             | password             | RequestFile             | accesstoken    | requestxmlfile    | responsexmlfile    |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | inputScrubAddress2.json | $.access_token | hveapirequest.xml | hveapiresponse.xml |

  Scenario Outline: Testing scenario, where we send data with Property Estimated Amount higher than expected
    Given HVE "<user_name>" and "<password>" combinations to get ACE API Access Token at "<accesstoken>" path
    And APIGEE Developer Portal For ACE API and a Valid HVE "<RequestFile>"
    Then Sending request with missing data to ACE API with the access token and checking for "<ErrorCode>"

    Examples: 
      | user_name             | password             | RequestFile                           | accesstoken    | requestxmlfile    | responsexmlfile    | ErrorCode |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | OverPropertyEstimatedValueAmount.json | $.access_token | hveapirequest.xml | hveapiresponse.xml |       400 |

  Scenario Outline: Testing scenario, where we send data with 'purchase' instead 'Purchase'
    Given HVE "<user_name>" and "<password>" combinations to get ACE API Access Token at "<accesstoken>" path
    And APIGEE Developer Portal For ACE API and a Valid HVE "<RequestFile>"
    Then Sending request with missing data to ACE API with the access token and checking for "<ErrorCode>"

    Examples: 
      | user_name             | password             | RequestFile        | accesstoken    | requestxmlfile    | responsexmlfile    | ErrorCode |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | purchaseInput.json | $.access_token | hveapirequest.xml | hveapiresponse.xml |       400 |

  Scenario Outline: Testing scenario, where we send data with two containers in 'deals'
    Given HVE "<user_name>" and "<password>" combinations to get ACE API Access Token at "<accesstoken>" path
    And APIGEE Developer Portal For ACE API and a Valid HVE "<RequestFile>"
    Then Sending request with missing data to ACE API with the access token and checking for "<ErrorCode>"

    Examples: 
      | user_name             | password             | RequestFile   | accesstoken    | requestxmlfile    | responsexmlfile    | ErrorCode |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | twoDeals.json | $.access_token | hveapirequest.xml | hveapiresponse.xml |       400 |
