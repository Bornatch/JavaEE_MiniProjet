package ch.hevs.bankservice;

import java.util.List;

import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.Owner;

public interface Platform {
	
	Car getCar(String brand, String ownerLastname) throws Exception;
	
	public List<Car> getCarListFromOwnerlastname(String lastname) throws Exception;
	
	void sellCar(Car carSrc, Owner newOwner) throws Exception;
	
	List<Owner> getOwners() throws Exception;
	
	Owner getOwner(long id) throws Exception;

	void createOwner(Owner o) throws Exception;

}
