package ca.uwo.model.item.states;

public class ItemStateFactory {
	
	public static ItemState create (int quantity)
	{
		if(quantity == 0)
		{
			return new OutofStockState();
		}
		
		else if(quantity < 10 && quantity > 0)
		{
			return new LowStockState();
		}
		
		return new InStockState();
	}

}
