package ch.hevs.bankservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.Client;
import ch.hevs.businessobject.Owner;

@Stateless
public class PlatformBean implements Platform {

	@PersistenceContext(name = "CarPU")
	private EntityManager em;

	@Override
	public Car getCar(String brand, String color) {
		Query query = em.createQuery("FROM Car a WHERE a.brand=:brand AND a.color=:color");
		query.setParameter("brand", brand);
		query.setParameter("color", color);

		return (Car) query.getSingleResult();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getCarListFromOwnerlastname(String lastname) {
		return (List<Car>) em.createQuery("SELECT o.cars " + "FROM Owner o " + "where o.lastname=:lastname")
				.setParameter("lastname", lastname).getResultList();
	}

	@Override
	public void sellCar(Car carSrc, Owner newOwner, double amount) throws Exception {
		em.persist(carSrc);
		em.persist(newOwner);
		//ajouter l'argent au vendeur
		carSrc.getOwner().setAccount(amount);
		// changer de propriétaire
		carSrc.setOwner(newOwner);
		// retirer le montant au propriétaire
		newOwner.setAccount(newOwner.getAccount() - amount);

	}

	@Override
	public List<Owner> getOwners() {
		return em.createQuery("FROM Owner").getResultList();
	}

	@Override
	public Owner getOwner(long id) {
		return (Owner) em.createQuery("FROM Owner c where c.id=:id").
				setParameter("id", id).getSingleResult();
	}

}
