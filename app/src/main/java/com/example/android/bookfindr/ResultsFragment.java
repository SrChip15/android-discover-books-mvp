package com.example.android.bookfindr;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.bookfindr.model.Book;
import com.example.android.bookfindr.model.SearchResults;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultsFragment extends Fragment {

	BookAdapter adapter;
	@BindView(R.id.search_results_rv)
	EffectiveRecyclerView recyclerView;
	@BindView(R.id.empty_state_tv)
	TextView emptyStateTextView;
	@BindView(R.id.progress_bar)
	ProgressBar progressBar;
	GoogleBooksService service;
	private String userInput;

	public static final String USER_INPUT = "userInput";

	public ResultsFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		Bundle args = getArguments();
		if (args != null) this.userInput = args.getString(USER_INPUT);

		// Configure Retrofit
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://www.googleapis.com")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		// Create service
		service = retrofit.create(GoogleBooksService.class);
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.search_results, container, false);
		ButterKnife.bind(this, view);

		adapter = new BookAdapter(getActivity());
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setHasFixedSize(true);
		recyclerView.setAdapter(adapter);

		performSearch(this.userInput);
		return view;
	}

	private void performSearch(String userInput) {
		String formatUserInput = userInput.trim().replaceAll("\\s+", "+");
		service.search("search+" + formatUserInput).enqueue(new Callback<SearchResults>() {
			@Override
			public void onResponse(@NonNull Call<SearchResults> call,
			                       @NonNull Response<SearchResults> response) {
				progressBar.setVisibility(View.GONE);
				updateUi(response.body().getBooks());
			}

			@Override
			public void onFailure(@NonNull Call<SearchResults> call, @NonNull Throwable t) {
				t.printStackTrace();
			}
		});

	}

	private void updateUi(List<Book> books) {
		if (books.isEmpty()) {
			emptyStateTextView.setVisibility(View.VISIBLE);
		} else {
			emptyStateTextView.setVisibility(View.GONE);
		}
		adapter.clearData();
		adapter.setData(books);
	}
}
