# Introduction
In this assignment,  we are required to find the fault of the cruise control system with Graphwalker. The entire system is a black box so we do not know the detailed implementation inside, so we would need to find potential issues by analyzing its states and transitions. We would first need to define the correct state machine of the cruise control system by reading the system specification document. Then, we would implement the entire state machine to Graphwalker and check the state machine by running it. If the state machine can be run in the Graphwalker UI with no infinite loop or other errors, we can proceed to define it in the maven project.  
After setting up the project by following the instruction on the readme, we would then download the JSON file from the Graphwalker UI which contains the specification of our normal model. Then, we would implement the CruiseControlTest to define the function to call at each edge and state. The definitions are as followed:  
```
@GraphWalker(value = "random(edge_coverage(100))", start = "v_Off" )
public class CruiseControlTest extends ExecutionContext implements CruiseControlModel {

    // Creating a CruiseControllerImpl object
    CruiseControllerImpl controller = new CruiseControllerImpl(new CarControllerImpl());
    
    @Override
    public void e_Cruise(){
        System.out.println("e_Cruise");
        controller.toggleCruise();
    }

    @Override
    public void e_Decel(){
        System.out.println("e_Decel");
        controller.setSpeedDecel();
    }

    @Override
    public void e_Accel(){
        System.out.println("e_Accel");
        controller.resumeCruiseAccel();
    }

    @Override
    public void e_Brake(){
        System.out.println("e_Brake");
        controller.brakeActivated();
    }

    @Override
    public void e_Cancel(){
        System.out.println("e_Cancel");
        controller.cancelCruise();
    }

    @Override
    public void v_Off(){
        Assert.assertTrue(controller.getCruiseControllerStatus() == CruiseControllerStatus.OFF);
        System.out.println("v_Off");
    }

    @Override
    public void v_Deactivated(){
        Assert.assertTrue(controller.getCruiseControllerStatus() == CruiseControllerStatus.PASSIVE);
        System.out.println("v_Deactivated");
    }

    @Override
    public void v_Activated(){
        Assert.assertTrue(controller.getCruiseControllerStatus() == CruiseControllerStatus.ACTIVE);
        System.out.println("v_Activated");
    }

    @Override
    public void v_Canceled(){
        System.out.println("!!!!!!!!!!!!");
        Assert.assertTrue(controller.getCruiseControllerStatus() == CruiseControllerStatus.CANCELLED);
        System.out.println("v_Canceled");
    }
}
```
Now we are ready to run the model to investigate the fault in the cruise control system.
# Normal model
## State machine diagram
![3d838b98709e88402910be3f6e34367](https://user-images.githubusercontent.com/56415387/143727521-27d33ec6-0522-4c1a-b856-ac44720abfab.png)  
[Normal model JSON file](https://github.com/McGill-ECSE429-Fall2021/assignment3-4-fm-04/blob/main/A3_JSON/Normal%20model.json)
## Maven instruction
we first need to clean the maven project to prevent potential build error:
```
mvn clean
```
then run
```
mvn install
```
After moving the JSON file to the resources directory to replace the placeholder txt file, we are now ready to start working with GraphWalker. In the A3 directory, run the following commands to generate Java source code from your .json file:
```
mvn graphwalker:generate-sources
```
After the interface has been generated, the way we implemented the test file has been discussed in the introduction section. With that being done, we can run Graphwalker directly:
```
mvn graphwalker:test
```
## Graphwalker test output
After we run the test, we get the following output:  
![e466a29bb906a11a9a95397df549838](https://user-images.githubusercontent.com/56415387/143727951-d4ede202-50bd-44da-98b4-3d8e2cc0b2b7.png)  
![9bd730c2cb33e1439b34d2cce71d668](https://user-images.githubusercontent.com/56415387/143727970-188c55a6-d7ae-4ee0-af1c-bfcc6b41115f.png)  
We see that the build actually failed at v_canceled. After spending time to make sure again that there is nothing wrong with the test implementations, I have a doubt that there might be a fault with the program which causes this build failure. Since the Graph walking path is decided randomly until it reaches 100 percent edge coverage, I run the program many times to make sure it is one specific error that makes this happen.  
With the following output of the Graphwailker test, I can finally verify my assumption:  
![e59ac4bcc7f7c6e31b29948aa8d9c5f](https://user-images.githubusercontent.com/56415387/143728645-8af696ed-da63-4533-a2b1-8ca3bb78bef8.png)  
![6fc4aba625578e947b80a8ff2e6405c](https://user-images.githubusercontent.com/56415387/143728663-d65f7e1d-0eb5-432a-9bd0-27712252a67d.png)  
![8f8fc13cf35ace5f3a649d51ee1f740](https://user-images.githubusercontent.com/56415387/143728670-0480f8a5-d199-403b-8413-cc04ab56e0a3.png)  
From this test run, we can very clearly see that the program will specifically fail when going from v_Deactivated to v_Canceled. Previously, these two states have been traversed with no problem. The state can go normally from v_Activated to v_canceled as well. So, I have enough proof to suspect that the program may not handle this specification correctly. 
## Potential implementation issues
In the system specification, the system can be interrupted with 'brake' or 'cancel' while in CC control. CC control is defined with two states: both v_deactivated and v_activated. So, it should be able to go to v_Canceled in both states with events e_Brake and e_Cancel as shown in the normal model. Let's further verify this is indeed a fault using a second fault model.

# Fault model 1
## State machine diagram
![5899ed0aa0a59ea72ee881f3404d11f](https://user-images.githubusercontent.com/56415387/143728922-7881f4b4-2189-4608-b06b-ed220c3f5eba.png)
[Fault model JSON file](https://github.com/McGill-ECSE429-Fall2021/assignment3-4-fm-04/blob/main/A3_JSON/Fault%20model.json)
## Maven instruction
we first need to clean the maven project to prevent potential build error:
```
mvn clean
```
then run
```
mvn install
```
After moving the JSON file to the resources directory to replace the placeholder txt file, we are now ready to start working with GraphWalker. In the A3 directory, run the following commands to generate Java source code from your .json file:
```
mvn graphwalker:generate-sources
```
After the interface has been generated, the way we implemented the test file has been discussed in the introduction section. With that being done, we can run Graphwalker directly:
```
mvn graphwalker:test
```
## Graphwalker test output
After we run the test, we get the following output:  
![d233ce8738aa8bbc49060cd892469a9](https://user-images.githubusercontent.com/56415387/143728981-5cb2bad7-a5b3-4ebe-8ff1-1b22aec2f238.png)  
![fde762acf704191e2f2a167f1be8274](https://user-images.githubusercontent.com/56415387/143728992-d1e376ef-cf69-4041-9b80-63ff583296e3.png)  
![acf472ea181e0fa141d41ecd1f57542](https://user-images.githubusercontent.com/56415387/143729004-71660f47-571a-4459-8c1c-5c070e948d10.png)  
![c5b201de8b4fd233b1bdc98d508d77c](https://user-images.githubusercontent.com/56415387/143729013-62c50c77-713b-48bf-afc7-cd464e887f8a.png)  
![fbf2c1e45ca9e3b2ac19ff44b37971a](https://user-images.githubusercontent.com/56415387/143729015-33d4d7ae-afc3-4e86-aa1c-19cd6c62f515.png)  
After I made a change to the normal model(removing the two edges from v_Deactivated to v_Canceled), you can see the build is successful now! This verifies that a fault of the system has been discovered. I have marked the v_Canceled state log information with '!!!!!!!!!!', so you could clearly see now the state machine will not have any issue to transverse to v_Canceled, since the transition from v_Deactivated to it has been removed.

## Potential implementation issues
My guess of the possible implementing issues is: the code has defined that brake and cancel events can only be triggered when the cruise control system is in an activated state. This is a conflict with the specification as stated previously. Both deactivated and activated states are in CC control. When in a deactivated state, the CC control has already been turned on and the car is moving. The user still can press cancel to cancel the cruise while in this state. The user can also brake to make the car stop to cancel the CC control.

# Fault model 2
## State machine diagram
![Fault Model 2](https://raw.githubusercontent.com/franciscomeau/images/master/Fault%20Graph%202%20highlighted.jpg?token=AGCZ3UEARF75I74HCLPHW5DBUWD4W)

Pointed by the blue arrow is the added edge that could expose any implementation issues. This is the edge e_Accel going from v_Deactivated to v_Activated.<br/>
<br/>
In the real car for which the behavior is specified, this case would be the equivalent of:<br/>
* toggling on cruise control
* pressing the RES/ACCEL button
* and cruise control turning on<br/>
<br/>
This behavior is not what is described in the specifications.

## Maven instruction
we first need to clean the maven project to prevent potential build error:
```
mvn clean
```
then run
```
mvn install
```
After moving the JSON file to the resources directory to replace the placeholder txt file, we are now ready to start working with GraphWalker. In the A3 directory, run the following commands to generate Java source code from your .json file:
```
mvn graphwalker:generate-sources
```
After the interface has been generated, the way we implemented the test file has been discussed in the introduction section. With that being done, we can run Graphwalker directly:
```
mvn graphwalker:test
```
## Graphwalker test output
After we run the test, we get the following output:  
![tests passing](https://raw.githubusercontent.com/franciscomeau/images/master/tests%20should%20not%20be%20passing.JPG?token=AGCZ3UDDEA6PQ44BOQDEKGTBUWD46)
As we can see. The tests still pass when the expected result is to get an error from the spec violating edge. This demonstrates that there is an implementation issue with the resumeCruiseAccel() function.

## Potential implementation issues
It is likely that there is no state check being made in the resumeCruiseAccel() function to prevent the Cruise Control from being activated from what we called the 'v_Deactivated' state.