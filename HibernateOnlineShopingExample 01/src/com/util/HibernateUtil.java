package com.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import model.AddToChart;
import model.Product;
import model.ProductOrder;
import model.User;

public class HibernateUtil {

	private static SessionFactory sf = null;
	private static StandardServiceRegistry registry;

	public static SessionFactory getSessionFactory() {

		if (sf == null) {
			Map<String, Object> map = new HashMap<>();

			map.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
			map.put(Environment.URL, "jdbc:mysql://localhost:3306/OnlineShopping");
			map.put(Environment.USER, "root");
			map.put(Environment.PASS, "root");

			map.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
			map.put(Environment.SHOW_SQL, true);
			map.put(Environment.HBM2DDL_AUTO, "update");

			
			registry = new StandardServiceRegistryBuilder().applySettings(map).build();
			MetadataSources mds = new MetadataSources(registry).addAnnotatedClass(Product.class).addAnnotatedClass(User.class).addAnnotatedClass(AddToChart.class).addAnnotatedClass(ProductOrder.class);
			Metadata md = mds.getMetadataBuilder().build();

			sf = md.buildSessionFactory();

		}

		return sf;
	}


}
