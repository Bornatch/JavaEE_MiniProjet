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
	
	// related to the vehicles (in general)
	Vehicle getVehicle(String brand, String ownerLastname) /*throws Exception*/;
	public List<Vehicle> getVehicleListByOwnerLastname(String lastname) /*throws Exception*/;
		
	
	// related to the cars
	public List<Car> getCars() /*throws Exception*/;
	void sellCar(Car car, User newOwner) throws Exception;
	void createCar(Car c) /*throws Exception*/;
		
	
	// related to the bikes
	void createBike(Bike b) /*throws Exception*/;
	List<Bike> getBikes() /*throws Exception*/;
	
	
	// related to the users
	User getUser(long id) /*throws Exception*/;
	User getUserByLastname(String lastname) /*throws Exception*/;
	List<User> getUsers();
	void createUser(User u) /*throws Exception*/;
}
