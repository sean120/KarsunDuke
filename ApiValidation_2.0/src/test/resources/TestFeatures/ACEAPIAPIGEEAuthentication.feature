@APIGEESITTEST
Feature: APIGEE Developer Portal ACE API Access Token

  @SMOKETEST @Iteration05SITEDSFirst
  Scenario Outline: User sends ACE API Access Token Request
    Given APIGEE Developer Portal ACE API Access Token request
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
      | content_type                      | grant_type | client_id                        | client_secret    | username              | password             | operation | accesstoken    |
      # | application/x-www-form-urlencoded | password   | m3qmWiMPGc1egD0py4KcDp1aTqCpKPO7 | TFLuyrXWf3n16GCE | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | POST      | $.access_token |
      | application/x-www-form-urlencoded | password   | QnqaRPWTQtgManGMEgXdq4opZXjW7WS7 | 6Wu1GMd5zGOrShMy | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | POST      | $.access_token |

  @RegressionSIT
  Scenario Outline: User sends Invalid ACE API Access Token Request
    Given APIGEE Developer Portal Invalid ACE API Access Token request
    And content type is "<content_type>" for APIGEE Developer Portal
    And passing granttype "<grant_type>" parameter
    And passing clientid "<client_id>" parameter
    And passing clientsecret "<client_secret>" parameter
    And passing username "<username>" parameter
    And passing pwd "<password>" parameter
    When Request is performing "<operation>" on APIGEE Gateway
    Then Service sends error "<responsecode>" and "<errordetails>"

    Examples: 
      | content_type                      | grant_type | client_id                        | client_secret    | username              | password             | operation | responsecode | errordetails       |
      # Wrong Client_id
      | application/x-www-form-urlencoded | password   | nFCT7gBQ5zvBjb5vQH4D6CI69nSaQ    | 6Wu1GMd5zGOrShMy | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | POST      |          401 | $.details[0].error |
      #GET Instead of POST
      | application/x-www-form-urlencoded | password   | QnqaRPWTQtgManGMEgXdq4opZXjW7WS7 | 6Wu1GMd5zGOrShMy | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | GET       |          404 | $.details[0].error |
      #Wrong Username
      | application/x-www-form-urlencoded | password   | QnqaRPWTQtgManGMEgXdq4opZXjW7WS7 | 6Wu1GMd5zGOrShMy | baofam_lasap2sys4     | fJ6%@9o=[EwB2Pr$VjUp | POST      |          401 | $.details[0].error |
      #Wrong Password
      | application/x-www-form-urlencoded | password   | QnqaRPWTQtgManGMEgXdq4opZXjW7WS7 | 6Wu1GMd5zGOrShMy | baofam_lasapisys2sys4 | fJ6%@9o=[EwBjUp      | POST      |          401 | $.details[0].error |
