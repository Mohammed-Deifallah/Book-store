package controller;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Excuter ex = new Excuter(Connector.getInstance());
			String tableName = "book";
			ArrayList<Condition> conditions = new ArrayList<>();
			Condition c = new Condition("ISBN", "=", "\"5\"");
			conditions.add(c);
			c = new Condition("ISBN", "=", "\"30\"");
			conditions.add(c);
			ex.delete(tableName, conditions, false);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
