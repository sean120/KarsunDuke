@SMOKETEST-20
Feature: Health Check Test for ACE API

Scenario: User validates the ACE API 2.0 Healthcheck URL is working 
	Given Webservice URL for ACE API 2.0 in the Properties file and payload "ACEAPI20Request-LQA-02-v2.json"
	When Invoke GET method on ACE API 2.0 healthcheck URL 
	Then User validates the response code returned by ACE API 2.0 matches the expected value 200
	