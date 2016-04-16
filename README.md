# drop-wizard-full-example
Dropwizard full example to include all the required concepts of dropwizard , 
it could be used as the starter template  for your next micro-service project .

##Services Description:
This Solutions contains the following exposable services : 
1- Users Service 
2- Admin Services
3- Test Service
![alt tag](https://github.com/kiswanij/drop-wizard-full-example/design/services.PNG
##Architecture:
![alt tag](https://github.com/kiswanij/drop-wizard-full-example/design/archiecture.PNG)
##Database Design: 
 Below is the database ER diagram for the database tables structure, note that the "tc" prefix is refers to True-Caller.
![alt tag](https://github.com/kiswanij/drop-wizard-full-example/design/erd.PNG)

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

