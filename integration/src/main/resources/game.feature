@game1 @product_triai
Feature: Customer create & confirm

  Scenario Outline: Create customer & confirm
    
    And GET /game/play on RTS from ROCK into CustomerResult<remember>
    Then CustomerResult<remember> should match
      """
        {
          "success": true
        }
      """
    
    And GET /game/play on RTS from PAPER into CustomerResult<remember>
    Then CustomerResult<remember> should match
      """
        {
          "success": true
        }
      """
     
    
    And GET /game/play on RTS from SCISSORS into CustomerResult<remember>
    Then CustomerResult<remember> should match
      """
        {
          "success": true
        }
      """  
      
    Examples: Customers
      | email            | firstName | lastName | username | remember |
      | test@example.org | Adam      | Smith    | testUser | C        |