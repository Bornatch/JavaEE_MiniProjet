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
	public Car getCar(String brand, String ownerLastname) throws Exception {
		Query query1 = em.createQuery("FROM Owner a WHERE a.nom=:ownerLastname");
		query1.setParameter("ownerLastname", ownerLastname);
		Owner prop = (Owner) query1.getSingleResult();
		
		Query query = em.createQuery("FROM Car a WHERE a.brand=:brand AND a.owner=:owner");
		query.setParameter("brand", brand);
		query.setParameter("owner", prop );

		return (Car) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getCarListFromOwnerlastname(String lastname) throws Exception {
		return (List<Car>) em.createQuery("SELECT o.cars " + "FROM Owner o " + "where o.nom=:lastname")
				.setParameter("lastname", lastname).getResultList();
	}

	@Override
	public void sellCar(Car carSrc, Owner newOwner) throws Exception {
		em.persist(carSrc);
		em.persist(newOwner);
		//ajouter l'argent au vendeur
		carSrc.getOwner().setAccount(carSrc.getPrice());
		// changer de propriétaire
		carSrc.setOwner(newOwner);
		// retirer le montant au propriétaire
		newOwner.setAccount(newOwner.getAccount() - carSrc.getPrice());

	}

	@Override
	public List<Owner> getOwners() throws Exception {
		return em.createQuery("FROM Owner").getResultList();
	}

	@Override
	public Owner getOwner(long id) throws Exception {
		return (Owner) em.createQuery("FROM Owner c where c.id=:id").
				setParameter("id", id).getSingleResult();
	}

	@Override
	public void createOwner(Owner o) throws Exception {
		em.persist(o);
		
	}

}
