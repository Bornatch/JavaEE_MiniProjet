package ch.hevs.businessobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Car")
public class Car extends Vehicule {

	@Column(name = "state")
	private String state;

	// relations
	@ManyToOne
	@JoinColumn(name = "FK_OWNER")
	private Owner owner;

	// state
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	// owner
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	// constructors
	public Car() {
		super();
	};

	public Car(String brand, int km, String color, double price, String State, Owner owner) {
		super();
		this.owner = owner;
	}
}
