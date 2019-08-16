@B_128820-UAT @ACEcheckAPiMAPPING_NEWHVSDATA_EDS_REQUEST
Feature: AceCheckAPI Map New Elements for HVE/FDR CCE from HVS Response to EDS Request

  Scenario Outline: AceCheckAPI Map New Elements for HVE/FDR CCE from HVS Response to EDS Request
    When User submits ACE Check API (No APIGEE) request and gets response
      | partyRoleIdentifier   | loanPurposeType   | salesContractAmount   | propertyEstimatedValueAmount   | addressLineText   | cityName   | postalCode   | stateCode   |
      | <partyRoleIdentifier> | <loanPurposeType> | <salesContractAmount> | <propertyEstimatedValueAmount> | <addressLineText> | <cityName> | <postalCode> | <stateCode> |
    And User verifies the following data elements from ACE Check API "HVEResponse" data are mapped to "AARequest"
      | predictedConditionScoreValue                       |
      | femaDisasterName                                   |
      | appraisalAlternativeDisasterEligibilityDescription |

    Examples: 
      | partyRoleIdentifier | loanPurposeType | salesContractAmount | propertyEstimatedValueAmount | addressLineText        | cityName        | postalCode | stateCode |
      |              000601 | Purchase        |              346585 |                              | 11931 BENTON LAKE RD   | BRISTOW         |      20136 | VA        |
      |              000601 | Purchase        |              346186 |                              | 1938 TINNIN RD         | GREELEY         |      37072 | CO        |
      |              000601 | Refinance       |                     |                       410000 | 23303 ANGELA CT        | SMITHSBURG      |      21783 | MD        |
      |              000601 | Purchase        |              287671 |                              | 14576 BERKLEE DR       | ADDISON         |      75001 | LA        |
      |              000601 | Purchase        |              140949 |                              | 9204 MARSH MOUNTAIN RD | PINSON          |      35126 | AL        |
      |              000601 | Purchase        |              173617 |                              | 1318 PRINCETON ST      | ABILENE         |      79602 | TX        |
      |              000601 | Purchase        |              447246 |                              | 25264 WINDY BLUFF LN   | ARLINGTON       |      68002 | NE        |
      |              000601 | Purchase        |              320000 |                              | 37 PARK AVE            | LAKE RONKONKOMA |      11779 | NY        |
      |              000601 | Purchase        |              395000 |                              | 3365 MORONEY DR        | RICHARDSON      |      75082 | TX        |
      |              000601 | Refinance       |                     |                       575000 | 2140 DEMETRIUS WAY     | ROSEVILLE       |      95661 | CA        |
      |              000601 | Purchase        |              797945 |                              | 33 OAK KNOLL RD        | RIDGEFIELD      |      06877 | CT        |
      |              000601 | Purchase        |              550000 |                              | 5901 COVINGTON CT      | MINNETONKA      |      55345 | MN        |
