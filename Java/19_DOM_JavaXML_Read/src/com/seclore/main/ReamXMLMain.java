package com.seclore.main;

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

public class ReamXMLMain {

	public static void main(String[] args) {
		File xmlFile = new File("users.xml");

		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);

			document.getDocumentElement().normalize();

			System.out.println("Root Element: " + document.getDocumentElement().getNodeName());

			NodeList users = document.getElementsByTagName("user");
			for (int i = 0; i < users.getLength(); i++) {
				Node node = users.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element userElement = (Element) node;
					String id = userElement.getAttribute("id");
					String firstName = userElement.getElementsByTagName("firstname").item(0).getTextContent();
					String lastName = userElement.getElementsByTagName("lastname").item(0).getTextContent();
					String occupation = userElement.getElementsByTagName("occupation").item(0).getTextContent();

					System.out.println("id: " + id);
					System.out.println("firstname: " + firstName);
					System.out.println("lastname: " + lastName);
					System.out.println("occupation: " + occupation);
					System.out.println();

				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}

}
