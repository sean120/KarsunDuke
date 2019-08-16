@DoNotIncludeThis
Feature: Regression POST Request Call Validation

  Background: Clear previously posted data from GFS database
    Given Remove "GFS" MongoDB documents with the following values
      | LoanID_LPKey | addressLineText  | PartyID_SELLER | postalCode |
      | B0012685-88  | 14576 BERKLEE DR |         000601 |      75001 |
      | A0100842     | 233 YUKON PASS   |         000601 |      46706 |

  Scenario Outline: User sends ACEAPI2.0 requests
    Given ACEAPI2.0 request payload in the file "ACEAPI20Request-<Client>-<TMPL_ID>.json"
    And The Address in the ACEAPI2.0 request payload updated as below
      | addressLineText   | cityName   | postalCode   | stateCode   | zipPlusFourCode   | LoanID_LPKey   |
      | <addressLineText> | <cityName> | <postalCode> | <stateCode> | <zipPlusFourCode> | <LoanID_LPKey> |
    When User submits ACEAPI2.0 request
    And User retrieves the lates data stored in Mongo DB collection "grandFatheringAceRequest"
    And User validates the "requestDataElements" values in request payload and the DB document
    And User validates the "sourceApplicationName" is "LPAv2" in "grandFatheringAceRequest"
    And User retrieves the lates data stored in Mongo DB collection "grandFatheringPresentValueRequest"
    And User validates the "requestDataElements" values in request payload and the DB document
    And User validates the "sourceApplicationName" is "LPAv2" in "grandFatheringPresentValueRequest"

    Examples: 
      | Client | TMPL_ID | addressLineText            | cityName          | postalCode | stateCode | zipPlusFourCode | LoanID_LPKey |
      | ULAD   |      03 | 11931 BENTON LAKE RD       | BRISTOW           |      20136 | VA        |            6156 | B0012685     |
      | ULAD   |      03 | 14576 BERKLEE DR           | ADDISON           |      75001 | TX        |            3532 | B0012685     |
      | ULAD   |      03 | 10211 MAGNOLIA BLOSSOM AVE | GREENWELL SPRINGS |      70739 | LA        |            4975 | B0012685     |
      | ULAD   |      03 | 520 BERKELEY HILL DR       | CHARLOTTE         |      28262 | NC        |            1084 | B0012685     |
      | ULAD   |      03 | 11931 BENTON LAKE RD       | BRISTOW           |      20136 | VA        |            6156 | B0012685     |
      | ULAD   |      03 | 10211 MAGNOLIA BLOSSOM AVE | GREENWELL SPRINGS |      70739 | LA        |            4975 | B0012685     |

  Scenario: User sends ACE2.0 request
    Given ACEAPI2.0 request payload in the file "ACEAPI20Request-ULAD-VALID.json"
    When User submits ACEAPI2.0 request
    #          And User retrieves the lates data stored in Mongo DB collection "grandFatheringAceData"
    Then User retrieves the corresponding "AARequest" from ACEAPI2.0 Database
    Then User retrieves the corresponding "AAResponse" from ACEAPI2.0 Database
    And User retrieves the corresponding ACEAPI2.0 "GFSACEData" from GFS Database
    Then User verifies "ACEData" data is mapped correctly to grandfathering POST Call
    And User retrieves the corresponding ACEAPI2.0 "GFSPVData" from GFS Database
    Then User retrieves the corresponding "AARequest" from ACEAPI2.0 Database
    Then User verifies "PVData" data is mapped correctly to grandfathering PVPOST Call
