Feature: ACE API User Authorization with ED for disabled accounts

  Scenario Outline: User sends ACE Eligibility request with access disabled username password and a valid combination of app and policyDirectorIdentifier
    Given ED "<user_name>" and "<password>" combinations to get ACE API Access Token at "<accesstoken>" path
    Then Apigee sends error "<respcode>" and "<errordata>"

    Examples: 
      | user_name              | password             | accesstoken    | respcode | errordata          |
      | baofam_lasapisys2sys16 | 6g48(Vkn3J~@7fFSdKOt | $.access_token |      401 | $.details[0].error |
      | baofam_lasapisys2sys15 | /QS0SrqmVgk(mpiG(Ub2 | $.access_token |      401 | $.details[0].error |
      | baofam_lasapisys2sys6  | 4aNk/?krf+W591jmvMt6 | $.access_token |      401 | $.details[0].error |
      | test_lasapisys2sys     | 7wd4yY8488iyPIkJ[Z4w | $.access_token |      401 | $.details[0].error |
