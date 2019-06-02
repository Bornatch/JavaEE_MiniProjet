package ch.byebyecar.businessobject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/* Students : Valentin Bornatici & Montaine Burger
 * Class : 606_3
 * Project : June 2019
 */


@Entity
@Table(name="Bike")
public class Bike extends Vehicle {

	@Column(name="cylinder")
	private int cylinder;
	@Column(name="fuel")
	private String fuel;
	
	
	// cylinder
	public int getCylinder() { return cylinder; }
	public void setCylinder(int cylinder) { this.cylinder = cylinder; }
	
	// fuel
	public String getFuel() { return fuel; }
	public void setFuel(String fuel) { this.fuel = fuel; }
	
	
	// constructors
	public Bike() {
		super();
	}
	
	public Bike(String brand, int km, String color, double price, User owner, 
			int cylinder, String fuel) {
		super(brand, km, color, price, owner);
		this.cylinder = cylinder;
		this.fuel = fuel;
	}
}
