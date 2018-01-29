package com.example.android.bookfindr.presenter;


import android.support.annotation.NonNull;

import com.example.android.bookfindr.model.BooksInteractor;
import com.example.android.bookfindr.model.SearchResults;
import com.example.android.bookfindr.view.BooksView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksPresenter {

	private BooksInteractor interactor;
	BooksView view;

	public BooksPresenter(BooksInteractor interactor) {
		this.interactor = interactor;
	}

	public void bind(BooksView view) {
		this.view = view;
	}

	public void unbind() {
		this.view = null;
	}

	public void performSearch(String userInput) {
		String formatUserInput = userInput.trim().replaceAll("\\s+", "+");
		interactor.search("search+" + formatUserInput).enqueue(new Callback<SearchResults>() {
			@Override
			public void onResponse(@NonNull Call<SearchResults> call,
			                       @NonNull Response<SearchResults> response) {
				if (view != null) view.updateUi(response.body().getBooks());
			}

			@Override
			public void onFailure(@NonNull Call<SearchResults> call, @NonNull Throwable t) {
				t.printStackTrace();
			}
		});

	}
}
