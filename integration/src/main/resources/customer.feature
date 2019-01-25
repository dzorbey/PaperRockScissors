@product1 @product_triai
Feature: Customer create & confirm

  Scenario Outline: Create customer & confirm
    
    And GET /product/test on RTS into CustomerResult<remember>
    Then CustomerResult<remember> should match
      """
        {
          "result": "I am from properties file"
        }
      """
    
     
      
      
    Examples: Customers
      | email            | firstName | lastName | username | remember |
      | test@example.org | Adam      | Smith    | testUser | C        |