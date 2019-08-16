Feature: 001 HVS response validation

  Scenario Outline: User Sends ACEAPI request
    Given User submits new ACEAPI request
      | partyRoleIdentifier   | loanPurposeType   | salesContractAmount   | propertyEstimatedValueAmount   | addressLineText   | cityName   | postalCode   | stateCode   |
      | <partyRoleIdentifier> | <loanPurposeType> | <salesContractAmount> | <propertyEstimatedValueAmount> | <addressLineText> | <cityName> | <postalCode> | <stateCode> |



    Examples: 
      | partyRoleIdentifier | loanPurposeType | salesContractAmount | propertyEstimatedValueAmount | addressLineText      | cityName | postalCode | stateCode |
      |              000601 | Purchase        |              346585 |                              | 11931 BENTON LAKE RD | BRISTOW  |      20136 | VA        |

      
