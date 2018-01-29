package com.example.android.bookfindr;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bookfindr.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends EffectiveRecyclerView.Adapter<BookViewHolder> {
	private Context context;
	private List<Book> books;

	public BookAdapter(Context context) {
		this.context = context;
		this.books = new ArrayList<>();
	}

	@Override
	public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View bookView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
		return new BookViewHolder(bookView);
	}

	@Override
	public void onBindViewHolder(BookViewHolder holder, int position) {
		Book current = books.get(position);
		holder.setTitle(current.getVolumeInfo().getTitle());
	}

	@Override
	public int getItemCount() {
		return books != null ? books.size() : 0;
	}

	public void setData(List<Book> books) {
		this.books.addAll(books);
		notifyDataSetChanged();
	}

	public void clearData() {
		this.books.clear();
	}
}
