@scenarios @scenario4
Feature: Tests relating to scenario 4

  Background: Set-up the test state
    Given I make a GET request to the public endpoint

  @only1Playing
  Scenario: Test only one track in the list has “now_playing” set to true
    Then only one track has "offset.now_playing" set to true