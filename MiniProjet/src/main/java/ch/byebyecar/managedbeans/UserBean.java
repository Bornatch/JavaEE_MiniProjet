package ch.byebyecar.managedbeans;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ch.byebyecar.businessobject.User;
import ch.byebyecar.service.Platform;

/* Students : Valentin Bornatici & Montaine Burger
 * Class : 606_3
 * Project : June 2019
 */


public class UserBean {
	
	// add, delete and display users
	private User user;
	private String lastname;
	private String firstname;
	private double account;
	private List<User> owners;
	private List<String> ownerNames;
	private String result;
	private Platform platform;
	
	
	@PostConstruct
	public void initialize() throws NamingException {
		
		// use JNDI to inject reference to platform EJB
		InitialContext ctx = new InitialContext();
		// platform = (Platform) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/PlatformBean!ch.hevs.bankservice.Platform");
		platform = (Platform) ctx.lookup("");
		
		// initialize the variables
		this.firstname = "prénom";
		this.lastname = "nom";
	}
	
	
	// create a user
	public String createUser() {
		try {
			User u = new User();
			u.setFirstname(firstname);
			u.setLastname(lastname);
			
			// account initialized at 0
			u.setAccount(0);
			
			this.result = "Succès !";
			this.user = u;
			platform.createUser(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "showUserResult";
	}
	
	
	// update method
    public void updateUser(ValueChangeEvent event) throws Exception {
		this.lastname = (String) event.getNewValue();
		this.user = platform.getUserByLastname(this.lastname);
	}
    
    
    // getters and setters
	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }
	
	public String getLastname() { return lastname; }
	public void setLastname(String lastname) { this.lastname = lastname; }
	
    public String getFirstname() { return firstname; }
	public void setFirstname(String firstname) { this.firstname = firstname; }
	
	public double getAccount() { return account; }
	public void setAccount(double account) { this.account = account; }
	
	public List<User> getUsers() { return owners; }
	public void setUsers(List<User> owners) { this.owners = owners; }
	
	public List<String> getOwnerNames() { return ownerNames; }
	public void setOwnerNames(List<String> ownerNames) { this.ownerNames = ownerNames; }
	
	public String getResult() { return result; }
	public void setResult(String result) { this.result = result; }
	
	public Platform getPlatform() { return platform; }
	public void setPlatform(Platform platform) { this.platform = platform; }
}
