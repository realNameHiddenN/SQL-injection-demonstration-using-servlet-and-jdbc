package com.test.util;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.test.entity.Course;
import com.test.entity.Student;

public class HibernateUtil {
	static SessionFactory factory = null;
	static {
		Configuration cfg = new Configuration();
		
		Properties prop = new Properties();
		
		prop.put(Environment.URL, "jdbc:mysql://localhost:3306/new");
		prop.put(Environment.USER, "root");
		prop.put(Environment.PASS, "root");
		prop.put(Environment.SHOW_SQL,true);
		prop.put(Environment.FORMAT_SQL, true);
		prop.put(Environment.HBM2DDL_AUTO, "update");
		
		cfg.setProperties(prop);
		cfg.addAnnotatedClass(Student.class);
		cfg.addAnnotatedClass(Course.class);
		factory = cfg.buildSessionFactory();
	}
	public static SessionFactory getSessionFactory() {
		return factory;
	}
	public static Session getSession() {
		return factory.openSession();
	}
}
