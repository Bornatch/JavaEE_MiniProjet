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
	void createUser(String username, String password, String firstname, String lastname,
			String street, String code, String city);
	void deleteUser(Long userId);
	List<User> getUsers();
	User getUser(Long id);
	User getUserByUsername(String username);
	void updateOwner(User owner, String password, double account);
	
	
	// related to the cars
	void createCar(Car c);
	void createCar(String brand, int km, String color, double price, User owner, String state);
	void deleteCar(Long carId);
	List<Car> getCars();
	Car getCar(String brand, String username);
	Car getCarById(Long id);
	List<Car> getCarListByUsername(String username);
	void sellCar(String srcUsername, String destUsername, Long carId) throws Exception;
	
	
	// related to the bikes
	void createBike(Bike b);
	void createBike(String brand, int km, String color, double price, User owner, String category);
	void deleteBike(Long bikeId);
	List<Bike> getBikes();
	Bike getBike(String brand, String username);
	Bike getBikeById(Long id);
	List<Bike> getBikeListByUsername(String username);
	void sellBike(String srcUsername, String destUsername, Long bikeId) throws Exception;
	
	
	// related to the vehicles (in general)
	Vehicle getVehicle(String brand, String username);
	List<Vehicle> getVehicles();
	List<Vehicle> getVehicleListByUsername(String username);
}
