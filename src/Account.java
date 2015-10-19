

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


@WebServlet("/Account")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		if (!helper.isLoggedin()) {
			response.sendRedirect("Login");
			return;
		}

		// System.out.print(helper.usertype());
		String usertype = helper.usertype();
		if (usertype.equals("customer")) {
			displayCustomer(request, response);
		} else if (usertype.equals("retailer")) {
			displayRetailer(request, response);
		} else if (usertype.equals("manager")) {
			displayManager(request, response);
		}
	}

	protected void displayCustomer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		helper.printHtml("site_header.html");
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Orders</a></h2>"
				+ "<div class='entry'>");

		MongoClient mongo = new MongoClient("localhost", 27017);

		// If database doesn't exists, MongoDB will create it for you
		MongoDatabase db = mongo.getDatabase("gamespeed");

		// If the collection does not exists, MongoDB will create it for you
		MongoCollection<Document> user_details = db.getCollection("order_"
				+ helper.username());
		MongoCursor<Document> cursor = user_details.find().iterator();
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<OrderPojo> orders = new ArrayList<OrderPojo>();
		OrderPojo order = new OrderPojo();
		boolean empty = true;
		while (cursor.hasNext()) {
			order = mapper.readValue(cursor.next().toJson().toString(),
					OrderPojo.class);
			orders.add(order);
			pw.print("<table class='gridtable'>");
			pw.print("<tr><th>Order#" + order.getOrderid() + " : " + order.order.size()
					+ " items</th>");
			HttpSession session = request.getSession(true);
			session.setAttribute("orderToDelete", order);
			pw.print("<th>"
					+ "<form method='post' action='DeleteOrder'>"
					+ "<input type='submit' class='btnbuy' value='Delete'></input>"
					+ "</form></th></tr>");
			pw.print("<tr><td colspan=2>");
			pw.print("<table>");
			int i = 1;
			for (ItemPojo item : order.order) {
				pw.print("<tr><td>");
				pw.print(i);
				pw.print("</td><td>");
				pw.print(item.item.getItemname());
				pw.print("</td><td>");
				pw.print(item.item.getPrice());
				pw.print("</td></tr>");
			}
			pw.print("<tr><td></td><td>Total:</td><td>" + order.getTotal()
					+ "</td></tr>");

			pw.print("</table></td></tr>");

			pw.print("</table>");
			empty = false;
		}
		if (empty) {
			pw.print("<h4 style='color:red'>No orders are found</h4>");
		}
		pw.print("</div></div>");
		helper.printHtml("site_footer.html");
	}

	protected void displayRetailer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		helper.printHtml("site_header.html");
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Account</a></h2>"
				+ "<div class='entry'>");
		pw.print("<a class='btnbuy' href='AddNew' style='width:100px'>Add New Product</a>");

		pw.print("</div></div>");
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>My Products</a></h2>"
				+ "<div class='entry'>");
		String username = helper.username();
		Retailer retailer = RetailerHashMap.retailers.get(username);
		System.out.println(username);
		System.out.println(retailer);
		if (retailer == null) {
			pw.print("<h4 style='color:red'>No products found</h4>");
		} else {
			HashMap<String, RetialerItem> products = retailer
					.getRetailerItems();
			if (products.isEmpty()) {
				pw.print("<h4 style='color:red'>No products found</h4>");
			} else {
				int size = products.size();
				int i = 1;
				pw.print("<table>");
				for (Map.Entry<String, RetialerItem> entry : products
						.entrySet()) {
					if (i % 3 == 1)
						pw.print("<tr>");
					pw.print("<td>");
					RetialerItem product = entry.getValue();
					helper.displayProduct(product.getItemName(),
							product.getItemType(), product.getItemMaker(),
							product.getItemAcc(), false);
					pw.print("</td>");
					if (i % 3 == 0 || i == size)
						pw.print("</tr>");
				}
				pw.print("</table>");
			}
		}

		pw.print("</div></div>");
		helper.printHtml("site_footer.html");
	}

	protected void displayManager(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		helper.printHtml("site_header.html");
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Create User</a></h2>"
				+ "<div class='entry'>");
		HttpSession session = request.getSession(true);
		if (session.getAttribute("login_msg") != null)
			pw.print("<h4 style='color:red'>"
					+ session.getAttribute("login_msg") + "</h4>");
		pw.print("<form method='post' action='Registration'>");
		pw.print("<table><tr><td>User Name</td><td>Password</td><td>ReEnter Password</td><td></td></tr><tr>"
				+ "<td><input type='text' name='username' value='' class='input' required></input></td>"
				+ "<td><input type='password' name='password' value='' class='input' required></input></td>"
				+ "<td><input type='password' name='repassword' value='' class='input' required></input></td>"
				+ "<td><input type='submit' class='btnbuy' value='Create User'></input></td>"
				+ "</tr></table></form>");
		pw.print("</div></div>");
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Customer Orders</a></h2>"
				+ "<div class='entry'>");
		MongoClient mongo = new MongoClient("localhost", 27017);

		// If database doesn't exists, MongoDB will create it for you
		MongoDatabase db = mongo.getDatabase("gamespeed");

		// If the collection does not exists, MongoDB will create it for you

		boolean empty = true;
		for (String col : db.listCollectionNames()) {
			if (col.startsWith("order_")) {
				MongoCollection<Document> user_details = db.getCollection(col);
				MongoCursor<Document> cursor = user_details.find().iterator();
				ObjectMapper mapper = new ObjectMapper();
				ArrayList<OrderPojo> orders = new ArrayList<OrderPojo>();
				OrderPojo order = new OrderPojo();
				while (cursor.hasNext()) {
					order = mapper.readValue(cursor.next().toJson().toString(),
							OrderPojo.class);
					orders.add(order);
					pw.print("<table class='gridtable'>");
					pw.print("<tr><th>Order#" + order.getOrderid() + " : "
							+ order.order.size() + " items</th>");
					session.setAttribute("orderToDelete", order);
					pw.print("<th>"
							+ "<form method='post' action='DeleteOrder'>"
							+ "<input type='hidden' name='collection_name' value='"+col+"'></input>"
							+ "<input type='submit' class='btnbuy' value='Delete'></input>"
							+ "</form></th></tr>");
					pw.print("<tr><td colspan=2>");
					pw.print("<table>");
					int i = 1;
					for (ItemPojo item : order.order) {
						pw.print("<tr><td>");
						pw.print(i);
						pw.print("</td><td>");
						pw.print(item.item.getItemname());
						pw.print("</td><td>");
						pw.print(item.item.getPrice());
						pw.print("</td></tr>");
					}
					pw.print("<tr><td></td><td>Total:</td><td>"
							+ order.getTotal() + "</td></tr>");

					pw.print("</table></td></tr>");

					pw.print("</table>");
					empty = false;
				}
			}
		}
		if (empty) {
			pw.print("<h4 style='color:red'>No orders are found</h4>");
		}
		pw.print("</div></div>");
		helper.printHtml("site_footer.html");
	}
}
