Feature: Listing Objects
  Ensuring that a user can list all objects using the relevant endpoint

  Scenario: Listing all objects
    Given An object has been added to the list
    When A user lists all objects
    Then The list of all objects is returned

