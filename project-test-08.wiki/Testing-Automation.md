> **Code automation for testing the various components of the application.** This section contains information regarding the various continuous integration and quality assurance tools used in the project.

### Automation on Test Levels
The quality assurance test suite is fully automated on the unit, integration, acceptance, and API test levels with the maven build tool. All necessary test dependencies are added in the [pom.xml](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/pom.xml) file.

### Code Coverage 
Jacoco code coverage reports can be generated on the local machine by running `mvn clean test` in the same directory as the pom.xml file and are found under the `./target/site/jacoco/` folder. The GUI report with the name `index.html` can be opened and interacted with via a browser tool.
![jacoco report location](https://user-images.githubusercontent.com/59708333/141720060-91a89637-11fc-4221-b7aa-e2d779712431.png)


### Continuous Integration 
The project is integrated with the GitHub Actions build tool to ensure quality production code on the master branch. All necessary CI/CD specifications can be found in the [`.github/workflows`](https://github.com/McGill-ECSE429-Fall2021/project-test-08/blob/master/.github/workflows/main.yml) YAML file.


