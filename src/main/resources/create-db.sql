CREATE TABLE IF NOT EXISTS tc_users
	(id NUMBER(20) AUTO_INCREMENT, 
	name varchar(50));

CREATE TABLE IF NOT EXISTS tc_visits_log
	(id NUMBER(20) AUTO_INCREMENT,
	visitor_user_id NUMBER(20),
	visited_user_id NUMBER(20),
	visit_time_stamp 
	TIMESTAMP,
	FOREIGN KEY (visitor_user_id) REFERENCES tc_users(id),
	FOREIGN KEY (visited_user_id) REFERENCES tc_users(id));