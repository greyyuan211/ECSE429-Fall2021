## Part1: Formal modeling 
### 1) URL of UPPAAL Model:<br>
https://github.com/McGill-ECSE429-Fall2021/assignment3-4-fm-04/blob/main/A4/:Volumes:uppaal64-4.1.24:uppaal64-4.1.24::Volumes:uppaal64-4.1.24:uppaal64-4.1.24::Volumes:uppaal64-4.1.24:uppaal64-4.1.24:formalmodel.xml

### 2) Explanation of UPPAL Model: <br>
* **Global Variable Declaration:**<br>
  ![](https://github.com/McGill-ECSE429-Fall2021/assignment3-4-fm-04/blob/main/A4/global%20declaration.png) <br>
This photo shows all the global variable for the formal model, N stands for the number of processes(we choose 3 in this case), t stands for index of target process(default as -1), x[N] is an array to store the status of each process. 

* **Formal Modeling:**<br>
  ![](https://github.com/McGill-ECSE429-Fall2021/assignment3-4-fm-04/blob/main/A4/formal%20model.png) <br>
The model we implemented representing Dekker’s mutual exclusion algorithm for N processes. Local parameter i is the index of current process. The model starts with idle status(x[1 to N]=false, t=-1), and goes to enter to check if the current process can enter the critical session with existtrue() and allfalse() functions. If there exists one process with true status and its index is not equal to the current process, the current process goes into wait condition. If all other processes except the current one has false status, the current process can enter the cs(critical section). For wait condition, the current process does busy waiting to enter the critical section when t equals to its index i or t equals to -1(default). t and x[i] updates automatically when the transition happens. When a process finishes its task in critical section, it set x[I] to false and t=-1 and return to the idle condition that could allow other processes to enter critical section. 

* **Local Variable Declaration:**<br>
  ![](https://github.com/McGill-ECSE429-Fall2021/assignment3-4-fm-04/blob/main/A4/local%20function%20declaration.png)<br>
These functions are defined and used locally. existtrue() is used to check if the x[N] array has a true status besides the current process, and all false() is used to check if the x[N] array all have false value except the current process. 

* **System Declaration:**<br>
  ![](https://github.com/McGill-ECSE429-Fall2021/assignment3-4-fm-04/blob/main/A4/system%20declaration.png)<br>
System declaration produces three processes with input parameters: index of current process and global array x[N]

## Part2: Properties to check <br>
![](https://github.com/McGill-ECSE429-Fall2021/assignment3-4-fm-04/blob/main/A4/verification.png)<br>
After finishing the model implementation, we need to verifier the correctness of the algorithm. There are three target problems that we need to check:
* The system is deadlock free:<br>
we used A[] not deadlock(true if in every place of the model, there is no deadlock), and received a property satisfy result
* Mutual exclusion is forced:
we used A[] not(P1.cs and P2.cs)(true if P1 and P2 can not enter the critical section together in every place of the model),A[] not(P2.cs and P3.cs)(true if P2 and P3 can not enter the critical section together in every place of the model),A[] not(P1.cs and P2.cs and P3.cs)(true if P1 and P2 and P3 can not enter the critical section together in every place of the model) to check the mutual exclusion model and received a property satisfy result
* The critical section is reachable for some processes:
we used E<> P1.cs(P1 can enter critical section in some place), E<> P2.cs(P2 can enter critical section in some place),E<> P3.cs(P3 can enter critical section in some place) to check if all processes can enter the critical section in some place and received a property satisfy result 

### Part3: Conclusion
By using UPPAAL model checking tool, we simulate how processes will interact and execute of the Dekker’s mutual exclusion algorithm for N processes. We assume there are three processes in our test case, but when N equals to other number, the model and verification can also be satisfy the same as what we created. In the verification part, we check the deadlock, mutual exclusion and reachable situation, and received positive result for every case. Therefore, we could summarize the model is correct in multi-processor processing 
