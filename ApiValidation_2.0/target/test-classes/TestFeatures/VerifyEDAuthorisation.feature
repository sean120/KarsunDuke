@RegressionSIT
Feature: ACE API User Authorization with ED

  Scenario Outline: User sends ACE Eligibility request with a valid username password and a valid combination of app and policyDirectorIdentifier
    Given ED "<user_name>" and "<password>" combinations to get ACE API Access Token at "<accesstoken>" path
    And APIGEE Developer Portal For ACE API and a Valid "<RequestFile>"
    When User Sends request to APIGEE ACE API Gateway with the returned access token
    Then ACE API sends back successful ACE Response
    Then Check Mongo DB for ED Response
    Then Validate the ED Response

    Examples: 
      | user_name              | password             | RequestFile                    | accesstoken    |
      | baofam_lasapisys2sys4  | fJ6%@9o=[EwB2Pr$VjUp | ValidACEAPIRequest.json        | $.access_token |
      | baofam_lasapisys2sys17 | CZY3?E9%SAK[U4b/ARPR | ValidACEAPIRequest.json        | $.access_token |
#      | wfbmn_lasapisys2sys5   | LCH?ff4JObM#RZ5@M%2[ | AnotherEligibleSITRequest.json | $.access_token |
      | peb_lasapisys2sys      | PeEbpv~0R@$8gYux(UaF | inputfile12.json 			   | $.access_token |

  Scenario Outline: User sends ACE Eligibility request with access removed username password and a valid combination of app and policyDirectorIdentifier
    Given ED "<user_name>" and "<password>" combinations to get ACE API Access Token at "<accesstoken>" path
    And APIGEE Developer Portal For ACE API and a Valid "<RequestFile>"
    When User Sends request to APIGEE ACE API Gateway with the returned access token
    Then ACE API sends back unauthorized user response
    Then Check Mongo DB for ED Response

    Examples: 
      | user_name             | password             | RequestFile             | accesstoken    | typeofcustomer  |
      | baofam_lasapisys2sys7 | ZS79qm3y1ExJ9cG/6!N9 | ValidACEAPIRequest.json | $.access_token | customer        |
      | baofam_lasapisys2sys8 | TrfH+GKK4[o+q3CXcXTC | ValidACEAPIRequest.json | $.access_token | customersupport |
      | wfbmn_lasapisys2sys5  | LCH?ff4JObM#RZ5@M%2[ | ValidACEAPIRequest.json | $.access_token | customer        |

