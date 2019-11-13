create database private_school;
use private_school;
-- drop database  private_school;




-- Create tables

create table student(
	student_id int not null auto_increment,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    date_of_birth date not null,
    tuitionFees decimal(7,2),
    primary key(student_id)
);

create table trainer(
	trainer_id int not null auto_increment,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    trainer_subject varchar(250) not null,
    primary key(trainer_id)
);

create table course(
	course_id int not null auto_increment,
    course_title varchar(100) not null,
    course_stream varchar(100) not null,
    course_type varchar(100) not null,
	course_start_date date,
    course_end_date date,
    primary key (course_id)
);

create table assignment(
	assignment_id int not null auto_increment,
    assignment_title varchar(100) not null,
    assignment_desc varchar(250) not null,
    sub_date_time datetime,
    course_id int,
    primary key (assignment_id),
    foreign key (course_id) references course(course_id)
);

create table students_in_course(
	student_id int not null,
    course_id int not null,
	primary key (student_id, course_id),
    foreign key (student_id) references student(student_id),
    foreign key (course_id) references course(course_id)
);

create table trainers_in_course(
	trainer_id int not null,
    course_id int not null,
	primary key (trainer_id,course_id),
    foreign key (trainer_id) references trainer (trainer_id),
    foreign key (course_id) references course (course_id) 
);

create table marks(
	student_id int not null,
    assignment_id int not null,
	oral_mark int default 0,
    total_mark int default 0,
    primary key (student_id,assignment_id),
    foreign key (student_id) references student(student_id),
    foreign key (assignment_id) references assignment(assignment_id)
);

create table last_assignment_ID(
	id int not null primary key
);

create table synthetic_data(
	id int not null auto_increment,
    status  boolean,
	primary key (id)
);




-- Create procedures/triggers

delimiter  //
create procedure insert_student(in f_name varchar(30),l_name varchar(30), birthdate date, fees decimal(7,2))
begin
	insert into student values(null,f_name,l_name,birthdate,fees);
end //
delimiter ;

delimiter  //
create procedure insert_trainer(in f_name varchar(30),l_name varchar(30), t_subject varchar(250))
begin
	insert into trainer values(null,f_name,l_name,t_subject);
end //
delimiter ;

delimiter  //
create procedure insert_course(in c_title varchar(100), c_stream varchar(100), c_type varchar(100), start_date date, end_date date)
begin
	insert into course values(null,c_title, c_stream,c_type,start_date,end_date);
end //
delimiter ;


delimiter  //
create procedure insert_assignment(in a_title varchar(100), description varchar(250), sub_datetime datetime, c_id int)
begin
	insert into assignment values(null,a_title, description,sub_datetime,c_id);
end //
delimiter ;


delimiter //
create procedure delete_records()
begin	
	delete from last_assignment_ID;
	delete from marks;
    delete from students_in_course;
    delete from trainers_in_course;
    
    delete from assignment;
    alter table assignment auto_increment = 1;
    
	delete from student;
    alter table student auto_increment = 1;
    
    delete from trainer;
    alter table trainer auto_increment = 1;
    
    delete from course;
    alter table course auto_increment = 1;
end //
delimiter ;




delimiter //
create trigger insert_assignment_trigger 
after insert 
on assignment 
for each row
begin
	delete from last_assignment_ID;
	insert into last_assignment_ID values(new.assignment_id);
end //
delimiter ;


