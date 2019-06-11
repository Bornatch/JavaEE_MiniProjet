package ch.byebyecar.managedbeans;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ch.byebyecar.businessobject.Car;
import ch.byebyecar.businessobject.User;
import ch.byebyecar.service.Platform;

/* Students : Valentin Bornatici & Montaine Burger
 * Class : 606_3
 * Project : June 2019
 */


public class CarBean {
	
	private String brand;
	private int km;
	private String color;
	private double price;
	private String state;
	private User owner;
	
	// list of states
	private String[] states = {"Comme neuf", "Très bon état", "Bon état",
			"Etat correct"};
	
	private List<String> usernames;
	private String username;
	private List<Car> cars;
	private String result;
	private Platform platform;
	
	
	@PostConstruct
	public void initialize() throws NamingException {
		
		// use JNDI to inject reference to platform EJB
		InitialContext ctx = new InitialContext();
		platform = (Platform) ctx
				.lookup("java:global/BYEBYE-0.0.1-SNAPSHOT/PlatformBean!ch.byebyecar.service.Platform");
		
		// get owners
		List<User> ownerList = platform.getUsers();
		this.usernames = new ArrayList<String>();
		if (ownerList.isEmpty() == true)
			usernames.add("Aucun utilisateur existant...");
		else {
			for (User u: ownerList) {
				this.usernames.add(u.getUsername());
			}
		}
		
		// get cars
		try {
			cars = platform.getCars();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// initialize variables
		this.username = "Sélectionner";		
	}
	
	
	// create a car
	public String createCar() {
		try {
			platform.createCar(brand, km, color, price, owner, state);
			this.result = "succès !";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "showCarResult";
	}
	
	
	// update methods
	public void updateState(ValueChangeEvent event) throws Exception {
		this.state = (String) event.getNewValue();
	}
	
	public void updateOwner(ValueChangeEvent event) throws Exception {
		this.username = (String) event.getNewValue();
		this.owner = platform.getUserByUsername(this.username);
	}

	
	// getters and setters
	public String getBrand() { return brand; }
	public void setBrand(String brand) { this.brand = brand; }
	
	public int getKm() { return km; }
	public void setKm(int km) { this.km = km; }

	public String getColor() { return color; }
	public void setColor(String color) { this.color = color; }
	
	public double getPrice() { return price; }
	public void setPrice(double price) { this.price = price; }
	
	public String getState() { return state; }
	public void setState(String state) { this.state = state; }
	
	public User getOwner() { return owner; }
	public void setOwner(User owner) { this.owner = owner; }
	
	public String[] getStates() { return states; }
	public void setStates(String[] states) { this.states = states; }

	public List<String> getUsernames() { return usernames; }
	public void setUsernames(List<String> usernames) { this.usernames = usernames; }

	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }

	public List<Car> getCars() { return cars; }
	public void setCars(List<Car> cars) { this.cars = cars; }

	public String getResult() {	return result; }
	public void setResult(String result) { this.result = result; }
	
	public Platform getPlatform() {	return platform; }
	public void setPlatform(Platform platform) { this.platform = platform; }
}
