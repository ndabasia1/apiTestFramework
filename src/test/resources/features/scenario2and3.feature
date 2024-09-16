@scenarios @scenario2and3
Feature: Tests relating to scenario 2 and 3

  Background: Set-up the test state
    Given I make a GET request to the public endpoint

  @fieldOccupied
  Scenario Outline: Test the ID field is never null or empty for all items in the array
    Then the <field Name> field is never null or empty for all items present in the array

  	Examples: 
      | field Name           |
      | "id"                 |
      | "title_list.primary" |

  @segmentTypeIsMusic
  Scenario: Test the segment_type field is always music for all items in the array
    Then the "segment_type" field for every track is always "music"