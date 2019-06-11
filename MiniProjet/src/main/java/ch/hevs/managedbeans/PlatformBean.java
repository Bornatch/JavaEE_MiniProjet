package ch.hevs.managedbeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ActionEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.bankservice.Bank;
import ch.hevs.bankservice.Platform;
import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Bike;
import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.Owner;
import ch.hevs.businessobject.Vehicule;

@RequestScoped
public class PlatformBean {

	private List<Owner> owner;
	private List<String> ownerNames;
	private String transactionResult;
	private String sourceOwnerName;
	private String destinationOwnerName;
	private double price;
	private Platform platform;

	private List<Bike> bikes;
	private Bike bike;

	private List<Car> cars;
	private Car car;

	// data from dataTable
	private HtmlDataTable datatableCars;

	@PostConstruct
	public void initialize() throws Exception {

		// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		platform = (Platform) ctx
				.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/PlatformBean!ch.hevs.bankservice.Platform");

		// get clients
		List<Owner> ownerList = platform.getOwners();
		this.ownerNames = new ArrayList<String>();
		for (Owner owner : ownerList) {
			this.ownerNames.add(owner.getLastname());
		}

		// get cars
		try {
			cars = platform.getCars();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// get bikes
		try {
			bikes = platform.getBikes();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String sellCar() {

		try {
			sourceOwnerName = car.getOwner().getUsername();

			if (sourceOwnerName.equals(destinationOwnerName)) {

				this.transactionResult = "Error: Owner are identical!";
			} else {

		//		Vehicule car = platform.getCar(car.getId());
		//		Account compteDest = bank.getAccount(destinationAccountDescription, destinationClientName);

				platform.sellCar(sourceOwnerName, destinationOwnerName, car.getId());
				this.transactionResult = "Success!";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showSellResult"; // the String value returned represents the
										// outcome used by the navigation
										// handler to determine what page to
										// display next.
	}

	public String getCarFromList() {
		setCar((Car) datatableCars.getRowData());

		return "carInfo";
	}

	// initialisation des boutons
	public String getCarList() {
		return "carList";
	}

	public List<Owner> getOwner() {
		return owner;
	}

	public void setOwner(List<Owner> owner) {
		this.owner = owner;
	}

	public List<String> getOwnerNames() {
		return ownerNames;
	}

	public void setOwnerNames(List<String> ownerNames) {
		this.ownerNames = ownerNames;
	}

	public String getTransactionResult() {
		return transactionResult;
	}

	public void setTransactionResult(String transactionResult) {
		this.transactionResult = transactionResult;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public List<Bike> getBikes() {
		return bikes;
	}

	public void setBikes(List<Bike> bikes) {
		this.bikes = bikes;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public HtmlDataTable getDatatableCars() {
		return datatableCars;
	}

	public void setDatatableCars(HtmlDataTable datatableCars) {
		this.datatableCars = datatableCars;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}
