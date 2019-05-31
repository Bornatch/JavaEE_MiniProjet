package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.bankservice.Platform;
import ch.hevs.businessobject.Bike;
import ch.hevs.businessobject.Owner;

public class BikeBean {

	private String brand;
	private int km;
	private String color;
	private double price;
	private String category;
	private Owner owner;

	//
	private String[] categories = {"Sportive", "Custom", "Enduro",
			"Trial", "Cross", "Routière"};
	
	// private List<Owner> owners;
	private List<String> ownerNames;
	private String ownerName;

	private String transactionResult;
	private Platform platform;

	@PostConstruct
	public void initialize() throws NamingException {

		// use JNDI to inject reference to platform EJB
		InitialContext ctx = new InitialContext();
		platform = (Platform) ctx
				.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/PlatformBean!ch.hevs.bankservice.Platform");

		// get owners
		List<Owner> ownerList = platform.getOwners();
		this.ownerNames = new ArrayList<String>();
		if (ownerList.isEmpty() == true) {
			ownerNames.add("ca marche presque ...");
		} else {
			for (Owner o : ownerList) {
				this.ownerNames.add(o.getLastname());
			}
		}

		// initialize xhtml 
		this.brand = "Marque";
		this.color = "Couleur";
		this.ownerName = "Sélectionner";	

	}

	public String createBike() {
		try {
			Bike c = new Bike();
			c.setBrand(brand);
			c.setKm(km);
			c.setColor(color);
			c.setPrice(price);
			c.setCategory(category);
			c.setOwner(owner);

			this.transactionResult = "succès !";
			platform.createBike(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showVehiculeResult";
	}
	
	public void updatecategory(ValueChangeEvent event) throws Exception {
		this.category = (String) event.getNewValue();
	}

	public void updateOwner(ValueChangeEvent event) throws Exception {
		this.ownerName = (String) event.getNewValue();

		this.owner = platform.getOwnerFromLastname(this.ownerName);

	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getTransactionResult() {
		return transactionResult;
	}

	public void setTransactionResult(String transactionResult) {
		this.transactionResult = transactionResult;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public List<String> getOwnerNames() {
		return ownerNames;
	}

	public void setOwnerNames(List<String> ownerNames) {
		this.ownerNames = ownerNames;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

}
