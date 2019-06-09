package ch.hevs.bankservice;

import java.util.List;

import javax.ejb.Stateless;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getCarListFromOwnerlastname(String lastname) throws Exception {
		return (List<Car>) em.createQuery("SELECT o.cars " + "FROM Owner o " + "where o.lastname=:lastname")
				.setParameter("lastname", lastname).getResultList();
	}

	@Override
	public void sellCar(Car carSrc, Owner newOwner) throws Exception {
		em.persist(carSrc);
		em.persist(newOwner);
		// ajouter l'argent au vendeur
		carSrc.getOwner().setAccount(carSrc.getPrice());
		// changer de propriétaire
		carSrc.setOwner(newOwner);
		// retirer le montant au propriétaire
		newOwner.setAccount(newOwner.getAccount() - carSrc.getPrice());

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
		//compte client initialisé à 0
		o.setAccount(0);
		
		em.persist(o);
		
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

//	@Override
//	public Owner getOwnerFromLastname(String ownerName) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}

	


}
