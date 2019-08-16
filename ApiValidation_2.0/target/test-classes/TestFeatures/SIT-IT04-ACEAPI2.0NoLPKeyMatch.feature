@Iteration04_SIT @B-115766 @Kostia @IT04 @SIT-ACEAPI20 @Regression_SIT
Feature: User sends ACEAPI2.0 request and LPKey is not matching

  Background: Clear previously posted data from GFS database
    Given Remove "GFS" MongoDB documents with the following values
      | LoanID_LPKey | addressLineText  | PartyID_SELLER | postalCode |
      | B4798-OG     | 14576 BERKLEE DR |         000601 |      75001 |
    Given Remove "GFS" MongoDB documents with the following values
      | postalCode |
      |      44444 |
    Given Remove "GFS" MongoDB documents with the following values
      | sourceApplicationName | addressLineText  | PartyID_SELLER |
      | ACEAPI                | 14576 BERKLEE DR |         000601 |

  Scenario Outline: User sends ACEAPI2.0 request
    Given User submits new ACEAPI request
      | partyRoleIdentifier   | loanPurposeType   | salesContractAmount   | propertyEstimatedValueAmount   | addressLineText   | cityName   | postalCode   | stateCode   |
      | <partyRoleIdentifier> | <loanPurposeType> | <salesContractAmount> | <propertyEstimatedValueAmount> | <addressLineText> | <cityName> | <postalCode> | <stateCode> |
    Then User retrieves the corresponding "ACEAPIRequest" from ACEAPI Database
    And User retrieves the corresponding "GFSPostData" from GFS Database
    And User submits ACEAPI2.0 request "ACEAPI20Request-ULAD-GF-07.json"
      | LoanID_LPKey | LoanID_LPT | subscriberIdentifier | addressLineText  | postalCode | sellerAccountIdentifier |
      | B4798-OG     |            | LPAv2                | 14576 BERKLEE DR |      75001 |                  000601 |
    And User retrieves the lates data stored in Mongo DB collection "grandFatheringAceData"
    When User retrieves the latest data from MongoDB "grandFatheringAceResponse" collections
    And Verify that sourceType is "ACEAPI" in Response
    Then Verify that latest grandFatheringResponse and grandFatheringData contains grandFathering elements
      | PartyID_SELLER | addressLineText  | postalCode |
      |         000601 | 14576 BERKLEE DR |      95661 |

    Examples: 
      | partyRoleIdentifier | loanPurposeType | salesContractAmount | propertyEstimatedValueAmount | addressLineText  | cityName | postalCode | stateCode |
      |              000601 | Purchase        |              346585 |                              | 14576 BERKLEE DR | BRISTOW  |      75001 | VA        |
