package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Welcome extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon imgIcon;
	private JLabel lblWelcomeToOur, lblAlreadyHaveAn, image;
	private JButton signUp, btnSignIn;
	static Welcome window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new Welcome();
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
	public Welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		getContentPane().setBackground(Color.ORANGE);
		// setBounds(100, 100, 450, 300);
		setSize(getMaximumSize());
		getContentPane().setLayout(null);

		lblWelcomeToOur = new JLabel("Welcome to our application !");
		lblWelcomeToOur.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToOur.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblWelcomeToOur.setBounds(295, 21, 389, 39);
		getContentPane().add(lblWelcomeToOur);

		signUp = new JButton("Get Started");
		signUp.setFont(new Font("Tahoma", Font.BOLD, 14));
		signUp.setForeground(Color.BLACK);
		signUp.setBackground(Color.GREEN);
		signUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
		signUp.setBounds(191, 200, 269, 39);
		getContentPane().add(signUp);

		btnSignIn = new JButton("Sign in");
		btnSignIn.setBackground(Color.ORANGE);
		btnSignIn.setBorderPainted(false);
		btnSignIn.setFocusPainted(false);
		btnSignIn.setContentAreaFilled(false);
		btnSignIn.setBounds(371, 250, 89, 23);
		btnSignIn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		Map attributes = btnSignIn.getFont().getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		btnSignIn.setFont(btnSignIn.getFont().deriveFont(attributes));

		getContentPane().add(btnSignIn);

		lblAlreadyHaveAn = new JLabel("already have an account ?");
		lblAlreadyHaveAn.setBounds(201, 254, 160, 14);
		getContentPane().add(lblAlreadyHaveAn);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		imgIcon = new ImageIcon("Books.jpg");
		image = new JLabel(imgIcon);
		image.setBounds(850, 250, imgIcon.getIconWidth(), imgIcon.getIconHeight());
		getContentPane().add(image);
		
		
		
		signUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					@Override
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
		});
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				window.setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
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
}
