> **Roles and responsibilities of the quality assurance team members.** This section contains a general outline of the project timeline for creating the test plan, managing team workflow, and working on the project. 

### Timeline of Events
* **November 1 - November 2**: exploratory testing of the PetClinic application. Got together as a group, ran the application, and tested the front-end behaviour, as well as back-end API calls with Postman.
* **November 3 - November 4**: designed and finalized a test plan. Used Google Docs to write out all the classes and associated methods-to-test for each test level (unit, integration, API, acceptance, non-functional). 
* **November 5**: assigned responsibilities. Every team member got an almost equal amount of tests to do for unit, integration, API, and acceptance tests. It was important that all team members got a chance to work on as many different test levels as possible so that everyone could benefit learning from the project.
* **November 6**: created test stubs in classes and used GitHub issues to assign tasks. GitHub actions were integrated.
* **November 7-12**: everyone worked on their assigned tasks. Branches were used for each test class and pull requests were created for code reviews. 
* **November 13-14**: merging of branch code and code coverage analysis. Non-functional performance tests were written and wiki created.

### Roles and Responsibilities
|Name            |Test Level Responsibility | Other Responsibilities|
| :-------------| ------- | ------------- | 
|💻 Yacine Bouadi|unit (model + service), integration (model + persistence), API (controller), acceptance |Code reviews, setting up cucumber and gherkin automated build with maven, updating wiki.|
|💻 Zhanna Klimanova|unit (model + service), integration (model + persistence), API (controller), acceptance|Code reviews, merging branches, setting up GitHub actions, integrating Jacoco into maven, updating wiki, writing extra tests to increase branch code coverage. |
|🖥️ Mohamed Mohamed|unit (model + service), integration (model + persistence), API (controller), acceptance|Code reviews, updating wiki, writing extra tests to increase branch code coverage.|
|💻 Ruixin Su|unit (model + service), integration (model + persistence), API (controller), acceptance, non-functional|Code reviews, writing non-functional tests and generating reports, updating wiki. |
|💻 Linwei Yuan|unit (model + service), integration (model + persistence), API (controller), acceptance|Code reviews, setting up cucumber and gherkin automated build with maven, updating wiki, creating and populating an input data class. |

#### Unit Test Model Class 
|Class            |Tester(s) Responsible | 
| :-------------| ------- | 
|BaseEntityTests|Ruixin|
|NamedEntityTests|Ruixin|
|PersonTests|Zhanna|
|OwnerTests|Yacine, Mohamed, Zhanna|
|PetTests|Mohamed, Linwei, Zhanna|
|VetTests|Linwei|
|VisitTests|Linwei|

#### Unit Test Service Class 
|Class            |Tester(s) Responsible | 
| :-------------| ------- | 
|CacheConfigurationTests|Yacine|
|PetTypeFormatterTests|Yacine, Ruixin, Linwei, Zhanna|
|PetValidatorTests|Linwei, Zhanna|

#### Integration Test Model Class 
|Class            |Tester(s) Responsible | 
| :-------------| ------- | 
|OwnerTests|Ruixin, Zhanna|
|PetTests|Yacine, Mohamed, Linwei|
|VetTests|Ruixin|

#### Integration Test Persistence Class 
|Class            |Tester(s) Responsible | 
| :-------------| ------- | 
|OwnerRepositoryTests|Yacine, Zhanna|
|PetRepositoryTests|Zhanna, Mohamed, Ruixin|
|VisitRepositoryTests|Mohamed, Linwei|

#### API Test Persistence Class 
|Class            |Tester(s) Responsible | 
| :-------------| ------- | 
|CrashControllerTests|Zhanna|
|OwnerControllerTests|Zhanna, Yacine, Ruixin|
|PetControllerTests|Ruixin, Linwei, Mohamed|
|VetControllerTests|Mohamed|
|VisitController|Mohamed|

#### Acceptance Testing
|Feature        |Tester(s) Responsible | 
| :-------------| ------- | 
|Feature 1|Zhanna, Yacine|
|Feature 2|Ruixin, Linwei|
|Feature 3|Yacine, Mohamed|

#### Non-Functional Testing
|Entity            |Tester(s) Responsible | 
| :-------------| ------- | 
|Owner|Ruixin|
|Pet| Ruixin|

