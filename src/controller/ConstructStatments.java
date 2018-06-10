package controller;

import java.util.ArrayList;

public class ConstructStatments {

	private static int limitNum = 10;
	public static String getInsertQuery(String tableName, ArrayList<String> columnNames, ArrayList<String> values) {
		String query = "INSERT INTO " + tableName + "(" + commaSep(columnNames) + ")"+
						" VALUES " + "(" + commaSep(values) + ")";
		return query;
	}
	
	
	public static String getSelectQuery(String tableName, ArrayList<String> columnNames, int offset) {
		String query = " SELECT " + commaSep(columnNames) +
					   " FROM " + tableName +
					   " LIMIT " + limitNum + " OFFSET " + offset;
		return query;
	}
	
	public static String getUpdateQuery(String tableName, ArrayList<Assignment> assignments,
			ArrayList<Condition> conditions, boolean and) {
		String query = "UPDATE " + tableName +
					   " SET " + getAssignment(assignments) +
					   " WHERE " + getCondition(conditions, and);
		return query;
	}
	
	protected static String getSelectConditionalQuery(String tableName, ArrayList<String> columnToSelect,
			ArrayList<Condition> conditions, boolean and, int offset) {
		String query = "SELECT " + commaSep(columnToSelect) +
				       " FROM " + tableName +
				       " WHERE " + getCondition(conditions, and) +
				       " LIMIT " + limitNum + " OFFSET " + offset;
		return query;
	}
	
	private static String commaSep(ArrayList<String> cols) {
		return String.join(",", cols);
	}
	
	private static String getAssignment(ArrayList<Assignment> assignments) {
		String assignString = "";
		String sep = ",";
		for(Assignment assign : assignments) {
			assignString += assign.getAll() + sep;
		}
		return assignString;
	}
	
	private static String getCondition(ArrayList<Condition> conditions, boolean and) {
		String condiString = "";
		String sep = and == true ? " AND " : " OR ";
		for(Condition condition : conditions) {
			condiString += condition.getAll() + sep;
		}
		return condiString;
	}
}
