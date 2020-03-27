
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LoginValidation {
	
	String toExport = "";
	
	boolean findRecord (String email, String password, String orderData) {
		boolean status = false;		
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("Users.xml");
			NodeList persons = doc.getElementsByTagName("user");
			for (int i = 0; i < persons.getLength(); i++){
				Node p = persons.item(i);
				if (p.getNodeType() == Node.ELEMENT_NODE){
					Element person = (Element) p;
					NodeList nameList1 = person.getElementsByTagName("email");
					NodeList nameList2 = person.getElementsByTagName("pass");
					//extra
					NodeList nameList3 = person.getElementsByTagName("name");
					NodeList nameList4 = person.getElementsByTagName("ph");
					NodeList nameList5 = person.getElementsByTagName("add");
					for (int j = 0; j < nameList1.getLength(); j++){
						Node mail = nameList1.item(j);
						Node passcode = nameList2.item(j);
						//extra
						Node customerName = nameList3.item(j);
						Node customerPh = nameList4.item(j);
						Node customeradd = nameList5.item(j);
						if ((mail.getNodeType() == Node.ELEMENT_NODE) && (passcode.getNodeType() == Node.ELEMENT_NODE)){
							Element mailId = (Element) mail;
							Element pass = (Element) passcode;
							//extra
							Element cName = (Element) customerName;
							Element cPh = (Element) customerPh;
							Element cAdd = (Element) customeradd;
							if ((mailId.getTextContent().equals(email)) && (pass.getTextContent().equals(password))){
								status = true;
								orderData += "<orderdata>\n";
								orderData += "\t<name>" + cName.getTextContent() + "</name>\n";
								orderData += "\t<ph>" + cPh.getTextContent() + "</ph>\n";
								orderData += "\t<add>" + cAdd.getTextContent() + "</add>\n";
								orderData += "\t<order>\n";
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
		
		toExport = orderData;
		return status; 
	}
	
	String returnOrderData(){
		return toExport;
	}
}
