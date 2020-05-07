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
	
	public LowQuantityOrderProxy()
	{
		next = new HighQuantityOrderProxy();
	}

	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
		
		int quantity = -1;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("driver_file")));
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineTokens = line.split("\t");
				if (lineTokens[0].equals("StrategyChange")) {
					// Uncomment the following lines when restock strategies are implemented
					
					//RestockStrategy strategy = RestockStrategyFactory.create(lineTokens[1]);
					//StockManager.getInstance().setRestockStrategy(strategy);
				} else {
					//Integer buyerId = Integer.parseInt(lineTokens[0]);
					Map<String, Integer> orderItems = new HashMap<String, Integer>();
					for (int i = 1; i < lineTokens.length - 1; i += 2) {
						orderItems.put(lineTokens[i], Integer.parseInt(lineTokens[i + 1]));
						quantity += Integer.parseInt(lineTokens[i+1]);
					}
				}

			}
			br.close();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
		
		if(quantity < 10)
		{
			Facade facade = new Facade();
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
