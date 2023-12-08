package com.seclore.main;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

import com.seclore.annotations.JavaFileInfo;
import com.seclore.pojo.DemoClass;

public class ProcessAnnotationMain {

	public static void main(String[] args) {
		Class<DemoClass> demoClass = DemoClass.class;
		System.out.println("Annotations for " + demoClass.getName());
		readAnnotation(demoClass);

		try {
			System.out.println("Annotations for printMessage");
			Method method = demoClass.getMethod("printMessage", new Class[] {});
			readAnnotation(method);
		} catch (NoSuchMethodException | SecurityException e) {
			System.err.println(e.getMessage());
		}

		Method[] methods = demoClass.getMethods();
		for (Method method : methods) {
			System.out.println("Annotations for " + method.getName());
			readAnnotation(method);
		}
	}

	public static void readAnnotation(AnnotatedElement annotatedElement) {
		Annotation[] annotations = annotatedElement.getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation instanceof JavaFileInfo) {
				JavaFileInfo fileInfo = (JavaFileInfo) annotation;
				System.out.println("Author: " + fileInfo.author());
				System.out.println("Version: " + fileInfo.version());
			}
		}
	}

}
