# Findings report
## Project management
This exploratory testing project is carried out by Linwei Yuan and Ruixin Su. In this assignment, session-based exploratory testing has been performed on TodoManagerRestAPI to analyze the capabilities and functionalities of the application. TodoManager allows users to store and manage stand-alone information about todos, categories and projects, and also the relationships among the three entities. We carried out two exploratory-testing sessions and the sessions focused on the capability of the system under test(SUT) to perform CRUD operations on category instances and the capability of the SUT to maintain interoperability between todo instances and the two other types of instances. The charter for each session has therefore been specified following the overall charter of identifying documented and undocumented SUT capabilities and areas of potential instability and exercises each capability identified with data typical to the intended use of the application. Guided by the charters, two 60-minutes long sessions were carried out by us in pairs.   
    
The two testing sessions are on different time sections when we can meet up to discuss: one session is about testing on /categories capability. and the other one is on /todos capability. The tool that we will be using to executes our black box testing is Postman and SWagger also helps, which has greatly improved our testing efficiency since we can get input and output within one interface with ease. The testing mode we choose to operate the testing session is Strong Style Pairing. Since we have seen Strong Style Pairing method in the tutorial, we have learned that it is different from the traditional way of pairing which you would have one operator and a recorder. This improved method can engage both of the participants in the testing session so that we can get the most of the 1-hour session. Both of us can get a more general idea of what exploratory testing is. In the same way, this method can also help us to better understand what one another is thinking and therefore form more efficient cooperation. This really helps us to understand each other more quickly. We began with reading the API documentation of the application on our own. Then, we met up to start Session 1, in which Linwei is the main operator to test /categories capability. Next, we met up again on another day to execute Session 2, in which Ruixin is the main operator to test /todos capability. The major outcomes are recorded by the other person, and we detail them together to a testing report.
## Testing Results

### Session 1 testing results

Everything is difficult at the beginning. During the first session of our exploratory testing, we meet up earlier than the time we decided to discuss a bit about in what way we can carry out our tests. We decided to use the exhaustive testing models introduced in the lecture as a guideline to design our test cases. Basically, the navigator would tell and communicate with the operator about one documented user scenario and try to design more edge or common cases that are possible to happen. For example, when we were testing out the PUT function, we would not only test the documented method that we input all the attributes required. We would extend the test cases to "only inputting one variable" and observe what could possibly happen. When we saw a result that might crash the program, damage user experiences, or make it difficult for developers to improve the code, we recorded the problems in the bug summary. When we saw the result that we expected, the navigator would simply record the major test cases that we started from reading the document. The operator indeed encountered a lot of problems at the beginning and wasted about 20 minutes to get started, but things went very smoothly afterward. We also add additional time to complete all the tests that we would like to test on.

### Session 2 testing results

We were more familiar with the SUT and the testing methods(i.e.:the format of Postman requests) in this session, so prior to the beginning, we decided to cover all the interoperability related capabilities, including functionalities that manage todo instances' relationships, category instances' relationships and project instances' relationship. When the session started, however, we realized that testing on the interoperability also requires testing the basic CRUD operations, especially the POST requests, on entity instances alone to make sure the documented functionality of entity instances would not interfere with the tests related to relationship instances, which is a bit time-consuming. After the basic functionality test on todo instances, we followed the documentation and designed both regular and irregular(e.g. when one of the given id represent non-exist instance) test cases to test the functionalities. As a result, we noted two undocumented functionalities and six bugs in total. It took 90 minutes for us to complete testing on functionalities related to solely the relationship of todo instances, so we decided to end the session and make plans to test the rest of the capabilities in the future when time permits.




