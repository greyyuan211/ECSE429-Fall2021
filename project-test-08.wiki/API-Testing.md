> **API level test plan describing the quality assurance strategies for testing the API endpoints of the application.** This section contains the API testing plan and report for the test suite. It aims to investigate the _controller_ classes.

## Plan
### Testing Goals 
The API testing objective is to ensure that the provided GET and POST endpoint commands accurately access and update the web application contents, return the expected status codes, and provide the appropriate model/view page. MockMvc class is part of the Spring WebMvcTest framework which aids in testing the controllers explicitly starting a Servlet container. The controller test suite classes make use of this MockMvc to execute JUnit test cases aimed at testing the REST controller methods.

### Test Coverage Objectives:
> Controller Classes
>- Total Method Coverage: 85%
>- Total Line Coverage: 85%
>- Total Branch Coverage: 85%

## Report
No failing test cases have been observed. The severity of the lack of code coverage for the `processCreation()` method in `PetController.java` is minimal. All branches in the method have been tested and the fail occurs because not all combinations of the if statement condition are present in the controller tests. 

### Controller Classes 
#### IntelliJ IDEA Code Coverage Report
![overall_intellij](https://user-images.githubusercontent.com/59708333/141685968-73386dff-7a26-4755-90bc-b5d60da06608.png)
<br>

________

#### Jacoco Code Coverage Report
![overall_jacoco](https://user-images.githubusercontent.com/59708333/141685977-cfc296d0-1929-4ca6-916c-11af56dc5faf.png)

##### [Owner Controller Test Class](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java) <br>
![owner](https://user-images.githubusercontent.com/59708333/141685991-4b3e0543-9d98-4944-8ff3-48ddced5a865.png) <be>
##### Non-trivial cases
**testProcessFindFormByLastNameMultipleOwners()**  
Here the goal is to test the ability of the find form method to find the owners with the same last name. So, we first create the two owners with the constructor and then set both names to Hancock. Then, use the endpoint to send the request to create these two owners. The status code 200 is returned and the returned list includes both owners that are created. So, the situation is handled correctly.

##### [Vet Controller Test Class](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/controller/VetControllerTests.java) <br>
![vet](https://user-images.githubusercontent.com/59708333/141686007-09e6be35-291c-4147-9453-bbed4f72f733.png) <be>
##### Non-trivial cases

**testShowResourcesVetList()**  
The goal of this test is to show if we can get the vet list successfully with /vets. In the setup section, we have created a vet named Helen and we want to test this by getting Helen from the vet list. The result is the scenario is handled by the controller: the result of the status code is 200 and the vet list is successfully got with the pre-created vet objects in it.

##### [Visit Controller Test Class](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/controller/VisitControllerTests.java) <br>
![visit](https://user-images.githubusercontent.com/59708333/141686013-bd475fd2-0c04-4218-bb82-251307649849.png) <be>
##### Non-trivial cases

**testProcessNewVisitFormNonExistentPetId()**  
In this test, we are trying to create a duplicate visit on the same pet. So, we will input the same pet id and check if the controller handles this special case. With 2 pet with the same pet id created, the controller does not handle this case and pass the duplicate pet id directly. So, if we check the status code and the web response, we see that the duplicate visit is created which is not desired. The expected outcome is to raise an error on the web page saying the visit has been created under the same pet. Further improvement can be asking the user for proof of human beings by a quick non-robot test. If the test is passed, the users can continue. Otherwise, this is a serious security problem, which may cause the website to be crushed by numerous input from attackers or robots. Further behavior on how the system responds to these kinds of inputs is shown in non-functional tests.

##### [Crash Controller Test Class](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/controller/CrashControllerTests.java) <br>
![crash](https://user-images.githubusercontent.com/59708333/141686203-31e7825a-c2f2-4ec3-a7f3-61cf07303c9d.png) <be>
##### Non-trivial cases

**testGetOups()**  
This method aims to determine if the crash page is shown only when there are run-time exceptions or other exceptions. The input is an error message and the expected outcome is the page is shown when the exception is thrown. The result shows that this does work.

##### [Pet Controller Test Class](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/controller/PetControllerTests.java) <br>
![pet](https://user-images.githubusercontent.com/59708333/141686216-6064088d-c5ff-4c9a-b5c6-2d154ace8912.png) <be>
##### Non-trivial cases

**testInitCreationFormOwnerNonexistent()**  
To add to the valid owner creation errors, we add another test for creating a pet for a nonexistent owner. The command will have errors but it will not throw one. This is bad because the code will be very easily broken by users, and it will be very difficult for further development and maintenance.

**testProcessCreationForm()**  
There is a 25% missing branch coverage in processCreationForm() because of the following if-condition is highly-nested and therefore hard to access:  
```
if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
			result.rejectValue("name", "duplicate", "already exists");
}
```
As the code coverage block has shown us, the content of the if-statement is executed but the condition itself is not fully executed。 This is because even we have satisfied all the conditions in the logic statement, only a portion of the code is executed and the program knows the if-condition has been met. So, the program would just continue to the content of the if-statement and leave the rest of the if-condition untested. 

##### [Welcome Controller Test Class](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/controller/WelcomeControllerTests.java) <br>
![welcome](https://user-images.githubusercontent.com/59708333/141686222-6078dd39-d141-4fb2-b9b3-b8292bc0912d.png) <br>
This test is very similar to the crash controller test. 
