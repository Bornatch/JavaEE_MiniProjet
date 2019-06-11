package ch.hevs.bankservice;

import java.util.List;

import ch.hevs.businessobject.Bike;
import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.Owner;

public interface Platform {

	Car getCar(String brand, String ownerLastname) throws Exception;
	
	Car getCarById(long id) throws Exception;

	public List<Car> getCarListFromOwnerlastname(String lastname) throws Exception;
	
	public List<Car> getCars() throws Exception;

	void sellCar(String sourceOwnerName, String destinationOwnerName, long carId) throws Exception;

	List<Owner> getOwners();
	
//	Owner getOwnerFromLastname(String ownerName) throws Exception;
	
	Owner getOwnerFromUsername(String Username) throws Exception;

	Owner getOwner(long id) throws Exception;

	void createOwner(Owner o) throws Exception;
	
	void updateOwner(Owner owner, String password, double account);

	void createCar(Car c) throws Exception;	

	void createBike(Bike c) throws Exception;

	List<Bike> getBikes() throws Exception;

	void createCar(String brand, int km, String color, double price, String state, Owner owner);

	void createOwner(String firstname, String lastname, String username, String password);

	//void updateOwner(String usernameInit, String firstname, String lastname, String usernameNew, String password);

	

	

}
