@Iteration02  @B-112989 @113223 @TESME
Feature: Ace Api 2.0 Response Schema Validation

  Scenario: User sends a valid ace api 2.0 request and receives a stubbed response
    Given User sends "UAT" ACE API 2.0 request  "UpdatedRequest3_28_19" and validates response code "200"

  Scenario Outline: User sends a valid ace api 2.0 request and receives a stubbed response
    Then User validates that the ACE API 2.0 response element "<elementName1>" equals "<elementValue1>"

    Examples: 
      | elementName1                                 | elementValue1 |
      #      | propertyValuationEffectiveDateTime           | NoValue       |
      | alternateAppraisalDecisionExpirationDatetime | NoValue       |
      | alternateAppraisalDecisionStatusType         | NoValue       |
      | alternateAppraisalEligibilityDecision        | NoValue       |
      | minimumLoanAssessmentFormType                | NoValue       |
      | aceFACTExpirationDatetime                    | NoValue       |
      | aceFACTDecisionStatusType                    | NoValue       |
      | aceFACTEligibilityDecision                   | NoValue       |
      | aceFACTPropertyValuationEffectiveDateTime    | NoValue       |
      #| listText1                                     | NoValue       |
      #      | error                                        | NoValue       |
      #      | code                                         | NoValue       |
      #      | message                                      | NoValue       |
      | loanIdentifier                               | NoValue       |
      | loanIdentifierType                           | NoValue       |
      #      | listText2                                     | NoValue       |
      #      | listText3                                    | NoValue       |
      #      | listText4                                     | NoValue       |
      #      | listText5                                     | NoValue       |
      #      | listText6                                     | NoValue       |
      #      | listText7                                     | NoValue       |
      #      | listText8                                     | NoValue       |
      #      | listText9                                     | NoValue       |
      | messageCategory1                             | NoValue       |
      #      | messageDisplayFlag1                           | NoValue       |
      | messageId1                                   | NoValue       |
      | messageType1                                 | NoValue       |
#      | sequenceNumber1                               | NoValue       |
#      | messageCategory2                              | NoValue       |
#      | messageDisplayFlag2                           | NoValue       |
#      | messageId2                                    | NoValue       |
#      | messageType2                                  | NoValue       |
#      | sequenceNumber2                               | NoValue       |
