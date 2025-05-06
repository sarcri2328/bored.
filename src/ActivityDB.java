/*******************************************************************
* Name: Sarah Criswell
* Date: 5/6/2025
* Assignment: SDC330 Week 3 Course Project
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
import java.util.ArrayList;

public class ActivityDB {
    public static boolean createTable(Connection conn) {
        // SQL statement for creating a new table
        String sql =
            "CREATE TABLE IF NOT EXISTS Activities (\n"
            + " ID integer PRIMARY KEY\n"
            + " ,Activity varchar(50)\n"
            + " ,Location varchar(50)\n"
            + " ,Description varchar(100))";

        System.out.println(sql);
        
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void addActivity(Connection conn, Activity a){
        String sql =
            "INSERT INTO Activities(Activity, Location, Description) VALUES(?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, a.Activity);
            pst.setString(2, a.Location);
            pst.setString(3, a.Description);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateActivity(Connection conn, Activity a) {
        String sql =
            "UPDATE Activities SET Activity=?, Location=?, Description=? WHERE id=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, a.Activity);
            pst.setString(2, a.Location);
            pst.setString(3, a.Description);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteActivity(Connection conn, int id) {
        String sql = "DELETE from Activities WHERE ID=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Activity> getAllIndoorActivities(Connection conn) {
        ArrayList<Activity> indoorActivities = new ArrayList<Activity>();
        String sql = "SELECT * FROM Activities WHERE Location='Indoor'";
    
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Activity a = new Activity(rs.getInt("ID"), rs.getString("Activity"),
                    rs.getString("Location"), rs.getString("Description"));
                    indoorActivities.add(a);
            }   
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return indoorActivities;
    }

    public static ArrayList<Activity> getAllOutdoorActivities(Connection conn) {
        ArrayList<Activity> outdoorActivities = new ArrayList<Activity>();
        String sql = "SELECT * FROM Activities WHERE Location='Outdoor'";
    
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Activity a = new Activity(rs.getInt("ID"), rs.getString("Activity"),
                    rs.getString("Location"), rs.getString("Description"));
                    outdoorActivities.add(a);
            }   
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return outdoorActivities;
    }

    public static Activity getActivity(Connection conn, int id) {
        Activity a = new Activity();
        String sql = "SELECT * FROM Activities WHERE ID=?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
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
        }
        
        return a;
    }
}
