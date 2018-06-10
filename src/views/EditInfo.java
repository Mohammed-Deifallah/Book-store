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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import info.User;

public class EditInfo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton submit, cancel, logout;
	private JTextField username, email, phone, address, firstName, lastName;
	private JPasswordField password, repassword;
	private ImageIcon imgIcon;
	private JLabel note, image;
	private static Container content;
	private User user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					User u = new User("Mohammed", "@yahoo", "00000", "Mo", "Salah", "+20", "21");
					EditInfo window = new EditInfo(u);
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
	public EditInfo(User user) {
		this.user = user;
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

		note = new JLabel("Modify User");
		note.setFont(new Font("Tahoma", Font.PLAIN, 18));
		note.setBounds(50, 0, 700, 50);
		note.setForeground(Color.BLACK);
		getContentPane().add(note);

		username = new JTextField("Username: " + user.getUserName());
		initialize_text_field(username, "Username: " + user.getUserName(), 140, 120);

		email = new JTextField("Email: " + user.getEmail());
		initialize_text_field(email, "Email: " + user.getEmail(), 460, 120);

		firstName = new JTextField("First Name: " + user.getFirstName());
		initialize_text_field(firstName, "First Name: " + user.getFirstName(), 460, 120);

		lastName = new JTextField("Last Name: " + user.getLastName());
		initialize_text_field(lastName, "Last Name: " + user.getLastName(), 460, 120);

		password = new JPasswordField("Password");
		initialize_password_field(password, "Password", 140, 180);

		repassword = new JPasswordField("Re-type Password");
		initialize_password_field(repassword, "Re-type Password", 460, 180);

		phone = new JTextField("Phone: " + user.getPhone());
		initialize_text_field(phone, "Price: " + "Phone: " + user.getPhone(), 140, 240);

		address = new JTextField("Shipping Address: " + user.getAddress());
		initialize_text_field(address, "Shipping Address: " + user.getAddress(), 460, 240);

		submit = new JButton("Submit");
		initialize_button(submit, "Submit", 290, 550);
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		cancel = new JButton("Cancel");
		initialize_button(cancel, "Cancel", 460, 550);
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		logout = new JButton("Logout");
		initialize_button(logout, "Logout", 1000, 10);
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		imgIcon = new ImageIcon("Books.jpg");
		image = new JLabel(imgIcon);
		image.setBounds(850, 250, imgIcon.getIconWidth(), imgIcon.getIconHeight());
		getContentPane().add(image);
	}

	private static void initialize_text_field(JTextField field, String name, int x, int y) {
		// field.setForeground(Color.LIGHT_GRAY);
		String prefix = name.substring(0, name.indexOf(':'));
		field.setHorizontalAlignment(SwingConstants.CENTER);
		field.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		field.setBounds(x, y, 300, 50);
		field.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (field.getText().length() < prefix.length()) {
					field.setText(name);
					// field.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if (field.getText().length() < prefix.length()) {
					field.setText(name);
					// field.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

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
	
	private static void initialize_password_field(JPasswordField pass, String name, int x, int y){
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