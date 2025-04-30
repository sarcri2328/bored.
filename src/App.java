/* 
 * Name: Sarah Criswell
 * Date: 4/27/2025
 * Assignment: SDC330 Performance Assessment - Constructors and Access Specifiers
 * 
 * Class Description: This is the main entry point of the application. This is where the program
 * initiates instances of the different classes and demonstrates the use of 
 * constructors and access specifiers.
 */

import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        //Print application header
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Sarah Criswell - Week 3 Constructors & Access Specifiers Performance Assessment");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println();

        //Print header for cars in the parking lot
        System.out.println("Cars in the parking lot:");
        System.out.println();

            //Create an instance of the ParkingLot class
            ParkingLot lot = new ParkingLot();
            //Create an instance of the ElectricCar class and add it to the ParkingLot instance
            ElectricCar eCar1 = new ElectricCar();
            lot.addCar(eCar1);
            //Create an instance of the GasCar class using the 2-parameter constructor and add to
            // the ParkingLot instance
            GasCar gasCar1 = new GasCar("Unleaded Regular", "4-Cylinder");
            lot.addCar(gasCar1);
            //Create an instance of the GasCar class using the 1-parameter constructor and add to
            // the ParkingLot instance
            GasCar gasCar2 = new GasCar("V8");
            lot.addCar(gasCar2);

        //Get list of cars in the parking lot
        ArrayList<Car> carsInLot = lot.getCars();

        //Print the ParkingLot instance
        for (Car car : carsInLot) {
            System.out.println(car);
        }
    }
}
