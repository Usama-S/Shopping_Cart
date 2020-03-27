
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class CartItems {

	String data;
	int i, amount, index;
	String ttlPrice;
	
	int sum = 0;
	int lastGpIndex;
	
	String customerName;
	String customerPh;
	String customerAdd;
	
	void orderExtraction (GridPane gp) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("orderData.xml");
			NodeList name = doc.getElementsByTagName("name");
			Node cName = name.item(0);
			NodeList ph = doc.getElementsByTagName("ph");
			Node cPh = ph.item(0);
			NodeList add = doc.getElementsByTagName("add");
			Node cAdd = add.item(0);
			
			if ((cName.getNodeType() == Node.ELEMENT_NODE) && (cName.getNodeType() == Node.ELEMENT_NODE) && (cName.getNodeType() == Node.ELEMENT_NODE)){
				Element cusName = (Element) cName;
				customerName = cusName.getTextContent();
				Element cusPh = (Element) cPh;
				customerPh = cusPh.getTextContent();
				Element cusAdd = (Element) cAdd;
				customerAdd = cusAdd.getTextContent();
				
				//System.out.println(customerName + "\n" + customerPh + "\n" + customerAdd);
			}
			NodeList order = doc.getElementsByTagName("order");
			//amount = ((products.getLength()));
			
			for (int i = 0; i < order.getLength(); i++){
				Node orderNode = order.item(i);
				if (orderNode.getNodeType() == Node.ELEMENT_NODE){
					Element orderElement = (Element) orderNode;
					NodeList products = orderElement.getChildNodes();
					amount = products.getLength();
					Text t1[] = new Text[amount];
					Text t2[] = new Text[amount];
					Text t3[] = new Text[amount];
					Text t4[] = new Text[amount];
					for (int j = 0; j < products.getLength(); j++){
						Node product = products.item(j);
						if (product.getNodeType() == Node.ELEMENT_NODE){
						Element productElement = (Element) product;
						NodeList itemDetails = productElement.getChildNodes();
						Node detail = (Node) itemDetails;
						if (detail.getNodeType() == Node.ELEMENT_NODE){
							Element item = (Element) itemDetails;
							NodeList title = item.getElementsByTagName("title");
							Node ttle = title.item(0);
							Element ttl = (Element) ttle;
							NodeList price = item.getElementsByTagName("price");
							Node prc = price.item(0);
							Element pr = (Element) prc;
							NodeList qty = item.getElementsByTagName("qty");
							Node qt = qty.item(0);
							Element q = (Element) qt;
							
							sum = sum + Integer.parseInt(pr.getTextContent());
							
							t1[j] = new Text(ttl.getTextContent());
							t2[j] = new Text(pr.getTextContent());
							t3[j] = new Text(q.getTextContent());
							
							ttlPrice = pr.getTextContent();
							
							/*
							index = j;
							t3[index].setOnKeyReleased(new EventHandler<Event>() {

								@Override
								public void handle(Event event) {
									// TODO Auto-generated method stub
									int x = quantity(Integer.parseInt(t3[index].getText()), Integer.parseInt(pr.getTextContent()));
									t4[index].setText(Integer.toString(x));
								}
							});
							*/
							t4[j] = new Text();
							t4[j].setText(ttlPrice);
							
							gp.add(t1[j], 0, j+5);
							gp.add(t2[j], 1, j+5);
							gp.add(t3[j], 2, j+5);
							gp.add(t4[j], 3, j+5);
							
							lastGpIndex = j+5;
							
						}
						
					}
				}
			}
		}
		
	}catch (ParserConfigurationException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (SAXException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	} catch (IOException e3) {
		// TODO Auto-generated catch block
		e3.printStackTrace();
	}
	
	//String returnOrderData(){
		//return toExport;
	}
	
	int quantity(int qty, int price){
		int pricee = qty * price;
		return pricee;
	}
}
