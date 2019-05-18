package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.bankservice.Bank;
import ch.hevs.bankservice.Platform;
import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.Client;
import ch.hevs.businessobject.Owner;

public class PlatformBean {
	
	 	private List<Owner> owner;
	    private List<String> ownerNames;
	    private String transactionResult;
	    private String sourceOwnerName;
	    private String destinationOwnerName;
	    private double price;
	    private Platform platform;
	    
	    @PostConstruct
	    public void initialize() throws Exception {
	    	
	    	// use JNDI to inject reference to bank EJB
	    	InitialContext ctx = new InitialContext();
			platform = (Platform) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/PlatformBean!ch.hevs.bankservice.Platform");    
			
			// get clients
			List<Owner> ownerList = platform.getOwners();
			this.ownerNames = new ArrayList<String>();
			for (Owner owner : ownerList) {
				this.ownerNames.add(owner.getLastname());
			}
						
	    }
	    
	    public String performSelling() {
	    	
	    	try {
				
				if (sourceOwnerName.equals(destinationOwnerName)) {
					
					this.transactionResult="Error: Owner are identical!";
				} /*
				else {
					
										
					Car carSrc = bank.getAccount(sourceAccountDescription, sourceClientName);
					Account compteDest = bank.getAccount(destinationAccountDescription, destinationClientName);
		
					// Transfer
					platform.sellCar(carSrc, newOwner);
					this.transactionResult="Success!";
				}*/
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}

			return "showTransferResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
		} 

		public List<Owner> getOwner() {
			return owner;
		}

		public void setOwner(List<Owner> owner) {
			this.owner = owner;
		}

		public List<String> getOwnerNames() {
			return ownerNames;
		}

		public void setOwnerNames(List<String> ownerNames) {
			this.ownerNames = ownerNames;
		}

		public String getTransactionResult() {
			return transactionResult;
		}

		public void setTransactionResult(String transactionResult) {
			this.transactionResult = transactionResult;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public Platform getPlatform() {
			return platform;
		}

		public void setPlatform(Platform platform) {
			this.platform = platform;
		}

}
