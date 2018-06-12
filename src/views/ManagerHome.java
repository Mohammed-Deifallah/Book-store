package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.Condition;
import controller.Connector;
import controller.Excuter;

public class ManagerHome extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton editInfo, search, add, view, remove, addBook, editBook, order, promote, report, logout;
	private ImageIcon imgIcon;
	private JLabel note, image;
	private static Container content;
	String email="a@b";
	static ManagerHome window;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new ManagerHome();
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
	public ManagerHome() {
		initialize();
	}
	public ManagerHome(String email) {
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

		note = new JLabel("User Home");
		note.setFont(new Font("Tahoma", Font.PLAIN, 18));
		note.setBounds(50, 0, 700, 50);
		note.setForeground(Color.BLACK);
		getContentPane().add(note);

		editInfo = new JButton("Edit info");
		initialize_button(editInfo, "Edit info", 200, 120);
		editInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							dispose();
							EditInfo window = new EditInfo(email);
							window.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				
				
				
			}
		});
		

		search = new JButton("Search");
		initialize_button(search, "Search", 460, 120);
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							dispose();
							SearchBook window = new SearchBook(false,email);
							window.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		add = new JButton("Add/delete from cart");
		initialize_button(add, "Add/delete from cart", 200, 180);
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							dispose();
							AddOrRemoveFromCart window = new AddOrRemoveFromCart(email);
							window.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		view = new JButton("View cart");
		initialize_button(view, "View cart", 460, 180);
		view.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							dispose();
							ViewCart window = new ViewCart(email);
							window.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});


		addBook = new JButton("Add new book");
		initialize_button(addBook, "Add new book", 460, 240);
		addBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		editBook = new JButton("Edit book");
		initialize_button(editBook, "Edit book", 200, 300);
		editBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		order = new JButton("Place order");
		initialize_button(order, "Place order", 460, 300);
		order.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		promote = new JButton("Promote user");
		initialize_button(promote, "Promote user", 200, 360);
		promote.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							Promote window = new Promote(email);
							window.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		report = new JButton("View reports");
		initialize_button(report, "View reports", 460, 360);
		report.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							ReportMenu window = new ReportMenu(email);
							window.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		logout = new JButton("Logout");
		initialize_button(logout, "Logout", 1000, 10);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				Excuter ex;
				try {
					ex = new Excuter(Connector.getInstance());
					Condition emailc=new Condition("email","=","\""+email+"\"");
					ArrayList<Condition> conditions = new ArrayList<>(Arrays.asList(emailc));
					boolean rs = ex.delete("cart",conditions,true);
				} catch (SQLException | ClassNotFoundException e1) {
					int pane = JOptionPane.showConfirmDialog(window,
			                 "error in connection", "ERROR",JOptionPane.DEFAULT_OPTION);
					e1.printStackTrace();
				}
				EventQueue.invokeLater(new Runnable() {
					@Override
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

	private static void initialize_button(JButton button, String name, int x, int y) {
		button.setBounds(x, y, 200, 50);
		button.setBackground(Color.LIGHT_GRAY);
		button.setFont(new Font("Hobo Std", Font.BOLD, 15));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		content.add(button);
	}
}
