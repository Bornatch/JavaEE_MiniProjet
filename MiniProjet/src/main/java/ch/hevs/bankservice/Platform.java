package ch.hevs.bankservice;

import java.util.List;

import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.Owner;

public interface Platform {
	
	Car getCar(String brand );
	
	public List<Car> getCarListFromOwnerlastname(String lastname);
	
	//void sellCar(Car carSrc, )
	
	List<Owner> getOwners();
	
	Owner getOwner(long id);

}