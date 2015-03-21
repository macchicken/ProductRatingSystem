package common;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.Product;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ProductStore {

	private static ArrayList<Product> stores;
	private static InputStream resource;
	
	private static class ProductStoreHolder {
		private static final ProductStore INSTANCE = new ProductStore();
	}

	private ProductStore(){
		stores=new ArrayList<Product>();
		/*Product a=new Product("Internet&WWW How To Program",121.99f);
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
		stores.add(d);*/
		stores=parserXml(resource,stores);
	}

	public static ProductStore getInstance(InputStream res) {
		resource=res;
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

	private ArrayList<Product> parserXml(InputStream resouceFile,ArrayList<Product> targetStore){
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(resouceFile);
			NodeList objects = document.getChildNodes();
			for (int i = 0; i < objects.getLength(); i++) {
				Node obj = objects.item(i);
				NodeList objInfo = obj.getChildNodes();
				for (int j = 0; j < objInfo.getLength(); j++) {
					Product p=new Product();
					Node node = objInfo.item(j);
					NodeList objMeta = node.getChildNodes();
					for (int k = 0; k < objMeta.getLength(); k++) {
						String nodeName=objMeta.item(k).getNodeName();
						String content=objMeta.item(k).getTextContent();
						try {
							Field field=p.getClass().getDeclaredField(nodeName);
							if (field!=null) {
								field.setAccessible(true);
								if (field.getType().getName().equals("float")){
									field.set(p, Float.parseFloat(content));
								}else if(field.getType().getName().equals("int")){
									field.set(p, Integer.parseInt(content));
								}else{field.set(p, content);}
							}
						} catch (IllegalArgumentException e) {continue;
						} catch (IllegalAccessException e) {continue;
						} catch (NoSuchFieldException e) {continue;
						} catch (SecurityException e) {continue;
						}
					}
					if (objMeta.getLength()>0) {targetStore.add(p);}
				}
			}
		} catch (FileNotFoundException e) {System.out.println(e.getMessage());
		} catch (ParserConfigurationException e) {System.out.println(e.getMessage());
		} catch (SAXException e) {System.out.println(e.getMessage());
		} catch (IOException e) {System.out.println(e.getMessage());
		}finally{
			try {
				resouceFile.close();
			} catch (IOException e) {System.out.println(e.getMessage());
			}
		}
		return targetStore;
	}
}
