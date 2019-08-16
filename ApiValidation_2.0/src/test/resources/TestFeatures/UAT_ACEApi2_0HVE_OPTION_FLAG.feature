@012748-WS-ACE_API_2.0 @HOMEVALUEOptionType
Feature: Map the HVEOptionType value in ACEAPI Request to EDS Request

  Scenario Outline: Map the HVEOptionType value in ACEAPI Request to EDS Request
    #Original submission
    When User submits ACEAPI2.0 request "ACEAPI20Request-<Client>-<TMPL_ID>.json"
      | HVEOptionType   |
      | <HVEOptionType> |
    Then User retrieves the corresponding "AARequest" from ACEAPI2.0 Database
    And User verifies the "AARequest" values
      | homeValueExplorerOptionType | HVE2homeValueExplorerOptionType |
      |                             | <HVEOptionType>                 |

    Examples: 
      | Client | TMPL_ID | HVEOptionType      |
      | ULAD   |      02 | HVEFeedback        |
      | ULAD   |      02 | NoHVEFeedback      |
      | ULAD   |      02 | ReliefRefinance    |
      | ULAD   |      02 | LimitedHVEFeedback |
      | ULAD   |      02 | __NULL_VALUE__     |
      | ULAD   |      02 | __NO_ELEMENT__     |
      | LQA    |      02 | HVEFeedback        |
      | LQA    |      02 | NoHVEFeedback      |
      | LQA    |      02 | ReliefRefinance    |
      | LQA    |      02 | LimitedHVEFeedback |
      | LQA    |      02 | __NULL_VALUE__     |
      | LQA    |      02 | __NO_ELEMENT__     |
#001 NO HVE DATA 
 #Scenario: User sends a valid ace api 2.0 request and grabs the EDS request from the database
    #Given User sends "UAT" ACE API 2.0 request  "ULAD_DriveNOHVEDATA.json" and validates response code "200"
    #Then User retrieves "REQUEST" for "EDS" from UAT Mongo DB
#
  #Scenario Outline: User goes into the mongo database and validates that the EDS request was mapped correctly from the ACE 2.0 request
    #Then Validate "REQUEST" "EDS" element "<elementName1>" with value "<elementValue1>" from Mongo
#
    #Examples: 
      #| elementName1                | elementValue1       |
      #| HomeValueExplorerOptionType | HVEFeedback         |
    #
    #
 #002 Relief Refinance   
#Scenario: User sends a valid ace api 2.0 request and grabs the EDS request from the database
    #Given User sends "UAT" ACE API 2.0 request  "ULAD_RELIEF_REFINANCE" and validates response code "200"
    #Then User retrieves "REQUEST" for "EDS" from UAT Mongo DB
#
  #Scenario Outline: User goes into the mongo database and validates that the EDS request was mapped correctly from the ACE 2.0 request
    #Then Validate "REQUEST" "EDS" element "<elementName1>" with value "<elementValue1>" from Mongo
#
    #Examples: 
      #| elementName1                | elementValue1       |
      #| HomeValueExplorerOptionType | HVEFeedback         |
