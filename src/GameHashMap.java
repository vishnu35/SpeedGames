

import java.util.HashMap;

public class GameHashMap {

	public static HashMap<String, Game> electronicArts = new HashMap<String, Game>();
	public static HashMap<String, Game> activision = new HashMap<String, Game>();
	public static HashMap<String, Game> takeTwoInteractive = new HashMap<String, Game>();
	
	public static String string_electronicArts = "Electronic Arts";
	public static String string_activision = "Activision";
	public static String string_takeTwoInteractive = "Take-Two Interactive";
	
	public GameHashMap() {
		if(electronicArts.isEmpty()){
			Game ea_nfs = new Game("Need for Speed",10.00,"ea_nfs.jpg","Electronic Arts","New",10);
			Game ea_fifa = new Game("FIFA 2016",10.00,"ea_fifa.jpg","Electronic Arts","New",10);
			
			electronicArts.put("ea_nfs", ea_nfs);
			electronicArts.put("ea_fifa", ea_fifa);
		}
		if(activision.isEmpty()){
			Game activision_cod = new Game("Call Of Duty",13.50,"activision_cod.jpg","Activision","New",10);
			Game activision_prot = new Game("Prototype",11.00,"activision_prot.jpg","Activision","New",10);

			activision.put("activision_cod", activision_cod);
			activision.put("activision_prot", activision_prot);			
		}
		if(takeTwoInteractive.isEmpty()){
			Game tti_gta = new Game("Grand Theft Auto",14.00,"tti_gta.jpg","Take-Two Interactive","New",10);
			Game tti_evolve = new Game("Evolve",13.50,"tti_evolve.jpg","Take-Two Interactive","New",10);
			
			takeTwoInteractive.put("tti_gta", tti_gta);
			takeTwoInteractive.put("tti_evolve", tti_evolve);
		}
	}
}
