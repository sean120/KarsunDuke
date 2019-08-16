@Iteration
Feature: API HVE Payload Request Response DB Validation
  
  @Iteration1gouri
  Scenario Outline: To verify the API HVE service with Payload servicer request
    Given HVE Read input from json "<inputfile>" with "<user_name>" and "<proxymessageid>" as header
    When HVE Request is performing "<operation>" on HVE API URL
    And HVE Save response code in xml "<inputrequestfile>"
    Then HVE Verify the value for below xpath elements in "<inputrequestfile>"
      | xpath                                                                                      | value                   |
      | //HVSRequest/ServiceRequestContainer/ServiceRequest/ClientSchemaVersionIdentifier          |                     1.0 |
      | //HVSRequest/ServiceRequestContainer/ServiceRequest/SchemaVersionIdentifier                |                     1.0 |
      | //HVSRequest/ServiceRequestContainer/ServiceRequest/RequestorIdentifier                    | ACEAPI                  |
      | //HVSRequest/ServiceRequestContainer/ServiceRequest/ServiceName                            | HVSWS                   |
      | //HVSRequest/ServiceRequestContainer/ServiceRequest/ServiceRequestDateTime                 | YYYY-MM-DDThh:mm:ss.SSS |
      | //HVSRequest/ServiceRequestContainer/ServiceRequest/ServiceRequestOperationName            | GetHVSData              |
      | //HVSRequest/ServiceRequestContainer/ServiceRequest/SubscriberRequestCorrelationIdentifier | qewd123                 |
      | //HVSRequest/AccountIdentifier                                                             | acehvssvc               |
      | //HVSRequest/HVSRequestContainers/HVSRequestContainer/ADDRESS/AddressLineText              | 123 Main Street         |
      | //HVSRequest/HVSRequestContainers/HVSRequestContainer/ADDRESS/CityName                     | LalaLand                |
      | //HVSRequest/HVSRequestContainers/HVSRequestContainer/ADDRESS/FIPSStateAlphaCode           | VA                      |
      | //HVSRequest/HVSRequestContainers/HVSRequestContainer/ADDRESS/PostalCode                   |                   12345 |

    Examples: 
      | inputfile      | inputrequestfile  | operation | user_name       | proxymessageid |
      | inputfile.json | hveapirequest.xml | POST      | webslr1_wksmith | qewd123        |

  @Iteration1
  Scenario Outline: To verify the API HVE service with Payload Response in DB
    Given HVE Response Read input from json "<inputfile>" with "<user_name>" and "<proxymessageid>" as header
    When HVE Response Request is performing "<operation>" on API URL
    And HVE Response Save response code in xml "<inputrequestfile>"
    Then HVE Response Verify the value for below xpath elements in "<inputrequestfile>"
      | xpath                                                                                                                                                       | value                     |
      #| //HVSResponse/ResponseDateTime | YYYY-MM-DDThh:mm:ss.SSS |
      | //HVSResponse/ServiceRequestContainer/ServiceRequest/ServiceRequestIdentifier                                                                               |                       1.0 |
      | //HVSResponse/ServiceRequestContainer/ServiceRequest/ClientSchemaVersionIdentifier                                                                          |                       1.0 |
      | //HVSResponse/ServiceRequestContainer/ServiceRequest/SchemaVersionIdentifier                                                                                |                         1 |
      | //HVSResponse/ServiceRequestContainer/ServiceRequest/RequestorIdentifier                                                                                    | LoanProductAdvisor        |
      | //HVSResponse/ServiceRequestContainer/ServiceRequest/ServiceName                                                                                            | HVSWS                     |
      #| //HVSResponse/ServiceRequestContainer/ServiceRequest/ServiceRequestDateTime | YYYY-MM-DDThh:mm:ss.SSS |
      | //HVSResponse/ServiceRequestContainer/ServiceRequest/ServiceRequestType                                                                                     | RuleServiceRequest        |
      | //HVSResponse/ServiceRequestContainer/ServiceRequest/SubscriberRequestCorrelationIdentifier                                                                 |                     12345 |
      | //HVSResponse/HVSResponseContainers/HVSResponseContainer/AlternativeLoanIdentifications/AlternativeLoanIdentification/LoanIdentifier                        |                         1 |
      | //HVSResponseContainers/HVSResponseContainer/BillingType                                                                                                    | NotApplicable             |
      | //HVSResponseContainers/HVSResponseContainer/FDRVersion                                                                                                     |                   2018.02 |
      | //HVSResponseContainers/HVSResponseContainer/HVSTransactionIdentifier                                                                                       |                   9370307 |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/AddressLineText                                                                           | 1938 TINNIN RD            |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/CityName                                                                                  | GREELEY                   |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/FIPSStateAlphaCode                                                                        | CO                        |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/PostalCode                                                                                |                     37072 |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation/AVMConfidenceScoreValue                                                | M                         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation/AVMHighValueRangeAmount                                                |                    403250 |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation/AVMLowValueRangeAmount                                                 |                    294581 |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation/AVMModelNameType                                                       | HomeValueExplorer         |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation/AVMValueAmount                                                         |                    432020 |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/AVMValuations/AVMValuation/HVEStandardDeviationNumber                                             |                      0.16 |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/ActiveMLSListingIndicator        | false                     |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/AppraisalCompletionAsIsIndicator | false                     |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/ARMsLengthContractIndicator      | true                      |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/DaysOnMarketIndicator            | true                      |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/DistressedIndicator              | true                      |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/LegalityIndicator                | true                      |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/MissingDataIndicator             | false                     |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/MultipleListingIndicator         | false                     |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/NeighborhoodConformityIndicator  | false                     |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PhysicalDeficiencyIndicator      | false                     |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PropertyAgeCount                 |                        10 |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/PublicRecordExistenceIndicator   | true                      |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/QualityAgeIndicator              | false                     |
      | //HVSResponseContainers/HVSResponseContainer/HVSValuation/PropertyInspectionWaiverContainer/PropertyInspectionWaiverResult/QualityIndicator                 | true                      |
      | //HVSResponse/HVSResponseContainers/HVSResponseContainer/AlternativeLoanIdentifications/AlternativeLoanIdentification/AlternateLoanIdentifier               |                   1213421 |
      | //HVSResponse/HVSResponseContainers/HVSResponseContainer/AlternativeLoanIdentifications/AlternativeLoanIdentification/LoanIdentifierType                    | LoanProspectorTransaction |
      | //HVSResponse/HVSResponseContainers/HVSResponseContainer/HVSValuation/Address/ZIPPlusFourCode                                                               |                      1234 |

    Examples: 
      | inputfile               | inputrequestfile   | operation | user_name            | proxymessageid |
      | inputfile_response.json | hveapiresponse.xml | POST      | baofam_lasapisys2sys | qewd123        |
