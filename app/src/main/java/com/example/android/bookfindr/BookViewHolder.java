package com.example.android.bookfindr;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.book_cover_tv)
	TextView bookTitle;

	public BookViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	public void setTitle(String title) {
		bookTitle.setText(title);
	}
}
