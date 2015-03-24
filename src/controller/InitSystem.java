package controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ProductStore;

/**
 * Servlet implementation class InitSystem
 */
@WebServlet("/InitSystem")
public class InitSystem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitSystem() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ServletContext ctx = getServletContext();
			InputStream is = ctx.getResourceAsStream("/resources/products.xml");
			ProductStore store = ProductStore.getInstance(is);
			getServletContext().setAttribute("store", store);
			is.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		request.getRequestDispatcher("/ProductCatalogue").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
