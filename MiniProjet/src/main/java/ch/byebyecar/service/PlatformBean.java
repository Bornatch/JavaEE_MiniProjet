package ch.byebyecar.service;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import java.util.List;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import ch.byebyecar.businessobject.Address;
import ch.byebyecar.businessobject.Bike;
import ch.byebyecar.businessobject.Car;
import ch.byebyecar.businessobject.User;

/* Students : Valentin Bornatici & Montaine Burger
 * Class : 606_3
 * Project : June 2019
 */

@Stateful
@RolesAllowed(value = { "user", "admin" })
public class PlatformBean implements Platform {

	@PersistenceContext(name = "PlatformPU", type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@Resource
	private SessionContext ctx;

	// related to the users
	public void createUser(User u) {
		em.persist(u);
	}

	public void createUser(String username, String password, String firstname, String lastname, String street,
			String code, String city) {
		User u = new User();
		Address a = new Address();

		a.setStreet(street);
		a.setCode(code);
		a.setCity(city);

		u.setUsername(username);
		u.setPassword(password);
		u.setFirstname(firstname);
		u.setLastname(lastname);
		u.setAddress(a);

		// account set at 0
		u.setAccount(0);

		em.persist(u);
	}

	public String deleteUser(Long userId) {
		int result = em.createQuery("DELETE FROM User u WHERE u.id = :id").setParameter("id", userId).executeUpdate();
		String suppression = "Cet utilisateur a bien �t� supprim� !";

		if (!ctx.isCallerInRole("admin")) {
			suppression = "Vous n'avez pas les droits requis pour effectuer cette action.";
			ctx.setRollbackOnly();
		}

		return suppression;
	}

	public List<User> getUsers() {
		return em.createQuery("FROM User").getResultList();
	}

	public User getUser(Long id) {
		return (User) em.createQuery("FROM User u where u.id=:id").setParameter("id", id).getSingleResult();
	}

	public User getUserByUsername(String username) {
		Query query = em.createQuery("FROM User u where u.username=:username");
		query.setParameter("username", username);
		return (User) query.getSingleResult();
	}

	public String updateOwner(User owner, String password, double amount) {
		User ow = owner;
		try {		
			if (ow.getAccount() + amount < 0) {
				return "Retrait trop �lever.";
			}
			em.persist(ow);
			ow.setPassword(password);
			ow.setAccount(ow.getAccount() + amount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ow.getAccount()>100000)
			return "Plein de sousous!!!! Profitez en!";
		return "Un bon d�but pour vos achats!";
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

	public void deleteCar(Long carId) {
		int result = em.createQuery("DELETE FROM Car c WHERE c.id = :id").setParameter("id", carId).executeUpdate();
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

	public Car getCarById(Long id) {
		return (Car) em.createQuery("FROM Car c where c.id=:id").setParameter("id", id).getSingleResult();
	}

	public List<Car> getCarListByUsername(String username) {
		return (List<Car>) em.createQuery("FROM Car WHERE c.owner.username=:username")
				.setParameter("username", username).getResultList();
	}

	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	public String sellCar(String srcUsername, String destUsername, Long carId) throws Exception {
		Car car = getCarById(carId);
		User src = getUserByUsername(srcUsername);
		User dst = getUserByUsername(destUsername);

		if (dst.getAccount() - car.getPrice() < 0) {
			return "Trop cher pour toi... D�sol�!";
		} else {
			// add money to the vendor
			src.setAccount(src.getAccount() + car.getPrice());
			// take money from the buyer

			// take money from the buyer
			dst.setAccount(dst.getAccount() - car.getPrice());
			// change owner
			car.setOwner(dst);

			em.persist(car);
			em.persist(src);
			em.persist(dst);

			return "L'achat de la voiture " + car.getBrand() + " a �t� r�alis� avec succ�s! Bonne route!";
		}

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

	public void deleteBike(Long bikeId) {
		int result = em.createQuery("DELETE FROM Bike b WHERE b.id = :id").setParameter("id", bikeId).executeUpdate();
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

	public Bike getBikeById(Long id) {
		return (Bike) em.createQuery("FROM Bike b where b.id=:id").setParameter("id", id).getSingleResult();
	}

	public List<Bike> getBikeListByUsername(String username) {
		return (List<Bike>) em.createQuery("FROM Bike WHERE b.owner.username=:username")
				.setParameter("username", username).getResultList();
	}

	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	public String sellBike(String srcUsername, String destUsername, Long bikeId) throws Exception {
		Bike bike = getBikeById(bikeId);
		User src = getUserByUsername(srcUsername);
		User dst = getUserByUsername(destUsername);

		if (dst.getAccount() - bike.getPrice() < 0) {
			return "Si t'as pas les sous, t'as pas le deux roues!";
		} else {
			// add money to the vendor
			src.setAccount(src.getAccount() + bike.getPrice());

			// take money from the buyer

			dst.setAccount(dst.getAccount() - bike.getPrice());

			// change owner
			bike.setOwner(dst);

			em.persist(bike);
			em.persist(src);
			em.persist(dst);

			return "L'achat de la moto " + bike.getBrand() + " a �t� r�alis� avec succ�s! Bonne route!";
		}

	}
}
