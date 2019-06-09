package ch.byebyecar.managedbeans;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ch.byebyecar.businessobject.User;
import ch.byebyecar.businessobject.Vehicle;
import ch.byebyecar.service.Platform;

/* Students : Valentin Bornatici & Montaine Burger
 * Class : 606_3
 * Project : June 2019
 */


public class AdminBean {
	
	private List<User> users;
	private List<String> userNames;
	private String username;
	private List<Vehicle> vehicles;
	private List<String> vehicleBrands;
	private String brand;
	private Platform platform;
	
	
	@PostConstruct
	public void initialize() throws NamingException {
		
		// use JNDI to inject reference to platform EJB
		InitialContext ctx = new InitialContext();
		//platform = (Platform) ctx.lookup("");
		platform = (Platform) ctx
				.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/PlatformBean!ch.hevs.bankservice.Platform");
		
		// initialize variables
		this.username = "pseudo vide";
		this.brand = "marque vide";
	}
	
	
	// getters and setters
	public List<User> getUsers() { return users; }
	public void setUsers(List<User> users) { this.users = users; }
	
	public List<String> getUserNames() { return userNames; }
	public void setUserNames(List<String> userNames) { this.userNames = userNames; }
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	
	public List<Vehicle> getVehicles() { return vehicles; }
	public void setVehicles(List<Vehicle> vehicles) { this.vehicles = vehicles; }
	
	public List<String> getVehicleBrands() { return vehicleBrands; }
	public void setVehicleBrands(List<String> vehicleBrands) { this.vehicleBrands = vehicleBrands; }
	
	public String getBrand() { return brand; }
	public void setBrand(String brand) { this.brand = brand; }
	
	public Platform getPlatform() { return platform; }
	public void setPlatform(Platform platform) { this.platform = platform; }	
}
