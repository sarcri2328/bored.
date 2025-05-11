/*******************************************************************
* Name: Sarah Criswell
* Date: 5/11/2025
* Assignment: SDC330 Course Project - bored. 
*
* Class to handle all interactions with the Activities table in the
* database, including creating the table if it doesn't exist and all
* CRUD (Create, Read Update, Delete) operations on the Activities table.
* Note that the interactions are all done using standard SQL syntax
* that is then executed by the SQLite JDBC library.
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ActivityDB {

    //Method for creating a new table at the beginning of the application if one does not already exist
    public static boolean createTable(Connection conn) {
        // SQL statement for creating a new table
        String sql =
            "CREATE TABLE IF NOT EXISTS Activities (\n"
            + " ID integer PRIMARY KEY\n"
            + " ,Activity varchar(50)\n"
            + " ,Location varchar(50)\n"
            + " ,Description varchar(100))";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error closing statement: " + e.getMessage());
                }
            }
        }
    }

    //Method for adding a new activity. This is only used for manually adding template data at beginning of the app creation. It will not be used again or able to be used by user in the terminal.
    public static void addActivity(Connection conn, Activity a){
        String sql =
            "INSERT INTO Activities(Activity, Location, Description) VALUES(?,?,?)";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, a.Activity);
            pst.setString(2, a.Location);
            pst.setString(3, a.Description);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e){
                    System.out.println("Error closing prepared statement: " + e.getMessage());
                }
            }
        }
    }

    //Method for adding new activities in the terminal. Contains a scanner for user input, and adds the activity to the database.
    public static void addActivityPrompt(Connection conn){
        Scanner scanner = new Scanner(System.in);
        Activity newActivity = new Activity();

        System.out.print("Enter the activity name: ");
        newActivity.Activity = scanner.nextLine();

        System.out.print("Enter the location of the activity (Indoors/Outdoors): ");
        newActivity.Location = scanner.nextLine();

        System.out.print("Enter the description of the activity: ");
        newActivity.Description = scanner.nextLine();
        
        String sql =
            "INSERT INTO Activities(Activity, Location, Description) VALUES(?,?,?)";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, newActivity.Activity);
            pst.setString(2, newActivity.Location);
            pst.setString(3, newActivity.Description);
            pst.executeUpdate();
            System.out.println("Activity added successfully!");
        } catch(SQLException e) {
            System.out.println("Error adding activity: " + e.getMessage());
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    System.out.println("Error closing prepared statement: " + e.getMessage());
                }
            }
        }
    }

    //Method for updating activities via the terminal. It contains a scanner that is used to take in user input and update the database.
    public static void updateActivityPrompt(Connection conn){
        Scanner updateScanner = new Scanner(System.in);
        System.out.println("Enter the ID of the activity you want to update: ");
        PreparedStatement pst = null;
        try {
            int idToUpdate = Integer.parseInt(updateScanner.nextLine());
            Activity existingActivity = ActivityDB.getActivity(conn, idToUpdate);

            if (existingActivity.ID != -1) {
                Activity updatedActivity = new Activity();
                updatedActivity.ID = idToUpdate;
                System.out.print("Enter the new activity name (leave blank to keep current: " + existingActivity.Activity + "): ");
                String newActivityName = updateScanner.nextLine();
                updatedActivity.Activity = newActivityName.isEmpty() ? existingActivity.Activity : newActivityName;

                System.out.print("Enter the new location (leave blank to keep current: " + existingActivity.Location + "): ");
                String newLocation = updateScanner.nextLine().toLowerCase();
                updatedActivity.Location = newLocation.isEmpty() ? existingActivity.Location : newLocation;

                System.out.print("Enter the new description (leave blank to keep current: " + existingActivity.Description + "): ");
                String newDescription = updateScanner.nextLine();
                updatedActivity.Description = newDescription.isEmpty() ? existingActivity.Description : newDescription;

                String sql =
                "UPDATE Activities SET Activity=?, Location=?, Description=? WHERE id=?";
                try {
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, updatedActivity.Activity);
                    pst.setString(2, updatedActivity.Location);
                    pst.setString(3, updatedActivity.Description);
                    pst.setInt(4, updatedActivity.ID);
                    pst.executeUpdate();
                    System.out.println("Activity updated successfully!");
                } catch(SQLException e) {
                    System.out.println("Error updating activity: " + e.getMessage());
                }  finally {
                    if (pst != null){
                        try {
                            pst.close();
                        } catch (SQLException e) {
                            System.out.println("Error closing prepared statement: " + e.getMessage());
                        }
                    }
                }
            }
            else {
                System.out.println("Activity with ID " + idToUpdate + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }

    //Method for deleting activities via the terminal. It contains a scanner that is used to take in user input and delete specific rows from the database.
    public static void deleteActivityPrompt(Connection conn) {

        Scanner deleteScanner = new Scanner(System.in);
        System.out.println("Enter the ID of the activity you want to delete: ");

        String sql = "DELETE from Activities WHERE ID=?";
        PreparedStatement pst = null;
        try {
            int idToDelete = Integer.parseInt(deleteScanner.nextLine());
            pst = conn.prepareStatement(sql);
            pst.setInt(1, idToDelete);
            pst.executeUpdate();
            System.out.println("Id Number " + idToDelete + " has been deleted!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number");
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    System.out.println("Error closing prepared statement: " + e.getMessage());
                }
            }
        }
    }

    //Method for finding activities using SQL select statements. This is used in the update and delete methods above.
    public static Activity getActivity(Connection conn, int id) {
        Activity a = new Activity();
        String sql = "SELECT * FROM Activities WHERE ID=?";

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                a.ID = rs.getInt("ID");
                a.Activity = rs.getString("Activity");
                a.Location = rs.getString("Location");
                a.Description = rs.getString("Description");
            } else {
                a.ID = id;
                a.Activity = "Activity";
                a.Location = "Not";
                a.Description = "Found";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Error closing result set: " + e.getMessage());
                }
            } if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    System.out.println("Error closing prepared statement: " + e.getMessage());
                }
            }
        }
        
        return a;
    }
}
