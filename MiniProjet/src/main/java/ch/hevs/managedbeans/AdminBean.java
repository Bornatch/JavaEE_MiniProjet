package ch.hevs.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.bankservice.Platform;
import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.Owner;

public class AdminBean {

	// private List<Owner> owners;
	private List<Owner> owners;
	private List<String> ownerNames;
	private String ownerName;
	private List<Car> cars;
	private List<String> carBrands;
	private String carBrand;

	private Platform platform;

	@PostConstruct
	public void initialize() throws NamingException {

		// use JNDI to inject reference to platform EJB
		InitialContext ctx = new InitialContext();
		platform = (Platform) ctx
				.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/PlatformBean!ch.hevs.bankservice.Platform");

		// initialize xhtml Owner
		this.ownerName = "nom vide";
		this.carBrand = "marque vide";
	}

	public List<Owner> getOwners() {
		return owners;
	}

	public void setOwners(List<Owner> owners) {
		this.owners = owners;
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

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public List<String> getCarBrands() {
		return carBrands;
	}

	public void setCarBrands(List<String> carBrands) {
		this.carBrands = carBrands;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

}
