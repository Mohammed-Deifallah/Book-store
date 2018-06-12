package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.Assignment;
import controller.Condition;
import controller.Connector;
import controller.Excuter;

public class ViewCart extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton back, logout, calc,next,prev,buy;
	private String[] columnNames;
	private JLabel note, total;
	private JTable cart;
	private JScrollPane scroll;
	private static Container content;
	static ViewCart window ;
	String email="a@b1.com";
	int offset;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new ViewCart();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewCart() {
		initialize();
	}
	public ViewCart(String email) {
		this.email=email;
		initialize();
	}

	/**
	 * Initialize the contents of the
	 */
	private void initialize() {
		// setBounds(10, 10, 1146, 700);
		setSize(getMaximumSize());
		getContentPane().setLayout(null);
		setTitle("Book Store");
		getContentPane().setBackground(Color.ORANGE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		content = getContentPane();

		note = new JLabel("View Cart");
		note.setFont(new Font("Tahoma", Font.PLAIN, 18));
		note.setBounds(50, 0, 700, 50);
		note.setForeground(Color.BLACK);
		getContentPane().add(note);

		back = new JButton("Back");
		initialize_button(back, "Back", 150, 240);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connector conn;
				Excuter ex;
				try {
					ex = new Excuter(Connector.getInstance());
					ArrayList<String> colNames = new ArrayList<>(Arrays.asList("email", "privilege"));
					Condition emailc = new Condition("email", "=", "\"" + email + "\"");
					ArrayList<Condition> conditions = new ArrayList<>(Arrays.asList(emailc));
					ResultSet rs = ex.selectConditional("user", colNames, conditions, true, 0);
					rs.next();
					if (rs.getInt("privilege") == 0) {
						EventQueue.invokeLater(new Runnable() {
							@Override
							public void run() {
								try {
									dispose();
									UserHome window = new UserHome(email);
									window.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});

					} else {
						EventQueue.invokeLater(new Runnable() {
							@Override
							public void run() {
								try {
									dispose();
									ManagerHome window = new ManagerHome(email);
									window.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});

					}

				} catch (SQLException | ClassNotFoundException e) {
					int pane = JOptionPane.showConfirmDialog(window, "error in connection", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					e.printStackTrace();
				}

			}
		});

		next = new JButton("next");
		initialize_button(next, "next", 150, 320);
		
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel model = new DefaultTableModel( new String[]
						{ "ISBN", "Title","quantity", "Price" }, 0);
				 
				 offset+=10;
				 Excuter ex;
				try {
					ex = new Excuter(Connector.getInstance());
					 ArrayList<String> colNames = new ArrayList<>(Arrays.asList("book.ISBN","title","cart.quantity","price"));
						ArrayList<Condition>	conditions = new ArrayList<>();
							Condition c3 = new Condition("email", "=", "\"" + email + "\"");
							conditions.add(c3);
							Condition c4 = new Condition("book.ISBN", "=", "cart.ISBN");
							conditions.add(c4);
							Condition c5 = new Condition("book.ISBN", "=", "quantity_table.ISBN");
							conditions.add(c5);
						 ResultSet rs=ex.selectConditional("book,cart,quantity_table ",colNames,conditions,true,offset);
						while (rs.next()) {
							String d = rs.getString("ISBN");
							String e = rs.getString("title");
							String f = rs.getString("quantity");
							String q = rs.getString("price");
							((DefaultTableModel) model).addRow(new Object[] { d, e, f, q });
						}
						cart.setModel(model);
						
						
				}  catch (SQLException | ClassNotFoundException e) {
					int pane = JOptionPane.showConfirmDialog(window, "error in connection", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					e.printStackTrace();
				}
			}
		});
		
		
		prev = new JButton("prev");
		initialize_button(prev, "prev", 150, 380);
		
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel model = new DefaultTableModel( new String[]
						{ "ISBN", "Title","quantity", "Price" }, 0);
				 
				 offset-=10;
				 if(offset<10)
					 offset=0;
				 Excuter ex;
				try {
					ex = new Excuter(Connector.getInstance());
					 ArrayList<String> colNames = new ArrayList<>(Arrays.asList("book.ISBN","title","cart.quantity","price"));
						ArrayList<Condition>	conditions = new ArrayList<>();
							Condition c3 = new Condition("email", "=", "\"" + email + "\"");
							conditions.add(c3);
							Condition c4 = new Condition("book.ISBN", "=", "cart.ISBN");
							conditions.add(c4);
							Condition c5 = new Condition("book.ISBN", "=", "quantity_table.ISBN");
							conditions.add(c5);
						 ResultSet rs=ex.selectConditional("book,cart,quantity_table ",colNames,conditions,true,offset);
						while (rs.next()) {
							String d = rs.getString("ISBN");
							String e = rs.getString("title");
							String f = rs.getString("quantity");
							String q = rs.getString("price");
							((DefaultTableModel) model).addRow(new Object[] { d, e, f, q });
						}
						cart.setModel(model);
						
						
				}  catch (SQLException | ClassNotFoundException e) {
					int pane = JOptionPane.showConfirmDialog(window, "error in connection", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					e.printStackTrace();
				}
			}
		});
		
		buy = new JButton("buy");
		initialize_button(buy, "buy", 150, 500);
		buy.addActionListener(new ActionListener() {
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Excuter ex = new Excuter(Connector.getInstance());
					ArrayList<String> colNames = new ArrayList<>(Arrays.asList("*"));
					ArrayList<Condition>	conditions = new ArrayList<>();
					
					Condition c3 = new Condition("email", "=", "\"" + email + "\"");
					conditions.add(c3);
					ResultSet rs = ex.selectConditional("cart", colNames, conditions, true, 0);
					//rs.next();
					
					while(rs.next()){
						int q=rs.getInt("quantity");
						String isbn=rs.getString("ISBN");
						ArrayList<Condition> conditions2 = new ArrayList<>();
						Condition c4 = new Condition("ISBN", "=", "\"" + isbn + "\"");
						conditions2.add(c4);
						
						rs = ex.selectConditional("quantity_table", colNames, conditions2, true, 0);
						rs.next();
						if(q>rs.getInt("quantity")){
							
							int pane = JOptionPane.showConfirmDialog(window, "quantity is not enough in store", "ERROR",
									JOptionPane.DEFAULT_OPTION);
							Condition emailc = new Condition("email", "=", "\"" + email + "\"");
							Condition isbnc = new Condition("ISBN", "=", "\"" + isbn + "\"");
							ArrayList<Condition> conditions3 = new ArrayList<>(Arrays.asList(emailc,isbnc));
							ex.delete("cart", conditions3, true);
							rs = ex.selectConditional("cart", colNames, conditions, true, 0);
							
							
						}else{
							Condition emailc = new Condition("email", "=", "\"" + email + "\"");
							Condition isbnc = new Condition("ISBN", "=", "\"" + isbn + "\"");
							ArrayList<Condition> conditions3 = new ArrayList<>(Arrays.asList(emailc,isbnc));
							ex.delete("cart", conditions3, true);
							ArrayList<Assignment> ass = new ArrayList<Assignment>();
							ass.add(new Assignment("quantity", "quantity-"+q));
							ex.update("quantity_table", ass, conditions2, true);
							
							
							colNames= new ArrayList<>(Arrays.asList("email","ISBN","quantity","sell_date"));
							
							java.util.Date dt = new java.util.Date();

							java.text.SimpleDateFormat sdf = 
							     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

							String currentTime = sdf.format(dt);
							ArrayList<String> values= new ArrayList<>(Arrays.asList("\"" + email + "\"","\"" + isbn + "\"",""+q,"\""+currentTime+"\""));
							ex.insert("log", colNames, values);
							colNames= new ArrayList<>(Arrays.asList("*"));
							rs = ex.selectConditional("cart", colNames, conditions, true, 0);
							
							
							
						}
						
						
						
					}
					
					
				} catch (ClassNotFoundException |SQLException e) {
					int pane = JOptionPane.showConfirmDialog(window, "error in connection", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					e.printStackTrace();
				} 
				 
				
			}
		});
		
		
		calc = new JButton("Calculate Total Price");
		initialize_button(calc, "Calculate Total Price", 150, 120);
		calc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				 
				 Excuter ex;
				try {
					ex = new Excuter(Connector.getInstance());
					 ArrayList<String> colNames = new ArrayList<>(Arrays.asList("sum(price*cart.quantity)"));
						ArrayList<Condition>	conditions = new ArrayList<>();
							Condition c3 = new Condition("email", "=", "\"" + email + "\"");
							conditions.add(c3);
							//
							
							ResultSet rs=ex.selectConditional(" cart  JOIN quantity_table on cart.ISBN=quantity_table.ISBN ",colNames,conditions,true,0);
							rs.next();
						 int pane = JOptionPane.showConfirmDialog(window," total price is  :  " +rs.getInt(1) , "total price",
									JOptionPane.DEFAULT_OPTION);
						
						
				}  catch (SQLException | ClassNotFoundException e) {
					int pane = JOptionPane.showConfirmDialog(window, "error in connection", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					e.printStackTrace();
				}
			}
		});



		columnNames = new String[] { "ISBN", "Title","quantity", "Price" };
		cart = new JTable(new Object[][] {}, columnNames);
		
		
		
		DefaultTableModel model = new DefaultTableModel( new String[]
				{ "ISBN", "Title","quantity", "Price" }, 0);
		 
		 offset=0;
		 Excuter ex;
		try {
			ex = new Excuter(Connector.getInstance());
			 ArrayList<String> colNames = new ArrayList<>(Arrays.asList("book.ISBN","title","cart.quantity","price"));
				ArrayList<Condition>	conditions = new ArrayList<>();
					Condition c3 = new Condition("email", "=", "\"" + email + "\"");
					conditions.add(c3);
					Condition c4 = new Condition("book.ISBN", "=", "cart.ISBN");
					conditions.add(c4);
					Condition c5 = new Condition("book.ISBN", "=", "quantity_table.ISBN");
					conditions.add(c5);
				 ResultSet rs=ex.selectConditional("book,cart,quantity_table ",colNames,conditions,true,0);
				 while (rs.next()) {
					String d = rs.getString("ISBN");
					String e = rs.getString("title");
					String f = rs.getString("quantity");
					String q = rs.getString("price");
					((DefaultTableModel) model).addRow(new Object[] { d, e, f, q });
				}
				cart.setModel(model);
				
				
		}  catch (SQLException | ClassNotFoundException e) {
			int pane = JOptionPane.showConfirmDialog(window, "error in connection", "ERROR",
					JOptionPane.DEFAULT_OPTION);
			// window.dispatchEvent(new WindowEvent(window,
			// WindowEvent.WINDOW_CLOSING));
			e.printStackTrace();
		}

		
		
		
		
		
		
		cart.setDefaultEditor(Object.class, null);

		scroll = new JScrollPane(cart);
		scroll.setBounds(400, 100, 300, 500);
		cart.setFillsViewportHeight(true);
		content.add(scroll);

		total = new JLabel("Total Price: ");
		total.setBounds(600, 80, 150, 10);
		content.add(total);

		logout = new JButton("Logout");
		initialize_button(logout, "Logout", 1000, 10);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Excuter ex;
				try {
					ex = new Excuter(Connector.getInstance());
					Condition emailc = new Condition("email", "=", "\"" + email + "\"");
					ArrayList<Condition> conditions = new ArrayList<>(Arrays.asList(emailc));
					boolean rs = ex.delete("cart", conditions, true);
				} catch (SQLException | ClassNotFoundException e1) {
					int pane = JOptionPane.showConfirmDialog(window, "error in connection", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					e1.printStackTrace();
				}
				EventQueue.invokeLater(new Runnable() {

					public void run() {
						try {
							dispose();
							SignInForm window = new SignInForm();
							window.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});
		}
	
	private static void initialize_button(JButton button, String name, int x, int y) {
		button.setBounds(x, y, 200, 50);
		button.setBackground(Color.LIGHT_GRAY);
		button.setFont(new Font("Hobo Std", Font.BOLD, 15));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		content.add(button);
	}
}
