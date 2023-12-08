package com.seclore.main;

import java.io.File;

import com.seclore.pojo.Employee;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class XMLToObjectMain {

	public static void main(String[] args) {
		try {
			JAXBContext context = JAXBContext.newInstance(Employee.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			File marshalledFile = new File("divy.xml");
			Employee divy = (Employee) unmarshaller.unmarshal(marshalledFile);
			
			System.out.println(divy);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
