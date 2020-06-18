Feature: Todo
  Scenario: A Todo can be created with Spring Tests
    Given a todo object with the following data:
    | name | description | completed | date                     |
    | test | test        | false     | 2020-06-17T20:49:35.000Z |
    When I create a todo
    Then I should get a response of 201

  Scenario: A Todo can be created using MVC Tests
    Given a todo object with the following data (MVC):
    | name | description | completed | date                     |
    | test | test        | false     | 2020-06-17T20:49:35.000Z |
    When I create a todo using MVC
    Then I should get a response of 201 using MVC