## Learning outcome
This exploratory testing project really gives us a sense of what the testing work routine is like, especially for black-box testing. Since we are given a _runTodoManagerRestAPI-1.5.5.jar_ file, we do not know what the code inside the application looks like. The very basic thing that we can do at first when getting this testing assignment is roughly running through all the documented test cases to see if any of them is not true. According to the test results, we observed that most of the documented capabilities are true about what they said. In that way, we should put ourselves in a user position to see what could be a user scenario that can possibly break the application. Testing how well the application supports these extra scenarios is an important part of exploratory testing. If we picture ourselves as a user or a developer who is maintaining the code or adding extra features to the application, we can know if it is considered an error, a potential bug, or an issue/concern. In detail, we would have an expected output before we run each test, which is either getting from the document or from a common sense of a normal user. Then, after we compare the actual output and our expectations, we will have a very clear idea about the nature of the testing case. In this way, we also have a better understanding of the potential solution or the severity of the problem.  
  
For the two sessions in total, we have found 10 bugs. Some of them are a representation of a category of bugs that will show up not only once. For example, the input type bug will show up as long as we are using an input variable that is not a string and not an integer. Also, the PUT error that automatically deletes the other unspecified attributes will not just show up in endpoint /categories/:id, but also on other endpoints where there is a PUT feature incorporated. These are seemingly the developer forget to handle these in the application. A good way to resolve or avoid these problems is to do a careful user case analysis and draw the scenario graph as we are coding. A nice way to double-check if we want to make sure is to do a user scenario analysis as a part of exploratory testing right after the completion of one capability. In this way, we can avoid duplicated bugs at the early stage.  
  
Furthermore, there are still many things that can be tested for this API. What we have achieved in the two exploratory testing sessions is very limited. The exhaustive testings that we have conducted are for capabilities like categories and todos, we have not conducted tests for projects. Also, the relationship for the 3 different capabilities is not thoroughly tested, meaning that other black-box testing techniques like system testings and integration testings can also be applied to make sure the interacting relationship among todos, projects, and categories is the same as documented.  
  
In conclusion, exploratory testing is a very detailed and rigorous technique that facilitates simultaneous learning, testing design, and execution for both of the participants. Testing thoroughly and documenting as detailed as possible is the most important takeaway for exploratory testing. The more tests that we have executed, the less likely our software crashed on the run.

# Session 1 (categories)

## Session notes

### Charter:  
Test the APIs of capability "categories" of the "the rest api todo list manager"  
Identify documented and undocumented “rest api todo list manager” category capabilities  
Exercise category capability identified with data typical to the intended use of the application  

### Area:
Test if the documented mandatory and not mandatory field is working as expected  
Test if **GET** and **POST** in **/categories** is working properly as documented and find potential bugs  
Test if **GET**, **POST**, **PUT**, **DELETE** in **/categories/:id** is working properly as documented and find potential bugs  
Test if different kinds of input variables are valid or not. If not, check if they are handled or not.  

### Environment:
WSL Ubuntu

### Start time:
2021-10-05 2:14pm

### Testers:
Linwei Yuan linwei.yuan@mail.mcgill.ca  
Ruixin Su ruixin.su@mail.mcgill.ca

### Duration:

60 minutes

