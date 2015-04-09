package controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ProductStore;

/**
 * Servlet implementation class InitSystem
 * for initialisation of some pre-setting datas
 */
public class InitSystem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitSystem() {
        super();
    }

	@Override
	public void init() throws ServletException {
		InputStream is=null;
		try {
			super.init();
			ServletContext ctx = getServletContext();
			ProductStore store=(ProductStore) ctx.getAttribute("store");
			if (store==null){
				is=getServletContext().getResourceAsStream("/resources/products.xml");
				store = ProductStore.getInstance(is);
				ctx.setAttribute("store", store);
				ctx.setAttribute("ratingRange", 5);
			}
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			throw e;
		}finally{
			try {
				if (is!=null){is.close();}
			} catch (IOException e) {
				System.out.println(e.getMessage());
				throw new ServletException(e);
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
