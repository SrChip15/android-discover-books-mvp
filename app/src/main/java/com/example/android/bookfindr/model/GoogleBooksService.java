package com.example.android.bookfindr.model;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleBooksService {
	@GET("books/v1/volumes")
	Call<SearchResults> search(@Query("q") String search);
}
