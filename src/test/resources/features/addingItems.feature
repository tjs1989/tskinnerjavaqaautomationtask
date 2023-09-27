Feature: Adding Items
  Ensuring that a user can add items using the relevant endpoint

  Scenario: Creating an Item
    Given a "Alienware Aurora R15" item is created
    And the CPU model is "Intel iCore i7"
    And the item has a price of 1999.99
    And the item was made in the year 2023
    And the item has a hdd capacity of "1TB"
    When the request to add the item is made
    Then a 200 response code is returned
    And a "Alienware Aurora R15" is created




