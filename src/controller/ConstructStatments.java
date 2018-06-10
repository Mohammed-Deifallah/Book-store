package controller;

import java.util.ArrayList;

public class ConstructStatments {

	private static int limitNum = 10;

	public static String getInsertQuery(String tableName, ArrayList<String> columnNames, ArrayList<String> values) {
		String query = "INSERT INTO " + tableName + "(" + commaSep(columnNames) + ")" + " VALUES " + "("
				+ commaSep(values) + ")";
		return query;
	}

	public static String getSelectQuery(String tableName, ArrayList<String> columnNames, int offset) {
		String query = " SELECT " + commaSep(columnNames) + " FROM " + tableName + " LIMIT " + offset + " , "
				+ (offset + limitNum);
		return query;
	}

	public static String getUpdateQuery(String tableName, ArrayList<Assignment> assignments,
			ArrayList<Condition> conditions, boolean and) {
		String query = "UPDATE " + tableName + " SET " + getAssignment(assignments) + " WHERE "
				+ getCondition(conditions, and);
		return query;
	}
	
	public static String getSelectConditionalQuery(String tableName, ArrayList<String> columnToSelect,
			ArrayList<Condition> conditions, boolean and, int offset) {
		String query = "SELECT " + commaSep(columnToSelect) + " FROM " + tableName + " WHERE "
				+ getCondition(conditions, and) + " LIMIT " + offset + " , " + (offset + limitNum);
		return query;
	}

	public static String getDelete(String tableName, ArrayList<Condition> conditions, boolean and) {
		String query = "DELETE FROM " + tableName +
					   " WHERE " + getCondition(conditions, and);
		return query;
	}
	
	private static String commaSep(ArrayList<String> cols) {
		return String.join(",", cols);
	}

	private static String getAssignment(ArrayList<Assignment> assignments) {
		String assignString = "";
		String sep = ",";
		for (int i = 0; i < assignments.size() - 1; i++) {
			assignString += assignments.get(i).getAll() + sep;
		}
		assignString += assignments.get(assignments.size() - 1).getAll();
		return assignString;
	}

	private static String getCondition(ArrayList<Condition> conditions, boolean and) {
		String condiString = "";
		String sep = and == true ? " AND " : " OR ";
		for (int i = 0; i < conditions.size() - 1; i++) {
			condiString += conditions.get(i).getAll() + sep;
		}
		condiString += conditions.get(conditions.size() - 1).getAll();
		return condiString;
	}
	
	
}