### Test notes:
#### GET /categories  
documented function: return all the instances of a category  
screenshot:  
![1633765408(1)](https://user-images.githubusercontent.com/56415387/136649264-fd0f15a6-bd75-4cb8-91ed-9fe2fd492b49.jpg)  
result: all instances in category are returned  
#### POST /categories  
documented function: we should be able to create category without a ID using the field values in the body of the message  
screenshot:  
![image](https://user-images.githubusercontent.com/56415387/136649497-39c2a97d-652a-4674-a8b3-b936faf4739e.png)  
result: a category is successfully created with the provided title and description even without providing an ID  
#### GET /categories/:id  
documented function: return a specific instances of category using a id    
screenshot:  
![image](https://user-images.githubusercontent.com/56415387/136649665-8789b5fc-bbdc-4f39-9610-ba19c60581d5.png)  
result: the category with ID 3 which we just created is sucessfully returned    
#### POST /categories/:id  
documented function: amend a specific instances of category using a id with a body containing the fields to amend      
screenshot:  
![image](https://user-images.githubusercontent.com/56415387/136649862-060b0444-d215-4acf-97ad-bf15c624712d.png)  
result: with putting ID 3 to the API, the description is changed to "wash my car and my dog" and the title is changed to "wash". The new category with ID 3 is successfully updated and returned    
#### PUT /categories/:id  
documented function: amend a specific instances of category using a id with a body containing the fields to amend  
screenshot:  
![image](https://user-images.githubusercontent.com/56415387/136650593-49627999-5c2a-4b8b-98f1-51695fdd60ca.png)  
result: with putting ID 3 to the API, the description is changed to "wash my car and my cat" and the title is unchanged. The new category with ID 3 is successfully updated and returned    
#### DELETE /categories/:id
documented function: delete a specific instances of category using a id  
screenshot:  
![image](https://user-images.githubusercontent.com/56415387/136650841-2406eb84-a545-4afa-b95a-5cf10e6f4777.png)  
![image](https://user-images.githubusercontent.com/56415387/136650864-d5ffb19b-5c75-4c00-b945-634f79042dcf.png)  
result: the category with ID 3 is deleted successfully. This is checked by using GET to try to fetch the category with ID 3, but it returns a "not found" error.  

***
Below are tests run for undocumented functions in order to find potential bugs
#### POST /categories with integer as input  
screenshot:  
![image](https://user-images.githubusercontent.com/56415387/136678120-ee6bdd6f-5907-4524-bead-c0b44f268884.png)  
result: The integer input is automatically converted to a string, and its decimal is increased by 1  

#### POST /categories with double (float) as input  
screenshot:  
![image](https://user-images.githubusercontent.com/56415387/136678555-5580c491-1d26-45f8-87cb-771db7c53d01.png)  
result: The double input is converted to a string and then is used to create the category  

#### POST /categories with an array of string as input  
is reported as a potential bug in the bug summary session below  

#### POST /categories with NULL as input  
is reported as a potential bug in the bug summary session below  

#### POST /categories/:id but only make changes but only input one attribute of information  
expected function: post function should amend the attribute to the new string information provided and leave other attributes unchanged  
screenshot:    
First, we should check if a category with ID 1 is available to be amended:
![image](https://user-images.githubusercontent.com/56415387/136678860-a6a23baa-5773-4848-b16d-90974860af41.png)  
Then, we use POST to change the title information:  
![image](https://user-images.githubusercontent.com/56415387/136679000-238e2700-4d6e-4443-a1dc-4b333367ed22.png)  
result: it works as we expected  

#### PUT /categories/:id but only make changes but only input one attribute of information  
is reported as a potential bug in the bug summary session below  

#### GET /categories but all the categories have been deleted  
is reported as a potential bug in the bug summary session below  
expected function： should return NULL or give a 404 not found exception  
screenshot:  
![image](https://user-images.githubusercontent.com/56415387/136680038-4044b1d6-2cc9-4e79-8388-0d4c9b537843.png)  
result: it returns an empty list, so it is considered as a potential bug in the Bug session


### Bugs logged:
There are potential input bugs for categories capability that are logged in the bug summary:  
1. Inputting a different data type other than String and Integer (e.g. array of string）to POST a category
2. Inputting nothing (e.g. NULL) to use PUT to make changes to a category  
3. Inputting just one attribute of information to use PUT /categories/:id to amend the information in a category  
4. Deleting all the categories and get categories using endpoint Get /categories  

### Issues/Concerns:
Not catching an invalid input like an array of strings may cause the user (other inexperienced programmers) not to be aware of what is happening if they input a non-string or non-integer variable.  
Not catching an invalid input like NULL may increase the difficulty for the app to include more features in the future because the NULL exception may not be raised.
Users may lose information when they are trying to update the category information using PUT while they are not providing sufficient information. This may lead to potential bugs in the future.  
When there are no categories available to return, the API would still return an empty list. This is not an optimum situation since the user may think if there is a network stability issue or his or her device is frozen since they can see nothing. It would be better to return a 404 not found error.

### Testing Ideas:
Try out all the different types of the input variable to see how the API handle the error and how it will behave
Carry out the PUT method for other capabilities to see if the bug of automatically deleting attribute information still exists.
Check how the API would behave when deleting all items and get items using the GET function. This should be a design choice problem that is prevalent in all endpoints.

## Session summary
* In this session, we aim to search for the software failure in the capability categories of the "rest api todo list manager". The main functions that we have tested out are /categories and /categories/:id. For /categories, we have tested GET and POST with normal string input. For /categories/:id, we have tested GET, POST, PUT, and DELETE. One potential bug we have found on this is the input error handling: if we input invalid input like an array of strings, the API will just return an empty string without raising an error; if we input NULL, the API will also return an empty string without raising an error. We think those are not acceptable in terms of improving and maintaining the app, so they are defined as potential bugs. 
* Another more severe bug that we found during the session is losing information when only using one attribute to amend information of categories. We found this when we are considering how users would think to only change one of the attribute. Normally, one would not input the same information again, so it is very possible that the user only provides information that he or she needs to change. Therefore, this is a very common user scenario, but it is not handled by the API, which causes the loss of other attribute information.
* Another issue/concern is that the API would return an empty list instead of raising a 404 not found error when there is nothing to get from the list. This may cause users to waste time waiting for responses since an empty list is returned so the user will not see anything. Also, since no error is raised, the user may think the device is frozen and keep waiting for a response. It would be a better practice if we raise an error and inform the user about the fact the list is empty. 
* **/categories** Bug#1 and Bug#2 are found in this endpoint. Also, Bug#4 can also happen in this endpoint, when the user is trying to get all categories when there are actually none. 
* **/categories/:id** Bug#1 and Bug#2 are also possible to occur in this endpoint since they are present in any feature that needs an input. Also, Bug#3 is also very likely to happen when users use PUT /categories/:id to amend the information.

## Bug summary

### Bug #1
**Inputting a different data type other than String and Integer (e.g. array of string）to POST a category**

* Pre-conditions: \
Run the API on your device and open postman  

* Steps to reproduce: \
use the following input for POST in /categories:  
![19bd84dc877bdea1dc234bb644442e3](https://user-images.githubusercontent.com/56415387/136652351-e8c09400-1a6b-4a62-8c70-805ae90be763.png)  

* Frequency: \
Every time you provide an invalid input  
* Productivity impact: \
When users see there is no information that is stored in the to-do list category. There can be many reasons due to the potential bugs that are mentioned: it could be caused by an invalid input like multiple inputs in sequence like what is produced in this bug summary, or other illegal data types or errors. Developers who are contributing to this app may also be confused when they are adding more features and testing them out.  

### Bug #2
**Inputting nothing (e.g. NULL) to use PUT to make change to a category**

* Pre-conditions: \
Run the API on your device and open postman  
Make sure there are categories created  

* Steps to reproduce: \
use the following input for POST in /categoroes/:id, where id is set to an ID that corresponds to one category that you have previously created:  
![image](https://user-images.githubusercontent.com/56415387/136652566-d5cb6b50-7b5d-44d5-9e56-0b3e53609da6.png)  

* Frequency: \
Every time you provide a NULL input  
* Productivity impact: \
When users see there is no information that is stored in the to-do list category. There can be many reasons due to the potential bugs that are mentioned: it could be caused by a NULL input like what is produced in this bug summary, or other illegal data types or errors. Developers who are contributing to this app may also be confused when they are adding more features and testing them out.  

### Bug #3
**Inputting just one attribute of information to use PUT /categories/:id to amend the information in a category**

* Pre-conditions: \
Run the API on your device and open postman  
Make sure to use POST to create a category with ID 2 

* Steps to reproduce: \
First, we should check if a category with ID 2 is available to be amended:
![image](https://user-images.githubusercontent.com/56415387/136679277-027237b8-1584-4d71-8dcd-bdf3e0565a7e.png)  
Then, copy the following JSON input to BODY
'''
{
  "title": "changed information"
}
'''
we use PUT to change the title information as followed:  
![image](https://user-images.githubusercontent.com/56415387/136679332-d1646669-8e60-4a56-90a2-964f0186cf9c.png)  
result: as the title information is changed, the information in the description is automatically discarded  

* Frequency: \
Every time you provide not enough information to amend the category information using PUT categories/:id  
* Productivity impact: \
This is a very serious bug that may cause the user to lose their information. As described in the documentation, PUT is supposed to amend the attribute information that is provided by the user. However, it also automatically assumes that other information that is not given is empty, which may cause many undesired consequences and may cause software failure.   

### Bug #4
**Deleting all the categories and get categories using endpoint Get /categories**

* Pre-conditions: \
Run the API on your device and open postman  
Using Get /categories to see what categories are present and their id  
Make sure to empty all the categories using DELETE /categories/:id one by one  

* Steps to reproduce: \
Use Get /categories to return the list of categories. As mentioned in the tutorial, it is better to provide a 404 not found error or throw an exception.  
screenshot:  
![image](https://user-images.githubusercontent.com/56415387/136680038-4044b1d6-2cc9-4e79-8388-0d4c9b537843.png)  
result: it returns an empty list, so it is considered as a potential bug in the Bug session

* Frequency: \
Every time you try to use GET /categories or Get _/other endpoints_ of the API when the list is empty.
* Productivity impact: \
This issue/concern may cause users to waste time waiting for responses since an empty list is returned so the user will not see anything. Also, since no error is raised, the user may think the device is frozen and keep waiting for a response. It would be a better practice if we raise an error and inform the user about the fact the list is empty. 

### Notes:
This is just testing out the potential bugs for input variables. There may be more to discover in the next session.  
The bugs that are raised in this capability: category are very possible to exist in other capabilities.
The bug that not raising an error from trying to get categories from an empty list is very possible to also exist in /todos and /projects. This should be checked for further testing.

## Any other file created during session (optional)
NA

# Session 2 (Interoperability of todo entity)
## Session notes

### Charter:  
Identify, Analyze and exercise the capabilities of the system under test to perform CRUD operations on the relationships of todo instances.   

### Area:
* Test if the documented mandatory and not mandatory field is working as expected  
* Test the functionality of system to manage task-of relationships of a todo instance, i.e.: **GET** and **POST** in /todos/:id/task-of and **DELETE** in /todos/:id/task-of/:id
Test the functionality of system to manage categories relationships of a todo instance, i.e.: **GET** and **POST** in /todos/:id/categories and **DELETE** in /todos/:id/categories/:id  

### Environment:
macOs Mojave

### Start time:
2021-10-07 5:00pm

### Testers:
Linwei Yuan linwei.yuan@mail.mcgill.ca  
Ruixin Su ruixin.su@mail.mcgill.ca

### Duration:

90 minutes

### Test notes:
The basic documented capabilities of todo instances have been tested first to ensure that these capabilities don't interfere the bug found in the following session. 
#### /todos  
* GET /todos \
documented functionality: return all the instances of todo  \
result: list of todo instances was successfully returned. \
screenshot:  
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_get.png) 
* POST /todos \
documented functionality: be able to create todo without a ID using the field values in the body of the message  \
result: a todo instance defined by fields contained by message body was successfully created. \
screenshot:
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_post.png)
#### /todos/:id
* GET /todos/:id \
documented functionality: return a specific instances of todo using a id  \
result: an instance of todo with the input id was successfully returned \
screenshot:
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_id_get.png)
* POST /todos/:id \
documented functionality: amend a specific instances of todo using a id with a body containing the fields to amend \
result: the todo instance with the input id was successfully amended \
screenshot:
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_id_post.png)
* PUT /todos/:id \
documented functionality: amend a specific instances of todo using a id with a body containing the fields to amend \
result: the todo instance with the input id was successfully amended \
screenshot:
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_id_put.png)
* DELETE /todos/:id \
documented functionality: delete a specific instances of todo using a id \
result: the todo instance with the input id was successfully deleted with status 200 OK. \
screenshot:
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_id_delete.png)

Next, documented and undocumented functionalities of the SUT to manage categories relationships of todo instances were tested thoroughly.
#### /todos/:id/categories
* GET /todos/:id/categories
  * documented functionality: return all the category items related to todo, with given id, by the relationship named categories \
result: the category items related to todo with given id by the relationship categories were successfully returned \
screenshot: 
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_categories_get1.png)
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_categories_get.png)
  * undocumented functionality: return proper error message if the todo instance represented by the given id does not exist \
result: returned a category item with code 200OK \
see bug#1 for details \
screenshot: 
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_category_nonexist_todohascategory.png)
* POST /todos/:id/categories
  * documented functionality: create an instance of a relationship named categories between todo instance :id and the category instance represented by the id in the body of the message \
result: returned error message: "title field is mandatory" \
see bug#2 for details \
screenshot: 
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_category_post_wrongerrmsg.png)
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_category_post_wrongerrmsg-1.png)
  * undocumented functionality: create an instance of a relationship named categories between todo instance :id and the category instance created using the fields values(excluding id) in the body message \
result: an instance of a relationship named categories between todo instance :id and the category instance created using the fields values(excluding id) in the body message was successfully created \
screenshot:
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_categories_post_withtitle.png)
#### /todos/:id/categories/:id
* DELETE /todos/:id/categories/:id 、
  * documented functionality: delete the instance of the relationship named categories between todo and category using the :id \
result: the instance of the relationship named categories between todo and category with given id was successfully deleted \
screenshot:
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_categories_delete_success.png)
  * undocumented functionality: return proper error message when the todo instance or category instance represented by the given ids don't exist. \
result: returned error message: "Could not find any instance with todos/id/categories/id" \n
see bug#3 for details \
screenshot:
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_categories_deleteinpropererrmsg.png)

Lastly, documented and undocumented functionalities of the SUT to manage task-of relationships of todo instances were tested thoroughly.
#### /todos/:id/task-of
* GET /todos/:id/task-of
  * documented functionality: return all the project items related to todo, with given id, by the relationship named task-of \
result: all the project items related to todo with given id, by the relationship named task-of, were successfully returned. \
screenshot: 
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_task-of_getsuccess.png)
  * undocumented functionality: return proper error message if the todo instance represented by the given id does not exist \
result: returned a list of projects. \
see bug #4 for details \
screenshot: \
GET todos/999 shows that todo instance does not exist.\
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_task-of_nonexisttodohasproject-1.png)
GET todos/999/task-of shows that system still returned list of projects.\
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_task-of_nonexisttodohasproject.png)

* POST /todos/:id/task-of
  * documented functionality: create an instance of a relationship named task-of between todo instance :id and the project instance represented by the id in the body of the message \
result: return error message: "Not allowed to create with id" with code 400 bad request. \
see bug #5 for details \
screenshot: 
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_task-of_postwithidfailed.png)

  * undocumented functionality: create an instance of a relationship named task-of between todo instance :id and the project instance created using the fields values(excluding id) in the body message \
result: an instance of a relationship named task-of between todo instance :id and the project instance created using the fields values(excluding id) in the body message was successfully created. \
screenshot:
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_task-of-postsuccess.png)

