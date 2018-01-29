package com.example.android.bookfindr.view;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.bookfindr.R;

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
