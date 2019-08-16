@Iteration
Feature: Ace Api Response

  Scenario Outline: - User Story- B-81324 - As an API user, I want to send to LOS customers with ACE decision and associated messages so that LOS customers
    can utilize the details.
    
    User sends ACE Eligibility request with a valid username proxymessageid and a valid JSON Request

    Given Valid url "<requestUrl>" and valid JSON "<requestfilepath>" ACE API request with "<user_name>" and "<proxymessageid>" passed as header
    And executing "<typeoftc>" test case
    When Request is performing "<operation>" on ACE API
    Then Service sends back responsecode
    Then User sees the expected "<aceDecision>" value in the response
    Given Return Json response from Mongo
    Then Validate Json "<aceDecision>" value in the response from Mongo
    Then Validate Json "<addressLineText>" value in the response from Mongo
    Then Validate Json "<cityName>" value in the response from Mongo
    Then Validate Json "<postalCode>" value in the response from Mongo
    Then Validate Json "<fipsstateAlphaCode>" value in the response from Mongo
    Then Validate Json "<messageCode1>" value in the response from Mongo
    Then Validate Json "<messageDescription1>" value in the response from Mongo
    Then Validate Json "<messageCode2>" value in the response from Mongo
    Then Validate Json "<messageDescription2>" value in the response from Mongo
    Then Validate that the JSON response value "<elementName1>" equals "<elementValue1>"
    Then Validate that the JSON response value "<elementName2>" equals "<elementValue2>"
    Then Validate that the JSON response value "<elementName3>" equals "<elementValue3>"
    Then Validate that the JSON response value "<elementName4>" equals "<elementValue4>"
    Then Validate that the JSON response value "<elementName5>" equals "<elementValue5>"

    Examples: 
      | requestUrl       | user_name            | proxymessageid | operation | typeoftc | requestfilepath | aceDecision | addressLineText     | cityName    | postalCode | fipsstateAlphaCode | messageCode1 | messageDescription1             | messageCode2 | messageDescription2                 | elementName1 | elementValue1 | elementName2               | elementValue2 | elementName3    | elementValue3       | elementName4 | elementValue4 | elementName5 | elementValue5 | elementName6       | elementValue6 | elementName7 | elementValue7 | elementName8        | elementValue8                   | elementName9 | elementValue9 | elementName10       | elementValue10                      |
      | ACEAPIBaseUrlUAT | baofam_lasapisys2sys |            123 | POST      | positive | validrequest    | ELIGIBLE    | 123 Notareal Street | Chapel Hill |      27514 | NC                 | ACE_001      | Some good information text here | ACE_002      | Some good information text here too | aceDecision  | cool          | decisionExpirationDateTime | NoValue       | addressLineText | 123 Notareal Street | cityName     | Chapel Hill   | postalCode   |         27514 | fipsstateAlphaCode | NC            | messageCode1 | ACE_001       | messageDescription1 | Some good information text here | messageCode2 | ACE_002       | messageDescription2 | Some good information text here too |
