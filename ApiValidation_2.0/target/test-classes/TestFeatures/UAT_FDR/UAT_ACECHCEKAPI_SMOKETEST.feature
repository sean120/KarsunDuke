@SmokeTest @ACEHCK_CHECKAPI
Feature: AceCheckAPI Consume New Elements for HVE/FDR CCE from HVS Response

  Scenario Outline: User submits Post request and Validates response
    When User submits ACE Check API (No APIGEE) request and gets response
      | partyRoleIdentifier   | loanPurposeType   | salesContractAmount   | propertyEstimatedValueAmount   | addressLineText   | cityName   | postalCode   | stateCode   |
      | <partyRoleIdentifier> | <loanPurposeType> | <salesContractAmount> | <propertyEstimatedValueAmount> | <addressLineText> | <cityName> | <postalCode> | <stateCode> |

    Examples: 
      | partyRoleIdentifier | loanPurposeType | salesContractAmount | propertyEstimatedValueAmount | addressLineText      | cityName | postalCode | stateCode | 
      |              000601 | Purchase        |              346585 |                              | 11931 BENTON LAKE RD | BRISTOW  |      20136 | VA        |                        
      |              000601 | Purchase        |              346186 |                              | 1938 TINNIN RD       | GREELEY  |      37072 | CO        |               
