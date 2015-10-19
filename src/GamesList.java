

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GamesList")
public class GamesList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
				
		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Game> hm = new HashMap<String, Game>();
		
		if(CategoryName==null){
			hm.putAll(GameHashMap.electronicArts);
			hm.putAll(GameHashMap.activision);
			hm.putAll(GameHashMap.takeTwoInteractive);
			name = "";
		}else{
		if(CategoryName.equals("electronicArts")){
			hm.putAll(GameHashMap.electronicArts);
			name = GameHashMap.string_electronicArts;
		}
		else if(CategoryName.equals("activision")){
			hm.putAll(GameHashMap.activision);
			name = GameHashMap.string_activision;
		}
		else if(CategoryName.equals("takeTwoInteractive")){
			hm.putAll(GameHashMap.takeTwoInteractive);
			name = GameHashMap.string_takeTwoInteractive;
		}
		}
		
		Helper helper = new Helper(request,pw);
		helper.printHtml("site_header.html");
		helper.printHtml("site_sidebar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Games</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Game> entry : hm.entrySet()){
			Game game = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+game.getName()+"</h3>");
			pw.print("<strong>"+game.getPrice()+"$</strong><ul>");
			pw.print("<li id='item'><img src='images/games/"+game.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='games'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now' href='#'></input></form></li>");
			pw.print("<li><a class='btnreview' href='Review?name="+entry.getKey()+"&type=games&maker="+CategoryName+"&access='>Reviews</a></li>");
			
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}		
		pw.print("</table></div></div></div>");		
		helper.printHtml("site_footer.html");
		
	}

}
