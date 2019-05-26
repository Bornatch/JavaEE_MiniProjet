package ch.byebyecar.businessobject;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/* Students : Valentin Bornatici & Montaine Burger
 * Class : 606_3
 * Project : June 2019
 */


@Entity
@Table(name = "Vehicle")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name = "brand")
	private String brand;
	@Column(name = "km")
	private int km;
	@Column(name = "color")
	private String color;
	@Column(name = "price")
	private double price;
	
	
	// relations
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_OWNER")
	private User owner;
	

	// ID
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	// brand
	public String getBrand() { return brand; }
	public void setBrand(String brand) { this.brand = brand; }
	
	// km
	private int getKm() { return km; }
	private void setKm(int km) { this.km = km; }
	
	// color
	private String getColor() { return color; }
	private void setColor(String color) { this.color = color; }
	
	// price
	private double getPrice() { return price; }
	private void setPrice(double price) { this.price = price; }
	
	// owner (from User)
	public User getOwner() { return owner; }
	public void setOwner(User owner) { this.owner = owner; }
	

	// method
	public void drive(int km) {
		this.km += km;
	}
	
	
	// constructors
	public Vehicle() {}
	
	public Vehicle(String brand, int km, String color, double price, User owner) {
		this.brand = brand;
		this.km = km;
		this.color = color;
		this.price = price;
		this.owner = owner;
	}
}
