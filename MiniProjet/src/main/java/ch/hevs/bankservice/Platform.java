package ch.hevs.bankservice;

import java.util.List;

import ch.hevs.businessobject.Bike;
import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.Owner;

public interface Platform {

	Car getCar(String brand, String ownerLastname) throws Exception;

	public List<Car> getCarListFromOwnerlastname(String lastname) throws Exception;
	
	public List<Car> getCars() throws Exception;

	void sellCar(Car carSrc, Owner newOwner) throws Exception;

	List<Owner> getOwners();
	
	Owner getOwnerFromLastname(String ownerName) throws Exception;

	Owner getOwner(long id) throws Exception;

	void createOwner(Owner o) throws Exception;

	void createCar(Car c) throws Exception;	

	void createBike(Bike c) throws Exception;

	List<Bike> getBikes() throws Exception;

}
