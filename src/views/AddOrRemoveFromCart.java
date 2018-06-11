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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.Assignment;
import controller.Condition;
import controller.Connector;
import controller.Excuter;

public class AddOrRemoveFromCart extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton add, remove, cancel, logout;
	private JTextField isbn, title;
	private ImageIcon imgIcon;
	private JLabel note, image;
	private static Container content;
	String email;
	static AddOrRemoveFromCart window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new AddOrRemoveFromCart();
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
	public AddOrRemoveFromCart() {
		initialize();
	}

	public AddOrRemoveFromCart(String email) {
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

		note = new JLabel("Add or Remove from your Cart");
		note.setFont(new Font("Tahoma", Font.PLAIN, 18));
		note.setBounds(50, 0, 700, 50);
		note.setForeground(Color.BLACK);
		getContentPane().add(note);

		isbn = new JTextField("ISBN");
		initialize_text_field(isbn, "ISBN", 140, 120);

		title = new JTextField("Title");
		initialize_text_field(title, "Title", 460, 120);

		add = new JButton("Add");
		initialize_button(add, "Add", 290, 200);
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ResultSet rs;
				Excuter ex;
				try {
					ex = new Excuter(Connector.getInstance());
					ArrayList<String> colNames = new ArrayList<>(Arrays.asList("*"));
					ArrayList<Condition> conditions = new ArrayList<>();
					if (isbn.getText().compareTo("ISBN") != 0) {
						Condition c = new Condition("ISBN", "=", "\"" + isbn.getText() + "\"");
						conditions.add(c);
						if (title.getText().compareTo("Title") != 0) {
							Condition c2 = new Condition("title", "=", "\"" + title.getText() + "\"");
							conditions.add(c2);

						}
						rs = ex.selectConditional("book", colNames, conditions, true, 0);
						if (rs.next() == false) {
							int pane = JOptionPane.showConfirmDialog(window, "no such book found", "ERROR",
									JOptionPane.DEFAULT_OPTION);
							return;
						}
						conditions = new ArrayList<>((Arrays.asList(c)));
						Condition c3 = new Condition("email", "=", "\"" + email + "\"");
						conditions.add(c3);
						rs = ex.selectConditional("cart", colNames, conditions, true, 0);
						colNames = new ArrayList<>(Arrays.asList("ISBN", "email", "quantity"));
						ArrayList<String> values = new ArrayList<>(
								Arrays.asList("\"" + isbn.getText() + "\"", "\"" + email + "\"", "1"));
						if (rs.next() == false) {

							ex.insert("cart", colNames, values);
						} else {
							ArrayList<Assignment> ass = new ArrayList<Assignment>();
							ass.add(new Assignment("quantity", "quantity+1"));
							ex.update("cart", ass, conditions, true);

						}

					} else {
						int pane = JOptionPane.showConfirmDialog(window, "isbn is not set", "ERROR",
								JOptionPane.DEFAULT_OPTION);
						return;
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

		remove = new JButton("Remove");
		initialize_button(remove, "Remove", 460, 200);
		remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ResultSet rs;
				Excuter ex;
				try {
					ex = new Excuter(Connector.getInstance());
					ArrayList<String> colNames = new ArrayList<>(Arrays.asList("*"));
					ArrayList<Condition> conditions = new ArrayList<>();
					if (isbn.getText().compareTo("ISBN") != 0) {
						Condition c = new Condition("ISBN", "=", "\"" + isbn.getText() + "\"");
						conditions.add(c);
						if (title.getText().compareTo("Title") != 0) {
							Condition c2 = new Condition("title", "=", "\"" + title.getText() + "\"");
							conditions.add(c2);

						}
						rs = ex.selectConditional("book", colNames, conditions, true, 0);
						if (rs.next() == false) {
							int pane = JOptionPane.showConfirmDialog(window, "no such book found", "ERROR",
									JOptionPane.DEFAULT_OPTION);
							return;
						}
						conditions = new ArrayList<>((Arrays.asList(c)));
						Condition c3 = new Condition("email", "=", "\"" + email + "\"");
						conditions.add(c3);
						rs = ex.selectConditional("cart", colNames, conditions, true, 0);
						// colNames = new
						// ArrayList<>(Arrays.asList("ISBN","email","quantity"));
						// ArrayList <String>values = new
						// ArrayList<>(Arrays.asList("\""+isbn.getText()+"\"","\""+email+"\"","1"));
						if (rs.next() == false) {
							int pane = JOptionPane.showConfirmDialog(window, "book is not in cart", "ERROR",
									JOptionPane.DEFAULT_OPTION);
							return;
						} else {

							if (rs.getInt("quantity") == 1) {
								ex.delete("cart", conditions, true);

							}

							else {
								ArrayList<Assignment> ass = new ArrayList<Assignment>();
								ass.add(new Assignment("quantity", "quantity-1"));
								ex.update("cart", ass, conditions, true);
							}

						}

					} else {
						int pane = JOptionPane.showConfirmDialog(window, "isbn is not set", "ERROR",
								JOptionPane.DEFAULT_OPTION);
						return;
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

		cancel = new JButton("Cancel");
		initialize_button(cancel, "Cancel", 375, 275);
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
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setFont(new Font("Hobo Std", Font.BOLD, 15));
		content.add(button);
	}
}
