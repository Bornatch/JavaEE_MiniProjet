package ch.byebyecar.businessobject;
import javax.persistence.Embeddable;

/* Students : Valentin Bornatici & Montaine Burger
 * Class : 606_3
 * Project : June 2019
 */


@Embeddable
public class Address {
	
	private String street;
	private String code;
	private String city;
	
	
	// street
	public String getStreet() { return street; }
	public void setStreet(String street) { this.street = street; }
	
	// code
	public String getCode() { return code; }
	public void setCode(String code) { this.code = code; }
	
	// city
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
	
	
	// constructors
	public Address() {}
	
	public Address(String street, String code, String city) {
		this.street = street;
		this.code = code;
		this.city = city;
	}
}
