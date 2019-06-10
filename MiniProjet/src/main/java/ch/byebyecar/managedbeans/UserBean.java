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
	private String username;
	private String lastname;
	private String firstname;
	private double account;
	private List<User> owners;
	private List<String> usernames;
	private String result;
	private Platform platform;
	
	
	@PostConstruct
	public void initialize() throws NamingException {
		
		// use JNDI to inject reference to platform EJB
		InitialContext ctx = new InitialContext();
		platform = (Platform) ctx
				.lookup("java:global/BYEBYE-0.0.1-SNAPSHOT/PlatformBean!ch.byebyecar.service.Platform");
		
		// initialize the variables
		this.username = "pseudo";
		this.firstname = "prénom";
		this.lastname = "nom";
	}
	
	
	// create a user
	public String createUser() {
		try {
			platform.createUser(username, firstname, lastname);			
			this.result = "Succès !";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "showUserResult";
	}
	
	
	// update method
    public void updateUser(ValueChangeEvent event) throws Exception {
		this.username = (String) event.getNewValue();
		this.user = platform.getUserByUsername(this.username);
	}
    
    
    // getters and setters
	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	
	public String getLastname() { return lastname; }
	public void setLastname(String lastname) { this.lastname = lastname; }
	
    public String getFirstname() { return firstname; }
	public void setFirstname(String firstname) { this.firstname = firstname; }
	
	public double getAccount() { return account; }
	public void setAccount(double account) { this.account = account; }
	
	public List<User> getUsers() { return owners; }
	public void setUsers(List<User> owners) { this.owners = owners; }
	
	public List<String> getUsernames() { return usernames; }
	public void setUsernames(List<String> usernames) { this.usernames = usernames; }
	
	public String getResult() { return result; }
	public void setResult(String result) { this.result = result; }
	
	public Platform getPlatform() { return platform; }
	public void setPlatform(Platform platform) { this.platform = platform; }
}
