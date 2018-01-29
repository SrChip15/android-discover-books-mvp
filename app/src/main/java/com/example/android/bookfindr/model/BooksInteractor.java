package com.example.android.bookfindr.model;


import retrofit2.Call;

public interface BooksInteractor {
	Call<SearchResults> search(String search);
}
