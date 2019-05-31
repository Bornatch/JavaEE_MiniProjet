package ch.hevs.businessobject;

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

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Vehicule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(name = "brand")
	private String brand;

	@Column(name = "km")
	private int km;

	@Column(name = "color")
	private String color;

	@Column(name = "price")
	private double price;

	// id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	// brand
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	// methods
	public void drive(int km){
		this.km += km;
	}	
	
	//constructors 
	public Vehicule(){};
	
	public Vehicule(String brand, int km, String color, double price, Owner owner) {
		super();
		this.brand = brand;
		this.km = km;
		this.color = color;
		this.price = price;
	}
}

