@Customers1 @Customer_create_&_confirm
Feature: Customer create & confirm

  Scenario Outline: Create customer & confirm
    Given RTS is running
      """
      {}
      """
    And GET /product/test on RTS into CustomerResult<remember>
    Then CustomerResult<remember> should match
      """
        {
          "id": "${results['Customer<remember>']['id']}",
          "email": "${results['Customer<remember>']['email']}",
          "username": "<username>",
          "firstName": "<firstName>",
          "sendElectronicInvoiceAccepted": "true", 
          "gender": "FEMALE",
          "pe" : "PE"
        }
      """
    
     
      
      
    Examples: Customers
      | email            | firstName | lastName | username | remember |
      | test@example.org | Adam      | Smith    | testUser | C        |