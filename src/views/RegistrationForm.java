package views;

import java.awt.Color;
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

public class RegistrationForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton submit;
	private JTextField username, email;
	private JPasswordField password, repassword;
	private ImageIcon imgIcon;
	private JLabel note, image, tri;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationForm window = new RegistrationForm();
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

		note = new JLabel("Sign up");
		note.setFont(new Font("Tahoma", Font.PLAIN, 18));
		note.setBounds(50, 0, 700, 50);
		note.setForeground(Color.BLACK);
		getContentPane().add(note);

		username = new JTextField("Username");
		username.setForeground(Color.LIGHT_GRAY);
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		username.setBounds(320, 120, 300, 50);
		username.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (username.getText().equals("Username")) {
					username.setText("");
					username.setForeground(Color.black);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (username.getText().equals("")) {
					username.setText("Username");
					username.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		username.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (username.getText().equals("")) {
					username.setText("Username");
					username.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (username.getText().equals("Username")) {
					username.setText("");
					username.setForeground(Color.black);
				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if (username.getText().equals("")) {
					username.setText("Username");
					username.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (username.getText().equals("Username")) {
					username.setText("");
					username.setForeground(Color.black);
				}
			}
		});
		getContentPane().add(username);

		email = new JTextField("Email");
		email.setForeground(Color.LIGHT_GRAY);
		email.setHorizontalAlignment(SwingConstants.CENTER);
		email.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		email.setBounds(320, 180, 300, 50);
		email.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (email.getText().equals("Email")) {
					email.setText("");
					email.setForeground(Color.black);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (email.getText().equals("")) {
					email.setText("Email");
					email.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		email.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (email.getText().equals("")) {
					email.setText("Email");
					email.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (email.getText().equals("Email")) {
					email.setText("");
					email.setForeground(Color.black);
				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if (email.getText().equals("")) {
					email.setText("Email");
					email.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (email.getText().equals("Email")) {
					email.setText("");
					email.setForeground(Color.black);
				}
			}
		});
		getContentPane().add(email);

		password = new JPasswordField("Password");
		password.setForeground(Color.LIGHT_GRAY);
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		password.setBounds(320, 240, 300, 50);
		password.setEchoChar((char) 0);
		password.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				tri.setText(new String(password.getPassword()));
				if (new String(password.getPassword()).equals("Password")) {
					password.setText("");
					password.setEchoChar('.');
					password.setForeground(Color.black);
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				tri.setText(new String(password.getPassword()));
				if (new String(password.getPassword()).equals("")) {
					password.setText("Password");
					password.setEchoChar((char) 0);
					password.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		password.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				tri.setText(new String(password.getPassword()));
				if (new String(password.getPassword()).equals("")) {
					password.setText("Password");
					password.setEchoChar((char) 0);
					password.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				tri.setText(new String(password.getPassword()));
				if (new String(password.getPassword()).equals("Password")) {
					password.setText("");
					password.setEchoChar('.');
					password.setForeground(Color.black);
				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				tri.setText(new String(password.getPassword()));
				if (new String(password.getPassword()).equals("")) {
					password.setText("Password");
					password.setEchoChar((char) 0);
					password.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				tri.setText(new String(password.getPassword()));
				if (new String(password.getPassword()).equals("Password")) {
					password.setText("");
					password.setEchoChar('.');
					password.setForeground(Color.black);
				}
			}
		});
		getContentPane().add(password);

		repassword = new JPasswordField("Re-type Password");
		repassword.setForeground(Color.LIGHT_GRAY);
		repassword.setHorizontalAlignment(SwingConstants.CENTER);
		repassword.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		repassword.setBounds(320, 300, 300, 50);
		repassword.setEchoChar((char) 0);
		repassword.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				tri.setText(new String(repassword.getPassword()));
				if (new String(repassword.getPassword()).equals("Re-type Password")) {
					repassword.setText("");
					repassword.setEchoChar('.');
					repassword.setForeground(Color.black);
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				tri.setText(new String(repassword.getPassword()));
				if (new String(repassword.getPassword()).equals("")) {
					repassword.setText("Re-type Password");
					repassword.setEchoChar((char) 0);
					repassword.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		repassword.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				tri.setText(new String(repassword.getPassword()));
				if (new String(repassword.getPassword()).equals("")) {
					repassword.setText("Re-type Password");
					repassword.setEchoChar((char) 0);
					repassword.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				tri.setText(new String(repassword.getPassword()));
				if (new String(repassword.getPassword()).equals("Re-type Password")) {
					repassword.setText("");
					repassword.setEchoChar('.');
					repassword.setForeground(Color.black);
				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				tri.setText(new String(repassword.getPassword()));
				if (new String(repassword.getPassword()).equals("")) {
					repassword.setText("Re-type Password");
					repassword.setEchoChar((char) 0);
					repassword.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				tri.setText(new String(repassword.getPassword()));
				if (new String(repassword.getPassword()).equals("Re-type Password")) {
					repassword.setText("");
					repassword.setEchoChar('.');
					repassword.setForeground(Color.black);
				}
			}
		});
		getContentPane().add(repassword);

		submit = new JButton("Submit");
		submit.setBounds(400, 400, 150, 50);
		submit.setBackground(Color.LIGHT_GRAY);
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
	}
}
