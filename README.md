# Robot Read - Measure Polution Over Polyline  
  
### Problem definition ###  
The city of London has implemented a robot that moves through a predetermined route every morning, collecting pollution data at fixed intervals, and reporting them.  
  
The goal of this exercise is to implement code that would control such robot, within the following guidelines:  
 * Every morning an operator feeds the robot with the polyline (taken from Google) with the route of the robot (see polyline below).  
 * The robot will move along the points of the polyline.  
 * Acceptable speed for the robot is between 1 and 3 meters per second  
 * Every 100 meters, the robot “reads” the level of PM2.5 particles in the location.  
 * Values of PM2.5 will be in the following range:  
	 * 0 to 50: Good  
	 * 51 - 100: Moderate  
    * 101 - 150: USG (Unhealthy to Sensitive Groups)  
	*  +150: Unhealthy  
	* Of course this can be randomly generated at each stop  
* Every 15 minutes the robot reports the average of all new readings since the last one and reports according to the grouping above. The output should be a json like:  
```shell  
{“timestamp”: 1528106219,“location”: {“lat”: 51.23241, “lng”: -0.1223},“level”: “USG”,“source”: “robot”}  
```
### Inputs / Outputs

The only input is the polyline, given by an operator as a startup parameter.  
The output is the periodic (15 minutes) report.
   
### Implementation ###  
**Requirenments**  
- JDK 1.8  
- Maven version 3.3.9  
- Junit version 4.12  
- Maven Compiler Plugin version 3.8.1  
- Maven Jar Plugin version 3.1.2
  
  
#### Running the solution ####  
  
**Run JUnit Tests**  
* To test the application, on the root folder of the project execute:  
```shell  
$ mvn test  
```  
`JUnit` tests are located under `/src/test/java`.  
  
  
**Build**  
* To generate a jar file of the solution, on the root folder of the project execute:  
```shell  
$ mvn package  
```  
**Run the application**  
* To run the application, after the package generation, on the root folder of the project execute:   
```shell  
$ java -jar target\robotread-1.0.jar  
```
****
  
**Example of menu**  
```shell  
despinosa:pruebas mingo$ java -jar kiwiland-1.0.jar graph.txt commands.txt 
1. Start
2. Stop
3. Re-Route
4. Print report
5. Exit
Enter one of the options:
  
Process finished with exit code 0  
```
**Example of menu Re-Route**  
```shell  
despinosa:pruebas mingo$ java -jar kiwiland-1.0.jar graph.txt commands.txt 
1. Start
2. Stop
3. Re-Route
4. Print report
5. Exit
Enter one of the options:
3
Insert new polyline:
sadf789nm23@
new polylineInput:sadf789nm23@
  
```    
### Design ###  
  
Using hexagonal architecture(ports and adapters, clean architecture or onion architecture).
The development has been done with TDD, London school or outside-in, it has been proven that the requirements are satisfied by testing features.
  
In order to solve the proposed problems I have used the following methods:  
  
* To "read" every 100 meters the level of particles PM2.5, I used a scheduler every 33 seconds (taken from the formula t=d/v), in addition to playing with the distance traveled and the distance accumulated to get the position.  
* To print the report every 15 minutes, I used a scheduler like as in the previous point.    
  
 I take the **bonus 2** option to make an interactive API with the console, where you can stop and start the robot system, print the report while the robot is running or change the default polyline from the example to your own.  
  
### JavaDoc ###  
  
can be found [here](https://github.com/gornovah/robot_reads_pm25/tree/master/JavaDoc).  
  
### Aditional info ### 

Polyline
For the sake of this exercise, this is the polyline to use. It covers a route between West London and the City.
``` text
mpjyHx`i@VjAVKnAh@BHHX@LZR@Bj@Ml@WWc@]w@bAyAfBmCb@o@pLeQfCsDVa@@ODQR}AJ{A?{BGu
AD_@FKb@MTUX]Le@^kBVcAVo@Ta@|EaFh@m@FWaA{DCo@q@mCm@cC{A_GWeA}@sGSeAcA_EOSMa
@}A_GsAwFkAiEoAaFaBoEGo@]_AIWW{AQyAUyBQqAI_BFkEd@aHZcDlAyJLaBPqDDeD?mBEiA}@F]yKWqGSkI
CmCIeZIuZi@_Sw@{WgAoXS{DOcAWq@KQGIFQDGn@Y`@MJEFIHyAVQVOJGHgFRJBBCCSKBcAKoACyA?m@^y
VJmLJ{FGGWq@e@eBIe@Ei@?q@Bk@Hs@Le@Rk@gCuIkJcZsDwLd@g@Oe@o@mB{BgHQYq@qBQYOMSM
GBUBGCYc@E_@H]DWJST?JFFHBDNBJ?LED?LBv@WfAc@@EDGNK|@e@hAa@`Bk@b@OEk@Go@IeACoA@
a@PyB`@yDDc@e@K{Bi@oA_@w@]m@_@]QkBoAwC{BmAeAo@s@uAoB_AaBmAwCa@mAo@iCgAwFg@iD
q@}G[uEU_GBuP@cICmA?eI?qCB{FBkCI}BOyCMiAGcAC{AN{YFqD^}FR}CNu@JcAHu@b@_E`@}DVsB^mBTsAQ
KkCmAg@[YQOIOvAi@[m@e@s@g@GKCKAEJIn@g@GYGIc@ScBoAf@{A`@uAlBfAG`@
```
 
