package ch.byebyecar.managedbeans;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ch.byebyecar.businessobject.Bike;
import ch.byebyecar.businessobject.User;
import ch.byebyecar.service.Platform;

/* Students : Valentin Bornatici & Montaine Burger
 * Class : 606_3
 * Project : June 2019
 */


public class BikeBean {
	
	private String brand;
	private int km;
	private String color;
	private double price;
	private User owner;
	private String category;
		
	// define the categories
	private String[] categories = {"Sportive", "Custom", "Enduro",
			"Trial", "Cross", "Routière"};
	
	// sale
	private List<String> usernames;
	private String username;
	private String result;
	private Platform platform;
	
	
	@PostConstruct
	public void initialize() throws NamingException {
		
		// use JNDI to inject reference to platform EJB
		InitialContext ctx = new InitialContext();
		platform = (Platform) ctx
				.lookup("java:global/BYEBYE-0.0.1-SNAPSHOT/PlatformBean!ch.byebyecar.service.Platform");
		
		// get users
		List<User> userList = platform.getUsers();
		this.usernames = new ArrayList<String>();		
		for (User user: userList) {
			this.usernames.add(user.getUsername());
		}
		
//		if (ownerList.isEmpty() == true) {
//			ownerUsernames.add("ca marche presque ... ");
//		} else {
//			for (Owner o : ownerList) {
//				this.ownerUsernames.add(o.getUsername());
//			}
//		}
		
		// initialize variables
		this.brand = "marque";
		this.color = "couleur";
		this.username = "sélectionner";
	}
	
	
	// create a bike
	public String createBike() {
		try {
			platform.createBike(brand, km, color, price, owner, category);
			this.result = "Succès !";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "showBikeResult";
	}

	
	// methods to update
	public void updateCategory(ValueChangeEvent event) throws Exception {
		this.category = (String) event.getNewValue();
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
	
	public User getOwner() { return owner; }
	public void setOwner(User owner) { this.owner = owner; }
	
	public String getCategory() { return category; }
	public void setCategory(String category) { this.category = category; }
	
	public String[] getCategories() { return categories; }
	public void setCategories(String[] categories) { this.categories = categories; }
	
	public List<String> getUsernames() { return usernames; }
	public void setUsernames(List<String> usernames) { this.usernames = usernames; }
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }

	public String getResult() { return result; }
	public void setResult(String result) { this.result = result; }

	public Platform getPlatform() { return platform; }
	public void setPlatform(Platform platform) { this.platform = platform; }
}
