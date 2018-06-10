package views;

import java.awt.Color;
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

import controller.*;

public class SignInForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton submit;
	private JTextField username;
	private JPasswordField password;
	private ImageIcon imgIcon;
	private JLabel note, image;
	static SignInForm window;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new SignInForm();
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
	public SignInForm() {
		System.out.println("Love".substring(0, 0));
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

		note = new JLabel("Sign in to your account");
		note.setFont(new Font("Tahoma", Font.PLAIN, 18));
		note.setBounds(50, 0, 700, 50);
		note.setForeground(Color.BLACK);
		getContentPane().add(note);

		username = new JTextField("Email");
		username.setForeground(Color.LIGHT_GRAY);
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		username.setBounds(320, 120, 300, 50);
		username.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (username.getText().equals("Email")) {
					username.setText("");
					username.setForeground(Color.black);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (username.getText().equals("")) {
					username.setText("Username or Email");
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
					username.setText("Username or Email");
					username.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (username.getText().equals("Username or Email")) {
					username.setText("");
					username.setForeground(Color.black);
				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if (username.getText().equals("")) {
					username.setText("Username or Email");
					username.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (username.getText().equals("Username or Email")) {
					username.setText("");
					username.setForeground(Color.black);
				}
			}
		});
		getContentPane().add(username);

		password = new JPasswordField("Password");
		password.setForeground(Color.LIGHT_GRAY);
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		password.setBounds(320, 260, 300, 50);
		password.setEchoChar((char) 0);
		password.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (new String(password.getPassword()).equals("Password")) {
					password.setText("");
					password.setEchoChar('.');
					password.setForeground(Color.black);
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
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
				if (new String(password.getPassword()).equals("")) {
					password.setText("Password");
					password.setEchoChar((char) 0);
					password.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (new String(password.getPassword()).equals("Password")) {
					password.setText("");
					password.setEchoChar('.');
					password.setForeground(Color.black);
				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
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
				if (new String(password.getPassword()).equals("Password")) {
					password.setText("");
					password.setEchoChar('.');
					password.setForeground(Color.black);
				}
			}
		});
		getContentPane().add(password);

		submit = new JButton("Submit");
		submit.setBounds(400, 400, 150, 50);
		submit.setBackground(Color.LIGHT_GRAY);
		submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connector conn ;
				Excuter ex;
				try {
					ex = new Excuter(Connector.getInstance());
					ArrayList<String> colNames = new ArrayList<>(Arrays.asList("email","privilege"));
					Condition emailc=new Condition("email","=","\""+username.getText()+"\"");
					Condition passwordc=new Condition("password","=","\""+password.getText()+"\"");
					ArrayList<Condition> conditions = new ArrayList<>(Arrays.asList(emailc,passwordc));
					ResultSet rs=ex.selectConditional("user",colNames,conditions,true,0);
					if(rs.next()==false){
						int pane = JOptionPane.showConfirmDialog(window,
				                 "incorrect email or password", "ERROR",JOptionPane.DEFAULT_OPTION);
						
					}else{
						if(rs.getInt("privilege")==0){
							EventQueue.invokeLater(new Runnable() {
								@Override
								public void run() {
									try {
										window.setVisible(false);
										UserHome window = new UserHome(username.getText());
										window.setVisible(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
							
						}else{
							EventQueue.invokeLater(new Runnable() {
								@Override
								public void run() {
									try {
										window.setVisible(false);
										ManagerHome window = new ManagerHome(username.getText());
										window.setVisible(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
							
							
							
						}
						
						
					}
				} catch (SQLException | ClassNotFoundException e) {
					int pane = JOptionPane.showConfirmDialog(window,
			                 "error in connection", "ERROR",JOptionPane.DEFAULT_OPTION);
					//window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
					e.printStackTrace();
				}


				
			}
		});
		getContentPane().add(submit);

		imgIcon = new ImageIcon("Books.jpg");
		image = new JLabel(imgIcon);
		image.setBounds(850, 250, imgIcon.getIconWidth(), imgIcon.getIconHeight());
		getContentPane().add(image);
	}
}
