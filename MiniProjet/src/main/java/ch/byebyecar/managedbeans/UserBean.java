package ch.byebyecar.managedbeans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ch.byebyecar.businessobject.Address;
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
	private String password;
	private String firstname;
	private String lastname;
	private String street;
	private String code;
	private String city;
	private double amount;
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

		// get usernames
		List<User> userList = platform.getUsers();
		this.usernames = new ArrayList<String>();
		if (userList.isEmpty() == true)
			usernames.add("Aucun utilisateur existant...");
		else {
			for (User u : userList) {
				this.usernames.add(u.getUsername());
			}
		}
	}

	// create a user
	public String createUser() {
		for (String str : usernames) {
			if (str.equals(username))
				return "userError";
		}

		try {
			User u = new User();
			Address a = new Address();

			u.setUsername(username);
			u.setPassword(password);
			u.setFirstname(firstname);
			u.setLastname(lastname);

			a.setStreet(street);
			a.setCode(code);
			a.setCity(city);

			u.setAddress(a);
			u.setAccount(0);
			this.user = u;

			platform.createUser(username, password, firstname, lastname, street, code, city);
			this.result = "succès !";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showUserResult";
	}

	// update user
	public String updateUser() {
		try {
			// User u = new User();
			// Address a = new Address();
			// u.setUsername(username);
			// u.setPassword(password);
			// u.setFirstname(firstname);
			// u.setLastname(lastname);
			// a.setStreet(street);
			// a.setCode(code);
			// a.setCity(city);
			// u.setAddress(a);
			// u.setAccount(0);

			this.setPassword(password);
			double soldeNew = this.account + amount;

			platform.updateOwner(this.user, password, soldeNew);
			this.account = soldeNew;

			this.result = "succès !";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showOwnerResult";
	}

	// update method
	public void updateUser(ValueChangeEvent event) throws Exception {
		this.username = (String) event.getNewValue();
		this.user = platform.getUserByUsername(this.username);

		this.password = user.getPassword();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.account = user.getAccount();
		this.street = user.getAddress().getStreet();
		this.code = user.getAddress().getCode();
		this.city = user.getAddress().getCity();
	}

	// getters and setters
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public List<User> getUsers() {
		return owners;
	}

	public void setUsers(List<User> owners) {
		this.owners = owners;
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

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
}
