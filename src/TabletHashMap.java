

import java.util.HashMap;

public class TabletHashMap {
	public static HashMap<String, Tablet> apple = new HashMap<String, Tablet>();
	public static HashMap<String, Tablet> microsoft = new HashMap<String, Tablet>();
	public static HashMap<String, Tablet> samsung = new HashMap<String, Tablet>();
	
	public static String string_apple = "Apple";
	public static String string_microsoft = "Microsoft";
	public static String string_samsung = "Samsung";
	
	public TabletHashMap() {
		if(apple.isEmpty()){
			Tablet a_ipadmini = new Tablet("IPad Mini",10.00,"a_ipadmini.png","Apple","New",10);
			Tablet a_ipadair = new Tablet("IPad Air",10.00,"a_ipadair.jpg","Apple","New",10);
			
			apple.put("a_ipadmini", a_ipadmini);
			apple.put("a_ipadair", a_ipadair);
		}
		if(microsoft.isEmpty()){
			Tablet m_surface3 = new Tablet("Surface 3",13.50,"m_surface3.jpg","Microsoft","New",10);
			Tablet m_surfacepro = new Tablet("Surface Pro",11.00,"m_surfacepro.jpg","Microsoft","New",10);

			microsoft.put("m_surface3", m_surface3);
			microsoft.put("m_surfacepro", m_surfacepro);			
		}
		if(samsung.isEmpty()){
			Tablet s_samsungg = new Tablet("Samsung Galaxy",14.00,"s_samsungg.jpg","Samsung","New",10);
			Tablet s_samsungpro = new Tablet("Samsung Galaxy Pro",13.50,"s_samsungpro.jpg","Samsung","New",10);
			
			samsung.put("s_samsungg", s_samsungg);
			samsung.put("s_samsungpro", s_samsungpro);
		}
	}
}
