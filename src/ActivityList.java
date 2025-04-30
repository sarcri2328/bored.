/* 
 * Name: Sarah Criswell
 * Date: 4/29/2025
 * Assignment: SDC330 Week 3 Course Project
 * 
 * Class Description: This is a class using composition that will take array lists from
 * indoor and outdoor activities, as well as add the ability to interact with local data
 * storage.
 */
import java.util.ArrayList;

public class ActivityList {
    
    private ArrayList<IndoorActivity> IndoorActivities;
    private ArrayList<OutdoorActivity> OutdoorActivities;

    public ActivityList() {
        IndoorActivities = new ArrayList<>();
        OutdoorActivities = new ArrayList<>();
    }

    public ArrayList<IndoorActivity> listIndoorActivities() {
        return IndoorActivities;
    }

    public ArrayList<OutdoorActivity> listOutdoorActivities() {
        return OutdoorActivities;
    }

    public void addNewActivity(String location, String activity, String description){
        //Add functionality to add information to local storage
    }

    public void deleteActivity(String activityID){
        //Add functionality to delete activity from local storage
    }

}
