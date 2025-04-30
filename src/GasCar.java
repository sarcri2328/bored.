/* 
 * Name: Sarah Criswell
 * Date: 4/27/2025
 * Assignment: SDC330 Performance Assessment - Constructors and Access Specifiers
 * 
 * Class Description: This is the GasCar class which is the child class of Car.
 * It has its own methods separate from the Car class that are specific to cars that run off
 * of gas.
 */

public class GasCar extends Car {
    
    //Class Constructor with 1 parameter
    public GasCar(String engine) {
        super("Unleaded", engine);
    }

    //Class Constructor with 2 parameters
    public GasCar(String fuelType, String engine) {
        super(fuelType, engine);
    }
    
    //Update super class fuelType
    public void updateFuel(String fuelType) {
        setFuel(fuelType);
    }

}
