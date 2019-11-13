
-- Queries
/*Before running these queries make sure you add data first. For example, use Synthetic data can populate tables via  menu
in the java console application
*/
use private_school;

select * from student;
select * from trainer;
select * from course;
select * from assignment;

select s.first_name, s.last_name, c.course_title,c.course_type, c.course_stream, c.course_start_date, c.course_start_date
from students_in_course sc
join student s on s.student_id=sc.student_id
join course c on c.course_id=sc.course_id
order by sc.course_id;


select t.first_name, t.last_name, c.course_title,c.course_type, c.course_stream, c.course_start_date, c.course_start_date
from trainers_in_course tc
join trainer t on t.trainer_id=tc.trainer_id
join course c on c.course_id=tc.course_id
order by tc.course_id;


select a.assignment_title,a.assignment_desc,a.sub_date_time, c.course_title,c.course_type, c.course_stream, c.course_start_date, c.course_start_date
from assignment a
join course c on c.course_id=a.course_id
order by a.course_id;

select a.assignment_title,a.assignment_desc,c.course_title,c.course_type, c.course_stream,s.first_name, s.last_name
from  marks m 
join assignment a on m.assignment_id=a.assignment_id
join course c on c.course_id=a.course_id
join student s on s.student_id=m.student_id
order by a.course_id, s.student_id;

select * 
from student 
where student_id in
                    (select student_id
                    from students_in_course
                    group by student_id
                    having count(student_id)>1);
