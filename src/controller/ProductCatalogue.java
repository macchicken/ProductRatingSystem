package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

import common.ProductStore;

/**
 * Servlet implementation class ProductCatalogue
 */
@WebServlet(description = "Product Catalogue", urlPatterns = { "/ProductCatalogue.do" })
public class ProductCatalogue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductCatalogue() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext ctx = getServletContext();
		ProductStore store=(ProductStore) ctx.getAttribute("store");
		HttpSession current=request.getSession();
		RequestDispatcher view = request.getRequestDispatcher("/productCatalogue.jsp");
		request.setAttribute("products", store.getProducts());
		request.setAttribute("total", store.getProducts().size());
		User currentUser;
		if ((currentUser=(User) current.getAttribute("currentUser"))==null){
			currentUser=new User(store.getProducts().size());
			current.setAttribute("currentUser", currentUser);
		}
		request.setAttribute("currentUser", currentUser);
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
