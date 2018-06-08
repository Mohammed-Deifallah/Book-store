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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SearchBook extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton submit, cancel, logout;
	private JTextField search;
	private String[] columnNames;
	private JLabel note, key;
	private JTable results;
	private JScrollPane scroll;
	private JComboBox<String> list;
	private final String options[] = { "ISBN", "Title", "Category", "Author", "Publisher" };
	private static Container content;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SearchBook window = new SearchBook();
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
				/*
				 * try { Class.forName("com.mysql.jdbc.Driver"); Connection con
				 * = DriverManager.getConnection(
				 * "jdbc:mysql://localhost:3306/PHONEBOOK", "root", "admin");
				 * DefaultTableModel model = new DefaultTableModel( new String[]
				 * { "ID", "NAME", "BIRTH_DATE", "ADDRESS" }, 0); ResultSet rs =
				 * con.createStatement().executeQuery(
				 * "SELECT ID, PNAME, DOB, PADDRESS FROM PERSON"); while
				 * (rs.next()) { String d = rs.getString("ID"); String e =
				 * rs.getString("PNAME"); String f = rs.getString("DOB"); String
				 * q = rs.getString("PADDRESS"); model.addRow(new Object[] { d,
				 * e, f, q }); } results.setModel(model); } catch (Exception e)
				 * {
				 * 
				 * }
				 */
			}
		});

		cancel = new JButton("Cancel");
		initialize_button(cancel, "Cancel", 210, 240);
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		columnNames = new String[] { "ISBN", "Title", "Publisher", "Author", "Price", "Year", "Category" };
		results = new JTable(new Object[][] {}, columnNames);
		results.getColumnModel().getColumn(0).setPreferredWidth(40);
		// results.getColumnModel().getColumn(4).setPreferredWidth(40);
		// results.getColumnModel().getColumn(5).setPreferredWidth(40);
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

			@Override
			public void actionPerformed(ActionEvent arg0) {

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
}
