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
	@Column(name = "password")
	private String password;
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
	private List<Car> cars;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private List<Bike> bikes;

	// ID
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// username
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// password
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// firstname
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	// lastname
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	// account
	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}

	// list cars
	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	// list bike
	public List<Bike> getBikes() {
		return bikes;
	}

	public void setBikes(List<Bike> bikes) {
		this.bikes = bikes;
	}

	// address (from Address)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	// constructors
	public User() {
		cars = new ArrayList<Car>();
		bikes = new ArrayList<Bike>();
	}

	public User(String username, String password, String firstname, String lastname, double account, Address address) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.account = account;
		this.address = address;

		cars = new ArrayList<Car>();
		bikes = new ArrayList<Bike>();
	}

	// helper method
	public void addCar(Car v) {
		cars.add(v);
	}

	// helper method
	public void addBike(Bike v) {
		bikes.add(v);	
	}
}
