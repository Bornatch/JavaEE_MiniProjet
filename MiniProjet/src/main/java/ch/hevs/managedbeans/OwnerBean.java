package ch.hevs.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.bankservice.Platform;
import ch.hevs.businessobject.Owner;

//affichge des propriétaires, ajout et suppression
public class OwnerBean {
	
	private Owner owner;
	private String lastname;
	private String firstname;
	private double account;
	private List<Owner> owners;
    private List<String> ownerNames;
    private String transactionResult;
    private Platform platform;
    
    @PostConstruct
    public void initialize() throws NamingException{
    	
    	// use JNDI to inject reference to platform EJB
    	InitialContext ctx = new InitialContext();
    	platform = (Platform) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/PlatformBean!ch.hevs.bankservice.Platform");
    	
    	// add Owner
    	
    }	
    
    public void createOwner(){
    	
    	try{
    		Owner o = new Owner();
    		o.setFirstname(firstname);
    		o.setLastname(lastname);
    		//compte client initialisé à 0
    		o.setAccount(0);
    		
    		this.transactionResult ="Success!";
    		platform.createOwner(o);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
		}
    	
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
