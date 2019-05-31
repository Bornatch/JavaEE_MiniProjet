package ch.hevs.businessobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Bike")
public class Bike extends Vehicule {

	@Column(name = "category")
	private String category;

	// relations
	@ManyToOne
	@JoinColumn(name = "FK_OWNER")
	private Owner owner;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	// constructors
	public Bike() {
		super();
	};

	public Bike(String brand, int km, String color, double price, String category, Owner owner) {
		super();
		this.owner = owner;
	}
}