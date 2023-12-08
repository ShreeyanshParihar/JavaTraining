package com.seclore.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.seclore.pojo.Employee;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public class ObjectToXMLMain {

	public static void main(String[] args) {
		try {
			JAXBContext context = JAXBContext.newInstance(Employee.class);
			Marshaller marshaller = context.createMarshaller();
			
			Employee divy = new Employee(101, "Divy Jain", 100000); 
			marshaller.marshal(divy, new FileOutputStream("divy.xml"));

		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
