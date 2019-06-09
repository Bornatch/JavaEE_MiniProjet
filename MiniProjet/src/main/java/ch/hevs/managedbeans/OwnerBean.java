package ch.hevs.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.bankservice.Platform;
import ch.hevs.businessobject.Owner;

//affichge des propriétaires, ajout et suppression
public class OwnerBean {

	private Owner owner;
	private String lastname;
	private String firstname;
	private String username;
	private String password;
	private double account;

	private List<Owner> owners;
	private List<String> ownerUsernames;
	private String ownerUsername;
	private String transactionResult;
	private Platform platform;

	@PostConstruct
	public void initialize() throws NamingException {

		// use JNDI to inject reference to platform EJB
		InitialContext ctx = new InitialContext();
		platform = (Platform) ctx
				.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/PlatformBean!ch.hevs.bankservice.Platform");

		// initialize xhtml Owner
		this.firstname = "Prénom";
		this.lastname = "Nom";
		this.username = "Nom d'utilisateur";
		this.password = "123456";
	}

	public String createOwner() {

		try {
			Owner o = new Owner();
			o.setFirstname(firstname);
			o.setLastname(lastname);
			o.setUsername(username);
			o.setPassword(password);
			// compte client initialisé à 0
			o.setAccount(0);

			platform.createOwner(firstname, lastname, username, password);

			this.transactionResult = "succès !";
			this.owner = o;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showOwnerResult";
	}

	// à voir si besoin
	public String updateOwner() {

		try {
			Owner o = new Owner();
			o.setFirstname(firstname);
			o.setLastname(lastname);
			o.setUsername(username);
			o.setPassword(password);
			// compte client initialisé à 0
			o.setAccount(0);

			platform.createOwner(firstname, lastname, username, password);

			this.transactionResult = "succès !";
			this.owner = o;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showOwnerResult";
	}

	public void updateOwner(ValueChangeEvent event) throws Exception {
		this.lastname = (String) event.getNewValue();

		this.owner = platform.getOwnerFromUsername(this.username);
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}

	public List<Owner> getOwners() {
		return owners;
	}

	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}

	public String getownerUsername() {
		return ownerUsername;
	}

	public void setownerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}

	public List<String> getOwnerNames() {
		return ownerUsernames;
	}

	public void setOwnerNames(List<String> ownerUsername) {
		this.ownerUsernames = ownerUsername;
	}

	public String getTransactionResult() {
		return transactionResult;
	}

	public void setTransactionResult(String transactionResult) {
		this.transactionResult = transactionResult;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

}
