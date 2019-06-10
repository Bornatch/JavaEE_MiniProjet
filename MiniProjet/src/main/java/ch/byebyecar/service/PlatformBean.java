package ch.byebyecar.service;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ch.byebyecar.businessobject.Bike;
import ch.byebyecar.businessobject.Car;
import ch.byebyecar.businessobject.User;
import ch.byebyecar.businessobject.Vehicle;

/* Students : Valentin Bornatici & Montaine Burger
 * Class : 606_3
 * Project : June 2019
 */


@Stateless
public class PlatformBean implements Platform {
	
	@PersistenceContext(name = "PlatformPU")
	private EntityManager em;
	
	
	// related to the users
	public void createUser(User u) {
		em.persist(u);
	}
	
	public void createUser(String username, String firstname, String lastname) {
		User u = new User();
		
		u.setUsername(username);
		u.setFirstname(firstname);
		u.setLastname(lastname);
		
		// account set at 0
		u.setAccount(0);
		
		em.persist(u);
	}
	
	public void deleteUser(long userId) {
		int result = em.createQuery("DELETE FROM User u WHERE u.id = :id").setParameter("id", userId)
				.executeUpdate();
	}
	
	public List<User> getUsers() {
		return em.createQuery("FROM User").getResultList();
	}
	
	public User getUser(long id) {
		return (User) em.createQuery("FROM User u where u.id=:id")
				.setParameter("id", id).getSingleResult();
	}
	
	public User getUserByUsername(String username) {
		Query query = em.createQuery("FROM User u where u.username=:username");
		query.setParameter("username", username);
		return (User) query.getSingleResult();
	}
	
	
	// related to the cars
	public void createCar(Car c) {
		em.persist(c);
	}
	
	public void createCar(String brand, int km, String color, double price, User owner, String state) {
		Car c = new Car();
		
		c.setBrand(brand);
		c.setKm(km);
		c.setColor(color);
		c.setPrice(price);
		c.setOwner(owner);
		c.setState(state);
		
		em.persist(c);
	}
	
	public void deleteCar(long carId) {
		int result = em.createQuery("DELETE FROM Car c WHERE c.id = :id").setParameter("id", carId)
				.executeUpdate();
	}
	
	public List<Car> getCars() {
		return em.createQuery("FROM Car").getResultList();
	}
	
	public Car getCar(String brand, String username) {
		Query query = em.createQuery("FROM Car c WHERE c.brand=:brand AND c.owner.username=:username");
		query.setParameter("brand", brand);
		query.setParameter("username", username);
		
		return (Car) query.getSingleResult();		
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
	
	
	// related to the bikes
	public void createBike(Bike b) {
		em.persist(b);
	}
	
	public void createBike(String brand, int km, String color, double price, User owner, String category) {
		Bike b = new Bike();
		
		b.setBrand(brand);
		b.setKm(km);
		b.setColor(color);
		b.setPrice(price);
		b.setOwner(owner);		
		b.setCategory(category);
		
		em.persist(b);	
	}

	public void deleteBike(long bikeId) {
		int result = em.createQuery("DELETE FROM Bike b WHERE b.id = :id").setParameter("id", bikeId)
				.executeUpdate();
	}
	
	public List<Bike> getBikes() {
		return em.createQuery("FROM Bike").getResultList();
	}
	
	public Bike getBike(String brand, String username) {
		Query query = em.createQuery("FROM Bike b WHERE b.brand=:brand AND b.owner.username=:username");
		query.setParameter("brand", brand);
		query.setParameter("username", username);
		
		return (Bike) query.getSingleResult();		
	}
	
	public void sellBike(Bike bike, User newOwner) throws Exception {
		em.persist(bike);
		em.persist(newOwner);
		
		// add money to the vendor
		bike.getOwner().setAccount(bike.getPrice());		
		// take money from the buyer
		newOwner.setAccount(newOwner.getAccount() - bike.getPrice());		
		// change owner
		bike.setOwner(newOwner);
	}
	

	// related to the vehicles (in general)
	public Vehicle getVehicle(String brand, String username) {
		Query query = em.createQuery("FROM Vehicle v WHERE v.brand=:brand AND v.owner.username=:username");
		query.setParameter("brand", brand);
		query.setParameter("username", username);
		
		return (Vehicle) query.getSingleResult();		
	}
	
	public List<Vehicle> getVehicles() {
		return em.createQuery("FROM Vehicle").getResultList();
	}
	
	public List<Vehicle> getVehicleListByOwnerUsername(String username) {
		return (List<Vehicle>) em.createQuery("SELECT u.vehicles FROM User u where u.username=:username")
				.setParameter("username", username).getResultList();
	}
}
