/* 
 * Name: Sarah Criswell
 * Date: 5/11/2025
 * Assignment: SDC330 Course Project - bored. 
 * 
 * Class Description: The OutdoorActivity class is a child class of the Activity abstract class.
 * It inherits from the Activity class
 */

public class OutdoorActivity extends Activity {

    //Declarations
    private String Location;
    private String Activity;
    private boolean IsSunny;

    public OutdoorActivity(Integer id, String activity, String location, String description) {
        super.ID = id;
        super.Activity = activity;
        super.Location = location;
        super.Description = description;
    }
    public OutdoorActivity(String activity, String location, String description) {
        super.Activity = activity;
        super.Location = location;
        super.Description = description;
    }

    public String getDescription(){
        return Description;
    }

    public String getLocation(){
        return Activity;
    }

    public String getActivity() {
        return Location;
    }

    public boolean getWeather() {
        return IsSunny;
    }

    public void setWeather(boolean isSunny){
        IsSunny = isSunny;
    }
}

    
    
