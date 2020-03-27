
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

public class UserDataEntry{
	
	String fileData = "";
	int userId;
	
	void enterRecord(String fullname, String password, String mailAdd, String phNum, String homeAdd){
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("Users.xml");
			fileData += "<users>\n";
			NodeList persons = doc.getElementsByTagName("user");
			for (int i = 0; i < persons.getLength(); i++){
				fileData += "\t<user id=\"";
				Node p = persons.item(i);
				if (p.getNodeType() == Node.ELEMENT_NODE){
					Element person = (Element) p;
					userId = Integer.parseInt(person.getAttribute("id"));
					fileData += userId + "\">\n";
					NodeList nameList = person.getChildNodes();
					for (int j = 0; j < nameList.getLength(); j++){
						Node n = nameList.item(j);
						if (n.getNodeType() == Node.ELEMENT_NODE){
							Element name = (Element) n;
							fileData += "\t\t<" + name.getTagName() + ">" + name.getTextContent() + "</" + name.getTagName() + ">\n";
						}
					}
				}
				fileData += "\t</user>\n";
			}
			
			fileData += "\t<user id=\"" + ++userId + "\">\n";
			fileData += "\t\t<name>" + fullname + "</name>\n";
			fileData += "\t\t<pass>" + password + "</pass>\n";
			fileData += "\t\t<email>" + mailAdd + "</email>\n";
			fileData += "\t\t<type>customer</type>\n";
			fileData += "\t\t<ph>" + phNum + "</ph>\n";
			fileData += "\t\t<add>" + homeAdd + "</add>\n";
			fileData += "\t</user>\n";
			
			fileData += "</users>";
			
			FileWriter file = new FileWriter("Users.xml");
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
	
	
}