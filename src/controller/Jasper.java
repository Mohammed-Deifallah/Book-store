package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Jasper {
	private JasperReport jr;
	private JRResultSetDataSource dataSource;
	private JasperPrint jp;
	private Connection con;
	private Excuter ex;

	public Jasper() throws ClassNotFoundException, SQLException {
		con = Connector.getInstance();
		ex = new Excuter(con);
	}

	public void printTotalSales() throws JRException, SQLException {
		jr = JasperCompileManager.compileReport("TotalSales.jrxml");
		dataSource = new JRResultSetDataSource(ex.totalSalesBooks());
		jp = JasperFillManager.fillReport(jr, new HashMap<String, Object>(), dataSource);
		JasperViewer.viewReport(jp, false);
	}

	public void printTopCustomers() throws JRException, SQLException {
		jr = JasperCompileManager.compileReport("TopCustomers.jrxml");
		dataSource = new JRResultSetDataSource(ex.top5Customers());
		jp = JasperFillManager.fillReport(jr, new HashMap<String, Object>(), dataSource);
		JasperViewer.viewReport(jp, false);
	}

	public void printTopBooks() throws JRException, SQLException {
		jr = JasperCompileManager.compileReport("TopBooks.jrxml");
		dataSource = new JRResultSetDataSource(ex.top10Books());
		jp = JasperFillManager.fillReport(jr, new HashMap<String, Object>(), dataSource);
		JasperViewer.viewReport(jp, false);
	}
}
