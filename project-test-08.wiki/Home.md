# Welcome to the project-test-08 wiki!
>This wiki hosts the documentation for the ECSE 429 - Software Validation project.

## Project Overview 
As a team of 5 quality assurance testers, we developed a test suite for a
[veterinarian clinic management system](https://github.com/McGill-ECSE429-Fall2021/project-test-08#spring-petclinic-sample-application-) implemented as a Java Spring application. The test suite implements quality assurance methodologies on various levels of the application. See links below for test code.

1. Unit/Component Testing 
    1. [Model Class Tests](https://github.com/McGill-ECSE429-Fall2021/project-test-08/tree/master/src/test/java/org/springframework/samples/petclinic/model)
    2. [Service Class Tests](https://github.com/McGill-ECSE429-Fall2021/project-test-08/tree/master/src/test/java/org/springframework/samples/petclinic/service)
2. Integration Testing
    1. [Persistence Class Tests](https://github.com/McGill-ECSE429-Fall2021/project-test-08/tree/master/src/test/java/org/springframework/samples/petclinic/persistence)
    2. [Model Class Tests](https://github.com/McGill-ECSE429-Fall2021/project-test-08/tree/master/src/test/java/org/springframework/samples/petclinic/model_integration)
3. API Testing    
    1. [Controller Class Tests](https://github.com/McGill-ECSE429-Fall2021/project-test-08/tree/master/src/test/java/org/springframework/samples/petclinic/controller)
4. Acceptance Testing
    1. [Cucumber Feature File](https://github.com/McGill-ECSE429-Fall2021/project-test-08/tree/master/src/test/resources/features)
    2. [Feature Tests Class]()
5. Non-functional/performance testing
    1. [Performance Tests](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/src/test/java/org/springframework/samples/petclinic/performance/PerformanceTests.java)


### Summary of Achieved Coverage
#### Total Coverage 
|Code Coverage Tool|Class Coverage | Method Coverage | Branch Coverage | Line Coverage |
|----| :----: | :---: | :---: |  :---: |  
|IntelliJ IDEA | 100% | 96% | N/A | 98% |
|Jococo | 100% | 97% | 94% | 97% |

#### IntelliJ IDEA Full Report

| Component |Class Coverage | Method Coverage | Line Coverage |
|----| :----: | :---: | :---: |  
|Controller | 100% | 100% | 100% |
|Model | 100% | 100% | 98% |
|Service | 100% | 75% | 86% |

#### Jococo Full Report

| Component |Class Coverage | Method Coverage | Branch Coverage | Line Coverage |
|----| :----: | :---: | :---: | :---: |  
|Controller | 100% | 100% | 91% | 100%|
|Model | 100% | 98% | 95% |99%|
|Service | 100% | 82% | 91% |85%|



