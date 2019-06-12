package ch.byebyecar.managedbeans;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import ch.byebyecar.businessobject.Bike;
import ch.byebyecar.businessobject.Car;
import ch.byebyecar.businessobject.User;
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
	private User user;
	private String result;
	private String sourceOwnerName;
	private String destinationOwnerName;
	private String suppression;
	private double price;
	private Platform platform;

	private List<Car> cars;
	private Car car;
	private List<Bike> bikes;
	private Bike bike;

	
	@PostConstruct
	public void initialize() throws Exception {

		// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		platform = (Platform) ctx
				.lookup("java:global/BYEBYE-0.0.1-SNAPSHOT/PlatformBean!ch.byebyecar.service.Platform");

		// get users
		updateUsers();

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

	
	// update users list
	public void updateUsers() {
		List<User> userList = platform.getUsers();
		users = userList;
		this.usernames = new ArrayList<String>();
		for (User user : userList) {
			this.usernames.add(user.getUsername());
		}
	}
	
	// delete user
	public String deleteUser(Long id) {
		suppression = platform.deleteUser(id);
		return "showSuppressionResult";
	}
	
	
	// selling methods
	public String sellCar() {
		try {
			sourceOwnerName = car.getOwner().getUsername();

			if (sourceOwnerName.equals(destinationOwnerName)) {
				this.result = "Erreur : les utilisateurs sont identiques !";
			} else {
				platform.sellCar(sourceOwnerName, destinationOwnerName, car.getId());
				this.result = " succ�s !";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showSellResult";
	}
		
	public String sellBike() {
		try {
			sourceOwnerName = bike.getOwner().getUsername();

			if (sourceOwnerName.equals(destinationOwnerName)) {
				this.result = "Erreur : les utilisateurs sont identiques !";
			} else {
				platform.sellBike(sourceOwnerName, destinationOwnerName, bike.getId());
				this.result = " succ�s !";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showSellResult";
	}

	
	// get data
	public String getCarFromList(Long id) {
		setCar((Car) platform.getCarById(id));		
		return "carInfo";
	}
	
	public String getBikeFromList(Long id) {
		setBike((Bike) platform.getBikeById(id));		
		return "bikeInfo";
	}
	
	public String getUserFromList(Long id) {
		setUser((User) platform.getUser(id));
		return "userDetails";
	}
	
	public String carList() {
		updateUsers();
		
		try {
			cars = platform.getCars();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "carList";		
	}
	
	public String bikeList() {
		updateUsers();
		
		try {
			bikes = platform.getBikes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "bikeList";
	}
	
	public String userList() {
		updateUsers();		
		return "userList";
	}
	
	public void updateListOwner(ValueChangeEvent event) throws Exception {
		this.destinationOwnerName = (String) event.getNewValue();
		this.setOwner(platform.getUserByUsername(this.destinationOwnerName));
	}


	// initialization
	public String getCarList() {
		return "carList";
	}

	public String getBikeList() {
		return "bikeList";
	}

	
	// getters and setters	
	public List<User> getUsers() { return users; }
	public void setUsers(List<User> users) { this.users = users; }

	public List<String> getUsernames() { return usernames; }
	public void setUsernames(List<String> usernames) { this.usernames = usernames; }
	
	public User getOwner() { return owner; }
	public void setOwner(User owner) { this.owner = owner; } 
	
	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; } 
	
	public String getResult() { return result; }
	public void setResult(String result) { this.result = result; }

	public String getSourceOwnerName() { return sourceOwnerName; } 
	public void setSourceOwnerName(String sourceOwnerName) { this.sourceOwnerName = sourceOwnerName; }

	public String getDestinationOwnerName() { return destinationOwnerName; }
	public void setDestinationOwnerName(String destinationOwnerName) { this.destinationOwnerName = destinationOwnerName; }

	public String getSuppression() { return suppression; }
	public void setSuppression(String suppression) { this.suppression = suppression; }
	
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
}
