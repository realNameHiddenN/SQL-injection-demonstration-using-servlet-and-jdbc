package com.test;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.test.entity.Course;
import com.test.entity.Student;
import com.test.util.HibernateUtil;

public class Test {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//get session
		Session ses = HibernateUtil.getSession();
		try(factory;ses){
			ses.beginTransaction();
			
			Course c1 = new Course(1,"Math",5000.0);
			Course c2 = new Course(2,"Science",4000.0);
			Course c3 = new Course(3,"History",6000.0);
			
			Student s1 = new Student(101,"Ajay",97.2,Set.of(c1,c2));
			Student s2 = new Student(102,"Vijay",98.2,Set.of(c2,c3));
			
			//save
			ses.save(c1);
			ses.save(c2);
			ses.save(c3);
			ses.save(s1);
			ses.save(s2);
			
			//commit the tx
			ses.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
