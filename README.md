# Private-School-Structure-With-SQL
Part B of Private School Structure using MySql

Following PART A you need to implement the following functionality:
1. Makea draft design ofadatabase that can keep data for the main entities of the assignment and name the tables as: Students, Trainers, Assignments, Courses [15 marks]
2. Identify any other tables you need based on your implementation and construct them [15 marks]
3. Design the ERD of your system and verify it through an online tool such as https://sqldbm.com/ (it requires a free account) [15 marks]
4. Populate the tables of the database with enough data[10marks]
5. You need to produce sql queries that output the following [19 marks in
total]:
• A list of all the students [2 marks]
• A list of all the trainers [2 marks]
• A list of all the assignments [2 marks]
• A list of all the courses [2 marks]
• All the students per course [2 marks]
• All the trainers per course [2 marks]
• All the assignments per course [2 marks]
• All the assignments per course per student [2 marks]
• A list of students that belong to more than one courses [3 marks]
6. You also need to produce a small project that [26 marks]
a. makes a connection to the database and executes the above sql
  queries [9 marks]
b. makes a connection to the database and inserts input data from the keyboard to the following tables,
i. students [2 marks]
ii. trainers [2 marks]
iii. assignments [2 marks]
iv. courses [2 marks]
v. students per course [3 marks]
vi. trainers per course [3 marks]
vii. assignments per student per course [3 marks]


========================
Notes:

Comments for the project:

1)Database private_school is created using the private_school.sql file. That is, the database, the tables as well the procedures. 
2)private_school.sql does not contain any data. To populate the tables with data run the java console application and select 'Use synthetic data' from the intro menu.
3)To run the java console application, input your user-name and password (mysql).
***Jar file  mysql-connector-java-8.0.16.tar. is not in the Libraries folder of the project. I use the jar file directly from Java/Library/Extension path in mac.
4)private_school_select_queries.sql contains only select queries.(populate data first to run them - see 2).
5)When a student is inserted into a course, it takes all the assignment tha exist in the course. When an assignment is inserted into a course, all students that belong to the course take this assignment. Initial marks are 0 but can be update (see. modify marks in the assignment menu).



Versions:
--JAVA
java version "1.8.0_211"
Java(TM) SE Runtime Environment (build 1.8.0_211-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.211-b12, mixed mode)

NetBeans IDE 8.2


--mySQL
Server version: 5.7.16 MySQL Community Server (GPL)

mySQL Workbench 6.3.10 build


