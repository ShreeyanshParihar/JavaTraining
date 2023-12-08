package com.seclore.main;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class WriteXMLMain {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.newDocument();
			
			Element root = document.createElement("users");
			document.appendChild(root);

			root.appendChild(createUser(document, "1", "Divya", "Jain", "Developer"));
			root.appendChild(createUser(document, "2", "Vivek", "Gohil", "Teacher"));
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			DOMSource source = new DOMSource(document);
			File xmlFile = new File("users.xml");

			StreamResult console = new StreamResult(System.out);
			StreamResult streamFile = new StreamResult(xmlFile);

			transformer.transform(source, streamFile);
			transformer.transform(source, console);
			
			
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}

	}
	
	public static Node createUser(Document document, String id, String firstName, String lastName, String occupation) {
		Element user = document.createElement("user");
		
		user.setAttribute("id", id);
		user.appendChild(createTextNode(document, "firstname", firstName));
		user.appendChild(createTextNode(document, "lastname", lastName));
		user.appendChild(createTextNode(document, "occupation", occupation));
		
		return user;
	}

	public static Node createTextNode(Document document, String tagname, String value) {
		Element element = document.createElement(tagname);
		element.setTextContent(value);
		return element;
	}
}
