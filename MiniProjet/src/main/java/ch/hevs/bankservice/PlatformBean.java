package ch.hevs.bankservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Bike;
import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.Client;
import ch.hevs.businessobject.Owner;

@Stateless
public class PlatformBean implements Platform {

	@PersistenceContext(name = "CarPU")
	private EntityManager em;

	@Override
	public Car getCar(String brand, String ownerLastname) throws Exception {
		Query query1 = em.createQuery("FROM Owner a WHERE a.nom=:ownerLastname");
		query1.setParameter("ownerLastname", ownerLastname);
		Owner prop = (Owner) query1.getSingleResult();

		Query query = em.createQuery("FROM Car a WHERE a.brand=:brand AND a.owner=:owner");
		query.setParameter("brand", brand);
		query.setParameter("owner", prop);

		return (Car) query.getSingleResult();
	}
	
	@Override
	public Car getCarById(long id) throws Exception {
		return (Car) em.createQuery("FROM Car c where c.id=:id").setParameter("id", id).getSingleResult();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getCarListFromOwnerlastname(String lastname) throws Exception {
		return (List<Car>) em.createQuery("SELECT o.cars " + "FROM Owner o " + "where o.lastname=:lastname")
				.setParameter("lastname", lastname).getResultList();
	}

	@Override
	public List<Owner> getOwners() {
		return em.createQuery("FROM Owner").getResultList();
	}

	@Override
	public Owner getOwner(long id) throws Exception {
		return (Owner) em.createQuery("FROM Owner c where c.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public void createOwner(Owner o) throws Exception {
		em.persist(o);

	}

	@Override
	public void createOwner(String firstname, String lastname, String username, String password) {

		Owner o = new Owner();
		o.setFirstname(firstname);
		o.setLastname(lastname);
		o.setUsername(username);
		o.setPassword(password);
		// compte client initialisé à 0
		o.setAccount(0);

		em.persist(o);

	}

	@Override
	public void updateOwner(Owner o, String password, double account) {
		Query query = em.createQuery("UPDATE Owner o SET  o.password= :password, o.account = account WHERE o.id = :id ")
				.setParameter("password", password).setParameter("account", account).setParameter("id", o.getId());

	}

	@Override
	public void createCar(Car c) throws Exception {
		em.persist(c);

	}

	@Override
	public void createCar(String brand, int km, String color, double price, String state, Owner owner) {
		Car c = new Car();
		c.setBrand(brand);
		c.setKm(km);
		c.setColor(color);
		c.setPrice(price);
		c.setState(state);
		c.setOwner(owner);

		em.persist(c);

	}

	@Override
	public Owner getOwnerFromUsername(String username) throws Exception {
		Query query = em.createQuery("FROM Owner o WHERE o.username=:username");
		query.setParameter("username", username);
		return (Owner) query.getSingleResult();
	}

	@Override
	public void createBike(Bike c) throws Exception {
		em.persist(c);
	}

	@Override
	public List<Car> getCars() throws Exception {
		return em.createQuery("FROM Car").getResultList();
	}

	@Override
	public List<Bike> getBikes() throws Exception {
		return em.createQuery("FROM Bike").getResultList();
	}
	
	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	@Override
	public String sellCar(String sourceOwnerName, String destinationOwnerName, long carId) throws Exception {
		
		Car car = getCarById(carId);
		
		Owner src = getOwnerFromUsername(sourceOwnerName);
		Owner dst = getOwnerFromUsername(destinationOwnerName);
		
		if(car.getOwner().getId() == src.getId()){
			em.persist(src);
			em.persist(dst);
			
			em.persist(car);
			
			src.setAccount(src.getAccount()+car.getPrice());
			dst.setAccount(dst.getAccount()-car.getPrice());
			
			car.setOwner(dst);
			
			return "showSellResult";
		}
		else
			return "error";		
	}



	
	// @Override
	// public Owner getOwnerFromLastname(String ownerName) throws Exception {
	// // TODO Auto-generated method stub
	// return null;
	// }

}
