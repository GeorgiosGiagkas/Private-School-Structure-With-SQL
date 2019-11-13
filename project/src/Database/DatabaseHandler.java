/*
 * Handles insert, update , delete and select using Statement and PreparedStatement classes or calling
 * procedures from sql.
 *
 */
package Database;

import Check.Checker;
import SchoolStructure.Assignment;
import SchoolStructure.Course;
import SchoolStructure.Student;
import SchoolStructure.Trainer;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giagkas
 */
public class DatabaseHandler {

    public static void insertStudent(Connection connection, Student student) {
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall("call insert_student(?,?,?,?);");
            callableStatement.setString(1, student.getFirstName());
            callableStatement.setString(2, student.getLastName());
            callableStatement.setDate(3, Date.valueOf(student.getDateOfBirth()),java.util.Calendar.getInstance());
            callableStatement.setDouble(4, student.getTuitionFees());
            callableStatement.execute();
            callableStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Student> retrieveStudents(Connection connection) {
        ArrayList<Student> students = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "select * \n"
                + "from student ;";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Student student = new Student(resultSet.getDate(4).toLocalDate(), resultSet.getDouble(5),
                        resultSet.getString(2), resultSet.getString(2));
                students.add(student);
            }
            statement.close();
            resultSet.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return students;

    }

