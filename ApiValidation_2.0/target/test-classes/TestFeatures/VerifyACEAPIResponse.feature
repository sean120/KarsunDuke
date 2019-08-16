@RegressionSIT
Feature: ACE API Response

  Scenario Outline: Send the ACE decision and associated message to freddiemac approved lender
    Given "<user_name>" and "<password>" combinations to get ACE Access Token at "<accesstoken>" path
    And APIGEE Portal For ACE and a Valid "<RequestFile>"
    When Sending request to ACE with the valid access token
    Then Save ACE Response
    Then Check for containers in ACE Response
      | Name                      | Element                                 |
      | ACE Eligibility Indicator | appraisalWaiverPrescreenEligibilityType |
      | addressLineText           | addressLineText                         |
      | cityName                  | cityName                                |
      | stateCode                 | stateCode                               |
      | postalCode                | addressLineText                         |
      | Message Code              | postalCode                              |
      | Message Text              | messageText                             |
      | Maximum Loan Amount       | maximumAuthorizedLoanAmount             |
      | Expiration Date           | appraisalWaiverPrescreenExpirationDate  |

    Examples: 
      | user_name             | password             | accesstoken    | RequestFile     | hvsresponsefile    | edsresponsefile    |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | inputfile9.json | hveapiresponse.xml | edsapiresponse.xml |
