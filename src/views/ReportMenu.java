package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ReportMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton totalSales, topBooks, topCustomers, cancel, logout;
	private ImageIcon imgIcon;
	private JLabel note, image;
	private static Container content;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ReportMenu window = new ReportMenu();
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
	public ReportMenu() {
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

		note = new JLabel("View Report");
		note.setFont(new Font("Tahoma", Font.PLAIN, 18));
		note.setBounds(50, 0, 700, 50);
		note.setForeground(Color.BLACK);
		getContentPane().add(note);

		totalSales = new JButton("Total Sales");
		initialize_button(totalSales, "Total Sales", 100, 200);
		totalSales.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		topBooks = new JButton("Top 10 Books");
		initialize_button(topBooks, "Top 10 Books", 300, 200);
		topBooks.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		topCustomers = new JButton("Top 5 Customers");
		initialize_button(topCustomers, "Top 5 Customers", 500, 200);
		topCustomers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		cancel = new JButton("Cancel");
		initialize_button(cancel, "Cancel", 300, 275);
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

	private static void initialize_button(JButton button, String name, int x, int y) {
		button.setBounds(x, y, 180, 50);
		button.setBackground(Color.LIGHT_GRAY);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setFont(new Font("Hobo Std", Font.BOLD, 15));
		content.add(button);
	}
}
