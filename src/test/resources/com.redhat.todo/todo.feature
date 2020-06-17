Feature: Todo
  Scenario: A Todo can be created
    Given a todo object with the following data:
    | name | description | completed | date                     |
    | test | test        | false     | 2020-06-17T20:49:35.000Z |
    When I create a todo
    Then I should get a response of 201