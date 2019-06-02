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

	@Column(name="doors")
	private int doors;
	

	// doors
	public int getDoors() { return doors; }
	public void setDoors(int doors) { this.doors = doors; }


	// constructors
	public Car() {
		super();
	}
	
	public Car(String brand, int km, String color, double price, User owner, int doors) {
		super(brand, km, color, price, owner);
		this.doors = doors;
	}
}
