> **Applications of testing techniques used for developing the quality assurance test suite for various components and layers of the application.** This section contains an overview of the various testing techniques used 

## Exploratory Testing
The very first testing technique used for the project was a freestyle exploratory testing. The 2 hour predefined time-boxed testing session occurred on 01/11/2021 in which one person was screen sharing the PetClinic web application, while others suggested test cases and took notes.

### Session Notes
#### Charter
1. Familiarize the team members with the basic functionality of the PetClinic application.
2. Identify capabilities and areas of potential instability of the features.  
3. Test the various API commands and observe expected outputs with the Postman API platform.
4. Document and design a high-level list of test cases for the test suite.

#### Area:
The targeted endpoints will be GET and POST REST API requests. 

#### Environment:
Operating System: x86_64 Ubuntu Linux
Java openjdk version: "11.0.11" 2021-04-20
OpenJDK Runtime Environment: build 11.0.11+9-Ubuntu-0ubuntu2.20.04


#### Start Time:
19h00 01/11/2021

#### Testers:
Zhanna Klimanova: zhanna.klimanova@mail.mcgill.ca <br/>
Yacine Bouadi: yacine.bouadi@mail.mcgill.ca  <br/>
Mohamed Mohamed: mohamed.mohamed5@mail.mcgill.ca  <br/>
Ruixin Su: ruixin.su@mail.mcgill.ca <br/>
Linwei Yuan: linwei.yuan@mcgill.ca <br>

#### Duration:
120 minutes

#### Issues/Concerns:
- Creating a particularly large amount of pet, owner, visit, and vet objects will slow down the system.

#### Testing Ideas:
Based on the exploratory session, the following test ideas were derived:

