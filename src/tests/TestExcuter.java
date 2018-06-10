package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.Connector;
import controller.Excuter;

class TestExcuter {

	@Test
	void insert_test1() {
		
		Connection conn = null;
		Excuter ex = null;
		try {
			conn = Connector.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("can't get connection");
		}
		try {
			ex = new Excuter(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("can't make instance of exuter.");
		}
		ArrayList<String> values = new ArrayList<String>();
		values.add("5");
		values.add("\"the title\"");
		values.add("1999");
		values.add("\"action\"");
		values.add("\"el solbky\"");
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("ISBN");
		columnNames.add("title");
		columnNames.add("pyear");
		columnNames.add("category");
		columnNames.add("publisher_name");
		String table_name = "book";
		try {
			ex.insert(table_name, columnNames, values);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("Can't insert in " + table_name + " " + e.getMessage());
		}
		
	}
	

	@Test
	void insert_test2() {
		
		Connection conn = null;
		Excuter ex = null;
		try {
			conn = Connector.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("can't get connection");
		}
		try {
			ex = new Excuter(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("can't make instance of exuter.");
		}
		ArrayList<String> values = new ArrayList<String>();
		values.add("\"el solbky\"");
		values.add("\"alex\"");
		values.add("0124");
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("name");
		columnNames.add("address");
		columnNames.add("telephone");
		String table_name = "publisher";
		try {
			ex.insert(table_name, columnNames, values);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("Can't insert in " + table_name + " " + e.getMessage());
		}
	}

}
