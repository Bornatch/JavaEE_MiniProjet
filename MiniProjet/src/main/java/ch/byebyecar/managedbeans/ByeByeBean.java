package ch.byebyecar.managedbeans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import ch.byebyecar.businessobject.Bike;
import ch.byebyecar.businessobject.Car;
import ch.byebyecar.businessobject.User;
import ch.byebyecar.businessobject.Vehicle;
import ch.byebyecar.service.Platform;

/* Students : Valentin Bornatici & Montaine Burger
 * Class : 606_3
 * Project : June 2019
 */

@RequestScoped
public class ByeByeBean {

	private List<User> users;
	private List<String> usernames;
	private User owner;
	private String result;
	private String sourceOwnerName;
	private String destinationOwnerName;
	private double price;
	private Platform platform;

	private List<Car> cars;
	private Car car;
	private List<Bike> bikes;
	private Bike bike;
	private List<Vehicle> vehicles;
	private Vehicle vehicle;

	// data from database
	private HtmlDataTable datatableCars;
	private HtmlDataTable datatableBikes;
	private HtmlDataTable datatableVehicles;

	@PostConstruct
	public void initialize() throws Exception {

		// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		platform = (Platform) ctx
				.lookup("java:global/BYEBYE-0.0.1-SNAPSHOT/PlatformBean!ch.byebyecar.service.Platform");

		// get users
		List<User> userList = platform.getUsers();
		this.usernames = new ArrayList<String>();
		for (User user : userList) {
			this.usernames.add(user.getUsername());
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

		// get vehicles
		try {
			vehicles = platform.getVehicles();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String sellCar() {
		try {
			sourceOwnerName = car.getOwner().getUsername();

			if (sourceOwnerName.equals(destinationOwnerName)) {
				this.result = "Error: Owners are identical!";
			} else {

				// Vehicule car = platform.getCar(car.getId());
				// Account compteDest =
				// bank.getAccount(destinationAccountDescription,
				// destinationClientName);

				platform.sellCar(sourceOwnerName, destinationOwnerName, car.getId());
				this.result = " succès !";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showSellResult"; // the String value returned represents the
									// outcome used by the navigation
									// handler to determine what page to
									// display next.
	}
	
	public String sellBike() {
		try {
			sourceOwnerName = bike.getOwner().getUsername();

			if (sourceOwnerName.equals(destinationOwnerName)) {
				this.result = "Error: Owners are identical!";
			} else {

				// Vehicule car = platform.getCar(car.getId());
				// Account compteDest =
				// bank.getAccount(destinationAccountDescription,
				// destinationClientName);

				platform.sellCar(sourceOwnerName, destinationOwnerName, bike.getId());
				this.result = " succès !";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showSellResult"; // the String value returned represents the
									// outcome used by the navigation
									// handler to determine what page to
									// display next.
	}

	public void updateListOwner(ValueChangeEvent event) throws Exception {
		this.destinationOwnerName = (String) event.getNewValue();
		this.setOwner(platform.getUserByUsername(this.destinationOwnerName));
	}

	public String getCarFromList() {
		setCar((Car) datatableCars.getRowData());
		return "carInfo";
	}

	public String getBikeFromList() {
		setBike((Bike) datatableBikes.getRowData());
		return "bikeInfo";
	}

	public String getVehicleFromList() {
		setVehicle((Vehicle) datatableVehicles.getRowData());
		return "vehicleInfo";
	}

	// initialization
	public String getCarList() {
		return "carList";
	}

	public String getBikeList() {
		return "bikeList";
	}

	public String getVehicleList() {
		return "vehicleList";
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	// getters and setters
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<String> getUsernames() {
		return usernames;
	}

	public void setUsernames(List<String> usernames) {
		this.usernames = usernames;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSourceOwnerName() {
		return sourceOwnerName;
	}

	public void setSourceOwnerName(String sourceOwnerName) {
		this.sourceOwnerName = sourceOwnerName;
	}

	public String getDestinationOwnerName() {
		return destinationOwnerName;
	}

	public void getDestinationOwnerName(String destinationOwnerName) {
		this.destinationOwnerName = destinationOwnerName;
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

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public List<Bike> getBikes() {
		return bikes;
	}

	public void setBikes(List<Bike> bikes) {
		this.bikes = bikes;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Vehicle getVehicle() {
		return car;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public HtmlDataTable getDatatableCars() {
		return datatableCars;
	}

	public void setDatatableCars(HtmlDataTable datatableCars) {
		this.datatableCars = datatableCars;
	}

	public HtmlDataTable getDatatableBikes() {
		return datatableBikes;
	}

	public void setDatatableBikes(HtmlDataTable datatableBikes) {
		this.datatableBikes = datatableBikes;
	}

	public HtmlDataTable getDatatableVehicles() {
		return datatableVehicles;
	}

	public void setDatatableVehicles(HtmlDataTable datatableVehicles) {
		this.datatableVehicles = datatableVehicles;
	}
}
