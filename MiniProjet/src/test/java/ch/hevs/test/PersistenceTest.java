package ch.hevs.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import ch.hevs.businessobject.Owner;
import ch.hevs.businessobject.Car;


import ch.hevs.businessobject.Client;

public class PersistenceTest {

	/*
	@Test
	public void test(){
		EntityTransaction tx = null;
		try{

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("carPU");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			Owner o1 = new Owner("Emil", "Frey", 0);
			Owner o2 = new Owner("Claude", "Urfer", 0);
			Owner o3 = new Owner("Mario", "Super", 0);
			
			Car c1 = new Car("Toyota", 20, "blue", 24999.99, o1);
			Car c2 = new Car("BMW", 10, "grey", 35000, o2);
			Car c3 = new Car("Kart", 25, "red", 125.25, o1);
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	*/
	
}
