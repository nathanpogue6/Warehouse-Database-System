package ca.uwo.model.item.states;

public class ItemStateFactory {
	
	public ItemStateFactory()
	{
		
	}
	
	public static ItemState create (int quantity)
	{
		
		if(quantity < 10 && quantity > 0)
		{
			return new LowStockState();
		}
		
		else if(quantity >= 10)
		{
			return new InStockState();
		}
		
		
		else return new OutOfStockState();
	}

}
