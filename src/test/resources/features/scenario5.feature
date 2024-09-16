@scenarios @scenario5
Feature: Tests relating to scenario 5

  Background: Set-up the test state
    Given I make a GET request to the public endpoint

  @responseHeaderPresent
  Scenario: Test a Date value gets returned in the response headers
    Then a "Date" value is returned within the response headers