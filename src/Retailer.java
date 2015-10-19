

import java.util.HashMap;

public class Retailer {
	private String name;
	private HashMap<String,RetialerItem> retailerItems;
	
	public Retailer(String name, HashMap<String,RetialerItem> retailerItems) {
		this.name=name;
		this.retailerItems = retailerItems;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, RetialerItem> getRetailerItems() {
		return retailerItems;
	}

	public void setRetailerItems(HashMap<String, RetialerItem> retailerItems) {
		this.retailerItems = retailerItems;
	}
	

}
