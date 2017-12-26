# Dropwizard full example
Dropwizard full example to include all the required concepts of dropwizard , 
it could be used as the starter template  for your next micro-service project .

##Example Services Description:
This Solutions contains the following exposable services : 
1- Users Service 
2- Admin Services
3- Test Service  
![alt tag](https://github.com/kiswanij/drop-wizard-full-example/blob/master/design/services.PNG)  
##Example Architecture:  
![alt tag](https://github.com/kiswanij/drop-wizard-full-example/blob/master/design/archiecture.PNG)  
##Example Database Design:   
 Below is the database ER diagram for the database tables structure, note that the "tc" prefix is refers to True-Caller.
![alt tag](https://github.com/kiswanij/drop-wizard-full-example/blob/master/design/erd.PNG)  

**Table	tc_users**
Contains the information of  the users (Visitors)
*Fields*	
-User_id: Contains Auto increment primary key
-User_number: Contains the User business number (Maybe phone number)

**Table	tc_visits_log**
Contains the information for the profile visits 
*Fields*	
- Visit_log_id :Contains Auto increment primary key
- Visited_user_id: The Visited user id
- Visitor_user_id: The visitor user id
- Visit_time_stamp: The time stamp of the visit (Date and Time)

##UML Class diagram:
![alt tag](https://github.com/kiswanij/drop-wizard-full-example/blob/master/design/class-diagram.PNG)

## Running The Applications : 
1. Run **start.bat**. (Note , this assumes the Java is installed correctly in your computer , also it assumes that is configured on the Windows PATH environment variable).
Test The Application : 
2. In your browser address  bar type :  **http://localhost:8080/admin/db/clean** . this request will cause to create the database structure available in src/main/resources/create-db.sql file.
3. In your browser address bar , type : **http://localhost:8080/test/create/users/100/20/150** , this will cause to create : 
  1. 100 Mock User Records.
  2. 150 Random Mock visit to the users records  with various dates up to 20 day before the    execution time randomly.
4. In your browser address bar  , type : **http://localhost:8080/users/visitors/1** , which will show you list of the visitors for the user with Id=1 , it will show only Ten NON-expired results in the following format :  
		*Log[498,User (1),User (76),2013-10-12 06:58:45.372,false]* 

Where the first number is the Log Id, the second number the visited user id , the third the visitor user id , then the visit time stamp , then the outDatedFlag.
5. In your browser address bar  , type : **http://localhost:8080/users/all-visitors/1** , where this will show the full list of the user visitors with the above informations , for debugging and testing purposes.


##Available Actions: 
### Admin : 
	1. /admin/db/create   : Create new database structure as scripted in create-db.sql
	2. /admin/db/drop : Drop the database structure as scripted in drop-db.sql
	3. /admin/db/clean : Call drop then create database actions.

### Users: 
	1. /users/visits/log/{visitor_id}/{visited_id} : 
		add visit log by the {visitor_id}  and {visitied_id} variables
	2. /users/visitors/{user} : 
		show the visitors NON-Expired top ten visitors list to the user with id {user}
	3. /users/all-visitors/{user}
		Show all visitors for the user with id #{user} with all there statuses  including outdated 			and none outdated.
###Test: 
	1./test/create/users/{users_count}/{days}/{visits_per_user} : 
	Create mock user records for {user_count} users , and create mock visits with {visits_per_user} 	visits , and make the visit time random between today and (today-{days})

Enjoy!

*Jalal*
