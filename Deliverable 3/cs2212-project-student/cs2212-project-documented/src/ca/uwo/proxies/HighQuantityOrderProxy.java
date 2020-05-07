package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class HighQuantityOrderProxy extends Proxy {
	
	private static HighQuantityOrderProxy instance = null;
	
	public static HighQuantityOrderProxy getInstance() {
		if (instance == null)
			instance = new HighQuantityOrderProxy();
		
		return instance;
	}
	
	private HighQuantityOrderProxy()
	{
		
	}

	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
		Facade facade = Facade.getInstance();
		facade.placeOrder(orderDetails, buyer);
	}

	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		
	}

}
