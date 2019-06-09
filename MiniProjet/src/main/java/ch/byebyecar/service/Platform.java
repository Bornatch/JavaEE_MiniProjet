package ch.byebyecar.service;
import java.util.List;
import javax.ejb.Local;
import ch.byebyecar.businessobject.Bike;
import ch.byebyecar.businessobject.Car;
import ch.byebyecar.businessobject.User;
import ch.byebyecar.businessobject.Vehicle;

/* Students : Valentin Bornatici & Montaine Burger
 * Class : 606_3
 * Project : June 2019
 */


@Local
public interface Platform {
	
	//TODO: check if all methods are used!
	
	// related to the users
	void createUser(User u);
	void createUser(String username, String firstname, String lastname);
	void deleteUser(long userId);
	List<User> getUsers();
	User getUser(long id);
	User getUserByUsername(String username);
	
	
	// related to the cars
	void createCar(Car c);
	void createCar(String brand, int km, String color, double price, User owner, String state);
	void deleteCar(long carId);
	List<Car> getCars();
	Car getCar(String brand, String username);
	void sellCar(Car car, User newOwner) throws Exception;
	
	
	// related to the bikes
	void createBike(Bike b);
	void createBike(String brand, int km, String color, double price, User owner, String category);
	void deleteBike(long bikeId);
	List<Bike> getBikes();
	Bike getBike(String brand, String username);
	void sellBike(Bike bike, User newOwner) throws Exception;
	
	
	// related to the vehicles (in general)
	Vehicle getVehicle(String brand, String username);
	List<Vehicle> getVehicleListByOwnerUsername(String username);
}