#### /todos/:id/task-of/:id
* DELETE /todos/:id/task-of/:id
  * documented functionality: delete the instance of the relationship named task-of between todo and project using the :id \
result: the instance of the relationship named task-of between todo and project using the given ids was successfully deleted. \
screenshot:
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_task-of_deletesuccess.png)

  * undocumented functionality: return proper error message when the todo instance or project instance represented by the given ids don't exist. \
result: returned error message: "Could not find any instances with todos/id/task-of/id" when the todo instance exist and project instance does not exist; return error message "java.lang.NullPointerException" when both todo instance and project instance do not exist \
see bug #6 for details \
screenshot:
when both todo instance and project instance do not exist:
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_task-of_deleteimpropererrmsg.png)
when the todo instance exist and project instance does not exist:
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_task-of_deletewrong.png)


### Bugs logged:
The bugs detected for capabilities related to interoperability of todo instances in this session are listed below. See bug summary for more detail. 
1. Return a category instance instead of proper error message upon GET request on categories of a non-exist todo instance 
2. Create an instance of a categories relationship between todo and a new category instance instead of creating an instance of a categories relationship between todo instance and the category instance represented by the id in the body of the message upon POST request.
3. Return improper error message upon DELETE request on categories of a non exist todo entity
4. Return a list of project instances instead of proper error message upon GET request on task-of of a non-exist todo instance 
5. Create an instance of a task-of relationship between todo and a new project instead of creating an instance of a task-of relationship between todo instance and the project instance represented by the id in the body of the message upon POST request.
6. Return improper error message upon DELETE request on task-of of a non-exist todo instance or non-exist project instance.


