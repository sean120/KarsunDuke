@Iteration0111TEMP
Feature: ACE API: Cardinality check

  Scenario Outline: Want to limit the repetition of the data elements to a cardinality of 1
    Given JSON file "<aceapirequestfilename>" is present
    Then Check for containers for cardinality in "<aceapirequestfilename>"
      | Name                                              | Element                                       |
      | Service Request Container                         | serviceContainer                              |
      | Loan Purpose Type Container                       | loanPurposeType                               |
      | Property Estimated value or Sales Contract Amount | propertyEstimatedValue or salesContractAmount |

    Examples: 
      | aceapirequestfilename |
      | ACEAPIRequest.json    |
