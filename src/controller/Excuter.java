package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class Excuter implements StatmentsIF{

	private Connection conn;
	private Statement stat;
	public Excuter(Connection conn) throws SQLException {
		this.conn = conn;
		this.stat = this.conn.createStatement();
	}
	
	@Override
	public ResultSet insert(String tableName, ArrayList<String> columnNames, ArrayList<String> values) throws SQLException {
		// TODO Auto-generated method stub
		String query = ConstructStatments.getInsertQuery(tableName, columnNames, values);
		return stat.executeQuery(query);
	}

	@Override
	public ResultSet select(String tableName, ArrayList<String> columnNames, int offset) throws SQLException {
		// TODO Auto-generated method stub
		String query = ConstructStatments.getSelectQuery(tableName, columnNames, offset);
		return stat.executeQuery(query);
	}

	@Override
	public ResultSet selectConditional(String tableName, ArrayList<String> columnToSelect, ArrayList<Condition> conditions,
			boolean and, int offset) throws SQLException{
		// TODO Auto-generated method stub
		String query = ConstructStatments.getSelectConditionalQuery(tableName, columnToSelect, conditions, and, offset);
		return stat.executeQuery(query);
	}

	@Override
	public ResultSet update(String tableName, ArrayList<Assignment> assignments, ArrayList<Condition> conditions,
			boolean and) throws SQLException{
		// TODO Auto-generated method stub
		String query = ConstructStatments.getUpdateQuery(tableName, assignments, conditions, and);
		return stat.executeQuery(query);
	}
	
	@Override
	public ResultSet totalSalesBooks() throws SQLException {
		String query = "SELECT SUM(price * quantity) " +
					   "FROM quantity_table";
		return stat.executeQuery(query);
	}
	
	@Override
	public ResultSet top5Customers() throws SQLException {
		String currentMonth = Integer.toString(Calendar.getInstance().get(Calendar.MONTH) + 1);
		String query = "SELECT *, SUM(l.quantity)" +
					   "FROM user as u " +
				       "JOIN log as l " +
					   "ON l.email = u.email " +
				       "WHERE " + currentMonth +" - MONTH(l.sell_date) <= 3 " +
				       "ORDER BY 2 DESC " +
					   "LIMIT 5";
		return stat.executeQuery(query);
	}
	
	@Override
	public ResultSet top10Books() throws SQLException {
		String currentMonth = Integer.toString(Calendar.getInstance().get(Calendar.MONTH) + 1);
		String query = "SELECT *, SUM(l.quantity) " +
					   "FROM book as b " +
				       "JOIN log as l " +
					   "ON b.ISBN = l.ISBN " +
					   "WHERE " + currentMonth +" - MONTH(l.sell_date) <= 3 " +
				       "ORDER BY 2 DESC " +
					   "LIMIT 10";
		return stat.executeQuery(query);
	}
	
}
