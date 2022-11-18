
Feature: Validation userid 10

  @ready @featureA
  Scenario Outline: Send a valid Request to get user details

    Given I send a request GET to the users to get id <id>
    And I send a request GET to posts and validate that userid <id> the titles and bodies are not empty
    Examples:
      | id |
      | 10 |
