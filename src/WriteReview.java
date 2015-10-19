

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class WriteReview
 */
@WebServlet("/WriteReview")
public class WriteReview extends HttpServlet {
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

		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String maker = request.getParameter("maker");
		String access = request.getParameter("access");
		
		ReviewHelper rh = new ReviewHelper(name, type, maker, access);
		
		pw.print("<form action='WriteReview' method='post'>"+
				"<input type='hidden' name='name' value='"+name+"'>"+
				"<input type='hidden' name='type' value='"+type+"'>"+
				"<input type='hidden' name='maker' value='"+maker+"'>"+
				"<input type='hidden' name='access' value='"+access+"'>"
				+"<table>"
		+"<tr><td>Product Name</td><td><input type='text' name='productModelName' maxlength='20' required='required' value='"+rh.itemname+"'/></td></tr>"
		+"<tr><td>Product Category</td><td><input type='text' name='productCategory' placeholder='Enter Product Category' maxlength='20' required='required' value='"+rh.category+"'/></td></tr>"
		+"<tr><td>Price</td><td><input type='text' name='productPrice' placeholder='Enter Product Price' maxlength='20' required='required' value='"+rh.price+"'/></td></tr>"
		+"<tr><td>Retailer Name</td><td><input type='text' name='retailerName' placeholder='Enter Retailer Name' maxlength='20' required='required'value='GameSpeed''/></td></tr>"
		+"<tr><td>Retailer Zip</td><td><input type='text' name='retailerZip' placeholder='Enter Retailer Zip' maxlength='20' required='required' value='60616'/></td></tr>"
		+"<tr><td>Retailer City</td><td><input type='text' name='retailerCity' placeholder='Enter Retailer City' maxlength='20' required='required' value='Chicago'/></td></tr>"
		+"<tr><td>Retailer State</td><td><input type='text' name='retailerState' placeholder='Enter Retailer State' maxlength='20' required='required' value='IL'/></td></tr>"
		+"<tr><td>Product On Sale</td><td><input type='text' name='productOnSale' placeholder='Enter Product On Sale' maxlength='20' required='required' value='Yes'/></td></tr>"
		+"<tr><td>Manufacture Name</td><td><input type='text' name='manufactureName' placeholder='Enter Manufacture Name' maxlength='20' required='required' value='"+rh.manufacture+"'/></td></tr>"
		+"<tr><td>Manufacture Rebate</td><td><input type='text' name='manufactureRebate' placeholder='Enter Manufacture Rebate' maxlength='20' required='required' value='"+rh.rebate+"'/></td></tr>"
		+"<tr><td>User Name</td><td><input type='text' name='userId' placeholder='Enter User Id' maxlength='20' required='required' value='"+helper.username()+"'/></td></tr>"
		+"<tr><td>Age</td><td><input type='text' name='userAge' placeholder='Enter User Age' maxlength='20' required='required' value=''/></td></tr>"
		+"<tr><td>Gender</td><td><input type = 'radio' name = 'userGender' value = 'Male'/> Male  <input type = 'radio' name = 'userGender' value = 'Female'/> Female</td></tr>"
		+"<tr><td>Occupation</td><td><input type='text' name='userOccupation' placeholder='Enter User Occupation' maxlength='20' required='required' value=''/></td></tr>"
		+"<tr><td>Rating</td><td><select name='reviewRating'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></td></tr>"
		+"<tr><td>Date</td><td><input type='date' name='reviewDate' required='required'/></br></td></tr>"
		+"<tr><td>Review</td><td><textarea name='reviewText' placeholder='Enter Review Text' rows='4' cols='25' maxlength='100' required='required'></textarea></td></tr>"
		+"<tr><td></td><td><input type='submit' value='Add Review' class='btnbuy'></form></td></tr>"
		+"</table>");
		
		

		pw.print("</div></div></div>");		
		helper.printHtml("site_footer.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);

		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String maker = request.getParameter("maker");
		String access = request.getParameter("access");
		
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase db = mongo.getDatabase("gamespeed");
		MongoCollection<Document> review_details = db.getCollection("review_" + name);
		
		Document doc = new Document("username", helper.username()).
		append("productModelName", request.getParameter("productModelName")).
		append("productCategory", request.getParameter("productCategory")).
		append("productPrice", request.getParameter("productPrice")).
		append("retailerName", request.getParameter("retailerName")).
		append("retailerZip", request.getParameter("retailerZip")).
		append("retailerCity", request.getParameter("retailerCity")).
		append("retailerState", request.getParameter("retailerState")).
		append("productOnSale", request.getParameter("productOnSale")).
		append("manufactureName", request.getParameter("manufactureName")).
		append("manufactureRebate", request.getParameter("manufactureRebate")).
		append("userId", request.getParameter("userId")).
		append("userAge", request.getParameter("userAge")).
		append("userGender", request.getParameter("userGender")).
		append("userOccupation", request.getParameter("userOccupation")).
		append("reviewRating", request.getParameter("reviewRating")).
		append("reviewDate", request.getParameter("reviewDate")).
		append("reviewText", request.getParameter("reviewText"));
				
		review_details.insertOne(doc);
		
		response.sendRedirect("Review?name="+name+"&type="+type+"&maker="+maker+"&access="+access);
		return;
		
	}

}
