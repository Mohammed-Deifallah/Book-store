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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Connector;
import controller.Excuter;

public class RegistrationForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton submit;
	private JTextField username, email, first_name, last_name, shipping_address, phone;
	private JPasswordField password, repassword;
	private ImageIcon imgIcon;
	private JLabel note, image, tri;
	private static Container content;
	static RegistrationForm window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new RegistrationForm();
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
	public RegistrationForm() {
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

		note = new JLabel("Sign up");
		note.setFont(new Font("Tahoma", Font.PLAIN, 18));
		note.setBounds(50, 0, 700, 50);
		note.setForeground(Color.BLACK);
		getContentPane().add(note);

		username = new JTextField("Username");
		initialize_text_field(username, "Username", 140, 120);

		email = new JTextField("Email");
		initialize_text_field(email, "Email", 460, 120);

		first_name = new JTextField("First Name");
		initialize_text_field(first_name, "First Name", 140, 180);

		last_name = new JTextField("Last Name");
		initialize_text_field(last_name, "Last Name", 460, 180);

		shipping_address = new JTextField("Address");
		initialize_text_field(shipping_address, "Address", 140, 240);

		phone = new JTextField("Phone");
		initialize_text_field(phone, "Phone", 460, 240);

		password = new JPasswordField("Password");
		initialize_password_field(password, "Password", 140, 300);

		repassword = new JPasswordField("Re-type Password");
		initialize_password_field(repassword, "Re-type Password", 460, 300);

		submit = new JButton("Submit");
		submit.setBounds(375, 400, 150, 50);
		submit.setBackground(Color.LIGHT_GRAY);
		submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		getContentPane().add(submit);

		imgIcon = new ImageIcon("Books.jpg");
		image = new JLabel(imgIcon);
		image.setBounds(850, 250, imgIcon.getIconWidth(), imgIcon.getIconHeight());
		getContentPane().add(image);

		tri = new JLabel("");
		tri.setBounds(300, 300, 50, 50);
		getContentPane().add(tri);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (password.getText().compareTo(repassword.getText()) != 0) {
					System.out.println(password.getText() + repassword.getText());
					int pane = JOptionPane.showConfirmDialog(window, "please make sure you wrote password correctly",
							"ERROR", JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					return;
				}
				if (password.getText().length() < 3) {
					int pane = JOptionPane.showConfirmDialog(window, "please length should be => 3", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					return;
				}
				if (username.getText().compareTo("Username") == 0) {
					int pane = JOptionPane.showConfirmDialog(window, "please check username", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					return;
				}
				if (!email.getText().contains("@")) {
					int pane = JOptionPane.showConfirmDialog(window, "please check email", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					return;
				}
				if (first_name.getText().compareTo("First Name") == 0) {
					int pane = JOptionPane.showConfirmDialog(window, "please check first name", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					return;
				}
				if (last_name.getText().compareTo("Last Name") == 0) {
					int pane = JOptionPane.showConfirmDialog(window, "please check last name", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					return;
				}
				if (shipping_address.getText().compareTo("Address") == 0) {
					int pane = JOptionPane.showConfirmDialog(window, "please check address", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					return;
				}
				if (!java.util.regex.Pattern.matches("\\d+", phone.getText())) {
					int pane = JOptionPane.showConfirmDialog(window, "please check phone number", "ERROR",
							JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					return;
				}
				Connector conn;
				try {
					Excuter ex = new Excuter(Connector.getInstance());
					ArrayList<String> colNames = new ArrayList<>(Arrays.asList("username", "email", "password",
							"first_name", "last_name", "phone_number", "shipping_address", "privilege"));
					ArrayList<String> colVal = new ArrayList<>(
							Arrays.asList("\""+username.getText()+"\"", "\""+email.getText()+"\"", "\""+password.getText()+"\"", "\""+first_name.getText()+"\"",
									"\""+last_name.getText()+"\"", "\""+phone.getText()+"\"","\""+ shipping_address.getText()+"\"","\""+ "0"+"\""));
					ex.insert("user", colNames, colVal);
					EventQueue.invokeLater(new Runnable() {
						@Override
						public void run() {
							try {
								window.setVisible(false);
								SignInForm window = new SignInForm();
								window.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});

				} catch (SQLException | ClassNotFoundException e) {
					int pane = JOptionPane.showConfirmDialog(window, "same email exists or error in database happened",
							"ERROR", JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					System.out.println(e.getMessage());
					e.printStackTrace();
					return;

				}

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
				// TODO Auto-generated method stub
				if (field.getText().equals(name)) {
					field.setText("");
					field.setForeground(Color.black);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (field.getText().equals("")) {
					field.setText(name);
					field.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

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

	private static void initialize_password_field(JPasswordField pass, String name, int x, int y) {
		pass.setForeground(Color.LIGHT_GRAY);
		pass.setHorizontalAlignment(SwingConstants.CENTER);
		pass.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		pass.setBounds(x, y, 300, 50);
		pass.setEchoChar((char) 0);
		pass.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (new String(pass.getPassword()).equals(name)) {
					pass.setText("");
					pass.setEchoChar('.');
					pass.setForeground(Color.black);
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (new String(pass.getPassword()).equals("")) {
					pass.setText(name);
					pass.setEchoChar((char) 0);
					pass.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		pass.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (new String(pass.getPassword()).equals("")) {
					pass.setText(name);
					pass.setEchoChar((char) 0);
					pass.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (new String(pass.getPassword()).equals(name)) {
					pass.setText("");
					pass.setEchoChar('.');
					pass.setForeground(Color.black);
				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if (new String(pass.getPassword()).equals("")) {
					pass.setText(name);
					pass.setEchoChar((char) 0);
					pass.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (new String(pass.getPassword()).equals(name)) {
					pass.setText("");
					pass.setEchoChar('.');
					pass.setForeground(Color.black);
				}
			}
		});
		content.add(pass);
	}

}
