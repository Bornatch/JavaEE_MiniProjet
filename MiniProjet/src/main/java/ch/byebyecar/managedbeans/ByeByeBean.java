package ch.byebyecar.managedbeans;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
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
	
	// data from database
	private HtmlDataTable datatable;
	
	
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
	
	
	public String performSelling() {
		try {
			if (sourceOwnerName.equals(destinationOwnerName)) {
				this.result = "Erreur : les propriétaires sont identiques !";
			} /*
			 * else {
			 * 
			 * Car carSrc = bank.getAccount(sourceAccountDescription,
			 * sourceClientName); Account compteDest =
			 * bank.getAccount(destinationAccountDescription,
			 * destinationClientName);
			 * 
			 * // Transfer platform.sellCar(carSrc, newOwner);
			 * this.transactionResult="Success!"; }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "showTransferResult";
	}
	
	
	// initialization
	public String getCarFromList() {
		setCar((Car) datatable.getRowData());
		return "carInfo";
	}
	
	public String getCarList() {
		return "carList";
	}
	
	
	// getters and setters
	public List<User> getUsers() { return users; }
	public void setUsers(List<User> users) { this.users = users; }
	
	public List<String> getUsernames() { return usernames; }
	public void setUsernames(List<String> usernames) { this.usernames = usernames; }
	
	public String getResult() { return result; }
	public void setResult(String result) { this.result = result; }
	
	public String getSourceOwnerName() { return sourceOwnerName; }
	public void setSourceOwnerName(String sourceOwnerName) { this.sourceOwnerName = sourceOwnerName; }
	
	public String getDestinationOwnerName() { return destinationOwnerName; }
	public void getDestinationOwnerName(String destinationOwnerName) { this.destinationOwnerName = destinationOwnerName; }
	
	public double getPrice() { return price; }
	public void setPrice(double price) { this.price = price; }
	
	public Platform getPlatform() { return platform; }
	public void setPlatform(Platform platform) { this.platform = platform; }

	public List<Car> getCars() { return cars; }
	public void setCars(List<Car> cars) { this.cars = cars; }

	public Car getCar() { return car; }
	public void setCar(Car car) { this.car = car; }
	
	public List<Bike> getBikes() { return bikes; }
	public void setBikes(List<Bike> bikes) { this.bikes = bikes; }

	public Bike getBike() { return bike; }
	public void setBike(Bike bike) { this.bike = bike; }

	public HtmlDataTable getDatatable() { return datatable; }
	public void setDatatable(HtmlDataTable datatable) { this.datatable = datatable; }
}
