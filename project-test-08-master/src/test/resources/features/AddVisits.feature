Feature: Add Visits // TODO Filip Mohamed

  As a receptionist, I would like to add visits to the clinic so that veterinarians
  and owners can be up to date on the upcoming pet appointment.

  Background:
    Given an owner exists with the following details
    And the owner has the following pet:
      | name | birthday  | type    |
      |Emma|2020-03-30|hamster|

  Scenario Outline: Create a vist (Normal Flow)
    When a receptionist attempts to create a new visit with the date "<date>", description "<description>", petId "<petId>"
    Then the visit is created

    Examples:
      | date       | description | petId |
      |2021-09-05| first visit |14|
      |2021-09-12| second visit |14|

  Scenario Outline: Create a vist with an incorrect description or date (Error Flow)
    When a receptionist attempts to create a new visit with the date "<date>", description "<description>", petId "<petId>"
    Then the visit is not created

    Examples:
      | date       | description | petId |
      |2021-09-05|             | 14     |
      |2021-09-122|second visit| 14     |
