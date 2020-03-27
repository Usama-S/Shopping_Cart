
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ProductExtraction {
	
	int i, orderCount;
	
	void setOrderCount(int count){
		orderCount = count;
	}
	
	List<String> products = new ArrayList<>();
	List<Integer> prices = new ArrayList<>();
	
	void findRecord (GridPane gp, String filter) {
		int amount;
		i = 0;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("products.xml");
			NodeList products = doc.getElementsByTagName("product");
			amount = ((products.getLength()));
			ImageView img[] = new ImageView[amount];
			Text title[] = new Text[amount];
			Text desc[] = new Text[amount];
			Text pri[] = new Text[amount];
			Button add[] = new Button[amount];
			
			Label l1 = new Label("Name");
			l1.setPrefHeight(100);
			l1.setStyle("-fx-font-weight: bold");
			Label l2 = new Label("Description");
			l1.setPrefHeight(100);
			l2.setStyle("-fx-font-weight: bold");
			Label l3 = new Label("Price");
			l1.setPrefHeight(100);
			l3.setStyle("-fx-font-weight: bold");
			
			gp.add(l1, 1, 0);
			gp.add(l2, 2, 0);
			gp.add(l3, 3, 0);
			
			for (i = 0; i < products.getLength(); i++){
				Node p = products.item(i);
				if (p.getNodeType() == Node.ELEMENT_NODE){
					Element product = (Element) p;
					NodeList nameList1 = product.getElementsByTagName("imgsource");
					NodeList nameList2 = product.getElementsByTagName("title");
					NodeList nameList3 = product.getElementsByTagName("description");
					NodeList nameList4 = product.getElementsByTagName("price");
					NodeList nameList5 = product.getElementsByTagName("category");
					for (int j = 0; j < nameList1.getLength(); j++){
						Node imgsrc = nameList1.item(j);
						Node ttl = nameList2.item(j);
						Node des = nameList3.item(j);
						Node pr = nameList4.item(j);
						Node cat = nameList5.item(j);
						if ((imgsrc.getNodeType() == Node.ELEMENT_NODE) && (ttl.getNodeType() == Node.ELEMENT_NODE)){
							
							Element image = (Element) imgsrc;
							Element prodtitle = (Element) ttl;
							Element prodDes = (Element) des;
							Element prodpr = (Element) pr;
							Element ctgry = (Element) cat;
							
							if (filter == "all"){
							img[i] = new ImageView(image.getTextContent());
							img[i].setFitHeight(250);
							img[i].setFitWidth(180);
							
							title[i] = new Text(prodtitle.getTextContent());
							title[i].setStyle("-fx-font-size: 16pt");
							desc[i] = new Text(prodDes.getTextContent());
							desc[i].setStyle("-fx-font-size: 12pt");
							pri[i] = new Text(prodpr.getTextContent());
							pri[i].setStyle("-fx-font-size: 16pt;");
							
							add[i] = new Button("Add to cart");
							
							add[i].setOnAction(new EventHandler<ActionEvent>() {
								
								@Override
								public void handle(ActionEvent arg0) {
									// TODO Auto-generated method stub
									alerty();									
								}
							});
							
							gp.add(img[i], 0, i+1);
							gp.add(title[i], 1, i+1);
							gp.add(desc[i], 2, i+1);
							gp.add(pri[i], 3, i+1);
							gp.add(add[i], 4, i+1);
							}
							else {
								if (filter.equals(ctgry.getTextContent())){
									img[i] = new ImageView(image.getTextContent());
									img[i].setFitHeight(250);
									img[i].setFitWidth(180);
									
									title[i] = new Text(prodtitle.getTextContent());
									title[i].setStyle("-fx-font-size: 16pt");
									desc[i] = new Text(prodDes.getTextContent());
									desc[i].setStyle("-fx-font-size: 12pt");
									pri[i] = new Text(prodpr.getTextContent());
									pri[i].setStyle("-fx-font-size: 16pt;");

									add[i] = new Button("Add to cart");
									
									add[i].setOnAction(new EventHandler<ActionEvent>() {
										
										@Override
										public void handle(ActionEvent arg0) {
											// TODO Auto-generated method stub
											alerty();									
										}
									});
									
									gp.add(img[i], 0, i+1);
									gp.add(title[i], 1, i+1);
									gp.add(desc[i], 2, i+1);
									gp.add(pri[i], 3, i+1);
									gp.add(add[i], 4, i+1);
																		
								}
							
							}
							
						}
					}
				}
			}
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
	}
	
	void alerty(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Sab ok he" + orderCount);
		alert.show();
		orderCount++;
	}
}
