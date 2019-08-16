@SIT-IT06-ACEAPI-Request-Schema-Validation-LQA-02-ACE_FA101 @SIT-IT05 @check-schema-all @B-110840 @SIT-ACEAPI20
Feature: ACEAPI2.0 Request Schema Validation - LQA - ACE_FA101

  Scenario Outline: ACEAPI2.0 Request Schema Validation - LQA - ACE_FA101
    When User submits ACEAPI2.0 request "ACEAPI20Request-<Client>-<TMPL_ID>.json" with below changes
      | <elementName>  |
      | <elementValue> |
    Then ACEAPI2.0 returns HTTP status code <returnCode>
    And User verifies the ACEAPI2.0 response values
      | errorCode   | alternateAppraisalEligibilityDecision   | errorCode   | errorMessage   |
      | <errorCode> | <alternateAppraisalEligibilityDecision> | <errorCode> | <errorMessage> |

    Examples: 
      | Client | TMPL_ID | elementName                               | elementValue     | returnCode | alternateAppraisalEligibilityDecision | errorCode | errorMessage |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_LQA | __EMPTY_OBJECT__ |        200 | Unavailable                           | ACE_FA101 | EDS Down     |
      | LQA    | 02-v2   | loanIdentification_ArrayElementObject_LQA | __NO_OBJECT__    |        200 | Unavailable                           | ACE_FA101 | EDS Down     |
      | LQA    | 02-v2   | subscriberIdentifier                      |                  |        200 | Unavailable                           | ACE_FA101 | EDS Down     |
      | LQA    | 02-v2   | subscriberIdentifier                      | abcd             |        200 | Unavailable                           | ACE_FA101 | EDS Down     |
