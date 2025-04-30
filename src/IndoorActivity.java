/* 
 * Name: Sarah Criswell
 * Date: 4/29/2025
 * Assignment: SDC330 Week 3 Course Project
 * 
 * Class Description: The IndoorActivity class is a child class of the Activity abstract class.
 * It inherits from the Activity class
 */

public class IndoorActivity extends Activity {
    
    //Declarations
    private String Location;
    private String Activity;

    public String getDescription(){
        return "Returns activity description"; //Change this later on
    }

    public String getLocation(){
        return Activity;
    }
    public String getActivity() {
        return Location;
    }
}
