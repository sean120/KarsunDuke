@Iteration01 @B-83929 
Feature: ED AUTHORIZATION - Integration and authorization for the Freddie approved Lenders with ED (Enterprise Directory)

  Scenario Outline: User Story- B-83929 TC001 - Positive Scenarios -  User sends ACE Eligibility request with a valid username password and a valid combination of app and policyDirectorIdentifier
    

    Given Valid ACE API request with the "<user_name>" and "<password>" passed as header and the request has valid combination of app as "<app>" and policyDirectorIdentifier as "<policyDirectorIdentifier>"
    And executing "<typeoftc>" type of test case
    When Request is performing "<operation>" Sys-to-Sys service
    Then Service validates the combination with ED and responds with an Acknowledement contains counterpartyid and userroles
    And The "<expectedAttribute1>" value is valid in the ED Authorization response
    And The "<expectedAttribute2>" value is valid in the ED Authorization response

    Examples: 
      | user_name         | password | app | policyDirectorIdentifier | operation | typeoftc | expectedAttribute1      | expectedAttribute2 |
      | Freddie_AllRole10 | Welcome! | LPA | baofam_btomlpatest003    | GET       | positive | LegacyAccountIdentifier | UserRoleName       |
      | Freddie_AllRole10 | Welcome! | LPA | baofam_btomlpatest004    | GET       | positive | LegacyAccountIdentifier | UserRoleName       |
      | Freddie_AllRole10 | Welcome! | LPA | baofam_btomlpatest005    | GET       | positive | LegacyAccountIdentifier | UserRoleName       |
      | Freddie_AllRole10 | Welcome! | LPA | baofam_btomlpatest006    | GET       | positive | LegacyAccountIdentifier | UserRoleName       |


  Scenario Outline: User Story- B-83929 - TC002 - Negative Scenarios -  User sends ACE Eligibility request with a valid username password and a valid combination of app and policyDirectorIdentifier

    Given Valid ACE API request with the "<user_name>" and "<password>" passed as header and the request has valid combination of app as "<app>" and policyDirectorIdentifier as "<policyDirectorIdentifier>"
    And executing "<typeoftc>" type of test case
    When Request is performing "<operation>" Sys-to-Sys service
    Then Service validates the combination with ED and responds with an Acknowledement contains counterpartyid and userroles

    Examples: 
      | wrongUser | Welcome! | LPA | baofam_btomlpatest003 | GET | negative |
      | wrongUser | Welcome! | LPA | baofam_btomlpatest004 | GET | positive |
      | wrongUser | Welcome! | LPA | baofam_btomlpatest005 | GET | negative |
      | wrongUser | Welcome! | LPA | baofam_btomlpatest006 | GET | negative |

  Scenario Outline: User Story - B-81434 - Positive Scenarios - As an ACE Developer, I want to integrate ED with URL so that ACE API can authorize the
    Freddie approved seller.
    
    User sends ED request and receives valid ED response

    Given Valid ACE API request with base url "<base_Url>" and with the "<user_name>" and "<password>" passed as header and the request has valid combination of app as "<app>" and policyDirectorIdentifier as "<policyDirectorIdentifier>"
    And executing "<typeoftc>" type of test case
    When Request is performing "<operation>" Sys-to-Sys service
    And The "<expectedUserRole>" value is valid in the ED Authorization response

    Examples: 
      | base_Url       | user_name         | password | app    | policyDirectorIdentifier | operation | typeoftc | expectedUserRole                             |
      | EDAuthBaseUrl2 | Freddie_AllRole10 | Welcome! | LASAPI | baofam_lasapisys2sys     | GET       | positive | Appraisal Waiver Pre-screen Customer Support |
      | EDAuthBaseUrl2 | Freddie_AllRole10 | Welcome! | LASAPI | 1natl_lasapisys2sys      | GET       | positive | Appraisal Waiver Pre-screen Customer         |


  Scenario Outline: User Story - B-81434 - Negative Scenarios - As an ACE Developer, I want to integrate ED with URL so that ACE API can authorize the
    Freddie approved seller.
    
    User sends ED request and receives valid ED response

    Given Valid ACE API request with base url "<base_Url>" and with the "<user_name>" and "<password>" passed as header and the request has valid combination of app as "<app>" and policyDirectorIdentifier as "<policyDirectorIdentifier>"
    And executing "<typeoftc>" type of test case
    When Request is performing "<operation>" Sys-to-Sys service

    Examples: 
      | base_Url       | user_name | password | app    | policyDirectorIdentifier | operation | typeoftc |
      | EDAuthBaseUrl2 | wrongUser | Welcome! | LASAPI | baofam_lasapisys2sys     | GET       | negative |
      | EDAuthBaseUrl2 | wrongUser | Welcome! | LASAPI | 1natl_lasapisys2sys      | GET       | negative |
