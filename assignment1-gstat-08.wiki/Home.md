Welcome to the assignment1-gstat-08 wiki!
# 1.1 – Code review and static analysis in simple project
* Sonarqube has been integrated into build and sonarqube analysis has been run on the repository.
  *   An interesting bug is documented in the screenshot below:

***

![sonarqube](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/master/1.1/screenshots/Screen%20Shot%202021-09-24%20at%2011.08.29%20AM.png)

***

* Infer has been integrated into build and infer analysis has been run on the repository.
  * An interesting bug is documented in the screenshot below:

***

![infer](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/master/1.1/screenshots/Screen%20Shot%202021-09-24%20at%2011.09.58%20AM.png)

***
* Code review:
The code review steps specified in the instructions has been conducted by the two team members respectively. The code review process is documented below as screenshots.
  * [DEV]:Ruixin Su &nbsp; [REV]:Grey Yuan
    * The reviewer inspects the pull request, starts a review and requests changes.

***

![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/master/1.1/screenshots/Screen%20Shot%202021-09-25%20at%206.52.28%20PM.png)
\
&nbsp;
   * After the additional commit made by the developer is approved by the reviewer, the developer merges the pull request.

***

![2](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/master/1.1/screenshots/Screen%20Shot%202021-09-25%20at%206.53.06%20PM.png)
\
&nbsp;
  * [DEV]:Grey Yuan &nbsp; [REV]:Ruixin Su
    * The reviewer inspects the pull request, starts a review and requests changes.

***

![3](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/master/1.1/screenshots/Screen%20Shot%202021-09-25%20at%206.53.48%20PM.png)
\
&nbsp;
   * After the additional commit made by the developer is approved by the reviewer, the developer merges the pull request.

***

![4](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/master/1.1/screenshots/Screen%20Shot%202021-09-25%20at%206.54.02%20PM.png)

***

#   1.2 – Static Analysis of Algorithms project

* The Algorithm project is cloned to the local repository under directory 1.2, and then committed to this private team repo  
  * First, the project is checked if it can be compiled with Maven:  
![image](https://user-images.githubusercontent.com/56415387/134777167-d65e2d30-01d7-410e-b434-35bb928b9137.png)  
  * Then, make sure to clean the project before running infer:
![image](https://user-images.githubusercontent.com/56415387/134777534-366d9264-0e68-4df7-a2c8-3bf4d0ddebb8.png)  
* Run infer using maven with the following command, then we get 5 issues as reported by infer:
![image](https://user-images.githubusercontent.com/56415387/134777934-653c54fb-4af2-4432-a781-af750284dff1.png)  
![image](https://user-images.githubusercontent.com/56415387/134778073-75ea1292-5279-4087-a989-8aa6e9eff4ce.png)  
![image](https://user-images.githubusercontent.com/56415387/134778153-7b51ef9c-9463-4b3c-8c3e-9fb897f71024.png)  
* The command lines used are:
> cd Algorithms  
> mvn compile  
> mvn clean  
> infer run -- mvn clean install -D.enforcer.skip=true  , and
* Examine the issues found by infer. As shown above, there are in total 5 issues. They have been categorized as false alarms or potential bugs(the order is the same as in the screenshot):
  * False alarm: It is a false alarm because this issue will not raise an error. It will just increase the efficiency of the algorithm to use an iterator on the 'entryset' of the map instead of using the extra 'Hashmap.get(key)'.  
  * False alarm: counter.get(candidate) could not be null because line 44 has confirmed that it cannot be getting a null. There must be 'candidate' key in 'counter' map. So, this is not a problem therefore it's a false alarm.  
  * False alarm: In the first iteration of the while loop, 'prevNode' is indeed null as it is initiated as null. However, the program will not execute the if statement since there is nothing in the first node of the linked list. After the first iteration, 'prevNode' will not be null anymore. Therefore, this is not a problem so it is a false alarm.  
  * False alarm: Since it is a JUnit test set, and the previous lines are successfully executed, reversedList.getNext().getNext() could not be null.  
  * False alarm: Since it is a JUnit test set, and the previous lines are successfully executed, reversedList.getNext() could not be null.  

#   1.3 – Static Analysis of TripleGeo project   
* The TripleGeo project is cloned to the local repository under directory 1.3, and then committed to this private team repo  
  * the project is checked if it can be compiled with ant:  

***

![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/master/1.3/screenshots/Screen%20Shot%202021-09-25%20at%2011.18.14%20PM.png)
  * Run infer using ant on the project, following are a full list of commands that were executed.
> ant compile\
> ant clean\
> infer run -- ant compile
  * A complete list of issues produced by infer is documented in the following screenshot, 1 issue in total.
\
&nbsp;
![2](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/master/1.3/screenshots/Screen%20Shot%202021-09-23%20at%204.13.38%20AM.png)
  * issue #0 is a potential bug. Since the class stream implements the Closeable interface, therefore needs to be closed after use. Furthermore, that close call must be made in a final block. Otherwise, an exception could keep the call from being made. Failure to properly close resources will result in a resource leak which could possibly crash the application.
  * the potential bug has been fixed in [this commit](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/commit/1efce16ea06e74ac370b9c88093feb9d4f791892). The result of infer after bug fixed is documented as the screenshot below.
\
&nbsp;
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/master/1.3/screenshots/Screen%20Shot%202021-09-25%20at%2011.03.40%20PM.png)
