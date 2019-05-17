package ch.hevs.bankservice;

import java.util.List;

import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.Owner;

public interface Platform {
	
	Car getCar(String brand, String color);
	
	public List<Car> getCarListFromOwnerlastname(String lastname);
	
	void sellCar(Car carSrc, Owner newOwner, double amount) throws Exception;
	
	List<Owner> getOwners();
	
	Owner getOwner(long id);

}
