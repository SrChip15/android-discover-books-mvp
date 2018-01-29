package com.example.android.bookfindr.model;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BooksInteractorImpl implements BooksInteractor {

	GoogleBooksService service;

	public BooksInteractorImpl() {
		// Configure Retrofit
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://www.googleapis.com")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		// Create service
		service = retrofit.create(GoogleBooksService.class);
	}

	@Override
	public Call<SearchResults> search(String search) {
		return service.search("search+" + search);
	}
}
