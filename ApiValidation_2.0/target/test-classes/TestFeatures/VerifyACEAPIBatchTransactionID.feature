@RegressionSIT @IterationBTI
Feature: ACE API Batch and Transaction Identifiers

  Scenario Outline: Check ACE API Request and Response for mapping Batch and Transaction Identifiers
    Given BTI "<user_name>" and "<password>" combinations to get ACE Access Token at "<accesstoken>" path
    And BTI APIGEE Portal For ACE and a Valid "<RequestFile>"
    When BTI Sending request to ACE with the valid access token
    Then Save ACE Response BTI and checking for "<success_code>"
    Then Check for Batch and Transaction identifiers in ACE Response
      | Name                   | Element                      |
      | Batch Identifier       | requestBatchIdentifier       |
      | Transaction Identifier | requestTransactionIdentifier |
    Then Validate the Batch Identifier and Transaction Identifier is don't have length more than 45 chars

    Examples: 
      | user_name             | password             | accesstoken    | RequestFile                     | success_code |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | BatchTransactionIDTruncate.json | positive     |

  Scenario Outline: Check ACE API Request and Response for value Batch and Transaction Identifiers
    Given BTI "<user_name>" and "<password>" combinations to get ACE Access Token at "<accesstoken>" path
    And BTI APIGEE Portal For ACE and a Valid "<RequestFile>"
    When BTI Sending request to ACE with the valid access token
    Then Save ACE Response BTI and checking for "<success_code>"
    Then Check for Batch and Transaction identifiers "<batch_value>" and "<transaction_value>" to be same

    Examples: 
      | user_name             | password             | accesstoken    | RequestFile                  | success_code | batch_value | transaction_value |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | inputfile8.json              | positive     |        1234 |              1234 |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | AceNotEligiblePurchase.json  | positive     |        1234 |              1234 |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | AceNotEligibleRefinance.json | positive     |        1234 |              1234 |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | NotApplicable_AWP0004.json   | positive     |        1234 |              1234 |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | NotApplicable_AWP0003.json   | positive     |        1234 |              1234 |

  Scenario Outline: Check Invalid ACE API Request and Response for missing Batch and Transaction Identifiers
    Given BTI "<user_name>" and "<password>" combinations to get ACE Access Token at "<accesstoken>" path
    And BTI APIGEE Portal For ACE and a Valid "<RequestFile>"
    When BTI Sending request to ACE with the valid access token
    Then Save ACE Response BTI and checking for "<scenario>"
    Then User validates the expected message code "<messageCode>" and associated text in the response BTI

    Examples: 
      | user_name             | password             | accesstoken    | RequestFile                            | messageCode                               | success_code |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | InvalidBatchIdentifier.json            | Invalid type: number                      | negative     |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | InvalidTransactionIdentifier.json      | Invalid type: number                      | negative     |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | InvalidBatchTransactionIdentifier.json | Invalid type: number,Invalid type: number | negative     |

  Scenario Outline: Check ACE API Request and Response for missing Batch and Transaction Identifiers
    Given BTI "<user_name>" and "<password>" combinations to get ACE Access Token at "<accesstoken>" path
    And BTI APIGEE Portal For ACE and a Valid "<RequestFile>"
    When BTI Sending request to ACE with the valid access token
    Then Save ACE Response BTI and checking for "<scenario>"
    Then Check for Batch or Transaction identifiers in ACE Response for "<Missing>" element

    Examples: 
      | user_name             | password             | accesstoken    | RequestFile                            | Missing                                             | success_code |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | MissingBatchIdentifier.json            | requestBatchIdentifier                              | positive     |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | MissingTransactionIdentifier.json      | requestTransactionIdentifier                        | positive     |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | MissingBatchTransactionIdentifier.json | requestBatchIdentifier,requestTransactionIdentifier | positive     |
