package com.wut;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.wut.datasources.jdbc.derby.Derby;

public class DerbyTest {
	
	private static final String SECOND_ID = "l62562242342544";
	private static final String FIRST_ID = "l62562525362543";

	//@Test
	public void connection() {
		Derby db = new Derby();
		String tableName = "webutilitykit";
		List<Map<String, String>> orders = db.get("testCustomer", "testApp", tableName);
		System.out.println("Orders = " + orders);
		
		HashMap<String, String> order = new HashMap<String, String>();
		order.put("id", FIRST_ID);
		order.put("_customer", "www.retailkit.com");
		order.put("_table", "order");
		order.put("_updated", "1406241534");
		order.put("_created", "1406241534");
		order.put("_updator", "russell@retailkit.com");
		order.put("_creator", "jeremy@retailkit.com");
		String id = db.insert("testCustomer", "testApp", tableName, order);
		System.out.println("New Order Id = " + id);
		
		order.put("id", SECOND_ID);
		String id2 = db.insert("testCustomer", "testApp", tableName, order);
		System.out.println("New Order Id = " + id2);
		
		orders = db.get("testCustomer", "testApp", tableName);
		System.out.println("Orders = " + orders);
		
		Map<String, String> newOrder2 = db.get("testCustomer", "testApp", tableName, id2);
		System.out.println("Order " + id2 + " = " + newOrder2);

		/*
		 *        String createTableSql = "CREATE TABLE webutilitykit " +
                         "(id VARCHAR(255) not NULL, " +
                         " customer VARCHAR(255), " + 
                         " tablename VARCHAR(255), " + 
                         " updated INTEGER, " + 
                         " created INTEGER, " + 
                         " updator VARCHAR(255), " + 
                         " creator VARCHAR(255), " + 
                         " data VARCHAR(255), " + 
                         " PRIMARY KEY ( id ))";

                         */
		
		db.remove("testCustomer", "testApp", tableName, FIRST_ID);
		db.remove("testCustomer", "testApp", tableName, SECOND_ID);
	 }
	
	
}
