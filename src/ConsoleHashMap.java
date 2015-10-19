

import java.util.HashMap;

public class ConsoleHashMap{
	public static HashMap<String, Console> microsoft = new HashMap<String, Console>();
	public static HashMap<String, Console> sony = new HashMap<String, Console>();
	public static HashMap<String, Console> nintendo = new HashMap<String, Console>();
	
	public static String string_microsoft = "Microsoft";
	public static String string_sony = "Sony";
	public static String string_nintendo = "Nintendo";
	
	public ConsoleHashMap() {
		HashMap<String, Accessory> accessories;
		if(microsoft.isEmpty()){
			Accessory xboxone_wc = new Accessory("Wireless Controller", 11.00, "xboxone_wc.jpg", "Microsoft","New",10);
			Accessory xboxone_sh = new Accessory("Stereo Headset", 10.00, "xboxone_sh.jpg", "Microsoft","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("xboxone_wc", xboxone_wc);
			accessories.put("xboxone_sh", xboxone_sh);			
			Console xboxone = new Console("XBox One",10.00,"xboxone.png","Microsoft","New",10,accessories);
			microsoft.put("xboxone", xboxone);
			
			Accessory xbox360_mr = new Accessory("Media Remote", 12.00, "xbox360_mr.jpg", "Microsoft","New",10);
			Accessory xbox360_wa = new Accessory("Wireless Adapter", 13.00, "xbox360_wa.png", "Microsoft","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("xbox360_mr", xbox360_mr);
			accessories.put("xbox360_wa", xbox360_wa);
			Console xbox360 = new Console("XBox 360",10.00,"xbox360.png","Microsoft","New",10,accessories);			
			microsoft.put("xbox360", xbox360);
		}
		if(sony.isEmpty()){			
			Accessory ps3_wc = new Accessory("Wireless Controller", 14.00, "ps3_wc.jpg", "Sony","New",10);
			Accessory ps3_umr = new Accessory("Universal Media Remote", 15.00, "ps3_umr.jpg", "Sony","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("ps3_wc", ps3_wc);
			accessories.put("ps3_umr", ps3_umr);
			Console ps3 = new Console("PS3",13.50,"ps3.png","Sony","New",10,accessories);
			sony.put("ps3", ps3);
			
			Accessory ps4_wc = new Accessory("Camera", 14.00, "ps4_c.jpg", "Sony","New",10);
			Accessory ps4_cs = new Accessory("Charging System", 15.00, "ps4_cs.jpg", "Sony","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("ps4_wc", ps4_wc);
			accessories.put("ps4_cs", ps4_cs);
			Console ps4 = new Console("PS4",11.00,"ps4.png","Sony","New",10,accessories);
			sony.put("ps4", ps4);			
		}
		if(nintendo.isEmpty()){
			Accessory wii_cc = new Accessory("Classic Controller", 14.00, "wii_cc.jpg", "Nintendo","New",10);
			Accessory wii_cs = new Accessory("Charging System", 15.00, "wii_cs.jpg", "Nintendo","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("wii_cc", wii_cc);
			accessories.put("wii_cs", wii_cs);
			Console wii = new Console("Wii",14.00,"wii.png","Nintendo","New",10,accessories);
			nintendo.put("wii", wii);
						
			Accessory wiiu_ncw = new Accessory("Nunchuk Controller White", 14.00, "wiiu_ncw.jpg", "Nintendo","New",10);
			Accessory wiiu_rc = new Accessory("Remote Controller", 15.00, "wiiu_rc.jpg", "Nintendo","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("wiiu_ncw", wiiu_ncw);
			accessories.put("wiiu_rc", wiiu_rc);
			Console wiiu = new Console("Wii U",13.50,"wiiu.png","Nintendo","New",10,accessories);			
			nintendo.put("wiiu", wiiu);
		}
	}
}
