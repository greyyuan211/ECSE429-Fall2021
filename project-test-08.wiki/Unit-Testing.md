> **Unit level test plan describing the quality assurance strategies for testing the back-end components of the application.** This section contains the unit testing plan and report for the test suite. It aims to investigate the _model_ and _service_ classes in isolation from any other component. 

## Plan
### Testing Goals 
The unit testing objective is to ensure that the principle functionality of each class entity is working correctly. Methods dealing with class objects, fields, and associations are all tested in isolation. To ensure sharing of test data between classes, an [`InputData`](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/model/InputData.java) class was created in the `/model` folder and its data was called from all model class methods. <be>  

### Testing Methods
Here is the model class hierarchy which we reply on to design our unit tests:  
![image](https://user-images.githubusercontent.com/56415387/141698617-447e038d-2a95-41f7-9655-f808f951fdba.png)  
We design test cases from the most basic model classes like BaseEntity and NameEntity making sure that these base methods have no error. In this way, the higher-level classes can extend these base methods with no problem. Even if there is a problem when we are testing these high-level classes, we can identify them easily because each method has been isolated by our testing strategy. 

### Test Coverage Objectives:
> Model Classes
>- Total Method Coverage: 85%
>- Total Line Coverage: 85%
>- Total Branch Coverage: 85%

> Service Classes
>- Total Method Coverage: 80%
>- Total Line Coverage: 80%
>- Total Branch Coverage: 80%

## Report

### Model Classes 
#### IntelliJ IDEA Code Coverage Report
![unit_model_intellij](https://user-images.githubusercontent.com/59708333/141676837-b5c61c14-2bf1-4903-b389-1255f24a3361.png) <br>

________

#### Jacoco Code Coverage Report
![unit_model_jacoco](https://user-images.githubusercontent.com/59708333/141676805-e06adb67-1fd3-418d-b347-553f9604547e.png)

##### [Pet Test Class](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/model/PetTests.java) <br>
![unit_model_pet](https://user-images.githubusercontent.com/59708333/141677138-dc876d1f-455f-4428-b8ea-5300c0159db5.png) <br>

The lack of code coverage for the `getVisitsInternal()` method is because `this.visits` can never be null. In the `Pet.java` class the set of visits are initialized by default whenever a Pet is created with `private Set<Visit> visits = new LinkedHashSet<>();`. Because the method is called on a pet object, which requires initialization (`Pet pet = new Pet()`), performing `pet.getVisitisInternal()` will always be initialized with an empty set and will therefore never be tested for null.

##### [Owner Test Class](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/model/OwnerTests.java) <br>
![unit_model_owner](https://user-images.githubusercontent.com/59708333/141677122-d728a7eb-daf2-4980-8b67-211cbe3e27d3.png) <be>
The getter and setter tests have been fully explained in BaseEntity and NameEntity and they are the same for other classes. Therefore, we will not include these trivial cases in the further discussion.
###### Test cases
**testSetAddressNull()**  
goal: test if we can set an owner address with null  
result: we can set an owner address with null  
**testSetCityNull()**  
goal: test if we can set an owner city with null  
result: we can set an owner address with null  
**testSetTelephoneNull()**  
goal: test if we can set an owner Telephone with null  
result: we can set an owner Telephone with null  

##### [Vet Test Class](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/model/VetTests.java) <br>
![unit_model_vet](https://user-images.githubusercontent.com/59708333/141677172-036aa232-3793-4377-a0ac-dc1193cdf919.png) <br>

##### [Visit Test Class](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/model/VisitTests.java) <br>
![unit_model_visit](https://user-images.githubusercontent.com/59708333/141677202-f9acd4af-e1f8-448c-9699-d97beb4bb3c5.png) <br>

##### [BaseEntity Test Class](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/model/BaseEntityTests.java) <br>
![unit_model_baseentity](https://user-images.githubusercontent.com/59708333/141677105-5e95b236-ac65-412f-b55e-13b9b75b69f0.png) <be>
It is important to test get method first. Then, after we know the getter tests pass, we can isolate setter tests and test the setId method. This is because we will need to use getId method in setter tests.  
###### Test cases
**testPersonGetId()**  
goal: test if BaseEntity method GetId works in Person  
result: BaseEntity method GetId works in Person  
**testOwnerGetId()**  
goal: test if BaseEntity method GetId works in Owner  
result: BaseEntity method GetId works in Owner  
**testPetGetId()**  
goal: test if BaseEntity method GetId works in Pet  
result: BaseEntity method GetId works in Pet  
**testVisitGetId()**  
goal: test if BaseEntity method GetId works in Visit  
result: BaseEntity method GetId works in Visit  
**testPetTypeSetId()**  
goal: test if BaseEntity method SetId works in PetType  
result: BaseEntity method SetId works in PetType  
**testPersonSetId()**  
goal: test if BaseEntity method SetId works in Person  
result: BaseEntity method SetId works in Person  
**testOwnerSetId()**  
goal: test if BaseEntity method SetId works in Owner  
result: BaseEntity method SetId works in Owner  
**testPetSetId()**  
goal: test if BaseEntity method SetId works in Pet  
result: BaseEntity method SetId works in Pet  
**testVisitSetId()**  
goal: test if BaseEntity method SetId works in Visit  
result: BaseEntity method SetId works in Visit  
**testPetTypeSetId()**  
goal: test if BaseEntity method SetId works in PetType  
result: BaseEntity method SetId works in PetType  
**testGetInvalidId()**  
goal: test if BaseEntity method GetId can get an invalid Id  
result: BaseEntity method GetId would return null if given an invalid Id  
**testIsNew()**  
goal: test if we can get id from a newly created BaseEntity. We use Person as an example.  
result: we would get null from the GetId method  

##### [Person Test Class](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/model/PersonTests.java) <br>
![unit_model_person](https://user-images.githubusercontent.com/59708333/141677129-f3f84fa4-600d-4f1c-99e3-1f168cefcb56.png) <br>

##### [Vets Test Class](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/model/VetsTests.java) <br>
![unit_model_vets](https://user-images.githubusercontent.com/59708333/141677187-b4b69b42-6e46-4779-9bfe-c77ee3925582.png) <br>

##### [NamedEntity Test Class](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/model/NamedEntityTest.java) <br>
![unit_model_namedentity](https://user-images.githubusercontent.com/59708333/141677114-0d1ad9ba-3a61-427f-81b7-9f92f8581f99.png) <be>
It is important to test get method first. Then, after we know the getter tests pass, we can isolate setter tests and test the setName method. This is because we will need to use getName method in setter tests.  
###### Test cases

**testPetGetName()**  
goal: test if NameEntity method getName works in Pet  
result: NameEntity method GetName works in Pet  
**testSpecialtyGetName()**  
goal: test if NameEntity method getName works in Specialty  
result: NameEntity method GetName works in Specialty  
**testPetSetName()**  
goal: test if NameEntity method setName works in Pet  
result: NameEntity method setName works in Pet  
**testSpecialtySetName()**  
goal: test if NameEntity method setName works in Specialty  
result: NameEntity method setName works in Specialty  

______________

### Service Classes 
#### IntelliJ IDEA Code Coverage Report
![unit_service_intellij](https://user-images.githubusercontent.com/59708333/141678309-7f538c73-2dba-4b7b-a95e-32c6f95025b3.png)
 <br>

________

#### Jacoco Code Coverage Report
![unit_service_jacoco](https://user-images.githubusercontent.com/59708333/141678337-e61976f7-cced-43d8-be1c-cfa3e444bc1f.png) <br>

##### [CacheConfiguration Test Class](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/service/CacheConfigurationTests.java) <br>
![unit_service_cacheconfiguration](https://user-images.githubusercontent.com/59708333/141677725-12d283fe-20a2-4cb5-8561-0ee14036f2d4.png)<br>

![unit_model_cacheconfig_failing](https://user-images.githubusercontent.com/59708333/141677941-5be5b190-187e-4ce8-adac-d2eaa7ca5eba.png) <br>

The lack of code coverage for the `cacheConfiguration()` method is because the method is private and cannot be called from an external test class. As it was not permitted to modify the model for unit testing, this method could not be tested. As for the lambda `createCache()` function, there is no way to call it directly from the test class. 

##### [PetTypeFormatter Test Class](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/service/PetTypeFormatterTests.java) <br>
![unit_model_pettypeformatter](https://user-images.githubusercontent.com/59708333/141677740-3e25a651-28bf-4208-bf77-7c66b98a4afb.png)<br>

##### [PetValidator Test Class](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/service/PetValidatorTests.java) <br>
![unit_service_petvalidator](https://user-images.githubusercontent.com/59708333/141678357-84db8853-823c-4091-b398-730303bfb2bb.png)
<br>
