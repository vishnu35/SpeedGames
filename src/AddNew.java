

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/AddNew")
@MultipartConfig(fileSizeThreshold=1024*1024*2, 
				maxFileSize=1024*1024*10,
				maxRequestSize=1024*1024*50) 
public class AddNew extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		Helper helper = new Helper(request, pw);
		helper.printHtml("site_header.html");

		pw.print("<form method='post' action='AddNew' enctype='multipart/form-data'>"
				+ "<input type='text' name='name' placeholder='Product Name' required='required'>"
				+ "<p>Select Product Category And Company</p>"
				+ "<select name='category'>"
				+ "<optgroup label='Consoles'>"
				+ "<option value='consoles_microsoft'>Microsoft</option>"
				+ "<option value='consoles_sony'>Sony</option>"
				+ "<option value='consoles_nintendo'>Nintendo</option>"
				+ "</optgroup>"
				+ "<optgroup label='Games'>"
				+ "<option value='games_electronicArts'>Electronic Arts</option>"
				+ "<option value='games_activision'>Activision</option>"
				+ "<option value='games_takeTwoInteractive'>Take-Two Interactive</option>"
				+ "</optgroup>"
				+ "<optgroup label='Tablets'>"
				+ "<option value='tablets_microsoft'>Microsoft</option>"
				+ "<option value='tablets_apple'>Apple</option>"
				+ "<option value='tablets_samsung'>Samsung</option>"
				+ "</optgroup>" + "<optgroup label='Accessories'>");
		for (Map.Entry<String, Console> entry : ConsoleHashMap.microsoft
				.entrySet()) {
			pw.print("<option value='accessories_microsoft_"
					+ entry.getKey().toString() + "'>"
					+ entry.getValue().getName() + "</option>");
		}
		for (Map.Entry<String, Console> entry : ConsoleHashMap.sony.entrySet()) {
			pw.print("<option value='accessories_sony_"
					+ entry.getKey().toString() + "'>"
					+ entry.getValue().getName() + "</option>");
		}
		for (Map.Entry<String, Console> entry : ConsoleHashMap.nintendo
				.entrySet()) {
			pw.print("<option value='accessories_nintendo_"
					+ entry.getKey().toString() + "'>"
					+ entry.getValue().getName() + "</option>");
		}
		pw.print("</optgroup>"
				+ "</select>"
				+ "<p>Select Product Condition</p>"
				+ "<select name='condition'>"
				+ "<option value='new'>New</option>"
				+ "<option value='Pre-Owned'>Pre-Owned</option>"
				+ "</select>"
				+ "<input type='number' name='price' placeholder='$ Price' required='required'>"
				+ "<input type='number' name='discount' placeholder='Discount $' required='required'>"
				+ "<p>Select Product Image</p>"
				+ "<input type='file' name='file' placeholder='Select Image' required='required'>"
				+ "<input type='submit' value='Add Product'>" + "</form>");

		helper.printHtml("site_footer.html");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		
		String productCat[] = request.getParameter("category").split("_");
		String type = productCat[0];
		String product = productCat[1];
		String access = "";
		
		String savePath = request.getServletContext().getRealPath("")
				+ File.separator + "images" + File.separator + type;
		Part part = request.getPart("file");
		String fileName = extractFileName(part);
		if (fileName.endsWith(".jpg") || fileName.endsWith(".png")) {
			String ext = "";
			if(fileName.endsWith(".jpg")) ext=".jpg"; else ext=".png";
			fileName = helper.username()
					+ "_"
					+ request.getParameter("name").replaceAll("\\s", "")
							.replaceAll("\\W", "");
			//System.out.println(savePath + File.separator + fileName + ext);
			part.write(savePath + File.separator + fileName + ext);

			if (type.equals("consoles")) {
				Console console = new Console();
				console.setName(request.getParameter("name"));
				console.setRetailer(helper.username());				
				console.setPrice(Double.parseDouble((request.getParameter("price"))));
				console.setDiscount(Integer.parseInt(request
						.getParameter("discount")));
				console.setCondition(request.getParameter("condition"));
				console.setImage(fileName + ext);
				
				if(product.equals("microsoft")){
					ConsoleHashMap.microsoft.put(fileName, console);					
				}else if(product.equals("sony")){
					ConsoleHashMap.sony.put(fileName, console);
				}else if(product.equals("nintendo")){
					ConsoleHashMap.nintendo.put(fileName, console);
				}
				

			} else if (type.equals("games")) {
				Game game = new Game();
				game.setName(request.getParameter("name"));
				game.setRetailer(helper.username());				
				game.setPrice(Double.parseDouble((request.getParameter("price"))));
				game.setDiscount(Integer.parseInt(request
						.getParameter("discount")));
				game.setCondition(request.getParameter("condition"));
				game.setImage(fileName + ext);
				
				if(product.equals("electronicArts")){
					GameHashMap.electronicArts.put(fileName, game);					
				}else if(product.equals("activision")){
					GameHashMap.activision.put(fileName, game);
				}else if(product.equals("takeTwoInteractive")){
					GameHashMap.takeTwoInteractive.put(fileName, game);
				}
			} else if (type.equals("tablets")) {
				Tablet tablet = new Tablet();
				tablet.setName(request.getParameter("name"));
				tablet.setRetailer(helper.username());				
				tablet.setPrice(Double.parseDouble((request.getParameter("price"))));
				tablet.setDiscount(Integer.parseInt(request
						.getParameter("discount")));
				tablet.setCondition(request.getParameter("condition"));
				tablet.setImage(fileName + ext);
				
				if(product.equals("apple")){
					TabletHashMap.apple.put(fileName, tablet);					
				}else if(product.equals("microsoft")){
					TabletHashMap.microsoft.put(fileName, tablet);
				}else if(product.equals("samsung")){
					TabletHashMap.samsung.put(fileName, tablet);
				}

			} else if (type.equals("accessories")){
				access = productCat[2];
				Accessory accessory = new Accessory();
				accessory.setName(request.getParameter("name"));
				accessory.setRetailer(helper.username());				
				accessory.setPrice(Double.parseDouble((request.getParameter("price"))));
				accessory.setDiscount(Integer.parseInt(request
						.getParameter("discount")));
				accessory.setCondition(request.getParameter("condition"));
				accessory.setImage(fileName + ext);
				
				Console console = new Console();
				if(product.equals("microsoft")){
					console = ConsoleHashMap.microsoft.get(access);
					console.getAccessories().put(fileName, accessory);
					ConsoleHashMap.microsoft.put(access,console);
				}else if(product.equals("sony")){
					console = ConsoleHashMap.sony.get(access);
					console.getAccessories().put(fileName, accessory);
					ConsoleHashMap.sony.put(access,console);
				}else if(product.equals("nintendo")){
					console = ConsoleHashMap.nintendo.get(access);
					console.getAccessories().put(fileName, accessory);
					ConsoleHashMap.nintendo.put(access,console);
				}				
				
			}
			
			RetialerItem ri = new RetialerItem(fileName, type, product, access);
			
			if(RetailerHashMap.retailers.containsKey(helper.username())){
				RetailerHashMap.retailers.get(helper.username()).getRetailerItems().put(fileName, ri);				
			}else{
				HashMap<String,RetialerItem> retailerItems = new HashMap<String,RetialerItem>();
				retailerItems.put(fileName, ri);
				Retailer r = new Retailer(helper.username(), retailerItems);	
				RetailerHashMap.retailers.put(helper.username(), r);
			}

//			System.out.println(RetailerHashMap.retailers.get(helper.username()));
//			System.out.println(RetailerHashMap.retailers.get(helper.username()).getRetailerItems().get(fileName).getItemName());
//
//			System.out.println("inp" + fileName+ "," + type +"," +product+","+access);

			response.sendRedirect("Account");
			return;
		} else {
			response.sendRedirect("AddNew");
			return;
			
		}

	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename"))
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
		}
		return "";
	}
}
