package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import info.Book;

public class EditBook extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton submit, cancel;
	private JTextField isbn, title, publisher, publication_year, selling_price, category, threshold, quantity;
	private JTextArea authors;
	private ImageIcon imgIcon;
	private JLabel note, image;
	private static Container content;
	private Book book;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ArrayList<String> a = new ArrayList<>();
					a.add("Taha");
					a.add("Omar");
					Book b = new Book("isbn", "publisher", "title", "category", 2018, 5, 7, (float) 2.25, a);
					EditBook window = new EditBook(b);
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
	public EditBook(Book book) {
		this.book = book;
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

		note = new JLabel("Modify Book: \"" + book.getTitle() + "\", ISBN: " + book.getISBN());
		note.setFont(new Font("Tahoma", Font.PLAIN, 18));
		note.setBounds(50, 0, 700, 50);
		note.setForeground(Color.BLACK);
		getContentPane().add(note);

		isbn = new JTextField("ISBN: " + book.getISBN());
		initialize_text_field(isbn, "ISBN: " + book.getISBN(), 140, 120);

		title = new JTextField("Title: " + book.getTitle());
		initialize_text_field(title, "Title: " + book.getTitle(), 460, 120);

		category = new JTextField("Category: " + book.getCategory());
		initialize_text_field(category, "Category: " + book.getCategory(), 140, 180);

		publisher = new JTextField("Publisher: " + book.getPublisher());
		initialize_text_field(publisher, "Publisher: " + book.getPublisher(), 460, 180);

		selling_price = new JTextField("Price: " + book.getPrice());
		initialize_text_field(selling_price, "Price: " + book.getPrice(), 140, 240);

		publication_year = new JTextField("Publication Year: " + book.getYear());
		initialize_text_field(publication_year, "Publication Year: " + book.getYear(), 460, 240);

		threshold = new JTextField("Minimum Quantity: " + book.getThreshold());
		initialize_text_field(threshold, "Minimum Quantity: " + book.getThreshold(), 140, 300);

		quantity = new JTextField("Current Quantity: " + book.getQuantity());
		initialize_text_field(quantity, "Current Quantity: " + book.getQuantity(), 460, 300);

		authors = new JTextArea("Authors:\n" + getAuthors());
		initialize_text_area(authors, "Authors:\n" + getAuthors(), 300, 360);

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

	private static void initialize_text_area(JTextArea field, String name, int x, int y) {
		// field.setForeground(Color.LIGHT_GRAY);
		String prefix = name.substring(0, name.indexOf('\n'));
		field.setFont(new Font("Hobo Std", Font.PLAIN, 20));
		field.setBounds(x, y, 300, 170);
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

	private String getAuthors() {
		String s = "";
		for (int i = 0; i < book.getAuthors().size(); i++) {
			s += book.getAuthors().get(i) + '\n';
		}
		return s;
	}
}
