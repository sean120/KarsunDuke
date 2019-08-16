@UATSmokeTest
Feature: Ace Api 2.0 Endpoint Url

  Scenario Outline: User sends a valid ace api 2.0 request and receives a stubbed response
     Given ACEAPI2.0 request payload in the file "017_ULADRequest1009CircleDrive.json"
    Then ACEAPI2.0 returns HTTP status code <returnCode>

    Examples: 
      | requestFileName                         | returnCode |
      | 012_LPARefinanceAceEligible_205558_ULAD |                  200 |
