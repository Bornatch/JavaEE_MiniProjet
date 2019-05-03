package ch.hevs.businessobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Platform")
public class Platform {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name="nom")
	private String name;

	// relation
	
	// id
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		
	// nom
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