- Generate large amounts of owner and pet object data to stress test the system -> [Actual performance test cases derived from this idea](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/performance/PerformanceTests.java).
- Automate the API testing process with MockMVC Spring MVC test framework -> [Actual controller test cases derived from this idea](https://github.com/McGill-ECSE429-Fall2021/project-test-08/tree/master/src/test/java/org/springframework/samples/petclinic/controller). 


## Black Box Techniques for API Testing
The second testing technique used for the project was boundary value analysis. Although this technique was not implemented in all its glory with appropriate graphs and models, the general idea was applied when designing the API level test suite. In particular, the tests were derived based on the possible extreme values that could occur while calling CRUD operations. 

Since there was only GET and POST requests, it was determined that each endpoint must be called with the nominal values (ones that will pass), extreme cases (valid/non-existent inputs), and null inputs. It was decided that for the sake of time, out of the `min, min+, nom, max-, max` conventional boundary analysis inputs only 3 would be considered: `min, nom, max`. The full set of test cases can be observed in following table.

#### Individual Test Cases for API operations

|GET API requests|Implemented Tests | 
| :-------------| ------- | 
|/oups|[Test Success Nominal](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/CrashControllerTests.java#L18)|
|/owners/new|[Test Success Nominal](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java#L158)|
|/owners/find|[Test Success Nominal](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java#L187)|
|/owners|[Test Success Empty Table](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java#L195)<br> [Test Success Non-Empty Table](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java#L202) <br> [Test Success Find By Last Name](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java#L217)|
|/owners/{ownerId}/edit|[Test Success Nominal](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java#L243)<br>[Test Success Valid Owner](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java#L275)<br> [Test Non-Existent Id](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java#L298)<br> [Test Invalid Id](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java#L313)<br> |
|/owners/{ownerId}|[Test Success Existent ID](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java#L381)<br>[Test Non-Existent Id](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java#L395)<br> [Test Invalid Id](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java#L415)<br>[Test Invalid Owner](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java#L286)|
|/owners/{ownerId}/pets/new|[Test Success Nominal](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/PetControllerTests.java#L90)<br>[Test Non-Existent Owner](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/PetControllerTests.java#L102)<br>[Test Sucess Get Visits](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/VisitControllerTests.java#L88)<br>[Test Non-Existent Pet Id](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/VisitControllerTests.java#L102)|
|/owners/{ownerId}/pets/{petId}/edit|[Test Success Nominal](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/PetControllerTests.java#L174)<br>[Test Non-Existent Pet](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/PetControllerTests.java#L185)<br> [Test Adding Null Pet](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/PetControllerTests.java#L202)<br> |
|/vets.html|[Test Success Nominal](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/VetControllerTests.java#L71)|
|/vets|[Test Success Nominal](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/VetControllerTests.java#L77)|
|/|[Test Success Nominal](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/WelcomeControllerTests.java#L38)|




|POST API requests|Implemented Tests |
| :-------------| ------- |  
|/owners/new|[Test Sucess Nominal](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java#L165)<br> [Test Fail](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java#L176)|
|/owners/{ownerId}/edit|[Test Success Valid Id](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java#L328)<br> [Test Non-Existent Id](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java#L351)<br> [Test Invalid Id](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/OwnerControllerTests.java#L365)|
|/owners/{ownerId}/pets/new|[Test Success Nominal](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/PetControllerTests.java#L120)<br>[Test Error](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/PetControllerTests.java#L136)<br>[Test Creating Duplicate Pet Error](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/PetControllerTests.java#L151)|
|/owners/{ownerId}/pets/{petId}/edit|[Test Success Update Pet](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/PetControllerTests.java#L219)<br> [Test Update Pet Error](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/PetControllerTests.java#L232)<br>[Test Post Invalid Id](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/PetControllerTests.java#L247)<br>[Test Valid Visit](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/VisitControllerTests.java#L129)<br>[Test Invalid Visit](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/VisitControllerTests.java#L147)<br>[Test Success First Visit Created](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/VisitControllerTests.java#L166)<br>[Test Non-Existent Pet Id](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/f558d89d72a7d271942b3fb1877b96a2e589c757/src/test/java/org/springframework/samples/petclinic/controller/VisitControllerTests.java#L187)|

## White Box Techniques for API Testing
The testing technique used for the project was white box testing control flow-oriented approach. The first stage of the testing technique involved setting [code coverage goals](https://github.com/McGill-ECSE429-Fall2021/project-test-08/wiki/Test-Plan#test-coverage-objectives) for the methods, lines, and branches on the unit, integration, and API test levels. After setting the coverage goals, the preliminary test methods were written out for each test level. It was decided that for each method, the tester must ensure that all if/else statements, for loops, and exceptions were tested. 

### Preliminary Test Suite
Note that during test development, some of these methods may have been removed or more methods have been added to increase method, line, and branch coverage. All tests can be found by navigating to the respective test folder in the project repository.

### Model Classes for Unit Test Level

**BaseEntityTests** <br>
testGetId <br>
testGetInvalIdId <br> 
testIsNew <br>

**NamedEntityTests** <br>
testSetName <br>
testGetName <br>

**PersonTests** <br>
testGetFirstName <br>
testSetFirstName <br>
testSetFirstNameNull <br>
testGetLastName <br>
testSetLastName <br>
testSetLastNameNull <br>
 
**OwnerTests** <br>
testGetAddress <br>
testSetAddressValIdAddress <br>
testSetAddressNull <br>
testGetCity <br>
testSetCityNull <br>
testSetCity <br>
testGetTelephone <br>
testSetValIdTelephone <br>
testSetTelephoneNull <br>

**PetTests**<br>
testGetBirthDate <br>
testGetBirthdayValIdDate <br>
testSetBirthdayInvalIdDate <br>
testSetBirthdayNull  <br>


**VetTests**<br>
testGetVetList  <br>

**VisitTests**<br>
testGetDate  <br>
testSetDateValId  <br>
testSetDateNull <br>
testGetDescription <br>
testSetDescriptionValId <br>
testSetDescriptionNull <br>
testGetPetId <br>
testSetPetId <br>


### Service Classes for Unit Test Level

**CacheConfigurationTests**<br>
testPetClinicCacheConfigurationCustomizer <br>

**PetTypeFormatterTests**<br>
testPrintValId <br>
testPrintPetTypeValId <br>
testPrintPetTypeInvalId <br>
testPrintLocaleValId <br>
testPrintPetTypeNull  <br>
testParseValId <br>
testParseTestNull <br>
testParseLocaleNull <br>

**PetValIdatorTests**<br>
testValIdateObjectValId <br>
testValIdateObjectNull <br>
testSupports <br>



### Model Classes for Integration Test Level

**OwnerTests**<br>
testGetPetsInternal <br>
testSetPetsInternalValIdPet <br>
testSetPetsInternalNull <br>
testGetPets <br>
testAddPetValIdPet <br>
testAddPetNull <br>
testAddDuplicatePet <br>
testGetPetExistentName <br>
testGetPetNonExistentName <br>
testGetPetNull <br>


**PetTests**<br>
testGetType <br>
testSetTypeValId <br>
testSetTypeNull <br>
testGetOwnerExistent <br>
testGetOwnerNull <br>
testSetOwner <br>
testSetOwnerDuplicate <br>
testSetVisitsInternal <br>
testGetVisitsInternalValId <br>
testSetVisitsInternalNull <br>
testGetVisits <br>
testAddVisit <br>
testAddVisitNull <br>
testAddVisitDuplicate <br>
testAddVisitDayThatAlreadyPassed <br>

**VetTests**<br>
testSpecialtiesInternal <br>
testGetSpecialties <br>
testGetNumberOfSpecialties <br>
testAddSpecialtyValId <br>
testAddSpecialtyNull <br>
testAddSpecialtyDuplicate <br>



### Persistence Classes for Integration Test Level
**OwnerRepositoryTests**<br>
testFindByLastNameExistent <br>
testFindByLastNameNonexistent <br>
testFindByLastNameNull <br>
testFindByIdExistent <br>
testFindByIdNonExistent <br>
testFindByIdNull <br>
testSaveValIdOwner <br>
testSaveNull <br>

**PetRepositoryTests**<br>
testFindPetTypes <br>
testFindByIdExistentId <br>
testFindByIdNonexistentId <br>
testFindByIdNull <br>
testSaveValIdPet <br>
testSaveNull <br>
testFindAll <br>
testFindAllEmptyDatabase <br>

**VisitRepositoryTests**<br>
testSaveVisitValId <br> 
testSaveVisitNull <br>
testFindPetByExistentId <br>
testFindPetByNonexistentId <br>
testFindPetNull  <br>

### Controller Classes for API Test Level
**CrashControllerTests**<br>
testGetOups <br> 

**OwnerControllerTests**<br>
testGetOwnersNew <br>
testPostOwnersNewIfCaseSucess <br>
testPostOwnersNewElseCaseHasErrors <br>
testGetOwnersFind <br>
testGetOwners <br>
testGetOwnersOwnerIdEditExistent <br>
testGetOwnersOwnerIdEditNonexistent <br>
testGetOwnersOwnerIdEditInvalId <br>
testPostOwnersOwnerIdEditExistent <br>
testPostOwnersOwnerIdEditNonExistent <br>
testPostOwnersOwnerIdEditInvalId <br>
testGetOwnersOwnerIdExistent <br> 
testGetOwnersOwnerIdNonExistent <br>
testGetOwnersOwnerIdInvalId <br>

**PetControllerTests**<br>
testGetPetsNewExistent <br>
testGetPetsNewNonExistent <br>
testPostPetsNew <br>
testGetPetsPetIdEditExistent <br>
testGetPetsPetIdEditNonExistent <br>
testGetPetsPetIdEditInvalId <br>
testPostPetsPetIdEditExistent <br>
testPostPetsPetIdEditNonexistent <br>
testPostPetsPetIdEditInvalId <br>

**VetController**<br>
testGetVetsHTML <br>
testGetVets <br>

**VisitController**<br>
testInitNewVisitFormExistentPetId <br>
testInitNewVisitFormNonExistentPetId <br>
testProcessNewVisitFormValidVisit <br> 
testProcessNewVisitFormNonValidVisit <br>
testProcessNewVisitFormNoVisit <br>
testProcessNewVisitFormNonExistentPetId <br>



