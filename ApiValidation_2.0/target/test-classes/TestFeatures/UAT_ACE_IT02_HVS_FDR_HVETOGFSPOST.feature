@B-129398 @HVSResponseToGFSPOST
Feature: Submit AceCheckRequest and Validate with HVS Response to GFS Post

  Scenario Outline: User Sends ACEAPI request
    Given User submits new ACEAPI request
      | partyRoleIdentifier   | loanPurposeType   | salesContractAmount   | propertyEstimatedValueAmount   | addressLineText   | cityName   | postalCode   | stateCode   |
      | <partyRoleIdentifier> | <loanPurposeType> | <salesContractAmount> | <propertyEstimatedValueAmount> | <addressLineText> | <cityName> | <postalCode> | <stateCode> |
   
    When User restrieves "ED" "RESPONSE" from "serviceCallTraces"
    Then User verify that "UserRoleName" is "ACE Check API Customer"

    Examples: 
      | partyRoleIdentifier | loanPurposeType | salesContractAmount | propertyEstimatedValueAmount | addressLineText      | cityName | postalCode | stateCode |
      |              000601 | Purchase        |              346585 |                              | 11931 BENTON LAKE RD | BRISTOW  |      20136 | VA        |
