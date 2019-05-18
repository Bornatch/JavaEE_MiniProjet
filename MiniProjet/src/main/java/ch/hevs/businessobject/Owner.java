package ch.hevs.businessobject;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Owner")
public class Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name = "lastname")
	private String lastname;
	@Column(name = "firstname")
	private String firstname;
	@Column(name = "account")
	private double account;
	
	// relation
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)//@JoinColumn(name = "FK_CLIENT")
	private List<Car> cars;
	
	// id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	//accounts (on the platform site
	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}

	// cars (from owner)
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	// moethods for persistence test
	

	//constructors	
	
	public Owner(){
		
	}
	
	public Owner(String firstname, String lastname, double amount ) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.account = amount;
	}
	
	@Override
	public String toString() {
		String result = id + "-" + lastname + "-" + firstname;
		return result;
	}
	
	
}
