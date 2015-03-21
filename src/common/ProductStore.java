package common;
import java.util.ArrayList;
import java.util.Date;

import model.Product;
import model.Publisher;


public class ProductStore {

	private static ArrayList<Product> stores;

	private static class ProductStoreHolder {
		private static final ProductStore INSTANCE = new ProductStore();
	}

	private ProductStore(){
		stores=new ArrayList<Product>();
		Product a=new Product("Internet&WWW How To Program",121.99f);
		a.setAuthors("PaulDeitel, Harvey Deitel and Abbey Deitel");
		a.setPaperBack(992);
		a.setLanguage("English");
		a.setIsbn10("0132151006");
		a.setIsbn13("978-0132151009");
		a.setPublisher(new Publisher("Prentice Hall",5,new Date(2011-1900,10,19)));
		a.setImgFile("51cjTlMm51L.jpg");
		stores.add(a);
		Product b=new Product("Web application architecture",54.56f);
		b.setAuthors("Leon Shkar and Rich Rosen");
		b.setPaperBack(440);
		b.setLanguage("English");
		b.setIsbn10("047051860X");
		b.setIsbn13("978-0470518601");
		b.setPublisher(new Publisher("Wiley",2,new Date(2009-1900,3,27)));
		b.setImgFile("71mcSbxVqML.jpg");
		stores.add(b);
		Product c=new Product("Design Patterns",39.53f);
		c.setAuthors("Erich Gamma, Richard Helm, Ralph Johnson and John Vlissides");
		c.setPaperBack(395);
		c.setLanguage("English");
		c.setIsbn10("0201633612");
		c.setIsbn13("078-5342633610");
		c.setPublisher(new Publisher("Addison-Wesley Professional",1,new Date(1994-1900,10,10)));
		c.setImgFile("81gtKoapHFL.jpg");
		stores.add(c);
		Product d=new Product("Head First Servlets and JSP",35.46f);
		d.setAuthors("Bryan Basham , Kathy Sierra and Bert Bates");
		d.setPaperBack(914);
		d.setLanguage("English");
		d.setIsbn10("0596516681");
		d.setIsbn13("978-0596516680");
		d.setPublisher(new Publisher("O'Reilly Media",2,new Date(2008-1900,3,4)));
		d.setImgFile("51u39QvXVeL.jpg");
		stores.add(d);
	}

	public static ProductStore getInstance() {
		return ProductStoreHolder.INSTANCE;
	}
	
	public synchronized void addProdcut(Product p){
		stores.add(p);
	}

	public synchronized void modifyProdcut(int index,Product p){
		stores.set(index,p);
	}
	
	public synchronized ArrayList<Product> getProducts(){
		return stores;
	}

	public synchronized Product getProdcut(int index){
		return stores.get(index);
	}

}
