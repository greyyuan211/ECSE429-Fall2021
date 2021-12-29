Feature: Add New  Owner
 As a receptionist, I would like to add a new pet owner 
 so they can become a clinic member and be able to book appointments

  	
  Scenario Outline: Create a new owner (Normal Flow)
  

  	When a receptionist attempts to create an account with first name "<firstName>" , last name "<lastName>" , address "<address>" , city "<city>" and telephone "<telephone>"
		Then the owner instance is created 

    Examples:
        | firstName    | lastName   | address        |     city     | telephone    |
        | Mamadou      | Mamadou    |25 boulanger    |Vancouver     |4383896287    |
        | Benteke0565  | comeonbrod |15 masson       |Laval         |5145687309    | 
        | ImpossibleIs | Nothing    |99 villeray     |Montreal      |4503265974    |

  Scenario Outline: Create a new owner with an incorrect telephone (Error Flow)
  
  
  	 When a receptionist attempts to create an account with first name "<firstName>" , last name "<lastName>" , address "<address>" , city "<city>" and telephone "<telephone>"
     Then the owner is not created

     Examples:
        | firstName    | lastName   | address        |     city     | telephone    | error                        |
        | Alphabe      | Mamadou    |25 boulanger    |Vancouver     |lfjweif       |owners/createOrUpdateOwnerForm|
        | Mathieuloeo  | comeonbrod |15 masson       |Laval         |514-514-2     |owners/createOrUpdateOwnerForm| 
        | NothingIsBro | Nothing    |99 villeray     |Montreal      |514str        |owners/createOrUpdateOwnerForm|