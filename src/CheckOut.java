

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@WebServlet("/CheckOut")
public class CheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		helper.printHtml("site_header.html");
		helper.printHtml("site_sidebar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order Details</a>");
		pw.print("</h2><div class='entry'>");
		
		pw.print("<form action='CheckOut' method='post'><table>"
		+ "<tr><td>Total Amount</td><td><input type='number' name='total' readonly='readonly' value='"+helper.getCatTotal()+"'/></td></tr>"
		+ "<tr><td>Full Name</td><td><input type='text' name='fullname' required='required'/></td></tr>"
		+ "<tr><td>Credit Card Number</td><td><input type='text' name='creditcard' required=required'/></td></tr>"
		+ "<tr><td>CVV</td><td><input type='text' name='cvv' required='required'/></td></tr>"
		+ "<tr><td>Address Line 1</td><td><input type='text' name='address1' required='required'/></td></tr>"
		+ "<tr><td>Address Line 1</td><td><input type='text' name='address2' required='required'/></td></tr>"
		+ "<tr><td>Zipcode</td><td><input type='text' name='zipcode' required='required'/></td></tr>"
		+ "<tr><td></td><td><input type='submit' name='login' class='buybtn' value='Place Order'></td></tr>"
		+ "</table></form>");
		
		pw.print("</div></div></div>");		
		helper.printHtml("site_footer.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		
		double total = Double.parseDouble(request.getParameter("total"));
		String fullname = request.getParameter("fullname");
		String creditcard = request.getParameter("creditcard");
		String cvv = request.getParameter("cvv");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String zipcode = request.getParameter("zipcode");
		
		
		MongoClient mongo = new MongoClient("localhost", 27017);
		
		MongoDatabase db = mongo.getDatabase("gamespeed");
		
		MongoCollection<Document> user_details = db.getCollection("order_" + helper.username());
		
		List<Document> orders = new ArrayList<Document>();
		for (OrderItem oi : helper.getCustomerOrders()) {			
			Document item = new Document("username", helper.username()).
				append("itemname", oi.getName()).
				append("price", oi.getPrice()).
				append("image", oi.getImage()).
				append("retailer", oi.getRetailer());
			
			orders.add(new Document("item", item));
		    //i++;
		}
		
		Random r = new Random(System.currentTimeMillis());
		int orderid = 10000 + r.nextInt(20000);
		
		Document doc = new Document("username", helper.username()).
			append("orderid",orderid).
			append("total", total).
			append("fullname", fullname).
			append("creditcard", creditcard).
			append("cvv", cvv).
			append("address1", address1).
			append("address2", address2).
			append("zipcode", zipcode).
			append("date", helper.currentDate()).
			append("itemscount", helper.CartCount()).
			append("order",orders);
			
		user_details.insertOne(doc);
		
		OrdersHashMap.orders.get(helper.username()).clear();
		
		helper.printHtml("site_header.html");
		helper.printHtml("site_sidebar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title'>");
		pw.print("<a style='font-size: 24px;'>Order Palaced Successfully</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("</div></div></div>");		
		helper.printHtml("site_footer.html");
	}

}
