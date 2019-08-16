@RegressionSIT
Feature: ACE API: Prefix $ Sign to Max Loan Amount

  Scenario Outline: As ACE API User, I want the value for the element Maximum Authorized Loan Amount to be refixed with a $ sign
    Given "<user_name>" and "<password>" combinations to get ACE Access Token at "<accesstoken>"
    And APIGEE For ACE and a Valid "<RequestFile>"
    When Sending our request to ACE with the valid access token
    Then Get ACE API response
    Then Check for element to contain sign or not, according to acceptance criteria

    Examples: 
      | user_name             | password             | accesstoken    | RequestFile     |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | inputfile11.json |

  Scenario Outline: As ACE API User, I want the value of Loan Amount and Expiration Date to be empty strings for Not Eligibile
    Given "<user_name>" and "<password>" combinations to get ACE Access Token at "<accesstoken>"
    And APIGEE For ACE and a Valid "<RequestFile>"
    When Sending our request to ACE with the valid access token
    Then Get ACE API response
    Then Check for element for Eligibility and values

    Examples: 
      | user_name             | password             | accesstoken    | RequestFile               |
      | baofam_lasapisys2sys4 | fJ6%@9o=[EwB2Pr$VjUp | $.access_token | AceNotEligiblePurchase.json |
