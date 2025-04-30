/* 
 * Name: Sarah Criswell
 * Date: 4/27/2025
 * Assignment: SDC330 Performance Assessment - Constructors and Access Specifiers
 * 
 * Class Description: This is the ParkingLot class which is composed of Caar objects. It 
 * includes ArrayList of Car objects, and a methods to get cars and add cars to the list
 */
import java.util.ArrayList;

public class ParkingLot {
    
    private ArrayList<Car> cars;

    public ParkingLot() {
        cars = new ArrayList<>();
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

}
