/*
 * Individual Project Assignment Part B. Coding Bootcamp 8 . Java Part Time. 
 * Author: Georgios Giagkas
 * Project title: Implementation of a private school structure.
 */
package individualprojectpartb;

import Database.DatabaseConnector;
import java.util.Scanner;


/**
 *
 * @author giagkas
 */
public class IndividualProjectPartB {

    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        String user;
        String password;

        
        System.out.println("Enter username to connect to private_school database: ");
        user = scanner.nextLine();
        System.out.println("Enter password: ");
        password = scanner.nextLine();

        DatabaseConnector db = DatabaseConnector.createDatabaseConnector(user, password);
        
        db.connect();
        

    }

}
