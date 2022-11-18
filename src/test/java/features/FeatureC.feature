Feature: Validation userid 5

  @new @featureC @omit
  Scenario Outline: Send a valid Request to get user details

    Given I send a request GET to users get id <id> validate that name and username are not empty
    Examples:
      | id |
      | 5  |