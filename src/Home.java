

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.print(ConsoleHashMap.microsoft.get("XBox One").getAccessories());
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		Helper helper = new Helper(request,pw);
		helper.printHtml("site_header.html");
		helper.printHtml("site_sidebar.html");
		helper.printHtml("site_home.html");
		helper.printHtml("site_footer.html");
				
	}

}
