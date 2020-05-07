package ca.uwo.proxies;


import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;
import java.util.Scanner;

/**
 * @author kkontog, ktsiouni, mgrigori
 * This is one concrete implementation of {@link ca.uwo.proxies.Proxy} base class, it is the first proxy
 * the {@link ca.uwo.client.Client} will encounter. If the request of client is not issued by this class, 
 * it is forwarded to the {@link ca.uwo.proxies.SupplierProxy}, then {@link ca.uwo.proxies.LowQuantityProxy}, 
 * lastly {@link ca.uwo.proxies.HighQuantityProxy}. The link between those proxies implements Chain of Responsibility 
 * design pattern.
 */
public class WelcomeProxy extends Proxy {
	
	private Proxy next;
	
	/**
	 * constructor for WelcomeProxy class.
	 */
	public WelcomeProxy() {
		
		next = new SupplierProxy();
		
	}
	
	public boolean authenticate(Buyer buyer)
	{
		String in = "";
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter password");
		in = input.next();
		
		if(in.equals(buyer.getPassword()))
			return true;
		
		else
			return false;
	}

	/* (non-Javadoc)
	 * @see ca.uwo.frontend.interfaces.FacadeCommands#placeOrder(java.util.Map, ca.uwo.client.Buyer)
	 */
	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
		
		if(authenticate(buyer) == true)
		{
			next.placeOrder(orderDetails, buyer);
		}
		
		else
		{
			System.out.println("Incorrect credentials...");
			System.exit(0);
		}
		
	}

	/* (non-Javadoc)
	 * @see ca.uwo.frontend.interfaces.FacadeCommands#restock(java.util.Map, ca.uwo.client.Supplier)
	 */
	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		Facade facade = new Facade();
		facade.restock(restockDetails, supplier);
	}

}
