package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class SearchBook extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton submit, cancel;
	private JTextField search;
	private JTextArea result;
	private ImageIcon imgIcon;
	private JScrollPane scroll;
	private JLabel note, image, key, res;
	private JComboBox<String> list;
	private final String options[] = { "ISBN", "Title", "Category", "Author", "Publisher" };
	private static Container content;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SearchBook window = new SearchBook();
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
	public SearchBook() {
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

		key = new JLabel("Search Key: ");
		key.setBounds(620, 10, 100, 20);
		content.add(key);

		list = new JComboBox<>(options);
		list.setBounds(700, 10, 100, 20);
		list.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					change_hint(options[list.getSelectedIndex()]);
				}
			}
		});
		content.add(list);

		note = new JLabel("Search for a book");
		note.setFont(new Font("Tahoma", Font.PLAIN, 18));
		note.setBounds(50, 0, 700, 50);
		note.setForeground(Color.BLACK);
		getContentPane().add(note);

		res = new JLabel("Result: ");
		res.setBounds(10, 50, 60, 20);
		content.add(res);

		result = new JTextArea();
		result.setEditable(false);
		scroll = new JScrollPane(result, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(10, 70, 200, 400);
		content.add(scroll);

		search = new JTextField("ISBN");
		initialize_text_field(search, "ISBN", 300, 120);

		submit = new JButton("Submit");
		initialize_button(submit, "Submit", 290, 240);
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		cancel = new JButton("Cancel");
		initialize_button(cancel, "Cancel", 460, 240);
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

	private void change_hint(String key) {
		// initialize_text_field(search, key, 300, 120);
		content.remove(search);
		search = new JTextField(key);
		initialize_text_field(search, key, 300, 120);
	}
}
