Feature: Selenium HQ Pages
  As a UI test automation engineer,
  I want to visit the Selenium HQ page
  so that I can learn more about Selenium WebDriver
@seleniumTest
  Scenario: Review Technical References and Guides Section
    Given I am on the Selenium HQ page
    When I select the Documentation tab
    And I select the topic "Introduction"
    Then the "Test Automation for Web Applications" subtopic is displayed
