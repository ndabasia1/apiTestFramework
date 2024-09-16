@scenarios @scenario1
Feature: Tests relating to scenario 1

  Background: Set-up the test state
    Given I make a GET request to the public endpoint

	@responseCode
  Scenario: Test the HTTP HTTP status code of the response is 200
    Then the HTTP status code of the response is 200
    
    
	@responseTime
  Scenario: Test the response time is less than 1000 milliseconds
    Then the response time of the request is less than 1000 milliseconds