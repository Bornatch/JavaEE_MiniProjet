package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.bankservice.Platform;
import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.Owner;

@ViewScoped
public class CarBean {

	private String brand;
	private int km;
	private String color;
	private double price;
	private String state;
	private Owner owner;

	//
	private String[] states = {"Comme neuf", "Très bon état", "Bon état",
			"Etat correct"};
	
	// private List<Owner> owners;
	private List<String> ownerNames;
	private String ownerName;
	private List<Car> cars;

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
		
		// get cars
		try {
			cars = platform.getCars();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// initialize xhtml 
		this.brand = "Marque";
		this.color = "Couleur";
		this.ownerName = "Sélectionner";
		

	}

	public String createCar() {
		try {
			Car c = new Car();
			c.setBrand(brand);
			c.setKm(km);
			c.setColor(color);
			c.setPrice(price);
			c.setState(state);
			c.setOwner(owner);

			this.transactionResult = "succès !";
			platform.createCar(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showCarResult";
	}
	
	public void updateState(ValueChangeEvent event) throws Exception {
		this.state = (String) event.getNewValue();
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String[] getStates() {
		return states;
	}

	public void setStates(String[] states) {
		this.states = states;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
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
