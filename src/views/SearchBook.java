package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.Condition;
import controller.Connector;
import controller.Excuter;

public class SearchBook extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton submit, cancel, logout, next, prev;
	private JTextField search;
	private String[] columnNames;
	private JLabel note, key;
	private JTable results;
	private JScrollPane scroll;
	private JComboBox<String> list;
	private final String options[] = { "ISBN", "Title", "Category", "Author", "Publisher" };
	private static Container content;
	static SearchBook window;
	String email = "a@b1.com";
	int offset;
	ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new SearchBook();
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
	public SearchBook() {
		initialize();
	}

	public SearchBook(String email) {
		this.email = email;
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

		key = new JLabel("Search Key: ");
		key.setBounds(620, 10, 100, 20);
		content.add(key);

		list = new JComboBox<>(options);
		list.setBounds(700, 10, 100, 20);
		list.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					change_hint(options[list.getSelectedIndex()]);
				}
			}
		});
		content.add(list);

		note = new JLabel("Search for a book");
		note.setFont(new Font("Tahoma", Font.PLAIN, 18));
		note.setBounds(50, 0, 700, 50);
		note.setForeground(Color.BLACK);
		getContentPane().add(note);

		search = new JTextField("ISBN");
		initialize_text_field(search, "ISBN", 50, 120);

		submit = new JButton("Submit");
		initialize_button(submit, "Submit", 50, 240);
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				offset = 0;

				Excuter ex;
				try {
					ex = new Excuter(Connector.getInstance());
					ArrayList<String> colNames = new ArrayList<>(Arrays.asList("*"));
					ArrayList<Condition> conditions = new ArrayList<>();
					if (list.getSelectedIndex() == 0) {
						Condition c = new Condition("ISBN", "=", "\"" + search.getText() + "\"");
						conditions.add(c);
						rs = ex.selectConditional("book natural join quantity_table natural join author", colNames,
								conditions, true, offset);
					} else if (list.getSelectedIndex() == 1) {
						Condition c = new Condition("title", " LIKE ", "\'%" + search.getText() + "%\'");
						conditions.add(c);
						rs = ex.selectConditional("book natural join quantity_table natural join author", colNames,
								conditions, true, offset);
					} else if (list.getSelectedIndex() == 2) {
						Condition c = new Condition("category", " LIKE ", "\'%" + search.getText() + "%\'");
						conditions.add(c);
						rs = ex.selectConditional("book natural join quantity_table natural join author", colNames,
								conditions, true, offset);
					} else if (list.getSelectedIndex() == 3) {

						Condition c = new Condition("author_name", " LIKE ", "\'%" + search.getText() + "%\'");
						conditions.add(c);
						rs = ex.selectConditional("book natural join quantity_table natural join author", colNames,
								conditions, true, offset);

					} else if (list.getSelectedIndex() == 4) {
						Condition c = new Condition("publisher_name", " LIKE ", "\'%" + search.getText() + "%\'");
						conditions.add(c);
						rs = ex.selectConditional("book natural join quantity_table natural join author", colNames,
								conditions, true, offset);
					}
					DefaultTableModel model = new DefaultTableModel(new String[] { "ISBN", "Title", "Publisher",
							"Category", "Year", "Price", "quantity", "author", "" }, 0);
					while (rs.next()) {
						String d = rs.getString("ISBN");
						String e = rs.getString("title");
						String f = rs.getString("publisher_name");
						String q = rs.getString("category");
						String r = rs.getString("pyear");
						String s = rs.getString("price");
						String t = rs.getString("quantity");
						String u = rs.getString("author_name");
						System.out.println(t);
						(model).addRow(new Object[] { d, e, f, q, r, s, t, u, "Edit/Remove" });
					}
					results.setModel(model);
					new ButtonColumn(results, delete, 8);

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
		initialize_button(next, "next", 50, 300);
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				offset += 10;

				Excuter ex;
				try {
					ex = new Excuter(Connector.getInstance());
					ArrayList<String> colNames = new ArrayList<>(Arrays.asList("*"));
					ArrayList<Condition> conditions = new ArrayList<>();
					if (list.getSelectedIndex() == 0) {
						Condition c = new Condition("ISBN", "=", "\"" + search.getText() + "\"");
						conditions.add(c);
						rs = ex.selectConditional("book natural join quantity_table natural join author", colNames,
								conditions, true, offset);
					} else if (list.getSelectedIndex() == 1) {
						Condition c = new Condition("title", " LIKE ", "\'%" + search.getText() + "%\'");
						conditions.add(c);
						rs = ex.selectConditional("book natural join quantity_table natural join author", colNames,
								conditions, true, offset);
					} else if (list.getSelectedIndex() == 2) {
						Condition c = new Condition("category", " LIKE ", "\'%" + search.getText() + "%\'");
						conditions.add(c);
						rs = ex.selectConditional("book natural join quantity_table natural join author", colNames,
								conditions, true, offset);
					} else if (list.getSelectedIndex() == 3) {

						Condition c = new Condition("author_name", " LIKE ", "\'%" + search.getText() + "%\'");
						conditions.add(c);
						rs = ex.selectConditional("book natural join quantity_table natural join author", colNames,
								conditions, true, offset);

					} else if (list.getSelectedIndex() == 4) {
						Condition c = new Condition("publisher_name", " LIKE ", "\'%" + search.getText() + "%\'");
						conditions.add(c);
						rs = ex.selectConditional("book natural join quantity_table natural join author", colNames,
								conditions, true, offset);
					}

					DefaultTableModel model = new DefaultTableModel(new String[] { "ISBN", "Title", "Publisher",
							"Category", "Year", "Price", "quantity", "author", "" }, 0);
					while (rs.next()) {
						String d = rs.getString("ISBN");
						String e = rs.getString("title");
						String f = rs.getString("publisher_name");
						String q = rs.getString("category");
						String r = rs.getString("pyear");
						String s = rs.getString("price");
						String t = rs.getString("quantity");
						String u = rs.getString("author_name");
						System.out.println(t);
						(model).addRow(new Object[] { d, e, f, q, r, s, t, u });
					}
					results.setModel(model);

				} catch (SQLException | ClassNotFoundException e) {
					int pane = JOptionPane.showConfirmDialog(window, "error in connection", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					e.printStackTrace();
				}

			}

		});

		prev = new JButton("prev");
		initialize_button(prev, "prev", 210, 300);
		prev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				offset -= 10;
				if (offset < 0) {
					offset = 0;
				}
				Excuter ex;
				try {
					ex = new Excuter(Connector.getInstance());
					ArrayList<String> colNames = new ArrayList<>(Arrays.asList("*"));
					ArrayList<Condition> conditions = new ArrayList<>();
					if (list.getSelectedIndex() == 0) {
						Condition c = new Condition("ISBN", "=", "\"" + search.getText() + "\"");
						conditions.add(c);
						rs = ex.selectConditional("book natural join quantity_table natural join author", colNames,
								conditions, true, offset);
					} else if (list.getSelectedIndex() == 1) {
						Condition c = new Condition("title", " LIKE ", "\'%" + search.getText() + "%\'");
						conditions.add(c);
						rs = ex.selectConditional("book natural join quantity_table natural join author", colNames,
								conditions, true, offset);
					} else if (list.getSelectedIndex() == 2) {
						Condition c = new Condition("category", " LIKE ", "\'%" + search.getText() + "%\'");
						conditions.add(c);
						rs = ex.selectConditional("book natural join quantity_table natural join author", colNames,
								conditions, true, offset);
					} else if (list.getSelectedIndex() == 3) {

						Condition c = new Condition("author_name", " LIKE ", "\'%" + search.getText() + "%\'");
						conditions.add(c);
						rs = ex.selectConditional("book natural join quantity_table natural join author", colNames,
								conditions, true, offset);

					} else if (list.getSelectedIndex() == 4) {
						Condition c = new Condition("publisher_name", " LIKE ", "\'%" + search.getText() + "%\'");
						conditions.add(c);
						rs = ex.selectConditional("book natural join quantity_table natural join author", colNames,
								conditions, true, offset);
					}

					DefaultTableModel model = new DefaultTableModel(new String[] { "ISBN", "Title", "Publisher",
							"Category", "Year", "Price", "quantity", "author", "" }, 0);
					while (rs.next()) {
						String d = rs.getString("ISBN");
						String e = rs.getString("title");
						String f = rs.getString("publisher_name");
						String q = rs.getString("category");
						String r = rs.getString("pyear");
						String s = rs.getString("price");
						String t = rs.getString("quantity");
						String u = rs.getString("author_name");
						System.out.println(t);
						(model).addRow(new Object[] { d, e, f, q, r, s, t, u });
					}
					results.setModel(model);

				} catch (SQLException | ClassNotFoundException e) {
					int pane = JOptionPane.showConfirmDialog(window, "error in connection", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					e.printStackTrace();
				}

			}

		});

		cancel = new JButton("Cancel");
		initialize_button(cancel, "Cancel", 210, 240);

		cancel.addActionListener(new ActionListener() {
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

		columnNames = new String[] { "ISBN", "Title", "Publisher", "Category", "Year", "Price", "Quantity", "author", ""
				/* "ID", "NAME", "DOB", "ADDRESS" */ };
		results = new JTable(new Object[][] {}, columnNames);
		results.getColumnModel().getColumn(0).setPreferredWidth(40);
		// results.getColumnModel().getColumn(4).setPreferredWidth(40);
		results.getColumnModel().getColumn(8).setPreferredWidth(70);
		results.setDefaultEditor(Object.class, null);
		results.getModel().addTableModelListener(new TableModelListener() {

			@SuppressWarnings("unused")
			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int column = e.getColumn();
				TableModel model = (TableModel) e.getSource();
				String columnName = model.getColumnName(column);
				Object data = model.getValueAt(row, column);

				// Update here

			}
		});

		scroll = new JScrollPane(results);
		scroll.setBounds(400, 100, 700, 500);
		results.setFillsViewportHeight(true);
		content.add(scroll);

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

	private static void initialize_text_field(JTextField field, String name, int x, int y) {
		field.setForeground(Color.LIGHT_GRAY);
		field.setHorizontalAlignment(SwingConstants.CENTER);
		field.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		field.setBounds(x, y, 300, 50);
		field.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (field.getText().equals(name)) {
					field.setText("");
					field.setForeground(Color.black);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (field.getText().equals("")) {
					field.setText(name);
					field.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		field.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (field.getText().equals("")) {
					field.setText(name);
					field.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (field.getText().equals(name)) {
					field.setText("");
					field.setForeground(Color.black);
				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if (field.getText().equals("")) {
					field.setText(name);
					field.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (field.getText().equals(name)) {
					field.setText("");
					field.setForeground(Color.black);
				}
			}
		});
		content.add(field);
	}

	private static void initialize_button(JButton button, String name, int x, int y) {
		button.setBounds(x, y, 140, 50);
		button.setBackground(Color.LIGHT_GRAY);
		button.setFont(new Font("Hobo Std", Font.BOLD, 15));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		content.add(button);
	}

	private void change_hint(String key) {
		// initialize_text_field(search, key, 300, 120);
		content.remove(search);
		search = new JTextField(key);
		initialize_text_field(search, key, 50, 120);
	}

	Action delete = new AbstractAction() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			JTable table = (JTable) e.getSource();
			int row = Integer.valueOf(e.getActionCommand());
			String isbn = table.getModel().getValueAt(row, 0).toString();
			// System.out.println(isbn);
		}
	};
}
