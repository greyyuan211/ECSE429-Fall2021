> **Non-functional level test plan describing the quality assurance strategies for testing the performance of the application.** This section contains the non-functional testing plan and report for the test suite. It aims to investigate the performance run-time, CPU usage, and memory usage of the application. 

## Plan
The non-functional testing objective is create a test driver program that verifies the application performance data by adding specific number of instances to the database and measuring the run-time of CRUD operations. In particular, the POST operation will be evaluated when there are 10, 100, and 1000 instances in the database. The main questions this test plan aims to answer is how much time it takes to add an Owner and a Pet to the database when their quantity increases. <br>

## Report
### Non-functional test suite 
In our test suite, the pet create operation, pet update operation, owner create operation and owner update operation were evaluated in terms of transaction time, CPU percent usage and memory usage. We performed 1000 repetitions of each operation separately and measured the aforementioned data for each transaction using open-source libraries(see details in the code) and also the total elapsed time for each transaction.  We visualized the data in the graphs below, where for transaction time, the number of transactions is the x axis and for CPU usage and memory usage, the total elapsed time is the x axis. Additionally, we gathered the average time, CPU and memory usage of each transaction for when the total transaction number is 10, 100 and 1000 respectively, and the results are shown in the table below.
#### links to the source code files used to gather measurement data is [here](https://github.com/McGill-ECSE429-Fall2021/project-test-08/tree/master/src/test/java/org/springframework/samples/petclinic/performance). 

### Overall Statistics
| Max number of estimators | Number of Transactions | Average Transaction Time(ms) | Average CPU(%) | Average Memory Usage(E7 bytes) |
|:------------------------:|:----------------------:|:----------------------------:|:--------------:|:---------------------------:|
|         createPet        |           10           |             412.1            |      2.48      |             2.09            |
|         createPet        |           100          |             103.6            |      2.14      |             2.21            |
|         createPet        |          1000          |             81.47            |      1.15      |             4.61            |
|         updatePet        |           10           |             63.1             |      9.55      |             2.2             |
|         updatePet        |           100          |             56.55            |      3.964     |             2.24            |
|         updatePet        |          1000          |            150.42            |      0.697     |            7.351            |
|        createOwner       |           10           |             199.9            |      5.139     |             2.05            |
|        createOwner       |           100          |             17.8             |      8.533     |             2.29            |
|        createOwner       |          1000          |             11.57            |      7.521     |             3.92            |
|        updateOwner       |           10           |             292.5            |      1.441     |             2.09            |
|        updateOwner       |           100          |             74.87            |      2.84      |             2.32            |
|        updateOwner       |          1000          |             42.36            |      2.33      |             5.11            |
### Runtime Analysis 

__________
* Create Owner
![COTime](https://user-images.githubusercontent.com/57294282/141700363-b9ee4606-fcb9-42c7-bd06-0bb65b765492.png)
* Update Owner
![UOTime](https://user-images.githubusercontent.com/57294282/141699821-72a22f46-c3c7-46c6-a4d1-7a827b52bd06.png)
* Create Pet
![CPTime](https://user-images.githubusercontent.com/57294282/141699817-6e1d67da-3e4a-4615-be62-169e73804e9e.png)
* Update Pet
![UPTime](https://user-images.githubusercontent.com/57294282/141699823-0cc13875-dc97-4320-a1fe-36fafe160a23.png)
* Analysis 
Note: For all graphs in the transaction time section, we removed a few outliers that are much larger than the other ones(as high as 7794ms) to better observe the overall trend.
As shown by the graphs, we observed that operations related to owner are generally less time-consuming than operations related to pet, as transaction time of createOwner and updateOwner are around 10ms and 50ms while the transaction time fo createPet and updatePet oscillate around 70ms and 150ms. Also, we noticed that there are different trend among different operations as the transaction time of create owner decreases over time while transaction time of updatePet increases overtime. For the other two operations, we can not conclude a clear trend, as transaction time of updateOwner bounces around 50ms with a few spikes as high as 400 ms and the transaction time of createPet starts with around 40ms for the first 140 transactions and increases to around 140ms for the rest of transactions.


__________
###  CPU and memory usage 
#### CPU percentage
_note: for CPU percentage graphs, the y axis is the CPU usage percentage and x axis is the total time elapsed._
  * Create Owner
![COCPU](https://user-images.githubusercontent.com/57294282/141707350-bc0570c4-2426-479a-be79-3a226b08761d.png)
  * Update Owner
![UOCPU](https://user-images.githubusercontent.com/57294282/141707674-71681f04-5c37-45ff-b8c7-c3935eccc5f6.png)
  * Create Pet
![CPCPU](https://user-images.githubusercontent.com/57294282/141723370-3269d1a4-2e93-4833-b71a-7b41e2145635.png)
  * Update Pet
![UPCPU](https://user-images.githubusercontent.com/57294282/141723370-3269d1a4-2e93-4833-b71a-7b41e2145635.png)
#### Memory Usage
_note: for memory usage graphs, the y axis is the memory used by system in E7 bytes and x axis is the total time elapsed._
  * Create Owner
![COMemory](https://user-images.githubusercontent.com/57294282/141707363-c24855ad-1d24-4283-825a-8a85720e547c.png)
  * Update Owner
![UOMemory](https://user-images.githubusercontent.com/57294282/141707680-cb939333-dc73-4985-bb1c-e91df9e5f1cd.png)
  * Create Pet
![CPMemory](https://user-images.githubusercontent.com/57294282/141723117-abeb0fbd-5023-411c-a2f3-e4c18fd9f7c0.png)
  * Update Pet
![UPMemory](https://user-images.githubusercontent.com/57294282/141707112-dccda401-3004-4463-a701-7c959ef5a009.png)

* As shown by the graphs, the performance of system does not distinctly differ across different CRUD operations in terms of CPU usage. The CPU usage is slightly higher for owner related operation than pet related operation as the average CPU usage of the former reached 7.521% and the latter around is 1.15%. In general, we did not observe any trend for the CPU usage.
As for memory usage, there is a relatively consistent trend among all CRUD operations as the memory usage gradually increases to a peak and descends vertically, which is due to the release of memory.


### Performance Risks 
In Runtime Analysis section, we noted that the transaction time of a single CRUD operation exceeds 1000ms occasionally while the highest average time among various number of total transactions is around 400ms.The presence of these outliers is an uncertain factor and influences the overall performance of the system. The slow completion of operation can have a negative impact on user experience. Also, the create an owner CRUD operation can consume more than 35% CPU. If CPU consumption is too high, users will experience long load and save times, and in the worst-case scenario, the system will start to freeze because the processor is overloaded with too many commands.
