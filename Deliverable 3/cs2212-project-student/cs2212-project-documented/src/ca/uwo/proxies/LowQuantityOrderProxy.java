package ca.uwo.proxies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class LowQuantityOrderProxy extends Proxy {
	
	private Proxy next;
	
	private static LowQuantityOrderProxy instance = null;
	
	public static LowQuantityOrderProxy getInstance() {
		if (instance == null)
			instance = new LowQuantityOrderProxy();
		
		return instance;
	}
	
	private LowQuantityOrderProxy()
	{
		next = HighQuantityOrderProxy.getInstance();
	}

	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
		
		if(orderDetails.values().size() < 10)
		{
			Facade facade = Facade.getInstance();
			facade.placeOrder(orderDetails, buyer);
		}
		
		else
		{
			next.placeOrder(orderDetails, buyer);
		}
		
	}

	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		
	}

}
