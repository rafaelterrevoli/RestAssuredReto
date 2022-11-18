
Feature: Validation userid 10

  @ready @featureA
  Scenario Outline: Send a valid Request to get user details

    Given I send a request GET to the users to get id <id> and name <name>
    And I send a request GET to posts and validate that userid <userid> the titles and bodies are not empty
    Examples:
      | id | userid |name                |
      | 9  | 10     | Clementina DuBuque |
