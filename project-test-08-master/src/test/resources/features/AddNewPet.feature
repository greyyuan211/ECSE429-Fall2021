Feature: Add New Pet  // TODO Ruixin Linwei


  Background:
    Given the owner with lastname Franklin exists
    And the owner has the following pet:
      | name | birthday  | type
      | Emma | 2020-03-30| Hamster
  Scenario Outline: The user registers a valid new pet to Franklin(Normal Flow)
    When the user attempts to register a pet with name "<name>" and birthday "<birthday>" and type "<type>" to the owner(Franklin)
    Then the pet with name "<name>" and birthday "<birthday>" and type "<type>" is successfully added to owner
    #And  the page will be redirected to owner infomration page.
    Examples:
      | name | birthday  | type |
      | Joe  | 2019-05-30| Cat  |
      | Ella | 2018-11-11| Bird |


  Scenario Outline: The user registers a duplicate pet to an owner(Error Flow)
#    Given the pet with name "<name>" and birthday "<birthday>" and type "<type>" is already reigstered to the owner
    When the user attempts to register a duplicate pet with name "<name>" and birthday "<birthday>" and type "<type>" to the owner(Franklin)
    Then pet creation fails with binding reject value "<reject>"

    Examples:
      | name | birthday  | type   | name1 | birthday1  | type1   | reject    |
      | Emma | 2020-03-30| Hamster| Emma  | 2020-03-30 | Hamster | duplicate |
#  Scenario Outline: The user registers a pet with invalid
#    When
#    Then
#
#    Examples:
