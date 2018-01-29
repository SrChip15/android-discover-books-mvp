package com.example.android.bookfindr.view;


import com.example.android.bookfindr.model.Book;

import java.util.List;

public interface BooksView {
	void updateUi(List<Book> books);
}