### Issues/Concerns:
During this session, it is noted that the PUT request for todos/id returned error message "invalid GUID for :id entity todo" with error code 404 not found when there is no todo instance with the given id. This is not a bug but the error message is unclear from the user's point of view. For future improvement, the error handler can be more specific in handling error cases and therefore provide clearer and better user experience.
screenshot: 
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/todo_id_put_nonexist.png)


### Testing Ideas:
Cover all regular and irregular(e.g. when one of the given id represent non-exist instance) test cases to test the documented functionalities and discover and test undocumented functionalities.


## Session summary
In this session, we aim to search for the software failures in the capability related to relationships of todo instances in the "rest api todo list manager". The main functions that we have tested out are the CRUD operations in /todos/:id/task-of, /todos/:id/task-of/:id, /todos/:id/categories and /todos/:id/categories/:id. The relationship instances involve two entity instances and are therefore more complicated than entity instances, this session focused on verifying the documented functionalities of todo instances' relationships and exploring the undocumented functionalities. We discovered that the POST request handlers of both task-of and categories has the same defect that they are not able to create relationship instances using given category/project id, which violates the documented functionality, they can, however, create an instance of category/project using given fields value(excluding id) and create relationship instance using the newly created category/project, which is an undocumented functionality. Also, we found out that the SUT return category/project instances upon receiving request on categories/task-of relationship of an un-exist todo instance, instead of returning error message, which is against a documented functionality and therefore noted as a bug.

