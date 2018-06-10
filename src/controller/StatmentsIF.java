/**
 * 
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Arsanuos
 *
 */
public interface StatmentsIF {
	/**
	 * inserts into table
	 * 
	 * INSERT INTO table_name(COULMN_NAMES,...)
	 * VALUES (val1, val2,...)
	 * 
	 * @return string that indicates either the values inserted or not.
	 */
	public boolean insert(String tableName, ArrayList<String> columnNames, ArrayList<String> values) throws SQLException;
	
	/**
	 * selects from table
	 * 
	 * SELECT column_names 
	 * FROM table_name
	 * LIMIT 10 OFFSET val
	 * 
	 * @param 
	 * table_name -> table name.
	 * columnName -> array list of columns to be selected.
	 * offset -> number to represent the lazy load for select as we will select only 10 at at time.
	 * 
	 * @return string that indicates either the values returned or error. 
	 */
	public ResultSet select(String tableName, ArrayList<String> columnNames, int offset) throws SQLException;
	
	/**
	 * select conditional
	 * 
	 * SELECT column_names
	 * FROM table_name
	 * WHERE condition1 and condition2 or condtion3
	 * LIMIT 10 OFFSET val
	 * 
	 * @param 
	 * table_name -> table name.
	 * columnToSelect -> columns to project from table.
	 * condition -> list of conditions
	 * and -> boolean to indicate if the operation between all conditions is AND(True) or OR(False)
	 * offset -> value for offset as we use lazy loading.
	 */
	public ResultSet selectConditional(String tableName, ArrayList<String> columnToSelect,
			ArrayList<Condition> conditions, boolean and , int offset) throws SQLException;
	
	/**
	 * UPDATE table_name SET column_name = val
	 * WHERE condtion1 and condition2 and conditon3
	 * 
	 * @return
	 * table_name -> table name.
	 * leftHandSide -> left side of the assignment
	 * values -> list of values.
	 * and -> boolean to indicate if the operation between all conditions is AND(True) or OR(False)
	 * conditions -> condition to set the values on.
	 */
	public ResultSet update(String tableName, ArrayList<Assignment> assignments, ArrayList<Condition> conditions, boolean and) throws SQLException;
	
	public ResultSet top10Books() throws SQLException;
	
	public ResultSet top5Customers() throws SQLException;
	
	public ResultSet totalSalesBooks() throws SQLException;
	
	public boolean delete(String tableName, ArrayList<Condition> conditions, boolean and) throws SQLException;
	
	
}
