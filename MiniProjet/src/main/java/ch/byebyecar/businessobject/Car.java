package ch.byebyecar.businessobject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/* Students : Valentin Bornatici & Montaine Burger
 * Class : 606_3
 * Project : June 2019
 */


@Entity
@Table(name="Car")
public class Car extends Vehicle {

	@Column(name="state")
	private String state;
	

	// state
	public String getState() { return state; }
	public void setState(String state) { this.state = state; }


	// constructors
	public Car() {
		super();
	}
	
	public Car(String brand, int km, String color, double price, User owner, String state) {
		super(brand, km, color, price, owner);
		this.state = state;
	}
}
