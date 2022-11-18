Feature: Validation userid 1

  @debug @featureB @omit
  Scenario Outline: Send a valid Request to get user details

    Given I send a request GET to users get id <id> validate that name and username are not empty
    And I send a request GET to posts and validate that userid <userid> the titles and bodies are not empty print postId
    And I look for all the comments of the postid print the name and email of the first comment
    Examples:
      | id | userid |
      | 1  | 1      |