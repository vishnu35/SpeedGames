

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@WebServlet("/DeleteOrder")
public class DeleteOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Helper helper = new Helper(request, null);
		

		MongoClient mongo = new MongoClient("localhost", 27017);

		// If database doesn't exists, MongoDB will create it for you
		MongoDatabase db = mongo.getDatabase("gamespeed");
		HttpSession session = request.getSession(true);

		OrderPojo order = (OrderPojo) session.getAttribute("orderToDelete");
		session.removeAttribute("orderToDelete");

		// If the collection does not exists, MongoDB will create it for you
		MongoCollection<Document> user_details;
		if(helper.usertype().equals("manager")) {
			user_details = db.getCollection(request.getParameter("collection_name"));
		}else{
			user_details = db.getCollection("order_"
				+ helper.username());
		}
		user_details.deleteOne(new Document("username", order.getUsername())
				.append("orderid", order.getOrderid()));
		mongo.close();

		response.sendRedirect("Account");
		return;
	}

}
