@Iteration02 @Regression @B-81434 @B-81307 @B-84976 @UATREGRESSION 
Feature: ED INTEGRATION - When using active user accounts in ED, Ace api should return the expected response

  Scenario Outline: User Story: B-81434 and B-81307 - Scenario 0001a - Positive Scenario - Appraisal Waiver Pre-screen Customer - User receives access token by sending a valid request to the APIGEE gateway
    Given User sets APIGEE Portal ACE API Access Token request "<requestURL>" url
    And content type is "<content_type>" for APIGEE Developer Portal
    And passing granttype "<grant_type>" parameter
    And passing clientid "<client_id>" parameter
    And passing clientsecret "<client_secret>" parameter
    And passing username "<username>" parameter
    And passing pwd "<password>" parameter
    When Request is performing "<operation>" on APIGEE Gateway
    Then Service sends back Access Token
    Then Store the "<accesstoken>" in ACE API Local

    Examples: 
      | requestURL                  | content_type                      | grant_type | client_id                        | client_secret    | username              | password             | operation | accesstoken    |
      | UatAPIGEEAccessTokenBaseUrl | application/x-www-form-urlencoded | password   | OhAEtpEKxPCcUFFx9i1myZ4RjWhTwGQr | 9OqWuaafuGcXApf7 | baofam_lasapisys2sys26 | OBK]z/R4f98=aheWoH?j | POST      | $.access_token |

  Scenario Outline: User Story: B-81434 and B-81307 - Scenario 0001b - Positive Scenario - User takes the access token and sends another request and receives a valid response
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
    Then Service sends back ResponseCode
    Then Validate that the JSON response value "<elementName1>" equals "<elementValue1>" in the response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile        | elementName1                            | elementValue1 |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | ValidAddress4.json | appraisalWaiverPrescreenEligibilityType | NoValue       |

  Scenario Outline: User Story: B-81434 - Scenario 0002a - Negative Scenario - Removal of Appraisal Waiver Pre-screen Customer Support Role - User receives access token by sending a valid request to the APIGEE gateway
    Given User sets APIGEE Portal ACE API Access Token request "<requestURL>" url
    And content type is "<content_type>" for APIGEE Developer Portal
    And passing granttype "<grant_type>" parameter
    And passing clientid "<client_id>" parameter
    And passing clientsecret "<client_secret>" parameter
    And passing username "<username>" parameter
    And passing pwd "<password>" parameter
    When Request is performing "<operation>" on APIGEE Gateway
    Then Store the "<accesstoken>" in ACE API Local

    Examples: 
      | requestURL                  | content_type                      | grant_type | client_id                        | client_secret    | username              | password             | operation | accesstoken    |
      | UatAPIGEEAccessTokenBaseUrl | application/x-www-form-urlencoded | password   | OhAEtpEKxPCcUFFx9i1myZ4RjWhTwGQr | 9OqWuaafuGcXApf7 | baofam_lasapisys2sys5 | 2#=W?U0x*?MHr%B$mHNv | POST      | $.access_token |

  Scenario Outline: User Story: B-81434 - Scenario 0002b - Negative Scenario - Removal of Appraisal Waiver Pre-screen Customer Support Role - User takes the access token and sends another request and receives a invalid response
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When Request is performing "<operation>" on APIGEE Gateway ACE API
    Then Service sends back "<statuscode>"

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile        | statuscode | errorMessage                                         |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | ValidAddress4.json |        401 | User profile is not authorized to access the system. |

  Scenario Outline: User Story: B-81434 Scenario 0003a - Negative Scenario - Removed Access User - User receives access token by sending a valid request to the APIGEE gateway with a user with removed access.
    Given User sets APIGEE Portal ACE API Access Token request "<requestURL>" url
    And content type is "<content_type>" for APIGEE Developer Portal
    And passing granttype "<grant_type>" parameter
    And passing clientid "<client_id>" parameter
    And passing clientsecret "<client_secret>" parameter
    And passing username "<username>" parameter
    And passing pwd "<password>" parameter
    When Request is performing "<operation>" on APIGEE Gateway
    Then Service sends back Access Token
    Then Store the "<accesstoken>" in ACE API Local

    Examples: 
      | requestURL                  | content_type                      | grant_type | client_id                        | client_secret    | username              | password             | operation | accesstoken    |
      | UatAPIGEEAccessTokenBaseUrl | application/x-www-form-urlencoded | password   | OhAEtpEKxPCcUFFx9i1myZ4RjWhTwGQr | 9OqWuaafuGcXApf7 | baofam_lasapisys2sys14  | rJ(jNB4/Q72!x)$]5XD4 | POST      | $.access_token |

  Scenario Outline: User Story: B-81434 - Scenario 0003b - Negative Scenario - Removed Access User - User takes the access token and sends another request and should receive the expected error message.
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When Request is performing "<operation>" on APIGEE Gateway ACE API
    Then Service sends back "<statuscode>"
    Then User sees the expected "<errorMessage>" value in the APIGEE ace response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile        | errorMessage                                         | statuscode |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | ValidAddress4.json | User profile is not authorized to access the system. |        401 |

  Scenario Outline: User Story: B-81434 Scenario 0004a - Negative Scenario - Removed Access User - User receives access token by sending a valid request to the APIGEE gateway with a user with removed access.
    Given User sets APIGEE Portal ACE API Access Token request "<requestURL>" url
    And content type is "<content_type>" for APIGEE Developer Portal
    And passing granttype "<grant_type>" parameter
    And passing clientid "<client_id>" parameter
    And passing clientsecret "<client_secret>" parameter
    And passing username "<username>" parameter
    And passing pwd "<password>" parameter
    When Request is performing "<operation>" on APIGEE Gateway
    Then Service sends back Access Token
    Then Store the "<accesstoken>" in ACE API Local

    Examples: 
      | requestURL                  | content_type                      | grant_type | client_id                        | client_secret    | username              | password             | operation | accesstoken    |
      | UatAPIGEEAccessTokenBaseUrl | application/x-www-form-urlencoded | password   | OhAEtpEKxPCcUFFx9i1myZ4RjWhTwGQr | 9OqWuaafuGcXApf7 | baofam_lasapisys2sys5 | 2#=W?U0x*?MHr%B$mHNv | POST      | $.access_token |

  Scenario Outline: User Story: B-81434 - Scenario 0004b - Negative Scenario - Removed Access User - User takes the access token and sends another request and should receive the expected error message.
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When Request is performing "<operation>" on APIGEE Gateway ACE API
    Then Service sends back "<statuscode>"
    Then User sees the expected "<errorMessage>" value in the APIGEE ace response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile        | errorMessage                                         | statuscode |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | ValidAddress4.json | User profile is not authorized to access the system. |        401 |

  Scenario Outline: User Story: B-81434 Scenario 0005a - Negative Scenario - Removed Access User - User receives access token by sending a valid request to the APIGEE gateway with a user with removed access.
    Given User sets APIGEE Portal ACE API Access Token request "<requestURL>" url
    And content type is "<content_type>" for APIGEE Developer Portal
    And passing granttype "<grant_type>" parameter
    And passing clientid "<client_id>" parameter
    And passing clientsecret "<client_secret>" parameter
    And passing username "<username>" parameter
    And passing pwd "<password>" parameter
    When Request is performing "<operation>" on APIGEE Gateway
    Then Service sends back Access Token
    Then Store the "<accesstoken>" in ACE API Local

    Examples: 
      | requestURL                  | content_type                      | grant_type | client_id                        | client_secret    | username              | password             | operation | accesstoken    |
      | UatAPIGEEAccessTokenBaseUrl | application/x-www-form-urlencoded | password   | OhAEtpEKxPCcUFFx9i1myZ4RjWhTwGQr | 9OqWuaafuGcXApf7 | baofam_lasapisys2sys2 | 8BN*K/1FWDwEoONQ)S]f | POST      | $.access_token |

  Scenario Outline: User Story: B-81434 - Scenario 0005b - Negative Scenario - Removed Access User - User takes the access token and sends another request and should receive the expected error message.
    Given APIGEE Developer Portal ACE API AccessToken and "<RequestFile>"
    And content type is "<content_type>" for APIGEE Developer Portal ACE API Request
    Given User sets APIGEE Portal ACE API request "<requestURL>" url
    When User sends "<operation>" request to ACE API through APIGEE
    Then Service sends back "<statuscode>"
    Then User sees the expected "<errorMessage>" value in the APIGEE ace response

    Examples: 
      | requestURL                  | content_type     | operation | RequestFile        | errorMessage                                         | statuscode |
      | UatAPIGEEAccessTokenBaseUrl | application/json | POST      | ValidAddress4.json | User profile is not authorized to access the system. |        401 |

       Scenario Outline: User Story: B-81434 - Scenario 0006a - Negative Scenario - Disabled user -User receives access token by sending a request to the APIGEE gateway as a disabled user.
    The client should see the expected error message,

    Given User sets APIGEE Portal ACE API Access Token request "<requestURL>" url
    And content type is "<content_type>" for APIGEE Developer Portal
    And passing granttype "<grant_type>" parameter
    And passing clientid "<client_id>" parameter
    And passing clientsecret "<client_secret>" parameter
    And passing username "<username>" parameter
    And passing pwd "<password>" parameter
    When Request is performing "<operation>" on APIGEE Gateway
    Then User sees the expected "<errorMessage>" value in the APIGEE response

    Examples: 
      | requestURL                  | content_type                      | grant_type | client_id                        | client_secret    | username           | password             | operation | accesstoken    | errorMessage                                                                                                                           |
      | UatAPIGEEAccessTokenBaseUrl | application/x-www-form-urlencoded | password   | OhAEtpEKxPCcUFFx9i1myZ4RjWhTwGQr | 9OqWuaafuGcXApf7 | test_lasapisys2sys | 7wd4yY8488iyPIkJ[Z4w | POST      | $.access_token | Invalid Username/Password combination, the provided combination of username and password is incorrect, please verify your credentials. |
      