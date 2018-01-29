package com.example.android.bookfindr.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResults {
	@SerializedName("items")
	private List<Book> books;

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
