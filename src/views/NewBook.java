package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Condition;
import controller.Connector;
import controller.Excuter;

public class NewBook extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton submit, cancel, logout;
	private JTextField isbn, title, publisher, publication_year, selling_price, category, threshold, quantity;
	private JTextArea authors;
	private ImageIcon imgIcon;
	private JLabel note, image;
	private static Container content;
	String email="mo@";
	static NewBook window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new NewBook();
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
	public NewBook() {
		initialize();
	}
	public NewBook(String email) {
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

		note = new JLabel("Add a new book");
		note.setFont(new Font("Tahoma", Font.PLAIN, 18));
		note.setBounds(50, 0, 700, 50);
		note.setForeground(Color.BLACK);
		getContentPane().add(note);

		isbn = new JTextField("ISBN");
		initialize_text_field(isbn, "ISBN", 140, 120);

		title = new JTextField("Title");
		initialize_text_field(title, "Title", 460, 120);

		category = new JTextField("Category");
		initialize_text_field(category, "Category", 140, 180);

		publisher = new JTextField("Publisher");
		initialize_text_field(publisher, "Publisher", 460, 180);

		selling_price = new JTextField("Price");
		initialize_text_field(selling_price, "Price", 140, 240);

		publication_year = new JTextField("Publication Year");
		initialize_text_field(publication_year, "Publication Year", 460, 240);

		threshold = new JTextField("Minimum Quantity");
		initialize_text_field(threshold, "Minimum Quantity", 140, 300);

		quantity = new JTextField("Current Quantity");
		initialize_text_field(quantity, "Current Quantity", 460, 300);

		authors = new JTextArea("Author1\nAuthor2\n...");
		initialize_text_area(authors, "Author1\nAuthor2\n...", 300, 360);

		submit = new JButton("Submit");
		initialize_button(submit, "Submit", 290, 550);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (isbn.getText().compareTo("ISBN") == 0) {
					
					int pane = JOptionPane.showConfirmDialog(window, "please make sure you wrote isbn correctly",
							"ERROR", JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					return;
				}
				if (title.getText().compareTo("Title") == 0) {
					int pane = JOptionPane.showConfirmDialog(window, "please check title", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					return;
				}
				if (category.getText().compareTo("Category") == 0) {
					int pane = JOptionPane.showConfirmDialog(window, "please check Category", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					return;
				}
				if (publisher.getText().compareTo("Publisher") == 0) {
					int pane = JOptionPane.showConfirmDialog(window, "please check publisher", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					return;
				}
				if (authors.getText().compareTo("Author1\nAuthor2\n...") == 0) {
					int pane = JOptionPane.showConfirmDialog(window, "please check authors", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					return;
				}
				if (!java.util.regex.Pattern.matches("\\d+", quantity.getText())) {
					int pane = JOptionPane.showConfirmDialog(window, "please check quantity", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					return;
				}
				if (!java.util.regex.Pattern.matches("\\d+", threshold.getText())) {
					int pane = JOptionPane.showConfirmDialog(window, "please check threshold", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					return;
				}
				if (!java.util.regex.Pattern.matches("\\d+", selling_price.getText())) {
					int pane = JOptionPane.showConfirmDialog(window, "please check selling_price", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					return;
				}
				if (!java.util.regex.Pattern.matches("^[12][0-9]{3}$", publication_year.getText())) {
					int pane = JOptionPane.showConfirmDialog(window, "please check publication_year", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					return;
				}
				Connector conn;
				try {
					Excuter ex = new Excuter(Connector.getInstance());
					ArrayList<String> colNames = new ArrayList<>(Arrays.asList("ISBN", "title", "pyear",
							"category", "publisher_name"));
					ArrayList<String> colVal = new ArrayList<>(
							Arrays.asList("\""+isbn.getText()+"\"", "\""+title.getText()+"\"", publication_year.getText(), "\""+category.getText()+"\"",
									"\""+publisher.getText()+"\""));
					ex.insert("book", colNames, colVal);
					colNames = new ArrayList<>(Arrays.asList("ISBN", "threshold", "quantity",
							"price"));
					colVal = new ArrayList<>(
							Arrays.asList("\""+isbn.getText()+"\"", threshold.getText(), quantity.getText(), selling_price.getText() ));
					ex.insert("quantity_table", colNames, colVal);
					
					colNames = new ArrayList<>(Arrays.asList("ISBN", "author_name"));
					String[] a=split(authors.getText(),'\n');
					for(int i=0;i<a.length;i++){
						colVal = new ArrayList<>(
						Arrays.asList("\""+isbn.getText()+"\"", "\""+a[i]+"\"" ));
						ex.insert("author", colNames, colVal);
						
					}
					int pane = JOptionPane.showConfirmDialog(window, "added",
							"OK", JOptionPane.DEFAULT_OPTION);
					
				} catch (SQLException | ClassNotFoundException e) {
					int pane = JOptionPane.showConfirmDialog(window, "dublicate book found or error in database happened",
							"ERROR", JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					System.out.println(e.getMessage());
					e.printStackTrace();
					return;

				}

			}
		});

		cancel = new JButton("Cancel");
		initialize_button(cancel, "Cancel", 460, 550);
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

		imgIcon = new ImageIcon("Books.jpg");
		image = new JLabel(imgIcon);
		image.setBounds(850, 250, imgIcon.getIconWidth(), imgIcon.getIconHeight());
		getContentPane().add(image);
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
		button.setBounds(x, y, 150, 50);
		button.setBackground(Color.LIGHT_GRAY);
		button.setFont(new Font("Hobo Std", Font.BOLD, 15));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		content.add(button);
	}

	private static void initialize_text_area(JTextArea field, String name, int x, int y) {
		field.setForeground(Color.LIGHT_GRAY);
		field.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		field.setBounds(x, y, 300, 170);
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
    public static String[] split(String strToSplit, char delimiter) {
        ArrayList<String> arr = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strToSplit.length(); i++) {
            char at = strToSplit.charAt(i);
            if (at == delimiter) {
                arr.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(at);
            }
        }
        arr.add(sb.toString());
        return arr.toArray(new String[0]);
    }
}
