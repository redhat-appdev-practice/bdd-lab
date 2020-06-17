Feature: Todo
  Scenario: A Todo can be created
    Given a todo object with the following data:
    | name | description | completed |
    | test | test        | false     |
    When I create a todo
    Then I should get a response of 201