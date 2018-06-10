package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewCart extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton back, logout, calc;
	private String[] columnNames;
	private JLabel note, total;
	private JTable cart;
	private JScrollPane scroll;
	private static Container content;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ViewCart window = new ViewCart();
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
	public ViewCart() {
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

		note = new JLabel("View Cart");
		note.setFont(new Font("Tahoma", Font.PLAIN, 18));
		note.setBounds(50, 0, 700, 50);
		note.setForeground(Color.BLACK);
		getContentPane().add(note);

		back = new JButton("Back");
		initialize_button(back, "Back", 150, 240);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		calc = new JButton("Calculate Total Price");
		initialize_button(calc, "Calculate Total Price", 150, 120);
		calc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		columnNames = new String[] { "ISBN", "Title", "Price" };
		cart = new JTable(new Object[][] {}, columnNames);
		cart.setDefaultEditor(Object.class, null);

		scroll = new JScrollPane(cart);
		scroll.setBounds(400, 100, 300, 500);
		cart.setFillsViewportHeight(true);
		content.add(scroll);

		total = new JLabel("Total Price: ");
		total.setBounds(600, 80, 150, 10);
		content.add(total);

		logout = new JButton("Logout");
		initialize_button(logout, "Logout", 1000, 10);
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
	}

	private static void initialize_button(JButton button, String name, int x, int y) {
		button.setBounds(x, y, 200, 50);
		button.setBackground(Color.LIGHT_GRAY);
		button.setFont(new Font("Hobo Std", Font.BOLD, 15));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		content.add(button);
	}
}
