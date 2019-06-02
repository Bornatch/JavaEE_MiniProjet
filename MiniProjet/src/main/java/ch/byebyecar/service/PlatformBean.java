package ch.byebyecar.service;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import ch.byebyecar.businessobject.Bike;
import ch.byebyecar.businessobject.Car;
import ch.byebyecar.businessobject.User;
import ch.byebyecar.businessobject.Vehicle;

/* Students : Valentin Bornatici & Montaine Burger
 * Class : 606_3
 * Project : June 2019
 */


@Stateful
public class PlatformBean implements Platform {
	
	@PersistenceContext(name = "PlatformPU", type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	
	// related to the vehicles (in general)
	public Vehicle getVehicle(String brand, String ownerLastname) /*throws Exception*/ {
		Query query = em.createQuery("FROM Vehicle v WHERE v.brand=:brand AND v.owner.lastname=:ownerLastname");
		query.setParameter("brand", brand);
		query.setParameter("ownerLastname", ownerLastname);
		
		return (Vehicle) query.getSingleResult();
	}
	
	public List<Vehicle> getVehicleListByOwnerLastname(String lastname) /*throws Exception*/ {
		return (List<Vehicle>) em.createQuery("SELECT u.vehicles FROM User u where u.lastname=:lastname")
				.setParameter("lastname", lastname).getResultList();
	}
	
	
	// related to the cars
	public List<Car> getCars() {
		return em.createQuery("FROM Car").getResultList();
	}
	
	public void createCar(Car c) /*throws Exception*/ {
		em.persist(c);
	}
	
	public void sellCar(Car car, User newOwner) throws Exception {
		em.persist(car);
		em.persist(newOwner);
		
		// add money to the vendor
		car.getOwner().setAccount(car.getPrice());		
		// take money from the buyer
		newOwner.setAccount(newOwner.getAccount() - car.getPrice());		
		// change owner
		car.setOwner(newOwner);
	}
	
	
	// related to the users
	public User getUser(long id) /*throws Exception*/ {
		return (User) em.createQuery("FROM User u where u.id=:id")
				.setParameter("id", id).getSingleResult();
	}
	
	public void createUser(User u) /*throws Exception*/ {
		em.persist(u);
	}
	
	public List<User> getUsers() {
		return em.createQuery("FROM User").getResultList();
	}

	public User getUserByLastname(String lastname) /*throws Exception*/ {
		Query query = em.createQuery("FROM User u where u.lastname=:lastname");
		query.setParameter("lastname", lastname);
		return (User) query.getSingleResult();
	}
	
	
	// related to the bikes
	public void createBike(Bike b) /*throws Exception*/ {
		em.persist(b);
	}
	
	public List<Bike> getBikes() /*throws Exception*/ {
		return em.createQuery("FROM Bike").getResultList();
	}
}
