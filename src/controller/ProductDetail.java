package controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
import model.User;

import common.ProductStore;

/**
 * Servlet implementation class ProductDetail
 */
@WebServlet(description = "Product Detail", urlPatterns = { "/ProductDetail" })
public class ProductDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String proid = (String) request.getParameter("proid");
		ServletContext ctx = getServletContext();
		InputStream is = ctx.getResourceAsStream("/resources/products.xml");
		ProductStore store = ProductStore.getInstance(is);
		is.close();
		Product p = store.getProdcut(Integer.parseInt(proid));
		HttpSession current=request.getSession();
		RequestDispatcher view = request.getRequestDispatcher("/productDetail.jsp");
		String pub = p.getPublisher();
		/*String publisherInfo = pub.getPress() + "; "
				+ String.valueOf(pub.getEdition()) + " edition ("
				+ pub.getGetDisplayPublishDate() + ")";*/
		request.setAttribute("product", p);
		request.setAttribute("productId", proid);
		request.setAttribute("publisherInfo", pub);
		User currentUser=(User) current.getAttribute("currentUser");
		int youtRating=currentUser==null?0:currentUser.getProductRating()[Integer.parseInt(proid)];
		request.setAttribute("yourRating", youtRating);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession current=request.getSession();
		User currentUser=(User) current.getAttribute("currentUser");
		if (currentUser!=null){
			String selectedRate = (String) request.getParameter("selectedrate");
			String proid = (String) request.getParameter("proid");
			ServletContext ctx = getServletContext();
			InputStream is = ctx.getResourceAsStream("/resources/products.xml");
			ProductStore store = ProductStore.getInstance(is);
			is.close();
			Product p = store.getProdcut(Integer.parseInt(proid));
			p.addRating(Integer.parseInt(selectedRate));
			store.modifyProdcut(Integer.parseInt(proid), p);
			currentUser.setProductRating(Integer.parseInt(proid), Integer.parseInt(selectedRate));
			current.setAttribute("currentUser", currentUser);
			RequestDispatcher view = request.getRequestDispatcher("/rateSuccess.jsp");
			request.setAttribute("rate", selectedRate);
			request.setAttribute("pname", p.getName());
			view.forward(request, response);
		}else{
			RequestDispatcher view = request.getRequestDispatcher("/rateFail.jsp");
			view.forward(request, response);
		}
	}

}
