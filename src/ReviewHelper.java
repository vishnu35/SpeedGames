

import java.util.HashMap;

public class ReviewHelper {
	public static String itemname,category,manufacture,rebate;
	public static double price;
	
	public ReviewHelper(String name,String type,String maker,String access) {
		
		if(type.equals("consoles")){
			Console console = null;
			if(maker.equals("microsoft")){
				console = ConsoleHashMap.microsoft.get(name);
			}
			else if(maker.equals("sony")){
				console = ConsoleHashMap.sony.get(name);
			}
			else if(maker.equals("nintendo")){
				console = ConsoleHashMap.nintendo.get(name);
			}else{
				HashMap<String, Console> hm = new HashMap<String, Console>();
				hm.putAll(ConsoleHashMap.microsoft);
				hm.putAll(ConsoleHashMap.sony);
				hm.putAll(ConsoleHashMap.nintendo);				
				console = hm.get(name);
			}
			itemname = console.getName();
			category = "Console";
			price = console.getPrice();
			manufacture = console.getRetailer();
			rebate = "Yes";
			
		}
		if(type.equals("games")){
			Game game = null;
			if(maker.equals("electronicArts")){
				game = GameHashMap.electronicArts.get(name);
			}
			else if(maker.equals("activision")){
				game = GameHashMap.activision.get(name);
			}
			else if(maker.equals("takeTwoInteractive")){
				game = GameHashMap.takeTwoInteractive.get(name);
			}else{
				HashMap<String, Game> hm = new HashMap<String, Game>();
				hm.putAll(GameHashMap.electronicArts);
				hm.putAll(GameHashMap.activision);
				hm.putAll(GameHashMap.takeTwoInteractive);				
				game = hm.get(name);
			}
			itemname = game.getName();
			category = "Game";
			price = game.getPrice();
			manufacture = game.getRetailer();
			rebate = "Yes";
		}
		
		if(type.equals("tablets")){
			Tablet tablet = null;
			if (maker.equals("apple")) {
				tablet = TabletHashMap.apple.get(name);
			} else if (maker.equals("microsoft")) {
				tablet = TabletHashMap.microsoft.get(name);
			} else if (maker.equals("samsung")) {
				tablet = TabletHashMap.samsung.get(name);
			}else{
				HashMap<String, Tablet> hm = new HashMap<String, Tablet>();
				hm.putAll(TabletHashMap.apple);
				hm.putAll(TabletHashMap.microsoft);
				hm.putAll(TabletHashMap.samsung);				
				tablet = hm.get(name);
			}
			itemname = tablet.getName();
			category = "Tablet";
			price = tablet.getPrice();
			manufacture = tablet.getRetailer();
			rebate = "Yes";
		}
		
		if(type.equals("accessories")){
			Console console = null;
			if(maker.equals("microsoft")){
				console = ConsoleHashMap.microsoft.get(access);
			}
			else if(maker.equals("sony")){
				console = ConsoleHashMap.sony.get(access);
			}
			else if(maker.equals("nintendo")){
				console = ConsoleHashMap.nintendo.get(access);
			}
			
			Accessory accessory = console.getAccessories().get(name); 
			itemname = accessory.getName();
			category = "Accessory";
			price = accessory.getPrice();
			manufacture = accessory.getRetailer();
			rebate = "Yes";
		}
	}
}
