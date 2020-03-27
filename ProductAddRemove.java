
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ProductAddRemove{
	
	String fileData = "";
	int prodId;
	boolean prodRemoved = false;
	
	void addProduct(String title, int price, String filePath, String category, String description){
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("products.xml");
			fileData += "<products>\n";
			NodeList products = doc.getElementsByTagName("product");
			for (int i = 0; i < products.getLength(); i++){
				fileData += "\t<product id=\"";
				Node p = products.item(i);
				if (p.getNodeType() == Node.ELEMENT_NODE){
					Element product = (Element) p;
					prodId = Integer.parseInt(product.getAttribute("id"));
					fileData += prodId + "\">\n";
					NodeList nameList = product.getChildNodes();
					for (int j = 0; j < nameList.getLength(); j++){
						Node n = nameList.item(j);
						if (n.getNodeType() == Node.ELEMENT_NODE){
							Element name = (Element) n;
							fileData += "\t\t<" + name.getTagName() + ">" + name.getTextContent() + "</" + name.getTagName() + ">\n";
						}
					}
				}
				fileData += "\t</product>\n";
			}
			
			fileData += "\t<product id=\"" + ++prodId + "\">\n";
			fileData += "\t\t<imgsource>" + filePath + "</imgsource>\n";
			fileData += "\t\t<category>" + category + "</category>\n";
			fileData += "\t\t<title>" + title + "</title>\n";
			fileData += "\t\t<price>" + price + "</price>\n";
			fileData += "\t\t<description>" + description + "</description>\n";
			fileData += "\t</product>\n";
			
			fileData += "</products>";
			
			FileWriter file = new FileWriter("products.xml");
			file.write(fileData);
			file.close();
			
			//System.out.println(fileData);
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e2) {
			e2.printStackTrace();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
	}
	
	boolean ifTrue(String toCheck){
		boolean isFound = false;
		DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder builder1 = factory1.newDocumentBuilder();
			Document doc1 = builder1.parse("Users.xml");
			NodeList emails = doc1.getElementsByTagName("email");
			for (int i = 0; i < emails.getLength(); i++){
				if (toCheck.equals(emails.item(i).getTextContent())){
					isFound = true;
				}
			}
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e2) {
			e2.printStackTrace();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		return isFound;
	}
	
	

	boolean removeProduct(int idToRemove){
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("products.xml");
			fileData += "<products>\n";
			NodeList products = doc.getElementsByTagName("product");
			for (int i = 0; i < products.getLength(); i++){
				Node p = products.item(i);
				if (p.getNodeType() == Node.ELEMENT_NODE){
					Element product = (Element) p;
					prodId = Integer.parseInt(product.getAttribute("id"));
					
					if (prodId != idToRemove){
					
						fileData += "\t<product id=\"";
						fileData += prodId + "\">\n";
						NodeList nameList = product.getChildNodes();
						for (int j = 0; j < nameList.getLength(); j++){
							Node n = nameList.item(j);
							if (n.getNodeType() == Node.ELEMENT_NODE){
								Element name = (Element) n;
								fileData += "\t\t<" + name.getTagName() + ">" + name.getTextContent() + "</" + name.getTagName() + ">\n";
							}
						}
						fileData += "\t</product>\n";
					}
					else{
						prodRemoved = true;
					}
				}
			}
			fileData += "</products>";
			
			FileWriter file = new FileWriter("products.xml");
			file.write(fileData);
			file.close();
			
			//System.out.println(fileData);
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e2) {
			e2.printStackTrace();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		return prodRemoved;
	}
	
	
}