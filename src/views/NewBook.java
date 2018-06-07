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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NewBook extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton submit, cancel, logout;
	private JTextField isbn, title, publisher, publication_year, selling_price, category, threshold, quantity;
	private JTextArea authors;
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
					NewBook window = new NewBook();
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
	public NewBook() {
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

		note = new JLabel("Add a new book");
		note.setFont(new Font("Tahoma", Font.PLAIN, 18));
		note.setBounds(50, 0, 700, 50);
		note.setForeground(Color.BLACK);
		getContentPane().add(note);

		isbn = new JTextField("ISBN");
		initialize_text_field(isbn, "ISBN", 140, 120);

		title = new JTextField("Title");
		initialize_text_field(title, "Title", 460, 120);

		category = new JTextField("Category");
		initialize_text_field(category, "Category", 140, 180);

		publisher = new JTextField("Publisher");
		initialize_text_field(publisher, "Publisher", 460, 180);

		selling_price = new JTextField("Price");
		initialize_text_field(selling_price, "Price", 140, 240);

		publication_year = new JTextField("Publication Year");
		initialize_text_field(publication_year, "Publication Year", 460, 240);

		threshold = new JTextField("Minimum Quantity");
		initialize_text_field(threshold, "Minimum Quantity", 140, 300);

		quantity = new JTextField("Current Quantity");
		initialize_text_field(quantity, "Current Quantity", 460, 300);

		authors = new JTextArea("Author1\nAuthor2\n...");
		initialize_text_area(authors, "Author1\nAuthor2\n...", 300, 360);

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
		button.setFont(new Font("Hobo Std", Font.BOLD, 15));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		content.add(button);
	}

	private static void initialize_text_area(JTextArea field, String name, int x, int y) {
		field.setForeground(Color.LIGHT_GRAY);
		field.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		field.setBounds(x, y, 300, 170);
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
}