## Bug summary
### Bug #1
**Return a category entity with code 200OK instead of proper error message upon GET request on category of a non-exist todo entity**
* Pre-conditions: \
There exists at least one category instance in the system and there is no todo instance in the system with the id in the GET request

* Steps to reproduce: \
Launch the TodoManagerRestAPI and send the series of request with message as shown below in Postman. \
POST http://localhost:4567/categories
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/bug1_reproduce.png)
GET http://localhost:4567/todos/65/categories

* Frequency: \
Occurs each time when following steps to reproduce the bug.
* Productivity impact:
The outputs are misleading. The bug impacts system performance and gives users the wrong result.
* Notes: \
Upon GET request on category of a non-exist todo instance, the system should produce proper error message to inform the user. This bug might be caused by defects in the request parser logic: since the return code is 200OK instead of 404 Not Found, the api clearly did not send the correct request to database to find the requested relationship instances.


### Bug #2
**Return error message instead of creating an instance of a categories relationship between todo instance and the category instance represented by the id in the body of the message upon POST request.**
* Pre-conditions: \
There exists at least one category instance and one todo instance in the system. The todo instance should not already be in categories  relationship with the category instance represented by id in the body of message.

* Steps to reproduce: \
Launch the TodoManagerRestAPI and execute the POST request below including the message body in Postman. \
http://localhost:4567/todos/1/categories \
Message Body: {"id": 1}

