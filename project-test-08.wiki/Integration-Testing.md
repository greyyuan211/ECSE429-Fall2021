> **Integration level test plan describing the quality assurance strategies for testing the back-end components of the application.** This section contains the integration testing plan and report for the test suite. It aims to investigate the _model_ and _persistence_ classes.

## Plan
### Testing Goals 
The integration testing objective is to ensure that the interactions between the entity layer and the persistence layer are working correctly. Upon looking through the model entity classes, it was noted that Owner, Pet, and Vet classes all contained associations to other classes. _For example, `Pet.java` contains associations to PetType, Owner, Visit, and LocalDate._  It was therefore a design decision was made to create separate `OwnerTests.java`, `PetTests.java`, `VetTests.java` classes to solely test the interactions between these associations and the persistence layer.

Integration tests for model classes involved the use of mocking technology Mockito, so it was decided to put the main model integration testing classes in a separate folder called [model_integration](https://github.com/McGill-ECSE429-Fall2021/project-test-08/tree/master/src/test/java/org/springframework/samples/petclinic/model_integration). As a result of this design decision, the Jacoco tool could not be used to assess code coverage on the integration classes. However, to verify code coverage in IntelliJ, the mocked code in Owner, Pet, and Vet Tests classes was temporarily copy-pasted into the OwnerTests, PetTests, and VetTests model package classes and the JUnit tests were executed with 100% code coverage. <br>

The persistence package interface methods were tested with relevant [repository](https://github.com/McGill-ECSE429-Fall2021/project-test-08/tree/master/src/test/java/org/springframework/samples/petclinic/persistence) JUnit test classes: OwnerRepositoryTests, PetRepositoryTests, VetRepositoryTests, and VisitRepositoryTests. For most methods in the persistence interface, several different tests were performed to observe the behaviour of the persistence and entity layer when receiving Existent, Non-Existent, Valid, Invalid, and Null inputs. The persistence test classes did not use a mocking technology as it was instead useful to work with the real database and ensure that it behaved accordingly when receiving SQL requests. Expected behaviour was observed by comparing the queried values with the PetClinic application persisted data (visible through the website). <br>
Note: because these JUnit tests were testing an interface, it was not possible to obtain a code coverage. 

**No code was modified in the model or persistence classes as the provided code was sufficient to design a thorough integration test suite.**

## Report

### Model-View-Controller Architecture
![256812203_426633595501147_2284466948216299444_n](https://user-images.githubusercontent.com/59708333/141681901-29de52f6-a90d-4c4c-a0eb-993d412eb15b.png) <br>

### Visualization of Entity Dependencies (UML diagram)
![248441027_1239764036527553_8699642115077890558_n](https://user-images.githubusercontent.com/59708333/141682800-951e2038-1cc9-48fd-a664-6e7f6dd52ef5.png) <br>

### Documentation of Test Cases
#### Integration Test Model Class 

|[PetTests](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/model_integration/PetTests.java)  |Description | 
| :-------------| ------- | 
|testGetType()|Testing the method by adding type to pet and asserting that the pet has the right type|
|testSetTypeValid|Asserting that type can be accurately set. |
|testSetTypeNull|Asserting that inputting a null does not throw a NullPointerException but instead set null as type. The website should never crash because a NullPointerException was thrown.|
|testGetOwnerExistentOwner|Testing the method by adding an existent owner to pet and asserting that the pet has the right owner|
|testGetOwnerNull|Asserting that trying to retrieve an existent owner return null|
|testSetOwner|Asserting that owner can be accurately set.|
|testSetVisitsInternal|Asserting that internal visits can be accurately set.|
|testGetVisitsInternalExistentVisits|Testing the method by adding an existent visit list to pet and asserting that the pet has the correct internal visits|
|testGetVisitsInternalNull|Asserting that pet with no visit does not throw a NullPointerException but instead set empty list as internal visit. The website should never crash because a NullPointerException was thrown.|
|testGetVisits|Testing the method by adding visits to pet and asserting that the pet has the visits list in right order|
|testAddVisit|Asserting that a valid visit can be added to the pet|
|testAddVisitNull|Asserting that a null pointer exception is thrown when a null visit is to be added. A null visit should not exist.|
|testAddVisitDuplicate|Asserting that a duplicate visit can be added since system doesn't not handle duplicates here|
|testAddVisitDayThatAlreadyPassed|Asserting that a visit with day already passed can be added since system doesn't not handle past date here|

|[OwnerTests](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/model_integration/OwnerTests.java) |Description | 
| :-------------| ------- | 
|testGetPetsInternalIfPetsIsNull()|Asserting that when the list of pets is null, a null pointer exception is not thrown but instead an empty list is returned.|
|testGetPetsInternal|Testing the method by adding pets to a list and asserting that the list is populated with the right amount of Pets.|
|testGetPetsInternalNull|Similar to testGetPetsInternalIfPetsIsNull().|
|testSetPetsInternalValidPet|Asserting that a list of valid pets can be accurately set.|
|testSetPetsInternalNull|Asserting that inputting a null list does not throw a NullPointerException but instead creates an empty list. The website should never crash because a NullPointerException was thrown.|
|testGetPets|Asserting the return of a valid list of pets.|
|testAddPetValidPet|Asserting that a valid pet can be added to the owner. `when()` Mockito method is used to ensure that the added pet is new in the list.|
|testAddPetNull|Asserting that a null pointer exception is thrown when a null pet is to be added. A null pet should not exist.|
|testGetPetExistentName|Retrieving a pet that exists in the list of pets. `when()` Mockito method is used to ensure that a specific name is returned when a call for a specific pet is made to the pet list.|
|testGetPetNonExistentName|Asserting that null is returned when a non-existent pet is queried from the owner's pet list.|
|testGetPetNull|Asserting that a NullPointerException is thrown when getPet(null) is called. getPet should never query a null input value.|
|testGetPetOverloadedExistentName|Testing functionality of the overloaded get(String, Boolean) method.|
|testGetPetOverloadedNonExistentName|Testing for branch coverage of the get(String, Boolean) method. Asserting that a non-existent pet cannot be retrieved.|
|testGetPetOverloadedNull|Asserting that the overloaded method throws a NullPointerException when a null pet is accessed. |

|[VetTests](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/model_integration/VetTests.java)  |Description | 
| :-------------| ------- | 
|testSetSpecialtiesInternal|Testing the set method which updates the Set of specialities by mocking (using Mockito) some initialized specialty objects that are added to a new set of specialities which will be now linked to a Vet object. We then assert that the Set of specialties of the corresponding Vet is correctly updated|
|testGetSpecialties|We execute the same process that was used for testSetSpecialtiesInternal and we make sure that the size of the Set of Specialty correspond to the number of elements present in the Set of Specialty that was previously added|
|testGetNumberOfSpecialties|Testing the method by adding specialties to a Set and asserting that the Set contains the right amount of Specialty object.|
|testAddSpecialtyValid|Testing the method by adding a valid specialty and check if the updated Set contains the new specialty object.|
|testAddSpecialtyDuplicate|Testing the method by creating a Set containing two duplicate Specialty Objects and link it to a Vet object. Assert that there is only one Specialty in the Set (size = 1)|

#### Integration Test Persistence Class 
|[OwnerRepositoryTests](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/persistence/OwnerRepositoryTests.java)  |Description| 
| :-------------| ------- | 
|testFindByLastNameExistent|To test this method, we create an Owner object with its fields while making sure the lastName is not null or empty. We save it to the ownerRepostioryand try to query it using the query findByLastName which returns a list of Owners having this lastName. We assert that there is only one owner with this lastName in the repository and we make sure that the otherfields are there|
|testFindByLastNameNonExistent|To test this method, we create an Owner with a specific lastName and we save it to the ownerRepository. We try query a List of Owners with a non-existent lastName using the findByLastName query method of the OwnerRepository class. We assert that this list is empty by checking that the size of the list is equal to 0|
|testFindByLastNameNull|To test this method, we create an Owner with a null lastName and we save it to the OwnerRepository. We assert that there is an Exception and that the returned list after querying using findByLastName is empty|
|testFindByIdExistent|We create an Owner with a known id and we save it to the OwnerRepository. We use the query method findById with the specific ID to get the corresponding owner. We assert that there are both equals in terms of address and fields|
|testFindByIdNonExistent|To test this method, we query an owner by using an non-existent id. We assert that the returned object is null|
|testFindByIdNull|To test this method, we query an owner by using a null id. We assert that the returned object is null|
|testSaveValidOwner|To test this method, we save a created owner with correct fields and we assert that the given id to the object is positive and corresponds to lastOwnerID + 1|
|testSaveNull|To test this method, we try to save a null owner and we assert that saving throws an exception|

#### Integration Test Persistence Class 
|[PetRepositoryTests](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/persistence/PetRepositoryTests.java)  |Description| 
| :-------------| ------- | 
|testFindPetTypes|To test this method, we query the list of PetTypes using the petRepository findPetTypes() method. We assert that each petType has a correct name corresponding to its type|
|testFindByIdExistentId|To test this method, we query one of the existing Pet in the petRepository using the findById method and we assert that its fields are correct (non-null/empty)|
|testFindByIdNonexistentId||
|testFindByIdNull|To test this method, we query a Pet using a non-existing ID using the findById query method and we assert that the returned Pet object is null|
|testSaveValidPet|To test this method, we create a valid Pet object and we save it using the save method of the PetRepository class. We assert that the ID of this saved pet is greater than 0 and equal to LastPetId + 1|
|testSaveNull|To test this method, we try to save a null Pet object and we assert the error message that was catched when trying to execute this method|

#### Integration Test Persistence Class 
|[VetRepositoryTests](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/persistence/VetRepositoryTests.java)  |Description| 
| :-------------| ------- | 
|testFindAll|To test this method, we query the current list of Vets in the vetRepository using the query method findAll and we assert that its size is equal to 6|

#### Integration Test Persistence Class 
|[VisitRepositoryTests](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/persistence/VisitRepositoryTests.java)  |Description| 
| :-------------| ------- | 
|testSaveVisitValid|To test this method, we create a visit and save it using the save method from VisitRepository class. We assert that the created visited was saved by using the findByPetId query method and check that all the fields correspond to the created visit|
|testSaveVisitNull|To test this method, we try to save a null Visit object and we assert that an exception was thrown|
|testFindPetByExistentId|To test this method, we query a list of visits of a Pet using the visitRepository query method findByPetID. We assert that each visit of the list of visits have correct fields|
|testFindPetByNonexistentId|To test this method, we query a list of visits of a non-existent id Pet using the visitRepository query method findByPetID. We assert that the size of this returned list is equal to 0 (Empty List)|
|testFindPetNull|To test this method, we query a list of visits of a null Pet ID using the visitRepository query method findByPetID. We assert that the size of this returned list is equal to 0 (Empty List)|




