package com.revature.util;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sf;
	
	public static SessionFactory getSessionFactory() throws IOException {
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		prop.load(loader.getResourceAsStream("hibernate.properties"));
		
		if(sf == null || sf.isClosed()) {
			sf = new Configuration().mergeProperties(prop).configure("hibernate.cfg.xml").buildSessionFactory();
		}
		
		return sf;
	}			
}
