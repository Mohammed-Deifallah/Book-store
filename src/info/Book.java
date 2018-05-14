package info;

import java.util.ArrayList;

public class Book {
	private String isbn, publisher, title, category;
	private int year, threshold, quantity;
	private float price;
	private ArrayList<String> authors;

	public Book(String isbn, String publisher, String title, String category, int year, int threshold, int quantity,
			float price, ArrayList<String> authors) {
		this.isbn = isbn;
		this.publisher = publisher;
		this.year = year;
		this.threshold = threshold;
		this.quantity = quantity;
		this.title = title;
		this.price = price;
		this.category = category;
		this.authors = authors;
	}

	public String getISBN() {
		return isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getTitle() {
		return title;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> getAuthors() {
		return (ArrayList<String>) authors.clone();
	}

	public String getCategory() {
		return category;
	}

	public float getPrice() {
		return price;
	}

	public int getYear() {
		return year;
	}

	public int getThreshold() {
		return threshold;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setISBN(String isbn) {
		this.isbn = isbn;
	}

	public void setPublisher(String pub) {
		publisher = pub;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
