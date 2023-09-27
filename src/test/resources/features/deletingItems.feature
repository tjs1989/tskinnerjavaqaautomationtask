Feature: Deleting items
  Confirming that an item can be successfully deleted

  Scenario: Deleting an item
    Given a "Macbook 2020 Pro" item is created with the default specs
    And a 200 response code is returned
    And a "Macbook 2020 Pro" is created
    And the created item ID is returned
    When the created item is deleted
    And a 200 response code is returned
    And a successful delete message is received
    And a call to to get the item by predefined Id is made
    Then an empty response is seen from the list item by ID endpoint