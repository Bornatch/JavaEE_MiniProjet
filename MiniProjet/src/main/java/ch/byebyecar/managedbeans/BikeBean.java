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
	private List<String> ownerNames;
	private String ownerName;
	private String result;
	private Platform platform;
	
	
	@PostConstruct
	public void initialize() throws NamingException {
		
		// use JNDI to inject reference to platform EJB
		InitialContext ctx = new InitialContext();
		//platform = (Platform) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/PlatformBean!ch.hevs.bankservice.Platform");
		platform = (Platform) ctx.lookup("");
		
		// get users
		List<User> userList = platform.getUsers();
		this.ownerNames = new ArrayList<String>();		
		for (User user: userList) {
			this.ownerNames.add(user.getLastname());
		}
		
//		if (ownerList.isEmpty() == true) {
//			ownerNames.add("ca marche presque ...");
//		} else {
//			for (Owner o : ownerList) {
//				this.ownerNames.add(o.getLastname());
//			}
//		}
		
		// initialize variables
		this.brand = "marque";
		this.color = "couleur";
		this.ownerName = "sélectionner";
	}
	
	
	// create a bike
	public String createBike() {
		try {
			Bike b = new Bike();
			b.setBrand(brand);
			b.setKm(km);
			b.setColor(color);
			b.setPrice(price);
			b.setCategory(category);
			b.setOwner(owner);
			
			this.result = "Succès !";
			platform.createBike(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "showVehicleResult";
	}
	
	
	// methods to update
	public void updateCategory(ValueChangeEvent event) throws Exception {
		this.category = (String) event.getNewValue();
	}
	
	public void updateOwner(ValueChangeEvent event) throws Exception {
		this.ownerName = (String) event.getNewValue();
		this.owner = platform.getUserByLastname(this.ownerName);
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
	
	public List<String> getOwnerNames() { return ownerNames; }
	public void setOwnerNames(List<String> ownerNames) { this.ownerNames = ownerNames; }
	
	public String getOwnerName() { return ownerName; }
	public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

	public String getResult() { return result; }
	public void setResult(String result) { this.result = result; }

	public Platform getPlatform() { return platform; }
	public void setPlatform(Platform platform) { this.platform = platform; }
}
