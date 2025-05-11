/* 
 * Name: Sarah Criswell
 * Date: 5/11/2025
 * Assignment: SDC330 Course Project - bored. 
 * 
 * Class Description: This is the main entry point of the application
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        final String dbName = "bored.db";
        //Print application header
        System.out.println("------------------------------");
        System.out.println("Bored.");
        System.out.println("------------------------------");
        System.out.println();
        Connection conn = SQLiteDatabase.connect(dbName);
        Scanner scanner = new Scanner(System.in);

        //Declare variables for app
        final String userName = "Sarah";

        //Create initial database if there is not one already
        if (conn != null) {
            createDatabase(conn);
        
            //Greet user by name
            System.out.println("Welcome, " + userName + "!");
            System.out.println();

            String mainResponse;
            do {
                System.out.println("\nYou must be bored, What do you want to do first?");
                System.out.println("1 = View Activities");
                System.out.println("2 = Create a new activity");
                System.out.println("3 = Update an activity");
                System.out.println("4 = Delete an activity");
                System.out.println("5 = Exit\n");
                System.out.println("Enter your choice: ");   
                mainResponse = scanner.nextLine().toLowerCase();

                switch (mainResponse) {
                            case "1":
                                String activityTypeResponse;
                                boolean viewingActivities = true;
                                while (viewingActivities) {
                                    //Start of program will provide a selection menu for which location they want suggestions for
                                    System.out.println("\nOkay! What type of activities would you like to see?");
                                    System.out.println("1 = indoors");
                                    System.out.println("2 = outdoors");
                                    System.out.println("3 = either");
                                    System.out.println("4 = Back to menu");
                                    activityTypeResponse = scanner.nextLine().toLowerCase();

                                    switch (activityTypeResponse) {
                                            case "1":
                                                printIndoorActivities(ActivityList.getAllIndoorActivities(conn));
                                                break;
                                            case "2":
                                                printOutdoorActivities(ActivityList.getAllOutdoorActivities(conn));
                                                break;
                                            case "3":
                                                printIndoorActivities(ActivityList.getAllIndoorActivities(conn));
                                                printOutdoorActivities(ActivityList.getAllOutdoorActivities(conn));
                                                break;
                                            case "4":
                                                System.out.println("Returning to the main menu.");
                                                viewingActivities = false;
                                                break;
                                            default:
                                                System.out.println("Invalid choice. Please enter a number between 1 and 4");
                                    }
                                }
                                break;
                            case "2":
                                //Create a new activity
                                ActivityDB.addActivityPrompt(conn);
                                break;
                            case "3":
                                ActivityDB.updateActivityPrompt(conn);
                                break;
                            case "4":
                                ActivityDB.deleteActivityPrompt(conn);
                                break;
                            case "5":
                                System.out.println("Exiting program, go forth and don't be bored!");
                                break;
                            default:
                                System.out.println("Invalid choice. Please enter a number between 1 and 5");
                        }
                    } while (!mainResponse.equals("5"));
                    try {
                        conn.close();
                    } catch (java.sql.SQLException e) {
                        System.err.println("Error closing connection: " + e.getMessage());
                    }
                    scanner.close();
                }
    }

    // Method to create initial database if there is not one already
    private static void createDatabase(Connection conn) {
        if (conn != null) {
            try {
                DatabaseMetaData dbmd = conn.getMetaData();
                // Check if the 'activity' table exists. Adjust the table name if yours is different.
                java.sql.ResultSet tables = dbmd.getTables(null, null, "Activities", null);
                if (!tables.next()) {
                    // Table doesn't exist, so create the database and populate it
                    if (ActivityDB.createTable(conn)) {
                        // Creating a new activity consists of an activity name, location, and description
                        ActivityDB.addActivity(conn, new Activity("Do some photography", "Outdoors", "Take either your phone or a camera and go outside and take photos of whatever is interesting to you. Try to see the world through a new lens."));
                        ActivityDB.addActivity(conn, new Activity("Go for a walk", "Outdoors", "Put on a pair of shoes, and go for a walk around your neighborhood or at a local trail."));
                        ActivityDB.addActivity(conn, new Activity("Draw a picture", "Indoors", "Type in a search for 'Sketch a Day Prompts', and draw whatever the subject of the day is. Use just a pencil and paper, or any other writing device."));
                        ActivityDB.addActivity(conn, new Activity("Read a book", "Indoors", "Choose a book to read if you don't already have one started, find a cozy spot to rest, then immerse yourself into the world of books."));
                    } else {
                        System.err.println("Failed to create database tables.");
                    }
                }
                tables.close();
            } catch (SQLException e) {
                System.err.println("Error checking for database existence: " + e.getMessage());
            }
        }
    }

    private static void printIndoorActivities(ArrayList<IndoorActivity> indoorActivities) {
        if (indoorActivities != null && !indoorActivities.isEmpty()) {
            System.out.println("\n--- Indoor Activities ---");
            for (Activity ia : indoorActivities) {
                printActivity(ia);
                System.out.println();
            }
        } else {
            System.out.println("No indoor activities found.");
        }
    }

    private static void printOutdoorActivities(ArrayList<OutdoorActivity> outdoorActivities) {
        if (outdoorActivities != null && !outdoorActivities.isEmpty()) {
            System.out.println("\n--- Outdoor Activities ---");
            for (Activity oa : outdoorActivities) {
                printActivity(oa);
                System.out.println();
            }
        } else {
            System.out.println("No outdoor activities found.");
        }
    }

    private static void printActivity(Activity a) {
        System.out.println(a.Activity + ": " + a.ID);
        System.out.print(" (" + a.Location + ") Description: " + a.Description);
        System.out.println();
    }
}
