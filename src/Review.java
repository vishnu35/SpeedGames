

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@WebServlet("/Review")
public class Review extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		helper.printHtml("site_header.html");
		helper.printHtml("site_sidebar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Reviews</a>");
		pw.print("</h2><div class='entry'>");

		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String maker = request.getParameter("maker");
		String access = request.getParameter("access");
		
		pw.print("<a href='WriteReview?name="+name+"&type="+type+"&maker="+maker+"&access="+access+"' class='btnbuy' style='width:100px'>Write Review</a>");
		
		pw.print("<hr><br><br>");
		MongoClient mongo = new MongoClient("localhost", 27017);

		// If database doesn't exists, MongoDB will create it for you
		MongoDatabase db = mongo.getDatabase("gamespeed");

		// If the collection does not exists, MongoDB will create it for you
		MongoCollection<Document> user_details = db.getCollection("review_"
				+ name);
		MongoCursor<Document> cursor = user_details.find().iterator();
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<ReviewPojo> orders = new ArrayList<ReviewPojo>();
		ReviewPojo review = new ReviewPojo();
		boolean empty = true;
		while (cursor.hasNext()) {
			review = mapper.readValue(cursor.next().toJson().toString(),
					ReviewPojo.class);
			orders.add(review);
			pw.print("<table class='gridtable'>");
			pw.print("<tr><th>" + review.getUsername()	+ " : ("+review.getReviewRating()+"/5)</th>");
			pw.print("<th> : "+ review.reviewText +"</th></tr>"
			+ "<tr><td>Manufacture Name </td><td> :"+review.getManufactureName()+"</td></tr>"
			+ "<tr><td>Manufacture Rebate </td><td> :"+review.getManufactureRebate()+"</td></tr>"
			+ "<tr><td>User Id  </td><td> :"+review.getUserId()+"</td></tr>"
			+ "<tr><td>User Age </td><td> :"+review.getUserAge()+"</td></tr>"
			+ "<tr><td>User Gender  </td><td> :"+review.getUserGender()+"</td></tr>"
			+ "<tr><td>User Occupation </td><td> : "+review.getUserOccupation()+"</td></tr>"
			+ "<tr><td>Review Rating  </td><td> : "+review.getReviewRating()+"</td></tr>"
			+ "<tr><td>Review Date  </td><td> :"+review.getReviewDate()+"</td></tr>");
			pw.print("</table>");
			pw.print("<hr>");
			empty = false;
		}

		if(empty)
			pw.print("<h4 style='color:red'>No reviews for this product. To write review press above button</h4>");
		
		pw.print("</div></div></div>");		
		helper.printHtml("site_footer.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
