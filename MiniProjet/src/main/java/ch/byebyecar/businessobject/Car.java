package ch.byebyecar.businessobject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/* Students : Valentin Bornatici & Montaine Burger
 * Class : 606_3
 * Project : June 2019
 */


@Entity
@Table(name = "Car")
public class Car extends Vehicle {

	@Column(name = "state")
	private String state;

	// relations
	@ManyToOne
	@JoinColumn(name = "FK_OWNER")
	private User owner;

	
	// state
	public String getState() { return state; }
	public void setState(String state) { this.state = state; }

	// owner (from User)
	public User getOwner() { return owner; }
	public void setOwner(User owner) { this.owner = owner; }

	
	// constructors
	public Car() {
		super();
	}

	public Car(String brand, int km, String color, double price, String state, User owner) {
		super(brand, km, color, price);
		this.state = state;
		this.owner = owner;
	}
}
