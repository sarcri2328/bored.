/* 
 * Name: Sarah Criswell
 * Date: 4/29/2025
 * Assignment: SDC330 Week 3 Course Project
 * 
 * Class Description: The Activity class is the super class of IndoorActivity and OutdoorActivity.
 * It contains getters for each field in the database, and constructors for the Database.
 */

public class Activity {
    public int ID;
    public String Activity;
    public String Location;
    public String Description;

    public Activity(int iD, String activity, String location, String description) {
        ID = iD;
        Activity = activity;
        Location = location;
        Description = description;
    }

    public Activity(String activity, String location, String description) {
        Activity = activity;
        Location = location;
        Description = description;
    }

    public Activity() {
    }

    public String getDescription(){
        return Description;
    }
    public String getLocation(){
        return Location;
    }
    public String getActivity(){
        return Activity;
    }
}
