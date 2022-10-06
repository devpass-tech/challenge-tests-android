Feature: Launch List

  Scenario: User should see launch list when opened the app
    Given The app started
    And Wait for list is visible
    Then I see launch list screen

  Scenario Outline: User can click in a item and navigate to LaunchActivity
    Given The app started
    And Wait for list is visible
    And I see launch list screen
    When I click in <position>
    Then I see LaunchActivity with "<title>"
    Examples:
      | position | title     |
      | 0        | FalconSat |
      | 9        | CRS-2     |
      | 19       | DSCOVR    |
