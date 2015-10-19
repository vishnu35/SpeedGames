

public class RetialerItem {
	String itemName;
	String itemType;
	String itemMaker;
	String itemAcc;
	public RetialerItem(String itemName,String itemType,String itemMaker,String itemAcc) {
		this.itemType = itemType;
		this.itemName = itemName;
		this.itemMaker = itemMaker;
		this.itemAcc = itemAcc;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemMaker() {
		return itemMaker;
	}
	public void setItemMaker(String itemMaker) {
		this.itemMaker = itemMaker;
	}
	public String getItemAcc() {
		return itemAcc;
	}
	public void setItemAcc(String itemAcc) {
		this.itemAcc = itemAcc;
	}
	
}
