/* 
 * Name: Sarah Criswell
 * Date: 4/27/2025
 * Assignment: SDC330 Performance Assessment - Constructors and Access Specifiers
 * 
 * Class Description: This is the car class which is the super class of GasCar and ElectricCar.
 * It provides all of the properties and methods needed to create a car.
 */

public class Car {
    //Declarations
    private String Fuel;
    private String Engine;

    protected Car(String fuel, String engine) {
        Fuel = fuel;
        Engine = engine;
    }

    //Getters and setters
    public String getEngine() {
        return Engine;
    }
    protected void setEngine(String engine) {
        Engine = engine;
    }

    public String getFuel() {
        return Fuel;
    }
    protected void setFuel(String fuel) {
        Fuel = fuel;
    }

    @Override
    public String toString() {
        return
            "Car Type: " + getClass().getName() + "\n" +
            "Engine Type: " + getEngine() + "\n" +
            "Fuel Type: " + getFuel() + "\n";
    }
}
