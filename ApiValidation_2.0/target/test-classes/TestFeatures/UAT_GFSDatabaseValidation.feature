@UATBCIteration04 @B-90710 @UATREGRESSION @TryMeNow
Feature: MAP AND STORE IVAN RESPONSE

  Scenario Outline: Scenario001 - User sends BC Api request and validates the stored IVAN response in Mongo DB according to the Response schema
    Then User retrieves "<requestType>" for "<serviceName>" from UAT Mongo DB
    Then Validate any "<test>" value in the response from Mongo
    Then Validate "<requestType>" "<serviceName>" element "<elementName1>" with value "<elementValue1>" from Mongo

    Examples: 
      | requestType | serviceName | test                                                                                | elementName1                  | elementValue1          |
      | RESPONSE    | EDS         | <AttrVal>468217551528</AttrVal>                                                     | AttrVal01                     |           468217551528 |
      | REQUEST     | EDS         | <ns3:ServiceRequestIdentifier>EDS must generate UUID</ns3:ServiceRequestIdentifier> | ServiceRequestIdentifier      | EDS must generate UUID |
      | RESPONSE    | HVS         | <ClientSchemaVersionIdentifier>1.0</ClientSchemaVersionIdentifier>                  | ClientSchemaVersionIdentifier |                    1.0 |
      | REQUEST     | EDS         | <ClientSchemaVersionIdentifier>1.0</ClientSchemaVersionIdentifier>                  | ClientSchemaVersionIdentifier |                    1.0 |
