package com.example.android.bookfindr;


public class Book {

	private String title;
	private String authors;
	private float rating;
	private float price;

	/**
	 * Create book object
	 *
	 * @param title   title of the book
	 * @param authors author of the book
	 * @param rating  average rating for the book
	 * @param price   retail price of the book
	 */
	Book(String title, String authors, float rating, float price) {
		this.title = title;
		this.authors = authors;
		this.rating = rating;
		this.price = price;
	}

	/**
	 * Return the title information of the book
	 *
	 * @return the title of the book
	 */
	String getTitle() {
		return title;
	}

	/** Return the author of the book */
	String getAuthor() {
		return authors;
	}

	/** Return the average rating of the book */
	float getRating() {
		return rating;
	}

	/** Return the retail price of the book */
	float getPrice() {
		return price;
	}
}
