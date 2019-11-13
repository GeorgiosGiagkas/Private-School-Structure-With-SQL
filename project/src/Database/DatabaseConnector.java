/*
 * Creates an object that takes username and password to connect to private_school database.
 * 
 */
package Database;

import Menu.MenuPrinter;
import Menu.MenuScanner;
import Synthetic.SyntheticData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giagkas
 */
public class DatabaseConnector {
    private Connection connection = null;
    private final String user ;
    private final String password ;
    private static  DatabaseConnector databaseConnector=null;
    private static Scanner scanner = new Scanner(System.in);
    
    //private constructor
    private DatabaseConnector( String user, String password){   
    this.user=user;
    this.password=password;
    
    };
    
  
    public static DatabaseConnector  createDatabaseConnector(String user, String password){
        if(databaseConnector==null){
            databaseConnector= new DatabaseConnector(user,password);
            return databaseConnector;
        }
        return databaseConnector;
    }
    
    
    public void connect(){       
        try {           
            connection = DriverManager.getConnection("jdbc:mysql://localhost/private_school?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", user, password);
         
            System.out.println("Connected!");

            boolean quit = false;//quit---> exit while loop
            boolean proceed = false;//proceed to main menu. (either using synthetic data or not) 

            if (!DatabaseHandler.syntheticDataExist(connection)) {
                while (!quit) {
                    MenuPrinter.printIntro();

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
                            //quit
                            quit = true;
                            break;
                        case 1:
                            //no synthetic data
                            proceed = true;
                            quit = true;
                            break;
                        case 2:
                            //use synthetic data
                            SyntheticData syntheticData = SyntheticData.createSyntheticData(connection);
                            syntheticData.populateTables();
                            System.out.println("Synthetic data added!");
                            DatabaseHandler.insertSynthDataStatus(connection, true);
                            proceed = true;
                            quit = true;
                            break;
                        default:
                            System.out.println("Ivalid input!");
                            break;
                    }

                }
            }
            else{
                proceed =true;
            }

            if (proceed)    MenuScanner.mainMenu(connection);
            
            MenuScanner.quiting(connection); 
            connection.close();
            System.out.println("Disconnect!");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
