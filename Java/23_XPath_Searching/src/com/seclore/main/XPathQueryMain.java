package com.seclore.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XPathQueryMain {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
			Document document = builder.parse("employees.xml");

			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xPath = xPathFactory.newXPath();

			System.out.println("Employee 3: " + getEmployeesById(document, xPath, 3));
			System.out.println("Female Employees: " + getFemaleEmployees(document, xPath));
			System.out.println("Employees Older Then 30: " + getEmployeesOlderThan(document, xPath, 30));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}

	public static List<String> getFemaleEmployees(Document document, XPath xPath) {
		List<String> names = new ArrayList<String>();

		try {
			XPathExpression expression = xPath.compile("/Employees/Employee[gender='female']/name/text()");
			NodeList nodes = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
			for (int i = 0; i < nodes.getLength(); i++) {
				names.add(nodes.item(i).getTextContent());
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return names;
	}

	public static List<String> getEmployeesOlderThan(Document document, XPath xPath, int age) {
		List<String> names = new ArrayList<String>();

		try {
			XPathExpression expression = xPath.compile("/Employees/Employee[age>" + age + "]/name/text()");
			NodeList nodes = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
			for (int i = 0; i < nodes.getLength(); i++) {
				names.add(nodes.item(i).getTextContent());
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return names;
	}

	public static String getEmployeesById(Document document, XPath xPath, int employeeId) {
		String name = "";
		try {
			XPathExpression expression = xPath.compile("/Employees/Employee[@id='" + employeeId + "']/name/text()");
			name = (String) expression.evaluate(document, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return name;
	}

}
