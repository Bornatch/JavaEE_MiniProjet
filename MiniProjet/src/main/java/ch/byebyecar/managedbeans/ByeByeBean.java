package ch.byebyecar.managedbeans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	private double amount;
	private boolean ascending;

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

	// update user
	public String updateUser() {
		try {
			this.result = platform.updateOwner(this.owner, owner.getPassword(), amount);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showOwnerResult";
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
				this.result = "Hé " + sourceOwnerName + ", tu comptais pas t'acheter ta propre voiture j'espère !";
			} else {
				this.result = platform.sellCar(sourceOwnerName, destinationOwnerName, car.getId());
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
				this.result = "Hé " + sourceOwnerName + ", tu comptais pas t'acheter ta propre moto j'espère !";
			} else {
				this.result = platform.sellBike(sourceOwnerName, destinationOwnerName, bike.getId());
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
		setOwner((User) platform.getUser(id));
		return "userInfo";
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

	// sort by price
	public String sortCarByPrice() {
		
		if(ascending == false){
			Collections.sort(cars, new Comparator<Car>() {

				@Override
				public int compare(Car o1, Car o2) {
					return Double.compare(o1.getPrice(), o2.getPrice());
				}
			});
			ascending = true;
		}else{
			Collections.sort(cars, new Comparator<Car>() {

				@Override
				public int compare(Car o1, Car o2) {
					return Double.compare(o2.getPrice(), o1.getPrice());
				}
			});
			ascending=false;
		}
		
		return null;
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
	
	// sort by price
		public String sortBikeByPrice() {
			if(ascending == false){
				Collections.sort(bikes, new Comparator<Bike>() {

					@Override
					public int compare(Bike o1, Bike o2) {
						return Double.compare(o1.getPrice(), o2.getPrice());
					}
				});
				ascending = true;
			}else{
				Collections.sort(bikes, new Comparator<Bike>() {

					@Override
					public int compare(Bike o1, Bike o2) {
						return Double.compare(o2.getPrice(), o1.getPrice());
					}
				});
				ascending=false;
			}

			
			return null;
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
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getSourceOwnerName() {
		return sourceOwnerName;
	}

	public void setSourceOwnerName(String sourceOwnerName) {
		this.sourceOwnerName = sourceOwnerName;
	}

	public void getDestinationOwnerName(String destinationOwnerName) {
		this.destinationOwnerName = destinationOwnerName;
	}

	public List<String> getUsernames() {
		return usernames;
	}

	public void setUsernames(List<String> usernames) {
		this.usernames = usernames;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public String getDestinationOwnerName() {
		return destinationOwnerName;
	}

	public void setDestinationOwnerName(String destinationOwnerName) {
		this.destinationOwnerName = destinationOwnerName;
	}

	public String getSuppression() {
		return suppression;
	}

	public void setSuppression(String suppression) {
		this.suppression = suppression;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}
}
