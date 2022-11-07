package com.model;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

public class HibernateUtil {

	private static StandardServiceRegistry registry=null;
	
	private static SessionFactory sf = null;
	
	public static SessionFactory getSessionFactory() {
		
		if (sf == null) {
		Map<String, Object>map=new HashMap<>();
		
		map.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
		map.put(Environment.URL, "jdbc:mysql://localhost:3306/myHibernateAutokeyGeneration");
		map.put(Environment.USER, "root");
		map.put(Environment.PASS,"root");
		
		map.put(Environment.DIALECT,"org.hibernate.dialect.MySQLDialect");
		map.put(Environment.SHOW_SQL, true);
		map.put(Environment.HBM2DDL_AUTO, "update");
		
		registry =new StandardServiceRegistryBuilder().applySettings(map).build();
		MetadataSources mrs =new MetadataSources(registry).addAnnotatedClass(Customer.class).addAnnotatedClass(Product.class);
		Metadata md = mrs.getMetadataBuilder().build();
		sf =md.buildSessionFactory();
		}
		return sf;
		
	}
}
