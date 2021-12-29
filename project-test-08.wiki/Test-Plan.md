> **High level test plan describing the quality assurance strategies for testing the software application.** This section contains the QA test plan which includes the general description of the testing goals, the predicted fault model, and test coverage goals. 

### Testing Goals
The test suite's goal is to ensure that the application works as intended on various levels of code operation: unit/component, integration, API, acceptance, and performance. Because the software under test is a web application that persists pet clinic data, the most important aspect to consider is its ability to perform CRUD operations when a user is interacting with the front-end. Therefore, it was decided that a high code coverage should be achieved for persistence and controller functionality. 

Although the main goal of the test plan is to achieve high code coverage for API testing, it was also considered important to assess the functionality of the underlying back-end components and their interoperability with each other. For that reason, it was decided to aim for good code coverage on the unit and integration testing levels.

Additionally, the test suite is fully automated on the unit, integration, acceptance, and API test levels with the maven build tool. All necessary test dependencies are added in the [pom.xml](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/pom.xml) file.  Jacoco code coverage reports can be generated on the local machine by running `mvn clean test` in the same directory as the pom.xml file and are found under the `./target/site/jacoco/` folder. The GUI report with the name `index.html` can be opened and interacted with via a browser tool. Finally, the project is also integrated with the GitHub Actions build tool to ensure quality production code on the master branch.

### Fault Model 
After performing a group exploratory testing session with the PetClinic application front-end and API calls, it was noted that the functionality of the Pet, Owner, Vet, and Visit classes should be prioritized. The reason being that the application's main features allow a user to find existing owners by last name, add new owners, edit owners personal information, add new pets, edit pet information, add new visits, and view information regarding the clinic's available veterinarians. The detailed description of each general web functionality and the expected faults is depicted in the table below. <br>

|Functionality|Related Class|Expected Faults|
| ----| :----: | :---: |
|Find Owner|Owner|Searching for a non-existent owner.|
|Add Owner|Owner|Adding an invalid telephone, leaving a field blank (null input),<br> and wrong input type in text field. |
|Edit Owner|Owner|Replacing with an invalid telephone, leaving a field blank (null input),<br> and wrong input type in text field.|
|Add New Pet|Pet, Owner|Adding an invalid birth date, adding a duplicate pet, leaving a field blank (null input). |
|Edit Pet|Pet|Replacing with an invalid birth date, adding a duplicate pet, leaving a field blank (null input).|
|Add Visit|Visit, Pet, Owner|Leaving the fields blank (null input), inputting an invalid date.|
|View List of Veterinarians|Vet|As this is solely a GET request, the only way a user can view the veterinarians is by selecting the appropriate tab on the application front-end. The veterinarians can only be added to the front-end through the back-end.|

### Test Coverage Objectives:

>- Total Method Coverage: 85%
>- Total Line Coverage: 80%
>- Total Branch Coverage: 80%

<br>

|Component|Minimum Method Coverage Goal|Minimum Line Coverage Goal|Minimum Branch Coverage Goal |
| ----| :----: | :---: | :---: | 
|Controller|80%|85%|80%|
|Service |80%|85%|80%|
|Model |80%|85%|80%|



