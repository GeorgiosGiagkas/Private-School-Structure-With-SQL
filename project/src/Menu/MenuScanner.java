/*
 * The Class MenuScanner is used to aquire and validate input from user calling appropriate methods from Checker class
 * and DatabaseHandler.
 * To navigate through different menus: Main Menu,Course Menu, Student Menu, Assignment Menu,Trainer Menu
 * 
 */
package Menu;

import Database.DatabaseHandler;
import Check.Checker;
import SchoolStructure.Assignment;
import SchoolStructure.Course;
import SchoolStructure.Student;
import SchoolStructure.Trainer;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author giagkas
 */
public class MenuScanner {

    static Scanner scanner = new Scanner(System.in);

    public static void mainMenu(Connection connection) {

        boolean quit = false;

        while (!quit) {
            MenuPrinter.printMainMenu();

            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid action...\n");
                continue;
            }

            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    System.out.println("Preparing log out ...\n");
                    quit = true;
                    break;
                case 1:
                    courseMenu(connection);
                    break;
                case 2:
                    studentMenu(connection);
                    break;
                case 3:
                    assignmentMenu(connection);
                    break;
                case 4:
                    trainerMenu(connection);
                    break;              
                default:
                    System.out.println("Invalid action.");
                    break;
            }

        }
    }

    public static Student inputNewStudentData() {
        String firstName = "No input";
        String lastName = "No input";
        LocalDate dateOfBirth = LocalDate.now();
        double tuitionFees = 0.0;

        String input = "";

        boolean quit = false;
        while (!quit) {
            System.out.println("Enter student's info all at once or one at a time?:\n"
                    + "0:\tAll at once.\n"
                    + "1:\tOne at a time\n");

            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid action...\n");
                continue;
            }

            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    System.out.println("Enter in order: first name, last name, date of birth(yyyy/mm/dd), tuition fees. Each field separated by comma:");
                    String userInput = scanner.nextLine();
                    String[] inputArray = userInput.split(",");
                    if (inputArray.length != 4) {
                        System.out.println("You do not enter a valid quantity of data!");
                        break;
                    }

                    if (Checker.isStringContainsLettersOnly(inputArray[0])) {
                        firstName = inputArray[0];
                    } else {
                        System.out.println("Invalid name input!");
                        break;
                    }

                    if (Checker.isStringContainsLettersOnly(inputArray[1])) {
                        lastName = inputArray[1];
                    } else {
                        System.out.println("Invalid name input");
                        break;
                    }

                    if (inputArray[2] != null && Checker.dateCheck(inputArray[2])) {
                        dateOfBirth = inputDate(inputArray[2]);
                    } else {
                        System.out.println("Invalid date!");
                        break;
                    }

                    if (inputArray[3].matches("^\\d+(\\.\\d+)?$")) {//regular expression to check double
                        tuitionFees = Double.parseDouble(inputArray[3]);
                    } else {
                        System.out.println("Invalid tuition fee input!");
                        break;
                    }
                    quit = true;
                    break;

                case 1:
                    System.out.println("Enter student's first name:");
                    input = scanner.nextLine();
                    if (Checker.isStringContainsLettersOnly(input)) {
                        firstName = input;
                    } else {
                        System.out.println("Invalid name input!");
                        break;
                    }

                    System.out.println("Enter student's last name:");
                    input = scanner.nextLine();
                    if (Checker.isStringContainsLettersOnly(input)) {
                        lastName = input;
                    } else {
                        System.out.println("Invalid name input!");
                        break;
                    }

                    System.out.println("Enter student's date of Birth (yyyy/mm/dd):");
                    String checkDate = scanner.nextLine();
                    if (Checker.dateCheck(checkDate)) {
                        dateOfBirth = inputDate(checkDate);
                    } else {
                        System.out.println("Invalid date.");
                        break;
                    }

                    System.out.println("Enter tuition fees:");
                    boolean hasNextDouble = scanner.hasNextDouble();
                    if (!hasNextDouble) {
                        System.out.println("Invalid input.");
                        break;
                    }
                    tuitionFees = scanner.nextDouble();
                    scanner.nextLine();
                    if (tuitionFees < 0) {
                        System.out.println("Negative value is invalid input!");
                        break;
                    }

                    quit = true;
                    break;
                default:
                    System.out.println("Invalid action...\n");
                    break;
            }

        }

        Student student = new Student(dateOfBirth, tuitionFees, firstName, lastName);
        return student;
    }

    public static Trainer inputNewTrainerData() {
        String firstName = "No input";
        String lastName = "No input";
        String subject = "No input";
        String input = "";

        boolean quit = false;
        while (!quit) {
            System.out.println("Enter trainer's info all at once or one at a time?:\n"
                    + "0:\tAll at once.\n"
                    + "1:\tOne at a time\n");

            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid action...\n");
                continue;
            }

            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    System.out.println("Enter in order: first name, last name, subject. Each field separated by comma:");
                    String userInput = scanner.nextLine();
                    String[] inputArray = userInput.split(",");
                    if (inputArray.length != 3) {
                        System.out.println("You do not enter a valid quantity of data!");
                        break;
                    }

                    if (Checker.isStringContainsLettersOnly(inputArray[0])) {
                        firstName = inputArray[0];
                    } else {
                        System.out.println("Invalid name input!");
                        break;
                    }

                    if (Checker.isStringContainsLettersOnly(inputArray[1])) {
                        lastName = inputArray[1];
                    } else {
                        System.out.println("Invalid name input!");
                        break;
                    }

                    if (inputArray[2] != null && Checker.checkForText(inputArray[2])) {
                        subject = inputArray[2];
                    } else {
                        System.out.println("Invalid subject input!");
                        break;
                    }

                    quit = true;
                    break;
                case 1:
                    System.out.println("Enter trainer's first name:");
                    input = scanner.nextLine();
                    if (Checker.isStringContainsLettersOnly(input)) {
                        firstName = input;
                    } else {
                        System.out.println("Invalid name input!");
                        break;
                    }

                    System.out.println("Enter trainer's last name:");
                    input = scanner.nextLine();
                    if (Checker.isStringContainsLettersOnly(input)) {
                        lastName = input;
                    } else {
                        System.out.println("Invalid name input");
                        break;
                    }

                    System.out.println("Enter subject:");
                    subject = scanner.nextLine();
                    if (!Checker.checkForText(subject)) {
                        System.out.println("Invalid subject input!");
                        break;
                    }

                    quit = true;
                    break;
                default:
                    System.out.println("Invalid action...\n");
                    break;
            }

        }

        Trainer trainer = new Trainer(subject, firstName, lastName);
        return trainer;
    }

    public static Course inputNewCourse() {
        String title = "No input";
        String stream = "No input";
        String type = "No input";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        String input = "";

        boolean quit = false;
        while (!quit) {
            System.out.println("Enter course info all at once or one at a time?:\n"
                    + "0:\tAll at once.\n"
                    + "1:\tOne at a time\n");

            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid action...\n");
                continue;
            }

            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    System.out.println("Enter in order: title, stream, type, start-date(yyyy/mm/dd), end-date(yyyy/mm/dd). Each field separated by comma:");
                    String userInput = scanner.nextLine();
                    String[] inputArray = userInput.split(",");
                    if (inputArray.length != 5) {
                        System.out.println("You do not enter a valid quantity of data!");
                        break;
                    }

                    if (inputArray[0] != null && Checker.checkForText(inputArray[0])) {
                        title = inputArray[0];
                    } else {
                        System.out.println("Invalid title input!");
                        break;
                    }
                    if (inputArray[1] != null && Checker.checkForText(inputArray[1])) {
                        stream = inputArray[1];
                    } else {
                        System.out.println("Invalid stream input!");
                        break;
                    }
                    if (inputArray[2] != null && Checker.checkForText(inputArray[2])) {
                        type = inputArray[2];
                    } else {
                        System.out.println("Ivalid type input!");
                        break;
                    }

                    if (inputArray[3] != null && Checker.dateCheck(inputArray[3])) {
                        startDate = inputDate(inputArray[3]);
                    } else {
                        System.out.println("Invalid date!");
                        break;
                    }
                    if (inputArray[4] != null && Checker.dateCheck(inputArray[4])) {
                        endDate = inputDate(inputArray[4]);
                    } else {
                        System.out.println("Invalid date!");
                        break;
                    }
                    quit = true;
                    break;
                case 1:
                    System.out.println("Enter course title:");
                    title = scanner.nextLine();
                    if (!Checker.checkForText(title)) {
                        System.out.println("Invalid title input!");
                        break;
                    }
                    System.out.println("Enter course stream:");
                    stream = scanner.nextLine();
                    if (!Checker.checkForText(stream)) {
                        System.out.println("Invalid stream input!");
                        break;
                    }
                    System.out.println("Enter course type:");
                    type = scanner.nextLine();
                    if (!Checker.checkForText(type)) {
                        System.out.println("Invalid type input!");
                        break;
                    }

                    System.out.println("Enter course start date(yyyy/mm/dd):");
                    input = scanner.nextLine();
                    if (Checker.dateCheck(input)) {
                        startDate = inputDate(input);
                    } else {
                        System.out.println("Invalid date!");
                        break;
                    }

                    System.out.println("Enter course end date(yyyy/mm/dd):");
                    input = scanner.nextLine();

                    if (Checker.dateCheck(input)) {
                        endDate = inputDate(input);
                    } else {
                        System.out.println("Invalid date!");
                        break;
                    }
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid action...\n");
                    break;
            }

        }

        Course course = new Course(title, stream, type, startDate, endDate);
        return course;
    }

    public static Assignment inputNewAssignment() {
        String title = "No input";
        String description = "No input";
        LocalDateTime subDateTime = LocalDateTime.now();
        int oralMark = 0;
        int totalMark = 0;
        String input = "";
        boolean quit = false;
        while (!quit) {
            System.out.println("Enter course info all at once or one at a time?:\n"
                    + "0:\tAll at once.\n"
                    + "1:\tOne at a time\n");
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid action...\n");
                continue;
            }

            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    System.out.println("Enter in order: title, description, submission-date(yyyy/MM/dd/hh/mm/ss). Each field separated by comma:");
                    String userInput = scanner.nextLine();
                    String[] inputArray = userInput.split(",");
                    if (inputArray.length != 3) {
                        System.out.println("You do not enter a valid quantity of data!");
                        break;
                    }

                    if (inputArray[0] != null && Checker.checkForText(inputArray[0])) {
                        title = inputArray[0];
                    } else {
                        System.out.println("Invalid title input!");
                        break;
                    }

                    if (inputArray[1] != null && Checker.checkForText(inputArray[1])) {
                        description = inputArray[1];
                    } else {
                        System.out.println("Invalid description input!");
                        break;
                    }

                    if (inputArray[2] != null && Checker.dateTimeCheck(inputArray[2])) {
                        subDateTime = inputDateTime(inputArray[2]);
                    } else {
                        System.out.println("Invalid date!");
                        break;
                    }

                    quit = true;
                    break;
                case 1:
                    System.out.println("Enter assignment title:");
                    title = scanner.nextLine();
                    if (!Checker.checkForText(title)) {
                        System.out.println("Invalid title input!");
                        break;
                    }
                    System.out.println("Enter assignment description:");
                    description = scanner.nextLine();
                    if (!Checker.checkForText(description)) {
                        System.out.println("Invalid description input!");
                        break;
                    }
                    System.out.println("Enter submission date and time(yyyy/MM/dd/HH/mm/ss):");
                    input = scanner.nextLine();
                    if (Checker.dateTimeCheck(input)) {
                        subDateTime = inputDateTime(input);
                    } else {
                        System.out.println("Invalid date!");
                        break;
                    }

                    quit = true;
                    break;
                default:
                    System.out.println("Invalid action...\n");
                    break;
            }

        }

        Assignment assignment = new Assignment(title, description, subDateTime, oralMark, totalMark);
        return assignment;
    }

    public static LocalDate inputDate(String userInput) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(userInput, dateFormat);
        return date;
    }

    public static LocalDateTime inputDateTime(String userInput) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd/HH/mm/ss");
        LocalDateTime date = LocalDateTime.parse(userInput, dateFormat);
        return date;
    }

    public static int selectCourse(Connection connection, int studentId) {

        if (studentId < 0) return -1;
        
        while (true) {
            System.out.println("Select course id:");
            System.out.println("ID\tTitle\tStream\tType\tStartDate\tEndDate");
            System.out.println("-------------------------------------------------------------");

            if (studentId == 0) {
                DatabaseHandler.showCourses(connection);
            } else if (studentId > 0) {
                DatabaseHandler.showCoursesForStudent(connection, studentId);
            }

            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid input!");
                continue;
            }

            int userInput = scanner.nextInt();
            scanner.nextLine();
            if (studentId == 0) {
                if (Checker.checkExistsCourseId(connection, userInput)) {
                    return userInput;
                } else {
                    System.out.println("Invalid input");
                }
            } else if (studentId > 0) {
                if (Checker.checkExistsStudentId(connection, studentId,userInput)) {
                    return userInput;
                } else {
                    System.out.println("Invalid input");
                }
            }
        }

    }

    public static int selectStudent(Connection connection, String table) {
        
        if(DatabaseHandler.studentTableIsEmpty(connection)) return -1;
        
        while (true) {
            System.out.println("Select student id:");
            System.out.println("ID\tFirstName\tLastName\tDateOfBirth\tTuitionFees");
            System.out.println("-------------------------------------------------------------");
            if (table.equalsIgnoreCase("student")) {
                DatabaseHandler.showStudents(connection);
            } else if (table.equalsIgnoreCase("marks")) {
                DatabaseHandler.showStudentsWithAssignedProject(connection);
            }

            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid input!");
                continue;
            }
            
            int userInput = scanner.nextInt();
            scanner.nextLine();
            if (table.equalsIgnoreCase("student")) {
                if (Checker.checkExistsStudentId(connection, userInput)) {
                    return userInput;
                } else {
                    System.out.println("Invalid input!");
                }
            } else if (table.equalsIgnoreCase("marks")) {
                if (Checker.checkExistsStudentIdInMarks(connection, userInput)) {
                    return userInput;
                } else {
                    System.out.println("Invalid input!");
                }
            }
        }

    }

    public static int selectTrainer(Connection connection) {
        
        if(DatabaseHandler.trainerTableIsEmpty(connection)) return -1;

        while (true) {
            System.out.println("Select trainer id:");
            System.out.println("ID\tFirstName\tLastName\tSubject");
            System.out.println("-------------------------------------------------------------");
            DatabaseHandler.showTrainers(connection);
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid input!");
                continue;
            }

            int userInput = scanner.nextInt();
            scanner.nextLine();
            if (Checker.checkExistsTrainerId(connection, userInput)) {
                return userInput;
            } else {
                System.out.println("Invalid input!");
            }
        }

    }

    public static int selectAssignment(Connection connection, int courseId) {

        if (courseId < 0) return -1;
        
        while (true) {
            System.out.println("Select assignment id:");
            System.out.println("ID\tTitle\tDiscription\tSumbissionDate");
            System.out.println("-------------------------------------------------------------");
            if (courseId == 0) {
                DatabaseHandler.showAssignments(connection);
            } else if (courseId > 0) {
                DatabaseHandler.showAssignmentsInCourse(connection, courseId);
            }
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid input!");
                continue;
            }

            int userInput = scanner.nextInt();
            scanner.nextLine();
            if (courseId == 0) {
                if (Checker.checkExistsAssignmentId(connection, userInput)) {
                    return userInput;
                } else {
                    System.out.println("Invalid input!");
                }
            } else if (courseId > 0) {
                if (Checker.checkExistsAssignmentIdInCourse(connection, userInput, courseId)) {
                    return userInput;
                } else {
                    System.out.println("Invalid input!");
                }
            }

        }

    }

    public static int selectAssignment(Connection connection, int courseId, int studentId) {

        if (courseId <= 0 || studentId <= 0) return -1;
        
        while (true) {
            System.out.println("Select assignment id:");
            System.out.println("ID\tTitle\tDiscription\tSumbissionDate\tOralMark\tTotalMark");
            System.out.println("-------------------------------------------------------------");

            DatabaseHandler.showAssignmentsPerStudent(connection, studentId, courseId);

            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid input!");
                continue;
            }

            int userInput = scanner.nextInt();
            scanner.nextLine();

            if (Checker.checkStudentHasAssignment(connection, userInput, studentId, courseId)) {
                return userInput;
            } else {
                System.out.println("Invalid input!");
            }

        }

    }

    public static void courseMenu(Connection connection) {
        boolean quit = false;
        int courseID;
        while (!quit) {
            MenuPrinter.printCourseMenu();
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid input!");
                continue;
            }

            int userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput) {
                case 0:
                    System.out.println("Returning to main.");
                    quit = true;
                    break;
                case 1:
                    if (DatabaseHandler.courseTableIsEmpty(connection)) {
                        break;
                    }
                    System.out.println("ID\tTitle\tStream\tType\tStartDate\tEndDate");
                    System.out.println("-------------------------------------------------------------");
                    DatabaseHandler.showCourses(connection);
                    break;
                case 2:
                    if (DatabaseHandler.courseTableIsEmpty(connection)) {
                        break;
                    }

                    courseID = selectCourse(connection, 0);

                    System.out.println("Students for this course:");
                    System.out.println("ID\tFirstName\tLastName\tDateOfBirth\tTuitionFees");
                    System.out.println("-------------------------------------------------------------");
                    DatabaseHandler.showStudentsInCourse(connection, courseID);
                    break;
                case 3:
                    if (DatabaseHandler.courseTableIsEmpty(connection)) {
                        break;
                    }

                    courseID = selectCourse(connection, 0);
                    System.out.println("Assignments for this course:");
                    System.out.println("ID\tTitle\tDiscription\tSumbissionDate");
                    System.out.println("-------------------------------------------------------------");
                    DatabaseHandler.showAssignmentsInCourse(connection, courseID);
                    break;
                case 4:
                    if (DatabaseHandler.courseTableIsEmpty(connection)) {
                        break;
                    }

                    courseID = selectCourse(connection, 0);

                    System.out.println("Trainers for this course:");
                    System.out.println("ID\tFirstName\tLastName\tSubject");
                    System.out.println("-------------------------------------------------------------");
                    DatabaseHandler.showTrainersInCourse(connection, courseID);

                    break;

                case 5:
                    Course courseToAdd = inputNewCourse();
                    if(Checker.checkDuplicateCourse(connection, courseToAdd)){
                        System.out.println("Course with identical title, stream, type, start-date and end-date already exists.");
                        System.out.println("Add anyway?");
                        if(!proceed())  break;
                    }
                    DatabaseHandler.insertCourse(connection, courseToAdd);
                    System.out.println("New course added!");
                    break;
                default:
                    System.out.println("Invalid action");
                    break;

            }

        }

    }

    public static void studentMenu(Connection connection) {

        if (DatabaseHandler.courseTableIsEmpty(connection)) {
            System.out.println("Please first add an initial course!\nGo to Courses-> Option[5] to add course!");
            return;
        }

        boolean quit = false;

        while (!quit) {
            MenuPrinter.printStudentMenu();
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid input!");
                continue;
            }

            int userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput) {
                case 0:
                    System.out.println("Returning to main.");
                    quit = true;
                    break;
                case 1:
                    System.out.println("ID\tFirstName\tLastName\tDateOfBirth\tTuitionFees");
                    System.out.println("-------------------------------------------------------------");
                    DatabaseHandler.showStudents(connection);
                    break;
                case 2:
                    System.out.println("ID\tFirstName\tLastName\tDateOfBirth\tTuitionFees");
                    System.out.println("-------------------------------------------------------------");

                    DatabaseHandler.showStudentsInMoreThanOneCourse(connection);
                    break;

                case 3:
                    Student student = inputNewStudentData();
                    if(Checker.checkDuplicateStudent(connection, student)){
                        System.out.println("Student with identical first name, last name and date of birth already exists!");
                        System.out.println("Add anyway?");
                        if(!proceed())  break;
                    }
                    DatabaseHandler.insertStudent(connection, student);
                    break;

                case 4:
                    int studentID = selectStudent(connection, "student");
                    if(studentID<0) break;
                    
                    System.out.println("Please add the student to a course.");
                    int courseID = selectCourse(connection, 0);
                    if(courseID<0) break;
                    
                    if(Checker.checkExistsStudentId(connection,studentID ,courseID)){
                        System.out.println("Student already exists in this course!");
                        break;
                    }
                                        
                    DatabaseHandler.insertStudentToCourse(connection, studentID, courseID);
                    
                    ArrayList<Integer> assignmentIds = DatabaseHandler.retrieveAssignmentsId(connection, courseID);
                    for(int id : assignmentIds){
                        DatabaseHandler.insertAssignmentToStudent(connection, studentID, id);
                    }
                    
                    break;

                default:
                    System.out.println("Invalid action");
                    break;

            }

        }

    }

    public static void trainerMenu(Connection connection) {

        if (DatabaseHandler.courseTableIsEmpty(connection)) {
            System.out.println("Please first add an initial course!\nGo to Courses-> Option[5] to add course!");
            return;
        }

        boolean quit = false;

        while (!quit) {
            MenuPrinter.printTrainerMenu();
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid input!");
                continue;
            }

            int userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput) {
                case 0:
                    System.out.println("Returning to main.");
                    quit = true;
                    break;
                case 1:
                    System.out.println("ID\tFirstName\tLastName\tSubject");
                    System.out.println("-------------------------------------------------------------");
                    DatabaseHandler.showTrainers(connection);
                    break;

                case 2:
                    Trainer trainer = inputNewTrainerData();
                    if(Checker.checkDuplicateTrainer(connection, trainer)){
                        System.out.println("Trainer with identical first name, last name and subject already exists!");
                        System.out.println("Add anyway?");
                        if(!proceed())  break;                       
                    }
                    DatabaseHandler.insertTrainer(connection, trainer);
                    break;

                case 3:
                    int trainerToAdd = selectTrainer(connection);
                    if(trainerToAdd<0) break;                  
                    System.out.println("Please add the trainer to a course.");
                    int course = selectCourse(connection, 0);
                    
                    if(Checker.checkExistsTrainerId(connection,trainerToAdd ,course)){
                        System.out.println("Trainer already exists in this course!");
                        break;
                    }
                    
                    DatabaseHandler.insertTrainerToCourse(connection, trainerToAdd, course);

                    break;

                default:
                    System.out.println("Invalid action");
                    break;

            }

        }

    }

    public static void assignmentMenu(Connection connection) {

        if (DatabaseHandler.courseTableIsEmpty(connection)) {
            System.out.println("Please first add an initial course!\nGo to Courses-> Option[5] to add course!");
            return;
        }

        boolean quit = false;

        while (!quit) {
            MenuPrinter.printAssignmentMenu();
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                scanner.nextLine();
                System.out.println("Invalid input!");
                continue;
            }

            int userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput) {
                case 0:
                    System.out.println("Returning to main.");
                    quit = true;
                    break;
                case 1:
                    System.out.println("ID\tTitle\tDiscription\tSumbissionDate");
                    System.out.println("-------------------------------------------------------------");
                    DatabaseHandler.showAssignments(connection);
                    break;

                case 2:
                    if(DatabaseHandler.marksTableIsEmpty(connection)){
                        System.out.println("Students have no assignments!");
                        break;
                    }
                    int studentId = selectStudent(connection, "marks");
                    int courseId = selectCourse(connection, studentId);
                    if(courseId<1){
                        System.out.println("Invalid course id.");
                        break;
                    }
                    System.out.println("ID\tTitle\tDescription\tSubmission-Date\tOralMark\tTotalMark");
                    System.out.println("-------------------------------------------------------------");
                    DatabaseHandler.showAssignmentsPerStudent(connection, studentId, courseId);
                    break;

                case 3:
                    Assignment assignment = inputNewAssignment();
                    if(Checker.checkDuplicateAssignment(connection, assignment)){
                        System.out.println("Assignment with identical title, description and submission date-time already exists!");
                        System.out.println("Add anyway?");
                        if(!proceed()) break;
                    }
                    
                    
                    System.out.println("Please add the new assignment to a course.");
                    int course = selectCourse(connection, 0);
                    DatabaseHandler.insertAssignment(connection, assignment, course);
                    int assignmentID = DatabaseHandler.retrieveLastAssignmentID(connection);
                    
                    ArrayList<Integer> studentIds = DatabaseHandler.retrieveStudentsId(connection, course);
                    
                    for(int id : studentIds){
                        DatabaseHandler.insertAssignmentToStudent(connection, id, assignmentID);
                    }

                    break;

                

                case 4:
                    if(DatabaseHandler.marksTableIsEmpty(connection)){
                        System.out.println("Students have no assignments!");
                        break;
                    }
                    int s = selectStudent(connection, "marks");
                    if(s<0) break;
                    int c = selectCourse(connection, s);
                    if(c<0) break;
                    
                    if(!DatabaseHandler.studentHasMarkInCourse(connection, s, c)){
                        System.out.println("No assignments!");
                        break;
                    }
                    
                    int a = selectAssignment(connection, c, s);
                    if(a<0) break;
                    
                    
                    int oralMark = enterOralMark();
                    int totalMark = enterTotalMark();
                    DatabaseHandler.modifyMarks(connection, s, a, oralMark, totalMark);
                    break;

                default:
                    System.out.println("Invalid action");
                    break;

            }

        }

    }

    private static int enterOralMark() {
        boolean quit = false;
        int mark = 0;
        while (!quit) {
            System.out.println("Enter oral mark. Integer number from 1 to 10:");
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                System.out.println("Invalid value");
                scanner.nextLine();
                continue;
            }

            mark = scanner.nextInt();
            scanner.nextLine();
            if (mark < 1 || mark > 10) {
                System.out.println("Mark out of range!");
                continue;
            }
            quit = true;

        }
        return mark;
    }

    private static int enterTotalMark() {
        boolean quit = false;
        int mark = 0;
        while (!quit) {
            System.out.println("Enter total mark. Integer number from 1 to 10:");
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                System.out.println("Invalid value");
                scanner.nextLine();
                continue;
            }

            mark = scanner.nextInt();
            scanner.nextLine();

            if (mark < 1 || mark > 10) {
                System.out.println("Mark out of range!");
                continue;
            }
            quit = true;

        }
        return mark;
    }

    
    public static boolean proceed(){
        String input;
        while(true){
            System.out.println("Enter [y] for yes, [n] for no:");
            input=scanner.nextLine();
            if(input.equalsIgnoreCase("y")){
                return true;
            }
            else if(input.equalsIgnoreCase("n")){
                return false;
            }
            else System.out.println("Invalid Input!");
        }      
    }
    
    
    
    public static void quiting(Connection connection) {
        String answer;
        boolean quit = false;

        while (!quit) {
            
            System.out.println("Important!!!!!\nTo show initial menu and  have the option to input synthetic data after restarting,you have to delete records!");
            System.out.println("Delete records before quit (y/n) ?");
            answer = scanner.nextLine().toLowerCase();
            switch (answer) {

                case "n":
                    System.out.println("Exit without deleting..."); 
                    DatabaseHandler.insertSynthDataStatus(connection, true);
                    quit = true;
                    break;
                case "y":
                    DatabaseHandler.deleteRecords(connection);
                    DatabaseHandler.insertSynthDataStatus(connection, false);
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid Action!");
                    break;

            }
        }
    }

}
