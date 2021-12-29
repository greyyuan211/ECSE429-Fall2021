> **Acceptance level test plan describing the quality assurance strategies for user requirements engineering.** This section contains the acceptance testing plan and report for the test suite. The test suite aims to use the Gherkin language to define the _Cucumber Feature_ files and JUnit step definition tests for validating the feature files against the source code.

## Plan
The acceptance testing objective is to ensure that the main user requirements for the application are fulfilled. In particular, it is expected that the user stories are testable on the API level. When a user is interacting with the PetClinic application, the GET and POST endpoint commands accurately access and update the web application contents, return the expected status codes, and provide the appropriate model/view page for the user. <br>

**A design decision was made to consider the receptionist as the main user for the application. They are responsible for interacting with the application by adding pet owners, adding pets, creating visits, and editing information.**

| Feature |User Story |
|----| :----: | 
|Feature 1 | As a receptionist, I would like to add a new pet owner so they can become a clinic member and be able to book appointments. |
|Feature 2 | As a receptionist, I would like to add a new pet to an existing pet owner so that they can book appointments for that pet. | 
|Feature 3 | As a receptionist, I would like to add visits to the clinic so that veterinarians and owners can be up to date on the upcoming pet appointment.  | 

## Report


The CucumberFeaturesTest.java located under [CucumberFeaturesTest.java](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/features/CucumberFeaturesTest.java), is JUnit's entry point which is run with Cucumber.class present in the 'cucumber.api.junit'. In the file we provide the links to the .feature files located under [src/test/resources/features](https://github.com/McGill-ECSE429-Fall2021/project-test-08/tree/master/src/test/resources/features). The cucumber step definitions are located under the same package features' as follows: [CucumberStepDefinitions.java](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/features/CucumberStepDefinitions.java). The cucumber step definitions is used when executing the .feature files.



![Java mappings of the individual steps of the gherkin scenario elements](https://cdn.discordapp.com/attachments/902594782776422434/909649100100763750/unknown.png)
### Feature 1
This cucumber feature is located under [AddNewOwner.feature](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/resources/features/AddNewOwner.feature)
There are two scenarios, one success , one failure.
The first scenario verifies that when a valid owner is given as input, then the creation is successful.
The second scenario verifies that when a given owner is not valid, by having an incorrect telephone format, then the creation of the owner is unsuccessful.

Inputs of scenario one : 
| firstName    | lastName   | address        |     city     | telephone    |
| ----         |  :----:    | :----:         |       :----: | :----:       | 
| Mamadou      | Mamadou    |25 boulanger    |Vancouver     |4383896287    |
| Benteke0565  | comeonbrod |15 masson       |Laval         |5145687309    | 
| ImpossibleIs | Nothing    |99 villeray     |Montreal      |4503265974    |

Will all output successful creation.

Inputs of scenario 2:
| firstName    | lastName   | address        |     city     | telephone    | error                        |
| ----         |  :----:    | :----:         |       :----: | :----:       | :---:                        |
| Alphabe      | Mamadou    |25 boulanger    |Vancouver     |lfjweif       |owners/createOrUpdateOwnerForm|
| Mathieuloeo  | comeonbrod |15 masson       |Laval         |514-514-2     |owners/createOrUpdateOwnerForm| 
| NothingIsBro | Nothing    |99 villeray     |Montreal      |514str        |owners/createOrUpdateOwnerForm|

Will all output unsuccessful creation.
### Feature 2
This cucumber feature is located under [AddNewPet.feature](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/resources/features/AddNewPet.feature)
There are two scenarios, one success , one failure.
The initial setup assumes that an owner and its pet already exists.
The first scenario verifies that when a valid pet is given as input, then the creation of that pet to the owner is successful.
The second scenario verifies that when a given pet is not valid by being a duplicate of the pet already created, then the creation of the new pet is unsuccessful.

Inputs of scenario one:
|name | birthday  | type |
| --- | ---       | ---  |
|  Joe  | 2019-05-30| Cat  |
|  Ella | 2018-11-11| Bird |

Will all output a successful creation

Inputs of scenario two:
| name | birthday  | type   | name1 | birthday1  | type1   | reject    |
| ---  | ---       | ---    | ---   | ---        | ---     | ---       |
| Emma | 2020-03-30| Hamster| Emma  | 2020-03-30 | Hamster | duplicate |

Will output an unsuccessful creation due to duplicate.
### Feature 3
This cucumber feature is located under [AddVisits.feature](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/resources/features/AddVisits.feature)
There are two scenarios, one success , one failure.
The initial setup assumes that an owner and its pet already exists.
The first scenario verifies that when a valid visit is given as input, then the creation of that visit to the pet and owner is successful.
The second scenario verifies that when a given visit is not valid by having an empty description or an incorrect date, then the creation of the visit is unsuccessful.

Inputs of scenario one:
| date       | description | petId |
| ---        | ---         | ---   |
| 2021-09-05| first visit |14|
| 2021-09-12| second visit |14|

Will all output a successful creation.

Inputs of scenario two:
| date       | description | petId |
| ---        | ---         | ---   |
| 2021-09-05|             | 14     |
| 2021-09-122|second visit| 14     |

Will all output an unsuccessful creation due to an empty description and an invalid date.

### Discussion on failure

According to the result of the features, the normal flow of creation of a pet owner's pet can be run successfully with no problem. However, the duplicate pet creation is not handled by the controller, so users may create many duplicate pet under one owner. This may happen when the pet clinic website is under attack, and a simple web bots can create numerous inputs to the pet creation process. Since there are no detection of duplication and user verification step, it is very easy to mess the database with delicate input causing extra work for the pet clinic receptionist.  Even worse, huge amount of overflow may lead the web to crash, in which case will be very difficult to bring the website back to normal. This can cause the developer much longer time to fix compared to adding a handling feature to the controller. The same is true for duplicate owners. This leads to invalidity of visits that can be added to duplicate owners or duplicate pets.

### Contribution to coverage goals

The acceptance tests are necessary to check the coverage of our testing. Because the three features that are written, basically cover the whole process of a user scenario. It describes how a costumer can register into the system as an owner, and how a pet can be created under the owner's name, and how we can add a visit to the clinic for a pet with a particular pet id. Not only we have created the normal flow for each feature, we have also created 1-2 error flow to see what could go wrong under each use case. The remaining acceptance tests that are not tested are, for example, getting a vet list to select from to book a visit. Test case like these are trivial, and will be difficult to have any error flow to tests. Therefore, we did not include them. Majority of the use cases (approximately more than 80%) are covered.