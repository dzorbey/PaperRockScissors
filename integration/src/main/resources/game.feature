@game1 @product_triai
Feature: Customer create & confirm

  Scenario Outline: Create customer & confirm
    
    #The randomized computerSelection and according return conditions are tested within the MockGameServiceTest.java
    #This feature tests the functional api responses.
    
    #Test Api Endpoint by ROCK Input
    And GET /game/play on RTS from ROCK into GameResult1<remember>
    Then GameResult1<remember> should match
      """
        {
          "success": true,
          "userSelection": "ROCK"
        }
      """
    #Test Api Endpoint by PAPER Input
    And GET /game/play on RTS from PAPER into GameResult2<remember>
    Then GameResult2<remember> should match
      """
        {
          "success": true,
          "userSelection": "PAPER"
        }
      """
     
    #Test Api Endpoint by SCISSORS Input
    And GET /game/play on RTS from SCISSORS into GameResult3<remember>
    Then GameResult3<remember> should match
      """
        {
          "success": true,
          "userSelection": "SCISSORS"
        }
      """  
   
      
    Examples: Gameplay
      | UserInput    | computerInput |
      | ROCK         | PAPER         |