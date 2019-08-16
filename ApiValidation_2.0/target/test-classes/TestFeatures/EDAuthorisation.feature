@Iteration01
Feature: ACE API User Authorization with ED

  @SMOKETEST_ED
  Scenario Outline: User sends ACE Eligibility request with a valid username password and a valid combination of app and policyDirectorIdentifier
    Given Valid ACE API request with the "<user_name>" and "<password>" passed as header and the request has valid combination of app as "<app>" and policyDirectorIdentifier as "<policyDirectorIdentifier>"
    And executing "<typeoftc>" type of test case
    When Request is performing "<operation>" Sys-to-Sys service
    Then Service validates the combination with ED and responds with an Acknowledement contains counterpartyid and userroles
    And "<counterpartyjsonpath>" in response is correct for ED Authorisation
    And "<userrolesjsonpath>" in response is correct for ED Authorisation

    Examples: 
      | user_name         | password | app    | policyDirectorIdentifier | operation | typeoftc | counterpartyjsonpath                              | userrolesjsonpath                      |
      | Freddie_AllRole10 | Welcome! | LASAPI | baofam_lasapisys2sys     | GET       | positive | $.resources[1].attributes.LegacyAccountIdentifier | $.resources[1].attributes.UserRoleName |

  #   | Freddie_AllRole10 | Welcome! | LASAPI | baofam_lasapisys2sys4    | GET       | positive | $.resources[1].attributes.LegacyAccountIdentifier | $.resources[1].attributes.UserRoleName |
  #  | Freddie_AllRole10 | Welcome! | LASAPI | baofam_lasapisys2sys5    | GET       | positive | $.resources[1].attributes.LegacyAccountIdentifier | $.resources[1].attributes.UserRoleName |
  #  | Freddie_AllRole10 | Welcome! | LASAPI | baofam_lasapisys2sys14   | GET       | positive | $.resources[1].attributes.LegacyAccountIdentifier | $.resources[1].attributes.UserRoleName |
  Scenario Outline: User sends ACE Eligibility request with invalid username password and invalid combination of app and policyDirectorIdentifier
    Given Invalid ACE API request with the "<user_name>" and "<password>" passed as header and the request has invalid combination of app as "<app>" and policyDirectorIdentifier as "<policyDirectorIdentifier>"
    And executing "<typeoftc>" type of test case
    When Request is performing "<operation>" Sys-to-Sys service
    Then Service validates the combination with ED and responds with an Acknowledement contains counterpartyid and userroles

    Examples: 
      | user_name         | password | app | policyDirectorIdentifier | operation | typeoftc |
      | Freddie_AllRole10 | Welcome! | LPA | baofam_btomlpatest003    | POST      | negative |
      | Freddie_AllRole   | Welcome! | LPA | baofam_btomlpatest003    | GET       | negative |
      | Freddie_AllRole10 | Welcom   | LPA | baofam_btomlpatest003    | GET       | negative |
      | Freddie_AllRole   | Welcome! | LPA | baofam_btomlpatest003    | POST      | negative |
      | Freddie_AllRole10 | Welcom   | LPA | baofam_btomlpatest003    | POST      | negative |
      | Freddie_AllRole10 | Welcome! | ABC | baofam_btomlpatest003    | GET       | negative |
      | Freddie_AllRole10 | Welcome! | LPA | baofam_btomlpate         | GET       | negative |
