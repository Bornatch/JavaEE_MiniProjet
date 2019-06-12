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
@Table(name = "Bike")
public class Bike extends Vehicle {

	@Column(name = "category")
	private String category;

	// relations
	@ManyToOne
	@JoinColumn(name = "FK_OWNER")
	private User owner;

	
	// category
	public String getCategory() { return category; }
	public void setCategory(String category) { this.category = category; }

	// owner (from User)
	public User getOwner() { return owner; }
	public void setOwner(User owner) { this.owner = owner; }

	
	// constructors
	public Bike() {
		super();
	}

	public Bike(String brand, int km, String color, double price, User owner, String category) {
		super(brand, km, color, price);
		this.owner = owner;
		this.category = category;
	}
}
