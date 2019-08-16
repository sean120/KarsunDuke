@SIT-IT06-ACECheckAPIRequestElementsValidation @B-122746_SIT @SIT-IT06 @SIT-ACEAPI20 @Regression_SIT
Feature: ACE Check API Submission Request Elements Validation

  Scenario Outline: ACE Check API Submission Request Elements Validation
    When User submits ACE Check API request
      | partyRoleIdentifier   | loanPurposeType   | salesContractAmount   | propertyEstimatedValueAmount   | addressLineText   | cityName   | postalCode   | stateCode   |
      | <partyRoleIdentifier> | <loanPurposeType> | <salesContractAmount> | <propertyEstimatedValueAmount> | <addressLineText> | <cityName> | <postalCode> | <stateCode> |
    Then ACE Check API returns HTTP status code <returnCode>

    Examples: 
      | partyRoleIdentifier | loanPurposeType | salesContractAmount | propertyEstimatedValueAmount | addressLineText                                                                                       | cityName                                            | postalCode | stateCode | returnCode |
      #valid request
      |              000601 | Purchase        |              346585 |                              | 11931 BENTON LAKE RD                                                                                  | BRISTOW                                             |      20136 | VA        |        200 |
      #Bad request - stateCode is not an ENUM
      |              000601 | Purchase        |              346585 |                              | 11931 BENTON LAKE RD                                                                                  | BRISTOW                                             |      20136 | V.A       |        400 |
      #valid request- special character in addressLineText
      |              000601 | Purchase        |              346585 |                              | #11931 BENTON LAKE RD                                                                                 | BRISTOW                                             |      20136 | VA        |        200 |
      #valid request- special character in addressLineText- all special chars
      |              000601 | Purchase        |              346585 |                              | ~!@#$%^&*()_+{}\|:\\"<>?`-=[]\\;',./                                                                  | BRISTOW                                             |      20136 | VA        |        200 |
      #valid request - addressLineText with maximum length(100 chars)
      |              000601 | Purchase        |              346585 |                              | #11931 BENTON LAKE RDddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd  | BRISTOW                                             |      20136 | VA        |        200 |
      #Bad Request - addressLineText too long(beyond maximum length 100 chars)
      |              000601 | Purchase        |              346585 |                              | #11931 BENTON LAKE RDddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd+ | BRISTOW                                             |      20136 | VA        |        400 |
      #Bad request - addressLineText too short
      |              000601 | Purchase        |              346585 |                              |                                                                                                       | BRISTOW                                             |      20136 | VA        |        400 |
      #valid request- special character in cityName
      |              000601 | Purchase        |              346585 |                              | #11931 BENTON LAKE RD                                                                                 | St. Louis                                           |      20136 | VA        |        200 |
      #valid request- special character in cityName- all special chars
      |              000601 | Purchase        |              346585 |                              | #11931 BENTON LAKE RD                                                                                 | ~!@#$%^&*()_+{}\|:\\"<>?`-=[]\\;',./                |      20136 | VA        |        200 |
      #valid request- cityName with maximum length(50 chars)
      |              000601 | Purchase        |              346585 |                              | 11931 BENTON LAKE RD                                                                                  | St. Louissssssssssssssssssssssssssssssssssssssssss  |      20136 | VA        |        200 |
      #Bad request- cityName too long(beyond maximum length 50 chars)
      |              000601 | Purchase        |              346585 |                              | 11931 BENTON LAKE RD                                                                                  | St. Louissssssssssssssssssssssssssssssssssssssssss+ |      20136 | VA        |        400 |
      #Bad request- cityName too short
      |              000601 | Purchase        |              346585 |                              | 11931 BENTON LAKE RD                                                                                  |                                                     |      20136 | VA        |        400 |
