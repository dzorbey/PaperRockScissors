@game1 @game_triai
Feature: Paper-Rock-Scissors GamePlay

  Scenario Outline: Paper-Rock-Scissors GamePlay
    
    And GET /game/play on Gameplay into GameResult<remember>
    Then GameResult<remember> should match
      """
        {
          "result": "I am from properties file"
        }
      """
    
     
      
      
    Examples: Customers
      | email            | firstName | lastName | username | remember |
      | test@example.org | Adam      | Smith    | testUser | C        |