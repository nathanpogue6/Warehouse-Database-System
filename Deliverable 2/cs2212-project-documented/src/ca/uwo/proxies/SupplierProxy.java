package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class SupplierProxy extends Proxy {

	private Proxy next;
	
	public SupplierProxy()
	{
		next = new LowQuantityOrderProxy();
	}
	
	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
		next.placeOrder(orderDetails, buyer);
	}

	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		Facade facade = new Facade();
		facade.restock(restockDetails, supplier);
	}

}
