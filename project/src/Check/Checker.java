/*
 * Methods to check user input such as date, and validates if objects exists in a list (see override equals in
 * the four main classes:Assignment,Course,Student,Trainer). Checks also if IDs exist.
 *
 */
package Check;

import SchoolStructure.Assignment;
import SchoolStructure.Course;
import Database.DatabaseHandler;
import SchoolStructure.Student;
import SchoolStructure.Trainer;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author giagkas
 */
public class Checker {
      private Checker() {
    }

    

    public static boolean checkForText(String userInput) {
        if (userInput.matches("^([a-zA-Z0-9]+)(.*)")) {
            return true;
        }
        return false;
    }

    private static boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean daysInAMonth(int year, int month, int day) {
        if (month == 1 && (day >= 1 && day <= 31)) {
            return true;
        }
        if (month == 2 && (day >= 1 && day <= 29) && isLeapYear(year)) {
            return true;
        }
        if (month == 2 && (day >= 1 && day <= 28) && (!isLeapYear(year))) {
            return true;
        }
        if (month == 3 && (day >= 1 && day <= 31)) {
            return true;
        }
        if (month == 4 && (day >= 1 && day <= 30)) {
            return true;
        }
        if (month == 5 && (day >= 1 && day <= 31)) {
            return true;
        }
        if (month == 6 && (day >= 1 && day <= 30)) {
            return true;
        }
        if (month == 7 && (day >= 1 && day <= 31)) {
            return true;
        }
        if (month == 8 && (day >= 1 && day <= 31)) {
            return true;
        }
        if (month == 9 && (day >= 1 && day <= 30)) {
            return true;
        }
        if (month == 10 && (day >= 1 && day <= 31)) {
            return true;
        }
        if (month == 11 && (day >= 1 && day <= 30)) {
            return true;
        }
        if (month == 12 && (day >= 1 && day <= 31)) {
            return true;
        }
        return false;
    }

    public static boolean dateCheck(String userInput) {

        String regx = "^\\d{4}/\\d{2}/\\d{2}$";

        if (!userInput.matches(regx)) {
            return false;
        }

        String[] stringSplit = userInput.split("/");

        if (stringSplit.length != 3) {
            return false;
        }

        int year = Integer.parseInt(stringSplit[0]);
        int month = Integer.parseInt(stringSplit[1]);
        int day = Integer.parseInt(stringSplit[2]);

        if (year < 1900 || year > 2200) {
            return false;
        }
        return daysInAMonth(year, month, day);

    }

    public static boolean dateTimeCheck(String userInput) {

        String regx = "^\\d{4}/\\d{2}/\\d{2}/\\d{2}/\\d{2}/\\d{2}$";

        if (!userInput.matches(regx)) {
            return false;
        }

        String[] stringSplit = userInput.split("/");

        if (stringSplit.length != 6) {
            return false;
        }

        int year = Integer.parseInt(stringSplit[0]);
        int month = Integer.parseInt(stringSplit[1]);
        int day = Integer.parseInt(stringSplit[2]);
        int hours = Integer.parseInt(stringSplit[3]);
        int min = Integer.parseInt(stringSplit[4]);
        int sec = Integer.parseInt(stringSplit[5]);

        if (year < 1900 || year > 2200) {
            return false;
        }

        if (!daysInAMonth(year, month, day)) {
            return false;
        }

        if (hours < 0 || hours > 23) {
            return false;
        }
        if (min < 0 || min > 60) {
            return false;
        }
        if (sec < 0 || sec > 60) {
            return false;
        }

        return true;

    }

    public static boolean isStringContainsLettersOnly(String string) {
        return ((string != null)
                && (!string.equals(""))
                && (string.matches("^[a-zA-Z]*$")));
    }

    public static boolean isStringContainsDigitOnly(String string) {
        String regex = "[0-9]+";
        return ((string != null)
                && (!string.equals(""))
                && (string.matches(regex)));
    }

    

    
    
