/* 
 * Name: Sarah Criswell
 * Date: 5/11/2025
 * Assignment: SDC330 Course Project - bored. 
 * 
 * Class Description: This is a class using composition that will pull from the local data storage in order to make array lists for
 * indoor and outdoor activities.
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ActivityList {
    
    private static ArrayList<IndoorActivity> IndoorActivities;
    private static ArrayList<OutdoorActivity> OutdoorActivities;

    public ActivityList() {

    }

    //Method to get all Indoor Activities from the Activities table where the location is equal to 'Indoors'
    public static ArrayList<IndoorActivity> getAllIndoorActivities(Connection conn) {
        IndoorActivities = new ArrayList<>();
        String sql = "SELECT * FROM Activities WHERE Location='Indoors'";
    
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                IndoorActivity a = new IndoorActivity(rs.getInt("ID"), rs.getString("Activity"),
                    rs.getString("Location"), rs.getString("Description"));
                    IndoorActivities.add(a);
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
            } if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error closing statement: " + e.getMessage());
                }
            }
        }
        return IndoorActivities;
    }

    //Method to get all Outdoor Activities from the Activities table where the location is equal to 'Outdoors'
    public static ArrayList<OutdoorActivity> getAllOutdoorActivities(Connection conn) {
        OutdoorActivities = new ArrayList<>();
        String sql = "SELECT * FROM Activities WHERE Location='Outdoors'";
    
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                OutdoorActivity a = new OutdoorActivity(rs.getInt("ID"), rs.getString("Activity"),
                    rs.getString("Location"), rs.getString("Description"));
                    OutdoorActivities.add(a);
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
            } if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error closing statement: " + e.getMessage());
                }
            }
        }
        return OutdoorActivities;
    }

}
