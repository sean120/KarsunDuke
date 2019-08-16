@RegressionSIT @APIGEESITTEST
Feature: APIGEE Developer Portal ACE API Request

  @SMOKETEST
  Scenario Outline: User sends valid APIGEE ACE API Request
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    When Request is performing "<operation>" on APIGEE Gateway ACE API
    Then Service sends back ResponseCode
    Then Validate the ACE API "<Decision>"

    Examples: 
      | content_type     | operation | Decision                                                           | RequestFile                            |
      #| application/json | POST     | $.deals[0].loanInformation.appraisalWaiverPrescreenEligibilityType | CorrectACEAPIRequest.json              |
      | application/json | POST      | $.deals[0].loanInformation.appraisalWaiverPrescreenEligibilityType | ValidACEAPIRequest.json                |
      | application/json | POST      | $.deals[0].loanInformation.appraisalWaiverPrescreenEligibilityType | PurchaseLoanPurposeACEAPIRequest.json  |
      | application/json | POST      | $.deals[0].loanInformation.appraisalWaiverPrescreenEligibilityType | RefinanceACEAPIRequest.json            |
      | application/json | POST      | $.deals[0].loanInformation.appraisalWaiverPrescreenEligibilityType | RefinanceLoanPurposeACEAPIRequest.json |

  @Iteration03SITMSGCODES
  Scenario Outline: User sends invalid ACE API Request
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>" for "<scenariosname>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    When Request is performing "<operation>" on APIGEE Gateway ACE API
    Then Service sends back "<statuscode>"
    Then User validates the expected message code "<messageCode>" and associated text in the response

    Examples: 
      | RequestFile                                                 | scenariosname                                                | content_type     | operation | statuscode | messageCode             |
      | MissPartyRoleIdentifier.json                                | Missing partyRoleIdentifier                                  | application/json | POST      |        400 | SV001                   |
      | MissingAddressLineText.json                                 | Missing Address line text                                    | application/json | POST      |        400 | SV002                   |
      | MissingCityName.json                                        | Missing CityName                                             | application/json | POST      |        400 | SV003                   |
      | MissingStateCode.json                                       | Missing StateCode                                            | application/json | POST      |        400 | SV007                   |
      | MissingPostalCode.json                                      | Missing Postal Code                                          | application/json | POST      |        400 | SV004                   |
      | MissingLoanPurposeType.json                                 | Missing Loan Purpose Type                                    | application/json | POST      |        400 | SV005                   |
      | MissingPropertyEstimatedValueAmount.json                    | Missing Property Estimated Value Amount                      | application/json | POST      |        400 | SV006                   |
      | LoanPurposeTypeSpecialChar.json                             | InValid Load Purpose Type special char                       | application/json | POST      |        400 | SV005                   |
      | InValidLoadPurposeTypeEnum.json                             | InValid Load Purpose Type enum                               | application/json | POST      |        400 | SV005                   |
      | MissingPartyRoleType.json                                   | Missing partyRoleType                                        | application/json | POST      |        400 | SV009                   |
      | MissingSalesContractAmount.json                             | Missing salesContractAmount                                  | application/json | POST      |        400 | SV008                   |
      | MissingCityNamestatecode.json                               | Missing CityName and statecode                               | application/json | POST      |        400 | SV003,SV007             |
      | MissingPartyRoleIdentifierAddressLineText.json              | Missing PartyRoleIdentifier and AddressLineText              | application/json | POST      |        400 | SV001,SV002             |
      | InvalidPartyRoleType.json                                   | Invalid partyRoleType                                        | application/json | POST      |        400 | SV009                   |
      | InvalidPartyRoleTypePartyRoleIdentifier.json                | Invalid PartyRoleType and PartyRoleIdentifier                | application/json | POST      |        400 | SV009,SV001             |
      | InvalidPartyRoleIdentifier.json                             | Invalid PartyRoleIdentifier                                  | application/json | POST      |        400 | SV001                   |
      | InvalidPropertyEstimatedValueAmountSalesContractAmount.json | Invalid PropertyEstimatedValueAmount and SalesContractAmount | application/json | POST      |        400 | SV006,SV008             |
      | InvalidPropertyEstimatedValueAmount.json                    | Invalid PropertyEstimatedValueAmount                         | application/json | POST      |        400 | SV006                   |
      | InvalidSalesContractAmount.json                             | Invalid SalesContractAmount                                  | application/json | POST      |        400 | SV008                   |
      | InvalidLoanPurposeType.json                                 | Invalid loanPurposeType                                      | application/json | POST      |        400 | SV005                   |
      | InvalidPostalCode.json                                      | Invalid postalCode                                           | application/json | POST      |        400 | SV004                   |
      | InvalidCityName.json                                        | Invalid cityName                                             | application/json | POST      |        400 | SV003                   |
#      | InvalidAddressLineText.json                                 | Invalid addressLineText                                      | application/json | POST      |        400 | SV002                   |
      | InvalidStateCode.json                                       | Invalid stateCode                                            | application/json | POST      |        400 | SV007                   |
      | InvalidAddressContainer.json                                | Invalid address                                              | application/json | POST      |        400 | SV003,SV004,SV007 |

  #  | ValidACEAPIRequest.json                        | InternalSystemDown_HVE_EDS_OR_ED        | application/json | POST      |        200 | AWP0003    |
  @IterationBCAPI
  Scenario Outline: User sends ACE API Request with "<typeoftoken>" token
    Given APIGEE Developer Portal "<typeoftoken>" ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    When Request is performing "<operation>" on APIGEE Gateway ACE API
    Then Service responds error "<responsecode>" and "<errordetails>"

    Examples: 
      | RequestFile             | content_type     | operation | responsecode | errordetails       | typeoftoken |
      | ValidACEAPIRequest.json | application/json | POST      |          401 | $.details[0].error | expired     |
      | ValidACEAPIRequest.json | application/json | POST      |          401 | $.details[0].error | wrong       |
      | ValidACEAPIRequest.json | application/json | POST      |          401 | $.details[0].error | BC API      |