    public static void showStudents(Connection connection) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from student;");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t"
                        + resultSet.getString(2) + "\t"
                        + resultSet.getString(3) + "\t"
                        + resultSet.getString(4) + "\t"
                        + resultSet.getString(5));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void showStudentsWithAssignedProject(Connection connection) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select distinct s.student_id,s.first_name,s.last_name,s.date_of_birth,s.tuitionFees\n"
                    + "from student s \n"
                    + "join marks m on s.student_id = m.student_id;");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t"
                        + resultSet.getString(2) + "\t"
                        + resultSet.getString(3) + "\t"
                        + resultSet.getString(4) + "\t"
                        + resultSet.getString(5));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insertTrainer(Connection connection, Trainer trainer) {
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall("call insert_trainer(?,?,?);");
            callableStatement.setString(1, trainer.getFirstName());
            callableStatement.setString(2, trainer.getLastName());
            callableStatement.setString(3, trainer.getSubject());

            callableStatement.execute();
            callableStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Trainer> retrieveTrainers(Connection connection) {
        ArrayList<Trainer> trainers = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "select * \n"
                + "from trainer ;";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Trainer trainer = new Trainer(resultSet.getString(4), resultSet.getString(2), resultSet.getString(3));
                trainers.add(trainer);
            }
            statement.close();
            resultSet.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return trainers;

    }

    public static void showTrainers(Connection connection) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from trainer;");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t"
                        + resultSet.getString(2) + "\t"
                        + resultSet.getString(3) + "\t"
                        + resultSet.getString(4));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insertCourse(Connection connection, Course course) {
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall("call insert_course(?,?,?,?,?);");
            callableStatement.setString(1, course.getTitle());
            callableStatement.setString(2, course.getStream());
            callableStatement.setString(3, course.getType());
            callableStatement.setDate(4, Date.valueOf(course.getStartDate()),java.util.Calendar.getInstance());
            callableStatement.setDate(5, Date.valueOf(course.getEndDate()),java.util.Calendar.getInstance());
            callableStatement.execute();
            callableStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Course> retrieveCourses(Connection connection) {
        ArrayList<Course> courses = new ArrayList();
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "select * \n"
                + "from course ;";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Course course = new Course(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getDate(5).toLocalDate(), resultSet.getDate(6).toLocalDate());
                courses.add(course);
            }
            statement.close();
            resultSet.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courses;

    }

    public static void showCourses(Connection connection) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from course;");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t"
                        + resultSet.getString(2) + "\t"
                        + resultSet.getString(3) + "\t"
                        + resultSet.getString(4) + "\t"
                        + resultSet.getString(5) + "\t"
                        + resultSet.getString(6));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void showCoursesInAssignment(Connection connection) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select c.course_id,c.course_title,c.course_stream,c.course_type,c.course_start_date,c.course_end_date\n"
                    + "from course c \n"
                    + "join assignment a on c.course_id=a.assignment_id;");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t"
                        + resultSet.getString(2) + "\t"
                        + resultSet.getString(3) + "\t"
                        + resultSet.getString(4) + "\t"
                        + resultSet.getString(5) + "\t"
                        + resultSet.getString(6));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void showCoursesForStudent(Connection connection, int studentId) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String query="select c.course_id,c.course_title,c.course_stream,c.course_type,c.course_start_date,c.course_end_date\n"
                    + "from course c \n"
                    + "join students_in_course s on s.course_id=c.course_id \n"
                    + "where s.student_id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, studentId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t"
                        + resultSet.getString(2) + "\t"
                        + resultSet.getString(3) + "\t"
                        + resultSet.getString(4) + "\t"
                        + resultSet.getString(5) + "\t"
                        + resultSet.getString(6));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insertAssignment(Connection connection, Assignment assignment, int courseId) {
        CallableStatement callableStatement = null;
        if (Checker.checkExistsCourseId(connection, courseId)) {
            try {
                callableStatement = connection.prepareCall("call insert_assignment(?,?,?,?);");
                callableStatement.setString(1, assignment.getTitle());
                callableStatement.setString(2, assignment.getDescription());
                callableStatement.setTimestamp(3, Timestamp.valueOf(assignment.getSubDateTime()),java.util.Calendar.getInstance());
                callableStatement.setInt(4, courseId);

                callableStatement.execute();
                callableStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void showAssignments(Connection connection) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select assignment_id,assignment_title,assignment_desc,sub_date_time from assignment;");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t"
                        + resultSet.getString(2) + "\t"
                        + resultSet.getString(3) + "\t"
                        + resultSet.getString(4));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insertStudentToCourse(Connection connection, int student_id, int course_id) {
        PreparedStatement statement = null;
        if (Checker.checkExistsStudentId(connection, student_id) && Checker.checkExistsCourseId(connection, course_id)) {
            try {
                statement = connection.prepareStatement("insert into students_in_course values(?,?);");
                statement.setInt(1, student_id);
                statement.setInt(2, course_id);
                statement.executeUpdate();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void showStudentsInCourse(Connection connection, int course_id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        if (Checker.checkExistsCourseId(connection, course_id)) {
            String query = "select s.student_id, s.first_name, s.last_name, s.date_of_birth,s.tuitionFees\n"
                    + "from student s\n"
                    + "join students_in_course c on s.student_id = c.student_id\n"
                    + "where c.course_id = ?";
            try {
                statement = connection.prepareStatement(query);
                statement.setInt(1, course_id);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + "\t"
                            + resultSet.getString(2) + "\t"
                            + resultSet.getString(3) + "\t"
                            + resultSet.getString(4) + "\t"
                            + resultSet.getString(5));
                }
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void showStudentsInMoreThanOneCourse(Connection connection) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from student where student_id in\n"
                    + "(select student_id\n"
                    + "from students_in_course\n"
                    + "group by student_id\n"
                    + "having count(student_id)>1);");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t"
                        + resultSet.getString(2) + "\t"
                        + resultSet.getString(3) + "\t"
                        + resultSet.getString(4) + "\t"
                        + resultSet.getString(5));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void insertTrainerToCourse(Connection connection, int trainer_id, int course_id) {
        PreparedStatement statement = null;
        if (Checker.checkExistsTrainerId(connection, trainer_id) && Checker.checkExistsCourseId(connection, course_id)) {
            try {
                statement = connection.prepareStatement("insert into trainers_in_course values(?,?);");
                statement.setInt(1, trainer_id);
                statement.setInt(2, course_id);
                statement.executeUpdate();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void showTrainersInCourse(Connection connection, int course_id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        if (Checker.checkExistsCourseId(connection, course_id)) {
            String query = "select t.trainer_id,t.first_name,t.last_name,t.trainer_subject\n"
                    + "from trainer t\n"
                    + "join trainers_in_course c on t.trainer_id=c.trainer_id\n"
                    + "where c.course_id= ?";
            try {
                statement = connection.prepareStatement(query);
                statement.setInt(1, course_id);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + "\t"
                            + resultSet.getString(2) + "\t"
                            + resultSet.getString(3) + "\t"
                            + resultSet.getString(4));
                }
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void showAssignmentsInCourse(Connection connection, int course_id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        if (courseIdExistsInAssignments(connection, course_id)) {
            String query = "select assignment_id,assignment_title,assignment_desc,sub_date_time\n"
                    + "from assignment\n"
                    + "where course_id=?";
            try {
                statement = connection.prepareStatement(query);
                statement.setInt(1, course_id);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + "\t"
                            + resultSet.getString(2) + "\t"
                            + resultSet.getString(3) + "\t"
                            + resultSet.getString(4));
                }
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void insertAssignmentToStudent(Connection connection, int student_id, int assignment_id) {
        PreparedStatement statement = null;
        if (Checker.checkExistsStudentId(connection, student_id) && Checker.checkExistsAssignmentId(connection, assignment_id)) {
            try {
                statement = connection.prepareStatement("insert into marks(student_id,assignment_id) values(?,?);");
                statement.setInt(1, student_id);
                statement.setInt(2, assignment_id);
                statement.executeUpdate();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void modifyMarks(Connection connection, int student_id, int assignment_id, int oralMark, int totalMark) {
        PreparedStatement statement = null;
        if (Checker.checkExistsStudentId(connection, student_id) && Checker.checkExistsAssignmentId(connection, assignment_id)) {
            try {
                statement = connection.prepareStatement("update marks set oral_mark=? ,total_mark=?\n"
                        + "where student_id=? and assignment_id=?;");
                statement.setInt(1, oralMark);
                statement.setInt(2, totalMark);
                statement.setInt(3, student_id);
                statement.setInt(4, assignment_id);
                statement.executeUpdate();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void showAssignmentsPerStudent(Connection connection, int student_id, int course_id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        if (courseIdExistsInMarks(connection, course_id) && Checker.checkExistsStudentIdInMarks(connection, student_id)) {
            String query = "select a.assignment_id, a.assignment_title,a.assignment_desc,a.sub_date_time,m.oral_mark,m.total_mark\n"
                    + "from marks m\n"
                    + "join student s on s.student_id = m.student_id\n"
                    + "join assignment a on m.assignment_id=a.assignment_id\n"
                    + "where a.course_id = ? and s.student_id = ?";
            try {
                statement = connection.prepareStatement(query);
                statement.setInt(1, course_id);
                statement.setInt(2, student_id);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + "\t"
                            + resultSet.getString(2) + "\t"
                            + resultSet.getString(3) + "\t"
                            + resultSet.getString(4) + "\t"
                            + resultSet.getString(5) + "\t"
                            + resultSet.getString(6));
                }
                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static int retrieveLastAssignmentID(Connection connection) {
        Statement statement = null;
        ResultSet resultSet = null;
        int id = -1;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select id \n"
                    + "from last_assignment_ID;");
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    

    public static ArrayList<Integer> retrieveStudentsIdFromMarks(Connection connection) {
        ArrayList<Integer> ids = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        boolean exists = false;
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select distinct student_id \n"
                    + "from marks ;");
            while (resultSet.next()) {
                ids.add(resultSet.getInt(1));
                
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ids;
    }
    public static ArrayList<Integer> retrieveStudentsIdFromMarks(Connection connection, int assignmentId) {
        ArrayList<Integer> ids = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean exists = false;
        
        try {
            statement = connection.prepareStatement("select distinct student_id \n"
                    + "from marks \n"+
                    "where assignment_id =?");
            statement.setInt(1, assignmentId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getInt(1));
                
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ids;
    }

    

    public static boolean studentTableIsEmpty(Connection connection) {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean isEmpty = false;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select exists(select 1 from student);");
            resultSet.next();
            if (resultSet.getInt(1) == 0) {
                isEmpty = true;
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isEmpty;
    }
    
    public static boolean marksTableIsEmpty(Connection connection) {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean isEmpty = false;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select exists(select 1 from marks);");
            resultSet.next();
            if (resultSet.getInt(1) == 0) {
                isEmpty = true;
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isEmpty;
    }
    
    public static boolean studentHasMarkInCourse(Connection connection, int studentId, int courseId) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean hasMark = false;
        try {
            statement = connection.prepareStatement("select count(*)\n" +
                                    "from marks m\n" +
                                    "join assignment a on m.assignment_id=a.assignment_id\n" +
                                    "join students_in_course c on m.student_id = c.student_id\n" +
                                    "where c.student_id = ? and a.course_id = ?");
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            resultSet = statement.executeQuery();
            resultSet.next();
            if (resultSet.getInt(1) > 0) {
                hasMark = true;
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasMark;
    }

    

    public static boolean trainerTableIsEmpty(Connection connection) {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean isEmpty = false;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select exists(select 1 from trainer);");
            resultSet.next();
            if (resultSet.getInt(1) == 0) {
                isEmpty = true;
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isEmpty;
    }

    

    private static boolean courseIdExistsInAssignments(Connection connection, int id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean exists = false;
        try {
            statement = connection.prepareStatement("select distinct course_id \n"
                    + "from assignment\n"
                    + "where course_id= ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                if (resultSet.getInt(1) == id) {
                    exists = true;
                }
            }
            resultSet.close();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }

    private static boolean courseIdExistsInMarks(Connection connection, int id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean exists = false;
        try {
            statement = connection.prepareStatement("select distinct a.course_id \n"
                    + "from marks m\n"
                    + "join assignment a on a.assignment_id=m.assignment_id\n"
                    + "where a.course_id= ?");
            
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                if (resultSet.getInt(1) == id) {
                    exists = true;
                }
            }
            resultSet.close();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }

    public static boolean courseTableIsEmpty(Connection connection) {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean isEmpty = false;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select exists(select 1 from course);");
            resultSet.next();
            if (resultSet.getInt(1) == 0) {
                isEmpty = true;
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isEmpty;
    }

    

    public static boolean assignmentTableIsEmpty(Connection connection) {
        Statement statement = null;
        ResultSet resultSet = null;
        boolean isEmpty = false;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select exists(select 1 from assignment);");
            resultSet.next();
            if (resultSet.getInt(1) == 0) {
                isEmpty = true;
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isEmpty;
    }

    

    

    public static ArrayList<Integer> retrieveStudentsId(Connection connection) {
        ArrayList<Integer> ids = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select student_id \n"
                    + "from student;");
            while (resultSet.next()) {
                ids.add(resultSet.getInt(1));
            }
            resultSet.close();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ids;
    }

    public static ArrayList<Integer> retrieveStudentsId(Connection connection, int courseID) {
        ArrayList<Integer> ids = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("select student_id \n"
                    + "from students_in_course where course_id = ?;");
            statement.setInt(1, courseID);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                ids.add(resultSet.getInt(1));
            }
            resultSet.close();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ids;
    }

    public static ArrayList<Integer> retrieveCoursesId(Connection connection) {
        ArrayList<Integer> ids = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select course_id \n"
                    + "from course;");
            while (resultSet.next()) {
                ids.add(resultSet.getInt(1));
            }
            resultSet.close();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ids;
    }

    public static ArrayList<Integer> retrieveTrainersId(Connection connection) {
        ArrayList<Integer> ids = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select trainer_id \n"
                    + "from trainer;");
            while (resultSet.next()) {
                ids.add(resultSet.getInt(1));
            }
            resultSet.close();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ids;
    }

    public static ArrayList<Integer> retrieveTrainersId(Connection connection, int courseID) {
        ArrayList<Integer> ids = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("select trainer_id \n"
                    + "from trainers_in_course where course_id = ?");
            statement.setInt(1, courseID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getInt(1));
            }
            resultSet.close();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ids;
    }
    
    public static ArrayList<Integer> retrieveAssignmentsId(Connection connection) {
        ArrayList<Integer> ids = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select assignment_id \n"
                    + "from assignment;");
            while (resultSet.next()) {
                ids.add(resultSet.getInt(1));
            }
            resultSet.close();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ids;
    }

    public static ArrayList<Integer> retrieveAssignmentsId(Connection connection, int courseID) {
        ArrayList<Integer> ids = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("select assignment_id \n"
                    + "from assignment where course_id = ?");
            statement.setInt(1, courseID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getInt(1));
            }
            resultSet.close();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ids;
    }
    
    
    public static ArrayList<Integer> retrieveAssignmentsId(Connection connection, int courseID, int studentID) {
        ArrayList<Integer> ids = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("select a.assignment_id \n"
                    + "from assignment a join marks m on a.assignment_id=m.assignment_id where course_id = ? and student_id =?");
            
            statement.setInt(1, courseID);
            statement.setInt(2, studentID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getInt(1));
            }
            resultSet.close();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ids;
    }

    public static ArrayList<Assignment> retrieveAssignments(Connection connection){
        ArrayList<Assignment> assignments = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from assignment;");
            while(resultSet.next()){
                Assignment assignment = new Assignment(resultSet.getString(2), resultSet.getString(3), resultSet.getTimestamp(4).toLocalDateTime());
                assignments.add(assignment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assignments;
    }
    
    
    public static boolean syntheticDataExist(Connection connection) {

        Statement statement = null;
        ResultSet resultSet = null;
        boolean exists = false;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select status \n"
                    + "from synthetic_data ;");

            if (resultSet.last()) {
                if (resultSet.getInt(1) != 0) {
                    exists = true;
                }
            }
            resultSet.close();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exists;
    }

    public static void insertSynthDataStatus(Connection connection, boolean status) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("insert into synthetic_data values(null,?);");
            statement.setBoolean(1, status);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void deleteRecords(Connection connection) {
        CallableStatement callableStatement = null;

        try {
            callableStatement = connection.prepareCall("call delete_records();");
            callableStatement.execute();
            System.out.println("Records deleted...");
            callableStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