    public static boolean checkDuplicateStudent(Connection connection, Student student){
        ArrayList<Student> students = DatabaseHandler.retrieveStudents(connection);
        for(Student st : students){
            if(st.equals(student))  return true;
        }
        return false;
    }
    public static boolean checkExistsStudentId(Connection connection ,int studentID){
        ArrayList<Integer> ids = DatabaseHandler.retrieveStudentsId(connection);
        for(int id : ids){
            if(id==studentID)   return true;
                     
        }
        return false;
    }
    public static boolean checkExistsStudentId(Connection connection ,int studentID, int courseId){
        ArrayList<Integer> ids = DatabaseHandler.retrieveStudentsId(connection, courseId);
        for(int id : ids){
            if(id==studentID)   return true;
                     
        }
        return false;
    }
    public static boolean checkExistsStudentIdInMarks(Connection connection ,int studentID){
        ArrayList<Integer> ids = DatabaseHandler.retrieveStudentsIdFromMarks(connection);
        for(int id : ids){
            if(id==studentID)   return true;
                     
        }
        return false;
    }
    
    public static boolean checkDuplicateCourse(Connection connection, Course course){
        ArrayList<Course> courses = DatabaseHandler.retrieveCourses(connection);
        for(Course cr : courses){
            if(cr.equals(course))  return true;
        }
        return false;
    }
    public static boolean checkExistsCourseId(Connection connection, int courseId){
        ArrayList<Integer> courses = DatabaseHandler.retrieveCoursesId(connection);
        for(int cr : courses){
            if(cr == courseId)  return true;
        }
        return false;
    }

    
    public static boolean checkDuplicateTrainer(Connection connection, Trainer trainer){
        ArrayList<Trainer> trainers = DatabaseHandler.retrieveTrainers(connection);
        for(Trainer tr : trainers){
            if(tr.equals(trainer))  return true;
        }
        return false;
    }
    public static boolean checkExistsTrainerId(Connection connection ,int trainerID){
        ArrayList<Integer> ids = DatabaseHandler.retrieveTrainersId(connection);
        for(int id : ids){
            if(id==trainerID)   return true;
                     
        }
        return false;
    }
    public static boolean checkExistsTrainerId(Connection connection ,int trainerID, int courseId){
        ArrayList<Integer> ids = DatabaseHandler.retrieveTrainersId(connection, courseId);
        for(int id : ids){
            if(id==trainerID)   return true;
                     
        }
        return false;
    }
    
    public static boolean checkDuplicateAssignment(Connection connection, Assignment assignment){
        ArrayList<Assignment> assignments = DatabaseHandler.retrieveAssignments(connection);
        for(Assignment a : assignments){
            if(a.equals(assignment))  return true;
        }
        return false;
    }
    public static boolean checkExistsAssignmentId(Connection connection, int assignmentId){
        ArrayList<Integer> assignments = DatabaseHandler.retrieveAssignmentsId(connection);
        for(int a : assignments){
            if(a==assignmentId)  return true;
        }
        return false;
    }
    
    public static boolean checkExistsAssignmentIdInCourse(Connection connection, int assignmentId,int courseId){
        ArrayList<Integer> assignments = DatabaseHandler.retrieveAssignmentsId(connection, courseId);
        for(int a : assignments){
            if(a==assignmentId)  return true;
        }
        return false;
    }
    
    public static boolean checkStudentHasAssignment(Connection connection, int assignmentId,int studentId){
        ArrayList<Integer> students = DatabaseHandler.retrieveStudentsIdFromMarks(connection, assignmentId);
        for(int s : students){
            if(s==studentId)  return true;
        }
        return false;
    }
    public static boolean checkStudentHasAssignment(Connection connection, int assignmentId,int studentId,int courseId){
        ArrayList<Integer> assignments = DatabaseHandler.retrieveAssignmentsId(connection, courseId, studentId);
        for(int a : assignments){
            if(a==assignmentId)  return true;
        }
        return false;
    }
 
}
