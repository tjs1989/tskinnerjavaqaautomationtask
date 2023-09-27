Feature: Listing Items
  Ensuring that a user can list all items using the relevant endpoint

  Scenario: Listing all Items
    Given An item has been added to the list
    When A user lists all items
    Then The list of all items is returned

