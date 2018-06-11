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

import controller.Assignment;
import controller.Condition;
import controller.Connector;
import controller.Excuter;
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
	static EditInfo window;
	String emailt="mo@";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new EditInfo();
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
	public EditInfo(String email) {
		this.emailt=email;
		initialize();
	}
	public EditInfo() {
		initialize();
	}

	String tr(String t){
		for(int i=0;i < t.length();i++){
			if(t.charAt(i)==':'){
				return t.substring(i+1, t.length());
				
			}
			
		}
		
		return t;
		
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
		ResultSet rs;
		Excuter ex;
		try {
			ex = new Excuter(Connector.getInstance());
			Condition emailc=new Condition("email","=","\""+emailt+"\"");
			ArrayList<Condition> conditions = new ArrayList<>(Arrays.asList(emailc));
			ArrayList<String> colNames = new ArrayList<>(Arrays.asList("*"));
			rs = ex.selectConditional("user", colNames, conditions, true, 0);
			rs.next();
			username = new JTextField("Username:"+rs.getString("username") );
			initialize_text_field(username, "Username:"+rs.getString("username"), 140, 120);

			email = new JTextField("Email:"+rs.getString("email") );
			initialize_text_field(email, "Email:"+rs.getString("email") , 460, 120);

			firstName = new JTextField("First Name:"+rs.getString("first_name") );
			initialize_text_field(firstName, "First Name:"+rs.getString("first_name") , 780, 180);

			lastName = new JTextField("Last Name:"+rs.getString("last_name") );
			initialize_text_field(lastName, "Last Name:"+rs.getString("last_name") , 780, 120);

			password = new JPasswordField("Password:"+rs.getString("password"));
			initialize_password_field(password, "Password:"+rs.getString("password"), 140, 180);

			repassword = new JPasswordField("Re-type Password:"+rs.getString("password"));
			initialize_password_field(repassword, "Re-type Password:"+rs.getString("password"), 460, 180);

			phone = new JTextField("Phone:"+rs.getString("phone_number") );
			initialize_text_field(phone,"Phone:"+rs.getString("phone_number"), 140, 240);

			address = new JTextField("Shipping Address:"+rs.getString("shipping_address") );
			initialize_text_field(address, "Shipping Address:"+rs.getString("shipping_address") , 460, 240);

			submit = new JButton("Submit");
			initialize_button(submit, "Submit", 290, 550);
		} catch (SQLException | ClassNotFoundException e1) {
			int pane = JOptionPane.showConfirmDialog(window,
	                 "error in connection", "ERROR",JOptionPane.DEFAULT_OPTION);
			e1.printStackTrace();
		}
		
		
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tr(password.getText()).compareTo(tr(repassword.getText())) != 0) {
					System.out.println(password.getText() + repassword.getText());
					int pane = JOptionPane.showConfirmDialog(window, "please make sure you wrote password correctly",
							"ERROR", JOptionPane.DEFAULT_OPTION);
					// window.dispatchEvent(new WindowEvent(window,
					// WindowEvent.WINDOW_CLOSING));
					return;
				}
				if (tr(password.getText()).length() < 3) {
					int pane = JOptionPane.showConfirmDialog(window, "please length should be => 3", "ERROR",
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
				if (!java.util.regex.Pattern.matches("\\d+", tr(phone.getText()))) {
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
							"first_name", "last_name", "phone_number", "shipping_address"));
					ArrayList<String> colVal = new ArrayList<>(
							Arrays.asList("\""+tr(username.getText())+"\"", "\""+tr(email.getText())+"\"", "\""+tr(password.getText())+"\"", "\""+tr(firstName.getText())+"\"",
									"\""+tr(lastName.getText())+"\"", "\""+tr(phone.getText())+"\"","\""+ tr(address.getText())+"\""));
					ArrayList<Assignment> ass= new ArrayList<Assignment>();
					for(int i=0 ;i<colNames.size();i++){
						ass.add(new Assignment(colNames.get(i),colVal.get(i)));
					}
					Condition emailc=new Condition("email","=","\""+emailt+"\"");
					System.out.println(emailt);
					ArrayList<Condition> conditions = new ArrayList<>(Arrays.asList(emailc));
					ex.update("user", ass, conditions ,true);
					
					EventQueue.invokeLater(new Runnable() {
						@Override
						public void run() {
							try {
								
								dispose();
								emailt=tr(email.getText());
								SignInForm window = new SignInForm();
								window.setVisible(true);								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});

				} catch (SQLException | ClassNotFoundException e) {
					int pane = JOptionPane.showConfirmDialog(window, "same email or username or phone number exists or error in database happened",
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
				Connector conn ;
				Excuter ex;
				try {
					ex = new Excuter(Connector.getInstance());
					ArrayList<String> colNames = new ArrayList<>(Arrays.asList("email","privilege"));
					Condition emailc=new Condition("email","=","\""+emailt+"\"");
					ArrayList<Condition> conditions = new ArrayList<>(Arrays.asList(emailc));
					ResultSet rs=ex.selectConditional("user",colNames,conditions,true,0);
					rs.next();
						if(rs.getInt("privilege")==0){
							EventQueue.invokeLater(new Runnable() {
								@Override
								public void run() {
									try {
										dispose();
										UserHome window = new UserHome(emailt);
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
										dispose();
										ManagerHome window = new ManagerHome(emailt);
										window.setVisible(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
							
							
							
						}
						
						
					
				} catch (SQLException | ClassNotFoundException e) {
					int pane = JOptionPane.showConfirmDialog(window,
			                 "error in connection", "ERROR",JOptionPane.DEFAULT_OPTION);
					//window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
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
					Condition emailc=new Condition("email","=","\""+emailt+"\"");
					ArrayList<Condition> conditions = new ArrayList<>(Arrays.asList(emailc));
					boolean rs = ex.delete("cart",conditions,true);
				} catch (SQLException | ClassNotFoundException e1) {
					int pane = JOptionPane.showConfirmDialog(window,
			                 "error in connection", "ERROR",JOptionPane.DEFAULT_OPTION);
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
