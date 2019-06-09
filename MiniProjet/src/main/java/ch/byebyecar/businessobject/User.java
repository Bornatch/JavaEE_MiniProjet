package ch.byebyecar.businessobject;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/* Students : Valentin Bornatici & Montaine Burger
 * Class : 606_3
 * Project : June 2019
 */


@Entity
@Table(name = "User")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(unique = true, name = "username")
	private String username;
	@Column(name = "firstname")
	private String firstname;
	@Column(name = "lastname")
	private String lastname;
	@Column(name = "account")
	private double account;
	
	
	// relations
	@Embedded
	private Address address;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private List<Vehicle> vehicles;
	

	// ID
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	// username
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	
	// firstname
	public String getFirstname() { return firstname; }
	public void setFirstname(String firstname) { this.firstname = firstname; }
	
	// lastname
	public String getLastname() { return lastname; }
	public void setLastname(String lastname) { this.lastname = lastname; }
	
	// account
	public double getAccount() { return account; }
	public void setAccount(double account) { this.account = account; }
	
	// vehicles (from User - owner)
	public List<Vehicle> getVehicles() { return vehicles; }
	public void setVehicles(List<Vehicle> vehicles) { this.vehicles = vehicles; }
	
	// address (from Address)
	public Address getAddress() { return address; }
	public void setAddress(Address address) { this.address = address; }
	
	
	// constructors
	public User() {
		vehicles = new ArrayList<Vehicle>();
	}
	
	public User(String firstname, String lastname, double account) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.account = account;
		
		vehicles = new ArrayList<Vehicle>();
	}
	
	
	// helper method
	public void addVehicle(Vehicle v) {
		vehicles.add(v);
	}
}
