
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Helper helper = new Helper(request, null);
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String maker = request.getParameter("maker");
		String access = request.getParameter("access");
		helper.displayProduct(name,type,maker,access,true);		
		
		response.sendRedirect("Account");
		RetailerHashMap.retailers.get(helper.username()).getRetailerItems().remove(name);
		
		return;
		
	}

}
