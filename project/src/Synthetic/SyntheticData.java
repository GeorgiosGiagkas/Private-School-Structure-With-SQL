/*
 * Random Generated synthetic data for Student, Trainer and Assignments. Courses are not random
 * Populate tables of the database private_school calling methods from DatabaseHandler for insert.
 *
 */
package Synthetic;

import Database.DatabaseHandler;
import SchoolStructure.Assignment;
import SchoolStructure.Course;
import SchoolStructure.Student;
import SchoolStructure.Trainer;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author giagkas
 */
public class SyntheticData {
    private static SyntheticData syntheticData=null;
    private Connection connection;
    private SyntheticData(Connection connection) {
        this.connection=connection;
    }
    
    public static SyntheticData createSyntheticData(Connection connection){
        if(syntheticData==null){
            syntheticData=new SyntheticData(connection);
            return syntheticData;
        }
        return syntheticData;
    }
    
    public void populateTables(){
        addSyntheticCourse();
        addSyntheticStudent();
        addSynteticTrainer();
        addSyntheticAssignment();
        addSyntheticTrainerToCourses();
        addSyntheticStudentToCourses();
        
    }

    private   void addSyntheticCourse() {
        LocalDate startDate = LocalDate.of(2019, 5, 13);
        LocalDate endDatePart = LocalDate.of(2019, 11, 23);
        LocalDate endDateFull = LocalDate.of(2019, 9, 13);
        Course cb8JavaPart = new Course("CB8", "Java", "part-time", startDate, endDatePart);
        Course cb8JavaFull = new Course("CB8", "Java", "full-time", startDate, endDateFull);
        Course cb8CSharpPart = new Course("CB8", "C#", "part-time", startDate, endDatePart);
        Course cb8CSharpFull = new Course("CB8", "C#", "full-time", startDate, endDateFull);

        DatabaseHandler.insertCourse(connection, cb8JavaPart);
        DatabaseHandler.insertCourse(connection, cb8JavaFull);
        DatabaseHandler.insertCourse(connection, cb8CSharpPart);
        DatabaseHandler.insertCourse(connection, cb8CSharpFull);

    }

    private  void addSyntheticStudent() {
        for (int i = 0; i < 24; i++) {
            Student student = Student.randomStudentGenerator();
            DatabaseHandler.insertStudent(connection, student);
        }
    }

    private  void addSynteticTrainer() {
        for (int i = 0; i < 12; i++) {
            Trainer trainer = Trainer.randomTrainerGenerator();
            DatabaseHandler.insertTrainer(connection, trainer);
        }
    }

    private  void addSyntheticAssignment() {
        ArrayList<Integer> courses = DatabaseHandler.retrieveCoursesId(connection);

        for (int courseId : courses) {
            for (int i = 0; i < 6; i++) {
                Assignment assignment = Assignment.randomAssignmentGenerator();

                DatabaseHandler.insertAssignment(connection, assignment, courseId);
            }
        }
    }

    private   void addSyntheticTrainerToCourses() {
        ArrayList<Integer> trainers = DatabaseHandler.retrieveTrainersId(connection);
        ArrayList<Integer> courses = DatabaseHandler.retrieveCoursesId(connection);

        int courseID;
        courseID = courses.get(0);
        for (int i = 0; i < 3; i++) {
            DatabaseHandler.insertTrainerToCourse(connection, trainers.get(i), courseID);
        }

        courseID = courses.get(1);
        for (int i = 3; i < 6; i++) {
            DatabaseHandler.insertTrainerToCourse(connection, trainers.get(i), courseID);
        }

        courseID = courses.get(2);
        for (int i = 6; i < 9; i++) {
            DatabaseHandler.insertTrainerToCourse(connection, trainers.get(i), courseID);
        }

        courseID = courses.get(3);
        for (int i = 9; i < 12; i++) {
            DatabaseHandler.insertTrainerToCourse(connection, trainers.get(i), courseID);
        }

    }

    private  void addSyntheticStudentToCourses() {
        ArrayList<Integer> students = DatabaseHandler.retrieveStudentsId(connection);
        ArrayList<Integer> courses = DatabaseHandler.retrieveCoursesId(connection);

        int courseID;
        courseID = courses.get(0);
        for (int i = 0; i < 6; i++) {
            DatabaseHandler.insertStudentToCourse(connection, students.get(i), courseID);
            ArrayList<Integer> assignments = DatabaseHandler.retrieveAssignmentsId(connection, courseID);
            for (int assignmentID : assignments) {
                DatabaseHandler.insertAssignmentToStudent(connection, students.get(i), assignmentID);
            }
        }

        courseID = courses.get(1);
        for (int i = 6; i < 12; i++) {
            DatabaseHandler.insertStudentToCourse(connection, students.get(i), courseID);
            ArrayList<Integer> assignments = DatabaseHandler.retrieveAssignmentsId(connection, courseID);
            for (int assignmentID : assignments) {
                DatabaseHandler.insertAssignmentToStudent(connection, students.get(i), assignmentID);
            }
        }

        courseID = courses.get(2);
        for (int i = 12; i < 18; i++) {
            DatabaseHandler.insertStudentToCourse(connection, students.get(i), courseID);
            ArrayList<Integer> assignments = DatabaseHandler.retrieveAssignmentsId(connection, courseID);
            for (int assignmentID : assignments) {
                DatabaseHandler.insertAssignmentToStudent(connection, students.get(i), assignmentID);
            }
        }

        courseID = courses.get(3);
        for (int i = 18; i < 24; i++) {
            DatabaseHandler.insertStudentToCourse(connection, students.get(i), courseID);
            ArrayList<Integer> assignments = DatabaseHandler.retrieveAssignmentsId(connection, courseID);
            for (int assignmentID : assignments) {
                DatabaseHandler.insertAssignmentToStudent(connection, students.get(i), assignmentID);
            }
        }

    }

}