* Frequency: \ 
Occurs each time when following steps to reproduce the bug.

* Productivity impact: \
The outputs are misleading and against the documentation of this functionality, in which it is stated that the category id should be in the message body while the output states that "Not allowed to create with id”. The bug impacts system performance and cause confusion to the users. Possible cause is that the system tried to create a new instance instead of retrieve the existing instance by given id when handling the POST request.

### Bug #3
**Return improper error message upon DELETE request on categories of a non exist todo entity**
* Pre-conditions: \
There are no preconditions since this bug occurs when the input todo instance or category instance do not exist.

* Steps to reproduce: \
Launch the TodoManagerRestAPI and execute the DELETE request below in Postman. \
DELETE http://localhost:4567/todos/31/categories/33

* Frequency: \
Occurs each time when following steps to reproduce the bug.

* Productivity impact: \
The outputs are misleading. The bug impacts system performance and cause confusion to the users.

### Bug #4
**Return a list of project instances with code 200 OK instead of proper error message upon GET request on task-of of a non-exist todo instance**

* Pre-conditions: \
There exists at least one project instance in the system and there is no todo instance in the system with the id in the GET request

* Steps to reproduce: \
Launch the TodoManagerRestAPI and send the series of request with message as shown below in Postman. \
POST http://localhost:4567/project \
![1](https://github.com/McGill-ECSE429-Fall2021/assignment1-gstat-08/blob/A2-session2-screenshot/A2-session2/bug4_reproduce.png)

* Frequency: \
Occurs each time when following steps to reproduce the bug.
* Productivity impact: \
The outputs are misleading. The bug impacts system performance and user experience.
* Notes: \
Upon GET request on task-of of a non-exist todo instance, the system should produce proper error message to inform the user. This bug is similar to bug #1 and might be caused by the same request handling logic.


### Bug #5
**Create an instance of a task-of relationship between todo and a new project instead of creating an instance of a task-of relationship between todo instance and the project instance represented by the id in the body of the message upon POST request.**

* Pre-conditions: \
There exists at least one project instance and one todo instance in the system. The todo instance should not already be in task-of  relationship with the project instance represented by id in the body of message.

* Steps to reproduce: \
Launch the TodoManagerRestAPI and execute the POST request below including the message body in Postman. \
http://localhost:4567/todos/1/task-of \
Message Body: {"id": 1}

* Frequency: \
Occurs each time when following steps to reproduce the bug.
* Productivity impact: \
The outputs are misleading and against the documentation of this functionality, in which it is stated that the project id should be in the message body while the output states that "Not allowed to create with id”. The bug impacts system performance and cause confusion to the users. This bug is similar to bug #2.

### Bug #6
**Return improper error message upon DELETE request on task-of of a non-exist todo instance or non-exist project instance: returned error "Could not find any instances with todos/id/task-of/id" when the todo instance exist and project instance does not exist; returned "java.lang.NullPointerException" when both todo instance and project instance do not exist \
**
* Pre-conditions: \
There are no preconditions since this bug occurs when the input todo instance or project instance does not exist.
* Steps to reproduce: \
Launch the TodoManagerRestAPI and execute the DELETE request below in Postman. \
DELETE http://localhost:4567/todos/33/task-of/33
* Frequency: \
The error message "Could not find any instances with todos/id/task-of/id" was returned every time when the todo instance exist and project instance does not exist with error code 404 not found, which is explainable since the relationship
under query did not exist, and can be fixed by more specific error handling. However, the error message "java.lang.NullPointerException" do not happen every time, sometimes, with the same DELETE request, "Could not find any instances with todos/id/task-of/id" was returned instead.
* Productivity impact: \
The outputs are misleading. The bug impacts system performance and causes confusion to the users. The error message "Could not find any instances with todos/id/task-of/id" came with  error code 404 not found, which is explainable since the relationship
under query did not exist, and can be fixed by more specific error handling. For "java.lang.NullPointerException", we suspect it is caused by bugs in the request handler logic and should be handled with care so as to not confuse the user.

### Notes:
The bug detected for task-of relationships of todo instances and categories relationships of todo instances have similar structure and output so we deduced that all relationship CRUD operators share similar logic, which means in the future test sessions on other interoperability related capabilities, we should focus on the functionalities that were tested to have defect in this session, i.e.: DELETE by instances' id and POST by given id. 




# Reference
https://www.softwaretestinghelp.com/what-is-exploratory-testing/  
https://www.educba.com/black-box-testing-techniques/
