/*
 * 
 * Prints Menu navigation options
 * 
 */
package Menu;

/**
 *
 * @author giagkas
 */
public class MenuPrinter {
        private MenuPrinter() {
    }

    public static void printIntro() {
        System.out.println("Select desired action:\n"
                + "0:\tTo quit the program.\n"
                + "1:\tTo input data from scratch.\n"
                + "2:\tTo use synthetic data.\n");
    }

    public static void printMainMenu() {
        System.out.println("_____________________________________________");
        System.out.println("Main Menu: Select desired category:\n"
                + "0:\tTo quit the program.\n"
                + "1:\tCourses\n"
                + "2:\tStudents\n"
                + "3:\tAssignments\n"
                + "4:\tTrainers\n");
    }

    public static void printCourseMenu() {
        System.out.println("_____________________________________________");
        System.out.println("Course Menu: Select desired action:\n"
                + "0:\tTo return to main menu.\n"
                + "1:\tTo print a list of all available courses.\n"
                + "2:\tTo print a list of all students per course.\n"
                + "3:\tTo print a list of all assignments per course.\n"
                + "4:\tTo print a list of all trainers per course.\n"
                + "5:\tTo add a new course.\n");
    }

    public static void printStudentMenu() {
        System.out.println("_____________________________________________");
        System.out.println("Student Menu: Select desired action:\n"
                + "0:\tTo return to main menu.\n"
                + "1:\tTo print a list of all students.\n"
                + "2:\tTo print a list of students that belong to more than one courses.\n"
                + "3:\tTo add new student.\n"
                + "4:\tTo add existing student to a course.\n");
    }

    public static void printAssignmentMenu() {
        System.out.println("_____________________________________________");
        System.out.println("Assignment Menu: Select desired action:\n"
                + "0:\tTo return to main menu.\n"
                + "1:\tTo print a list of all assignments.\n"
                + "2:\tTo print all assignments per student.\n"
                + "3:\tTo add new assignment.\n"                
                + "4:\tTo modify/add marks to an assignment.\n");
    }

    public static void printTrainerMenu() {
        System.out.println("_____________________________________________");
        System.out.println("Trainer Menu: Select desired action:\n"
                + "0:\tTo return to main menu.\n"
                + "1:\tTo print a list of all trainers.\n"
                + "2:\tTo add a trainer.\n"
                + "3:\tTo add existing trainer to a course.\n");
    }

}
