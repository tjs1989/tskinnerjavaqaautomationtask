Feature: Listing Items
  Ensuring that a user can list items using the relevant endpoints

  Scenario: Listing all items
    Given An item has been added to the list
    When A user lists all items
    Then The list of all items is returned

  Scenario: Listing an item by ID
    Given a "Macbook 2020 Pro" item is created with the default specs
    When a 200 response code is returned
    And a "Macbook 2020 Pro" is created
    Then the created item ID is returned
    And the item can be retrieved by ID from the list by ID endpoint

  Scenario: Listing an item by an invalid ID
    Given a call to the list item by ID endpoint with the ID of "!@$FDERHHEH"
    Then a 200 response code is returned
    Then an empty response is seen from the list item by ID endpoint